package com.tools.apps.rzientara.sdaCalendar.reader;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateInterpreterTest {

    @Test
    public void testFirstFormatDate() {
        // given
        String date = "1992-01-16";

        // when
        DateInterpreter interpreter = new DateInterpreter(date);

        // then
        assertEquals(1992, interpreter.getYear());
        assertEquals(1, interpreter.getMonth());
        assertEquals(16, interpreter.getDay());

    }

    @Test
    public void testSecondFormatDate() {
        // given
        String date = "16-01-1992";

        // when
        DateInterpreter interpreter = new DateInterpreter(date);

        // then
        assertEquals(1992, interpreter.getYear());
        assertEquals(1, interpreter.getMonth());
        assertEquals(16, interpreter.getDay());
    }

    @Test
    public void testThirdFormatDate() {
        // given
        String date = "03-02-2019";

        // when
        DateInterpreter interpreter = new DateInterpreter(date);

        // then
        assertEquals(2019, interpreter.getYear());
        assertEquals(2, interpreter.getMonth());
        assertEquals(3, interpreter.getDay());
    }

}