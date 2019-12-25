;;======================================================================
;; CS2110                      Homework 06                   Summer 2017
;;======================================================================
;; Author: Yamin Mousselli
;; Date: 06/20/2017
;; Assignment: Homework 06
;; Description: An assembly program that prints a historical timeline to
;;              the console
;;======================================================================


;ALWAYS USE R0 FOR TRAPS.
;After every PUTS call

.orig x3000
        AND R0, R0, #0      ;Clearing R0 for use
        AND R1, R1, #0      ;Clearing R1 for use
        AND R2, R2, #0      ;Clearing R2 for use
        AND R3, R3, #0      ;Clearing R3 for use
        AND R4, R4, #0      ;Clearing R4 for use
        AND R5, R5, #0      ;Clearing R5 for use
        AND R6, R6, #0      ;Clearing R6 for use
        AND R7, R7, #0      ;Clearing R7 for use

        LD R1, N                ;Loading N into R0
        ADD R1, R1, #-1         ;Decrementing N

        BRZ N_IS_ZERO           ;Checking if N == 0
        BRNP FIRST_ELSE          ;This is when n isn't zero


        N_IS_ZERO

        LEA R0, BEFORE_MSG_2    ;Putting the String value in R0
        PUTS                    ;TRAP x22 that prints out value in R0.
                                ;R0 is always cleared after every PUTS call

        LEA R0, YEAR_ARR        ;Loads R0 with all strings in YEAR_ARR address
        LDR R0, R0, #0          ;load R0 with index 0 of YEAR_ARR
        PUTS                    ;Prints "Nothing happened before "

        LEA R0, NEWLINE         ;Loads R0 with a new line character
        PUTSP                   ;Creates a next line for the next printStatement
        BRNP CLOSE

        FIRST_ELSE

        LEA R0, BEFORE_MSG_1     ;Loads R0 with index 1 in YEAR_ARR
        PUTS                     ;Prints "In the years before "

        LEA R0, YEAR_ARR         ;Loads R0 with all strings in YEAR_ARR address
        ADD R0, R0, R1           ;Adds what's stored in YEAR_ARR[N + 0] to R0
        LDR R0, R0, #0           ;Stores value contained in YEAR_ARR[N]
        PUTS                     ;Prints the value contained in R0: YEAR_ARR[N]

        LEA R0, ELLIPSIS         ;Stores the string containing "..."
        PUTS                     ;Prints "..."

        LEA R0, NEWLINE         ;Loads R0 with a new line character
        PUTSP                   ;Creates a next line for the next       printStatement

        ADD R2, R2, R1          ;This is where i = n

FIRST_WHILE ADD R2, R2, #-1      ;Decrements i by 1
        LEA R0, EVENT_ARR        ;Loads R0 with the message specified
        ADD R0, R0, R2           ;Adds i to R0
        LDR R0, R0, #0           ;R0 now points to the memory address at R0
        PUTS                     ;prints out the specified message

        LEA R0, NEWLINE          ;R0 now points to the specified character
        PUTSP                    ;Prints out the new line character

        ADD R2, R2, #0           ;Adds 0 to R2 so we can branch
        BRP FIRST_WHILE          ;This is the branch within FIRST_ELSE

CLOSE
        LD R3, MAX              ;R3 now points to MAX
        NOT R4, R3              ;R4 points to 2's comp. negation of R3
        ADD R4, R3, #1          ;R4 now points to ~R3
        ADD R5, R1, R4          ;Adds the negative number to N
        BRZ EQUAL               ;If the result is 0, branch to EQUAL
        BRNP DIFFERENT          ;If the result isn't 0, then branch to DIFFERENT

        EQUAL
        LEA R0, AFTER_MSG_2     ;Loads R0 with the specified message
        LEA R0, YEAR_ARR        ;R0 now points to the YEAR_ARR array
        ADD R0, R0, R1          ;R0 now points to YEAR_ARR[N]
        PUTS                    ;Prints the message @ YEAR_ARR[N]
        HALT


        DIFFERENT
        LEA R0, AFTER_MSG_1     ;Loads R0 with the specified message
        PUTS                    ;Prints message in R0 to the screen.
        LEA R0, YEAR_ARR        ;R0 points to the YEAR_ARR array
        ADD R0, R0, R1          ;R0 points to the message @ YEAR_ARR[N]
        LDR R0, R0, #0          ;R0 now points to the mem. address @ R0
        PUTS                    ;Prints the specified message to the screen
        LEA R0, ELLIPSIS        ;Loads R0 with the specified message
        PUTS                    ;Prints the specified message to the screen
        LEA R0, NEWLINE         ;R0 now points @ the specified character.
        PUTSP                   ;Prints the specified message to the screen


        AND R5, R5, #0          ;Clears R5
        ADD R5, R5, R1          ;R5 now points to N and we use this as i

SECOND_WHILE

        ADD R5, R5, #1          ;increments i by 1
        AND R6, R6, #0          ;Clears R6
        LD R6, MAX              ;R6 now points to MAX
        LEA R0, EVENT_ARR       ;R0 now points to EVENT_ARR
        ADD R0, R0, R5          ;R0 now points to i
        LDR R0, R0, #0          ;R0 now points to EVENT_ARR[i]
        PUTS                    ;Prints the message out to the screen
        LEA R0, NEWLINE         ;Loads R0 with the new line character
        PUTSP                   ;Prints the character out to the screen

        NOT R7, R5              ;R7 now points to 2's comp. negation of i
        ADD R7, R7, #1          ;R7 now points to -i
        ADD R6, R6, R7          ;R6 now points to max - i
        BRP SECOND_WHILE        ;Loop until it isn't positive. 
        HALT



N       .fill 1
MAX     .fill 14

NEWLINE .fill x0A

ELLIPSIS
    .stringz "..."

BEFORE_MSG_1
    .stringz "In the years before "

BEFORE_MSG_2
    .stringz "Nothing happened before "

AFTER_MSG_1
    .stringz "And in the years after "

AFTER_MSG_2
    .stringz "And nothing happened after "

YEAR_ARR
    .fill YEAR01
    .fill YEAR02
    .fill YEAR03
    .fill YEAR04
    .fill YEAR05
    .fill YEAR06
    .fill YEAR07
    .fill YEAR08
    .fill YEAR09
    .fill YEAR10
    .fill YEAR11
    .fill YEAR12
    .fill YEAR13
    .fill YEAR14
    .fill YEAR15

EVENT_ARR
    .fill EVENT01
    .fill EVENT02
    .fill EVENT03
    .fill EVENT04
    .fill EVENT05
    .fill EVENT06
    .fill EVENT07
    .fill EVENT08
    .fill EVENT09
    .fill EVENT10
    .fill EVENT11
    .fill EVENT12
    .fill EVENT13
    .fill EVENT14
    .fill EVENT15
.end

.orig x5000
YEAR01
    .stringz "1607"
YEAR02
    .stringz "1776"
YEAR03
    .stringz "1788"
YEAR04
    .stringz "1861"
YEAR05
    .stringz "1879"
YEAR06
    .stringz "1885"
YEAR07
    .stringz "1890"
YEAR08
    .stringz "1917"
YEAR09
    .stringz "1941"
YEAR10
    .stringz "1955"
YEAR11
    .stringz "1961"
YEAR12
    .stringz "1969"
YEAR13
    .stringz "1975"
YEAR14
    .stringz "1985"
YEAR15
    .stringz "1991"
EVENT01
    .stringz "John Smith founded Jamestown in 1607"
EVENT02
    .stringz "The Declaration of Independence was signed in 1776"
EVENT03
    .stringz "The Constitution was ratified in 1788"
EVENT04
    .stringz "The Civil War began in 1861"
EVENT05
    .stringz "Thomas Edison invented the lightbulb in 1879"
EVENT06
    .stringz "The Georgia Institute of Technology was founded in 1885"
EVENT07
    .stringz "Yosemite National Park was created in 1890"
EVENT08
    .stringz "The US entered WWI in 1917"
EVENT09
    .stringz "Japan attacked Pearl Harbor in 1941"
EVENT10
    .stringz "The Civil Rights movement began in 1955"
EVENT11
    .stringz "The Vietnam War began in 1961"
EVENT12
    .stringz "Apollo 11 landed on the moon in 1969"
EVENT13
    .stringz "Bill Gates founded Microsoft Corporation in 1975"
EVENT14
    .stringz "Super Mario Bros. debuted in 1985"
EVENT15
    .stringz "The Cold War formally ended in 1991"
.end
