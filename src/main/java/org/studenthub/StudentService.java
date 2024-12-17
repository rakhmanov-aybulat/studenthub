package org.studenthub;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.studenthub.model.*;

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
                "DELETE FROM ATTENDANCE WHERE attendance_id=?",
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
