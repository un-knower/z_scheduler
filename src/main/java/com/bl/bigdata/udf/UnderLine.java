package com.bl.bigdata.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.UDF;

public class UnderLine extends UDF{

	public String evaluate(String dateStr){
		if(dateStr.length()==8){
			Date d =null;
			try {
				d = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String rst = new SimpleDateFormat("yyyy-MM-dd").format(d);
			return rst;
			
		}else if(dateStr.length()==10){
			String rst = dateStr.replace("-", "");
			return rst;
		}else{
			return null;
		}
	}

	public static void main(String args[]){
		UnderLine date2Str = new UnderLine();
		String evaluate = date2Str.evaluate("");
		System.out.println(evaluate);
	}
}
