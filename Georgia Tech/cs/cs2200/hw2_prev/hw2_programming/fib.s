!============================================================
! CS 2200 Homework 2
!
! Please do not change main's functionality,
! except to change the argument for fibonacci or to meet your
! calling convention
! Name: Yamin Mousselli
!============================================================

main:
! CALLER

    la      $sp, stack          ! load ADDRESS of stack label into $sp

    lw      $sp, 0($sp)         ! FIXME: load the actual value of the
                                ! stack (defined in the label below)
                                ! into $sp. This sets $sp to the address stack

! Make Space for RV (N/A here), Push RA, & Call Function
    la      $at, fibonacci      ! load address of factorial label into $at
    addi    $a0, $zero, 15      ! $a0 = 5, the number n to compute fibonacci(n)

    addi    $sp, $zero, -1      ! Decrement SP to make space for RA
    sw      $ra, 0($sp)         ! Push RA onto stack
    jalr    $at, $ra            ! jump to factorial, set $ra to return addr
    halt                        ! when we return, just halt.
                                ! Fib is recursive so it will technically restore
                                ! $ra. They're doing this to check my stack.


fibonacci:
! CALLEE

    addi    $sp, $sp, -1        ! Decrement $sp by 1 for old fp
    sw      $fp, 0($sp)         ! Saving $fp
    addi    $fp, $sp, 0         ! Setting $fp = $sp



    beq    $zero, $a0, ZERO_CASE    ! Compare arg with zero. If same, z = 0.


    addi   $t0, $zero, 1            ! Doing this to check for 1 base case
    beq    $t0, $a0, ONE_CASE       ! Compare arg with one.


    beq $zero, $zero, the_assembler_is_something    ! Recurse



ZERO_CASE:
    addi    $v0, $zero, 0           ! Storing 0 in $rv.
    lw      $fp, 0($sp)             ! load $fp so I can pop off
    addi    $sp, $sp, 1             ! increment $sp to pop off $fp
    jalr    $ra, $zero              ! I'm going back to the caller

ONE_CASE:
    addi    $v0, $zero, 1           ! Storing 1 in $rv
    lw      $fp, 0($sp)             ! load $fp so I can pop off
    addi    $sp, $sp, 1             ! increment $sp to pop off $fp
    jalr    $ra, $zero              ! I'm going back to the caller

the_assembler_is_something:

    ! FORMULA
    ! a0 = a0 - 1
    ! recurse1
    ! a0 = a0 - 1
    ! recurse2
    ! a0 = a0 + 2

    addi   $a0, $a0, -1             ! Subtracting 1 from n for first recurse
    addi   $sp, $sp, -1             ! Decrement $sp for $ra
    sw     $ra, 0($sp)              ! save $ra/push onto stack
    la     $at, fibonacci           ! load address of factorial label into $at
    jalr   $at, $ra                 ! recurse and return
    lw     $ra, 0($sp)              ! Restore the RA.
    addi   $sp, $sp, 1              ! Pop RA off the stack
    addi   $sp, $sp, -1             ! Decrement sp for return value of rec.call
    sw     $v0, 0($sp)              ! Storing $sp onto stack


    addi   $a0, $a0, -1             ! Subtracting 1 from a0 for second recurse
    addi   $sp, $sp, -1             ! Decrement $sp for $ra
    sw     $ra, 0($sp)              ! save $ra/push onto stack
    la     $at, fibonacci           ! load address of factorial label into $at
    jalr   $at, $ra                 ! recurse & return
    lw     $ra, 0($sp)              ! Restore the RA.
    addi   $sp, $sp, 1              ! Pop RA off the stack
    addi   $a0, $a0, 2              ! Add 2 to get back to your original n
    lw     $t0, 0($sp)              ! Restore the first $v0 that we pushed
    addi   $sp, $sp, 1              ! Popping off the stack
    add    $v0, $t0, $v0            ! Adding the 2 recursive calls together.

    lw      $fp, 0($sp)             ! load $fp so I can pop off
    addi    $sp, $sp, 1             ! increment $sp to pop off old $fp
    jalr    $ra, $zero              ! I'm going back to the caller which is
                                    ! stored in ra....I'm not going anywhere

stack: .word 0x4000             ! the stack begins here (for example, that is)
