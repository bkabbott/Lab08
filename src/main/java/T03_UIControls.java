/**
 * File: T03_UIControls.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 03 - UI Controls and Shapes
 *
 * JavaFX ships with a full set of ready-to-use UI controls and a 2D shape API.
 * Controls are interactive widgets. Shapes are geometric primitives that you
 * can position, fill, stroke, and transform.
 *
 * To run: update mainClass in build.gradle to 'T03_UIControls'
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class T03_UIControls extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(16);
        root.setPadding(new Insets(16));

        // ---------------------------------------------------------------
        // SECTION 1: Common Controls
        // ---------------------------------------------------------------
        // Label - displays static or dynamic text. Not interactive.
        Label staticLabel = new Label("I am a Label.");

        // Button - triggers an action on click via setOnAction().
        Button clickBtn = new Button("Click Me");
        Label clickResult = new Label("(not clicked yet)");
        clickBtn.setOnAction(e -> clickResult.setText("Button was clicked."));

        // TextField - single-line text input.
        // getText() retrieves the current value.
        Label tfLabel = new Label("TextField:");
        TextField textField = new TextField();
        textField.setPromptText("Type something here");

        Button readTf = new Button("Read TextField");
        Label tfOutput = new Label();
        readTf.setOnAction(e -> tfOutput.setText("You typed: " + textField.getText()));

        // PasswordField - TextField that masks input.
        Label pwLabel   = new Label("PasswordField:");
        PasswordField pwField = new PasswordField();
        pwField.setPromptText("Enter password");

        // TextArea - multi-line text input.
        Label taLabel = new Label("TextArea:");
        TextArea textArea = new TextArea("Multi-line\ntext here.");
        textArea.setPrefRowCount(3);

        // CheckBox - boolean toggle. isSelected() returns current state.
        CheckBox checkBox = new CheckBox("I agree to the terms");
        checkBox.setOnAction(e ->
            System.out.println("Checked: " + checkBox.isSelected())
        );

        // RadioButton - mutually exclusive selection within a ToggleGroup.
        // Only one RadioButton in the group can be selected at a time.
        ToggleGroup sizeGroup = new ToggleGroup();
        RadioButton rbSmall  = new RadioButton("Small");
        RadioButton rbMedium = new RadioButton("Medium");
        RadioButton rbLarge  = new RadioButton("Large");
        rbSmall.setToggleGroup(sizeGroup);
        rbMedium.setToggleGroup(sizeGroup);
        rbLarge.setToggleGroup(sizeGroup);
        rbMedium.setSelected(true);

        HBox radioRow = new HBox(12, rbSmall, rbMedium, rbLarge);
        radioRow.setAlignment(Pos.CENTER_LEFT);

        // ComboBox - drop-down selector. getValue() returns the selection.
        ComboBox<String> combo = new ComboBox<>();
        combo.getItems().addAll("Option A", "Option B", "Option C");
        combo.setValue("Option A");
        combo.setOnAction(e ->
            System.out.println("Selected: " + combo.getValue())
        );

        // Slider - numeric range input. getValue() returns current position.
        Slider slider = new Slider(0, 100, 50);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        Label sliderReadout = new Label("Value: 50.0");
        slider.valueProperty().addListener((obs, oldVal, newVal) ->
            sliderReadout.setText(String.format("Value: %.1f", newVal.doubleValue()))
        );

        // Assemble controls into a grid for clean alignment.
        GridPane controls = new GridPane();
        controls.setHgap(10);
        controls.setVgap(8);
        controls.setPadding(new Insets(8));
        controls.setStyle("-fx-border-color: #ccc; -fx-border-radius: 4; -fx-background-color: #f9f9f9; -fx-background-radius: 4;");

        int row = 0;
        controls.add(new Label("Label:"),        0, row); controls.add(staticLabel,     1, row++);
        controls.add(new Label("Button:"),       0, row); controls.add(new HBox(8, clickBtn, clickResult), 1, row++);
        controls.add(tfLabel,                    0, row); controls.add(new HBox(8, textField, readTf, tfOutput), 1, row++);
        controls.add(pwLabel,                    0, row); controls.add(pwField,          1, row++);
        controls.add(taLabel,                    0, row); controls.add(textArea,         1, row++);
        controls.add(new Label("CheckBox:"),     0, row); controls.add(checkBox,         1, row++);
        controls.add(new Label("RadioButton:"),  0, row); controls.add(radioRow,         1, row++);
        controls.add(new Label("ComboBox:"),     0, row); controls.add(combo,            1, row++);
        controls.add(new Label("Slider:"),       0, row); controls.add(new VBox(2, slider, sliderReadout), 1, row++);

        // ---------------------------------------------------------------
        // SECTION 2: Shapes
        // ---------------------------------------------------------------
        // Shapes live in the javafx.scene.shape package.
        // All shapes have fill (interior) and stroke (outline) properties.
        // fill and stroke accept any Paint, including Color or gradients.

        // Line - defined by start and end points.
        Line line = new Line(0, 0, 150, 0);
        line.setStroke(Color.DARKSLATEGRAY);
        line.setStrokeWidth(3);
        line.setStrokeLineCap(StrokeLineCap.ROUND);

        // Rectangle - defined by x, y, width, height.
        // arcWidth/arcHeight round the corners.
        Rectangle rect = new Rectangle(120, 40);
        rect.setFill(Color.STEELBLUE);
        rect.setStroke(Color.NAVY);
        rect.setStrokeWidth(2);
        rect.setArcWidth(10);
        rect.setArcHeight(10);

        // Circle - defined by centerX, centerY, radius.
        // In a Pane the center coords are absolute; in a Group/HBox the
        // bounding box is positioned instead.
        Circle circle = new Circle(30);
        circle.setFill(Color.TOMATO);
        circle.setStroke(Color.DARKRED);
        circle.setStrokeWidth(2);

        // Ellipse - like Circle but with separate radiusX and radiusY.
        Ellipse ellipse = new Ellipse(60, 25);
        ellipse.setFill(Color.MEDIUMSEAGREEN);
        ellipse.setStroke(Color.DARKGREEN);
        ellipse.setStrokeWidth(1.5);

        // Polygon - defined by an array of (x, y) coordinate pairs.
        // Points are listed as x0, y0, x1, y1, ...
        Polygon triangle = new Polygon(
            0.0, 50.0,
            50.0, 0.0,
            100.0, 50.0
        );
        triangle.setFill(Color.GOLDENROD);
        triangle.setStroke(Color.SADDLEBROWN);
        triangle.setStrokeWidth(2);

        HBox shapes = new HBox(20);
        shapes.setAlignment(Pos.CENTER_LEFT);
        shapes.setPadding(new Insets(12));
        shapes.setStyle("-fx-border-color: #ccc; -fx-border-radius: 4; -fx-background-color: #f9f9f9; -fx-background-radius: 4;");
        shapes.getChildren().addAll(line, rect, circle, ellipse, triangle);

        // ---------------------------------------------------------------
        // Assemble root
        // ---------------------------------------------------------------
        Label controlsHeader = new Label("UI Controls");
        controlsHeader.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        Label shapesHeader = new Label("Shapes");
        shapesHeader.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        root.getChildren().addAll(controlsHeader, controls, shapesHeader, shapes);

        stage.setTitle("Tutorial 03 - UI Controls and Shapes");
        stage.setScene(new Scene(root, 620, 580));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Controls Quick Reference:
     *   Label          - display text, no interaction
     *   Button         - click to fire an ActionEvent
     *   TextField      - single-line text input
     *   PasswordField  - masked single-line input
     *   TextArea       - multi-line text input
     *   CheckBox       - boolean on/off
     *   RadioButton    - one-of-many (needs a ToggleGroup)
     *   ComboBox<T>    - drop-down list
     *   Slider         - numeric range input
     *
     * Shapes Quick Reference:
     *   Line       - two endpoints, no fill
     *   Rectangle  - width/height, optional rounded corners
     *   Circle     - radius, fills from center
     *   Ellipse    - radiusX + radiusY
     *   Polygon    - arbitrary closed shape from point list
     *   Arc        - partial ellipse (not shown here)
     *   Path       - general path from PathElement commands
     */
}
