package com.tools.apps.rzientara.sdaCalendar.reader;

import com.google.api.client.util.DateTime;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateInterpreter {

    //    private final static String DATE_REGEX_PATTERN = "([12]\\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01]))";//1978-12-20
    private final static String DATE_REGEX_PATTERN = "(\\d{4})-(\\d{2})-(\\d{2})";//1978-12-20
    private final static String DATE2_REGEX_PATTERN = "(\\d{2})-(\\d{2})-(\\d{4})";//03-02-2019
    private final static Pattern PATTERN = Pattern.compile(DATE_REGEX_PATTERN);
    private final static Pattern PATTERN_2 = Pattern.compile(DATE2_REGEX_PATTERN);
    private static final int PARAMETER_NOT_FOUND = -1;
    private final int year;
    private final int month;
    private final int day;

    public DateInterpreter(String date) {

//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//        String input = "Thu Jun 18 20:56:02 EDT 2009";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
//        formatter


        Matcher matcher = PATTERN.matcher(date);
        Matcher matcher2 = PATTERN_2.matcher(date);
        if (matcher.find()) {
            year = Integer.parseInt(matcher.group(1));
            month = Integer.parseInt(matcher.group(2));
            day = Integer.parseInt(matcher.group(3));
        } else if (matcher2.find()) {
            day = Integer.parseInt(matcher2.group(1));
            month = Integer.parseInt(matcher2.group(2));
            year = Integer.parseInt(matcher2.group(3));
        } else {
            year = PARAMETER_NOT_FOUND;
            month = PARAMETER_NOT_FOUND;
            day = PARAMETER_NOT_FOUND;
        }
    }

/*
    private void pareWithDefaultDateParser(String date) {
        DateParser dateParser = new DateParser(date);
        if (dateParser.parse()) {
            Integer[] dateFields = dateParser.getDateFields();
            year = dateFields[0];
            month = dateFields[1] + 1;
            day = dateFields[2];
        }
    }*/

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
