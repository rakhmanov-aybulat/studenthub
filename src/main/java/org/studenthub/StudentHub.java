package org.studenthub;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class StudentHub extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        stage.setMinWidth(900);
        stage.setMinHeight(650);

        Parent root = FXMLLoader.load(
                getClass().getResource("scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add(
                getClass().getResource("styles.css").toExternalForm());
        
        stage.setTitle("StudentHub");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
