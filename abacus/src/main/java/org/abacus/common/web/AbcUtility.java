package org.abacus.common.web;

import org.apache.commons.lang3.StringUtils;


public class AbcUtility {

	public static String LPad(String str, int length, char c) {
		String ret = StringUtils.leftPad(str, length, c);
		return ret;
	}

	public static String RPad(String str, int length, char c) {
		String ret = StringUtils.rightPad(str, length, c);
		return ret;
	}
	
}
