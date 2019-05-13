package com.tools.apps.rzientara.sdaCalendar.view.formatter;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;

import java.util.ArrayList;
import java.util.List;

public class LessonFormatter implements DayFormatter {
    private final List<LessonEvent> lessonEventList;

    public LessonFormatter(List<LessonEvent> lessonEventList) {
        this.lessonEventList = lessonEventList;
    }

    @Override
    public String formatDay(int day, int month, int year) {
        List<LessonEvent> matchingLessons = new ArrayList<>();
        for (LessonEvent lessonEvent : lessonEventList) {
            if (lessonEvent.isTheSameDay(day, month, year)) {
                matchingLessons.add(lessonEvent);
            }
        }

        if (matchingLessons.size() > 1) {
            return String.format("X%2dX ", day);
        } else if (matchingLessons.size() == 1) {
            return String.format("[%2d] ", day);
        } else {
            return String.format(" %2d  ", day);
        }
    }

    @Override
    public String formatEmptyDay() {
        return "     ";
    }
}
