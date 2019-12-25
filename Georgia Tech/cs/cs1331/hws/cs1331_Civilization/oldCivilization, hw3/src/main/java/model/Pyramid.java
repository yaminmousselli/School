package model;

class Pyramid extends Landmark {

    public Pyramid(Civilization owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "Pyramid. " + super.toString();
    }

    @Override
    public void invest() {
        super.invest();
        getOwner().getTechnology().philosophize();
    }
}
