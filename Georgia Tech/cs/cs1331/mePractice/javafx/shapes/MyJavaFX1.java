import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;
/*
getChildren() returns an instance of the JavaFX collection, observable list. An
observable list is much like an arraylist except that is used to store a collection
of elements. It's the equivalent of getting an arraylist and adding your nodes
to that list. You want to add the more complex items first and then down to least
complex.

Circle is not a custom object, it's a visual representation of a circle.
It's a JavaFX object. You must set the circle's radius center and the x center
and y center. Think of it as a two dimensional plane where you must set the
coordinates for x and y. The circle is stationary and if we wanted it to stay in
the center then we have to bind the properties. The stackPane will always stay in
the center because it will take the entire box.

You may notice that the colors are in caps. This is because the ways colors work
in JavaFX are based off CSS constants. You are working with CSS color and their
naming convention.

JavaFX allows us to use property binding to bind a target object to its source object.
Therefore, if the value of the source obkect changes then the target's object
value changes as well. The target object is called the binding object/property and
the source object is called the bindable object/observable object. 

*/
public class MyJavaFX1 extends Application {
    @Override
    public void start(Stage primaryStage) {
        //Create a StackPane Object
        StackPane sPane = new StackPane();
        sPane.getChildren().add(new Button("StackPane"));
        Scene scene = new Scene(sPane, 200, 50);
        primaryStage.setTitle("Stack Pane Demo");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Let's display a circle in a window.
        Stage stage2 = new Stage();
        Circle circle = new Circle(); //JavaFX Circle object.
        circle.setCenterX(100);//100 pixels in the x direction.
        circle.setCenterY(100);//Number of pixels
        circle.setRadius(50); //50 pixels in radius
        //Set the stroke and fill Color
        circle.setStroke(Color.LIGHTPINK);//Will change the line around the circle
        circle.setFill(Color.BLUE);
        Pane pane = new Pane();

        pane.getChildren().add(circle);
         //The reason why we set it to 200 is because we want our cirlce in the center....SIMPLE MATH.
        Scene scene2 = new Scene(pane, 200, 200);
        stage2.setTitle("Circle Window")
        stage2.setScene(scene2);
        stage2.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }

}
