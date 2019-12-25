!=================================================================
! General conventions:
!   1) Stack grows from high addresses to low addresses, and the
!      top of the stack points to valid data
!
!   2) Register usage is as implied by assembler names and manual
!
!   3) Function Calling Convention:
!
!       Setup)
!       * Immediately upon entering a function, push the RA on the stack.
!       * Next, push all the registers used by the function on the stack.
!
!       Teardown)
!       * Load the return value in $v0.
!       * Pop any saved registers from the stack back into the registers.
!       * Pop the RA back into $ra.
!       * Return by executing jalr $ra, $zero.
!=================================================================

        ! vector table
vector0:
        .fill 0x00000000            ! device ID 0
        .fill 0x00000000            ! device ID 1
        .fill 0x00000000            ! ...
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000
        .fill 0x00000000            ! device ID 15
        ! end vector table

main:   lea $sp, stack              ! initialize the stack pointer. This is at address value 0x10.
        lw $sp, 0($sp)              ! finish initialization

                                    ! Install timer interrupt handlers into vector table
! Store the handler address at the device:
! giving the device its vector

        lea $at, t1_handler
        sw  $at, 1($zero)

        lea $at, t2_handler
        sw  $at, 2($zero)

        ei                          ! enable interrupts

        lea $a0, BASE               ! load base for pow
        lw $a0, 0($a0)

        lea $a1, EXP                ! load exp for pow
        lw $a1, 0($a1)

        lea $at, POW                ! load address of pow
        jalr $at, $ra               ! run pow

        lea $a2, ANS                ! store answer from pow
        sw $v0, 0($a2)

        halt

BASE:   .fill 2
EXP:    .fill 12
ANS:    .fill 0                     ! should come out to 4096

POW:    addi $sp, $sp, -1           ! allocate space for old frame pointer
        sw $fp, 0($sp)
        addi $fp, $sp, 0            ! set new frame pinter

        skpe $a1, $zero             ! check if $a1 is zero
        goto CONT1
        goto RET1                   ! if the power is 0 return 1

CONT1:  skpe $a0, $zero
        goto CONT2
        goto RET0                   ! if the base is 0 return 0

CONT2:  addi $a1, $a1, -1           ! decrement the power
        lea $at, POW                ! load the address of POW
        addi $sp, $sp, -2           ! push 2 slots onto the stack
        sw $ra, -1($fp)             ! save RA to stack
        sw $a0, -2($fp)             ! save arg 0 to stack
        jalr $at, $ra               ! recursively call POW
        add $a1, $v0, $zero         ! store return value in arg 1
        lw $a0, -2($fp)             ! load the base into arg 0
        lea $at, MULT               ! load the address of MULT
        jalr $at, $ra               ! multiply arg 0 (base) and arg 1 (running product)
        lw $ra, -1($fp)             ! load RA from the stack
        addi $sp, $sp, 2
        goto FIN                    ! return

RET1:   addi $v0, $zero, 1          ! return a value of 1
        goto FIN

RET0:   add $v0, $zero, $zero       ! return a value of 0

FIN:    lw $fp, 0($fp)              ! restore old frame pointer
        addi $sp, $sp, 1            ! pop off the stack
        jalr $ra, $zero


MULT:   add $v0, $zero, $zero       ! zero out return value
AGAIN:  add $v0, $v0, $a0           ! multiply loop
        addi $a1, $a1, -1           ! a1 -= 1

        skpe $a1, $zero             ! finished multiplying
        goto AGAIN                  ! loop again

DONE:   jalr $ra, $zero

t1_handler:
        addi $sp, $sp, -1
        sw $k0, 0($sp)
        ei

        ! store all processor registers
        ! piazza said only regs u use in handler
        addi $sp, $sp, -1
        sw   $t0, 0($sp)
        addi $sp, $sp, -1
        sw   $t1, 0($sp)

        !I need to increment the value at the address.

        ! load address at label, $t0 = address
        lea  $t0, ticks1

        ! Store contents at address of ticks
        lw   $t0, 0($t0)

        ! Storing value at the address of ticks
        lw   $t1, 0($t0)

        ! Incrementing the value at ticks
        addi $t1, $t1, 1

        ! store value in memory
        sw $t1, 0($t0)

        ! Restore processor registers
        ! pop off registers LIFO
        ! don't be a dumbass
        lw   $t1, 0($sp)
        addi $sp, $sp, 1
        lw   $t0, 0($sp)
        addi $sp, $sp, 1

        ! Disable interrupts
        di

        ! Restore $k0
        lw  $k0, 0($sp)
        addi $sp, $sp, 1

        reti

t2_handler:
        addi $sp, $sp, -1
        sw $k0, 0($sp)
        ei

        ! store all processor registers
        ! piazza said only regs u use in handler
        addi $sp, $sp, -1
        sw   $t0, 0($sp)
        addi $sp, $sp, -1
        sw   $t1, 0($sp)

        !I need to increment the value at the address.

        ! load address at label, $t0 = address
        lea  $t0, ticks2

        ! Store contents at address of ticks
        lw   $t0, 0($t0)

        ! Storing value at the address of ticks
        lw   $t1, 0($t0)

        ! Incrementing the value at ticks
        addi $t1, $t1, 1

        ! store value in memory
        sw $t1, 0($t0)

        ! Restore processor registers
        ! pop off registers LIFO
        ! don't be a dumbass
        lw   $t1, 0($sp)
        addi $sp, $sp, 1
        lw   $t0, 0($sp)
        addi $sp, $sp, 1

        ! Disable interrupts
        di

        ! Restore $k0
        lw  $k0, 0($sp)
        addi $sp, $sp, 1

        reti



stack:  .fill 0xA00000

ticks1: .fill 0xFFFFFD
ticks2: .fill 0xFFFFFE
