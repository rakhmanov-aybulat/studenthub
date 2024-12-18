package org.studenthub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;


public class StudentHub extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Repo db = new Repo();
        db.executeSqlFile("baseline.sql");
        StudentService service = new StudentService(db);
        StudentHubController controller = new StudentHubController(service);

        showLoadTestDataDialog(db);

        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("scene.fxml");
        loader.setLocation(xmlUrl);
        loader.setController(controller);
        Parent root = loader.load();

        stage.setTitle("StudentHub");
        stage.setMinWidth(900);
        stage.setMinHeight(650);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showLoadTestDataDialog(Repo db) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Load Test Data");
        alert.setHeaderText("Load Test Data into Database");
        alert.setContentText("Do you want to load test data into the database?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            db.executeSqlFile("test_data.sql");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
