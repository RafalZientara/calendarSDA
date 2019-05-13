package com.tools.apps.rzientara.sdaCalendar.view;

import com.tools.apps.rzientara.sdaCalendar.view.formatter.DayFormatter;
import com.tools.apps.rzientara.sdaCalendar.view.formatter.DefaultDayFormatter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

public class CleanCalendar {
    private LocalDate date;
    private DayFormatter dayFormatter = new DefaultDayFormatter();

    /**
     * @param year  exact value
     * @param month values 1-12
     */
    public CleanCalendar(int year, int month) {
        this.date = LocalDate.of(year, month, 1);
    }

    public void printCalendar() {
        printHeader();
        printDays();
        System.out.println();
        System.out.println();
    }

    private void printHeader() {
        Month month = date.getMonth();
        System.out.printf("%s %2d.%4d\n", month, month.getValue(), date.getYear());
    }

    private void printDays() {
        int currentDay = 1;
        int month = date.getMonth().getValue();
        int year = date.getYear();
        DayOfWeek dayOfWeek = date.withDayOfMonth(1).getDayOfWeek();
        printMarginInFirstWeek(dayOfWeek);
        while (currentDay <= date.lengthOfMonth()) {
            System.out.print(dayFormatter.formatDay(currentDay, month, year));
            if (dayOfWeek == DayOfWeek.SUNDAY) {
                System.out.println();
            }
            currentDay++;
            dayOfWeek = dayOfWeek.plus(1);
        }
    }

    private void printMarginInFirstWeek(DayOfWeek dayOfWeek) {
        int value = dayOfWeek.getValue();
        for (int i = 0; i < value - 1; i++) {
            System.out.print(dayFormatter.formatEmptyDay());
        }
    }

    public void setDayFormatter(DayFormatter dayFormatter) {
        this.dayFormatter = dayFormatter;
    }
}
