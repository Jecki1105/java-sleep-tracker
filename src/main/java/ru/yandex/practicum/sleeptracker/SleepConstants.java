package ru.yandex.practicum.sleeptracker;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SleepConstants {
    public static final LocalTime OWL_ASLEEP_THRESHOLD = LocalTime.of(23, 0);
    public static final LocalTime OWL_WAKEUP_THRESHOLD = LocalTime.of(9, 0);
    public static final LocalTime LARK_ASLEEP_THRESHOLD = LocalTime.of(22, 0);
    public static final LocalTime LARK_WAKEUP_THRESHOLD = LocalTime.of(7, 0);

    public static final int MAX_HOURS_FOR_SLEEP = 6;
    public static final int MIDDAY = 12;

    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
}
