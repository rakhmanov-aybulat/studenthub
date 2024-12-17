package org.studenthub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.studenthub.model.Student;

import java.sql.ResultSet;
import java.util.List;

public class StudentService {
    private Repo db;

    public StudentService(Repo db) {
        this.db = db;
    }

    public void addStudent(Student student) {
        db.executePreparedUpdate(
                "INSERT INTO STUDENTS (full_name, group_id) VALUES (?, ?)",
                student.getFullName(), student.getGroupId());
    }

    public void removeStudent(int studentId) {
        db.executePreparedUpdate(
                "DELETE FROM STUDENTS WHERE student_id=?", studentId);
    }


    public void importStudentList(List<Student> students) {

    }

    public ObservableList<Student> getStudentObservableList() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String query = "SELECT student_id, full_name, group_id FROM STUDENTS";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getInt("group_id"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }
}
