/**
    For a stackpane, everything goes in the center of the pane and stays in the
center. A flowpane arranges the nodes in the pane either horizontally, from left
to right or vertically from top to bottom in the order in which they were added.
You determine the preference by specifying Orientation.HORIZONTAL or Orientation.VERTICAL
You can also specify the gap betwwen the nodes in the pixels by Hgap or Vgap.

There are four data fields in the scene class that all have their respective getter
methods. The scene class is an immutable class. The alignment data field, by
default, it is set to the left. The orientation data field, by default, is horizontal.
The hgap and vgap binding properties are used to tie something so there will always
be a gap in between the nodes when you insert something new.

Every binding property in JavaFX has its getter method, setter method,
and the property getter method.

GridPane doesn't have a default orientation, you just specify where to add something
at. You have to provide the orientation for a flowpane. For a gridpane, you have
a boolean property, gridlines visible data field. There are four data fields
alignment, girdlines visbile, hgap and vgap (double properties). GridPane reads
vertical then horizontal.

A BorderPane has five regions. It has the top, bottom, center, right, and left.
These are your data fields and by default, they are null. These data fields are
binding properties. Always work clockwise like Java does, so Top, Right, Left,
and then Bottom.

The Hbox lays out its children noded in a single horizontal row. The Vbox does it
in vertically. Both Hbox and Vbox and organizes into 1 row/column respectively.
A flowpane can lay out its children in muliple rows or columns but a Hbox or Vbox
can lay outs children in one row respectively.

Remember, Padding and spacing are two different ideas. Padding means the space
around every side of the image. Spacing/Margins is the space between two images.
Margins would be used to create


*/
import javafx.application.Application;
import javafx.geometry.Insets;//This will let us have spaces
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.geometry.HPos;//horizontal alignment methods for GridPane
import javafx.geometry.Pos;//vertical alignment

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ShowPanes extends Application {
    public void start(Stage primaryStage) {
//////////////////////////////FlowPane//////////////////////////////////////////
        FlowPane fp = new FlowPane();
        //Set padding now, it reads top, right, bottom, left (clockwise)
        //For uniform padding, specify one number for all
        fp.setPadding(new Insets(11, 12, 13, 14));
        fp.setVgap(5);
        fp.setHgap(5);

        fp.getChildren().addAll(new Label("First Name: "), new TextField(),
            new Label("MI: "));
        TextField tf = new TextField();
        tf.setPrefColumnCount(1);
        fp.getChildren().addAll(tf, new Label("Last Name: "), new TextField());

        Scene scene = new Scene(fp, 200, 250);
        primaryStage.setTitle("FlowPane");
        primaryStage.setScene(scene);
        primaryStage.show();

////////////////////////////////GridPane////////////////////////////////////////
        GridPane gridPane = new GridPane();
        Stage gridStage = new Stage();
        gridPane.setAlignment(Pos.CENTER); //This aligns everything to the center
            //so the center of the grid and window match when you resize the window.

        gridPane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        gridPane.setHgap(5.5);
        gridPane.setVgap(5.5);

        gridPane.add(new Label("First Name: "), 0, 0); //Must specify where the label is
        gridPane.add(new TextField(), 1, 0);
        gridPane.add(new Label("MI: "), 0, 1);
        gridPane.add(new TextField(), 1, 1);
        gridPane.add(new Label("Last Name: "), 0, 2);
        gridPane.add(new TextField(), 1, 2);
        Button button = new Button("Add Name");
        gridPane.add(button, 1, 3);
        gridPane.setHalignment(button, HPos.RIGHT);

        Scene scene2 = new Scene(gridPane, 200, 250);
        gridStage.setTitle("GridPane");
        gridStage.setScene(scene2);
        gridStage.show();
/////////////////////////////BorderPane////////////////////////////////////////
        Stage bPStage = new Stage();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(new CustomPane("Top"));
        borderPane.setRight(new CustomPane("Right"));
        borderPane.setBottom(new CustomPane("Bottom"));
        borderPane.setLeft(new CustomPane("Left"));
        borderPane.setCenter(new CustomPane("Center"));

        Scene scene3 = new Scene(borderPane, 200, 250);
        bPStage.setTitle("BorderPane");
        bPStage.setScene(scene3);
        bPStage.show();

///////////////BorderPane incorporated with Hbox and Vbox//////////////////////

        Stage stage3 = new Stage();
        BorderPane bPane = new BorderPane();
        bPane.setTop(getHBox());
        bPane.setLeft(getVBox());

        Scene scene4 = new Scene(bPane);
        stage3.setTitle("HBox, VBox");
        stage3.setScene(scene4);
        stage3.show();

    }
    private HBox getHBox() {
        //Hbox has two constrcutors. One no-arg constructor and the other specifies
        //its spacing.
        HBox hbox = new HBox(15);
        hbox.setPadding(new Insets(15,15,15,15));
        hbox.setStyle("-fx-background-color: gold");
        hbox.getChildren().add(new Button("Computer Science"));
        hbox.getChildren().add(new Button("Security"));
        ImageView imageview = new ImageView(new Image("https://www.onlinestores.com/media/catalog/american-flag-summary.jpg"));
        hbox.getChildren().add(imageview);
        return hbox;
    }
    private VBox getVBox() {
        //We're going to have an array that will store four labels.
        VBox vbox = new VBox(15);
        vbox.setPadding(new Insets(15,5,5,5));
        vbox.getChildren().add(new Label("Courses"));

        Label[] courses = {new Label("CS 1332"), new Label("CS 2340"),
            new Label("CS 4400"), new Label("CS 2050")};

        for (Label course : courses) {
            vbox.setMargin(course, new Insets(0,0,0,15));
            vbox.getChildren().add(course);
        }
        return vbox;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
class CustomPane extends StackPane {
    public CustomPane(String title) {
        getChildren().add(new Label(title));
        setStyle("-fx-border-color: red");
        setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
    }
}
