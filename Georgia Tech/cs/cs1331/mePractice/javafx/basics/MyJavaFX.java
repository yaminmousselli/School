/*
The button will always be in the center of the applcaition.
You can change this by simply setting the position and size of the button
(the properties of the button). However, it's much better to use container
classes called panes, for automatically laying out the nodes in a desired
location.

You place the nodes into a pane and the pane into the scene.
A node refers to a visual component such as a shape, pane, image view, or
a ui control. ImageView is for displaying an image. Panes include a flowpane,
gridpane, stackpane, Hbox, Vbox, or borderpane. A UI control refers to a
label, button, checkbox, textfield, or a text area.
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class MyJavaFX extends Application {
    //You start your program with this method.
    public void start(Stage primaryStage) {
        Button okayButton = new Button("OK");

        Scene scene = new Scene(okayButton, 200, 300); //Width then Height
        primaryStage.setTitle("MyJavaFX");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Cerating a second stage which is a window.
        Stage stage = new Stage();
        stage.setTitle("Second Stage");
        stage.setScene(new Scene(new Button("New Stage"), 500, 600));
        stage.show(); //This is how you display your applcation to your user.
    }

    public static void main(String[] args) {
        Application.launch(args);
        /* A static method defined in the Application class
        for launching a stand alone application.
        The main method isn't really needed when running
        the application from the command line but is
        needed for some IDE's. When you run an FX application without main,
        The JVM is smart enough to know to invoke the main method if it isn't there.
        */
    }
}
