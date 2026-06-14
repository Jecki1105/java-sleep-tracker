package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.time.Duration;

public class MaxSleepDuration implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        Long duration = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeAsleep(),
                        session.getTimeWakeUp()).toMinutes())
                .max()
                .orElse(0L);
        return new SleepAnalysisResult(duration, "Максимальная продолжительность сна составляет: ");
    }
}
