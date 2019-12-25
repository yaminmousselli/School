package model;

class MeleeUnit extends MilitaryUnit {
    public MeleeUnit(Civilization owner) {
        super(100, owner, 10, 10, 14, 5, 0, 30);
    }

    @Override
    public String toString() {
        return "Melee Unit. " + super.toString();
    }

    public void battle(MapObject o) {
        o.damage(this.getDamage());
        if (!o.isDestroyed() && (o instanceof MeleeUnit
            || o instanceof HybridUnit)) {
            damage(((MilitaryUnit) o).getDamage());
        }
    }

    @Override
    public char symbol() {
        return 'M';
    }
}
