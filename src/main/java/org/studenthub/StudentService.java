package org.studenthub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.studenthub.exceptions.*;
import org.studenthub.model.*;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class StudentService {
    private final Repo db;

    public StudentService(Repo db) {
        this.db = db;
    }

    public void addStudent(String fullName, String groupName)
            throws EntityNotFoundException,
            EntityAlreadyExistsException, SQLException {
        if (studentExists(fullName, groupName)) {
            throw new EntityAlreadyExistsException(
                    "Student with name: " + fullName +
                    " and group name: " + groupName + "already exists");
        }
        if (!groupExists(groupName)) {
            throw new EntityNotFoundException(
                    "Group not found with name: " + groupName);
        }

        String query = "INSERT INTO STUDENTS (full_name, group_id) " +
                "SELECT ?, group_id " +
                "FROM GROUPS WHERE group_name = ?";
        db.executePreparedUpdate(query, fullName, groupName);
    }

    public void removeStudent(int studentId)
            throws EntityDeletionException, SQLException {
        String query = "DELETE FROM STUDENTS WHERE student_id=?";
        try {
            db.executePreparedUpdate(query, studentId);
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new EntityDeletionException(
                        "Cannot delete student with ID: " + studentId +
                        ". because it has associations with other entities.");
            }
            throw e;
        }
    }

    public boolean studentExists(int studentId) {
        String query = "SELECT 1 FROM STUDENTS WHERE student_id=?";
        try (ResultSet rs = db.executePreparedQuery(query, studentId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean studentExists(String fullName, String groupName) {
        String query = "SELECT 1 FROM STUDENTS s " +
                "JOIN GROUPS g ON s.group_id = g.group_id " +
                "WHERE s.full_name = ? AND g.group_name = ?";
        try (ResultSet rs = db.executePreparedQuery(
                query, fullName, groupName)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public void importStudentsFromFile(File file)
            throws ImportGroupNotFoundException,
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

            try {
                addStudent(fullName, groupName);
            } catch (EntityNotFoundException e) {
                throw new ImportGroupNotFoundException(groupName, lineNumber , line);
            } catch (EntityAlreadyExistsException e) {
                continue;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ObservableList<Student> getStudentObservableList() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        String query = "SELECT s.student_id, s.full_name, g.group_name " +
                "FROM STUDENTS s JOIN GROUPS g ON s.group_id = g.group_id";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("student_id"),
                        rs.getString("full_name"),
                        rs.getString("group_name"))
                );
            }
        } catch (Exception ignored) {}
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
        } catch (Exception ignored) {}
        return groups;
    }

    public void addGroup(String groupName)
            throws EntityAlreadyExistsException, SQLException {
        if (groupExists(groupName)) {
            throw new EntityAlreadyExistsException(
                    "Group with name: " + groupName + " already exists");
        }

        db.executePreparedUpdate(
                "INSERT INTO GROUPS (group_name) VALUES (?)", groupName);
    }

    public void removeGroup(int groupId)
            throws EntityDeletionException, SQLException {
        String query = "DELETE FROM GROUPS WHERE group_id=?";
        try {
            db.executePreparedUpdate(query, groupId);
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new EntityDeletionException(
                        "Cannot delete group with ID: " + groupId +
                        ". because it has associations with other entities.");
            }
            throw e;
        }
    }

    public boolean groupExists(String groupName) {
        String query = "SELECT 1 FROM GROUPS WHERE group_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, groupName)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
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
        } catch (Exception ignored) {}
        return disciplines;
    }

    public void addDiscipline(String disciplineName)
            throws EntityAlreadyExistsException, SQLException {
        if (disciplineExists(disciplineName)) {
            throw new EntityAlreadyExistsException("Discipline with name: " +
                    disciplineName + " already exists");
        }
        String query = "INSERT INTO DISCIPLINES (discipline_name) VALUES (?)";
        db.executePreparedUpdate(query, disciplineName);
    }

    public void removeDiscipline(int disciplineId)
            throws EntityDeletionException, SQLException {
        String query = "DELETE FROM DISCIPLINES WHERE discipline_id=?";
        try {
            db.executePreparedUpdate(query, disciplineId);
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new EntityDeletionException(
                        "Cannot delete discipline with ID: " + disciplineId +
                        ". because it has associations with other entities.");
            }
            throw e;
        }
    }

    public boolean disciplineExists(String disciplineName) {
        String query = "SELECT 1 FROM DISCIPLINES WHERE discipline_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, disciplineName)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }


    public ObservableList<Schedule> getSchedulesObservableList() {
        ObservableList<Schedule> schedules = FXCollections.observableArrayList();
        String query = "SELECT s.schedule_id, g.group_name, d.discipline_name, s.date " +
                "FROM SCHEDULE s " +
                "JOIN GROUPS g ON s.group_id = g.group_id " +
                "JOIN DISCIPLINES d ON s.discipline_id = d.discipline_id";

        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                schedules.add(new Schedule(
                        rs.getInt("schedule_id"),
                        rs.getString("group_name"),
                        rs.getString("discipline_name"),
                        LocalDate.parse(rs.getString("date"))
                ));
            }
        } catch (Exception ignored) {}
        return schedules;
    }

    public void addSchedule(String groupName, String disciplineName, LocalDate date)
            throws EntityAlreadyExistsException, EntityNotFoundException, SQLException {
        if (scheduleExists(groupName, disciplineName, date)) {
            throw new EntityAlreadyExistsException("Schedule already exists.");
        }
        String query = "INSERT INTO SCHEDULE (group_id, discipline_id, date) " +
                "SELECT g.group_id, d.discipline_id, ? " +
                "FROM GROUPS g " +
                "JOIN DISCIPLINES d ON d.discipline_name = ? " +
                "WHERE g.group_name = ?";
        db.executePreparedUpdate(query, date, disciplineName, groupName);
    }

    public void removeSchedule(int scheduleId)
            throws EntityDeletionException, SQLException {
        try {
            db.executePreparedUpdate(
                    "DELETE FROM SCHEDULE WHERE schedule_id=?", scheduleId);
        } catch (SQLException e) {
            if (e.getErrorCode() == 19) {
                throw new EntityDeletionException(
                        "Cannot delete schedule with ID: " + scheduleId +
                        ". because it has associations with other entities.");
            }
            throw e;
        }
    }

    public boolean scheduleExists(int scheduleId) {
        String query = "SELECT 1 FROM SCHEDULE WHERE schedule_id=?";
        try (ResultSet rs = db.executePreparedQuery(query, scheduleId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean scheduleExists(
            String groupName, String disciplineName, LocalDate date) {
        String query = "SELECT 1 FROM SCHEDULE s " +
                "JOIN GROUPS g ON s.group_id = g.group_id " +
                "JOIN DISCIPLINES d ON s.discipline_id = d.discipline_id " +
                "WHERE g.group_name = ? AND d.discipline_name = ? AND s.date = ?";
        try (ResultSet rs = db.executePreparedQuery(
                query, groupName, disciplineName, date)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public ObservableList<Attendance> getAttendancesObservableList() {
        ObservableList<Attendance> attendances = FXCollections.observableArrayList();
        String query = "SELECT a.attendance_id, s.full_name, d.discipline_name, sc.date, a.present " +
                "FROM ATTENDANCE a " +
                "JOIN STUDENTS s ON a.student_id = s.student_id " +
                "JOIN SCHEDULE sc ON a.schedule_id = sc.schedule_id " +
                "JOIN GROUPS g ON sc.group_id = g.group_id " +
                "JOIN DISCIPLINES d ON sc.discipline_id = d.discipline_id";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                attendances.add(new Attendance(
                        rs.getInt("attendance_id"),
                        rs.getString("full_name"),
                        rs.getString("discipline_name"),
                        LocalDate.parse(rs.getString("date")),
                        rs.getBoolean("present")
                ));
            }
        } catch (Exception ignored) {}
        return attendances;
    }

    public void addAttendance(int studentId, int scheduleId, boolean present)
            throws EntityAlreadyExistsException,
            EntityNotFoundException, SQLException {
        if (attendanceExists(studentId, scheduleId)) {
            throw new EntityAlreadyExistsException("Attendance already exists");
        }
        if (!studentExists(studentId)) {
            throw new EntityNotFoundException(
                    "Student not found with id: " + studentId);
        }
        if (!scheduleExists(scheduleId)) {
            throw new EntityNotFoundException(
                    "Schedule not found with id: " + scheduleId);
        }

        db.executePreparedUpdate(
                "INSERT INTO ATTENDANCE (student_id, schedule_id, present) " +
                        "VALUES (?, ?, ?)", studentId, scheduleId, present);
    }

    public void removeAttendance(int attendanceId) throws SQLException {
        db.executePreparedUpdate(
                "DELETE FROM ATTENDANCE WHERE attendance_id=?;",
                attendanceId);
    }

    public boolean attendanceExists(int studentId, int scheduleId) {
        String query = "SELECT 1 FROM ATTENDANCE WHERE " +
                "student_id=? AND schedule_id=?";
        try (ResultSet rs = db.executePreparedQuery(
                query, studentId, scheduleId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public ObservableList<PracticalWork> getPracticalWorksObservableList() {
        ObservableList<PracticalWork> practicalWorks = FXCollections.observableArrayList();
        String query = "SELECT pw.practical_work_id, s.full_name," +
                "d.discipline_name, pw.date, pw.grade " +
                "FROM PRACTICALWORKS pw " +
                "JOIN STUDENTS s ON pw.student_id = s.student_id " +
                "JOIN DISCIPLINES d ON pw.discipline_id = d.discipline_id";
        try (ResultSet rs = db.executeQuery(query)) {
            while (rs.next()) {
                practicalWorks.add(new PracticalWork(
                        rs.getInt("practical_work_id"),
                        rs.getString("full_name"),
                        rs.getString("discipline_name"),
                        LocalDate.parse(rs.getString("date")),
                        rs.getInt("grade")
                ));
            }
        } catch (Exception ignored) {}
        return practicalWorks;
    }

    public void addPracticalWork(int studentId, String disciplineName, LocalDate date, int grade)
            throws EntityAlreadyExistsException, EntityNotFoundException, SQLException {
        if (practicalWorkExists(studentId, disciplineName, date)) {
            throw new EntityAlreadyExistsException(
                    "Practical work already exists.");
        }
        String query = "INSERT INTO PRACTICALWORKS " +
                "(student_id, discipline_id, date, grade) " +
                "SELECT ?, d.discipline_id, ?, ? " +
                "FROM DISCIPLINES d " +
                "WHERE d.discipline_name = ?";
        db.executePreparedUpdate(query, studentId, date, grade, disciplineName);
    }

    public void removePracticalWork(int practicalWorkId) throws SQLException {
        String query = "DELETE FROM PRACTICALWORKS WHERE practical_work_id=?";
        db.executePreparedUpdate(query, practicalWorkId);
    }

    public boolean practicalWorkExists(int studentId, String disciplineName, LocalDate date) {
        String query = "SELECT 1 FROM PRACTICALWORKS pw " +
                "JOIN DISCIPLINES d ON pw.discipline_id = d.discipline_id " +
                "WHERE pw.student_id = ? AND " +
                "d.discipline_name = ? AND " +
                "pw.date = ?";
        try (ResultSet rs = db.executePreparedQuery(
                query, studentId, disciplineName, date)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public ObservableList<Statistics> getStatisticsObservableList(Statistics.StatisticsType statisticsType, String disciplineName) {
        ObservableList<Statistics> statistics = FXCollections.observableArrayList();
        String query = null;

        switch (statisticsType) {
            case STUDENT:
                query = "SELECT " +
                        "s.full_name AS name, " +
                        "(COUNT(CASE WHEN a.present = TRUE THEN 1 END) * 100.0 / COUNT(DISTINCT sch.date)) AS attendance_percentage, " +
                        "AVG(pw.grade) AS average_grade " +
                        "FROM Students s " +
                        "JOIN Attendance a ON s.student_id = a.student_id " +
                        "JOIN Schedule sch ON a.schedule_id = sch.schedule_id " +
                        "JOIN Disciplines d ON sch.discipline_id = d.discipline_id " +
                        "LEFT JOIN PracticalWorks pw ON s.student_id = pw.student_id AND sch.discipline_id = pw.discipline_id " +
                        "WHERE d.discipline_name = ? " +
                        "GROUP BY s.student_id, s.full_name, d.discipline_name";
                break;
            case GROUP:
                query = "WITH StudentPerformance AS ( " +
                        "SELECT " +
                        "s.group_id, " +
                        "d.discipline_name, " +
                        "(COUNT(CASE WHEN a.present = TRUE THEN 1 END) * 100.0 / COUNT(DISTINCT sch.date)) AS attendance_percentage, " +
                        "AVG(pw.grade) AS average_grade " +
                        "FROM Students s " +
                        "JOIN Attendance a ON s.student_id = a.student_id " +
                        "JOIN Schedule sch ON a.schedule_id = sch.schedule_id " +
                        "JOIN Disciplines d ON sch.discipline_id = d.discipline_id " +
                        "LEFT JOIN PracticalWorks pw ON s.student_id = pw.student_id AND sch.discipline_id = pw.discipline_id " +
                        "WHERE d.discipline_name = ? " +
                        "GROUP BY s.group_id, d.discipline_name, s.student_id " +
                        ") " +
                        "SELECT " +
                        "g.group_name AS name, " +
                        "AVG(sp.attendance_percentage) AS attendance_percentage, " +
                        "AVG(sp.average_grade) AS average_grade " +
                        "FROM StudentPerformance sp " +
                        "JOIN Groups g ON sp.group_id = g.group_id " +
                        "GROUP BY g.group_name";
                break;
        }

        try (ResultSet rs = db.executePreparedQuery(query, disciplineName)) {
            while (rs.next()) {
                statistics.add(new Statistics(
                        rs.getString("name"),
                        rs.getFloat("attendance_percentage"),
                        rs.getFloat("average_grade")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return statistics;
    }
}
