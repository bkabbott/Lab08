/**
 * File: HelloJavaFx.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/23/26
 * Last modified: 3/23/26
 * Description: To be filled in by the user.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MyJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button btnOk = new Button("OK");
        Scene scene = new Scene(btnOk, 200, 250);
        primaryStage.setTitle("MyJavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {

            Application.launch(args);

    }

}