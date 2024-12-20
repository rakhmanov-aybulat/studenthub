package org.studenthub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.studenthub.exceptions.*;
import org.studenthub.model.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
    private Repo db;

    public StudentService(Repo db) {
        this.db = db;
    }

    public void addStudent(Student student) throws GroupNotFoundException, StudentAlreadyExistsException, SQLException {
        if (studentExists(student)) {
            throw new StudentAlreadyExistsException(student);
        }
        int groupId = getGroupIdByGroupName(student.getGroupName());
        db.executePreparedUpdate(
                "INSERT INTO STUDENTS (full_name, group_id) VALUES (?, ?)",
                student.getFullName(), groupId);
    }

    public void removeStudent(int studentId) throws EntityDeletionException {
        String query = "DELETE FROM STUDENTS WHERE student_id=?";
        try {
            db.executePreparedUpdate(query, studentId);
        } catch (SQLException e) {
            throw new EntityDeletionException(
                    "Cannot delete student with ID: " + studentId +
                    ". because it has associations with other entities.");
        }
    }

    public boolean studentExists(Student student) {
        int groupId;
        try {
            groupId = getGroupIdByGroupName(student.getGroupName());
        } catch (GroupNotFoundException e) {
            return false;
        }

        String query = "SELECT 1 FROM STUDENTS WHERE full_name=? AND group_id=?";
        try (ResultSet rs = db.executePreparedQuery(query,
                student.getFullName(), groupId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public void importStudentsFromFile(File file) throws ImportGroupNotFoundException,
            ImportInvalidLineFormatException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int lineNumber = 0;
        while ((line = reader.readLine()) != null) {
            lineNumber += 1;
            String[] parts = line.split(",");
            if (parts.length != 2) {
                throw new ImportInvalidLineFormatException(lineNumber, line);
            }
            String fullName = parts[0].trim();
            String groupName = parts[1].trim();
            Student student = new Student(-1, fullName, groupName);

            try {
                addStudent(student);
            } catch (GroupNotFoundException e) {
                throw new ImportGroupNotFoundException(groupName, lineNumber , line);
            } catch (StudentAlreadyExistsException e) {
                continue;
            } catch (SQLException e) {
                throw new RuntimeException(e);
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

    public void addGroup(Group group) throws GroupAlreadyExistsException, SQLException {
        if (groupExists(group))
            throw new GroupAlreadyExistsException(group.getGroupName());

        db.executePreparedUpdate(
                "INSERT INTO GROUPS (group_name) VALUES (?)",
                group.getGroupName());
    }

    public void removeGroup(int groupId) throws EntityDeletionException {
        try {
            db.executePreparedUpdate(
                    "DELETE FROM GROUPS WHERE group_id=?", groupId);
        } catch (SQLException e) {
            throw new EntityDeletionException(
                    "Cannot delete group with ID: " + groupId +
                    ". because it has associations with other entities.");
        }
    }

    public boolean groupExists(Group group) {
        String query = "SELECT 1 FROM GROUPS WHERE group_name=?";
        try (ResultSet rs = db.executePreparedQuery(
                query, group.getGroupName())) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int getGroupIdByGroupName(String groupName) throws GroupNotFoundException {
        String query = "SELECT group_id FROM GROUPS WHERE group_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, groupName)) {
            if (rs.next())
                return rs.getInt("group_id");
        } catch (SQLException ignored) {}
        throw new GroupNotFoundException(groupName);
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

    public void addDiscipline(Discipline discipline) throws SQLException {
        db.executePreparedUpdate(
                "INSERT INTO DISCIPLINES (discipline_name) VALUES (?)",
                discipline.getDisciplineName()
        );
    }

    public void removeDiscipline(int disciplineId) throws SQLException {
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

    public void addSchedule(Schedule schedule) throws SQLException {
        db.executePreparedUpdate(
                "INSERT INTO SCHEDULE (group_id, discipline_id, date) " +
                        "VALUES (?, ?, ?)", schedule.getGroupId(),
                schedule.getDisciplineId(), schedule.getDate()
        );
    }

    public void removeSchedule(int scheduleId) throws SQLException {
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

    public void addAttendance(Attendance attendance) throws SQLException {
        db.executePreparedUpdate(
                "INSERT INTO ATTENDANCE (student_id, schedule_id, present) " +
                        "VALUES (?, ?, ?)", attendance.getStudentId(),
                attendance.getScheduleId(), attendance.isPresent());
    }

    public void removeAttendance(int attendanceId) throws SQLException {
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

    public void addPracticalWork(PracticalWork practicalWork) throws SQLException {
        db.executePreparedUpdate(
                "INSERT INTO PRACTICALWORKS (student_id, discipline_id, " +
                        "date, grade) VALUES (?, ?, ?, ?)",
                practicalWork.getStudentId(), practicalWork.getDisciplineId(),
                practicalWork.getDate(), practicalWork.getGrade()
        );
    }

    public void removePracticalWork(int practicalWorkId) throws SQLException {
        db.executePreparedUpdate(
                "DELETE FROM PRACTICALWORKS WHERE practical_work_id=?",
                practicalWorkId
        );
    }
}
