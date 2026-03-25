/**
 * File: T04_PropertyBinding.java
 * Class: CSCI 1302
 * Author: Brian Abbott
 * Created on: 3/25/26
 * Description: Tutorial 04 - Property Binding
 *
 * JavaFX properties are observable wrappers around primitive values. They
 * support automatic synchronization (binding) between values, which removes
 * the need to manually propagate changes through your UI.
 *
 * To run: update mainClass in build.gradle to 'T04_PropertyBinding'
 */

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.binding.StringExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class T04_PropertyBinding extends Application {

    @Override
    public void start(Stage stage) {

        VBox root = new VBox(20);
        root.setPadding(new Insets(16));

        // ---------------------------------------------------------------
        // 1. What is a Property?
        // ---------------------------------------------------------------
        // A JavaFX Property is an observable, writable object that holds a
        // single typed value. The main concrete implementations are in the
        // javafx.beans.property package:
        //
        //   SimpleIntegerProperty, SimpleDoubleProperty,
        //   SimpleStringProperty, SimpleBooleanProperty, etc.
        //
        // You interact with them using:
        //   .get()     - read current value
        //   .set(v)    - write a new value
        //   .getValue() / .setValue(v) - boxed version (same as get/set)
        //   .addListener(...) - react when the value changes

        IntegerProperty counter = new SimpleIntegerProperty(0);
        System.out.println("Initial counter: " + counter.get());
        counter.set(5);
        System.out.println("After set(5): " + counter.get());

        // ---------------------------------------------------------------
        // 2. ChangeListener
        // ---------------------------------------------------------------
        // A ChangeListener fires every time the property's value changes.
        // The lambda receives the observable, the old value, and the new value.

        StringProperty nameProperty = new SimpleStringProperty("Alice");
        nameProperty.addListener((observable, oldValue, newValue) ->
            System.out.println("Name changed: " + oldValue + " -> " + newValue)
        );
        nameProperty.set("Bob");   // prints: Name changed: Alice -> Bob

        // ---------------------------------------------------------------
        // 3. Unidirectional Binding  (A binds to B)
        // ---------------------------------------------------------------
        // When you call a.bind(b), property 'a' automatically tracks 'b'.
        // Whenever 'b' changes, 'a' updates. You cannot call a.set() while
        // a binding is active -- you must call a.unbind() first.

        DoubleProperty source = new SimpleDoubleProperty(10.0);
        DoubleProperty copy   = new SimpleDoubleProperty();
        copy.bind(source);

        System.out.println("copy before: " + copy.get());   // 10.0
        source.set(42.0);
        System.out.println("copy after:  " + copy.get());   // 42.0
        // copy.set(1.0); would throw RuntimeException -- binding is active

        copy.unbind();
        copy.set(999.0); // now allowed

        // ---------------------------------------------------------------
        // 4. Bidirectional Binding  (A <-> B)
        // ---------------------------------------------------------------
        // bindBidirectional() keeps both properties in sync in both directions.
        // Either can be set at any time.

        DoubleProperty valueA = new SimpleDoubleProperty(1.0);
        DoubleProperty valueB = new SimpleDoubleProperty(1.0);
        valueA.bindBidirectional(valueB);

        valueA.set(7.0);
        System.out.println("B after A->7: " + valueB.get()); // 7.0
        valueB.set(99.0);
        System.out.println("A after B->99: " + valueA.get()); // 99.0

        // ---------------------------------------------------------------
        // 5. Expression / Computed Binding
        // ---------------------------------------------------------------
        // Properties expose arithmetic and string methods that return new
        // bindings representing derived values. This is the core of
        // declarative UI in JavaFX.

        DoubleProperty width  = new SimpleDoubleProperty(100);
        DoubleProperty height = new SimpleDoubleProperty(50);

        // Computed binding: area = width * height
        DoubleBinding area = width.multiply(height);
        System.out.println("Area: " + area.get()); // 5000.0
        width.set(200);
        System.out.println("Area after width->200: " + area.get()); // 10000.0

        // ---------------------------------------------------------------
        // 6. Binding to UI: Slider -> Circle radius example
        // ---------------------------------------------------------------
        // This is the most common use case: a control's value property is
        // bound to a node's visual property so the UI updates live.

        Slider radiusSlider = new Slider(10, 100, 40);
        radiusSlider.setShowTickLabels(true);
        radiusSlider.setShowTickMarks(true);
        radiusSlider.setMajorTickUnit(30);

        Circle circle = new Circle();
        circle.setFill(Color.STEELBLUE);
        circle.setStroke(Color.NAVY);
        circle.setStrokeWidth(2);

        // Bind circle's radius directly to the slider's value.
        // When the slider moves, the circle resizes instantly.
        circle.radiusProperty().bind(radiusSlider.valueProperty());

        Label radiusReadout = new Label();
        radiusReadout.textProperty().bind(
            Bindings.format("Radius: %.1f", radiusSlider.valueProperty())
        );

        HBox circleBox = new HBox(20);
        circleBox.setAlignment(Pos.CENTER_LEFT);
        circleBox.getChildren().add(circle);

        VBox sliderDemo = new VBox(6,
            new Label("Drag the slider to resize the circle via binding:"),
            radiusSlider,
            radiusReadout,
            circleBox
        );
        sliderDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 7. Binding to UI: TextField <-> Label (bidirectional text)
        // ---------------------------------------------------------------
        // textProperty() on TextField is a StringProperty. Binding it to a
        // label's textProperty means the label mirrors the field live.

        TextField inputField = new TextField("Type here...");
        Label mirrorLabel = new Label();

        // Unidirectional: label mirrors the field.
        mirrorLabel.textProperty().bind(inputField.textProperty());

        VBox textDemo = new VBox(6,
            new Label("Label mirrors TextField via property binding:"),
            inputField,
            new HBox(6, new Label("Mirror:"), mirrorLabel)
        );
        textDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // 8. Bindings.format() and Bindings.when()
        // ---------------------------------------------------------------
        // The Bindings utility class provides factory methods for common
        // expression patterns without building a full custom binding.

        DoubleProperty score = new SimpleDoubleProperty(75.0);

        // Bindings.format works like String.format but returns an observable.
        StringExpression scoreText =
            Bindings.format("Score: %.0f%%", score);

        // Bindings.when creates a conditional (ternary) binding.
        StringExpression grade =
            Bindings.when(score.greaterThanOrEqualTo(90))
                    .then("A")
                    .otherwise(
                Bindings.when(score.greaterThanOrEqualTo(80))
                        .then("B")
                        .otherwise(
                    Bindings.when(score.greaterThanOrEqualTo(70))
                            .then("C")
                            .otherwise("F")
                )
            );

        Slider scoreSlider = new Slider(0, 100, 75);
        scoreSlider.setShowTickLabels(true);
        scoreSlider.setMajorTickUnit(25);
        score.bind(scoreSlider.valueProperty());

        Label scoreDisplay = new Label();
        scoreDisplay.textProperty().bind(scoreText);
        Label gradeDisplay = new Label();
        gradeDisplay.textProperty().bind(grade);

        GridPane scoreGrid = new GridPane();
        scoreGrid.setHgap(10);
        scoreGrid.setVgap(4);
        scoreGrid.add(new Label("Score:"), 0, 0);
        scoreGrid.add(scoreDisplay, 1, 0);
        scoreGrid.add(new Label("Grade:"), 0, 1);
        scoreGrid.add(gradeDisplay, 1, 1);

        VBox conditionalDemo = new VBox(6,
            new Label("Bindings.format() and Bindings.when():"),
            scoreSlider,
            scoreGrid
        );
        conditionalDemo.setStyle("-fx-border-color: #ccc; -fx-padding: 10; -fx-border-radius: 4;");

        // ---------------------------------------------------------------
        // Assemble
        // ---------------------------------------------------------------
        Label header = new Label("Property Binding");
        header.setStyle("-fx-font-size: 15; -fx-font-weight: bold;");

        root.getChildren().addAll(header, sliderDemo, textDemo, conditionalDemo);

        stage.setTitle("Tutorial 04 - Property Binding");
        stage.setScene(new Scene(root, 540, 600));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*
     * Key Takeaways:
     *
     *   property.bind(other)              - one-way, property tracks 'other'
     *   property.bindBidirectional(other) - two-way sync
     *   property.unbind()                 - break a unidirectional binding
     *   property.addListener(...)         - react to changes manually
     *
     *   Arithmetic bindings:  .add()  .subtract()  .multiply()  .divide()
     *   String bindings:      .concat()  Bindings.format()
     *   Boolean bindings:     .greaterThan()  .lessThan()  .isEqualTo()
     *   Conditional:          Bindings.when(cond).then(a).otherwise(b)
     *
     *   Node properties commonly bound in practice:
     *     textProperty(), visibleProperty(), disableProperty(),
     *     radiusProperty(), widthProperty(), translateXProperty(), etc.
     */
}
