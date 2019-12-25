package model;

class Egypt extends Civilization {

    private Desert desert = new Desert();

    public Egypt() {
        super("Egypt");
    }

    public Desert getDesert() {
        return desert;
    }

    @Override
    public RangedUnit getRangedUnit() {
        return new WarChariot(this);
    }

    @Override
    public Landmark getLandmark() {
        return new Pyramid(this);
    }

    @Override
    public String explore() {
        int total = desert.findTreasure();
        super.getTreasury().earn(total);
        return "You have explored and you have acquired " + total
            + " gold";
    }

}
