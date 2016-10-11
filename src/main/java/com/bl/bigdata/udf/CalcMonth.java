package com.bl.bigdata.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name="calc_date",
value=" args : month,calc_month,format",
extended="example:\n  args : date,year,month,day,format \n"
		+"default date is today ,default fromat is yyyyMMdd \n"
		+"calc_date('20150608',0,0,-1,'yyyy-MM-dd') == 2015-06-07 \n"
		+"calc_date('20150608',0,-1,-1,'yyyy-MM-dd') == 2015-05-07 \n"
		+"calc_date('20150608',-1,0,-1,'yyyy-MM-dd') == 2014-06-07 \n"
)


public class CalcMonth extends UDF{

	public String evaluate(String date,int calc_month,String format){
		Date true_date = new Date();
		if(date!=null && (date.length()==7 || date.length()==6)){
			if(date.length()==7){
				try {
					true_date=new SimpleDateFormat("yyyy-MM").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(date.length()==6){
				try {
					true_date=new SimpleDateFormat("yyyyMM").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				return null;
			}
		}else{
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(true_date);
		calendar.add(Calendar.MONTH, calc_month);
		if(format==null||format.length()==0){
			format="yyyyMM";
		}
		String rst = new SimpleDateFormat(format).format(calendar.getTime());
		return rst;
	}

	public static void main(String args[]){
		CalcMonth date2Str = new CalcMonth();
		String evaluate = date2Str.evaluate("2015-08",-3,"yyyyMM");
		System.out.println(evaluate);
	}
}
