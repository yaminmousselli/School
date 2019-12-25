import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashSet;

public class Tester {

    public static String[] vehicles = {"ambulance", "hellicopter", "lifeboat"};

    public static String[][] drivers = {
        {"Fred", "Sue", "Pete"},
        {"Sue", "Richard", "Bob", "Fred"},
        {"Pete", "Mary", "Bob"},
    };

    public static void main(String[] args) {
        //We don't want duplicate names (values) so we're going to use a set for our values
        Map<String, Set<String>> personnel = new HashMap<String, Set<String>>();

        for (int i = 0; i < vehicles.length; ++i) {
            Set<String> driverSet = new LinkedHashSet<String>();
            for (String driverName : drivers[i]) {
                driverSet.add(driverName);
            }
            personnel.put(vehicles[i], driverSet);
        }

        for (String vehicle : personnel.keySet()) {
            System.out.println();
            System.out.println("For Vehicle: " + vehicle + '\n' + "Drivers are: ");

            for (String driverName : personnel.get(vehicle)) {
                System.out.println(driverName);
            }
        }
    }
}
