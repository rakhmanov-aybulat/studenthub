package org.studenthub;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.studenthub.exceptions.*;
import org.studenthub.model.*;

public class StudentHubController implements Initializable {
    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TextField studentsFullNameField;

    @FXML
    private ChoiceBox<String> studentsGroupNameChoiceBox;

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
        setupGroupsTableView();
        setupDisciplinesTableView();
        setupScheduleTableView();
        setupAttendanceTableView();
        setupPracticalWorksTableView();

        updateStudentsTableView();
        updateGroupsTableView();

        updateAllGroupNameChoiceBoxes();
    }

    private void setupStudentsTableView() {
        TableColumn<Student, Integer> studentIdColumn = new TableColumn<>("Student ID");
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentsTableView.getColumns().add(studentIdColumn);

        TableColumn<Student, String> fullNameColumn = new TableColumn<>("Full Name");
        fullNameColumn.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        studentsTableView.getColumns().add(fullNameColumn);

        TableColumn<Student, String> groupNameColumn = new TableColumn<>("Group Name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        studentsTableView.getColumns().add(groupNameColumn);

        studentIdColumn.setPrefWidth(100);
        studentIdColumn.setMinWidth(100);

        fullNameColumn.setPrefWidth(300);
        fullNameColumn.setMinWidth(300);

        groupNameColumn.prefWidthProperty().bind(
                studentsTableView.widthProperty()
                .subtract(studentIdColumn.widthProperty())
                .subtract(fullNameColumn.widthProperty())
        );
        groupNameColumn.setMinWidth(150);
    }

    private void setupGroupsTableView() {
        TableColumn<Group, Integer> groupIdColumn = new TableColumn<>("Group ID");
        groupIdColumn.setCellValueFactory(new PropertyValueFactory<>("groupId"));
        groupsTableView.getColumns().add(groupIdColumn);

        TableColumn<Group, String> groupNameColumn = new TableColumn<>("Group Name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        groupsTableView.getColumns().add(groupNameColumn);

        groupIdColumn.setPrefWidth(100);
        groupIdColumn.setMinWidth(100);

        groupNameColumn.setPrefWidth(150);
        groupNameColumn.setMinWidth(150);
    }

    private void setupDisciplinesTableView() {
        TableColumn<Discipline, Integer> disciplineIdColumn = new TableColumn<>("Discipline ID");
        disciplineIdColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineId"));
        disciplinesTableView.getColumns().add(disciplineIdColumn);

        TableColumn<Discipline, String> disciplineNameColumn = new TableColumn<>("Discipline Name");
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
        disciplinesTableView.getColumns().add(disciplineNameColumn);

        disciplineIdColumn.setPrefWidth(120);
        disciplineIdColumn.setMinWidth(120);

        disciplineNameColumn.setPrefWidth(250);
        disciplineNameColumn.setMinWidth(250);

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
        String fullName = studentsFullNameField.getText().trim();
        String groupName = studentsGroupNameChoiceBox.getValue().trim();
        Student student = new Student(-1, fullName, groupName);
        try {
            studentService.addStudent(student);
        } catch (GroupNotFoundException | StudentAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateStudentsTableView();
        studentsFullNameField.clear();
        studentsGroupNameChoiceBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRemoveStudentButtonClick() {
        int studentId;
        try {
            studentId = Integer.parseInt(studentsStudentIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Student ID must be integer");
            return;
        }
        try {
            studentService.removeStudent(studentId);
            updateStudentsTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        }
        studentsStudentIdField.clear();
    }

    @FXML
    private void handleHelpButtonClick() {
        Alert helpDialog = new Alert(Alert.AlertType.INFORMATION);
        helpDialog.setTitle("Help");
        helpDialog.setHeaderText("To import the student list, follow these steps");
        helpDialog.setContentText(
                "1. Click the 'Import Student List' button.\n" +
                        "2. Select a CSV file containing the list of students.\n\n" +
                        "The file must meet the following requirements:\n" +
                        "   - Each line in the file represents a student.\n" +
                        "   - Each line should be formatted as follows: 'Full Name,Group Name'.\n" +
                        "   - The full name and group name should be separated by a comma.\n" +
                        "   - The full name should be the first field, and the group name should be the second field.\n\n" +
                        "Example:\n" +
                        "   John Doe,Group A\n" +
                        "   Jane Smith,Group B\n\n" +
                        "Ensure that the file is correctly formatted before importing."
        );
        helpDialog.showAndWait();
    }

    @FXML
    private void handleImportStudentListButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Student List File");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile == null) {
            return;
        }

        String errorMessage = null;
        try {
            studentService.importStudentsFromFile(selectedFile);
        } catch (ImportInvalidLineFormatException | ImportGroupNotFoundException e) {
            errorMessage = e.getMessage() +
                    "\n\nPlease correct the errors and try again.";
        } catch (FileNotFoundException e) {
            errorMessage = "File not found with name: " + selectedFile.getName();
        } catch (Exception e) {
            errorMessage = "Something went wrong, please try again";
        } finally {
            if (errorMessage != null)
                showErrorAlert(errorMessage);
            updateStudentsTableView();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateAllGroupNameChoiceBoxes() {
        ObservableList<Group> groups = studentService.getGroupsObservableList();
        studentsGroupNameChoiceBox.getItems().clear();
        groups.forEach(group -> {
            studentsGroupNameChoiceBox.getItems().add(group.getGroupName());
        });
    }

    @FXML
    private void handleAddGroupButtonClick() {
        String groupName = groupsGroupNameField.getText().trim();
        Group group = new Group(-1, groupName);
        try {
            studentService.addGroup(group);
        } catch (GroupAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        updateGroupsTableView();
        groupsGroupNameField.clear();
        updateAllGroupNameChoiceBoxes();
    }

    @FXML
    private void handleRemoveGroupButtonClick() {
        int groupId;
        try {
            groupId = Integer.parseInt(groupsGroupIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Group ID must be integer");
            return;
        }
        try {
            studentService.removeGroup(groupId);
            updateGroupsTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        }
        groupsGroupIdField.clear();
    }


    @FXML
    private void handleAddDisciplineButtonClick() throws SQLException {
        String disciplineName = disciplinesDisciplineNameField.getText();
        Discipline discipline = new Discipline(-1, disciplineName);

        studentService.addDiscipline(discipline);
        updateDisciplinesTableView();

        disciplinesDisciplineNameField.clear();
    }

    @FXML
    private void handleRemoveDisciplineButtonClick() throws SQLException {
        int disciplineId = Integer.parseInt(disciplinesDisciplineIdField.getText());
        studentService.removeDiscipline(disciplineId);
        updateDisciplinesTableView();

        disciplinesDisciplineIdField.clear();
    }

    @FXML
    private void handleAddScheduleButtonClick() throws SQLException {
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
    private void handleRemoveScheduleButtonClick() throws SQLException {
        int scheduleId = Integer.parseInt(scheduleGroupIdField.getText());
        studentService.removeSchedule(scheduleId);
        updateScheduleTableView();

        scheduleGroupIdField.clear();
    }

    @FXML
    private void handleAddAttendanceButtonClick() throws SQLException {
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
    private void handleRemoveAttendanceButtonClick() throws SQLException {
        int attendanceId = Integer.parseInt(attendanceStudentIdField.getText());
        studentService.removeAttendance(attendanceId);
        updateAttendanceTableView();

        attendanceStudentIdField.clear();
    }

    @FXML
    private void handleAddPracticalWorkButtonClick() throws SQLException {
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
    private void handleRemovePracticalWorkButtonClick() throws SQLException {
        int practicalWorkId = Integer.parseInt(practicalWorksStudentIdField.getText());
        studentService.removePracticalWork(practicalWorkId);
        updatePracticalWorksTableView();

        practicalWorksStudentIdField.clear();
    }

}
