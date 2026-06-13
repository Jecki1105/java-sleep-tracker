package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.time.Duration;

public class MinSleepDuration implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        Long duration = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeAsleep(),
                        session.getTimeWakeUp()).toMinutes())
                .min()
                .orElse(0L);
        return new SleepAnalysisResult(duration, "Минимальная продолжительность сна составляет: ");
    }
}