package com.bl.bigdata.udf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

@Description(name="calc_date",
value=" args : date,year,month,day,format lalalallallalalla",
extended="example:\n  args : date,year,month,day,format \n"
		+"default date is today ,default fromat is yyyyMMdd \n"
		+"calc_date('20150608',0,0,-1,'yyyy-MM-dd') == 2015-06-07 \n"
		+"calc_date('20150608',0,-1,-1,'yyyy-MM-dd') == 2015-05-07 \n"
		+"calc_date('20150608',-1,0,-1,'yyyy-MM-dd') == 2014-06-07 \n"
)


public class CalcDate extends UDF{

	public String evaluate(String date,int year,int month,int day,String format){
		Date true_date = new Date();
		if(date!=null && (date.length()==10 || date.length()==8)){
			if(date.length()==10){
				try {
					true_date=new SimpleDateFormat("yyyy-MM-dd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else if(date.length()==8){
				try {
					true_date=new SimpleDateFormat("yyyyMMdd").parse(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}else{
				return null;
			}
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(true_date);
		calendar.add(Calendar.YEAR, year);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.DATE, day);
		if(format==null||format.length()==0){
			format="yyyyMMdd";
		}
		String rst = new SimpleDateFormat(format).format(calendar.getTime());
		return rst;
	}

	public static void main(String args[]){
		CalcDate date2Str = new CalcDate();
		String evaluate = date2Str.evaluate("2015-08-04",0,0,-1,"yyyyMM");
		System.out.println(evaluate);
	}
}
