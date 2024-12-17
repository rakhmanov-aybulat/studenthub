package org.studenthub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;


public class StudentHub extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Repo db = new Repo();
        db.executeSqlFile("baseline.sql");
        StudentService service = new StudentService(db);
        StudentHubController controller = new StudentHubController(service);

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

    public static void main(String[] args) {
        launch(args);
    }
}
