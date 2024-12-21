package org.studenthub;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
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
    private ChoiceBox<String> scheduleGroupNameChoiceBox;

    @FXML
    private ChoiceBox<String> scheduleDisciplineNameChoiceBox;

    @FXML
    private DatePicker scheduleDatePicker;

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
        updateDisciplinesTableView();
        updateScheduleTableView();

        updateAllGroupNameChoiceBoxes();
        updateAllDisciplineNameChoiceBoxes();
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

        groupNameColumn.setPrefWidth(150);
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

        TableColumn<Schedule, String> groupNameColumn = new TableColumn<>("Group Name");
        groupNameColumn.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        scheduleTableView.getColumns().add(groupNameColumn);

        TableColumn<Schedule, String> disciplineNameColumn = new TableColumn<>("Discipline Name");
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
        scheduleTableView.getColumns().add(disciplineNameColumn);

        TableColumn<Schedule, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        scheduleTableView.getColumns().add(dateColumn);

        scheduleIdColumn.setPrefWidth(110);
        scheduleIdColumn.setMinWidth(110);

        groupNameColumn.setPrefWidth(150);
        groupNameColumn.setMinWidth(150);

        disciplineNameColumn.setPrefWidth(220);
        disciplineNameColumn.setMinWidth(220);

        dateColumn.setPrefWidth(100);
        dateColumn.setMinWidth(100);
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
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {}

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
            scheduleGroupNameChoiceBox.getItems().add(group.getGroupName());
        });
    }

    private void updateAllDisciplineNameChoiceBoxes() {
        ObservableList<Discipline> disciplines = studentService.getDisciplinesObservableList();
        scheduleDisciplineNameChoiceBox.getItems().clear();
        disciplines.forEach(discipline -> {
            scheduleDisciplineNameChoiceBox.getItems().add(discipline.getDisciplineName());
        });
    }

    @FXML
    private void handleAddGroupButtonClick() {
        String groupName = groupsGroupNameField.getText().trim();
        Group group = new Group(-1, groupName);
        try {
            studentService.addGroup(group);
        } catch (EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {}

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
        String disciplineName = disciplinesDisciplineNameField.getText().trim();
        Discipline discipline = new Discipline(-1, disciplineName);

        try {
            studentService.addDiscipline(discipline);
        } catch (EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {}
        updateDisciplinesTableView();

        disciplinesDisciplineNameField.clear();
        updateAllDisciplineNameChoiceBoxes();
    }

    @FXML
    private void handleRemoveDisciplineButtonClick() throws SQLException {
        int disciplineId;
        try {
            disciplineId = Integer.parseInt(
                    disciplinesDisciplineIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Discipline ID must be integer");
            return;
        }
        try {
            studentService.removeDiscipline(disciplineId);
            updateDisciplinesTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        }
        disciplinesDisciplineIdField.clear();
    }

    @FXML
    private void handleAddScheduleButtonClick() throws SQLException {
        String groupName = scheduleGroupNameChoiceBox.getValue().trim();
        String disciplineName = scheduleDisciplineNameChoiceBox.getValue().trim();
        LocalDate date = scheduleDatePicker.getValue();
        Schedule schedule = new Schedule(-1, groupName, disciplineName, date);
        try {
            studentService.addSchedule(schedule);
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {}

        updateScheduleTableView();
        scheduleGroupNameChoiceBox.getSelectionModel().clearSelection();
        scheduleDisciplineNameChoiceBox.getSelectionModel().clearSelection();
        scheduleDatePicker.setValue(null);
    }

    @FXML
    private void handleRemoveScheduleButtonClick() throws SQLException {
        int scheduleId;
        try {
            scheduleId = Integer.parseInt(scheduleScheduleIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Schedule ID must be integer");
            return;
        }
        try {
            studentService.removeSchedule(scheduleId);
            updateScheduleTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        }
        scheduleScheduleIdField.clear();
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
