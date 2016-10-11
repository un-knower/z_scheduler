package z_bigdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CreateSqoopCommand {

	public static void main(String[] args) throws Exception {
		Map<String ,String> dependencyMap = getDependency();

	}

	
	
	private static void createFile(Map<String, List<String>> dependencyMap) {
		Iterator<String> iterator = dependencyMap.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			List<String> list = dependencyMap.get(key);
			for (String table : list) {
				
			}
		}
		
		//create str
		StringBuffer sb = new StringBuffer();
		//hdfs check
		
		//clean db
		
		//insert db
		
		//write file
		
	}

	
	
	public static Map <String, String> getDependency() throws Exception{
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/gp/workspace/z_bigdata/src/test/resouces/dependence"))));
		String line=null;
		Map <String,String>map = new HashMap<String,String>();
		int count=0;
		while((line=bufferedReader.readLine()) != null){
			count++;
			//System.out.println(line);
			line=line.replace("\"", "");
			String[] split = line.split(",");
			if(count==1){
				for(int i=0;i<split.length;i++){
					System.out.println("split["+i+"] "+ split[i]);
				}
				
			}
			System.out.println(count);
			String str= "sqoop import --connect jdbc:oracle:thin:@"+split[2]+":"+split[3]+":"+split[4]+" --username "+split[5]+" --password "+split[6]+" --verbose --table "+split[7]+"."+split[9]+"  --split-by "+split[11]+"  --target-dir /user/hdfs/output/"+split[8].toLowerCase()+" --hive-import --hive-overwrite --hive-database sourcedata --hive-table "+split[8].toLowerCase()+"   --hive-partition-key dt --hive-partition-value ${sdate} --fields-terminated-by \\t -m 1 --hive-drop-import-delims";
			System.out.println("\r\n---------------------\r\n");
			System.out.println(str);
		}
		
		
		
		return map;
	}
	
	public static String changeChars(String s){
		String ss = s.replace("coord_", "");
		//System.out.println(ss);
		char[] chars = ss.toCharArray();
		StringBuffer sb =new StringBuffer();
		int count=0;
		for (char c : chars) {
			count++;
			if(c>='A' && c <='Z'){
				c=Character.toLowerCase(c);
				if(count!=1){
					sb.append("_");
				}
			}
			sb.append(c);
		}
		String sss = sb.toString();
		return sss;
	}
}
