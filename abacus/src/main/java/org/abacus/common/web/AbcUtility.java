package org.abacus.common.web;

public class AbcUtility {

	public static String LPad(String strng, Integer length, char c) {
		return String.format("%" + (length - strng.length()) + "s", "").replace(" ", String.valueOf(c)) + strng;
	}
	
}
