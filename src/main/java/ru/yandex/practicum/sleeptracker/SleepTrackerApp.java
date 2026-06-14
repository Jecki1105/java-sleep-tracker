package ru.yandex.practicum.sleeptracker;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final List<SleepMonitoring> options = new ArrayList<>();

    static {
        options.add(new ClassificationOfUsers());
        options.add(new MinSleepDuration());
        options.add(new MaxSleepDuration());
        options.add(new AverageSleepDuration());
        options.add(new BadSleeplessSessions());
        options.add(new SleeplessNights());
        options.add(new TotalSleepSessions());
    }

    public static void main(String[] args) {

        String filePath;
        if (args.length == 0) {
            filePath = "src/main/resources/sleep_log.txt";
        } else {
            filePath = args[0];
        }

        Path path = Paths.get(filePath);
        List<SleepingSession> sessions;
        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            sessions = br.lines()
                    .map(SleepTrackerApp::createSession)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла '" + filePath + "': " + e.getMessage());
            return;
        }

        options.stream()
                .map(function -> function.sleepAnalysis(sessions))
                .forEach(result -> System.out.println(result.getDescription() + ": "
                        + result.getValue()));
    }

    private static SleepingSession createSession(String line) {
        String[] sessionData = line.split(";");
        LocalDateTime timeAsleep = LocalDateTime.parse(sessionData[0], SleepConstants.DATE_TIME_FORMATTER);
        LocalDateTime timeWakeUp = LocalDateTime.parse((sessionData[1]), SleepConstants.DATE_TIME_FORMATTER);
        Quality quality = Quality.valueOf(sessionData[2]);
        return new SleepingSession(timeAsleep, timeWakeUp, quality);
    }
}