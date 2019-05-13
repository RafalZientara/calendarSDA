package com.tools.apps.rzientara.sdaCalendar.reader;

import com.tools.apps.rzientara.sdaCalendar.events.LessonEvent;
import com.tools.apps.rzientara.sdaCalendar.view.SchedulePrinter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLessonLoader extends BaseLessonLoader {

    private static final String COMMA_SEPARATOR = ",";
    private static final String CSV = ".csv";
    private String filePath;

    public CsvLessonLoader(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public List<LessonEvent> loadLessons() {
        List<LessonEvent> data = new ArrayList<>();
        String line;
        String[] columnsMap = null;
        boolean isFirstLine = true;
        String groupName = new File(filePath).getName();
        if (groupName.endsWith(CSV)) {
            groupName = groupName.replaceAll(CSV, "");
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((line = br.readLine()) != null) {
                String[] splitedData = line.split(COMMA_SEPARATOR);
                if (isFirstLine) {
                    for (int i = 0; i < splitedData.length; i++) {
                        splitedData[i] = splitedData[i].replaceAll("\uFEFF", "").trim().toLowerCase();
                    }
                    columnsMap = splitedData;
                    isFirstLine = false;
                } else {
                    LessonEvent.Builder builder = new LessonEvent.Builder();
                    matchColumns(builder, splitedData, columnsMap);
                    builder.setGroupName(groupName);
                    data.add(builder.build());
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        SchedulePrinter schedulePrinter = new SchedulePrinter(data);
        schedulePrinter.print();

        return data;
    }

    private void matchColumns(LessonEvent.Builder builder, String[] splitedData, String[] columnsMap) {
        for (int i = 0; i < splitedData.length; i++) {
            String data = splitedData[i];
            String column = columnsMap[i];
            if (data != null && column != null) {
                applyColumn(builder, data, column);
            }
        }
    }
}
