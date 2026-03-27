package lab8;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

//Name: Carson Buie, Krish Patel, Brian Abbott
//Date: 3/27/2026
//Class: CSCI 1302
//File: Lab08Prob01
//Desc: A JavaFX program that will eventually become a fuel efficiency calculator (think MPG calculator).

public class Lab08Prob01 extends Application {

	public void start(Stage primaryStage) {
		//Create root and scene
		GridPane gridpane = new GridPane();
	    Scene scene = new Scene(gridpane, 200, 100);
	    
	    //Add text
		Text miles = new Text("Miles:");
	    gridpane.add(miles, 0, 0); 
		Text gallons = new Text("Gallons:");
	    gridpane.add(gallons, 0, 1); 
		Text mpg = new Text("MPG:");
	    gridpane.add(mpg, 0, 2); 
	    
	    //Add text fields
	    TextField mileInput = new TextField();
	    gridpane.add(mileInput, 1, 0);
	    TextField gallonInput = new TextField();
	    gridpane.add(gallonInput, 1, 1);
	    TextField mpgOutput = new TextField();
	    gridpane.add(mpgOutput, 1, 2);
	    
	    //Add buttons
	    Button reset = new Button("Reset");
	    gridpane.add(reset, 0, 3);
	    Button calc = new Button("Calculate");
	    gridpane.add(calc, 1, 3);
	    
	    //Set stage
	    primaryStage.setTitle("MPG Calculator");
	    primaryStage.setScene(scene);
	    primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
