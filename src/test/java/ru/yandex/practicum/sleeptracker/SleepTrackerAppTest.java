package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class SleepTrackerAppTest {

    static List<SleepingSession> sessionsList;
    static List<SleepingSession> emptyList;


    @BeforeAll
    static void create() {
        LocalDateTime timeAsleep1 = LocalDateTime.of(2026, 6, 13, 23, 30);
        LocalDateTime timeWakeUp1 = LocalDateTime.of(2026, 6, 14, 7, 40);
        Quality quality1 = Quality.GOOD;
        SleepingSession session1 = new SleepingSession(timeAsleep1, timeWakeUp1, quality1);

        LocalDateTime timeAsleep2 = LocalDateTime.of(2026, 6, 13, 23, 55);
        LocalDateTime timeWakeUp2 = LocalDateTime.of(2026, 6, 14, 5, 0);
        Quality quality2 = Quality.NORMAL;
        SleepingSession session2 = new SleepingSession(timeAsleep2, timeWakeUp2, quality2);

        LocalDateTime timeAsleep3 = LocalDateTime.of(2026, 6, 13, 14, 50);
        LocalDateTime timeWakeUp3 = LocalDateTime.of(2026, 6, 13, 18, 20);
        Quality quality3 = Quality.BAD;
        SleepingSession session3 = new SleepingSession(timeAsleep3, timeWakeUp3, quality3);


        sessionsList = List.of(session1, session2, session3);
        emptyList = new ArrayList<>();
    }

    @Test
    void testMinSleepDuration() {
        MinSleepDuration duration = new MinSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(sessionsList);
        assertEquals(210L, result.getValue());
        assertEquals("Минимальная продолжительность сна составляет: ", result.getDescription());
    }

    @Test
    void testMaxSleepDuration() {
        MaxSleepDuration duration = new MaxSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(sessionsList);
        assertEquals(490L, result.getValue());
        assertEquals("Максимальная продолжительность сна составляет: ", result.getDescription());
    }

    @Test
    void testAverageDurationWithCorrectList() {
        AverageSleepDuration duration = new AverageSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(sessionsList);
        assertEquals(335.0, result.getValue());
        assertEquals("Средняя продолжительность сна составляет: ", result.getDescription());
    }

    @Test
    void testMinDurationWithEmptyList() {
        MinSleepDuration duration = new MinSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(emptyList);
        assertEquals(0L, result.getValue());
    }

    @Test
    void testMaxDurationWithEmptyList() {
        MaxSleepDuration duration = new MaxSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(emptyList);
        assertEquals(0L, result.getValue());
    }

    @Test
    void testAverageDurationWithEmptyList() {
        AverageSleepDuration duration = new AverageSleepDuration();
        SleepAnalysisResult result = duration.sleepAnalysis(emptyList);
        assertEquals(0.0, result.getValue());
    }

    @Test
    void testClassificationOfUsersWithCorrectList() {
        ClassificationOfUsers classification = new ClassificationOfUsers();
        SleepAnalysisResult result = classification.sleepAnalysis(sessionsList);
        assertEquals("Голубь", result.getValue());
        assertEquals("Пользователь относится к хронотипу: ", result.getDescription());

    }

    @Test
    void testClassificationOfUsersWithEmptyList() {
        ClassificationOfUsers classification = new ClassificationOfUsers();
        SleepAnalysisResult result = classification.sleepAnalysis(emptyList);
        assertEquals("Голубь", result.getValue());
    }

    @Test
    void testBadSleeplessSessions() {
        BadSleeplessSessions bad = new BadSleeplessSessions();
        SleepAnalysisResult result = bad.sleepAnalysis(sessionsList);
        assertEquals("Количество ночей, где у пользователя наблюдались проблемы с качеством сна: ",
                result.getDescription());
        assertEquals(1L, result.getValue());
    }

    @Test
    void testBadSleeplessSessionsWithEmptyList() {
        BadSleeplessSessions quality = new BadSleeplessSessions();
        SleepAnalysisResult result = quality.sleepAnalysis(emptyList);
        assertEquals(0L, result.getValue());
    }

    @Test
    void testTotalSleepSessions() {
        TotalSleepSessions totalSleepSessions = new TotalSleepSessions();
        SleepAnalysisResult result = totalSleepSessions.sleepAnalysis(sessionsList);
        assertEquals("Общее количество сессий сна за отслеживаемый период: ", result.getDescription());
        assertEquals(3, result.getValue());
    }

    @Test
    void testTotalSleepSessionsWithEmptyList() {
        TotalSleepSessions totalSleepSessions = new TotalSleepSessions();
        SleepAnalysisResult result = totalSleepSessions.sleepAnalysis(emptyList);
        assertEquals(0, result.getValue());
    }

}