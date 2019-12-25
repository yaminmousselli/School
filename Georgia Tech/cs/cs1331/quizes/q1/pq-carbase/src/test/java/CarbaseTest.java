import static org.junit.Assert.*;
import org.junit.Test;

public class CarbaseTest {

    Car gtr = new Car(2017, "Nissan", "GT-R");
    Car model3 = new Car(2018, "Tesla", "Model 3");
    Car prius = new Car(2003, "Toyota", "Prius");
    Car r8 = new Car(2012, "Audi", "R8");

    Carbase cbase = new Carbase(gtr, model3, prius, r8);

    @Test
    public void constructorTest() {
        try {
            Carbase cb = new Carbase(gtr, model3, prius, r8);
        } catch (Throwable t) {
            fail("-10 Constructor threw exception for valid input:");
        }
    }

    @Test
    public void toStringTest() {
        assertEquals("-10 toString() returns incorrect value:",
                     "I love my 2017 Nissan GT-R", gtr.toString());
    }

    @Test
    public void findByModelTest() {
        assertEquals("-10 findByModel(\"Prius\") returns incorrect value:",
                     prius, cbase.findByModel(new String("Prius")));
    }

    @Test
    public void findByYearTest() {
        assertEquals("-10 findByYear(2018) returns incorrect value:",
                     model3, cbase.findByYear(2018));
    }

    @Test
    public void findNullTest() {
        assertEquals("-10 findByYear(3000) returns incorrect value:",
                     null, cbase.findByYear(3000));
    }
}
