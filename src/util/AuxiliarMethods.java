package util;

public class AuxiliarMethods {
	
	
	
	
	public static String resetStringType(String str){
		String ret = str.replace("'", "\\'");
		ret = str.replace("'", "\\'");
		ret = str.replace("\"", "\\\"");
		ret = str.replace("`", "\\`");
		ret = str.replace("´", "\\´");
		
		return ret;
	}
	
	
	
	
	
	


}
