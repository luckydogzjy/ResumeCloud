package com.qc.rc.common;

import java.io.UnsupportedEncodingException;

public class FormParameterUtil {
	
	public static String changeCode(String str) throws UnsupportedEncodingException {
		
		return new String(str.getBytes("ISO-8859-1"), "utf-8");
	}
}
