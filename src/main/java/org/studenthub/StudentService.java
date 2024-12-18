package org.studenthub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.studenthub.exceptions.GroupNotFoundException;
import org.studenthub.exceptions.InvalidLineFormatException;
import org.studenthub.model.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
    private Repo db;

    public StudentService(Repo db) {
        this.db = db;
    }

    public void addStudent(Student student) throws SQLException {
        int groupId = getGroupIdByGroupName(student.getGroupName());
        db.executePreparedUpdate(
                "INSERT INTO STUDENTS (full_name, group_id) VALUES (?, ?)",
                student.getFullName(), groupId);
    }

    public void removeStudent(int studentId) {
        db.executePreparedUpdate(
                "DELETE FROM STUDENTS WHERE student_id=?", studentId);
    }

    public boolean studentExists(Student student) {
        String query = "SELECT 1 FROM STUDENTS WHERE full_name=? AND group_id=?";
        try (ResultSet rs = db.executePreparedQuery(query,
                student.getFullName(), student.getGroupName())) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void importStudentsFromFile(File file) throws GroupNotFoundException,
            InvalidLineFormatException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder builder;
        int lineNumber = 0;
        int i;
        while ((line = reader.readLine()) != null) {
            lineNumber += 1;
            String[] parts = line.split(" ");
            if (parts.length < 2) {
                throw new InvalidLineFormatException(lineNumber, line);
            }
            builder = new StringBuilder();
            for (i = 0; i < parts.length - 2; i++) {
                builder.append(parts[i]).append(" ");
            }
            builder.append(parts[i]);
            String fullName = builder.toString();
            String groupName = parts[parts.length - 1];
            Student student = new Student(-1, fullName, groupName);

            if (studentExists(student))
                continue;

            try {
                addStudent(student);
            } catch (SQLException e) {
                throw new GroupNotFoundException(groupName, lineNumber, line);
            }
        }
    }

    public ObservableList<Student> getStudentObservableList() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String query = "SELECT s.student_id, s.full_name, g.group_name " +
                "FROM STUDENTS s " +
                "JOIN GROUPS g ON s.group_id = g.group_id";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("group_name"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    public ObservableList<Group> getGroupsObservableList() {
        ObservableList<Group> groups = FXCollections.observableArrayList();
        String query = "SELECT group_id, group_name FROM GROUPS";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                groups.add(new Group(
                        rs.getInt("group_id"),
                        rs.getString("group_name"))
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }

    public void addGroup(Group group) {
        db.executePreparedUpdate(
                "INSERT INTO GROUPS (group_name) VALUES (?)",
                group.getGroupName());
    }

    public void removeGroup(int groupId) {
        db.executePreparedUpdate(
                "DELETE FROM GROUPS WHERE group_id=?", groupId);
    }

    public int getGroupIdByGroupName(String groupName) throws SQLException {
        String query = "SELECT group_id FROM GROUPS WHERE group_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, groupName)) {
            if (rs.next()) {
                return rs.getInt("group_id");
            } else {
                throw new SQLException("Group not found with name: " + groupName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public ObservableList<Discipline> getDisciplinesObservableList() {
        ObservableList<Discipline> disciplines = FXCollections.observableArrayList();
        String query = "SELECT discipline_id, discipline_name FROM DISCIPLINES";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                disciplines.add(new Discipline(
                        rs.getInt("discipline_id"),
                        rs.getString("discipline_name")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    public void addDiscipline(Discipline discipline) {
        db.executePreparedUpdate(
                "INSERT INTO DISCIPLINES (discipline_name) VALUES (?)",
                discipline.getDisciplineName()
        );
    }

    public void removeDiscipline(int disciplineId) {
        db.executePreparedUpdate(
                "DELETE FROM DISCIPLINES WHERE discipline_id=?",
                disciplineId
        );
    }

    public ObservableList<Schedule> getSchedulesObservableList() {
        ObservableList<Schedule> schedules = FXCollections.observableArrayList();
        String query = "SELECT schedule_id, group_id, " +
                "discipline_id, date FROM SCHEDULE";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                schedules.add(new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getInt("group_id"),
                        rs.getInt("discipline_id"),
                        rs.getString("date")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public void addSchedule(Schedule schedule) {
        db.executePreparedUpdate(
                "INSERT INTO SCHEDULE (group_id, discipline_id, date) " +
                        "VALUES (?, ?, ?)", schedule.getGroupId(),
                schedule.getDisciplineId(), schedule.getDate()
        );
    }

    public void removeSchedule(int scheduleId) {
        db.executePreparedUpdate(
                "DELETE FROM SCHEDULE WHERE schedule_id=?", scheduleId);
    }

    public ObservableList<Attendance> getAttendancesObservableList() {
        ObservableList<Attendance> attendances = FXCollections.observableArrayList();
        String query = "SELECT attendance_id, student_id, " +
                "schedule_id, present FROM ATTENDANCE";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                attendances.add(new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getInt("student_id"),
                        rs.getInt("schedule_id"),
                        rs.getBoolean("present")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendances;
    }

    public void addAttendance(Attendance attendance) {
        db.executePreparedUpdate(
                "INSERT INTO ATTENDANCE (student_id, schedule_id, present) " +
                        "VALUES (?, ?, ?)", attendance.getStudentId(),
                attendance.getScheduleId(), attendance.isPresent());
    }

    public void removeAttendance(int attendanceId) {
        db.executePreparedUpdate(
                "DELETE FROM ATTENDANCE WHERE attendance_id=?;",
                attendanceId);
    }

    public ObservableList<PracticalWork> getPracticalWorksObservableList() {
        ObservableList<PracticalWork> practicalWorks = FXCollections.observableArrayList();
        String query = "SELECT practical_work_id, student_id, " +
                "discipline_id, date, grade FROM PRACTICALWORKS";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                practicalWorks.add(new PracticalWork(
                        rs.getInt("practical_work_id"),
                        rs.getInt("student_id"),
                        rs.getInt("discipline_id"),
                        rs.getString("date"),
                        rs.getInt("grade")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return practicalWorks;
    }

    public void addPracticalWork(PracticalWork practicalWork) {
        db.executePreparedUpdate(
                "INSERT INTO PRACTICALWORKS (student_id, discipline_id, " +
                        "date, grade) VALUES (?, ?, ?, ?)",
                practicalWork.getStudentId(), practicalWork.getDisciplineId(),
                practicalWork.getDate(), practicalWork.getGrade()
        );
    }

    public void removePracticalWork(int practicalWorkId) {
        db.executePreparedUpdate(
                "DELETE FROM PRACTICALWORKS WHERE practical_work_id=?",
                practicalWorkId
        );
    }
}
