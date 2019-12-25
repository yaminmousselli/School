#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include "arraylist.h"

void increment_int(void *p)
{
    int *ip = (int*)p;
    (*ip)++;
}

void print_int(void *p)
{
    int *ip = (int*)p;
    printf("%d\n", *ip);
}

int main()
{
    arraylist *al = create_arraylist(6);

    assert(append_arraylist(al, 8));
    assert(append_arraylist(al, 6));
    assert(append_arraylist(al, 7));
    assert(append_arraylist(al, 5));
    assert(append_arraylist(al, 3));
    assert(append_arraylist(al, 0));
    assert(append_arraylist(al, 9));

    // you should manually check that the printed value is correct
    traverse(al, print_int);
    traverse(al, increment_int);

    puts("");
    traverse(al, print_int);

    free_arraylist(al);

    return 0;
}
