package com.tools.apps.rzientara.sdaCalendar.view;

public class Date implements Comparable<Date> {
    final int year;
    final int month;

    Date(int year, int month) {
        this.year = year;
        this.month = month;
    }

    @Override
    public int hashCode() {
        return year * 1000 + month;
    }

    @Override
    public String toString() {
        return "Date{" +
                "year=" + year +
                ", month=" + month +
                '}';
    }

    @Override
    public int compareTo(Date o) {
        if (o == null) return 1;
        int compareYear = Integer.compare(year, o.year);
        if (compareYear == 0) {
            return compareMonths(this, o);
        } else {
            return compareYear;
        }
    }

    private int compareMonths(Date o1, Date o2) {
        return Integer.compare(o1.month, o2.month);
    }
}