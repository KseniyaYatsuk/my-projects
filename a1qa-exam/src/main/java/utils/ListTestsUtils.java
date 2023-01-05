package utils;

import models.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ListTestsUtils {

    public static List<Date> getStartDateListFromTestsList(List<Test> tests) {
        List<Date> startDates = tests.stream().map(s -> {
            try {
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(s.getStartTime());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        return startDates;
    }
}
