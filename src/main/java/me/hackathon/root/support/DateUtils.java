package me.hackathon.root.support;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DateUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/hh/mm/ss");

    public static Date longToDate(Long longDate) {
        if (Objects.nonNull(longDate)) {
            return new Date(longDate);
        }
        return new Date();
    }

    public static Long dateToLong(Date date) {
        return date.getTime();
    }

    public static Date stringToDate(String stringDate) {
        try {
            return simpleDateFormat.parse(stringDate);
        } catch (ParseException e) {
            /* 파싱 에러 */
        }
        return new Date();
    }

    public static String dateToString(Date date) {
        return simpleDateFormat.format(date);
    }

    public static String longToString(Long longDate) {
        return dateToString(longToDate(longDate));
    }

    public static Long stringToLong(String stringDate) {
        return stringToDate(stringDate).getTime();
    }
}
