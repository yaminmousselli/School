;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 25 June 2017
;; Assignment: Homework 07 (Part 1)
;; Description: McCarthy 91
;;======================================================================

;;Decrementing by 1 and storing a value is the process of pushing
;;We want SP to point to the last parameter.
;;R7 will ONLY be used for the return address.
;;JSR always overwrites the value in R7, that's why you save the value
;;If it's caller-saved then save registers, expect the register to have the
;;same value. Must save it to the stack.
;;Can't use R5-R7. R7-> Return Address, R6->SP, R5->Frame Pointer

;;In LC-3, all register are caller-saved so we don't have to save regs onto ;;Stack


.orig x3000
MAIN
LD R6, STACK ;; Initializing the Stack


;; CALL MC91 AND STORE ANSWER HERE :D

;; Call mc91(int n)
        LD R0, N
        ADD R6, R6, #-1 ;; Decrementing SP by 1
        STR R0, R6, #0  ;; Pushing the parameter onto the stack. SP points to N.
        JSR MC91        ;; Stores the PC in R7 so you can return to it later.

;; Pop the return value and arg off the stack
        LDR R0, R6, #0
        ADD R6, R6, #2 ; Popping RV and Argument


;;Save the answer
        ST R0, ANSWER
        HALT

STACK .fill xF000
N .fill 99          ; Input N
ANSWER .blkw 1      ; Save your answer here


MC91

    LDR R0, R6, #0      ;; Load arg into R0
    ADD R6, R6, #-3	    ;; Make space for OFP, RA, and RV
    STR R7, R6, #1	    ;; Store RA. 2 less than |-3|
    STR R5, R6, #0	    ;; Store old FP
    ADD R5, R6, #-1	    ;; Setup new FP


;;Subtract 100 to see if n is greater than 100.
    ADD R1, R0, #-15
    ADD R1, R1, #-15
    ADD R1, R1, #-15
    ADD R1, R1, #-15
    ADD R1, R1, #-15
    ADD R1, R1, #-15
    ADD R1, R1, #-10

    BRNZ UNDER_1HUNDRED ;; Everything after this branch will be the other case

    ;; IF BLOCK--> greater than 100

    ADD R0, R0, #-10    ;; Subtracting 10 from N and storing it in R0
    STR R0, R5, #3      ;; Storing return value where it belongs through offset
    LDR R7, R5, #2      ;; Loading the return address into R7
    LDR R5, R5, #1      ;; restore the old FP back into the FP register, R5.
    ADD R6, R6, #2      ;; popping off Old FP, RA, and RV AND
                        ;; Decrementing SP to store the return value

    JMP R7 ;;This jumps to return address and executes next Inst.AfterJSSRinMain
           ;;When you JMP, you're no longer in this function.

UNDER_1HUNDRED
    ;; ret = mc91(n+11)
    ;; return mc91(ret)


    ADD R0, R0, #11     ;; Adding 11
    ADD R6, R6, #-1     ;; Decrementing SP by 1
    STR R0, R6, #0      ;; Pushing R0 onto the stack. SP points to R0.
    JSR MC91            ;; Stores the PC in R7
    JSR MC91 ;; This is calling the function with return value of prev. JSR call
    LDR R0, R6, #0      ;; loading the return value at SP into R0.

    ;; This is function pipelining. Call the second JSR for the outer recursive ;; call


    ;;Since I called mc91 twice, there are 2 return values that I need to pop
    ;;off the stack because we reused one of the return values as the argument
    ;;and one argument that we passed in to the function

    ADD R6, R6, #3     ;; Shrink SP by 3 to pop off the three arguments.
    STR R0, R5, #3     ;; Storing return value in R0.
    LDR R7, R5, #2     ;; Loading the return address into R7
    LDR R5, R5, #1     ;; restore the old FP back into the FP register, R5.
    ADD R6, R6, #2     ;; removing the RA and old FP by moving SP up by 2

    RET            ;; This is the same as JMP R7.

.end
