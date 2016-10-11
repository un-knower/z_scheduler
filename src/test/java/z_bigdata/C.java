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

public class C {

	public static void main(String[] args) throws Exception {
		Map<String, List<String>> dependencyMap = getDependency();
		createFile(dependencyMap);

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

	
	
	public static Map <String,List<String>> getDependency() throws Exception{
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File("D:/gp/workspace/z_bigdata/src/test/resouces/dependence"))));
		String line=null;
		Map <String,List<String>>map = new HashMap<String,List<String>>();
		
		while((line=bufferedReader.readLine()) != null){
			System.out.println(line);
			String[] split = line.split(" +");
			System.out.println("0:"+split[0]+"         1:"+split[1]);
			if(map.containsKey(split[0])){
				List<String> list = map.get(split[0]);
				list.add(split[1]);
			}else{
				List<String> list = new ArrayList<String>();
				list.add(split[1]);
				map.put(split[0], list);
			}
		}
		
		Iterator<String> iterator = map.keySet().iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			List<String> list = map.get(key);
			System.out.println("key: "+ key);
			for (String s : list) {
			System.out.print(changeChars(s)+"   ");
			}
			System.out.println();
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
