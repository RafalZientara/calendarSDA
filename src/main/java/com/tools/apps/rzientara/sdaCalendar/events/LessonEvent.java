package com.tools.apps.rzientara.sdaCalendar.events;

public class LessonEvent {
    public static final String DATE_FORMAT = "%04d-%02d-%02dT%02d:00:00";//-00:00
    private final String groupName;
    private final String topic;
    private final int day;
    private final int month;
    private final int year;
    private final int hourStart;
    private final int hourEnd;
    private final int minuteStart;
    private final int minuteEnd;

    public LessonEvent(Builder builder) {
        groupName = builder.groupName;
        topic = builder.topic;
        day = builder.day;
        month = builder.month;
        year = builder.year;
        hourStart = builder.hourStart;
        hourEnd = builder.hourEnd;
        minuteStart = builder.minuteStart;
        minuteEnd = builder.minuteEnd;
    }

    public String getGroupName() {
        return groupName;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHourStart() {
        return hourStart;
    }

    public int getHourEnd() {
        return hourEnd;
    }

    public String getStartDate() {
        return String.format(DATE_FORMAT, year, month, day, hourStart);
    }

    public String getEndDate() {
        return String.format(DATE_FORMAT, year, month, day, hourEnd);
    }

    public static class Builder {
        private String groupName;
        private String topic;
        private int day;
        private int month;
        private int year;
        private int hourStart;
        private int hourEnd;
        private int minuteStart;
        private int minuteEnd;


        public Builder setGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder setTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public Builder setDay(int day) {
            this.day = day;
            return this;
        }

        public Builder setMonth(int month) {
            this.month = month;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setHourStart(int hourStart) {
            this.hourStart = hourStart;
            return this;
        }

        public Builder setHourEnd(int hourEnd) {
            this.hourEnd = hourEnd;
            return this;
        }

        public Builder setMinuteStart(int minuteStart) {
            this.minuteStart = minuteStart;
            return this;
        }

        public Builder setMinuteEnd(int minuteEnd) {
            this.minuteEnd = minuteEnd;
            return this;
        }

        public LessonEvent build() {
            return new LessonEvent(this);
        }
    }

    @Override
    public String toString() {
        return "LessonEvent{" +
                "groupName='" + groupName + '\'' +
                ", topic='" + topic + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", year=" + year +
                ", hourStart=" + hourStart +
                ", hourEnd=" + hourEnd +
                ", minuteStart=" + minuteStart +
                ", minuteEnd=" + minuteEnd +
                '}';
    }
}
