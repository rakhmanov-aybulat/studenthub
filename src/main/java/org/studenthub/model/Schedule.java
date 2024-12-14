package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Schedule {
    private SimpleIntegerProperty scheduleId;
    private SimpleIntegerProperty groupId;
    private SimpleIntegerProperty disciplineId;
    private SimpleStringProperty date;

    public Schedule(int scheduleId, int groupId,
                    int disciplineId, String date) {
        this.scheduleId = new SimpleIntegerProperty(scheduleId);
        this.groupId = new SimpleIntegerProperty(groupId);
        this.disciplineId = new SimpleIntegerProperty(disciplineId);
        this.date = new SimpleStringProperty(date);
    }

    public int getScheduleId() {
        return scheduleId.get();
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId.set(scheduleId);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }

    public int getDisciplineId() {
        return disciplineId.get();
    }

    public void setDisciplineId(int disciplineId) {
        this.disciplineId.set(disciplineId);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }
}
