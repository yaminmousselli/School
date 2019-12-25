!============================================================
! CS 2200 Homework 2 Part 2: gcd
! Author: Yamin Mousselli
!
! Please do not change main's functionality,
! except to change the argument for gcd or to meet your
! calling convention
!============================================================
! execute the following C code:
!
! int gcd(int a, int b) {
!   if (b == 0) {
!      return a;
!   }
!   return gcd(b, a % b);
! }
!
! Constraints: a >= 0, b >= 0, and a >= b
!
! NOOP stands for no operation. It wastes a clock cycle on nothing. For example,
! adding zero to zero register.

-----------------------------------------------------------------------------------------------------------------------
TA Announcement: 
Well, this is getting embarassing. We unearthed an issue in the gcd.s template file we gave you for homework 2.

On lines 17 and 18, we set a0 = 15 and a1 = 25, so gcd is called with arguments gcd(15, 25). If you
notice in the PDF, we say that the arguments a and b to gcd must always be a >= b. 15 is not >= 25, so this obviously is a mistake.

Please make sure you change these lines in gcd.s to set a0 = 25 and a1 = 15. Otherwise your
code will infinitely loop or not work, since the pseudocode we asked you to implement does not handle cases when a < b.

If you haven't started yet, I've already fixed the file on T-Square, so just re-download the homework.
And get started soon because this homework is difficult for many.
--------------------------------------------------------------------------------------------------------------------------


main:
    lea     $sp, stack          ! load ADDRESS of stack label into $sp

    lw      $sp, 0x0($sp)       ! Here, you need to initialize the stack
                                ! using the label below by loading its
                                ! VALUE into $sp (CHANGE THIS INSTRUCTION)

    lea     $at, gcd            ! load address of gcd label into $at
    addi    $a0, $zero, 100     ! $a0 = 25, the number a to compute gcd(a,b)
    addi    $a1, $zero, 50      ! $a1 = 15, the number b to compute gcd(a,b)
    jalr    $at, $ra            ! jump to gcd, set $ra to return addr
    halt                        ! when we return, just halt

gcd:
    sw      $fp, 0x0($sp)       ! save old $fp which is at the $sp
    addi    $sp, $sp, -1

    skpe    $a1, $zero          ! Is b == 0? Mode 0x0
    goto    mod
    goto    gcd_return          ! return a if b == 0

mod_finished:
    add     $a0, $a1, $zero
    add     $a1, $v0, $zero

    sw      $ra, 0x0($sp)
    addi    $sp, $sp, -1
    jalr    $at, $ra
    addi    $sp, $sp, 1
    lw      $ra, 0x0($sp)

gcd_return:
    add     $v0, $a0, $zero
    addi    $sp, $sp, 1
    lw      $fp, 0x0($sp)
    jalr    $ra, $zero


mod:
    add     $t0, $a0, $zero      ! storing a in x

back:
    skpe    $t0, $a1             ! Is x == b? mode 0x0.
    goto back1
    goto terminate_zero

back1:
    skpgt   $t0, $a1             ! Is x > b? mode 0x1
    goto    terminate_not_zero   ! If x == b, then x % b == 0
    goto    branch

branch:
    nand    $t1, $a1, $a1        ! NOT b
    addi    $t1, $t1, 1          ! adding 1 b/c 2's comp
    add     $t0, $t0, $t1        ! x = x - b
    goto    back                 ! go back to branch

terminate_not_zero:
    addi    $v0, $t0, 0
    goto    mod_finished

terminate_zero:
    addi $v0, $zero, 0           ! quick fix for edge case where % = 0
    goto mod_finished

stack: .word 0xFFFFFF            ! the stack begins here (for example, that is)
