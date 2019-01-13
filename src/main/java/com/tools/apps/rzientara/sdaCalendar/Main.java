package com.tools.apps.rzientara.sdaCalendar;// Copyright 2018 Google LLC
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

import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static void main(String... args) throws IOException, GeneralSecurityException {
        //TODO before use remember to add credentials.json from Google Console to resources
        GoogleCalendarApi calendarApi = new GoogleCalendarApi();
        if (calendarApi.init()) {
            LessonEvent sampleLesson = new LessonEvent.Builder()
                    .setDay(14)
                    .setMonth(1)
                    .setYear(2019)
                    .setGroupName("Javawro16")
                    .setHourStart(9)
                    .setHourEnd(16)
                    .build();
            calendarApi.createLesson(sampleLesson);
        }
    }


}