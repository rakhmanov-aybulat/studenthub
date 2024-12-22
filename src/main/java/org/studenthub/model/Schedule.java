package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Schedule {
    private SimpleIntegerProperty scheduleId;
    private SimpleStringProperty groupName;
    private SimpleStringProperty disciplineName;
    private SimpleObjectProperty<LocalDate> date;

    public Schedule(int scheduleId, String groupName,
                    String disciplineName, LocalDate date) {
        this.scheduleId = new SimpleIntegerProperty(scheduleId);
        this.groupName = new SimpleStringProperty(groupName);
        this.disciplineName = new SimpleStringProperty(disciplineName);
        this.date = new SimpleObjectProperty<>(date);
    }

    public int getScheduleId() {
        return scheduleId.get();
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId.set(scheduleId);
    }

    public String getGroupName() {
        return groupName.get();
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }

    public String getDisciplineName() {
        return disciplineName.get();
    }

    public void setDisciplineName(String disciplineName) {
        this.disciplineName.set(disciplineName);
    }

    public SimpleObjectProperty<LocalDate> dateProperty() {
        return date;
    }

    public LocalDate getDate() {
        return date.get();
    }

    public void setDate(LocalDate date) {
        this.date.set(date);
    }
}
