/**
 * CS 2110 - Summer 2017 - Homework #10
 *
 * @author YOUR NAME HERE
 *
 * dllist.c: Complete the functions!
 */

#include <stdlib.h>
#include <stdio.h>
#include "dllist.h"

/* The node struct. Has a prev pointer, next pointer, and data. */
/* DO NOT DEFINE ANYTHING OTHER THAN WHAT'S GIVEN OR YOU WILL GET A ZERO */
/* Design consideration: Only this file should know about nodes */
/* Only this file should be manipulating nodes */
/* DO NOT TOUCH THIS DECLARATION, DO NOT PUT IT IN OTHER FILES */
typedef struct dnode
{
    struct dnode* prev; /* Pointer to previous node */
    struct dnode* next; /* Pointer to next node */
    void* data; /* User data */
} node;

/* Do not create any global variables here. Your dllist library should obviously
 * work for multiple concurrent dllists */

// This function is declared as static since you should only be calling this
// function from inside this file.
static node* create_node(void* data);

/** create_node
  *
  * Helper function that creates a node by allocating memory for it on the heap.
  * Be sure to set its pointers to NULL.
  *
  * @param data a void pointer to data the user wants to store in the dllist
  * @return a node
  */
static node* create_node(void* data)
{
    /// @todo Implement changing the return value!
    return NULL;
}

/** create_dllist
  *
  * Creates a dllist by allocating memory for it on the heap.
  * Be sure to initialize size to zero and head/tail to NULL.
  *
  * @return an empty dllist
  */
dllist* create_dllist(void)
{
    /// @todo Implement changing the return value!
    return NULL;
}

/** push_front
  *
  * Adds the data to the front of the dllist.
  *
  * @param d a pointer to the dllist structure.
  * @param data pointer to data the user wants to store in the dllist.
  * @return true if successful false otherwise
  */
bool push_front(dllist *d, void *data)
{
    /// @todo Implement
}

/** push_back
  *
  * Adds the data to the back of the dllist.
  *
  * @param d a pointer to the dllist structure.
  * @param data pointer to data the user wants to store in the dllist.
  * @return true if successful false otherwise
  */
bool push_back(dllist *d, void *data)
{
    /// @todo Implement
}

/** front
  *
  * Gets the data at the front of the dllist
  * If the dllist is empty return NULL.
  *
  * @param d a pointer to the dllist
  * @return The data at the first node in the dllist or NULL.
  */
void *front(dllist *d)
{
    /// @todo Implement changing the return value!
    /// @note you are returning the HEAD's DATA not the head node. Remember, the
    /// user should never deal with the dllist nodes.
    return NULL;
}

/** back
  *
  * Gets the data at the "end" of the dllist
  * If the dllist is empty return NULL.
  *
  * @param d a pointer to the dllist structure
  * @return The data at the last node in the dllist or NULL.
  */
void *back(dllist *d)
{
    /// @todo Implement changing the return value!
    return NULL;
}

/** get
  *
  * Gets the data at the specified index in the dllist
  *
  * @param d a pointer to the dllist structure
  * @param index 0-based, starting from the head.
  * @return The data from the specified index in the dllist or NULL if index is
  *         out of range of the dllist.
  */
void *get(dllist *d, int index)
{
    /// @todo Implement changing the return value!
    return NULL;
}
/** add
  *
  * Adds the data at the specified index in the dllist.
  * Adding at index size should have the same behavior as push_back
  *
  * @param d a pointer to the dllist structure
  * @param index 0-based, starting from the head, to size.
  * @return true if insertion was successful or false if index is
  *         out of range of the dllist or malloc fails.
  */
bool add(dllist *d, void* data, int index)
{
    /// @todo Implement changing the return value!
    return true;
}

/** contains
  *
  * Traverses the dllist, trying to see if the dllist contains some data.
  * Since non-NULL values are considered true, this can be used like a boolean
  *
  * The "data" parameter may not necessarily point to the same address as the
  * equal data you are returning from this function, it's just data which the
  * eq_func says is equal. For instance, if you have a dllist of person structs:
  *   (Andrew, 26), (Nick, 24), (Collin, 23), (Marie, 23)
  * And you want to return any person whose age is 22, you could create a new
  * person struct (<NULL>, 24) with an eq_func that returns age == 24 and pass
  * that into this function as "data". contains() would then return (Nick, 24)
  *
  * If there are multiple pieces of data in the dllist which are equal to the
  * "data" parameter, return any one of them.
  *
  * @param d a pointer to the dllist structure
  * @param data The data, to see if it exists in the dllist
  * @param eq_func A function written by the user that will tell if two pieces
  *                of data are equal. Returns 0 if equal, something else
  *                otherwise. Imagine subtracting two numbers.
  * @return The data contained in the dllist, or NULL if it doesn't exist
  */
void *contains(dllist *d, void *data, dllist_eq eq_func) {
    /// @todo Implement
    return NULL;
}

/** pop_front
  *
  * Removes the node at the front of the dllist, and returns its data to the user
  *
  * @param d a pointer to the dllist.
  * @return The data in the first node, or NULL if the dllist is NULL or empty
  */
void *pop_front(dllist *d)
{
    /// @todo Implement
    return NULL;
}

/** pop_back
  *
  * Removes the node at the end of the dllist, and returns its data to the user
  *
  * @param d a pointer to the dllist.
  * @return The data in the first node, or NULL if the dllist is NULL or empty
  */
void *pop_back(dllist *d)
{
    /// @todo Implement
    return NULL;
}

/** copy_reverse_dllist
  *
  * Create a new dllist structure, new nodes, and new copies of the data by using
  * the copy function. The new dllist will contain all of the same elements except
  * in reverse order. Its implementation for any test structure must copy
  * EVERYTHING! (This means you need to use the copy_func)
  *
  * @param d A pointer to the dllist structure to make a copy of
  * @param copy_func A function pointer to a function that makes a copy of the
  *                  data that's being used in this dllist, allocating space for
  *                  every part of that data on the heap. This is some function
  *                  that the user of the library writes themselves, tailored
  *                  specifically to whatever data the dllist holds. If this
  *                  function fails to copy the element it will return NULL.
  *                  Also note that if the copy_function is called with NULL
  *                  as a parameter it will fail so you need to copy this value
  *                  manually.
  *
  * @param free_func A function pointer to a function that you can use to free
  *                  the user items. You need to use this function in the event
  *                  that the copy func fails while copying. If this occurs you
  *                  must free all of the elements of the newly created list.
  *
  * @return The dllist structure created by copying the old one, or NULL if the
  *         structure passed in is NULL. If free_func fails then return NULL and
  *         ensure there are no memory leaks by freeing the newly created list
  *         and the elements that would be in it.
  */
dllist* copy_reverse_dllist(dllist *d, dllist_copy copy_func, dllist_op free_func)
{
	/// @todo implement
	return NULL;
}

/** size
  *
  * Gets the size of the dllist
  *
  * @param d a pointer to the dllist structure
  * @return The size of the dllist
  */
int size(dllist *d)
{
    ///@note simply return the size of the dllist. It's that easy!
    return 0;
}

/** is_empty
  *
  * Checks to see if the dllist is empty.
  *
  * @param d a pointer to the dllist structure
  * @return true if the dllist is indeed empty, or false otherwise.
  */
bool is_empty(dllist *d)
{
    /// @note an empty dllist should have a size of zero and head points to NULL.
    return 0;
}

/** empty_dllist
  *
  * Empties the dllist. After this is called, the dllist should be empty.
  * This does not free the dllist struct itself, just all nodes and data within.
  *
  * @param d a pointer to the dllist structure
  * @param free_func function used to free the nodes' data.
  */
void empty_dllist(dllist *d, dllist_op free_func)
{
    /// @todo Implement
    /// @note Free all of the nodes, not the dllist structure itself.
    /// @note do not free the dllist structure itself.
}

/** traverse
  *
  * Traverses the dllist, calling a function on each node's data.
  *
  * @param d a pointer to the dllist structure (if null don't traverse)
  * @param do_func a function that does something to each node's data.
  */
void traverse(dllist *d, dllist_op do_func)
{
    /// @todo Implement
}
