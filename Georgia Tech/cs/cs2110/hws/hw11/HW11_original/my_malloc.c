#include "my_malloc.h"

/* You *MUST* use this macro when calling my_sbrk to allocate the
 * appropriate size. Failure to do so may result in an incorrect
 * grading!
 */
#define SBRK_SIZE 2048

/* Please use this value as your canary! */
#define CANARY 0x2110CAFE

/* If you want to use debugging printouts, it is HIGHLY recommended
 * to use this macro or something similar. If you produce output from
 * your code then you may receive a 20 point deduction. You have been
 * warned.
 */
#ifdef DEBUG
#define DEBUG_PRINT(x) printf x
#else
#define DEBUG_PRINT(x)
#endif

/* Our freelist structure - This is where the current freelist of
 * blocks will be maintained. Failure to maintain the list inside
 * of this structure will result in no credit, as the grader will
 * expect it to be maintained here.
 *
 * Technically this should be declared static for the same reasons
 * as above, but DO NOT CHANGE the way this structure is declared
 * or it will break the autograder.
 */
metadata_t* freelist;

void* my_malloc(size_t size)
{
  /* YOUR CODE HERE! */
  return NULL;
}

void my_free(void* ptr)
{
  /* YOUR CODE HERE! */
}

/* MAYBE ADD SOME HELPER FUNCTIONS HERE? */
