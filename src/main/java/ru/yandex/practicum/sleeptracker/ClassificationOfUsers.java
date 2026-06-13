package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ClassificationOfUsers implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(SleepConstants.PIGEON, "Пользователь относится к хронотипу: ");
        }

        List<String> chronoList = sessions.stream()
                .filter(this::isSleepWithinSameDay)
                .map(this::chronoUserOfNight)
                .toList();

        long countOwl = chronoList.stream().filter(SleepConstants.OWL::equals).count();
        long countLark = chronoList.stream().filter(SleepConstants.LARK::equals).count();
        long countPigeon = chronoList.stream().filter(SleepConstants.PIGEON::equals).count();

        String result;
        if (countOwl > countLark && countOwl > countPigeon) {
            result = SleepConstants.OWL;
        } else if (countLark > countOwl && countLark > countPigeon) {
            result = SleepConstants.LARK;
        } else {
            result = SleepConstants.PIGEON;
        }
        return new SleepAnalysisResult(result, "Пользователь относится к хронотипу: ");
    }

    public String chronoUserOfNight(SleepingSession session) {
        LocalTime timeAsleep = session.getTimeAsleep().toLocalTime();
        LocalTime timeWakeUp = session.getTimeWakeUp().toLocalTime();

        if (timeAsleep.isAfter(SleepConstants.OWL_ASLEEP_THRESHOLD)
                && timeWakeUp.isAfter(SleepConstants.OWL_WAKEUP_THRESHOLD)) {
            return SleepConstants.OWL;
        } else if (timeAsleep.isBefore(SleepConstants.LARK_ASLEEP_THRESHOLD)
                && timeWakeUp.isBefore(SleepConstants.LARK_WAKEUP_THRESHOLD)) {
            return SleepConstants.LARK;
        } else {
            return SleepConstants.PIGEON;
        }
    }

    public boolean isSleepWithinSameDay(SleepingSession session) {
        LocalDate date = session.getTimeWakeUp().toLocalDate();
        LocalDateTime startNight = date.atStartOfDay();
        LocalDateTime finishNight = startNight.plusHours(SleepConstants.MAX_HOURS_FOR_SLEEP);
        return session.getTimeAsleep().isBefore(finishNight) && session.getTimeWakeUp().isAfter(startNight);
    }
}