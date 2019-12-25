/**
 * CS 2110 Summer 2017 HW2
 * Part 1 - Coding a bit vector
 *
 * @author Yamin Mousselli
 *
 * Global rules for this file:
 * - You may not use multiplication, division or modulus in any method.
 * - You may not use more than 2 conditionals per method, and you may only use
 *   them in select methods. Conditionals are if-statements, if-else statements,
 *   or ternary expressions. The else block associated with an if-statement does
 *   not count toward this sum.
 * - You may not use looping constructs in most methods. Looping constructs
 *   include for loops, while loops and do-while loops. See below for exceptions
 * - In those methods that allow looping, you may not use more than 2 looping
 *   constructs, and they may not be nested.
 * - You may not declare any file-level variables.
 * - You may not declare any objects, other than String in select methods.
 * - You may not use switch statements.
 * - You may not use casting.
 * - You may not use the unsigned right shift operator (>>>)
 * - You may not write any helper methods, or call any other method from this
 *   file, another file, or the Java API to implement any method.
 * - You may only perform addition or subtraction with the number 1.
 *
 * Method-specific rules for this file:
 * - You may declare exactly one String variable, in toString only.
 * - Iteration may not be used in set, clear, toggle, isSet or isClear.
 * - Conditionals may not be used in set, clear, or toggle.
 */
public class BitVector {
    /**
     * 32-bit data initialized to all zeros. Here is what you will be using to represent
     * the Bit Vector. Do not change its scope from private.
     */
    private int bits;

    /** You may not add any more fields to this class other than the given one. */

    /**
     * Sets the bit (sets to 1) pointed to by index.
     * @param index index of which bit to set.
     *        0 for the least significant bit (right most bit).
     *        31 for the most significant bit.
     */
    public void set(int index) {
        /*
        0x1 is a  1 in 32 bits 0000000000000000000000000000001. The left
        shift moves the right most bit a certain amount of bits, in this case
        by the value of index, so that way the 1 is in aligned in the
        position of the bit that you want to set, using the bitwise
        operator OR, within bits.
        */
        bits = bits | (0x1 << index);
    }

    /**
     * Clears the bit (sets to 0) pointed to by index.
     * @param index index of which bit to set.
     * 	      0 for the least significant bit (right most bit).
     * 	      31 for the most significant bit.
     */
    public void clear(int index) {
       /*
        0x1 is a  1 in 32 bits 0000000000000000000000000000001. The left
        shift moves the right most bit a certain amount of bits, in this case
        by the value of index, so that way the 1 is in aligned in the
        position of the bit that you want to clear, using the bitwise
        operator AND, within bits.
        */
        bits = bits & ~(0x1 << index);
    }

    /**
     * Toggles the bit (sets to the opposite of its current value) pointed to by index.
     * @param index index of which bit to set.
     * 	      0 for the least significant bit (right most bit).
     * 	      31 for the most significant bit.
     */
    public void toggle(int index) {
        /*
        0x1 is a  1 in 32 bits 0000000000000000000000000000001. The left
        shift moves the right most bit a certain amount of bits, in this case
        by the value of index, so that way the 1 is in aligned in the
        position of the bit that you want to toggle, using the bitwise
        operator XOR, within bits.
        */
        bits = bits ^ (0x1 << index);
    }

    /**
     * Returns true if the bit pointed to by index is currently set.
     * @param index index of which bit to check.
     * 	      0 for the least significant bit (right-most bit).
     * 	      31 for the most significant bit.
     * @return true if the bit is set, false if the bit is clear.
     *         If the index is out of range (index >= 32), then return false.
     */
    public boolean isSet(int index) {
        /*
         Instead of evaluating for the condition that is false, you want to
         evaluate where the statement is true so you can evaluate the second
         part of the conditional. I need to watch parenthesis because of how
         the expression is evaluated (otherwise, Java will complain of
         bitwise operator when you need to return a boolean).

         This is first checking to see if the index that you wish to check
         within the 32 bit integer, bits, is not out of bounds and then it
         left shifts the 1 and AND's it to see if the bit is a 1 or a 0.
         return true if it's a 1.
         For example, to check index 3: 1011
                                        0001
         is left shifted to: 1011
                             0100

                        when evaluating &, it will return 0 because the
                        specified bit within bits is not a 1.
         */
        return (index < 32) && ((bits & (0x1 << index)) != 0);
    }

    /**
     * Returns true if the bit pointed to by index is currently clear.
     * @param index index of which bit to check.
     * 	      0 for the least significant bit (right-most bit).
     * 	      31 for the most significant bit.
     * @return true if the bit is clear, false if the bit is set.
     *         If the index is out of range (index >= 32), then return true.
     */
    public boolean isClear(int index) {

        /*
        This is just like the method above except that it checks the reverse
        condition. This method is true if your index is past 32 because all
        bits past index 31 are clear (set to 0). Moreover, If you negate
        (!=0) than you will get true and false when it is clear. Tricky but
        clear.
         */
        return !((index < 32) && ((bits & (0x1 << index)) != 0));
    }

    /**
     * Returns a string representation of this object.
     * Return a string with the binary representation of the bit vector.
     * You may use String concatenation (+) here.
     * You must return a 32-bit string representation.
     * i.e if the bits field was 2, then return "00000000000000000000000000000010"
     */
    public String toPaddedBinaryString() {
        String toBeReturned = "";
         for (int index = 0; index < 32; index++) {
             //using isSet() logic right here. Append it to the front
             if ((bits & (0x1 << index)) != 0) {
                 toBeReturned = "1" + toBeReturned;
             } else {
                 toBeReturned = "0" + toBeReturned;
             }
        }
        return toBeReturned;
    }

    /**
     * Returns the number of bits currently set (=1) in this bit vector.
     * You may obviously use the ++ operator to increment your counter.
     */
    public int onesCount() {
        int onesCounter = 0;

        for (int index = 0; index < 32; index++) {
            //using isSet() logic
            if (((bits & (0x1 << index)) != 0)) {
                onesCounter++;
            }
        }
        return onesCounter;
    }


    /**
     * Returns the number of bits currently clear (=0) in this bit vector.
     * You may obviously use the ++ operator to increment your counter.
     */
    public int zerosCount() {
        int zerosCounter = 0;

        for (int index = 0; index < 32; index++) {
            //using isSet() logic
            if (!((bits & (0x1 << index)) != 0)) {
                zerosCounter++;
            }
        }
        return zerosCounter;
    }

    /**
     * Returns the "size" of this BitVector. The size of this bit vector is defined
     * to be the minimum number of bits that will represent all of the ones.
     * For example, the size of the bit vector 00010000 will be 5.
     */
    public int size() {
        int minBits = 0;

        for (int index = 0; index < 32; index++) {
            if ((bits & (0x1 << index)) != 0) {
                minBits = index;
            }
        }
        minBits++;
        return minBits;
    }
}