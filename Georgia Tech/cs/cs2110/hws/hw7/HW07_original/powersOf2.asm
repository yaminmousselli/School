;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author:
;; Date:
;; Assignment: Homework 07 (Part 2)
;; Description: Powers of 2
;;======================================================================

.orig x3000

MAIN
LD R6, STACK

;; CALL POWERSOF2 AND STORE ANSWER HERE :D

STACK .fill xF000
N .fill 5             ; Input N
ANSWER .blkw 1        ; Save your answer here

POWERSOF2

;; YOUR CODE HERE :D

.end
