package z_bigdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

//20100529	星期六	149	20100529 00:00:00	201022	10/05.23-05.29	22	20100529000001	周末	2010	2010	2010053	3	201005T3	201005	201005	5	20102	2	2010Q2	2010	2010	1	11	31	90	365	7	365	20100529 12:00:00	20100531 12:00:00	20100531 12:00:00	20100630 12:00:00	20101231 12:00:00	20100529 12:00:00	20101231 12:00:00	2010	2010	2010040	四月	201004160	十六	其他	其他	其他	2010060300	10/05.28-06.03	20100603 12:00:00	22	20130306 12:00:00	20130306 12:00:00


public class AA {

	public static void main(String[] args) throws Exception {
		
		File old = new File("d://a.txt");
		File new_file = new File("d://d.txt");
		if(new_file.exists()){
			new_file.delete();
		}
		new_file.createNewFile();
//		String s="20100529	星期六	149	20100529 00:00:00	201022	10/05.23-05.29	22	20100529000001	周末	2010	2010	2010053	3	201005T3	201005	201005	5	20102	2	2010Q2	2010	2010	1	11	31	90	365	7	365	20100529 12:00:00	20100531 12:00:00	20100531 12:00:00	20100630 12:00:00	20101231 12:00:00	20100529 12:00:00	20101231 12:00:00	2010	2010	2010040	四月	201004160	十六	其他	其他	其他	2010060300	10/05.28-06.03	20100603 12:00:00	22	20130306 12:00:00	20130306 12:00:00";
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(old)));
		OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(new_file));
		String s =null;
		while((s=bufferedReader.readLine())!=null){
			String line="";
//			bufferedReader.readLine();
			String[] split = s.split("\t");
			for(int i=0;i<split.length;i++){
				//
				if(split[i].length()==17){
					String format = format(split[i]);
					line+="\t"+format;
				}else{
					if(i==0){
						line+=split[i];
					}else{
						line+="\t"+split[i];
					}
				}
				
			}
			writer.write(line+"\n");
			
		}
		
		bufferedReader.close();
		writer.flush();
		writer.close();
		
	}

	//20100603 12:00:00 
	private static String format(String s) throws ParseException{
		String ss="20100603 12:00:00";
		SimpleDateFormat oldformat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		SimpleDateFormat newformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format = newformat.format(oldformat.parse(ss));
		System.out.println(format);
		return format;
	}
	
}
