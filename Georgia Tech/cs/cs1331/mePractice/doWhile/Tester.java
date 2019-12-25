import java.util.Scanner;
class Tester {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        /*System.out.println("Please enter a number: ");
        int num = in.nextInt();*/

        //Prompt the user for a number until they enter the number 5, in which you will terminate the loop.
        /*while (num != 5) {
            System.out.println("Please enter a number: ");
            num = in.nextInt();
        }
        System.out.println("You've entered the right number");*/

        int num = 0;
        do {
            System.out.println("Please enter a number: ");
            int num = in.nextInt();
        }
        while (num != 5);
            System.out.println("You've entered the right number");
            System.out.println("Yay"); //This line will never get executed. 
    }
}
