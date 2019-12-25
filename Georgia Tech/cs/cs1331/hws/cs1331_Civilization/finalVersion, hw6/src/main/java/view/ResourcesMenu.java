package view;

import controller.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ResourcesMenu {
    private HBox hbox = new HBox();

    private Text stratLevelText = new Text();
    private Text resourcesText = new Text();
    private Text settlementsText = new Text();
    private Text moneyText = new Text();
    private Text foodText = new Text();
    private Text happinessText = new Text();
    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {
        hbox.getChildren().addAll(stratLevelText, resourcesText, settlementsText
            , moneyText, foodText, happinessText);
        this.update();
    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() { //different action listeners?
        String space = "     ";
        stratLevelText.setText("Strat Level: " + " "
            + Integer.toString(
            GameController.getCivilization().getStrategy().getStrategyLevel())
            + space);

        resourcesText.setText("Resources: " + " "
            + Integer.toString(GameController.getCivilization().getResources())
            + space);

        settlementsText.setText("Settlements: " + " " + Integer.toString(
            GameController.getCivilization().getNumSettlements()) + space);

        moneyText.setText("Money: " + " "
            + Integer.toString(GameController.getCivilization()
            .getTreasury().getCoins()) + space);

        foodText.setText("Food: " + " "
            + Integer.toString(GameController.getCivilization().getFood())
                + space);

        happinessText.setText("Happiness: "
            + Integer.toString(GameController.getCivilization().getHappiness())
            + space);
    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        update();
        return hbox;
    }
}
