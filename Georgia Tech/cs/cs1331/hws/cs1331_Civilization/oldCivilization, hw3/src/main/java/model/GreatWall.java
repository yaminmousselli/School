package model;

class GreatWall extends Landmark {

    public GreatWall(Civilization owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "Great Wall. " + super.toString();
    }

    @Override
    public void invest() {
        super.invest();
        int total = getOwner().getStrategy().getStrategyLevel() + 10;
        getOwner().getStrategy().setStrategyLevel(total);
    }
}
