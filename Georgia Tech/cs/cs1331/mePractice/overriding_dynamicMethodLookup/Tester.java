public class Tester {

    public static void main(String[] args) {
        Parent p = new Parent("Dad", 45, "Pizza");
        System.out.println(p);
        System.out.println(p.getName());
        p.print(10);

        System.out.println();

        Child c = new Child("Son", 5, "Cookie");
        System.out.println(c);
        System.out.println(c.getName());
        c.print(50, 50);

    }
}
