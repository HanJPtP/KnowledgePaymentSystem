package com.woniu.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {

    public static String DateToString(Date date,String pattern){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String format = simpleDateFormat.format(date);
        return format;
    }

    public static Date StringToDate(String pattern,String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date parse = simpleDateFormat.parse(date);
        return parse;
    }

}
