package org.studenthub.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Attendance {
    private SimpleIntegerProperty attendanceId;
    private SimpleIntegerProperty studentId;
    private SimpleIntegerProperty scheduleId;
    private SimpleBooleanProperty present;

    public Attendance(int attendanceId, int studentId,
                      int scheduleId, boolean present) {
        this.attendanceId = new SimpleIntegerProperty(attendanceId);
        this.studentId = new SimpleIntegerProperty(studentId);
        this.scheduleId = new SimpleIntegerProperty(scheduleId);
        this.present = new SimpleBooleanProperty(present);
    }

    public int getAttendanceId() {
        return attendanceId.get();
    }

    public void setAttendanceId(int attendanceId) {
        this.attendanceId.set(attendanceId);
    }

    public int getStudentId() {
        return studentId.get();
    }

    public void setStudentId(int studentId) {
        this.studentId.set(studentId);
    }

    public int getScheduleId() {
        return scheduleId.get();
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId.set(scheduleId);
    }

    public boolean isPresent() {
        return present.get();
    }

    public void setPresent(boolean present) {
        this.present.set(present);
    }
}
