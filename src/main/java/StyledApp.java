/**
 * File: StyledAPp.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/23/26
 * Last modified: 3/23/26
 * Description: To be filled in by the user.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StyledApp extends Application {

    @Override
    public void start(Stage stage) {
        Button button = new Button("Click me");
        button.setId("myButton");

        VBox root = new VBox(button);
        root.setSpacing(20);
        root.setStyle("-fx-padding: 20;");

        Scene scene = new Scene(root, 300, 200);

        scene.getStylesheets().add(
                getClass().getResource("styles.css").toExternalForm()
        );

        stage.setScene(scene);
        stage.setTitle("JavaFX CSS Example");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}