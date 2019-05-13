package com.tools.apps.rzientara.sdaCalendar.view;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;
import com.tools.apps.rzientara.sdaCalendar.view.formatter.LessonFormatter;

import java.util.*;

public class SchedulePrinter {
    final private List<LessonEvent> lessonEvents;

    public SchedulePrinter(List<LessonEvent> lessonEvents) {
        this.lessonEvents = lessonEvents;
    }

    public void print() {
        ArrayList<Date> dates = new ArrayList<>(getUniqueDates());
        dates.sort(new DateComparator());

        LessonFormatter lessonFormatter = new LessonFormatter(lessonEvents);
        for (Date date: dates) {
            CleanCalendar calendar = new CleanCalendar(date.year, date.month);
            calendar.setDayFormatter(lessonFormatter);
            calendar.printCalendar();
        }
    }

    private Set<Date> getUniqueDates() {
        Set<Date> dateMap = new TreeSet<>();
        for (LessonEvent lesson : lessonEvents) {
            dateMap.add(new Date(lesson.getYear(), lesson.getMonth()));
        }
        return dateMap;
    }
}
