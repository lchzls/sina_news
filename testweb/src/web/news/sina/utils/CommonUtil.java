package web.news.sina.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static String getColumn(String type){
		
		String res = null;
		
		switch(type.trim()){
			case "90":res = "国内";
				break;
			case "91":res = "国际";
		    	break;
			case "92":res = "社会";
				break;
		}
		
		return res;
	}
	
	public static String getEncoding(String str) {   
        String encode = "GB2312";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                String s = encode;   
                return s;   
            }   
        } catch (Exception exception) {   
        }   
        encode = "iso-8859-1";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                String s1 = encode;   
                return s1;   
            }   
        } catch (Exception exception1) {   
        }   
        encode = "UTF-8";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                String s2 = encode;   
                return s2;   
            }   
        } catch (Exception exception2) {   
        }   
        encode = "GBK";   
        try {   
            if (str.equals(new String(str.getBytes(encode), encode))) {   
                String s3 = encode;   
                return s3;   
            }   
        } catch (Exception exception3) {   
        }   
        return "";   
    } 
	
	public static String getDate(String time){
		long tie = Long.parseLong(time);
	    Date date= new Date(tie*1000);
	    return format.format(date).toString().substring(5);
	}
}
