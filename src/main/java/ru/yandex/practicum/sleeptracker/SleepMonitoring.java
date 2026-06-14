package ru.yandex.practicum.sleeptracker;

import java.util.List;

@FunctionalInterface
public interface SleepMonitoring {

    SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions);
}