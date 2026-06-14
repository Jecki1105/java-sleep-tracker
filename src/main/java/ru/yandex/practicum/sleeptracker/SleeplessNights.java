package ru.yandex.practicum.sleeptracker;

import java.time.Period;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class SleeplessNights implements SleepMonitoring {

    @Override
    public SleepAnalysisResult sleepAnalysis(List<SleepingSession> sessions) {

        if (sessions.isEmpty()) {
            return new SleepAnalysisResult(0, "Количество бессонных ночей:");
        }

        LocalDate dateStart = findFirstNight(sessions);
        LocalDate dateFinish = sessions.getLast().getTimeWakeUp().toLocalDate();
        Period period = Period.between(dateStart, dateFinish);
        int totalNightSessions = period.getDays() + 1;

        Set<LocalDate> datesOfNightSessions;
        datesOfNightSessions = sessions.stream()
                .filter(this::isSleepWithinSameDay)
                .map(session -> session.getTimeAsleep().toLocalDate())
                .collect(Collectors.toSet());

        sessions.stream()
                .filter(session -> session.getTimeWakeUp().getHour() < 6)
                .map(session -> session.getTimeWakeUp().toLocalDate())
                .forEach(datesOfNightSessions::add);

        int amountOfNightsWhenUsersSleep = totalNightSessions - datesOfNightSessions.size();
        return new SleepAnalysisResult(amountOfNightsWhenUsersSleep, "Количество бессонных ночей: ");
    }

    public LocalDate findFirstNight(List<SleepingSession> sessions) {
        LocalDateTime firstSession = sessions.getFirst().getTimeAsleep();
        int timeFirstSession = firstSession.toLocalTime().getHour();
        if (timeFirstSession < SleepConstants.MIDDAY) {
            return firstSession.toLocalDate().minusDays(1);
        } else {
            return firstSession.toLocalDate();
        }
    }

    public boolean isSleepWithinSameDay(SleepingSession session) {
        LocalDate date = session.getTimeWakeUp().toLocalDate();
        LocalDateTime startNight = date.atStartOfDay();
        LocalDateTime finishNight = startNight.plusHours(SleepConstants.MAX_HOURS_FOR_SLEEP);
        return session.getTimeAsleep().isBefore(finishNight) && session.getTimeWakeUp().isAfter(startNight);
    }
}
