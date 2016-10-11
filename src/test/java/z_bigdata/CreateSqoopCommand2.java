package z_bigdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class CreateSqoopCommand2 {

	public static void main(String[] args) throws Exception {
		String content = getDependency();
		File file = new File("d://sqoop.txt");
		if(file.exists()){
			file.delete();
		}
		FileUtils.write(new File("d://sqoop.txt"), content);

	}

	/**
	split[0] 10.201.1.1
	split[1] PRODUCT
	split[2] productdg
	split[3] S06
	split[4] PCM_CATEGORY_DISPLAY
	split[5] SID
	split[6] querydata
	split[7] bigdata
	
	 --connect jdbc:oracle:thin:@10.201.0.102:1521:product2 
	 --username querydata 
	 --password bigdata 
	 --verbose 
	 --table PRODUCT.PCM_CATEGORY_DISPLAY  
	 --split-by SID  
	 --target-dir /user/hdfs/output/s06_pcm_category_display 
	 --hive-import --hive-overwrite 
	 --hive-database sourcedata 
	 --hive-table s06_pcm_category_display   
	 --hive-partition-value ${task_date}  
	 --hive-partition-key dt
 */
	
	public static String getDependency() throws Exception{
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/gp/workspace/z_bigdata/src/test/resouces/dependence2"))));
		String line=null;
		StringBuffer content =new StringBuffer();
		Map <String,String>map = new HashMap<String,String>();
		int count=0;
		while((line=bufferedReader.readLine()) != null){
			count++;
			//System.out.println(line);
			line=line.replace("\"", "");
			String[] split = line.split("	");
			if(count==1){
				for(int i=0;i<split.length;i++){
					System.out.println("split["+i+"] "+ split[i]);
				}
				
			}
			
			System.out.println(count);
			StringBuffer sb =new StringBuffer();
			//sb.append("sqoop import ");
			sb.append(" --connect jdbc:oracle:thin:@"+split[0]+":1521:"+split[2]);
			sb.append(" --username "+split[6]);
			sb.append(" --password "+split[7]);
			sb.append(" --verbose");
			sb.append(" --table ").append(split[1].toUpperCase()+"."+split[4].toUpperCase());
			sb.append(" --split-by "+split[5].toUpperCase());
			sb.append(" --target-dir /user/hdfs/output/").append(split[3].toLowerCase()).append("_").append(split[4].toLowerCase());
			sb.append(" --hive-import");
			sb.append(" --hive-overwrite");
			sb.append(" --hive-database sourcedata");
			sb.append(" --hive-table ").append(split[3].toLowerCase()).append("_").append(split[4].toLowerCase());
			sb.append(" --hive-partition-value ${task_date}");
			sb.append(" --hive-partition-key dt");
			//sb.append(" --fields-terminated-by \\t");
			//sb.append(" --m 1");
			//sb.append(" --hive-drop-import-delims");
			sb.append("\r\n");
			
			content.append(sb.toString());
			System.out.println("---------------------");
			System.out.println(sb.toString());
		}
		
		return content.toString();
	}

}
