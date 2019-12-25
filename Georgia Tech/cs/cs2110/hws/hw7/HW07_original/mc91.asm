;;======================================================================
;; CS2110                    Homework #07                    Summer 2017
;;======================================================================
;; Author:
;; Date:
;; Assignment: Homework 07 (Part 1)
;; Description: McCarthy 91
;;======================================================================

.orig x3000

MAIN
LD R6, STACK

;; CALL MC91 AND STORE ANSWER HERE :D

HALT

STACK .fill xF000
N .fill 99          ; Input N
ANSWER .blkw 1      ; Save your answer here

MC91

;; YOUR CODE HERE :D

.end
