;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 6/25/2017
;; Assignment: Homework 07 (Part 3)
;; Description: BST height
;;======================================================================

.orig x3000

MAIN
LD R6, STACK

;; CALL HEIGHT AND STORE ANSWER HERE :D
LD R0, ARRAY ;;LOAD POINTER TO ARRAY
LD R1, INDEX ;;LOAD INDEX ARG
ADD R6, R6, #-2 ;;MAKE SPACE ON THE STACK FOR 2 ARGS
STR R1, R6, 1 ;;PUSH INDEX ON FIRST
STR R0, R6, 0 ;;PUSH ARRAY POINTER ON TOP
JSR HEIGHT ;; CALL HEIGHT SUBROUTINE
LDR R0, R6, 0 ;;LOAD RETURN VALUE FROM TOP OF STACK
ADD R6, R6, 3 ;;POP RVAL AND ARGS FROM THE STACK
ST R0, ANSWER ;;STORE ANSWER
HALT

STACK .fill xF000
ARRAY .fill x6000   ; Address of the array
INDEX .fill 1       ; Starting index
ANSWER .blkw 1      ; Save your answer here

HEIGHT

;; YOUR CODE HERE :D
;;STACK SETUP
ADD R6, R6, #-3 ;; MAKE STACK SPACE FOR RET VAL, RET ADDR, OLD FRAME POINTER
STR R7, R6, #1 ;; STORE RET ADDRESS ON STACK
STR R5, R6, #0 ;; STORE OLD FRAME POINTER ON THE STACK
ADD R5, R6, #-1 ;; SET NEW FRAME POINTER
ADD R6, R6, #-2 ;; MAKE SPACE FOR LOCALS

;;logic begins
AND R2, R2, #0 ;;CLEAR R2 AND USE AS LEFTHGHT
AND R3, R3, #0 ;;CLEAR R3 AND USE AS RIGHTHEIGHT
STR R2, R5, #0 ;;STORE LOCAL 1 AT FP=R5
STR R3, R5, #-1 ;;STORE LOCAL 2 1 ABOVE FP=R5-1
LDR R0, R5, #4 ;; LOAD ARR POINTER INTO R0
LDR R1, R5, #5 ;;LOAD INDEX INTO R1
ADD R4, R0, R1
LDR R4, R4, 0
BRNP NON_ZERO
STR R4, R5, 3 ;;NODE = 0 SO RETURN HEIGHT = 0
BR CLEANUP
NON_ZERO
;;RECURSE AND GET LEFTHEIGHT
ADD R6, R6, #-2 ;;MAKE SPACE ON STACK FOR 2 ARGS
ADD R1, R1, R1
STR R1, R6, 1 ;;PUSH ARGS ON IN REV ORDER FIRST IS INDEX
STR R0, R6, 0 ;;PUSH ARR PTR
JSR HEIGHT ;;RECURSE ON LEFT SUBTREE
LDR R2, R6, 0 ;;R2 = RET VALUE
STR R2, R5, 0 ;;UPDATE LEFTHEIGHT ON STACK
ADD R6, R6, #3 ;;POP THE RET VALUE

;;NOW RECURSE ON RIGHT SUBTREE
LDR R0, R5, #4 ;; LOAD ARR POINTER INTO R0
LDR R1, R5, #5 ;;LOAD INDEX INTO R1
ADD R4, R1, R1
ADD R4, R4, 1
ADD R6, R6, #-2 ;;MAKE STACK SPACE FOR 2 ARGS
STR R4, R6, 1 ;;PUSH IN REV ORDER
STR R0, R6, 0 ;;PUSH ARR PTR
JSR HEIGHT ;;RECURSE ON RIGHT SUBTREE
LDR R3, R6, 0
STR R3, R5, -1 ;;UPDATE RIGHTHEIGHT ON STACK
ADD R6, R6, 3 ;;POP RET VALUE

;;LH - RH IS POSITIVE -> LH > RH
LDR R2, R5, 0 ;;LOAD LEFTHEIGHT
LDR R3, R5, -1 ;; LOAD RIGHTHEIGHT
NOT R3, R3 ;;NOT R3 FOR SUBTRACTION
ADD R3, R3, 1 ;;ADD 1 FOR 2S COMPLEMENT
ADD R4, R2, R3
BRNZ RIGHT_PLUS_1
LDR R2, R5, #0 ;;RELOADING LEFTHEIGHT
ADD R2, R2, 1
STR R2, R5, 3 ;;STORE THE RETURN VAL ON THE STACK
BR CLEANUP
RIGHT_PLUS_1 LDR R3, R5, #-1 ;; RELOAD RIGHTHEIGHT
ADD R3, R3, 1
STR R3, R5, 3 ;;STORE RET VAL ON STACK
BR CLEANUP ;;JUST PUTTING FOR CONFORMITY

CLEANUP
ADD R6, R5, #1 ;; POP LOCALS
LDR R5, R6, #0 ;;RESTORE CALLER'S FRAME POINTER
ADD R6, R6, #1 ;;MOVE SP TO RET ADDR
LDR R7, R6, #0 ;;POP AND LOAD RETURN ADDRESS
ADD R6, R6, #1 ;;MOVE SP THE RV
RET
.end
;;======================================================================
;; You should not modify any code beyond this point
;;======================================================================

.orig x6000
.fill 0
.fill 8     ;row 1
.fill 0     ;row 2
.fill 10
.fill 0     ;row 3
.fill 0
.fill 0
.fill 0
.fill 0     ;row 4
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
.fill 0
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
