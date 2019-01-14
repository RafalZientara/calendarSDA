package com.tools.apps.rzientara.sdaCalendar.reader;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;

public abstract class BaseLessonLoader implements LessonLoader {

    void applyColumn(LessonEvent.Builder builder, String data, String column) {
        switch (column) {
            case "data":
                applyData(builder, data);
                break;
            case "czas":
                applyTimeRange(builder, data);
                break;
            case "temat":
                applyTopic(builder, data);
                break;
        }
    }

    private void applyData(LessonEvent.Builder builder, String data) {
        DateInterpreter interpreter = new DateInterpreter(data);
        builder.setYear(interpreter.getYear());
        builder.setMonth(interpreter.getMonth());
        builder.setDay(interpreter.getDay());
    }

    private void applyTimeRange(LessonEvent.Builder builder, String data) {
        String[] timeRange = data.split("-");
        if (timeRange.length == 2) {
            applyTimeStart(builder, timeRange[0]);
            applyTimeEnd(builder, timeRange[1]);
        } else {
            System.out.println("Wrong time format");
        }

    }

    private void applyTimeStart(LessonEvent.Builder builder, String s) {
        String[] start = splitTime(s);
        if (start.length == 2) {
            Time time = new Time(start);
            builder.setHourStart(time.getHour());
            builder.setMinuteStart(time.getMinute());
        } else {
            System.out.println("Wrong start time format");
        }
    }

    private void applyTimeEnd(LessonEvent.Builder builder, String s) {
        String[] end = splitTime(s);
        if (end.length == 2) {
            Time time = new Time(end);
            builder.setHourEnd(time.getHour());
            builder.setMinuteEnd(time.getMinute());
        } else {
            System.out.println("Wrong end time format");
        }
    }

    private String[] splitTime(String s) {
        return s.split(":");
    }

    private void applyTopic(LessonEvent.Builder builder, String data) {
        builder.setTopic(data);
    }

}
