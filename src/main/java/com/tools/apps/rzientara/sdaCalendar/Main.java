package com.tools.apps.rzientara.sdaCalendar;
// Copyright 2018 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

import com.tools.apps.rzientara.sdaCalendar.calendar.GoogleCalendarApi;
import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;
import com.tools.apps.rzientara.sdaCalendar.interactors.ConsoleDecision;
import com.tools.apps.rzientara.sdaCalendar.reader.CsvLessonLoader;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        //TODO before use remember to add credentials.json from Google Console to resources
        System.out.println("Welcome in Google API tool");
        GoogleCalendarApi calendarApi = new GoogleCalendarApi();
        if (calendarApi.init()) {
            List<LessonEvent> lessonEvents = loadLessons();
            if (lessonEvents.isEmpty()) {
                System.out.println("No lessons to import!");
            } else {
                printLessons(lessonEvents);

                System.out.println("Add to google calendar? y/n");
                ConsoleDecision decision = new ConsoleDecision();

                if (decision.userWantToContinue()) {
                    createLessons(calendarApi, lessonEvents);
                }
            }
        }
    }

    private static List<LessonEvent> loadLessons() {
        File rootFolder = new File("input/");
        System.out.println("HOME:" + rootFolder.getAbsolutePath());
        if (!rootFolder.exists())
            rootFolder.mkdir();
        File[] files = rootFolder.listFiles();
        List<LessonEvent> allLessons = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                System.out.println("Found file: " + file.getName());
                if (file.isFile()) {
                    CsvLessonLoader loader = new CsvLessonLoader(file.getAbsolutePath());
                    allLessons.addAll(loader.loadLessons());
                } else {
                    System.out.println("Skip directory: " + file.getName());
                }
            }
        }
        return allLessons;
    }

    private static void printLessons(List<LessonEvent> lessonEvents) {
        System.out.println("Prepared list of lessons");
        for (LessonEvent lessonEvent : lessonEvents) {
            System.out.println(lessonEvent + isConflict(lessonEvent, lessonEvents));
        }
    }

    private static String isConflict(LessonEvent currentLesson, List<LessonEvent> lessonEvents) {
        for (LessonEvent lessonEvent : lessonEvents) {
            if (lessonEvent != currentLesson && lessonEvent.isTheSameDay(currentLesson)) {
                return " CONFLICT WITH " + lessonEvent;
            }
        }
        return "";
    }

    private static void createLessons(GoogleCalendarApi calendarApi, List<LessonEvent> lessonEvents) {
        for (LessonEvent lessonEvent : lessonEvents) {
            calendarApi.createLesson(lessonEvent);
        }
    }

    private static LessonEvent createSampleLessonEvent() {
        return new LessonEvent.Builder()
                .setDay(14)
                .setMonth(1)
                .setYear(2019)
                .setGroupName("Lesson")
                .setHourStart(9)
                .setHourEnd(16)
                .build();
    }

}