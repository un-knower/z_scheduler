package com.diaodu.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.diaodu.domain.Constants;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

public class SshUtil {
	
	private static final Logger log = LoggerFactory.getLogger(SshUtil.class);
	
	public static Map<String,String> execTest(String cmd){
		log.info("================测试集群================");
		return exec(Constants.TEST_IP,Constants.TEST_USERNAME,Constants.TEST_PASSWORD,Constants.TEST_PORT,cmd);
	}
	
	public static Map<String,String> execProduct(String cmd){
		log.info("================生产集群================");
		return exec(Constants.PRODUCT_IP,Constants.PRODUCT_USERNAME,Constants.PRODUCT_PASSWORD,Constants.PRODUCT_PORT,cmd);
	}
	

	/**
	   * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
	   * @param host	主机名
	   * @param user	用户名
	   * @param psw	密码
	   * @param port	端口
	   * @param command	命令
	   * @return
					key SPEND_TIME
					key EXIT_CODE
					key RUNNING_MESSAGE
					key CMD
					key START_DATE

	 */
	  public static Map<String,String> exec(String host,String user,String psw,int port,String command){
		Map <String,String> resultMap = new HashMap<String,String>();
		 resultMap.put(Constants.TASK_DATE,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
		long start_time = System.currentTimeMillis();
	    String result="";
	    Session session =null;
	    ChannelExec openChannel =null;
	    try {
	    	JSch jsch=new JSch();
			session = jsch.getSession(user, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(psw);
			session.connect();
			openChannel = (ChannelExec) session.openChannel("exec");
			openChannel.setPty(true);
			openChannel.setCommand(command);
			System.out.println("-------------------------");
			System.out.println(command);
			System.out.println("-------------------------");
			openChannel.connect();  
			InputStream in = openChannel.getInputStream();
			InputStream err = openChannel.getErrStream();
			BufferedReader errReader = new BufferedReader(new InputStreamReader(err));
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
			String buf = null;
			//result+="标准输出<br/>";
			while ((buf = reader.readLine()) != null) {
//				result+= new String(buf.getBytes("gbk"),"UTF-8")+"<br/>";  
				result+= new String(buf.getBytes())+"<br/>";  
			}  
			//result+="错误输出<br/>";
			while ((buf = errReader.readLine()) != null) {
				result+= new String(buf.getBytes())+"<br/>";  
			}  
	    } catch (JSchException | IOException e) {
	      result+=e.getMessage();
	    }finally{
	      if(openChannel!=null&&!openChannel.isClosed()){
	        openChannel.disconnect();
	      }
	      if(session!=null&&session.isConnected()){
	        session.disconnect();
	      }
	      long end_time = System.currentTimeMillis();
	      resultMap.put(Constants.SPEND_TIME,""+(end_time-start_time));
	      resultMap.put(Constants.EXIT_CODE, ""+openChannel.getExitStatus());
	      resultMap.put(Constants.RUNNING_MESSAGE, result);
	      resultMap.put(Constants.CMD,command);
	      resultMap.put(Constants.START_DATE,new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	    }
	    log.info(resultMap.get(Constants.RUNNING_MESSAGE));
	    return resultMap;
	  }
	  

	public static void main(String[] args) {
		Map<String, String> resultMap = execTest("pwd");
		Set<Entry<String, String>> entrySet = resultMap.entrySet();
		Iterator<Entry<String, String>> iterator = entrySet.iterator();
		while(iterator.hasNext()){
			Entry<String, String> next = iterator.next();
			System.out.println(next.getKey()+"     --------->      "+next.getValue());
		}
		
	}

}
