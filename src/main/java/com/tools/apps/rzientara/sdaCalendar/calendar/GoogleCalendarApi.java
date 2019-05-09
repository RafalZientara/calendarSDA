package com.tools.apps.rzientara.sdaCalendar.calendar;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;

public class GoogleCalendarApi extends BaseGoogleCalendarApi implements CalendarApi {

    @Override
    public void createLesson(LessonEvent lesson) {
        Event event = createEvent(lesson);
        setStartTime(lesson, event);
        setEndTime(lesson, event);
/*

        DateTime startDateTime = new DateTime("2015-05-28T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setStart(start);
        DateTime endDateTime = new DateTime("2015-05-28T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime)
                .setTimeZone("America/Los_Angeles");
        event.setEnd(end);
*/

        setReminders(event);

        try {
            event = service.events()
                    .insert(PRIMARY_CALENDAR_ID, event)
                    .execute();
            System.out.printf("Event created: %s\n", event.getHtmlLink());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Event createEvent(LessonEvent lesson) {
        return new Event()
                .setSummary(String.format("%s %s", lesson.getGroupName(), lesson.getTopic()))
                .setLocation("Wrocław");
    }

    private static void setReminders(Event event) {
        EventReminder[] reminderOverrides = new EventReminder[]{
                new EventReminder().setMethod("popup").setMinutes(10),
                new EventReminder().setMethod("popup").setMinutes(60),
        };
        Event.Reminders reminders = new Event.Reminders()
                .setUseDefault(false)
                .setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
    }

    private static void setStartTime(LessonEvent lesson, Event event) {
        int hour = lesson.getHourStart();
        int minute = 0;
        Date out = createDate(lesson, hour, minute);
        EventDateTime end = createEventDateTime(out);
        event.setStart(end);
    }

    private static EventDateTime createEventDateTime(Date date) {
        DateTime startDateTime = new DateTime(date, TimeZone.getDefault());
        return new EventDateTime()
                .setDateTime(startDateTime);
    }

    private static void setEndTime(LessonEvent lesson, Event event) {
        int hour = lesson.getHourEnd();
        int minute = 0;
        Date date = createDate(lesson, hour, minute);
        EventDateTime end = createEventDateTime(date);
        end.setTimeZone(ZONE_EUROPE_WARSAW);
        event.setEnd(end);
    }

    private static Date createDate(LessonEvent lesson, int hour, int minute) {
        LocalDateTime date = LocalDateTime.of(lesson.getYear(),
                lesson.getMonth(),
                lesson.getDay(),
                hour, minute, 0);
        return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
    }

}
