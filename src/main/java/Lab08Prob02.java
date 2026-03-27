import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


/**
 * File: Calculator.java
 * Class: CSCI 1302
 * Author: Brian Abbott, Krish Patel, Carson Buie
 * Created on: 3/27/26
 * Last modified: 3/27/26
 * Description: Simple Calculator GUI
 */

public class Lab08Prob02 extends Application {

    @Override
    public void start(Stage primaryStage) {
        // create text fields
        TextField operand1TextField = new TextField();
        TextField operand2TextField = new TextField();
        TextField answerTextField = new TextField();

        // create buttons
        Button additionButton = new Button("+");
        Button subtractionButton = new Button("-");
        Button multiplicationButton = new Button("*");
        Button divisionButton = new Button("/");

        // create gridpane and add buttons to it
        GridPane buttonsGridPane = new GridPane();
        buttonsGridPane.setAlignment(Pos.CENTER); // Set center alignment
        buttonsGridPane.add(additionButton, 0, 0);
        buttonsGridPane.add(subtractionButton, 1, 0);
        buttonsGridPane.add(multiplicationButton, 0, 1);
        buttonsGridPane.add(divisionButton,1, 1);

        // create a Hbox and add items to it
        HBox hbox = new HBox();
        hbox.getChildren().addAll(operand1TextField, buttonsGridPane, operand2TextField, answerTextField);

        // create scene and add it to the stage
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        // render title
        primaryStage.setTitle("Simple Calculator");
        // show stage
        primaryStage.show();
    }

}