public class Animal extends NumLegsAdvantageException implements Comparable<Animal>{
    private String speciesName;
    private int numberLegs;
    private int maxSpeed;

    public int compareTo(Animal o) {
        return this.getMaxSpeed() - o.getMaxSpeed();
    }

    public Animal(String name, int legs, int speed) {
        speciesName = name;
        numberLegs = legs;
        maxSpeed = speed;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public int getNumberLegs() {
        return numberLegs;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public String toString() {
        return ("(" + speciesName + ", " + numberLegs + ", " + maxSpeed + ")");
    }

    public Animal race(Animal o) throws NumLegsAdvantageException {
        if (!(this.getNumberLegs() == o.getNumberLegs())) {
            throw new NumLegsAdvantageException();
        }
        return (this.compareTo(o) == 0) ? this : o;
        /*else if (this.compareTo(o) == 0) {
            return this;
        }
        else if (this.comapreTo(o) == -1) {
            return this; //if it is negative, return o.
        }
        else {
            return o;
        }*/
    }
}
