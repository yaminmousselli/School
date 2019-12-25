package view;

import controller.GameController;
import model.Convertable;
import model.MapObject;
import model.TerrainTile;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.media.AudioClip;
/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    private Button move;
    private Button convert;
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {
        move = new Button("Move");
        convert = new Button("Convert");

        move.setOnAction(e -> {
                AudioClip moveSound = new
                    AudioClip("File:./src/main/java/view/finalMoving.wam");
                moveSound.play();
                GameController.moving();
            });

        convert.setOnAction(e -> {
                AudioClip convertSound = new
                    AudioClip("File:./src/main/java/view/convert.mp3");
                convertSound.play();
                TerrainTile tile = GameController.getLastClicked().
                    getTile();
                if (!tile.isEmpty()) {
                    MapObject occupant = GameController.getLastClicked().
                        getTile().getOccupant();
                    if (((Convertable) occupant)
                        .canConvert(tile.getType())) {
                        tile.setOccupant(((Convertable) occupant)
                                    .convert());
                        GridFX.update();
                        GameController.updateResourcesBar();
                    } else {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy!");
                        alert.setHeaderText(null);
                        alert.setContentText("Tile can't be converted!");
                        alert.showAndWait();
                    }
                }
            });
        addMenuItem(move);
    }
}
