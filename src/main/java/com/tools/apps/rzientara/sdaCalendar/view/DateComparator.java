package com.tools.apps.rzientara.sdaCalendar.view;

import java.util.Comparator;

public class DateComparator implements Comparator<Date> {

    @Override
    public int compare(Date o1, Date o2) {
        int compareYear = Integer.compare(o1.year, o2.year);
        if (compareYear == 0) {
            return compareMonths(o1, o2);
        } else {
            return compareYear;
        }
    }

    private int compareMonths(Date o1, Date o2) {
        return Integer.compare(o1.month, o2.month);
    }
}