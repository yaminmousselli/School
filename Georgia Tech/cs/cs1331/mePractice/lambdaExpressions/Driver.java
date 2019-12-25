/*
A way of pressing a block of code into a method. You can replace the syntax sugar
of an anonymous class that implements a functional interface.

Side Note: Lambda expressions comes from Mathematics where the greek letter Lambda
has been historically asssociated with some kind of analogous situation where you can
pass some function to another function.

Lambda expressions are always associated with interfaces that have a single ABSTRACT
method which is are functional interfaces.

The block of code that you pass to your method will often have paramaters. The
method that you pass it to will supply the paramaters to the block of code.
Sometimes, you do not need to specify the type of the paramaters that you are
passing in. If you have more than one paramater, then you must sourround your
paramaters in their own set of paranthesis.

Variables declared in a method with an anoonymous class are effectively final
and can not be re-assigned in the anonymous class. However, you can decalre another
variable of with the same name of the effectively final variable in the anonymous
class because anonymous classes have their own scope. HOWEVER, you CAN NOT declare
variables with the same name in a lambda expression because of the scope.

People say lambda expressions open the door up to funcitonal programming where
you are passing functions around.

*/
interface Exexcutable {
    int execute(int a, int b);
}

interface StringExecutable {
    int execute(String a);
}
class Runner {
    public void run(Exexcutable e) { //Runner now takes in an object that implements the Exexcutable interface. It's going to know that object has the execute().
        System.out.println("Executing run in Runner");
        int value = e.execute(12, 13);
        System.out.println("Return value is: " + value);
    }

    public void run(StringExecutable e) { //Runner now takes in an object that implements the Exexcutable interface. It's going to know that object has the execute().
        System.out.println("Executing run in Runner");
        int value = e.execute("Yamin");
        System.out.println("Return value is: " + value);
    }
}
class Driver {

    public static void main(String[] args) {

        int c = 100; //This variable is effectively final and can not be changed

        Runner runner = new Runner();
        runner.run(new Exexcutable() {
            @Override
            public int execute(int a, int b) {
                System.out.println("Hello there."); //You are passing this to the
                return a + b + c;
            }
        });
        System.out.println("=========================>");

        //Different ways of implementing a lambda expression are listed below.

        //runner.run(() -> System.out.println("Hello there")); //This does the same thing as the anonoymous class above.

        //If you want to execute more statements then you must have a code block.

        /*runner.run(() -> {
            System.out.println("This is the code passed in the lambda expession");
            System.out.println("Hello there");
        });*/

        //runner.run(() -> 8); //For a single statement, you do not need the return type.

        /*runner.run((int a) -> {
            System.out.println("Hello there");
            return 7 + a;
        });*/

        /*runner.run((a) -> {
            System.out.println("Hello there");
            return 7 + a;
        });*/

        runner.run((a,b) -> {
            System.out.println("Hello there");
            return a + b;
        });

        Executing ex = (a,b) -> {
            System.out.println("Hello there");
            return a + b;
        };

        runner.run(ex);

        System.out.println("=========================>");

        Object cedeblock = (Exexcutable)(a,b) -> {
            System.out.println("Hello there");
            return a + b;
        };

    }
}
