package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;
import javafx.scene.paint.Color;
/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane  {
    private Button startButton = new Button("Start");
    //set the fill to change the color on this. To change font, do set style.
    private ListView<CivEnum> civList;
    private Label label = new Label("Select a Civilization to Begin");
    private VBox vbox = new VBox(10);
    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {
        label.setTextFill(Color.RED);
        label.setStyle("-fx-font-size: 25");
        Image image = new Image("File:./src/main/java/view/civ_background.png");
        ImageView imageview =
            new ImageView(image);
        ObservableList<CivEnum> civOList = FXCollections.observableArrayList();
        civOList.addAll(CivEnum.ANCIENT_EGYPT, CivEnum.QIN_DYNASTY,
            CivEnum.ROMAN_EMPIRE);
        civList = new ListView<>(civOList);

        civList.setMaxHeight(100);
        civList.setMaxWidth(300);
        vbox.getChildren().addAll(label, civList, startButton);
        //COME BACK AND IMPLEMENT SET X AND SET Y
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        getChildren().addAll(imageview, vbox);
    }
    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return startButton;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        return civList;
    }
}
