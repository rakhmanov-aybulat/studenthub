package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleIntegerProperty studentId;
    private SimpleStringProperty fullName;
    private SimpleIntegerProperty groupId;

    public Student(int studentId, String fullName, int groupId) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.fullName = new SimpleStringProperty(fullName);
        this.groupId = new SimpleIntegerProperty(groupId);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public String getFullName() {
        return fullName.get();
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

    public int getGroupId() {
        return groupId.get();
    }

    public void setGroupId(int groupId) {
        this.groupId.set(groupId);
    }
}
