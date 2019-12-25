package model;

class Coliseum extends Landmark {

    public Coliseum(Civilization owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "Coliseum. " + super.toString();
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().increaseHappiness(50);
    }
}
