/*
    The stack stores local variables and remembering which method called which
    subroutine. The stack is small section of memory that remembers function calls
    and local variables. The heap is an area of memory where objects are allocated
    when you use the new operator.

    The problem with recursion that you want to avoid is StackOverFlowExceptions()
    which means you have run out of memory on the stack.


*/
public class Driver {

    public static void main(String[] args) {
        System.out.println(factorial(4)); //The end result is printed here
    }
    
    private static int factorial(int value) {
        System.out.println(value);

        if (value == 0) {
            return 1;
        }
        return factorial(value - 1) * value; //these values are stored on the stack
    }
}
