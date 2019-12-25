#ifndef ARRAYLIST_H
#define ARRAYLIST_H
// Don't make any modifications to this file
#include <stdbool.h>

typedef struct al {
    size_t num_ints;
    size_t buffer_size;
    int *buffer;
} arraylist;

typedef void (*void_op)(void*);

arraylist *create_arraylist(size_t num);
void free_arraylist(arraylist *al);
bool append_arraylist(arraylist *al, int n);
void traverse(arraylist *al, void_op func);

#endif /* ARRAYLIST_H */
