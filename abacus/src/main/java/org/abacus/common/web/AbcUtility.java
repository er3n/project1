package org.abacus.common.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.internal.TypeLocatorImpl;
import org.hibernate.type.EnumType;
import org.hibernate.type.Type;
import org.hibernate.type.TypeResolver;


public class AbcUtility {

	public static String LPad(String str, int length, char c) {
		String ret = StringUtils.leftPad(str, length, c);
		return ret;
	}

	public static String RPad(String str, int length, char c) {
		String ret = StringUtils.rightPad(str, length, c);
		return ret;
	}
	
	public static Type getEnumHibernateType(Class<?> enumClass){
		String enumString = enumClass.getName();
		Properties params = new Properties();
		params.put("enumClass", enumString);
		params.put("type", "12");/*type 12 instructs to use the String representation of enum value*/
		Type retType = new TypeLocatorImpl(new TypeResolver()).custom(EnumType.class, params);
		return retType;
	}
	
	public static String formatDate(Date date){
		if (date==null)
			return null;
		return new SimpleDateFormat ("dd.MM.yyyy").format(date); 
	}
}
