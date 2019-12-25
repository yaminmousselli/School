import static org.junit.Assert.*;
import org.junit.Test;

public class CarTest {
    @Test
    public void constructorTest() {
        try {
            Car a = new Car(2005, "Ford", "Tahoe");
        } catch (Throwable t) {
            fail("-10 Constructor threw exception for valid input:");
        }
    }

    @Test
    public void gettersTest() {
        String expectedModel = "Tahoe";
        int expectedYear = 2005;
        String expectedMake = "Ford";
        Car b = new Car(expectedYear, expectedMake, expectedModel);
        assertEquals("-10 getModel() returns incorrect value:",
                     expectedModel, b.getModel()); //unit teting
        assertEquals("-10 getYear() returns incorrect value",
                     expectedYear, b.getYear());
        assertEquals("-10 getMake() returns incorrect value",
                     expectedMake, b.getMake());
    }
}
