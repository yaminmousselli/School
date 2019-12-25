public class Frog {
    private int id;
    private String name;

    /*public Frog() {
        this.id = id;
        this.name = name;
    }*/

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }
    public void setId(int newId) {
        id = newId;
    }
    public void setInfo(String name, int id) {
        setName(name);
        setId(id);
    }

    public String toString() { //toString() does not take any paramters.
        return getId() + ": " + getName();
    }
}
