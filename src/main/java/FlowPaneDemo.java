import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

/**
 * File: FlowPaneDemo.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/27/26
 * Last modified: 3/27/26
 * Description: Textbook FlowPane Demo
 */

public class FlowPaneDemo extends Application {

    public void start(Stage primaryStage) {
        FlowPane pane = new FlowPane();
        pane.setPadding(new Insets(11, 12, 13, 14));
        pane.setHgap(5);
        pane.setVgap(5);

        pane.getChildren().addAll(new Label("First Name"), new TextField(),
                new Label("MI: "));

        TextField tfMi = new TextField();
        tfMi.setPrefColumnCount(1);

        pane.getChildren().addAll(tfMi, new Label("Last Name"), new TextField());

        Scene scene = new Scene(pane, 200, 250);
        primaryStage.setTitle("ShowFlowPane");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}