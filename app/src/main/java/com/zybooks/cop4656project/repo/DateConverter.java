package com.zybooks.cop4656project.repo;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverter {

    //convert date
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }
    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}
