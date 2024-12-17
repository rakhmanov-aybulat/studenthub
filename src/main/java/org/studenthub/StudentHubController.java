package org.studenthub;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.studenthub.model.*;

public class StudentHubController implements Initializable {
    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TextField studentsFullNameField;

    @FXML
    private TextField studentsGroupIdField;

    @FXML
    private TextField studentsStudentIdField;

    @FXML
    private TableView<Group> groupsTableView;

    @FXML
    private TextField groupsGroupNameField;

    @FXML
    private TextField groupsGroupIdField;

    @FXML
    private TableView<Discipline> disciplinesTableView;

    @FXML
    private TextField disciplinesDisciplineNameField;

    @FXML
    private TextField disciplinesDisciplineIdField;

    @FXML
    private TableView<Schedule> scheduleTableView;

    @FXML
    private TextField scheduleGroupIdField;

    @FXML
    private TextField scheduleDisciplineIdField;

    @FXML
    private TextField scheduleDateField;

    @FXML
    private TextField scheduleScheduleIdField;

    @FXML
    private TableView<Attendance> attendanceTableView;

    @FXML
    private TextField attendanceStudentIdField;

    @FXML
    private TextField attendanceScheduleIdField;

    @FXML
    private TextField attendancePresentField;

    @FXML
    private TextField attendanceAttendanceIdField;

    @FXML
    private TableView<PracticalWork> practicalWorksTableView;

    @FXML
    private TextField practicalWorksStudentIdField;

    @FXML
    private TextField practicalWorksDisciplineIdField;

    @FXML
    private TextField practicalWorksDateField;

    @FXML
    private TextField practicalWorksGradeField;

    @FXML
    private TextField practicalWorksPracticalWorkIdField;

    private StudentService studentService;

    public StudentHubController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupStudentsTableView();
        updateStudentsTableView();
        setupGroupsTableView();
        updateGroupsTableView();

        setupDisciplinesTableView();
        setupScheduleTableView();
        setupAttendanceTableView();
        setupPracticalWorksTableView();
    }

    private void setupStudentsTableView() {
        TableColumn<Student, Integer> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentsTableView.getColumns().add(studentIdColumn);

        TableColumn<Student, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        studentsTableView.getColumns().add(fullNameColumn);

        TableColumn<Student, Integer> groupIdColumn = new TableColumn<>("Group ID");
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        studentsTableView.getColumns().add(groupIdColumn);
    }

    private void setupGroupsTableView() {
        TableColumn<Group, Integer> groupIdColumn = new TableColumn<>("Group ID");
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        groupsTableView.getColumns().add(groupIdColumn);

        TableColumn<Group, String> groupNameColumn = new TableColumn<>("Group Name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        groupsTableView.getColumns().add(groupNameColumn);
    }

    private void setupDisciplinesTableView() {
        TableColumn<Discipline, Integer> disciplineIdColumn = new TableColumn<>("Discipline ID");
        disciplineIdColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineId"));
        disciplinesTableView.getColumns().add(disciplineIdColumn);

        TableColumn<Discipline, String> disciplineNameColumn = new TableColumn<>("Discipline Name");
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
        disciplinesTableView.getColumns().add(disciplineNameColumn);
    }

    private void setupScheduleTableView() {
        TableColumn<Schedule, Integer> scheduleIdColumn = new TableColumn<>("Schedule ID");
        scheduleIdColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        scheduleTableView.getColumns().add(scheduleIdColumn);

        TableColumn<Schedule, Integer> groupIdColumn = new TableColumn<>("Group ID");
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        scheduleTableView.getColumns().add(groupIdColumn);

        TableColumn<Schedule, Integer> disciplineIdColumn = new TableColumn<>("Discipline ID");
        disciplineIdColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineId"));
        scheduleTableView.getColumns().add(disciplineIdColumn);

        TableColumn<Schedule, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        scheduleTableView.getColumns().add(dateColumn);
    }

    private void setupAttendanceTableView() {
        TableColumn<Attendance, Integer> attendanceIdColumn = new TableColumn<>("Attendance ID");
        attendanceIdColumn.setCellValueFactory(new PropertyValueFactory<>("attendanceId"));
        attendanceTableView.getColumns().add(attendanceIdColumn);

        TableColumn<Attendance, Integer> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        attendanceTableView.getColumns().add(studentIdColumn);

        TableColumn<Attendance, Integer> scheduleIdColumn = new TableColumn<>("Schedule ID");
        scheduleIdColumn.setCellValueFactory(new PropertyValueFactory<>("scheduleId"));
        attendanceTableView.getColumns().add(scheduleIdColumn);

        TableColumn<Attendance, Boolean> presentColumn = new TableColumn<>("Present");
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
        attendanceTableView.getColumns().add(presentColumn);
    }

    private void setupPracticalWorksTableView() {
        TableColumn<PracticalWork, Integer> practicalWorkIdColumn = new TableColumn<>("Practical Work ID");
        practicalWorkIdColumn.setCellValueFactory(new PropertyValueFactory<>("practicalWorkId"));
        practicalWorksTableView.getColumns().add(practicalWorkIdColumn);

        TableColumn<PracticalWork, Integer> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        practicalWorksTableView.getColumns().add(studentIdColumn);

        TableColumn<PracticalWork, Integer> disciplineIdColumn = new TableColumn<>("Discipline ID");
        disciplineIdColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineId"));
        practicalWorksTableView.getColumns().add(disciplineIdColumn);

        TableColumn<PracticalWork, String> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        practicalWorksTableView.getColumns().add(dateColumn);

        TableColumn<PracticalWork, Integer> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        practicalWorksTableView.getColumns().add(gradeColumn);
    }

    private void updateStudentsTableView() {
        ObservableList<Student> students = studentService.getStudentObservableList();
        studentsTableView.setItems(students);
    }

    private void updateGroupsTableView() {
        ObservableList<Group> groups = studentService.getGroupsObservableList();
        groupsTableView.setItems(groups);
    }

    public void updateDisciplinesTableView() {
        ObservableList<Discipline> disciplines = studentService.getDisciplinesObservableList();
        disciplinesTableView.setItems(disciplines);
    }

    public void updateScheduleTableView() {
        ObservableList<Schedule> schedules = studentService.getSchedulesObservableList();
        scheduleTableView.setItems(schedules);
    }

    public void updateAttendanceTableView() {
        ObservableList<Attendance> attendances = studentService.getAttendancesObservableList();
        attendanceTableView.setItems(attendances);
    }

    public void updatePracticalWorksTableView() {
        ObservableList<PracticalWork> practicalWorks = studentService.getPracticalWorksObservableList();
        practicalWorksTableView.setItems(practicalWorks);
    }

    @FXML
    private void handleAddStudentButtonClick() {
        String fullName = studentsFullNameField.getText();
        int groupId = Integer.parseInt(studentsGroupIdField.getText());
        Student student = new Student(-1, fullName, groupId);

        studentService.addStudent(student);

        updateStudentsTableView();

        studentsFullNameField.clear();
        studentsGroupIdField.clear();
    }

    @FXML
    private void handleRemoveStudentButtonClick() {
        int studentId = Integer.parseInt(studentsStudentIdField.getText());
        studentService.removeStudent(studentId);
        updateStudentsTableView();

        studentsStudentIdField.clear();
    }

    @FXML
    private void handleAddGroupButtonClick() {
        String groupName = groupsGroupNameField.getText();
        Group group = new Group(-1, groupName);

        studentService.addGroup(group);

        updateGroupsTableView();
        groupsGroupNameField.clear();
    }

    @FXML
    private void handleRemoveGroupButtonClick() {
        int groupId = Integer.parseInt(groupsGroupIdField.getText());
        studentService.removeGroup(groupId);
        updateGroupsTableView();

        studentsGroupIdField.clear();
    }

    @FXML
    private void handleAddDisciplineButtonClick() {
        String disciplineName = disciplinesDisciplineNameField.getText();
        Discipline discipline = new Discipline(-1, disciplineName);

        studentService.addDiscipline(discipline);
        updateDisciplinesTableView();

        disciplinesDisciplineNameField.clear();
    }

    @FXML
    private void handleRemoveDisciplineButtonClick() {
        int disciplineId = Integer.parseInt(disciplinesDisciplineIdField.getText());
        studentService.removeDiscipline(disciplineId);
        updateDisciplinesTableView();

        disciplinesDisciplineIdField.clear();
    }

    @FXML
    private void handleAddScheduleButtonClick() {
        int groupId = Integer.parseInt(scheduleGroupIdField.getText());
        int disciplineId = Integer.parseInt(scheduleDisciplineIdField.getText());
        String date = scheduleDateField.getText();

        Schedule schedule = new Schedule(-1, groupId, disciplineId, date);

        studentService.addSchedule(schedule);
        updateScheduleTableView();

        scheduleGroupIdField.clear();
        scheduleDisciplineIdField.clear();
        scheduleDateField.clear();
    }

    @FXML
    private void handleRemoveScheduleButtonClick() {
        int scheduleId = Integer.parseInt(scheduleGroupIdField.getText());
        studentService.removeSchedule(scheduleId);
        updateScheduleTableView();

        scheduleGroupIdField.clear();
    }

    @FXML
    private void handleAddAttendanceButtonClick() {
        int studentId = Integer.parseInt(attendanceStudentIdField.getText());
        int scheduleId = Integer.parseInt(attendanceScheduleIdField.getText());
        boolean present = Boolean.parseBoolean(attendancePresentField.getText());

        Attendance attendance = new Attendance(-1, studentId, scheduleId, present);

        studentService.addAttendance(attendance);
        updateAttendanceTableView();

        attendanceStudentIdField.clear();
        attendanceScheduleIdField.clear();
        attendancePresentField.clear();
    }

    @FXML
    private void handleRemoveAttendanceButtonClick() {
        int attendanceId = Integer.parseInt(attendanceStudentIdField.getText());
        studentService.removeAttendance(attendanceId);
        updateAttendanceTableView();

        attendanceStudentIdField.clear();
    }

    @FXML
    private void handleAddPracticalWorkButtonClick() {
        int studentId = Integer.parseInt(practicalWorksStudentIdField.getText());
        int disciplineId = Integer.parseInt(practicalWorksDisciplineIdField.getText());
        String date = practicalWorksDateField.getText();
        int grade = Integer.parseInt(practicalWorksGradeField.getText());

        PracticalWork practicalWork = new PracticalWork(-1, studentId, disciplineId, date, grade);

        studentService.addPracticalWork(practicalWork);
        updatePracticalWorksTableView();

        practicalWorksStudentIdField.clear();
        practicalWorksDisciplineIdField.clear();
        practicalWorksDateField.clear();
        practicalWorksGradeField.clear();
    }

    @FXML
    private void handleRemovePracticalWorkButtonClick() {
        int practicalWorkId = Integer.parseInt(practicalWorksStudentIdField.getText());
        studentService.removePracticalWork(practicalWorkId);
        updatePracticalWorksTableView();

        practicalWorksStudentIdField.clear();
    }
}
