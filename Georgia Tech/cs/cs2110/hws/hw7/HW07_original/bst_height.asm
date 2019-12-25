;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author:
;; Date:
;; Assignment: Homework 07 (Part 3)
;; Description: BST height
;;======================================================================

.orig x3000

MAIN
LD R6, STACK

;; CALL HEIGHT AND STORE ANSWER HERE :D

HALT

STACK .fill xF000
ARRAY .fill x6000   ; Address of the array
INDEX .fill 1       ; Starting index
ANSWER .blkw 1      ; Save your answer here

HEIGHT

;; YOUR CODE HERE :D

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
