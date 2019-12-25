!============================================================
! CS 2200 Homework 2
!
! Please do not change main's functionality,
! except to change the argument for fibonacci or to meet your
! calling convention
!============================================================

main:
    la      $sp, stack          ! load ADDRESS of stack label into $sp

    add     $zero, $zero, $zero ! FIXME: load the actual value of the
                                ! stack (defined in the label below)
                                ! into $sp

    la      $at, fibonacci      ! load address of factorial label into $at
    addi    $a0, $zero, 5       ! $a0 = 5, the number n to compute fibonacci(n)
    jalr    $at, $ra            ! jump to factorial, set $ra to return addr
    halt                        ! when we return, just halt

fibonacci:
    noop                        ! change me to your fibonacci implementation

stack: .word 0x4000             ! the stack begins here (for example, that is)
