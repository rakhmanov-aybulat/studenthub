package org.studenthub.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class Attendance {
    private SimpleIntegerProperty attendanceId;
    private SimpleStringProperty studentFullName;
    private SimpleStringProperty disciplineName;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleBooleanProperty present;

    public Attendance(int attendanceId, String studentFullName,
                      String disciplineName, LocalDate date, boolean present) {
        this.attendanceId = new SimpleIntegerProperty(attendanceId);
        this.studentFullName = new SimpleStringProperty(studentFullName);
        this.disciplineName = new SimpleStringProperty(disciplineName);
        this.date = new SimpleObjectProperty<>(date);
        this.present = new SimpleBooleanProperty(present);
    }

    public int getAttendanceId() {
        return attendanceId.get();
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId.set(attendanceId);
    }

    public String getStudentFullName() {
        return studentFullName.get();
    }

    public void setStudentFullName(String studentFullName) {
        this.studentFullName.set(studentFullName);
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

    public boolean isPresent() {
        return present.get();
    }

    public void setPresent(boolean present) {
        this.present.set(present);
    }
}
