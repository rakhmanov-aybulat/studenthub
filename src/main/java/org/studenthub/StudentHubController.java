package org.studenthub;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
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
    private RadioButton attendancePresentRadioButton;

    @FXML
    private TextField attendanceAttendanceIdField;

    @FXML
    private TableView<PracticalWork> practicalWorksTableView;

    @FXML
    private TextField practicalWorksStudentIdField;

    @FXML
    private ChoiceBox<String> practicalWorksDisciplineNameChoiceBox;

    @FXML
    private DatePicker practicalWorksDatePicker;

    @FXML
    private ChoiceBox<String> practicalWorksGradeChoiceBox;

    @FXML
    private TextField practicalWorksPracticalWorkIdField;

    private final StudentService studentService;

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
        updateAttendanceTableView();
        updatePracticalWorksTableView();

        updateAllGroupNameChoiceBoxes();
        updateAllDisciplineNameChoiceBoxes();
        updateAllGradeChoiceBoxes();
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

        TableColumn<Attendance, String> studentFullNameColumn = new TableColumn<>("Student Full Name");
        studentFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentFullName"));
        attendanceTableView.getColumns().add(studentFullNameColumn);

        TableColumn<Attendance, String> disciplineNameColumn = new TableColumn<>("Discipline Name");
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
        attendanceTableView.getColumns().add(disciplineNameColumn);

        TableColumn<Attendance, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        attendanceTableView.getColumns().add(dateColumn);

        TableColumn<Attendance, Boolean> presentColumn = new TableColumn<>("Present");
        presentColumn.setCellValueFactory(new PropertyValueFactory<>("present"));
        attendanceTableView.getColumns().add(presentColumn);

        attendanceIdColumn.setPrefWidth(130);
        attendanceIdColumn.setMinWidth(130);

        studentFullNameColumn.setPrefWidth(180);
        studentFullNameColumn.setMinWidth(180);

        disciplineNameColumn.setPrefWidth(130);
        disciplineNameColumn.setMinWidth(130);

        dateColumn.setPrefWidth(90);
        dateColumn.setMinWidth(90);

        presentColumn.setPrefWidth(80);
        presentColumn.setMinWidth(80);
    }

    private void setupPracticalWorksTableView() {
        TableColumn<PracticalWork, Integer> practicalWorkIdColumn = new TableColumn<>("Practical Work ID");
        practicalWorkIdColumn.setCellValueFactory(new PropertyValueFactory<>("practicalWorkId"));
        practicalWorksTableView.getColumns().add(practicalWorkIdColumn);

        TableColumn<PracticalWork, String> studentFullNameColumn = new TableColumn<>("Student Full Name");
        studentFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentFullName"));
        practicalWorksTableView.getColumns().add(studentFullNameColumn);

        TableColumn<PracticalWork, String> disciplineNameColumn = new TableColumn<>("Discipline Name");
        disciplineNameColumn.setCellValueFactory(new PropertyValueFactory<>("disciplineName"));
        practicalWorksTableView.getColumns().add(disciplineNameColumn);

        TableColumn<PracticalWork, LocalDate> dateColumn = new TableColumn<>("Date");
        dateColumn.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        practicalWorksTableView.getColumns().add(dateColumn);

        TableColumn<PracticalWork, Integer> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));
        practicalWorksTableView.getColumns().add(gradeColumn);

        practicalWorkIdColumn.setPrefWidth(150);
        practicalWorkIdColumn.setMinWidth(150);

        studentFullNameColumn.setPrefWidth(180);
        studentFullNameColumn.setMinWidth(180);

        disciplineNameColumn.setPrefWidth(130);
        disciplineNameColumn.setMinWidth(130);

        dateColumn.setPrefWidth(90);
        dateColumn.setMinWidth(90);

        gradeColumn.setPrefWidth(60);
        gradeColumn.setMinWidth(60);
    }

    private void updateStudentsTableView() {
        ObservableList<Student> students = studentService.getStudentObservableList();
        studentsTableView.setItems(students);
    }

    private void updateGroupsTableView() {
        ObservableList<Group> groups = studentService.getGroupsObservableList();
        groupsTableView.setItems(groups);
    }

    private void updateDisciplinesTableView() {
        ObservableList<Discipline> disciplines = studentService.getDisciplinesObservableList();
        disciplinesTableView.setItems(disciplines);
    }

    private void updateScheduleTableView() {
        ObservableList<Schedule> schedules = studentService.getSchedulesObservableList();
        scheduleTableView.setItems(schedules);
    }

    private void updateAttendanceTableView() {
        ObservableList<Attendance> attendances = studentService.getAttendancesObservableList();
        attendanceTableView.setItems(attendances);
    }

    private void updatePracticalWorksTableView() {
        ObservableList<PracticalWork> practicalWorks = studentService.getPracticalWorksObservableList();
        practicalWorksTableView.setItems(practicalWorks);
    }

    @FXML
    private void handleAddStudentButtonClick() {
        String fullName = studentsFullNameField.getText().trim();
        if (fullName.isBlank()) {
            showErrorAlert("Full Name must be filled out.");
            return;
        }
        String groupName = studentsGroupNameChoiceBox.getValue();
        if (groupName == null) {
            showErrorAlert("You can’t leave Group Name field blank.");
            return;
        }
        try {
            studentService.addStudent(fullName, groupName);
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
        }

        updateStudentsTableView();
        studentsFullNameField.clear();
        studentsGroupNameChoiceBox.getSelectionModel().clearSelection();
    }

    @FXML
    private void handleRemoveStudentButtonClick() {
        int studentId;
        try {
            studentId = Integer.parseInt(
                    studentsStudentIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Student ID must be integer");
            return;
        }
        try {
            studentService.removeStudent(studentId);
            updateStudentsTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
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

        try {
            studentService.importStudentsFromFile(selectedFile);
        } catch (ImportInvalidLineFormatException |
                 ImportGroupNotFoundException e) {
            showErrorAlert(e.getMessage() +
                    "\n\nPlease correct the errors and try again.");
        } catch (FileNotFoundException e) {
            showErrorAlert("File not found with name: " +
                    selectedFile.getName());
        } catch (Exception e) {
            showErrorAlert("Something went wrong, please try again");
        } finally {
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
        studentsGroupNameChoiceBox.getItems().clear();
        scheduleGroupNameChoiceBox.getItems().clear();
        ObservableList<Group> groups = studentService.getGroupsObservableList();
        groups.forEach(group -> {
            studentsGroupNameChoiceBox.getItems().add(group.getGroupName());
            scheduleGroupNameChoiceBox.getItems().add(group.getGroupName());
        });
    }

    private void updateAllDisciplineNameChoiceBoxes() {
        scheduleDisciplineNameChoiceBox.getItems().clear();
        practicalWorksDisciplineNameChoiceBox.getItems().clear();
        ObservableList<Discipline> disciplines = studentService.getDisciplinesObservableList();
        disciplines.forEach(discipline -> {
            scheduleDisciplineNameChoiceBox.getItems().add(discipline.getDisciplineName());
            practicalWorksDisciplineNameChoiceBox.getItems().add(discipline.getDisciplineName());
        });
    }

    private void updateAllGradeChoiceBoxes() {
        practicalWorksGradeChoiceBox.getItems().clear();
        ObservableList<Integer> grades = FXCollections.observableArrayList(1, 2, 3, 4, 5);
        grades.forEach(grade -> {
            practicalWorksGradeChoiceBox.getItems().add(grade.toString());
        });
    }

    @FXML
    private void handleAddGroupButtonClick() {
        String groupName = groupsGroupNameField.getText().trim();
        if (groupName.isBlank()) {
            showErrorAlert("Group Name must be filled out.");
            return;
        }
        try {
            studentService.addGroup(groupName);
        } catch (EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {
            showErrorAlert("Something went wrong.");
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
            updateAllGroupNameChoiceBoxes();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
        }
        groupsGroupIdField.clear();
    }

    @FXML
    private void handleAddDisciplineButtonClick() {
        String disciplineName = disciplinesDisciplineNameField.getText().trim();
        if (disciplineName.isBlank()) {
            showErrorAlert("Discipline Name must be filled out.");
            return;
        }
        try {
            studentService.addDiscipline(disciplineName);
        } catch (EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong");
        }
        updateDisciplinesTableView();
        disciplinesDisciplineNameField.clear();
        updateAllDisciplineNameChoiceBoxes();
    }

    @FXML
    private void handleRemoveDisciplineButtonClick() {
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
            updateAllDisciplineNameChoiceBoxes();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong");
        }
        disciplinesDisciplineIdField.clear();
    }

    @FXML
    private void handleAddScheduleButtonClick() {
        String groupName = scheduleGroupNameChoiceBox.getValue();
        if (groupName == null) {
            showErrorAlert("You can’t leave Group Name field blank.");
            return;
        }
        String disciplineName = scheduleDisciplineNameChoiceBox.getValue();
        if (disciplineName == null) {
            showErrorAlert("You can’t leave Discipline Name field blank.");
            return;
        }
        LocalDate date = scheduleDatePicker.getValue();
        if (date == null) {
            showErrorAlert("Invalid Date format.");
            return;
        }
        try {
            studentService.addSchedule(groupName, disciplineName, date);
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException ignored) {}

        updateScheduleTableView();
        scheduleGroupNameChoiceBox.getSelectionModel().clearSelection();
        scheduleDisciplineNameChoiceBox.getSelectionModel().clearSelection();
        scheduleDatePicker.setValue(null);
    }

    @FXML
    private void handleRemoveScheduleButtonClick() {
        int scheduleId;
        try {
            scheduleId = Integer.parseInt(
                    scheduleScheduleIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Schedule ID must be integer");
            return;
        }
        try {
            studentService.removeSchedule(scheduleId);
            updateScheduleTableView();
        } catch (EntityDeletionException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
        }
        scheduleScheduleIdField.clear();
    }

    @FXML
    private void handleAddAttendanceButtonClick() {
        boolean present = attendancePresentRadioButton.isSelected();
        int studentId;
        try {
            studentId = Integer.parseInt(
                    attendanceStudentIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Student ID must be integer");
            return;
        }
        int scheduleId;
        try {
            scheduleId = Integer.parseInt(
                    attendanceScheduleIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Schedule ID must be integer");
            return;
        }

        try {
            studentService.addAttendance(studentId, scheduleId, present);
            updateAttendanceTableView();
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong");
        }

        attendanceStudentIdField.clear();
        attendanceScheduleIdField.clear();
        attendancePresentRadioButton.setSelected(false);
    }

    @FXML
    private void handleRemoveAttendanceButtonClick() {
        int attendanceId;
        try {
            attendanceId = Integer.parseInt(
                    attendanceAttendanceIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Attendance ID must be integer");
            return;
        }
        try {
            studentService.removeAttendance(attendanceId);
        } catch (SQLException ignored) {}
        updateAttendanceTableView();
        attendanceAttendanceIdField.clear();
    }

    @FXML
    private void handleAddPracticalWorkButtonClick() {
        int studentId;
        try {
            studentId = Integer.parseInt(
                    practicalWorksStudentIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Student ID must be integer.");
            return;
        }
        String disciplineName = practicalWorksDisciplineNameChoiceBox.getValue();
        if (disciplineName == null) {
            showErrorAlert("You can’t leave Discipline Name field blank.");
            return;
        }
        LocalDate date = practicalWorksDatePicker.getValue();
        if (date == null) {
            showErrorAlert("Invalid Date format.");
            return;
        }
        int grade;
        try {
            String gradeString = practicalWorksGradeChoiceBox.getValue();
            if (gradeString == null) {
                showErrorAlert("You can’t leave Grade field blank.");
                return;
            }
            grade = Integer.parseInt(gradeString);
            if (grade < 1 | grade > 5) {
                showErrorAlert("Grade must be between 1 and 5.");
                return;
            }
        } catch (NumberFormatException e) {
            showErrorAlert("Student ID must be integer.");
            return;
        }
        try {
            studentService.addPracticalWork(studentId, disciplineName, date, grade);
        } catch (EntityNotFoundException | EntityAlreadyExistsException e) {
            showErrorAlert(e.getMessage());
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
        }
        updatePracticalWorksTableView();
        practicalWorksStudentIdField.clear();
        practicalWorksGradeChoiceBox.getSelectionModel().clearSelection();
        practicalWorksDisciplineNameChoiceBox.getSelectionModel().clearSelection();
        practicalWorksDatePicker.setValue(null);
    }

    @FXML
    private void handleRemovePracticalWorkButtonClick() {
        int practicalWorkId;
        try {
            practicalWorkId = Integer.parseInt(
                    practicalWorksPracticalWorkIdField.getText().trim());
        } catch (NumberFormatException e) {
            showErrorAlert("Practical Work ID must be an integer");
            return;
        }
        try {
            studentService.removePracticalWork(practicalWorkId);
            updatePracticalWorksTableView();
        } catch (SQLException e) {
            showErrorAlert("Something went wrong.");
        }
        practicalWorksPracticalWorkIdField.clear();
    }
}
