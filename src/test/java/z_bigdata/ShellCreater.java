package z_bigdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.io.FileUtils;

public class ShellCreater {
	/*
	 * m_mem_kip_store_day.q           时间参数名称: ${SDATE}                                    时间格式：yyyymmdd       频率：每日/次                                            数据周期：  T+1
		按周统计：m_mem_kip_store_week.q       时间参数名称: ${SDATE},${EDATE}                  时间格式：yyyymmdd,      频率： 每周/次(每周一)                          数据周期： 上周一到周日
		按月统计：m_mem_kip_store_month.q     时间参数名称: ${SMONTH},                              时间格式：yyyymm            频率：每月/次(每月1号)                       数据周期：  每月数据
		按季度统计：m_mem_kip_store_quar.q
	 */
	public static String tableName="m_da_goods_first_up_list";
	public static Boolean isInc=true;
	public static String path="d://shell//";
	public static String target_file=path+tableName+".sh";
	public static String demo_file=path+"m_sr_eg_sku_top20.sh";
	

	public static void main(String[] args) throws Exception {
		File target = createFile();
		File in_file = new File(demo_file);
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(in_file)));
		String line=null;
		StringBuffer sb =new StringBuffer();
		while((line=reader.readLine())!=null){
			System.out.println(line);
			line = line.replace("m_sr_eg_sku_top20", tableName);
			line = line.replace("M_SR_EG_SKU_TOP20", tableName.toUpperCase());
			if(line.contains("oracle@10.201.48.18")){
				//增量
				if(isInc){
					line="check_if_not_zero_exit  ssh oracle@10.201.48.18 \"/home/oracle/inc_clearoracletb.sh "+tableName+" ${task_date}\"";
				}
			}
			if(line.contains("sqoop export")){
				//增量
				if(isInc){
					line = line.replace(" --fields-terminated-by", "dt=${task_date}/ --fields-terminated-by");
				}
			}
			sb.append(line+"\r\n");
		}
		
		FileUtils.writeStringToFile(target, sb.toString());
	}
	
	
	

	private static File createFile() throws IOException{
        File target = new File(target_file);
        if(!target.exists()){
        	target.createNewFile();
        }else{
        	target.delete();
        }
        return target;
    }

}
