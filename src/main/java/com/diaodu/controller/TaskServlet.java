package com.diaodu.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSONObject;
import com.diaodu.domain.Task;
import com.diaodu.service.ETLService;
import com.diaodu.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 创建任务
 */

public class TaskServlet extends HttpServlet {

	private static final long serialVersionUID = -8194525654889919217L;


	Logger log = LoggerFactory.getLogger(getClass());
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		PrintWriter writer = resp.getWriter();
		TaskService taskService = new TaskService();
		String op = req.getParameter("op");
		if("create".equals(op)||"c".equals(op)){
			Task task = getBeanFromReqeust(req);
			//如果传入的task有并不是默认的-1 id 那么更新操作变为 先删除后添加
			String message="";
			if(task.getId()!=-1){
				taskService.deleteTask(task.getId());
				message=message+"删除id为:"+task.getId()+"的task!";
			}
			//修改etl状态为已经调度:
			String etl_id_str = req.getParameter("etl_id");
			if(etl_id_str==null){
				log.info("未与etl关联的操作");
			}else{
				int etl_id= Integer.parseInt(etl_id_str);
				ETLService etlService = new ETLService();
				int count = etlService.updateSchedulingByID(etl_id, 1);
				if(count==1){
					message=message+"修改了ETL的为已调度状态<br/>";
				}else{
					message+="ETL状态修改失败!<br/>";
				}	
			}
			int rows = taskService.addTask(task);
			if(rows==1){
				message=message+"新增了task!<br/>";
			}else{
				message+="新增失败!<br/>";
			}
			writer.print(message);
			writer.close();
		}else if("update".equals(op)||"u".equals(op)){
			Task task = getBeanFromReqeust(req);
			int rows = taskService.updateTask(task);
			writer.println(rows);
			writer.close();
		}else if("delete".equals(op)||"d".equals(op)){
			String id=req.getParameter("id");
			int row = taskService.deleteTask(Integer.parseInt(id));
			writer.println(row);
			writer.close();
		}else if("exec".equals(op)){
			int id = Integer.parseInt(req.getParameter("id").trim());
			String taskdate = req.getParameter("taskdate");
			Task task = taskService.getTaskByID(id);
			Map<String, String> resultMap = taskService.executeTask(task,taskdate);
			JSONObject jsonObject = new JSONObject();
			String jsonString = jsonObject.toJSONString(resultMap);
			writer.println(jsonString);
			writer.close();
		}else{
			req.getRequestDispatcher("/WEB-INF/view/index").forward(req, resp);
		}
		
		
	}
	
	
	private Task getBeanFromReqeust(HttpServletRequest req){
		Task t = new Task();
		if(!req.getParameter("id").equals("")){
			t.setId(Integer.parseInt(req.getParameter("id")));
		}
		if(!req.getParameter("seq").equals("")){
			t.setSeq(Integer.parseInt(req.getParameter("seq")));
		}
		t.setTname(ifNullBlank(req.getParameter("tname")));
		t.setTdesc(ifNullBlank(req.getParameter("tdesc")));
		t.setBatchid(ifNullBlank(req.getParameter("batchid")));
		t.setTasktype(ifNullBlank(req.getParameter("tasktype")));
		t.setCommandpath(ifNullBlank(req.getParameter("commandpath")));
		t.setCommand(ifNullBlank(req.getParameter("command")));
		t.setArgs(ifNullBlank(req.getParameter("args")));
		return t;
	}
	
	private String ifNullBlank(Object object){
		if (object==null){
			return "-";
		}else{
			try {
				return URLDecoder.decode(""+object, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return "";
		}
		
	}
	
}
