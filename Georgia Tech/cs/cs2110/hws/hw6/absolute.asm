;;======================================================================
;; CS2110                     Homework 06                   Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 20 June 2017
;; Assignment: Homework 06
;; Description: An assembly program that determines the absolute value
;;              of a given number
;;======================================================================

.orig x3000

        AND R0, R0, #0 ;Clears the register.
        LD R0, U       ;This is loading the current value of U in R0.

        ;Don't need an ADD statement since we are not incrementing anything.

        BRN NEGATIVE
        BRZP ELSE      ;this can be positive or zero for the branch.
                       ;no need to have BRZ, just add Z above.


        NEGATIVE        ;This will execute the branch (BRN)
        NOT R0, R0      ;Whenever you negate, you're performing 1's complement
        ADD R0, R0, #1  ;Adding 1 to R0 for 2's complement.

        ST R0, ANSWER   ;Store the number in the label ANSWER.
        HALT

        ELSE            ;The case where U is zero or positive
        ST R0, ANSWER   ;Storing R0 contents into label ANSWER.
        HALT

U       .fill 20
ANSWER  .fill 0

.end
