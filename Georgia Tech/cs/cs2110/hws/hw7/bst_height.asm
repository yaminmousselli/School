;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 25 June 2017
;; Assignment: Homework 07 (Part 3)
;; Description: BST height
;;======================================================================

.orig x3000
MAIN
LD R6, STACK

;; CALL HEIGHT AND STORE ANSWER HERE :D
        LD R1, INDEX        ;; last argument goes in first
        LD R2, ARRAY        ;; first argument goes in second, which is on top
        ADD R6, R6, #-2     ;; Decrementing SP by 2
        STR R1, R6, #1      ;; Pushing INDEX in first
        STR R2, R6, #0      ;; Pushing ARRAY in second which SP points to it
        JSR HEIGHT      ;; Stores the PC in R7 so you can return to it later.

;; Pop the return value and args off the stack
        LDR R0, R6, #0  ; What does this do?
        ADD R6, R6, #3 ; Popping RV and Arguments

;;Save the answer
        ST R0, ANSWER
        HALT

STACK .fill xF000
ARRAY .fill x6000   ; Address of the array
INDEX .fill 1       ; Starting index
ANSWER .blkw 1      ; Save your answer here

HEIGHT

        LDR R1, R5, #1     ;; Load INDEX into R1
        LDR R2, R5, #0      ;; Load ARRAY into R2
        ADD R6, R6, #-5	    ;; Make space for RA, OFP, RV, and 2 locals.
        STR R7, R6, #3	    ;; Store RA
        STR R5, R6, #2	    ;; Store old FP
        ADD R5, R6, #1	    ;; Setup new FP

        ADD R0, R1, R2      ;; Condition to see if arr + index == 0

        LDR R3, R0, #0      ;; loading the data from the new index we computed.

        BRZ IS_ZERO
;        BRNZP ELSE          ;;starting at leftheight

IS_ZERO
        ADD R6, R6, #2       ;; Pop off your local variables FIRST
        AND R0, R0, #0       ;; Clear R0; R0 == 0
        STR R0, R5, #3       ;; Pushing the RV onto the stack
        LDR R7, R5, #2       ;; Loading the RA from the stack
        LDR R5, R5, #1       ;; Restore the OLD FP and then move the SP
        ;;CALLER IS responsible for popping off the arg from the stack.
        ADD R6, R6, #2       ;; Popping off FP and SP.
        RET                  ;; Return R0 == 0

;ELSE
        ;ADD R6, R6, #-1     ;; Moving SP up
        ;;;;;;;;;ADD R3, R1, R1      ;; Multiplying INDEX by 2.
        ;STR R3, R6, #0      ;; SP points to value of 2 * INDEX

        ;JSR HEIGHT          ;; RECURSE
        ;LDR R3, R6, #0      ;; RELOAD AFTER RECURSE
        ;LDR R0, R6, #0      ;; Load the return value from the result in R6
        ;STR R0, R5, #0      ;; Storing LEFT_HEIGHT in R5 AT FRAME POINTER
        ;ADD R6, R6, #2      ;; Setting up process for RIGHT_HEIGHT

        ;;Working on RIGHT_HEIGHT below

        ;LDR R2, R5, #4      ;; Loading the array pointer at R2
        ;ADD R6, R6, #-1     ;; Growing the stack for RIGHT_HEIGHT
        ;ADD R4, R3, R3      ;; Multiplying INDEX by 2
        ;ADD R3, R4, #1      ;; Adding 1 to INDEX * 2
        ;STR R4, R6, #0      ;; Store the result on the stack

        ;JSR HEIGHT
        ;LDR R0, R6, #0      ;; Loading RIGHT_HEIGHT from second recurse
        ;STR R0, R5, #-1     ;; Storing value onto stack
        ;; DIDN'T INCLUDE DECREMENT ON SP HERE LIKE POWERSOF2

        ;;PREPARING VALUES FOR THE SECOND IF STATEMENT

        ;AND R4, R4, #0      ;; Clearing R4 for use
        ;LDR R4, R5, #-1     ;; loading RIGHT_HEIGHT into R4
        ;NOT R4, R4          ;; 1's comp on RIGHT_HEIGHT
        ;ADD R4, R4, #1      ;; Adding 1 for 2's comp. on RIGHT_HEIGHT
        ;AND R3, R3, #0      ;; Clearing R3 to load LEFT_HEIGHT into it
        ;LDR R3, R5, #0      ;; Loading LEFT_HEIGHT to make comparison

        ;ADD R3, R3, R4      ;; The condition for the if statement
        ;BRP LH_BIG
        ;BRNZ RH_BIG

;LH_BIG
        ;AND R3, R3, #0      ;; Clearing R3 for LEFT_HEIGHT
        ;LDR R3, R5, #0      ;; Loading LEFT_HEIGHT to add 1
        ;ADD R0, R3, #1      ;; Adding 1 to LEFT_HEIGHT

        ;RET                 ;; Same as JMP R7

;RH_BIG
        ;AND R3, R3, #0      ;; Clearing R3 for LEFT_HEIGHT
        ;LDR R3, R5, #-1     ;; Loading LEFT_HEIGHT to add 1
        ;ADD R0, R3, #1      ;; Adding 1 to LEFT_HEIGHT

        ;RET                 ;; Same as JMP R7


        ;;TEAR DOWN PROCESS
        ;ADD R6, R6, #2    ;; Pop 5 things off because of 2 calls with 1 arg each
        ;STR R3, R5, #3      ;; Putting the RV on the stack
        ;LDR R7, R5, #2      ;; Loading the return address
        ;LDR R5, R5, #1      ;; Restore the old FP

        ;; CHANGES FOR ADDITIONAL ARGUMENT
        ;ADD R6, R6, #2     ;; Popping the return value and frame pointer

        ;RET                 ;; Same as JMP R7


.end ;;Piazza post
;;======================================================================
;; You should not modify any code beyond this point
;;======================================================================

.orig x6000
.fill 0
.fill 8     ;row 1
.fill 3     ;row 2
.fill 10
.fill 1     ;row 3
.fill 6
.fill 0
.fill 14
.fill 0     ;row 4
.fill 0
.fill 4
.fill 7
.fill 0
.fill 0
.fill 13
.fill 0
.fill 0     ;row 5
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.end
