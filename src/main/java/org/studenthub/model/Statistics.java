package org.studenthub.model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Statistics {
    private SimpleStringProperty name;
    private SimpleFloatProperty attendancePercentage;
    private SimpleFloatProperty averageGrade;

    public Statistics(String name, float attendancePercentage,
                    float averageGrade) {
        this.name = new SimpleStringProperty(name);
        this.attendancePercentage = new SimpleFloatProperty(attendancePercentage);
        this.averageGrade = new SimpleFloatProperty(averageGrade);
    }

    public enum StatisticsType {
        STUDENT,
        GROUP;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public float getAttendancePercentage() {
        return attendancePercentage.get();
    }

    public void setAttendancePercentage(float attendancePercentage) {
        this.attendancePercentage.set(attendancePercentage);
    }

    public float getAverageGrade() {
        return averageGrade.get();
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade.set(averageGrade);
    }
}

