import java.util.Scanner;
class Tester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //You can only use switch to check certain types of variables. The most common two are int and String

        System.out.print("Please enter a command: ");
        String text = in.nextLine();

        /*When you have a switch statement, you need a case for every value that you are checking and the value that you want to check
        with the colon. Then you have the statement that you want to execute with the break statement preceding that. The cases must have
        constant values NOT variables. The only variable should be in the switch paramter.
        The default case is optional but is reccomended. The default case will be executed if none of the cases are executed.
        */
        switch(text) {
            case "start":
                System.out.println("Machine started");
                break;

            case "stop":
                System.out.println("Machine stopped");
                break;

            default:
                System.out.println("Command not found");

            }
        }
}
