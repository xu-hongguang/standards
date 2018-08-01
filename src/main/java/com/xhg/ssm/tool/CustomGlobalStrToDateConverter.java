package com.xhg.ssm.tool;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 16033
 */
public class CustomGlobalStrToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

class DateToStrConverter implements Converter<Date,String>{

    @Override
    public String convert(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }
}
