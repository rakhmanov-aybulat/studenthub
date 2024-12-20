package org.studenthub.exceptions;

import javafx.beans.property.SimpleStringProperty;
import org.studenthub.model.Student;

public class StudentAlreadyExistsException extends Exception {
    private final Student student;
    private SimpleStringProperty groupName;
    public StudentAlreadyExistsException(Student student) {
        super("Student with name: " + student.getFullName() +
                " and group: " + student.getGroupName() + " already exists");
        this.student = student;
    }

    public Student getStudent() {
        return student;
    }
}
