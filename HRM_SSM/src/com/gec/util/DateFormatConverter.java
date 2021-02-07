package com.gec.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class DateFormatConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		SimpleDateFormat[] sdfs = {new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
								   new SimpleDateFormat("yyyy-MM-dd"),
								   new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"),
								   new SimpleDateFormat("yyyy/MM/dd")};
		for (SimpleDateFormat sdf : sdfs) {
			try {
				if(source!=null){
					return sdf.parse(source);
				}
			} catch (Exception e) {}
		}
		return null;
	}

}
