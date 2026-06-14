package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class BadSleeplessSessions implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        Long count = sessions.stream()
                .filter(session -> session.getQuality() == Quality.BAD)
                .count();
        return new SleepAnalysisResult(count, "Количество ночей," +
                " где у пользователя наблюдались проблемы с качеством сна: ");
    }
}