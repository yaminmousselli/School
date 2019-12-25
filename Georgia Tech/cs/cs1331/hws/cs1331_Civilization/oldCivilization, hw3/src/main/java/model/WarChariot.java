package model;

class WarChariot extends RangedUnit {

    public WarChariot(Civilization owner) {
        super(owner);
        setBaseEndurance(20);
    }

    @Override
    public char symbol() {
        return 'W';
    }

    @Override
    public String toString() {
        return "War Chariot Unit. " + super.toString();
    }
}
