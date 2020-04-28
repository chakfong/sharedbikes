package com.fly.common.utils;

import java.util.Date;

public class DateUtil {
    public static Integer getDurationIn30Minute(Date beginDate, Date endDate) {
        Double minutes = Math.ceil((endDate.getTime() - beginDate.getTime()) / 1000.0 / 60.0 / 30.0);
        return minutes.intValue();
    }
}
