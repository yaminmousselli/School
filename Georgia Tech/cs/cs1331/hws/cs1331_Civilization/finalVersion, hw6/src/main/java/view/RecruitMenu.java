package view;

import controller.GameController;
import java.util.Arrays;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.TerrainTile;
import model.Unit;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {
    private ListView<REnum> units;
    private Button select = new Button("Select");

    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    public RecruitMenu() {
        List<REnum> unitList1 = Arrays.asList(REnum.values());
        ObservableList<REnum> unitList2 = FXCollections
            .observableArrayList(unitList1);
        units = new ListView<>(unitList2);

        select.setOnAction(e -> {
                TerrainTile tile = GameController.getLastClicked()
                    .getTile();
                REnum shortCut = units.getSelectionModel()
                    .getSelectedItem();

                if (shortCut == REnum.MELEE) {
                    Unit temp = GameController.getCivilization()
                        .getMeleeUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy!");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getMeleeUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.RANGE) {
                    Unit temp = GameController.getCivilization()
                        .getRangedUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getRangedUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.HYBRID) {
                    Unit temp = GameController.getCivilization()
                        .getHybridUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getHybridUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.SIEGE) {
                    Unit temp = GameController.getCivilization().
                        getSiegeUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getSiegeUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.SETTLER) {
                    Unit temp = GameController.getCivilization()
                        .getSettlerUnit("Awesome Settler Unit");
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getSettlerUnit(REnum.SETTLER.toString()));
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.FARMER) {
                    Unit temp = GameController.getCivilization()
                        .getFarmerUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getFarmerUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else if (shortCut == REnum.COALMINER) {
                    Unit temp = GameController.getCivilization()
                        .getCoalMinerUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getCoalMinerUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                } else {
                    Unit temp = GameController.getCivilization()
                        .getMasterBuilderUnit();
                    if (!temp.isAffordable()) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Hold It There Buddy");
                        alert.setHeaderText(null);
                        alert.setContentText("You can't afford to recruit");
                        alert.showAndWait();
                    } else {
                        temp.applyInitialCosts();
                        tile.setOccupant(GameController.getCivilization()
                            .getMasterBuilderUnit());
                        GridFX.getInstance().update();
                        GameController.updateResourcesBar();
                    }
                }
            });
        addMenuItem(units);
        addMenuItem(select);
            //addMenuItem(select);
    }
}
