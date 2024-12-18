package org.studenthub.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Student {
    private SimpleIntegerProperty studentId;
    private SimpleStringProperty fullName;
    private SimpleStringProperty groupName;

    public Student(int studentId, String fullName, String groupName) {
        this.studentId = new SimpleIntegerProperty(studentId);
        this.fullName = new SimpleStringProperty(fullName);
        this.groupName = new SimpleStringProperty(groupName);
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

    public String getGroupName() {
        return groupName.get();
    }

    public void setGroupName(String groupName) {
        this.groupName.set(groupName);
    }
}
