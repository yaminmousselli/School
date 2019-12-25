;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 25 June 2017
;; Assignment: Homework 07 (Part 2)
;; Description: Powers of 2
;;======================================================================
;;UP IS NEGATIVE, DOWN IS POSITIVE

.orig x3000
MAIN
LD R6, STACK

;; CALL POWERSOF2 AND STORE ANSWER HERE :D

        LD R0, N
        ADD R6, R6, #-1 ;; Decrementing SP by 1
        STR R0, R6, #0  ;; Pushing the parameter onto the stack. SP points to N.
        JSR POWERSOF2  ;; Stores the PC in R7 so you can return to it later.

;; Pop the return value and arg off the stack
        LDR R0, R6, #0
        ADD R6, R6, #2 ; Popping RV and Argument ;

;;Save the answer
        ST R0, ANSWER
        HALT


STACK .fill xF000
N .fill 2             ; Input N
ANSWER .blkw 1        ; Save your answer here


POWERSOF2
        ;; Offset everything by an additional 2 to account for the 2 local vars.
        LDR R0, R6, #0      ;; Load arg into R0
        ADD R6, R6, #-5	    ;; Make space for RA, OFP, RV, and 2 local vars.
        STR R7, R6, #3	    ;; Store RA
        STR R5, R6, #2	    ;; Store old FP
        ADD R5, R6, #1	    ;; Setup new FP

        ADD R0, R0, 0       ;; Branch Check
        BRZ N_IS_ZERO

        ADD R1, R0, #-1      ;; Subtract 1 and see if it's equal to 0
        BRZ N_IS_ONE

        BRNZP N_REST

    N_IS_ZERO
        ADD R6, R6, #2       ;; Pop off your local variables FIRST
        ADD R0, R0, #1       ;; This is the proper way to add 1, NOT LD
        ;; LD R0, #1            ;; DON'T DO THIS B.S.
        STR R0, R5, #3       ;; Pushing the RV onto the stack
        LDR R7, R5, #2       ;; Loading the RA from the stack
        LDR R5, R5, #1       ;; Restore the OLD FP and then move the SP

        ;;CALLER IS responsible for popping off the arg from the stack.
        ADD R6, R6, #2       ;; Popping off FP and SP.
        RET                  ;; Return which is the same as JMP R7


    N_IS_ONE
        ADD R6, R6, #2       ;; Pop off your local variables FIRST
        ADD R0, R0, #1       ;; This is the proper way to add 1 to R0, NOT LD.
        ;;LD R0, #2            ;; CAN NOT DO THIS B.S.
        STR R0, R5, #3       ;; Pushing the RV onto the stack
        LDR R7, R5, #2       ;; Loading the RA from the stack
        LDR R5, R5, #1       ;; Restore the OLD FP and then move the SP

        ;;CALLER IS responsible for popping off the arg from the stack.
        ADD R6, R6, #2       ;; Popping off FP and SP.
        RET                  ;; Return which is the same as JMP R7

    N_REST
        ADD R6, R6, #-1     ;; Growing the stack so it's positive.
        ADD R0, R0, #-1     ;; powersOf2(n-1), this is the n-1 part
        STR R0, R6, #0      ;; store onto the stack

        JSR POWERSOF2       ;; RECURSE
        LDR R0, R6, #0
        STR R0, R5, #0      ;;updating temp1

        ADD R6, R6, #2      ;;setting up process for temp2

        ;;WORKING ON TEMP2 below

        LDR R0, R5, #4      ;; Must reload R0 for the other recursive call
        ADD R6, R6, #-1     ;; Growing the stack for temp2
        ADD R0, R0, #-2     ;; PowersOf2(n-1), this is the n- part
        STR R0, R6, #0      ;; Store onto the stack

        JSR POWERSOF2       ;; RECURSE
        LDR R0, R6, #0      ;; Load the return value
        STR R0, R5, #-1     ;; fetching temp2
        ADD R6, R6, #2      ;; Decrementing SP by 2

        ;; The next statement is multiplying temp2 by 2
        LDR R0, R5, #-1      ;; Loading the first return value which is temp2
        ADD R0, R0, R0       ;; Multiplying temp2 by 2 ; MISTAKE.
        NOT R0, R0           ;; Making this negative for order of opreations
        ADD R0, R0, #1       ;; Don't FORGET 2's complement, DON'T

        ;;LD R, #0            ;; This is so R1'S VALUE doesn't change.
        LDR R1, R5, #0       ;; Loading temp1 into R1

        ;;R1 is temp1, R0 is temp2

        ;;Multiply by 3
        ADD R2, R1, R1
        ADD R2, R2, R1

        ADD R3, R2, R0      ;; RETURN VALUE, THIS IS THE ARTHIMETIC. mistake

        ;;TEAR DOWN PROCESS 
        ADD R6, R6, #2    ;; Pop 4 things off because of 2 calls with 1 arg each
        STR R3, R5, #3      ;; Putting the RV on the stack
        LDR R7, R5, #2      ;; Loading the return address
        LDR R5, R5, #1      ;; Restore the old FP
        ADD R6, R6, #2      ;; Popping the return value and frame pointer

        RET                 ;; Same as JMP R7

.end
