package com.tools.apps.rzientara.sdaCalendar.reader;

public class Time {
    private final int hour;
    private final int minute;

    public Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public Time(String[] time) {
        hour = Integer.parseInt(time[0]);
        minute = Integer.parseInt(time[1]);
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}
