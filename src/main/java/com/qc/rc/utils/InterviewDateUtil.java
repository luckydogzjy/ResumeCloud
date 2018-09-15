package com.qc.rc.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterviewDateUtil {

	//interview用
		
//		将字符串转换为日期
		public static Date strToDate(String strDate) {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date date = null;
			try {
				date = sdf.parse(strDate);
			} catch (ParseException e) {

				e.printStackTrace();
			}
			return date;
			
		}
//		日期转字符串
		public static String dateToStr(Date date) {
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String dateStr = sdf.format(date);
			return dateStr;
		}
//		日期格式化
		public static Date dateFormat(Date date) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		    String dateString = formatter.format(date);  
		    ParsePosition pos = new ParsePosition(8);  
		    Date time_2 = formatter.parse(dateString, pos);  
		    return time_2; 
		}
		
		
		
//		判断是否是时分	"2018.2.27 09:20:30"
		public static boolean isValidTime(String str) {
			String regExp="^((\\d{2}(([02468][048])|([13579][26]))[\\.]?((((0?[13578])|(1[02]))[\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\.]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\.]?((((0?[13578])|(1[02]))[\\.]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\.]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\.]?((0?[1-9])|(1[0-9])|(2[0-8]))))))"
					+ "([ \t\n\\x0B\f\r])"
					+ "(([0-1]{1}[0-9]{1})|([2]{1}[0-4]{1}))([:])(([0-5]{1}[0-9]{1}|[6]{1}[0]{1}))([:])((([0-5]{1}[0-9]{1}|[6]{1}[0]{1})))$";
			Pattern p = Pattern.compile(regExp);
			Matcher m = p.matcher(str);	
			if(m.matches()){
				return true;
			}
			return false;
			
		}
}
