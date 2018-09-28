package com.qc.rc.common;

import java.util.UUID;

public class GetUuid {

	public static String getuuid32(){
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
}
