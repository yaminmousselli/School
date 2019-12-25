package view;

import controller.GameController;
import javafx.scene.control.Button;
import model.Building;
import model.Settlement;
import model.MapObject;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    private Button demolish;
    private Button invest;
    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        invest = new Button("Invest");
        demolish = new Button("Demolish");


        invest.setOnAction(e -> {
                AudioClip investSound = new
                    AudioClip("File:./src/main/java/view/money.mp3");
                investSound.play();
                MapObject occupant = GameController.getLastClicked().getTile()
                    .getOccupant();
                if (GameController.getCivilization().getTreasury()
                    .getCoins() >= 25) {
                    ((Building) occupant).invest();
                    GameController.getCivilization().getTreasury().spend(25);
                } else {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Hold It There Buddy!");
                    alert.setHeaderText(null);
                    alert.setContentText("You can't invest, you're broke!");
                    alert.showAndWait();
                }
                GameController.updateResourcesBar();
            });
        demolish.setOnAction(e -> {
                AudioClip demolishSound = new
                    AudioClip("File:./src/main/java/view/shotgun.mp3");
                demolishSound.play();
                MapObject occupant = GameController.getLastClicked().
                    getTile().getOccupant();
                if (occupant instanceof Settlement && GameController
                    .getCivilization().getNumSettlements() > 1) {
                    GameController.getLastClicked().getTile().
                            setOccupant(null);
                    GameController.getCivilization().
                            decrementNumSettlements();
                    GameController.updateResourcesBar();
                } else if (occupant.getOwner().equals(GameController
                    .getCivilization())) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Hold It There Buddy!");
                    alert.setHeaderText(null);
                    alert.setContentText("You can't demolish");
                    alert.showAndWait();
                }
                GameController.updateResourcesBar();
            });
        addMenuItem(invest);
        addMenuItem(demolish);
    }
}
