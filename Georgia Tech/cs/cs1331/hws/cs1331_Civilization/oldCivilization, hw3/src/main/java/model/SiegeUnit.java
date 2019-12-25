package model;

class SiegeUnit extends MilitaryUnit {

    public SiegeUnit(Civilization owner) {
        super(200, owner, 5, 10, 14, 5, 10, 60);
    }

    @Override
    public char symbol() {
        return 'S';
    }

    @Override
    public String toString() {
        return "Siege Unit. " + super.toString();
    }

    @Override
    public void battle(MapObject o) {
        if (o instanceof Building) {
            o.damage(getDamage());
        }
    }

    @Override
    public void attack(MapObject o) {
        battle(o);
        getOwner().getStrategy().siege();
        setCanAttack(false);
    }
}
