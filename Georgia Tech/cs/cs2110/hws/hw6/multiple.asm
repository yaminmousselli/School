;;======================================================================
;; CS2110                      Homework 06                   Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 06/20/2017
;; Assignment: Homework 06
;; Description: An assembly program that determines whether one number
;;              is a multiple of another number
;;======================================================================

;;You can't directly compare numbers in assembly. The way to check if A > B is ;;to negate B and add A and B. If the result is positive, than you know A is ;;bigger than B. It wouldn't make sense to negate A and then add to B because ;;what if A was 2 and B was 1? You'd get a negative number which is not the ;;logic we are looking for.

;;The WHILE label goes on the condition!!! Do not use HALT in the while loop ;;because that would end the iteration! Don't be stupid!



.orig x3000

        AND R0, R0, #0       ;Clear the contents of R0
        LD R0, A             ;Loading R0 with contents at address A

        AND R1, R1, #0       ;Clear the contents of R1
        LD R1, B             ;Loading R1 with contents at address B

        AND R3, R3, #0       ;Clearing R3 to store result of A > B


        NOT R1, R1          ;This will negate contents at B
        ADD R1, R1, #1      ;Adding 1 to B for 2's comp. representation.
WHILE   ADD R0, R0, R1      ;Performing the addition of A and B and setting R0. 


        BRP WHILE
        BR NEXT
        NEXT

        BRZ EQUAL
        BRNP ELSE

        EQUAL
        ADD R3, R3, #1      ;Adding 0 to R3
        ST R3, ANSWER       ;Copying over the value in R3 to ANSWER.
        HALT

        ELSE
        ST R3, ANSWER       ;R3 is cleared so we are just storing 0 for ANSWER.
        HALT

A       .fill 15
B       .fill 5
ANSWER  .fill 0


.end
