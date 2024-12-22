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
    private Repo db;

    public StudentService(Repo db) {
        this.db = db;
    }

    public void addStudent(Student student) throws EntityNotFoundException,
            EntityAlreadyExistsException, SQLException {
        if (studentExists(student)) {
            throw new EntityAlreadyExistsException("Group with name: " +
                    student.getFullName() + " already exists");
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

    public boolean studentExists(int studentId) {
        String query = "SELECT 1 FROM STUDENTS WHERE student_id=?";
        try (ResultSet rs = db.executePreparedQuery(query, studentId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean studentExists(Student student) {
        int groupId;
        try {
            groupId = getGroupIdByGroupName(student.getGroupName());
        } catch (EntityNotFoundException e) {
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

    public void addGroup(Group group) throws EntityAlreadyExistsException, SQLException {
        if (groupExists(group))
            throw new EntityAlreadyExistsException("Group with name: " +
                    group.getGroupName() + " already exists");

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

    public int getGroupIdByGroupName(String groupName) throws EntityNotFoundException {
        String query = "SELECT group_id FROM GROUPS WHERE group_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, groupName)) {
            if (rs.next())
                return rs.getInt("group_id");
        } catch (SQLException ignored) {}
        throw new EntityNotFoundException(
                "Group not found with name: " + groupName);
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

    public void addDiscipline(Discipline discipline) throws SQLException, EntityAlreadyExistsException {
        if (disciplineExists(discipline))
            throw new EntityAlreadyExistsException("Discipline with name: " +
                    discipline.getDisciplineName() + " already exists");
        db.executePreparedUpdate(
                "INSERT INTO DISCIPLINES (discipline_name) VALUES (?)",
                discipline.getDisciplineName()
        );
    }

    public void removeDiscipline(int disciplineId) throws EntityDeletionException {
        try {
            db.executePreparedUpdate(
                    "DELETE FROM DISCIPLINES WHERE discipline_id=?",
                    disciplineId);
        } catch (SQLException e) {
            throw new EntityDeletionException(
                    "Cannot delete discipline with ID: " + disciplineId +
                            ". because it has associations with other entities.");
        }
    }

    private boolean disciplineExists(Discipline discipline) {
        String query = "SELECT 1 FROM DISCIPLINES WHERE discipline_name=?";
        try (ResultSet rs = db.executePreparedQuery(
                query, discipline.getDisciplineName())) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    public int getDisciplineIdByDisciplineName(String disciplineName) throws EntityNotFoundException {
        String query = "SELECT discipline_id FROM DISCIPLINES WHERE discipline_name=?";
        try (ResultSet rs = db.executePreparedQuery(query, disciplineName)) {
            if (rs.next())
                return rs.getInt("discipline_id");
        } catch (SQLException ignored) {}
        throw new EntityNotFoundException(
                "Discipline not found with name: " + disciplineName);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return schedules;
    }

    public void addSchedule(Schedule schedule) throws SQLException, EntityAlreadyExistsException, EntityNotFoundException {
        if (scheduleExists(schedule)) {
            throw new EntityAlreadyExistsException("Schedule already exists");
        }
        int groupId = getGroupIdByGroupName(schedule.getGroupName());
        int disciplineId = getDisciplineIdByDisciplineName(schedule.getDisciplineName());

        db.executePreparedUpdate(
                "INSERT INTO SCHEDULE (group_id, discipline_id, date) " +
                        "VALUES (?, ?, ?)", groupId, disciplineId, schedule.getDate());
    }

    public void removeSchedule(int scheduleId) throws EntityDeletionException {
        try {
            db.executePreparedUpdate(
                    "DELETE FROM SCHEDULE WHERE schedule_id=?", scheduleId);
        } catch (SQLException e) {
            throw new EntityDeletionException(
                    "Cannot delete schedule with ID: " + scheduleId +
                    ". because it has associations with other entities.");
        }
    }

    private boolean scheduleExists(int scheduleId) {
        String query = "SELECT 1 FROM SCHEDULE WHERE schedule_id=?";
        try (ResultSet rs = db.executePreparedQuery(query, scheduleId)) {
            return rs.next();
        } catch (SQLException e) {
            return false;
        }
    }

    private boolean scheduleExists(Schedule schedule) {
        int groupId;
        int disciplineId;
        try {
            groupId = getGroupIdByGroupName(schedule.getGroupName());
            disciplineId = getDisciplineIdByDisciplineName(schedule.getDisciplineName());
        } catch (EntityNotFoundException e) {
            return false;
        }

        String query = "SELECT 1 FROM SCHEDULE WHERE " +
                "group_id=? AND discipline_id=? AND date=?";
        try (ResultSet rs = db.executePreparedQuery(query,
                groupId, disciplineId, schedule.getDate())) {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return attendances;
    }

    public void addAttendance(int studentId, int scheduleId, boolean present) throws SQLException, EntityNotFoundException, EntityAlreadyExistsException {
        if (!studentExists(studentId)) {
            throw new EntityNotFoundException(
                    "Student not found with id: " + studentId);
        }
        if (!scheduleExists(scheduleId)) {
            throw new EntityNotFoundException(
                    "Schedule not found with id: " + scheduleId);
        }
        if (attendanceExists(studentId, scheduleId)) {
            throw new EntityAlreadyExistsException("Attendance already exists");
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
