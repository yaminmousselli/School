/*
The image class represents a graphical image and the ImageView class displays an
image. It's important to know that the Image class object that there is no
guarentee that it is corresponding to a real image. You must make sure by using
the getError() which indicates whether the image was loaded correctly. You must
import both of them.

An HBox is a slightly different variation from the pane. The HBox will always
display the nodes horizontally. A Vbox alligns the nodes vertically.

*/
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;

public class MyJavaFX2 extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane sPane = new StackPane();
        sPane.getChildren.add(new Button("StackPane"));
        Scene scene = new Scene(sPane, 200, 200);
        primaryStage.setTitle("Stack Pane Gui");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage stage3 = new Stage();
        Pane pane5 = new HBox(10); //the amount of nodes that it can carry
        pane5.setPadding(5,5,5,5); //Set Padding so you're not constantly right on the edge.
        Image image = new Image("file:imageView/civ_background.png"); //This is where you specify a file path and the pic must be in the bin as src
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        pane5.getChildren.add(imageView);
        Scene scene2 = new Scene(pane5);
        stage3.setTitle("Image Gui");
        stage3.setScene(scene2);
        stage3.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
