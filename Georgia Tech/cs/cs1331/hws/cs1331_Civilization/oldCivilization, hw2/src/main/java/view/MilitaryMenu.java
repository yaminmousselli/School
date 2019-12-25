package view;

import controller.GameController;
import javafx.scene.control.Button;
/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    private Button attack;
    private Button move;
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {
        attack = new Button("Attack");
        move = new Button("Move");

        attack.setOnAction(e -> {
                GameController.attacking();
                GameController.updateResourcesBar();
            });

        move.setOnAction(e -> {
                GameController.moving();
                //check to see GameController.move alert box works!
            });

        addMenuItem(attack);
        addMenuItem(move);
    }
}
