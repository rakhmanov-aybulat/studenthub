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
import org.studenthub.model.Student;

public class StudentHubController implements Initializable {
    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TextField studentsFullNameField;

    @FXML
    private TextField studentsGroupIdField;

    @FXML
    private TextField studentsStudentIdField;

    private StudentService studentService;

    public StudentHubController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initStudentsTableView();
        updateStudentsTableView();
    }

    private void initStudentsTableView() {
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

    private void updateStudentsTableView() {
        ObservableList<Student> students = studentService.getStudentObservableList();
        studentsTableView.setItems(students);
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
}
