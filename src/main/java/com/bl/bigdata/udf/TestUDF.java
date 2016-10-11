package com.bl.bigdata.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
/**
 * calc_month
 * calc_year
 * calc_day
 * 
 * args <calc_month -1 >
 * 
 * 
 * 
 * 
 * @author GP39
 *
 */
public class TestUDF extends UDF{
	
	public Integer evaluate(String[] args){
		int s = args.length;
		return s;
	}
}
