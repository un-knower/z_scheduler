package z_bigdata;

public class B {
	public static void main(String[] args){
		String s="coord_mDaKdjLogisticsKpi";
		String ss = s.replace("coord_", "");
		System.out.println(ss);
		char[] chars = ss.toCharArray();
		StringBuffer sb =new StringBuffer();
		for (char c : chars) {
			if(c>='A' && c <='Z'){
				c=Character.toLowerCase(c);
				sb.append("_");
			}
			sb.append(c);
		}
		String sss = sb.toString();
		System.out.println(sss);
	}
}
