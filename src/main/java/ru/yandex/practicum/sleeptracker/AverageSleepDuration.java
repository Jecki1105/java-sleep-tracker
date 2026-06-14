package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.time.Duration;

public class AverageSleepDuration implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        Double duration = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeAsleep(),
                        session.getTimeWakeUp()).toMinutes())
                .average()
                .orElse(0.0);
        return new SleepAnalysisResult(duration, "Средняя продолжительность сна составляет: ");
    }
}