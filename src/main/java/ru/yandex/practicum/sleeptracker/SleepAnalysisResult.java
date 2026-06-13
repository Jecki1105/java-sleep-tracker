package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {

    Object value;
    String description;

    public SleepAnalysisResult(Object value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
