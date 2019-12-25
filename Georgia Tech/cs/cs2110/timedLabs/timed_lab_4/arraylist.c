#include <stdio.h>
#include <stdlib.h>
#include "arraylist.h"

/*
 * Create the arraylist in heap memory as well as the backing
 * array. This will require that you do two separate memory
 * allocations. If either fails ensure their are no memory
 * leaks and return NULL.
 *
 * also initialize the other fields of the struct detailed below:
 * num_ints : the number of ints currently in the arraylist - initialized to 0
 * buffer_size : the size of the backing buffer in bytes
 * buffer: the backing array of the arraylist
 *
 * returns : the arraylist allocated in heap memory or NULL if failed

 n is the total number of ints that the arraylist can hold w/o resizing.
 */
arraylist* create_arraylist(size_t n) {
    arraylist* aList = malloc(sizeof(arraylist));
    if (aList == NULL) {
        return NULL;
    }
    //4 bytes per int. malloc takes in a number of bytes. Think of the //arguments for malloc as unit conversion...Must be in bytes!
    if ((aList->buffer = malloc(n * sizeof(int))) == NULL) {
        return NULL;
    }
    /*else {
        aList->buffer = malloc(n);
    }
    don't need the else because then I'm calling malloc twice.

    */
    aList->num_ints = 0;
    aList->buffer_size = n * 4;
    //not n / 8 because it's = to number of bytes you passed in to malloc

    return aList;

}

/*
 * free both the arraylist struct as well as the
 * backing buffer that were allocated in create_arraylist
 * if the arraylist is NULL return - do not segfault.
 */
void free_arraylist(arraylist* al) {
    //There could be a pointer to buffer even if size == 0.
    if (al == NULL) {
        return;
    }
    /*
        Remember that even if the buffer is null, that does not mean that you can free the arraylist.
    */
    if (al->buffer != NULL) {
        free(al->buffer);
    }
    //Must free this struct later because otherwise, you will not have a pointer to the backing array.
    free(al);

    /*
        I don't need to free the data because the data is not generic and is not allocated by malloc. You just to need to free the arraylist and buffer. This is the case because the arraylist holds ints, if not then it would involve a free_func like the linkedlist hw

        The first for loop would cause a seg fault and it is not doing what I thought it would do because it's not going anywhere. &al[i] = al + i. It is incrementing the struct pointer.

        The second for loop does delete the elements in the array but that is not what I want. buffer is a pointer in the arraylist struct that points to the first element in the backing array.

    for (unsigned int i = 0; i < al->num_ints; i++) {
        free(&(al[i]));
    }
    al->num_ints = 0;
    free(al);
    for (unsigned int i = 0; i < al->buffer_size; i++) {
        free(&(al->buffer[i]));
    }
    al->buffer_size = 0;
    free(al->buffer);

    */
}

/*
 * We provide the template of this helper function
 * to aid you in implementing append_arraylist.
 *
 * We're not grading this function but implementing it
 * properly will greatly help you implement the next function.
 *
 * You'll have to do multiple things in this function:
 * - attempt to allocate a buffer twice the size of the current buffer
 * - if this fails return false
 * - if it succeeds then copy the ints into the new buffer and free the old
 *   buffer
 * - don't forget to also change the value of the fields in the arraylist
 * - return true on success
 *
 */
static bool double_arraylist(arraylist* al) {
    if (al == NULL) {
        return 0;
    }
    //if the below isn't true, then it will dump the stack. This will create a //core file that shows what line the dump occurred on.
    assert(al->buffer_size > 0);
    //arraylist* newAl = create_arraylist((al->buffer_size) * 2);
    /*
    why doesn't this work
    arraylist* newBuffer = malloc(sizeof((al->buffer_size) * 2));
    */
    int* newBuffer = malloc((al->buffer_size) * 2);

    if (newBuffer == NULL) {
        return 0;
    }
    for (unsigned int i = 0; i < al->num_ints; i++) {
        newBuffer[i] = al->buffer[i];
    }
    al->buffer_size = ((al->buffer_size) * 2);
    //al->buffer_size = 0;
    free(al->buffer);
    al->buffer = newBuffer;
    return 1;
}


/*
 * You must append the int to the end of the arraylist.
 * If the arraylist backing buffer is not large enough
 * you must double the backing arraylist. If you're not
 * able to double the backing arraylist return false.
 *
 *
 * al : the arraylist that you need to add the element to
 * n : the int to add at the end of the arraylist
 */
bool append_arraylist(arraylist* al, int n) {

    if (al == NULL) {
        return 0;
    }

    if (((al->num_ints) * sizeof(int)) != al->buffer_size) { //must multiply num_ints * 4
        al->buffer[al->num_ints] = n;
        al->num_ints++;
        return 1;
    } else {
        double_arraylist(al);
        al->buffer[al->num_ints] = n;
        al->num_ints++;
        return 1;
    }
}


/*
 * This function has been provided for you
 */
void traverse(arraylist* al, void_op func) {
    for (unsigned int i = 0; i < al->num_ints; i++) {
        func(&(al->buffer[i]));
    }
}
