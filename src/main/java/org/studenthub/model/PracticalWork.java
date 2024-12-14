package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PracticalWork {
    private SimpleIntegerProperty practicalWorkId;
    private SimpleIntegerProperty studentId;
    private SimpleIntegerProperty disciplineId;
    private SimpleStringProperty date;
    private SimpleIntegerProperty grade;

    public PracticalWork(int practicalWorkId, int studentId,
                         int disciplineId, String date, int grade) {
        this.practicalWorkId = new SimpleIntegerProperty(practicalWorkId);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.disciplineId = new SimpleIntegerProperty(disciplineId);
        this.date = new SimpleStringProperty(date);
        this.grade = new SimpleIntegerProperty(grade);
    }

    public int getPracticalWorkId() {
        return practicalWorkId.get();
    }

    public void setPracticalWorkId(int practicalWorkId) {
        this.practicalWorkId.set(practicalWorkId);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
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

    public int getGrade() {
        return grade.get();
    }

    public void setGrade(int grade) {
        this.grade.set(grade);
    }
}
