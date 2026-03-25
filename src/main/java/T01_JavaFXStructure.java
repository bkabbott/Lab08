/**
 * File: T01_JavaFXStructure.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 01 - Basic Structure of a JavaFX Application
 *
 * JavaFX programs are built around three key objects: Application, Stage, and Scene.
 * Understanding how these relate to each other is the foundation for everything else.
 *
 * To run this file, update the mainClass in build.gradle:
 *     mainClass = 'T01_JavaFXStructure'
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * Every JavaFX program extends Application. This base class manages
 * the JavaFX runtime lifecycle, including initialization and shutdown.
 */
public class T01_JavaFXStructure extends Application {

    /*
     * The start() method is the entry point for the JavaFX UI. It receives a
     * Stage, which represents the main application window. You can think of a
     * Stage as the window frame and the Scene as what is displayed inside it.
     *
     * The "scene graph" is the tree of nodes (controls, shapes, layouts) that
     * make up the UI. Every scene has exactly one root node. All other nodes
     * descend from that root.
     */
    @Override
    public void start(Stage primaryStage) {

        // --- Building the Scene Graph ---

        // Nodes are the building blocks of a JavaFX UI.
        // Label is a simple node that displays read-only text.
        Label titleLabel = new Label("JavaFX Application Structure");
        Label descLabel  = new Label("Stage  ->  Scene  ->  Root Node  ->  Child Nodes");
        Label noteLabel  = new Label("This window is the Stage. The Scene fills it.");

        // VBox is a layout pane that stacks children vertically.
        // It serves as the root node of our scene graph.
        VBox root = new VBox(10); // 10px spacing between children
        root.setPadding(new Insets(20));
        root.getChildren().addAll(titleLabel, descLabel, noteLabel);

        // --- Creating the Scene ---

        // A Scene wraps the root node and defines the viewport size (width x height).
        // The scene fills the Stage's content area.
        Scene scene = new Scene(root, 480, 200);

        // --- Configuring the Stage ---

        // The Stage is the OS-level window. Set a title and assign the scene.
        primaryStage.setTitle("Tutorial 01 - JavaFX Structure");
        primaryStage.setScene(scene);

        // You can control window size behavior:
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(150);
        primaryStage.setResizable(true);

        // show() makes the Stage visible. Nothing appears before this call.
        primaryStage.show();

        // --- Lifecycle Notes ---
        // JavaFX calls these overridable methods in order:
        //   1. init()   - runs before start(); safe for non-UI setup
        //   2. start()  - builds and shows the UI (required)
        //   3. stop()   - called when the application closes; clean up resources here
        //
        // You don't have to override init() or stop() unless you need them.
    }

    /*
     * main() is the standard Java entry point. The call to launch() hands control
     * to the JavaFX runtime, which then calls init() and start() on the JavaFX
     * Application Thread.
     *
     * Do not construct Stage or Scene objects anywhere except the JavaFX
     * Application Thread (i.e., inside start() or methods it calls directly).
     */
    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Key Takeaways:
     *   - Extend Application and override start(Stage stage).
     *   - Build a tree of nodes (the scene graph) with a single root.
     *   - Wrap the root in a Scene, attach the Scene to the Stage, call show().
     *   - launch() in main() starts the runtime.
     *   - Every UI update must happen on the JavaFX Application Thread.
     */
}
