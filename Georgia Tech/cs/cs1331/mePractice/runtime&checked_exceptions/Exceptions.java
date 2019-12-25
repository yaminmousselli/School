class Exceptions {

    /*
    There are two basic kind of exceptions. Checked exceptions are the exceptions that you are forced to handle at compile-time. Think Compile-time = syntax
    Run-time exceptions are exceptions that you aren't forced to handle and that crash your program at run-time.
    */

    public static void main(String[ ]args) {
        //int value = 7;
        //value = value / 0; //This is a run-time exception (specifically an Arthimeic exception). Run-time exception is a child class of java.lang.RuntimeException

        //String text = null;
        //System.out.println(text.length()); //This will result in a null pointer exception because you don't have text referencing anything.
                                             //This is a compile-time error.

        String texts = "one, two, three";

        try {
            prinl(texts);
            System.out.println(result); //This will result in an ArrayOutOfBounds Exception which is a runtime exception.
        }
        catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public static String print(String texts) {
        if (texts.length > 2) {
            throw new Exception("Our of Bounds Error");
        }
        texts = "one, two, four";
        return texts;
    }
}
