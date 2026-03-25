/**
 * File: T06_Styling.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 06 - CSS Styling in JavaFX
 *
 * JavaFX supports a subset of CSS 2.1 with its own extensions, all prefixed
 * with "-fx-". There are three places you can apply styles:
 *
 *   1. Default stylesheet  - Modena (shipped with JavaFX, always active)
 *   2. External stylesheet - a .css file linked to the Scene or a node
 *   3. Inline style        - node.setStyle("...") on an individual node
 *
 * Specificity works the same as in web CSS:
 *   inline > #id > .class > type selector > default
 *
 * This tutorial demonstrates all three approaches and covers the most
 * commonly used JavaFX CSS properties.
 *
 * To run: update mainClass in build.gradle to 'T06_Styling'
 *
 * The companion stylesheet is:
 *   src/main/resources/styles/tutorial.css
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T06_Styling extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setId("root-container");

        // ---------------------------------------------------------------
        // A. Inline Styles via setStyle()
        // ---------------------------------------------------------------
        // setStyle() accepts a string of CSS property:value pairs.
        // These are JavaFX CSS properties -- they always start with -fx-.
        // The string is the *body* of a CSS rule, not a full rule.

        Label inlineLabel = new Label("Inline styled label");
        inlineLabel.setStyle(
            "-fx-font-family: 'Segoe UI', Arial, sans-serif;" +
            "-fx-font-size: 15px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #1a1a2e;" +
            "-fx-background-color: #e8f4fd;" +
            "-fx-padding: 8 12 8 12;" +
            "-fx-border-color: #2196f3;" +
            "-fx-border-width: 0 0 0 4;" +   // left border only
            "-fx-border-radius: 2;" +
            "-fx-background-radius: 2;"
        );

        // Shapes have slightly different CSS properties.
        Rectangle inlineRect = new Rectangle(200, 40);
        inlineRect.setStyle(
            "-fx-fill: linear-gradient(to right, #667eea, #764ba2);" +
            "-fx-arc-width: 10;" +
            "-fx-arc-height: 10;" +
            "-fx-stroke: #4a3f7e;" +
            "-fx-stroke-width: 2;"
        );

        Button inlineBtn = new Button("Inline styled button");
        inlineBtn.setStyle(
            "-fx-background-color: #4caf50;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 13px;" +
            "-fx-font-weight: bold;" +
            "-fx-padding: 8 20 8 20;" +
            "-fx-background-radius: 20;" +  // pill shape
            "-fx-cursor: hand;"
        );
        // Hover effect via mouse events (inline CSS cannot use :hover)
        inlineBtn.setOnMouseEntered(e ->
            inlineBtn.setStyle(
                "-fx-background-color: #388e3c;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 20 8 20;" +
                "-fx-background-radius: 20;" +
                "-fx-cursor: hand;"
            )
        );
        inlineBtn.setOnMouseExited(e ->
            inlineBtn.setStyle(
                "-fx-background-color: #4caf50;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 13px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 8 20 8 20;" +
                "-fx-background-radius: 20;" +
                "-fx-cursor: hand;"
            )
        );

        VBox inlineSection = new VBox(10,
            sectionHeader("A. Inline setStyle()"),
            inlineLabel,
            inlineRect,
            inlineBtn
        );

        // ---------------------------------------------------------------
        // B. Style Classes
        // ---------------------------------------------------------------
        // Defining style classes in Java and styling them in CSS is cleaner
        // than duplicating inline strings. Any node can have multiple classes.
        //
        // getStyleClass().add("name")    - add a class
        // getStyleClass().remove("name") - remove a class
        // getStyleClass().setAll(...)    - replace all classes

        Label primaryTag = new Label("primary");
        primaryTag.getStyleClass().addAll("tag", "tag-primary");

        Label successTag = new Label("success");
        successTag.getStyleClass().addAll("tag", "tag-success");

        Label warningTag = new Label("warning");
        warningTag.getStyleClass().addAll("tag", "tag-warning");

        Label dangerTag  = new Label("danger");
        dangerTag.getStyleClass().addAll("tag", "tag-danger");

        // These four buttons use classes defined in tutorial.css
        Button primaryBtn = new Button("Primary Action");
        primaryBtn.getStyleClass().addAll("btn", "btn-primary");

        Button secondaryBtn = new Button("Secondary");
        secondaryBtn.getStyleClass().addAll("btn", "btn-secondary");

        Button outlineBtn = new Button("Outline");
        outlineBtn.getStyleClass().addAll("btn", "btn-outline");

        HBox tagRow = new HBox(8, primaryTag, successTag, warningTag, dangerTag);
        HBox btnRow = new HBox(8, primaryBtn, secondaryBtn, outlineBtn);

        VBox classSection = new VBox(10,
            sectionHeader("B. Style Classes (see tutorial.css)"),
            tagRow,
            btnRow
        );

        // ---------------------------------------------------------------
        // C. ID Selector
        // ---------------------------------------------------------------
        // setId() targets a unique node. CSS #id selectors have higher
        // specificity than class selectors. Use sparingly.

        Label idLabel = new Label("I am styled by #styled-title in tutorial.css");
        idLabel.setId("styled-title");

        VBox idSection = new VBox(10,
            sectionHeader("C. ID Selector (#styled-title)"),
            idLabel
        );

        // ---------------------------------------------------------------
        // D. Pseudo-Classes
        // ---------------------------------------------------------------
        // Pseudo-classes represent states and are defined in the stylesheet,
        // not in Java. Common ones:
        //
        //   :hover    - mouse is over the node
        //   :focused  - node has keyboard focus
        //   :pressed  - node is being clicked
        //   :disabled - node is disabled
        //   :selected - selection state (ListView, CheckBox, etc.)
        //
        // In an external stylesheet you can write:
        //   .btn-primary:hover { -fx-background-color: #1565c0; }
        //
        // You cannot use pseudo-classes with setStyle() (inline only applies
        // to normal state). This is the main reason to prefer external CSS.

        Button pseudoBtn = new Button("Hover / Click me (see tutorial.css)");
        pseudoBtn.getStyleClass().add("pseudo-demo");

        TextField focusField = new TextField();
        focusField.setPromptText("Click here to see :focused state");
        focusField.getStyleClass().add("pseudo-demo");

        VBox pseudoSection = new VBox(10,
            sectionHeader("D. Pseudo-Classes (:hover, :focused, :pressed)"),
            pseudoBtn,
            focusField
        );

        // ---------------------------------------------------------------
        // E. Important CSS Properties Reference
        // ---------------------------------------------------------------
        // The section below demonstrates key -fx- properties visually.
        // Each label shows what it looks like as a result of inline CSS.

        // Text properties
        Label fontDemo = new Label("Font family, size, weight, style");
        fontDemo.setStyle("-fx-font-family: Georgia, serif; -fx-font-size: 14; -fx-font-weight: bold; -fx-font-style: italic;");

        Label textFillDemo = new Label("Text fill (color)");
        textFillDemo.setStyle("-fx-text-fill: #e91e63; -fx-font-size: 14;");

        Label underlineDemo = new Label("Underline text");
        underlineDemo.setStyle("-fx-underline: true; -fx-font-size: 14;");

        // Background properties
        Label solidBg = new Label("Solid background");
        solidBg.setStyle("-fx-background-color: #ffd54f; -fx-padding: 4 10; -fx-background-radius: 3;");

        Label gradientBg = new Label("Gradient background");
        gradientBg.setStyle("-fx-background-color: linear-gradient(to bottom, #43a047, #1b5e20); -fx-text-fill: white; -fx-padding: 4 10; -fx-background-radius: 3;");

        Label radialGradient = new Label("Radial gradient");
        radialGradient.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 60%, #ff8f00, #e65100); -fx-text-fill: white; -fx-padding: 4 20; -fx-background-radius: 20;");

        // Border properties
        Label borderDemo = new Label("Border properties");
        borderDemo.setStyle(
            "-fx-border-color: #9c27b0;" +
            "-fx-border-width: 2;" +
            "-fx-border-style: dashed;" +
            "-fx-border-radius: 6;" +
            "-fx-padding: 4 10;" +
            "-fx-background-radius: 6;"
        );

        Label shadowDemo = new Label("Drop shadow via effect (not CSS)");
        shadowDemo.setEffect(new javafx.scene.effect.DropShadow(6, Color.GRAY));
        shadowDemo.setStyle("-fx-padding: 4 10;");

        VBox propsSection = new VBox(10,
            sectionHeader("E. Key CSS Properties"),
            fontDemo, textFillDemo, underlineDemo,
            solidBg, gradientBg, radialGradient,
            borderDemo, shadowDemo
        );

        // ---------------------------------------------------------------
        // F. Load External Stylesheet
        // ---------------------------------------------------------------
        // A stylesheet is linked to a Scene or a Region via its CSS URL.
        // The URL must point to the classpath resource location.
        //
        // Two common approaches:
        //
        //   1. getClass().getResource("/path/to/file.css").toExternalForm()
        //      - resolves relative to the classpath root
        //
        //   2. getClass().getResource("adjacent.css").toExternalForm()
        //      - resolves relative to the class's package directory
        //
        // Gradle puts resources from src/main/resources/ on the classpath.
        // So "src/main/resources/styles/tutorial.css" is reachable at
        // "/styles/tutorial.css".

        String cssPath = T06_Styling.class
            .getResource("/styles/tutorial.css")
            .toExternalForm();

        root.getChildren().addAll(
            inlineSection,
            divider(),
            classSection,
            divider(),
            idSection,
            divider(),
            pseudoSection,
            divider(),
            propsSection
        );

        Scene scene = new Scene(root, 580, 900);

        // Add the external stylesheet to the Scene. All nodes in this scene
        // can now be targeted by rules in tutorial.css.
        scene.getStylesheets().add(cssPath);

        // You can also add a stylesheet to a single subtree rooted at a Region:
        //   someRegion.getStylesheets().add(cssPath);
        // Only descendants of that region will be affected.

        stage.setTitle("Tutorial 06 - CSS Styling");
        stage.setScene(scene);
        stage.show();
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private Label sectionHeader(String text) {
        Label label = new Label(text);
        label.setStyle(
            "-fx-font-size: 13;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #37474f;" +
            "-fx-border-color: transparent transparent #90a4ae transparent;" +
            "-fx-border-width: 0 0 1 0;" +
            "-fx-padding: 0 0 4 0;"
        );
        label.setMaxWidth(Double.MAX_VALUE);
        return label;
    }

    private javafx.scene.shape.Line divider() {
        javafx.scene.shape.Line line = new javafx.scene.shape.Line(0, 0, 540, 0);
        line.setStyle("-fx-stroke: #e0e0e0;");
        return line;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * CSS Quick Reference - JavaFX Properties:
     *
     * TEXT
     *   -fx-font-family         font name or family
     *   -fx-font-size           size in px or em
     *   -fx-font-weight         normal | bold
     *   -fx-font-style          normal | italic
     *   -fx-text-fill           color value
     *   -fx-underline           true | false
     *   -fx-text-alignment      left | center | right
     *
     * BACKGROUND
     *   -fx-background-color    color, gradient, or comma-separated layers
     *   -fx-background-radius   corner radius per layer
     *   -fx-background-insets   inset from edge per layer
     *
     * BORDER
     *   -fx-border-color        color (top right bottom left)
     *   -fx-border-width        width
     *   -fx-border-style        solid | dashed | dotted | none
     *   -fx-border-radius       corner radius
     *   -fx-border-insets       inset from edge
     *
     * BOX MODEL
     *   -fx-padding             insets inside the border
     *
     * SHAPE (javafx.scene.shape.*)
     *   -fx-fill                fill paint
     *   -fx-stroke              stroke paint
     *   -fx-stroke-width        width
     *   -fx-arc-width/height    corner rounding for Rectangle
     *
     * EFFECTS (use Java API instead -- CSS effect support is limited)
     *   node.setEffect(new DropShadow(...))
     *   node.setEffect(new Glow(...))
     *   node.setEffect(new GaussianBlur(...))
     *
     * SELECTORS
     *   TypeName       .Button, .Label
     *   .class         .my-class
     *   #id            #my-id
     *   :pseudo-class  :hover, :focused, :pressed, :disabled, :selected
     *   descendant     .parent .child
     *   direct child   .parent > .child
     */
}
