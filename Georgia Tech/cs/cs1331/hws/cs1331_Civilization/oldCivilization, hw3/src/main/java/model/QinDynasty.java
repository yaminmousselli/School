package model;

class QinDynasty extends Civilization {

    private Hills hills = new Hills();

    public QinDynasty() {
        super("QinDynasty");
    }

    public Hills getHills() {
        return hills;
    }

    @Override
    public SiegeUnit getSiegeUnit() {
        return new BlackPowderUnit(this);
    }

    @Override
    public Landmark getLandmark() {
        return new GreatWall(this);
    }

    @Override
    public String explore() {
        hills.replenishGame();
        Game huntFood = hills.hunt();
        int health = huntFood.getHealth();
        super.makeFood(health);
        return "You have hunted and added it to your supply";
    }
}
