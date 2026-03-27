import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class Test extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        HBox pane = new HBox(5);
        Circle circle = new Circle(50, 200, 200);
        pane.getChildren().addAll(circle);

        circle.setCenterX(100);
        circle.setCenterY(100);
        circle.setRadius(50);
        pane.getChildren().addAll(circle);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane);
        primaryStage.setTitle("Test"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
}