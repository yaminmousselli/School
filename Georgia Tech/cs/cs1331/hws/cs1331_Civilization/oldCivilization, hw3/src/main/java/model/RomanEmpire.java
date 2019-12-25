package model;

class RomanEmpire extends Civilization {

    private Hills hills = new Hills(); //Specific to Roman and Qin?

    public RomanEmpire() {
        super("Roman Empire");
    }

    public Hills getHills() {
        return hills;
    }

    @Override
    public Landmark getLandmark() {
        return new Coliseum(this);
    }

    @Override
    public String explore() {
        int resources = hills.mineCoal();
        produceResources(resources);
        return "You have mined " + resources + " resources";
    }
    @Override
    public MeleeUnit getMeleeUnit() {
        return new LegionUnit(this);
    }
}
