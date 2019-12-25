package view;

import controller.GameController;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {
    private static ResourcesMenu resources = new ResourcesMenu();
    private static WorkerMenu wm = new WorkerMenu();
    private static MilitaryMenu mm = new MilitaryMenu();
    private static BuildingMenu bm = new BuildingMenu();
    private static StatusMenu sm = new StatusMenu();
    private static RecruitMenu rm = new RecruitMenu();
    private static AbstractMenu am = new AbstractMenu();
    private static VBox vbox = new VBox();

    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {
        //Top, Right, Bottom, Left, Center
        setTop(this.getResources().getRootNode());
        setLeft(vbox);
        setCenter(GridFX.getInstance());
        vbox.getChildren().add(am.getRootNode());
    }
    /**
     * This method should update the gridfx and the resouce bar
     */
    public void update() {
        GridFX.getInstance().update();
        this.getResources().update();
        setTop(this.getResources().getRootNode());
    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        return resources;
    }
    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        if (state == GameController.GameState.MILITARY) {
            vbox.getChildren().clear();
            vbox.getChildren().add(mm.getRootNode());
        } else if (state == GameController.GameState.WORKER) {
            vbox.getChildren().clear();
            vbox.getChildren().add(wm.getRootNode());
        } else if (state == GameController.GameState.RECRUITING) {
            vbox.getChildren().clear();
            vbox.getChildren().add(rm.getRootNode());
        } else if (state == GameController.GameState.BUILDING) {
            vbox.getChildren().clear();
            vbox.getChildren().add(bm.getRootNode());
        } else {
            vbox.getChildren().clear();
            vbox.getChildren().add(sm.getRootNode());
        }
    }
}
