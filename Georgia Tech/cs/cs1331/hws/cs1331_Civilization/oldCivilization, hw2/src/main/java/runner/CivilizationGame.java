package runner;
//Jave Importer

import controller.GameController;
import view.StartScreen;
import view.CivEnum;
import view.GameScreen;
import model.QinDynasty;
import model.RomanEmpire;
import model.Egypt;
import model.Bandit;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import view.GridFX;
import javafx.scene.control.ButtonType;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {
    private StartScreen ss = new StartScreen();
    private Scene toBeReturned;
    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    @Override
    public void start(Stage primaryStage) {
        Scene startScene = new Scene(ss);
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Civilization");

        Button start = ss.getStartButton();
        start.setOnMouseClicked(e -> {
                primaryStage.setScene(startGame());
            });
        primaryStage.show();
    }

    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        Application.launch(args);
    }
    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public Scene startGame() {
        GameScreen gameScreen;
        TextInputDialog dialog = new TextInputDialog("Town Name");
        dialog.setTitle("A New Settlement");
        dialog.setHeaderText("You have built a Settlement!");
        dialog.setContentText("Enter the name of your first town:");
        Optional<String> result = dialog.showAndWait();

        //This is for if there isn't any text and they try to press okay
        if (!result.isPresent()) {
            toBeReturned = new Scene(ss);
            return toBeReturned;
        }
        if (ss.getCivList().getSelectionModel().getSelectedItem() == null) {
            Egypt civParam = new Egypt();
            GameController.setCivilization(civParam);
            gameScreen = new GameScreen();

            GridFX.getMap().putSettlement(result.get(), civParam, 0, 9);

            Bandit bandit = new Bandit();
            GridFX.getMap().addEnemies(bandit, 5);

            gameScreen.update();
            GridFX.update();
            toBeReturned = new Scene(gameScreen);
            return toBeReturned;
        }

        if (CivEnum.ANCIENT_EGYPT
            == ss.getCivList().getSelectionModel().getSelectedItem()) {

            Egypt civParam = new Egypt();
            GameController.setCivilization(civParam);
            gameScreen = new GameScreen();

            GridFX.getMap().putSettlement(result.get(), civParam, 0, 9);

            Bandit bandit = new Bandit();
            GridFX.getMap().addEnemies(bandit, 5);

            gameScreen.update();
            GridFX.update();
            toBeReturned = new Scene(gameScreen);
            return toBeReturned;
        } else if (CivEnum.QIN_DYNASTY
            == ss.getCivList().getSelectionModel().getSelectedItem()) {

            QinDynasty civParam = new QinDynasty();
            GameController.setCivilization(civParam);
            gameScreen = new GameScreen();

            GridFX.getMap().putSettlement(result.get(), civParam, 0, 9);

            Bandit bandit = new Bandit();
            GridFX.getMap().addEnemies(bandit, 5);

            gameScreen.update();
            GridFX.update();
            toBeReturned = new Scene(gameScreen);
            return toBeReturned;
        } else if (CivEnum.ROMAN_EMPIRE
            == ss.getCivList().getSelectionModel().getSelectedItem()) {

            RomanEmpire civParam = new RomanEmpire();
            GameController.setCivilization(civParam);
            gameScreen = new GameScreen();

            GridFX.getMap().putSettlement(result.get(), civParam, 0, 9);

            Bandit bandit = new Bandit();
            GridFX.getMap().addEnemies(bandit, 5);

            gameScreen.update();
            GridFX.update();
            toBeReturned = new Scene(gameScreen);
            return toBeReturned;
        }
        toBeReturned = new Scene(ss);
        return toBeReturned;
    }
}
