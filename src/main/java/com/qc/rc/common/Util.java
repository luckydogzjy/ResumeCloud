package com.qc.rc.common;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class Util {

//	判断是否是数字
	public static boolean isNumber(String str) {

		String regExp="[\\d]+";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(str);
		
		if(m.matches()){
			return true;
		}else {
			return false;
		}
		
	}
	
//	判断是否在选项集合内
	public static boolean isvalidChoose(ArrayList<Integer> chooses , String jobChoose) {
		chooses.add(0);
		String regExp="[\\d]+";
		Pattern p = Pattern.compile(regExp);
		Matcher m = p.matcher(jobChoose);	
		if(m.matches()){
			if(chooses.contains(Integer.valueOf(jobChoose))){
				return true;
			}
		}
		return false;
	}
	
	
//	将字符串转换为日期
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
//	日期转字符串
	public static String dateToStr(Date date) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dateStr = sdf.format(date);
		return dateStr;
	}
//	日期格式化
	public static Date dateFormat(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    String dateString = formatter.format(date);  
	    ParsePosition pos = new ParsePosition(8);  
	    Date time_2 = formatter.parse(dateString, pos);  
	    return time_2; 
	}
	
	
	
//	判断是否是时分	"2018.2.27 09:20:30"
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
	
//	判断输入日期是否大于当前时间
	public static boolean isGreaterThanNow(String dateGet) {
		Date date = new Date();
		if(strToDate(dateGet).after(date)){
			return true;
		}
		return false;		
	}
	

//	时间比较测试
/*	public static void main(String args[]) {
		String string ="2018.12.29 09:20:30";
		System.out.println(isGreaterThanNow(strToDate(string)));
	}*/
//	打印ArrayList信息
//	public static void print(ArrayList arrayList) {
//		for (Object object : arrayList) {
//			System.out.println(object.toString());
//		}
//	}
//	日期转换测试
//	public static void main(String args[]) {
//		Date date = new Date();
//		System.out.println(dateToStr(date));
//		
//		String datestr = "2018.06.06";
//		System.out.println(strToDate(datestr));
//	}
}
