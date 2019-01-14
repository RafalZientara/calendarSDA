package com.tools.apps.rzientara.sdaCalendar.reader;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;

import java.util.List;

public interface LessonLoader {
    List<LessonEvent> loadLessons();
}
