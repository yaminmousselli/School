package model;

import java.util.Random;

/**
 * A wrapper class for all of the Objects needed in the game. Also has methods
 * to facilitate interactions between these objects, and methods to interface
 * with the UI. Handles some of the game logic.
 *
 * @version 2.0
 * @author Jim Harris, Ryan Voor
 */

//Dead code now? - Marc
public class Model {

    private static Civilization playerCivilization;
    private static Bandit enemyCiv = new Bandit();
    private static Map map;
    private static boolean playing;
    private static TerrainTile selected;
    private static TerrainTile oldSelected;
    private static int selectedRow;
    private static int selectedCol;
    private static Random rand = new Random();

    static {
        map = new Map(10, 10);
        playing = true;
    }

    /**
     * Do not instantiate this. Ever. There can be only one.
     */
    private Model() {
    }

    /**
     * @return a boolean representing whether or not the game is still going on.
     */
    public static boolean getPlaying() {
        return Model.playing;
    }

    /**
     * Sets whether or not the game is being played.
     *
     * @param playing whether or not the game is still being played.
     */
    public static void setPlaying(boolean playing) {
        Model.playing = playing;
    }

    /**
     * Instantiates the player civilization based on a user selection. Will only
     * do this if a valid option is entered for @param{civChoice}:
     * <p>
     * 1: Egypt
     * 2: QinDyansty
     * 3: RomanEmpire
     * <p>
     * This is a prerequisite to most of this class actually working.
     *
     * @param civChoice the user choice for which civlization to play.
     * @return a boolean representing whether or not the playerCivilization was
     * instantiated.
     */
    public static boolean chooseCivilization(int civChoice) {
        switch (civChoice) {
        case 1:
            playerCivilization = new Egypt();
            return true;
        case 2:
            playerCivilization = new QinDynasty();
            return true;
        case 3:
            playerCivilization = new RomanEmpire();
            return true;
        default:
            return false;
        }
    }

    /**
     * Calls the playerCivilization's explore method.
     *
     * @return the output String from the explore method.
     */
    public static String explore() {
        return playerCivilization.explore();
    }

    /**
     * Puts a Settlement onto the map. This is slightly convenient because it
     * forces the number of settlements to get increased as well, which is
     * something that can be easily forgotten.
     *
     * @param name the name of the Settlement.
     * @param civ  the Civilization that owns the new Settlement.
     * @param r    the row to add at.
     * @param c    the column to add at.
     */
    public static void putSettlement(String name, Civilization civ,
                                     int r, int c) {
        map.getTile(r, c).setOccupant(civ.getSettlement(name));
        civ.incrementNumSettlements();
    }

    /**
     * Adds some enemies and an enemy settlement onto the Map.
     *
     * @param bandit the enemy Civilization.
     */
    private static void addEnemies(Bandit bandit, int numEnemies) {
        for (int i = 0; i < numEnemies; i++) {
            int r = rand.nextInt(10);
            int c = rand.nextInt(10);
            putSettlement("Bandit Hideout", bandit, 9, 0);
            while (!map.getTile(r, c).isEmpty()) {
                //try to get new coords
                r = rand.nextInt(10);
                c = rand.nextInt(10);
            }
            // bandit.addUnit(map.getTile(r, c), bandit.getMeleeUnit());
        }
    }

    /**
     * Returns whether or not the tile at r, c is adjacent to something.
     * This something depends on some code that is passed through @param{what}.
     *
     * @param r    the row of the tile to check adjacency of.
     * @param c    the column of the tile to check adjacency of.
     * @param what a code that gets passed in to determine what we're checking
     * adjacency to. If "SETTLEMENT" is passed in, it checks if the tile at r, c
     * is adjacent to a Settlement on the map. "FR_SETTLEMENT" checks for a
     * friendly (playerCivilization owned) Settlement, and "SELECTED" checks for
     * adjacency to the selected tile.
     * @return a boolean representing whether or not the tile at r, c is
     * adjacent to something.
     */
    public static boolean adjacentTo(int r, int c, String what) {
        for (int i = r - 1; i <= r + 1; i++) {
            for (int j = c - 1; j <= c + 1; j++) {
                if (i >= 0 && i < map.getRows()
                        && j >= 0 && j < map.getColumns()) {
                    if (adjBool(i, j, what) && !(i == r && j == c)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * @param r    the row of the tile to check.
     * @param c    the column of the tile to check.
     * @param what the code for what we are looking for at tile (r, c)
     * @return whether or not a particular thing occupies (r, c)
     */
    private static boolean adjBool(int r, int c, String what) {
        switch (what) {
        case "SETTLEMENT":
            return map.getTile(r, c).getOccupant() instanceof Settlement;
        case "SELECTED":
            return map.getTile(r, c) == selected;
        case "FR_SETTLEMENT":
            return adjBool(r, c, "SETTLEMENT")
                    && map.getTile(r, c).getOccupant().getOwner()
                    == playerCivilization;
        default:
            break;
        }
        return false;
    }

    /**
     * Adds the first player Settlement and some enemies to the Map. This should
     * be called when the game starts up for the first time, and after the
     * player has selected their civilization and provided a name for the
     * Settlement.
     * <p>
     * Refactored to populate entire board with home settlement and bandit(Marc)
     *
     * @param name the name of the first player Settlement.
     */
    public static void addFirstSettlement(String name) {
        putSettlement(name, playerCivilization, 0, 9);
        addEnemies(enemyCiv, 5);
    }
    /**
    * gets the gold of the civilization
    * @return int
    */
    public static int getGold() {
        return playerCivilization.getTreasury().getCoins();
    }
    /**
    * gets the food of the civilization
    * @return int
    */
    public static int getFood() {
        return playerCivilization.getFood();
    }
    /**
    * gets the resource of the civilization
    * @return int
    */
    public static int getResources() {
        return playerCivilization.getResources();
    }
    /**
    * gets the hapiness of the civilization
    * @return int
    */
    public static int getHappiness() {
        return playerCivilization.getHappiness();
    }
    /**
    * gets the philosophy of the civilization
    * @return int
    */
    public static int getPhilosophy() {
        return playerCivilization.getTechnology().getUnderstanding();
    }
    /**
    * gets the build experience of the civilization
    * @return int
    */
    public static int getBuildExperience() {
        return playerCivilization.getTechnology().getBuildExperience();
    }
    /**
    * gets the strategy of the civilization
    * @return int
    */
    public static int getStrategy() {
        return playerCivilization.getStrategy().getStrategyLevel();
    }
    /**
    * gets the number of settlements of the civilization
    * @return int
    */
    public static int getSettlementRemaining() {
        return playerCivilization.getNumSettlements();
    }

    /**
     * Designates a specific tile as the player's selected tile. This therefore
     * will set @code{selected}.
     *
     * @param r the row of the tile to select.
     * @param c the column of the tile to select.
     */
    public static void selectTile(int r, int c) {
        selected = map.getTile(r, c);
        selectedRow = r;
        selectedCol = c;
    }


    /**
     * calls the move method and returns whether the move was successfully
     * executed
     * NOTE: THIS METHOD SHOULD USE NO MORE THAN ONE CATCH BLOCK
     *
     * @param r the
     * @param c the column value of the tile to be moved to
     * @return boolean whether the move was successfully executed
     */
    public static boolean moveSelected(int r, int c) {
        try {
            move(r, c);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * attempted to move the currently selected unit to the given
     * Tile location. Throws NotAdjacentToSelectedTileException
     * if the given Tile location is not adjacent to the current
     * selection. Throws UnitCannotMoveException if the unit cannot
     * move to the Tile location for some reason (e.g. the unit already
     * moved this turn). Throws TileAlreadyOccupiedException if the
     * unit is attempting to move to a Tile that already has an
     * occupant.
     * Does not execute the move if any exception is thrown.
     */
    private static void move(int r, int c)
            throws NotAdjacentToSelectedTileException,
            UnitCannotMoveException, TileAlreadyOccupiedException {
        if (!adjacentTo(r, c, "SELECTED")) {
            throw new NotAdjacentToSelectedTileException(
                    "That Tile isn't adjacent to the currently selected Tile.");
        }
        if (!((Unit) selected.getOccupant())
                .canMove(selected.getType().getCost())) {
            throw new UnitCannotMoveException(
                    "That unit cannot move for some reason.");
        }
        if (!map.isEmpty(r, c)) {
            throw new TileAlreadyOccupiedException(
                    "The Tile you tried to move to is already"
                            + " occupied by another unit");
        }
        map.getTile(r, c).setOccupant(selected.getOccupant());
        ((Unit) selected.getOccupant()).deductEndurance(
                map.getTile(r, c).getType().getCost());
        selected.setOccupant(null);
    }

    /**
     * Recruits a unit for the player civilization to the selected tile. Should
     * have just overloaded this. Will only recruit a unit if the selection is
     * valid, and the unit can be afforded by playerCivilization. Assumes that
     * selected is empty.
     *
     * @param selection an int representing what kind of unit to recruit.
     * @param name      the name of the Settlement a Settler might settle.
     * @return a boolean representing whether or not a unit was recruited.
     */
    public static boolean recruitSelected(int selection, String name) {
        Unit newUnit = null;
        switch (selection) {
        case 1:
            newUnit = playerCivilization.getMeleeUnit();
            break;
        case 2:
            newUnit = playerCivilization.getRangedUnit();
            break;
        case 3:
            newUnit = playerCivilization.getHybridUnit();
            break;
        case 4:
            newUnit = playerCivilization.getSiegeUnit();
            break;
        case 5:
            newUnit = playerCivilization.getSettlerUnit(name);
            break;
        case 6:
            newUnit = playerCivilization.getFarmerUnit();
            break;
        case 7:
            newUnit = playerCivilization.getCoalMinerUnit();
            break;
        case 8:
            newUnit = playerCivilization.getAnglerUnit();
            break;
        case 9:
            newUnit = playerCivilization.getMasterBuilderUnit();
            break;
        default:
            break;
        }
        if (newUnit != null && newUnit.isAffordable()) {
            newUnit.applyInitialCosts();
            selected.setOccupant(newUnit);
            return true;
        }
        return false;
    }

    /**
     * Makes the MilitaryUnit at selected attack something at (r, c). Only
     * assumes that selected is occupied by a MilitaryUnit. There is an
     * instanceof check there that is uneccessary, but I'm not touching it
     * because I don't want to break anything :P. This will also remove any
     * destroyed units from the Map.
     *
     * @param r the row of the prospective enemy MapObject.
     * @param c the column of the prospective enemy MapObject.
     * @return a boolean representing whether or not an attack happened.
     */
    public static boolean attackSelected(int r, int c) {
        MapObject enemy = map.getTile(r, c).getOccupant();
        MapObject attacker = selected.getOccupant();
        if (selected.getOccupant() instanceof MilitaryUnit
                && enemy != null && enemy.getOwner() != playerCivilization
                && ((Unit) attacker).getCanAttack()
                && adjacentTo(r, c, "SELECTED")) {
            ((MilitaryUnit) attacker).attack(enemy);
            if (attacker.isDestroyed()) {
                selected.setOccupant(null);
            }
            if (enemy.isDestroyed()) {
                map.getTile(r, c).setOccupant(null);
            }
            return true;
        }
        return false;
    }

    /**
     * Attempts to convert the MapObject at selected. Makes an assumption that
     * the thing on selected is Convertable. Will check the canConvert method.
     *
     * @return a boolean representing whether or not the Convertable could
     * convert.
     */
    public static boolean convertSelected() {
        Convertable worker = (Convertable) selected.getOccupant();
        if (worker.canConvert(selected.getType())) {
            selected.setOccupant(worker.convert());
            return true;
        } else {
            return false;
        }
    }

    /**
     * Calls the tick method for every valid MapObject in the Map.
     */
    public static void tick() {
        for (int r = 0; r < map.getRows(); r++) {
            for (int c = 0; c < map.getColumns(); c++) {
                if (!map.getTile(r, c).isEmpty()) {
                    map.getTile(r, c).getOccupant().tick();
                }
            }
        }
    }

    /**
     * @return a boolean representing whether or not the player has won.
     */
    public static boolean checkWin() {
        return playerCivilization.getTechnology().hasTechnologyWin()
                || playerCivilization.getStrategy().conqueredTheWorld();
    }

    /**
     * Invests in a Building at selected. Assumes that selected has a Building.
     * Will spend 20 gold from the playerCivilization in order to do this, and
     * will only do it if the playerCivilization can afford it.
     *
     * @return a boolean representing whether or not the building could be
     * invested in.
     */
    public static boolean investSelected() {
        if (playerCivilization.getTreasury().getCoins() >= 20) {
            ((Building) selected.getOccupant()).invest();
            playerCivilization.getTreasury().spend(20);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Demolishes a Building at selected to harvest resources for the player.
     * Makes an assumption that selected houses a Building. Will only demolish
     * if the player owns the Building, and they are not trying to demolish
     * their last Settlement.
     *
     * @return whether or not the Building was demolished.
     */
    public static boolean demolishSelected() {
        if (selected.getOccupant() instanceof Settlement
                && playerCivilization.getNumSettlements() <= 1) {
            return false;
        } else if (selected.getOccupant().getOwner() == playerCivilization) {
            ((Building) selected.getOccupant()).demolish();
            selected.setOccupant(null);
            playerCivilization.decrementNumSettlements();
            return true;
        }
        return false;
    }
    /**
     * Gets the symbol of the occupant, empty char
     * if tile is empty
     * @param int r, int c
     * @return char
     */
    public static char getOccupantSymbol(int r, int c) {
        if (map.getTile(r, c).isEmpty()) {
            //empty
            return ' ';
        }
        return map.getTile(r, c).getOccupant().symbol();
    }
    /**
     * Gets the name of the owner of the tile
     * @param int r, int c
     * @return String
     */
    public static String getOwnerString(int r, int c) {
        TerrainTile a = map.getTile(r, c);
        if (a.isEmpty()) {
            return "";
        } else {
            return a.getOccupant().getOwner().getName();
        }
    }
    /**
    * gets the type of occupent on the til specified
    * by the row and column
    * @param int r, int c
    * @return String
    */
    public static String getTileOccupantType(int r, int c) {
        MapObject occupant = map.getTile(r, c).getOccupant();
        if (occupant != null) {
            if (occupant.getOwner() != playerCivilization) {
                return "ENEMY";
            } else if (occupant instanceof Building) {
                return "BUILDING";
            } else if (occupant instanceof MilitaryUnit) {
                return "MILITARY_UNIT";
            } else {
                return "WORKER_UNIT";
            }
        } else if (adjacentTo(selectedRow, selectedCol, "FR_SETTLEMENT")) {
            return "RECRUIT_SPACE";
        } else {
            return "";
        }
    }

    public static boolean tileIsEmpty(int r, int c) {
        return map.getTile(r, c).getOccupant() == null;
    }

    /**
     * Attacks one tile with another, assuming that they are already adjacent!
     *
     * @param attacker attacking tile
     * @param target   tile to be attacked
     * @return whether that attack was successful
     * <p>
     * Used by the AI
     */
    private static boolean attackTile(TerrainTile attacker,
                                      TerrainTile target) {
        if (attacker.getOccupant() instanceof MilitaryUnit) {
            MilitaryUnit attackingUnit = (MilitaryUnit) attacker.getOccupant();
            if (attacker.getOccupant().getOwner() != playerCivilization
                    && attackingUnit.getCanAttack()) {
                attackingUnit.attack(target.getOccupant());

                if (attackingUnit.isDestroyed()) {
                    selected.setOccupant(null);
                }

                if (target.getOccupant().isDestroyed()) {
                    map.getTile(target.getRow(),
                            target.getCol()).setOccupant(null);
                    return true;
                }
            }
        }
        return false;
    }

    /**
    * gets the name of the terrain tile
    * @param int r, int c
    * @return String
    */
    public static String getTileTerrain(int r, int c) {
        return map.getTile(r, c).getType().getName();
    }
    /**
    * gets the health of the occupant on the terrain tile
    * @param int r, int c
    * @return int
    */
    public static int getHealthAt(int r, int c) {
        return map.getTile(r, c).getOccupant().getHealth();
    }

    /**
     * gets the endurance of the MapObject at r, c
     * @param r the row
     * @param c the column
     * @return -1 if the MapObject does not have an endurance
     */
    public static int getEnduranceAt(int r, int c) {
        MapObject a = (map.getTile(r, c).getOccupant());
        if (a instanceof Unit) {
            return ((Unit) a).getEndurance();
        } else {
            return -1;
        }
    }
    /**
    * set selected as the old selected
    */
    public static void saveSelected() {
        oldSelected = selected;
    }
    /**
    * set old selected as the selected
    */
    public static void restoreSelected() {
        selected = oldSelected;
    }
}
