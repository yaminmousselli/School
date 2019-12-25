import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.ArrayList;
import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.Method;
import org.junit.Test;
import org.junit.Before;
import java.io.*;

public class DriverTest {

    @Test
    public void bingeableTest() {
        String expectedString = "Test";
        Bingeable b = new Bingeable() {
            public String binge() {
                return expectedString;
            }
        };

        // b.binge();
        assertEquals(expectedString, b.binge());
    }

    @Test
    public void tvSeriesModifierTest() {
        try {
            Class<?> c = Class.forName("TvSeries");
            assertTrue(Modifier.isAbstract(c.getModifiers()));
        } catch (Exception e) {
            fail("Class TvSeries not found");
        }
    }

    @Test
    public void bingeableModifierTest() {
        try {
            Class<?> c = Class.forName("Bingeable");
            assertTrue(Modifier.isInterface(c.getModifiers()));
        } catch (Exception e) {
            fail("Bingeable not found");
        }
    }

    @Test
    public void setterTest() {

        try {
            Class<?> c = Class.forName("TvSeries");
            Method[] methods = c.getDeclaredMethods();
            for (Method method : methods) {
                if (Modifier.isPublic(method.getModifiers()) &&
                method.getReturnType().equals(void.class) &&
                method.getParameterTypes().length == 1 &&
                method.getName().matches("^set.*")) {
                    fail("Setters present. You are not allowed to have Setters in the TvSeries class");
                }
            }
        } catch (Exception e) {
            fail("Class TvSeries not found");
        }
    }

    @Test
    public void tvSeries_NotBingeableTest() {
        try {
            Class<?> c = Class.forName("TvSeries");
            Class<?> b = Class.forName("Bingeable");
            Class[] interfaces = c.getInterfaces();
            boolean found = true;
            for (Class i : interfaces) {
                if (i.toString().equals(b.toString())) {
                    found = false;
                }
            }
            assertTrue("Bingeable being implemented in TvSeries", found);
        } catch(Exception e) {
            fail("TvSeries class does not exist");
        }
    }

    @Test
    public void subclass_test() {

        try {
            Class<?> c = Class.forName("NetflixTvSeries");
        } catch(Exception e) {
            fail("NetflixTvSeries class does not exist");
        }

        NetflixTvSeries strangerThings = new NetflixTvSeries("StrangerThings", 55, 8);

        if (!(strangerThings instanceof TvSeries)) {
            fail("Stranger Things is not an instance of TvSeries. It should be.");
        }
    }

    @Test
    public void bingeable_implementation() {
        // Given
        try {
            Class<?> c = Class.forName("NetflixTvSeries");
            Class<?> b = Class.forName("Bingeable");
            Class[] interfaces = c.getInterfaces();
            boolean found = false;
            for (Class i : interfaces) {
                if (i.toString().equals(b.toString())) {
                    found = true;
                }
            }
            assertTrue("Bingeable not implemented", found);
        } catch(Exception e) {
            fail("NetflixTvSeries class or Bingeable does not exist");
        }
    }

    @Test
    public void binge_Method_Test() {
        try {
            Class<?> c = Class.forName("NetflixTvSeries");
            Class<?> b = Class.forName("Bingeable");
            Class[] interfaces = c.getInterfaces();
            boolean found = false;
            for (Class i : interfaces) {
                if (i.toString().equals(b.toString())) {
                    found = true;
                }
            }
            assertTrue("Bingeable not implemented", found);
        } catch(Exception e) {
            fail("NetflixTvSeries class does not exist");
        }

        Bingeable stranger = new NetflixTvSeries("Stranger Things", 55, 8);

        String expectedStranger = getExpectedBinge("Stranger Things", 55, 8);

        // When
        String out = stranger.binge();
        // Then
        if (out.contains("\n")) {
            assertEquals(expectedStranger+"\n", out);
        } else {
            assertEquals(expectedStranger, out);
        }
    }

    private String getExpectedBinge(String title, int num, int length) {
        return "You binge watch " + title + ". It takes " + (num * length)
            + " minutes to binge.";
    }

}
