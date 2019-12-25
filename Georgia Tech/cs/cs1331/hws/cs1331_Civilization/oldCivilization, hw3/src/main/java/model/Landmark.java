package model;

class Landmark extends Building {

    public Landmark(Civilization owner) {
        super(200, owner, 0, 0, 0, 10, 0, 10);
    }

    @Override
    public void invest() {
        int morePoints = getTechPointGeneration() + 5;
        setTechPointGeneration(morePoints);
    }

    @Override
    public char symbol() {
        return '!';
    }

    @Override
    public String toString() {
        return "Landmark. " + super.toString();
    }
}
