package model;

class LegionUnit extends MeleeUnit {

    public LegionUnit(Civilization owner) {
        super(owner);
        setDamage(45);
    }

    @Override
    public void battle(MapObject o) {
        o.damage(this.getDamage());
        if (!o.isDestroyed() && o instanceof MeleeUnit) {
            damage(((MilitaryUnit) o).getDamage());
        }
    }

    @Override
    public String toString() {
        return "Legion. " + super.toString();
    }

    @Override
    public char symbol() {
        return 'L';
    }
}
