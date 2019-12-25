!============================================================
! CS 2200 Homework 2 Part 1: mod
! Author: Yamin Mousselli
!
! Edit any part of this file necessary to implement the
! mod operation. Store your result in $v0.
!============================================================
! I need to calculate 28 mod 13 which is 2 and store in $v0
! Code can only support non-negative integers and b != 0
! NAND DR, SR1, SR1 == NOT R1
!
! Don't have to follow calling convention.
!
! execute the following C code:
!
! int mod(int a, int b) {
!     int x = a;
!     while(x >= b) {
!       x = x - b;
!     }
!     return x;
! }
! IF SKP IS TRUE -> PC + 2, ELSE PC + 1 (NEXT instruction)
! IF SKP IS FALSE -> PC + 1

mod:
    addi    $a0, $zero, 13      ! $a0 = 28, the number a to compute mod(a,b)
    addi    $a1, $zero, 13      ! $a1 = 13, the number b to compute mod(a,b)

    add     $t0, $a0, $zero     ! storing a in x

back:
    skpe    $t0, $a1            ! Is x == b? mode 0x0.
    goto back1
    goto terminate_zero

back1:
    skpgt   $t0, $a1            ! Is x > b? mode 0x1
    goto    terminate_not_zero   ! If x == b, then x % b == 0
    goto    branch

branch:
    nand    $t1, $a1, $a1      ! NOT b
    addi    $t1, $t1, 1        ! adding 1 b/c 2's comp
    add     $t0, $t0, $t1      ! x = x - b
    goto    back               ! go back to branch

terminate_not_zero:
    addi    $v0, $t0, 0
    halt

terminate_zero:
    addi $v0, $zero, 0       ! quick fix for edge case where % = 0
    ! addi $v0, $zero, 0      ! quick fix for edge case where % = 0
    halt
