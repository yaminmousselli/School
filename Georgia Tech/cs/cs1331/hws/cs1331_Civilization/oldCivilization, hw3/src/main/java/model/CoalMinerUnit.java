package model;

class CoalMinerUnit extends Unit implements Convertable {

    public CoalMinerUnit(Civilization owner) {
        super(owner);
    }

    @Override
    public String toString() {
        return "CoalMiners. " + super.toString();
    }

    @Override
    public Building convert() {
        return getOwner().getCoalMine();
    }

    @Override
    public boolean canConvert(TileType type) {
        return type == TileType.HILLS;
    }

    @Override
    public char symbol() {
        return 'c';
    }
}
