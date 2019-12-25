public class Format {

    public static void main(String[] args) {
        int age = 22;
        int num = 100;

        System.out.printf("How old are you: %d, quantity is %d", age, num); //%d is to specify a width. %10d specifies the width to the right by adding 10 spaces.
                                                                         //You can left align it by doing %-d which includes the number and then adds the width
                                                                         //preceding the number.
        for (int i = 0; i <= 7; ++i) {
            System.out.printf("%d: Yamin is learning\n", i);
        }

        for (int i = 0; i <=3; ++i) {
            System.out.printf("%-2d: %s\n ", i, "here is some text"); //%s adds a string to the the the location of the format specifier
                                                                      //MAKE SURE THAT YOU HAVE AS MANY ARGUMENTS AS YOU HAVE SPECIFIERS.
        }
    }
}
