package com.diaodu.util;

import java.io.*;
import java.util.Properties;

import com.google.common.base.Strings;
import groovy.ui.SystemOutputInterceptor;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.SimpleEmail;


/*
 * 功能发邮件
 * 2016-6-24 11:54:02
 * update 2016-10-8 14:13:37 添加加密解密功能
 */

public class MailUtil {

	public static void main(String[] args) throws IOException, EmailException {
//		String s="gp39";
//		System.out.println(mixCode(s));
//		String hack="bDXc':hO4g__1";
//		System.out.println(hackCode(hack));
		MailUtil.createPrivateMail(false);
	}


	public static HtmlEmail createPrivateMail(boolean needcc) throws EmailException, IOException {

		InputStream input = MailUtil.class.getClassLoader().getResourceAsStream("mail.properties");

		Properties properties = new Properties();
		properties.load(input);
		String to = (String)properties.get("to");
		String cc = (String)properties.get("cc");
		String from = (String)properties.get("from");
		String password = hackCode((String)properties.get("password"));
		String username = hackCode((String)properties.get("username"));
		HtmlEmail email = new HtmlEmail();
		email.setHostName("mail.bl.com");
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setFrom(from);
		if(needcc){
			if(!Strings.isNullOrEmpty(cc)){
				for(int i=0;i<cc.split(",").length;i++){
					email.addCc(cc.split(",")[i]);
				}
			}
		}
		if(!Strings.isNullOrEmpty(to)){
			for(int j=0;j<to.split(",").length;j++){
				email.addTo(to.split(",")[j]);
			}
		}
		return email;
	}



	//混淆字符串
	// 加密字符串

	//input  :syj125?gongpengllpp@sina.com
	//output :bDXc':hO4gdb[bXWbXWb0WaNQa`DT`bZgV`0J
	public static String mixCode(String x){
		StringBuffer sb = new StringBuffer();
		StringBuffer result = new StringBuffer();
		char[] chars = x.toCharArray();
		for (int i=0;i<chars.length;i++) {
			int j = chars[i];
			sb.append(1000-j-i);
			//System.out.println(1000-j-i);
		}
		char[] charArray = sb.toString().toCharArray();
		int mmm=0;
		for(int j=0;j<charArray.length;j++){
			int mm = Integer.parseInt(charArray[j]+"");
			if(j%2==1){
				mmm=mmm*10+mm+10;
				char xx= (char) mmm;
				result.append(xx);
			}else{
				mmm=mm;
			}
		}
		if(charArray.length%2!=0){
			result.append("__").append(charArray[charArray.length-1]);
		}
		return result.toString();
	}



public static String hackCode(String x){
	String tail="";
	if(x.split("__").length >1){
		tail=x.split("__")[1];
		x=x.split("__")[0];
	}

	StringBuffer sb = new StringBuffer();
	StringBuffer result = new StringBuffer();
	char[] chars = x.toCharArray();
	for(int i=0;i<chars.length;i++){
		int mm= chars[i];
		if(mm<20){
			sb.append("0");
		}
		sb.append(mm-10);
	}
	sb.append(tail);
	char[] charArray = sb.toString().toCharArray();
	int mmm=0;
	for(int j=0;j<charArray.length;j++){
		int mm = Integer.parseInt(charArray[j] + "");
		mmm=mmm*10+mm;
		if(j%3==2){
			int t=1000-mmm-j/3;
			char xx= (char) t;
			result.append(xx);
			mmm=0;
		}
	}

	return result.toString();

}




	
}
