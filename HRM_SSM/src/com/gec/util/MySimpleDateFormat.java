package com.gec.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MySimpleDateFormat {
	
	public Date formart(String str) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	public java.sql.Date dateToFormatDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
        String strDate= sdf.format(date); //格式化成yyyy-MM-dd格式的时间字符串
        Date newDate = null;
		try {
			newDate = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        java.sql.Date resultDate = new java.sql.Date(newDate.getTime());//最后转换成 java.sql.Date类
		return resultDate;
	}
}
