/**
 * File: T05_NodeProperties.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 05 - Common Node Properties and Methods
 *
 * Every node in the scene graph inherits from javafx.scene.Node. That base
 * class provides properties and methods that work on all nodes regardless of
 * type: position, size, visibility, rotation, scale, opacity, and more.
 *
 * To run: update mainClass in build.gradle to 'T05_NodeProperties'
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class T05_NodeProperties extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(16));

        // ---------------------------------------------------------------
        // 1. Visibility and Managed
        // ---------------------------------------------------------------
        // setVisible(false)  - hides the node but keeps its layout space.
        // setManaged(false)  - removes the node from layout calculations entirely.
        // setOpacity(0.0)    - fully transparent but still occupies space.
        //
        // Use setVisible(false) when you want to show/hide while keeping
        // the layout stable. Pair with setManaged(false) to reclaim the space.

        Label visibleLabel   = new Label("I am visible");
        Label invisibleLabel = new Label("I am invisible (but take up space)");
        Label unmanagedLabel = new Label("I am unmanaged (no layout space)");
        Label opacityLabel   = new Label("I have 30% opacity");

        invisibleLabel.setVisible(false);

        unmanagedLabel.setVisible(false);
        unmanagedLabel.setManaged(false);

        opacityLabel.setOpacity(0.3);

        Button toggleVisible = new Button("Toggle invisible label");
        toggleVisible.setOnAction(e ->
            invisibleLabel.setVisible(!invisibleLabel.isVisible())
        );

        VBox visDemo = new VBox(6, visibleLabel, invisibleLabel, unmanagedLabel, opacityLabel, toggleVisible);
        visDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 2. Disable
        // ---------------------------------------------------------------
        // setDisable(true) marks a node and all its descendants as disabled.
        // Disabled nodes do not receive user input events and render grayed out.

        Button enabledBtn  = new Button("I am enabled");
        Button disabledBtn = new Button("I am disabled");
        disabledBtn.setDisable(true);

        HBox disableDemo = new HBox(10, enabledBtn, disabledBtn);
        disableDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 3. Translation (position offset)
        // ---------------------------------------------------------------
        // setTranslateX / setTranslateY shift a node from its layout position.
        // This is different from setLayoutX/setLayoutY. Layout properties are
        // managed by the parent pane; translate properties are a post-layout
        // offset you apply yourself.

        Rectangle baseRect   = new Rectangle(60, 30, Color.LIGHTGRAY);
        Rectangle shiftedRect = new Rectangle(60, 30, Color.STEELBLUE);
        shiftedRect.setTranslateX(30);
        shiftedRect.setTranslateY(15);

        // Both rects share the same layout position; the blue one is translated.
        Pane translatePane = new Pane(baseRect, shiftedRect);
        translatePane.setPrefSize(200, 80);
        translatePane.setStyle("-fx-border-color: #ccc;");

        // ---------------------------------------------------------------
        // 4. Rotation
        // ---------------------------------------------------------------
        // setRotate(degrees) rotates around the node's center by default.
        // The pivot can be changed via setRotationAxis() or by using the
        // Rotate transform with explicit pivot coordinates.

        Rectangle rotateRect = new Rectangle(80, 30, Color.TOMATO);
        rotateRect.setLayoutX(60);
        rotateRect.setLayoutY(40);
        rotateRect.setRotate(30);  // 30 degrees clockwise

        Slider rotateSlider = new Slider(-180, 180, 30);
        rotateRect.rotateProperty().bind(rotateSlider.valueProperty());
        Label rotateLabel = new Label();
        rotateLabel.textProperty().bind(
            rotateSlider.valueProperty().asString("Rotation: %.1f deg")
        );

        Pane rotatePane = new Pane(rotateRect);
        rotatePane.setPrefSize(200, 100);
        rotatePane.setStyle("-fx-border-color: #ccc;");

        VBox rotateDemo = new VBox(6, rotatePane, rotateSlider, rotateLabel);
        rotateDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 5. Scale
        // ---------------------------------------------------------------
        // setScaleX / setScaleY scale the node around its center.
        // Values > 1 enlarge; values < 1 shrink; negative values mirror.

        Rectangle scaleRect = new Rectangle(60, 40, Color.MEDIUMSEAGREEN);
        scaleRect.setLayoutX(70);
        scaleRect.setLayoutY(30);

        Slider scaleSlider = new Slider(0.1, 3.0, 1.0);
        scaleRect.scaleXProperty().bind(scaleSlider.valueProperty());
        scaleRect.scaleYProperty().bind(scaleSlider.valueProperty());
        Label scaleLabel = new Label();
        scaleLabel.textProperty().bind(
            scaleSlider.valueProperty().asString("Scale: %.2f")
        );

        Pane scalePane = new Pane(scaleRect);
        scalePane.setPrefSize(200, 100);
        scalePane.setStyle("-fx-border-color: #ccc;");

        VBox scaleDemo = new VBox(6, scalePane, scaleSlider, scaleLabel);
        scaleDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 6. Transforms List
        // ---------------------------------------------------------------
        // For compound or pivot-aware transforms, use node.getTransforms().
        // Transforms are applied in order: last added is applied first.
        //
        //   Translate  - moves the node
        //   Rotate     - rotates around a pivot
        //   Scale      - scales around a pivot
        //   Shear      - skews the node

        Rectangle transformRect = new Rectangle(80, 30, Color.GOLDENROD);
        Rotate rotateTx   = new Rotate(45, 40, 15);  // 45deg, pivot at (40,15)
        Translate moveTx  = new Translate(40, 40);
        transformRect.getTransforms().addAll(moveTx, rotateTx);

        Pane transformPane = new Pane(transformRect);
        transformPane.setPrefSize(200, 120);
        transformPane.setStyle("-fx-border-color: #ccc;");

        // ---------------------------------------------------------------
        // 7. Inline CSS style on Node
        // ---------------------------------------------------------------
        // setStyle() applies CSS directly to a single node. The string is
        // the body of a CSS rule -- property:value pairs separated by semicolons.
        // This is the quickest way to style a node without a stylesheet.
        //
        // See T06_Styling.java for the full CSS tutorial.

        Label styledLabel = new Label("Styled with setStyle()");
        styledLabel.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: white;" +
            "-fx-background-color: #2e6da4;" +
            "-fx-padding: 8 16 8 16;" +
            "-fx-background-radius: 5;"
        );

        // ---------------------------------------------------------------
        // 8. Style Classes and IDs (for external CSS)
        // ---------------------------------------------------------------
        // Every node can have:
        //   - One ID:       node.setId("myId")       -> CSS selector #myId
        //   - Many classes: node.getStyleClass().add("myClass") -> .myClass
        //
        // Classes are the recommended approach. IDs are useful for unique nodes.

        Label classLabel = new Label("I have style class 'highlight-box'");
        classLabel.getStyleClass().add("highlight-box");

        Label idLabel = new Label("I have id 'unique-node'");
        idLabel.setId("unique-node");

        // ---------------------------------------------------------------
        // 9. Cursor and Tooltip
        // ---------------------------------------------------------------
        // setCursor() changes the mouse pointer over the node.
        // Tooltip shows a hover tip (not shown here but easy to add).

        Button cursorBtn = new Button("Hover me for crosshair cursor");
        cursorBtn.setCursor(javafx.scene.Cursor.CROSSHAIR);

        // ---------------------------------------------------------------
        // Assemble
        // ---------------------------------------------------------------
        Label header = new Label("Node Properties and Methods");
        header.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

        GridPane propGrid = new GridPane();
        propGrid.setHgap(10);
        propGrid.setVgap(12);

        int r = 0;
        propGrid.add(new Label("Visibility / Opacity:"), 0, r); propGrid.add(visDemo,        1, r++);
        propGrid.add(new Label("Disable:"),              0, r); propGrid.add(disableDemo,     1, r++);
        propGrid.add(new Label("TranslateX/Y:"),         0, r); propGrid.add(translatePane,   1, r++);
        propGrid.add(new Label("Rotate:"),               0, r); propGrid.add(rotateDemo,      1, r++);
        propGrid.add(new Label("Scale:"),                0, r); propGrid.add(scaleDemo,       1, r++);
        propGrid.add(new Label("Transforms list:"),      0, r); propGrid.add(transformPane,   1, r++);
        propGrid.add(new Label("setStyle():"),           0, r); propGrid.add(styledLabel,     1, r++);
        propGrid.add(new Label("Style class / ID:"),     0, r);
        propGrid.add(new VBox(4, classLabel, idLabel),   1, r++);
        propGrid.add(new Label("setCursor():"),          0, r); propGrid.add(cursorBtn,        1, r++);

        root.getChildren().addAll(header, propGrid);

        stage.setTitle("Tutorial 05 - Node Properties");
        stage.setScene(new Scene(root, 600, 780));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Node Methods Quick Reference:
     *
     *   setVisible(boolean)       - show/hide, preserves layout space
     *   setManaged(boolean)       - include/exclude from layout
     *   setOpacity(0.0 - 1.0)    - transparency
     *   setDisable(boolean)       - disable input on node and children
     *   setTranslateX/Y/Z(double) - post-layout offset
     *   setLayoutX/Y(double)      - layout position (set by parent panes)
     *   setRotate(double)         - degrees clockwise around center
     *   setScaleX/Y/Z(double)     - scale factor around center
     *   setStyle(String)          - inline CSS
     *   setId(String)             - CSS #id selector
     *   getStyleClass()           - list of CSS class selectors
     *   setCursor(Cursor)         - mouse pointer style
     *   getTransforms()           - list of explicit Transform objects
     *   toFront() / toBack()      - Z-order within parent
     *   snapshot(...)             - render node to a WritableImage
     *   lookup(cssSelector)       - find descendant node by CSS selector
     */
}
