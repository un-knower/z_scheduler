package com.diaodu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.diaodu.domain.Constants;
import com.diaodu.ssh.SshUtil;
import com.diaodu.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 2016-7-4 12:56:23
 * 用于查看前端到etl的依赖
 * 1.上传文件到sql文件夹,通过scp 替换,覆盖脚本
 * 2.生成shell脚本
 * 3.跑批shell
 * 	上传文件:
 * 	refer: http://www.cnblogs.com/xdp-gacl/p/4200090.html
 * 	解决 ssh sudo 没有权限:
 * 		需要开一个terminal
 * 	http://stackoverflow.com/questions/14220550/jsch-sudo-su-command-tty-error
 */
public class UtilServlet extends HttpServlet {

	private static final long serialVersionUID = -1536994094456952856L;

	Logger log = LoggerFactory.getLogger(getClass());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		String filename = "";
		String op = req.getParameter("op");
		if (op != null && op.equals("upload")) {
			req.getRequestDispatcher("/WEB-INF/view/util/upload.jsp").forward(req, resp);
		} else if (op != null && "toRerun".equals(op)) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar instance = Calendar.getInstance();
			instance.add(Calendar.DATE, -1);
			String d = sdf.format(instance.getTime());
			req.setAttribute("d", d);
			req.getRequestDispatcher("/WEB-INF/view/util/rerun.jsp").forward(req, resp);
		} else if (op != null && op.equals("rerun")) {
			String start_date = req.getParameter("start_date");
			String end_date = req.getParameter("end_date");
			String script = req.getParameter("script");
			String is_custom = req.getParameter("is_custom");
			String custom = req.getParameter("custom");
			StringBuffer message = new StringBuffer();
			if (!script.endsWith("sh")) {
				script = script + ".sh";
			}
			script = "/home/bl/ETL/" + script;
			message.append("运行脚本文件为:" + script).append("<br/>");
			// 自定义日期
			List<String> dateList = new ArrayList<String>();
			if (is_custom.equals("1")) {
				String[] split = custom.split(",");
				for (int i = 0; i < split.length; i++) {
					dateList.add(split[i].trim());
				}
			} else {
				if (start_date != null && end_date != null && script != null) {
					List<String> taskList = null;
					try {
						taskList = DateUtils.getTaskList(start_date, end_date);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					dateList.addAll(taskList);
				}
			}
			if (dateList.size() > 25) {
				message.append("运行跳过:重跑日期建议改为25天以内!<br/>");
			} else {
				for (String date : dateList) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					message.append(sdf.format(new Date())).append("-----------start <br/>");
					message.append("process ----------- date :" + date).append("<br/>");
					Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22, "sudo -S -p 'bl' -u hive  " + script + " " + date);
					message.append(exec.get(Constants.RUNNING_MESSAGE));
					message.append("运行时间为:").append(exec.get(Constants.SPEND_TIME)).append("<br/>");
					if (exec.get(Constants.EXIT_CODE).equals("0")) {
						message.append("<font color='green'>运行成功</font><br/>");
					} else {
						message.append("<font color='red'>运行失败</font><br/>");
					}
					message.append(sdf.format(new Date())).append("-----------end  <br/>");
					message.append("<br/>");
				}
			}
			PrintWriter out = resp.getWriter();
			out.print(message.toString());
			out.close();
		} else if (op != null && op.equals("view")) {
			// 查看当前的shell脚本
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22, "ls -ltr /home/bl/ETL/*.sh");
			exec.put("sort", "按照时间的降序排列");
			json.put("map", exec);
			out.print(json);
			out.close();
		}else if(op !=null && op.equals("viewShell")){
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
            String table = req.getParameter("script");
			Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22, "cat /home/bl/ETL/"+table+".sh");
            String message = exec.get(Constants.RUNNING_MESSAGE);
            json.put("message",message);
            out.print(json);
            out.close();

		}else if (op != null && op.equals("viewSQL")) {
			// 查看当前的sql脚本
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22, "ls -ltr /home/bl/ETL/sql/*.q");
			exec.put("sort", "按照时间的降序排列");
			json.put("map", exec);
			out.print(json);
			out.close();
		} else if (op != null && op.equals("viewCrontab")) {
			// 查看当前的sql脚本
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22,
					"sudo -S -p 'bl' -u hive  crontab -l");
			exec.put("sort", "按照时间的降序排列");
			json.put("map", exec);
			out.print(json);
			out.close();
		} else if (op != null && op.equals("createShell")) {
			String is_inc = req.getParameter("is_inc");
			String is_partation = req.getParameter("is_partation");
			String table = req.getParameter("script");
			String delete_column = req.getParameter("delete_column");
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			String message = "";
			if (StringUtils.isEmpty(table)) {
				message = "表名有误,文件创建失败";
			} else {
				table = table.toLowerCase();
				Map<String, String> exec = SshUtil.exec("10.201.48.101", "bl", "bl", 22,
						"/home/bl/app/new_template/shell_creator.sh " + table + " " + is_inc + " " + is_partation + " "
								+ delete_column);
				if (exec.get(Constants.EXIT_CODE).equals("0")) {
					message += "创建成功";
				} else {
					message += "创建失败";
				}
				message = message + "<br/>" + exec.get(Constants.RUNNING_MESSAGE);
			}
			json.put("message", message);
			out.print(json);
			out.close();
		} else if (op != null && op.equals("toCreateShell")) {
			req.getRequestDispatcher("/WEB-INF/view/util/shell_creator.jsp").forward(req, resp);
		} else if (op != null && op.equals("viewSQLContent")) {
			String table_name = req.getParameter("script");
			if (!table_name.endsWith("q")) {
				table_name = table_name + ".q";
			}
			PrintWriter out = resp.getWriter();
			JSONObject json = new JSONObject();
			Map<String, String> exec = SshUtil.exec("10.201.48.26", "bl", "bl", 22,
					"cat /home/bl/ETL/sql/" + table_name);
			String x = exec.get(Constants.RUNNING_MESSAGE);
			x.replace("<br/><br/>", "<br/>");
			exec.put(Constants.RUNNING_MESSAGE, x);
			json.put("map", exec);
			out.print(json);
			out.close();
		} else {
			// 默认的保存路径为: /tmp/upload
			// 默认的26上的存放路径为 /tmp/
			String savePath = "/tmp/upload";
			File file = new File(savePath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdir();
			}
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setHeaderEncoding("UTF-8");
				if (!ServletFileUpload.isMultipartContent(req)) {
					return;
				}
				List<FileItem> list = upload.parseRequest(req);
				for (FileItem item : list) {
					if (item.isFormField()) {
						String name = item.getFieldName();
						// 解决普通输入项的数据的中文乱码问题
						String value = item.getString("UTF-8");
						// value = new
						// String(value.getBytes("iso8859-1"),"UTF-8");
						System.out.println(name + "=" + value);
					} else {// 如果fileitem中封装的是上传文件
						// 得到上传的文件名称，
						filename = item.getName();
						System.out.println(filename);
						if (filename == null || filename.trim().equals("")) {
							continue;
						}
						// 注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：
						// c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
						// 处理获取到的上传文件的文件名的路径部分，只保留文件名部分
						filename = filename.substring(filename.lastIndexOf("/") + 1);
						// 获取item中的上传文件的输入流
						InputStream in = item.getInputStream();
						// 创建一个文件输出流
						// File newFile = new File(savePath + "\\" + filename);
						// newFile.createNewFile();

						FileOutputStream out = new FileOutputStream(savePath + "/" + filename);
						// 创建一个缓冲区
						byte buffer[] = new byte[1024];
						// 判断输入流中的数据是否已经读完的标识
						int len = 0;
						// 循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
						while ((len = in.read(buffer)) > 0) {
							// 使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath +
							// "\\"
							// + filename)当中
							out.write(buffer, 0, len);
						}
						// 关闭输入流
						in.close();
						// 关闭输出流
						out.close();
						// 删除处理文件上传时生成的临时文件
						item.delete();
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			// 本地上传到101的temp文件夹下
			// FileInputStream in = new FileInputStream(new
			// File("/tmp/upload/"+filename));
			// boolean flag = FtpApache.uploadFile("10.201.48.26", 21,
			// "bl","bl", "/home/bl/upload/", filename, in);

			// 101上传到26
			log.info("远程拷贝");
			if (filename.endsWith("q")) {
				Map<String, String> exec = SshUtil.exec("10.201.48.101", "bl", "bl", 22,
						"scp /tmp/upload/" + filename + " bl@10.201.48.26:/home/bl/ETL/sql/" + filename);
				req.setAttribute("scpinfo", exec.get(Constants.RUNNING_MESSAGE));
				req.setAttribute("filename", filename);
				if (exec.get(Constants.EXIT_CODE).equals("0")) {
					req.setAttribute("message", "<font color='green'>上传成功</font>");
				} else {
					req.setAttribute("message", "<font color='red'>上传失败</font>");
				}
			} else {
				req.setAttribute("message", "<font color='red'>文件格式必须以 .q 结尾</font>");
			}

			log.info("远程拷贝 完成");

			req.getRequestDispatcher("/WEB-INF/view/util/upload.jsp").forward(req, resp);
		}

	}

}
