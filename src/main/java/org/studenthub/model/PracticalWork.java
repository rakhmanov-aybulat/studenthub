package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class PracticalWork {
    private SimpleIntegerProperty practicalWorkId;
    private SimpleStringProperty studentFullName;
    private SimpleStringProperty disciplineName;
    private SimpleObjectProperty<LocalDate> date;
    private SimpleIntegerProperty grade;

    public PracticalWork(int practicalWorkId, String studentName,
                         String disciplineName, LocalDate date, int grade) {
        this.practicalWorkId = new SimpleIntegerProperty(practicalWorkId);
        this.studentFullName = new SimpleStringProperty(studentName);
        this.disciplineName = new SimpleStringProperty(disciplineName);
        this.date = new SimpleObjectProperty<>(date);
        this.grade = new SimpleIntegerProperty(grade);
    }

    public int getPracticalWorkId() {
        return practicalWorkId.get();
    }

    public void setPracticalWorkId(int practicalWorkId) {
        this.practicalWorkId.set(practicalWorkId);
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

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }
}
