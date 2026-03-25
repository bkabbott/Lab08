/**
 * File: T02_Panes.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 02 - Panes and Groups
 *
 * Panes are layout containers that position their children automatically
 * according to layout rules. Groups apply transforms and effects to a
 * collection of nodes without doing any layout themselves.
 *
 * To run: update mainClass in build.gradle to 'T02_Panes'
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class T02_Panes extends Application {

    @Override
    public void start(Stage stage) {

        // We will build a demo that shows several pane types inside a BorderPane.
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // -------------------------------------------------------------------
        // BorderPane
        // -------------------------------------------------------------------
        // BorderPane divides its space into five regions: top, bottom, left,
        // right, and center. Each region holds at most one node.
        // It is the most common root layout for application windows.
        //
        //        [ top    ]
        //  [left][center  ][right]
        //        [ bottom ]
        //
        // We are already using BorderPane as the root. Let's place labels
        // in its regions to demonstrate:
        Label topLabel    = new Label("BorderPane: TOP region");
        Label bottomLabel = new Label("BorderPane: BOTTOM region");
        Label leftLabel   = new Label("LEFT");
        Label rightLabel  = new Label("RIGHT");

        topLabel.setStyle("-fx-font-weight: bold;");
        bottomLabel.setStyle("-fx-font-style: italic;");

        root.setTop(topLabel);
        root.setBottom(bottomLabel);
        root.setLeft(leftLabel);
        root.setRight(rightLabel);
        BorderPane.setMargin(topLabel,    new Insets(0, 0, 5, 0));
        BorderPane.setMargin(bottomLabel, new Insets(5, 0, 0, 0));

        // -------------------------------------------------------------------
        // VBox / HBox
        // -------------------------------------------------------------------
        // VBox stacks children vertically; HBox arranges them horizontally.
        // Both accept a spacing argument in the constructor and support
        // alignment and padding.
        VBox vbox = new VBox(8);
        vbox.setAlignment(Pos.CENTER_LEFT);
        vbox.setPadding(new Insets(8));
        vbox.setStyle("-fx-border-color: #999; -fx-border-radius: 4;");
        vbox.getChildren().addAll(
            new Label("VBox row 1"),
            new Label("VBox row 2"),
            new Label("VBox row 3")
        );

        HBox hbox = new HBox(8);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(8));
        hbox.setStyle("-fx-border-color: #999; -fx-border-radius: 4;");
        hbox.getChildren().addAll(
            new Label("HBox col 1"),
            new Label("HBox col 2"),
            new Label("HBox col 3")
        );

        // -------------------------------------------------------------------
        // GridPane
        // -------------------------------------------------------------------
        // GridPane arranges children in a flexible grid of rows and columns.
        // Children are added with column and row indices (0-based).
        // Cells can span multiple rows or columns.
        GridPane grid = new GridPane();
        grid.setHgap(8);        // horizontal gap between columns
        grid.setVgap(8);        // vertical gap between rows
        grid.setPadding(new Insets(8));
        grid.setStyle("-fx-border-color: #999; -fx-border-radius: 4;");

        grid.add(new Label("Name:"),  0, 0);   // col 0, row 0
        grid.add(new Label("Age:"),   0, 1);
        grid.add(new Label("Brian"),  1, 0);   // col 1, row 0
        grid.add(new Label("38"),     1, 1);

        // -------------------------------------------------------------------
        // FlowPane
        // -------------------------------------------------------------------
        // FlowPane wraps children to a new row (or column) when the pane
        // runs out of space, like text wrapping. Useful for tag lists.
        FlowPane flow = new FlowPane(6, 6);
        flow.setPadding(new Insets(8));
        flow.setStyle("-fx-border-color: #999; -fx-border-radius: 4;");
        for (int i = 1; i <= 8; i++) {
            Label tag = new Label("Tag " + i);
            tag.setStyle("-fx-background-color: #dde; -fx-padding: 2 6 2 6; -fx-border-radius: 3; -fx-background-radius: 3;");
            flow.getChildren().add(tag);
        }

        // -------------------------------------------------------------------
        // TilePane
        // -------------------------------------------------------------------
        // TilePane is similar to FlowPane but forces all cells to the same
        // size. Good for icon grids or button banks.
        TilePane tiles = new TilePane(6, 6);
        tiles.setPadding(new Insets(8));
        tiles.setPrefColumns(3);
        tiles.setStyle("-fx-border-color: #999; -fx-border-radius: 4;");
        for (int i = 1; i <= 6; i++) {
            Label tile = new Label("Tile " + i);
            tile.setStyle("-fx-background-color: #eed; -fx-padding: 4 8 4 8;");
            tiles.getChildren().add(tile);
        }

        // -------------------------------------------------------------------
        // StackPane
        // -------------------------------------------------------------------
        // StackPane layers children on top of each other, all centered by
        // default. The last child added is drawn on top.
        StackPane stack = new StackPane();
        stack.setPadding(new Insets(8));
        Rectangle stackBg = new Rectangle(120, 60, Color.LIGHTBLUE);
        Label stackLabel  = new Label("StackPane");
        stack.getChildren().addAll(stackBg, stackLabel);

        // -------------------------------------------------------------------
        // Pane (absolute positioning)
        // -------------------------------------------------------------------
        // Plain Pane does no automatic layout. You position children manually
        // using node.setLayoutX() / setLayoutY(). Use when you need pixel-
        // precise control, such as for drawing canvases.
        Pane absolutePane = new Pane();
        absolutePane.setPrefSize(180, 80);
        absolutePane.setStyle("-fx-border-color: #999;");

        Label absoluteLabel = new Label("Absolute (60, 20)");
        absoluteLabel.setLayoutX(60);
        absoluteLabel.setLayoutY(20);
        absolutePane.getChildren().add(absoluteLabel);

        // -------------------------------------------------------------------
        // Group
        // -------------------------------------------------------------------
        // Group is not a pane. It does not lay out its children, nor does it
        // clip or resize them. Instead, it treats all children as a single
        // unit for transforms (translate, rotate, scale) and effects.
        // The group's bounds are the union of its children's bounds.
        //
        // Common use: grouping shapes so you can move or rotate them together.
        Circle c1 = new Circle(15, Color.TOMATO);
        Circle c2 = new Circle(15, Color.STEELBLUE);
        c2.setTranslateX(40);

        Group shapeGroup = new Group(c1, c2);
        shapeGroup.setTranslateX(20);
        shapeGroup.setTranslateY(25);

        Pane groupContainer = new Pane(shapeGroup);
        groupContainer.setPrefSize(120, 60);
        groupContainer.setStyle("-fx-border-color: #999;");

        // -------------------------------------------------------------------
        // Assemble the center area
        // -------------------------------------------------------------------
        VBox center = new VBox(10);
        center.setPadding(new Insets(8));
        center.getChildren().addAll(
            labeled("VBox",      vbox),
            labeled("HBox",      hbox),
            labeled("GridPane",  grid),
            labeled("FlowPane",  flow),
            labeled("TilePane",  tiles),
            labeled("StackPane", stack),
            labeled("Pane (abs)", absolutePane),
            labeled("Group",     groupContainer)
        );

        root.setCenter(center);

        stage.setTitle("Tutorial 02 - Panes and Groups");
        stage.setScene(new Scene(root, 520, 620));
        stage.show();
    }

    // Helper: wraps a node with a small header label so we can identify it.
    private VBox labeled(String title, javafx.scene.Node node) {
        Label header = new Label(title + ":");
        header.setStyle("-fx-font-size: 11; -fx-text-fill: #555;");
        VBox box = new VBox(2, header, node);
        return box;
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Pane Summary:
     *
     *   VBox        - vertical stack
     *   HBox        - horizontal row
     *   BorderPane  - top/bottom/left/right/center regions
     *   GridPane    - row/column grid, variable cell sizes
     *   FlowPane    - wrapping row or column
     *   TilePane    - wrapping grid, uniform cell sizes
     *   StackPane   - layered/overlapping children
     *   Pane        - no layout, absolute positioning
     *   Group       - not a layout; groups nodes for shared transforms
     *
     * Panes extend Region, which means they participate in layout and can
     * be styled with CSS. Group does not extend Region.
     */
}
