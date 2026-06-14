package ru.yandex.practicum.sleeptracker;

import java.util.List;

public class TotalSleepSessions implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        int numberOfSessions = sessions.size();
        return new SleepAnalysisResult(numberOfSessions, "Общее количество сессий " +
                "сна за отслеживаемый период: ");
    }
}
