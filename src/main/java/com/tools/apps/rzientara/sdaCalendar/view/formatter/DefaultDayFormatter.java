package com.tools.apps.rzientara.sdaCalendar.view.formatter;

public class DefaultDayFormatter implements DayFormatter {

    @Override
    public String formatDay(int day, int month, int year) {
        return String.format("%2d\t", day);
    }

    @Override
    public String formatEmptyDay() {
        return "  \t";
    }
}
