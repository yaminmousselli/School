/**
 * CS 2110 Summer 2017 HW2
 * Part 2 - Coding with bases
 *
 * @author Yamin Mousselli
 *
 * Global rules for this file:
 * - You may not use more than 2 conditionals per method. Conditionals are
 *   if-statements, if-else statements, or ternary expressions. The else block
 *   associated with an if-statement does not count toward this sum.
 * - You may not use more than 2 looping constructs per method. Looping
 *   constructs include for loops, while loops and do-while loops.
 * - You may not use nested loops.
 * - You may not declare any file-level variables.
 * - You may not declare any objects, other than String in select methods.
 * - You may not use switch statements.
 * - You may not use the unsigned right shift operator (>>>)
 * - You may not write any helper methods, or call any other method from this or
 *   another file to implement any method.
 * - The only Java API methods you are allowed to invoke are:
 *     String.length()
 *     String.charAt()
 * - You may not invoke the above methods from string literals.
 *     Example: "12345".length()
 * - When concatenating numbers with Strings, you may only do so if the number
 *   is a single digit.
 *
 * Method-specific rules for this file:
 * - You may not use multiplication, division or modulus in any method, EXCEPT
 *   decimalStringToInt.
 * - You may declare exactly one String variable each in intToBinaryString and
 *   and intToHexString.
 */
public class Bases {
    /**
     * Convert a string containing ASCII characters (in binary) to an int.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid binary numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: binaryStringToInt("111"); // => 7
     */
    public static int binaryStringToInt(String binary) {
        int decimalRepresentation = 0;
        /*
        farRightIndex is the powers of two for what we are adding to our int
        at the position of the farLeftIndex.
         */
        int farRightIndex = 0;
        /*
        farLeftIndex is the position in the string where the power of two
        is. Remember that in a string the right most bit is the last
        character of the string and that is where the powers of two start.
        */

        for (int farLeftIndex = binary.length() - 1; farLeftIndex >= 0;
             farLeftIndex--) {
            if (binary.charAt(farLeftIndex) != '0') {
                decimalRepresentation += (0x1 << farRightIndex);
            }
            farRightIndex++;
        }
        return decimalRepresentation;
    }

    /**
     * Convert a string containing ASCII characters (in decimal) to an int.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid decimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: decimalStringToInt("123"); // => 123
     */
    public static int decimalStringToInt(String decimal) {
        int decimalRepresentation = 0;
        //REMEMBER STRINGS INDEX 0 START FROM THE RIGHT. THEY ARE NOT NUMBERS
        for (int farLeftIndex = 0; farLeftIndex < decimal.length();
             farLeftIndex++) {
            char currentChar = decimal.charAt(farLeftIndex);
            decimalRepresentation = (decimalRepresentation * 10) +
                    (currentChar - '0');
        }
        return decimalRepresentation;
    }

    /**
     * Convert a string containing ASCII characters (in hex) to an int.
     * The input string will only contain numbers and uppercase letters A-F.
     * You do not need to handle negative numbers. The Strings we will pass in will be
     * valid hexadecimal numbers, and able to fit in a 32-bit signed integer.
     *
     * Example: hexStringToInt("A6"); // => 166
     */
    public static int hexStringToInt(String hex) {
        int decimalRepresentation = 0;

        for (int farLeftIndex = 0; farLeftIndex < hex.length(); farLeftIndex++)
        {
            /*
            resetting the decimal to be itself * 16. Remember left shift is
            for multiplication
            */
            decimalRepresentation = (decimalRepresentation << 4);
            char currentChar = hex.charAt(farLeftIndex);

            if (currentChar >= 'A') {
                //I'm converting the characters hex value into a decimal. We
                // add 10 due to the gap because we end up subtracting too much.
                decimalRepresentation += currentChar - 'A' + 10;

            } else {
                decimalRepresentation += currentChar - '0';
            }
        }
        return decimalRepresentation;
    }

    /**
     * Convert a int into a String containing ASCII characters (in binary).
     * You do not need to handle negative numbers.
     * The String returned should contain the minimum number of characters necessary to
     * represent the number that was passed in.
     *
     * Example: intToBinaryString(7); // => "111"
     */
    public static String intToBinaryString(int binary) {
        String binaryRepresentation = "";
        //the case where the the string is 0.
        if (binary == 0) {
            return "0";
        }
        //Essentially performing the division algorithm
        for (int i = 0; binary != 0; i++) {
            int currentDigit = 0x1 & binary;
            binaryRepresentation = currentDigit + binaryRepresentation;
            binary = (binary >> 1);
        }
        return binaryRepresentation;
    }

    /**
     * Convert an int into a String containing ASCII characters (in
     * hexadecimal).
     * The output string should only contain numbers and uppercase letters A-F.
     * You do not need to handle negative numbers.
     * The String returned should contain the minimum number of characters necessary to
     * represent the number that was passed in.
     *
     * Example: intToHexString(166); // => "A6"
     */
    public static String intToHexString(int hex) {
        String hexRepresentation = "";
        if (hex == 0) {
            return "0";
        }
        for (int i = 0; hex != 0; i++) {
            int currentDigit = 0xF & hex;

            if (currentDigit >= 10) {
                hexRepresentation = (char) (('A' + currentDigit) - 10) +
                        hexRepresentation;
            } else {
                hexRepresentation = (char) (currentDigit + '0') +
                        hexRepresentation;
            }
            hex = hex >> 4;
        }
        return hexRepresentation;
    }
}
