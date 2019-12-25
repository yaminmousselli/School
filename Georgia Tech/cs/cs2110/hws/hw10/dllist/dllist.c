/**
 * CS 2110 - Summer 2017 - Homework #10
 *
 * @author Yamin Mousselli
 *
 * dllist.c: Complete the functions!
 */

 /*
    memory leak is when you don't free memory ...temp variables. if you call malloc, then you free if you're not returning object to the user.
 */

 /*
 memory error and that's when iterating over linked list and go one to far, you're accessing memory that's not yours to mess with (out of binds...valgrind will complain)
 */

 /*
     node* temp = dlllist->head;
     a constant pointer is an array name
     int arr[3]; pointer to an array of size 3.
     int *p1, *d1;
     can not do arr = p1 because arr is an constant pointer.
     can do p1 = arr and C doesn't make it a constant pointer.
     temp = temp->next
 */

 /*
                            STACKOVERFLOW:

 Basically it means "nothing" or "no type"

 There are 3 basic ways that void is used:

 Function argument: int myFunc(void) -- the function takes nothing.

 Function return value: void myFunc(int) -- the function returns nothing

 Generic data pointer: void* data -- 'data' is a pointer to data of unknown type, and cannot be dereferenced

 Note: the void in a function argument is optional in C++, so int myFunc() is exactly the same as int myFunc(void), and it is left out completely in C#. It is always required for a return value.

 You can refer to this as struct dnode or as just node when accessing things. I prefer to use one word over two, so just go with the alias (typedef) name.
*/


#include <stdlib.h>
#include <stdio.h>
#include "dllist.h"

#if 0
/* The node struct. Has a prev pointer, next pointer, and data. */
/* DO NOT DEFINE ANYTHING OTHER THAN WHAT'S GIVEN OR YOU WILL GET A ZERO */
/* Design consideration: Only this file should know about nodes */
/* Only this file should be manipulating nodes */



/* DO NOT TOUCH THIS DECLARATION, DO NOT PUT IT IN OTHER FILES */
#endif
typedef struct dnode {
    struct dnode* prev; /* Pointer to previous node */
    struct dnode* next; /* Pointer to next node */
    void* data; /* User data */
} node;

/* Do not create any global variables here. Your dllist library should obviously
 * work for multiple concurrent (existing/happening at the same time) dllists */

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
static node* create_node(void* data) {
    // Allocates space on the heap for the members of the struct
    node* newNode = malloc(sizeof(node));
    //check if malloc fails meaning there isn't space on the heap
    if (newNode == NULL) {
        return NULL;
    }
    newNode->data = data; //don't forget the data
    newNode->prev = NULL;
    newNode->next = NULL;
    return newNode;
}

/** create_dllist
  *
  * Creates a dllist by allocating memory for it on the heap.
  * Be sure to initialize size to zero and head/tail to NULL.
  *
  * @return an empty dllist
  */
dllist* create_dllist(void) {
    dllist* doublyList = malloc(sizeof(dllist)); //size of dllist struct
    if (doublyList == NULL) {
        return NULL;
    }
    doublyList->head = NULL;
    doublyList->tail = NULL;
    doublyList->size = 0;
    return doublyList;
}

#if 0
//this is for nested comments
/** push_front
  *
  * Adds the data to the front of the dllist.
    bool is a char. If you return 0 or 1 it will automatically cast the int into bool which is 1 byte

    My Java Code for Singly List
    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The value, null, is"
                    + " can not be added to the list");
        }
        head = new LinkedListNode<T>(data, head);

        Say they don't have a constructor that takes a next pointer, then you
         would do the following:

        LinkedListNode<T> temp = new LinkedListNode<T>(data);
        temp.setNext(head);
        head = temp;

        if (tail == null) {
            tail = head;
        }
        size++;
    }

    destroy:
    if (llist == NULL || llist->size == 0) return;
    NODE* currnode = llist->head;
    while (currnode->next != NULL) {
        NODE* tempnode = currnode;
        currnode = currnode->next;

        free_func(tempnode->data);
        free(tempnode);
    }
    llist->size = 0;
    free(llist);

    to_array:
    if (llist == NULL || llist->size == 0) return NULL;

	void* array = malloc((struct_size * llist->size));
	if (array == NULL) {
		return NULL;
	}
	void* temp = array;
	NODE* current = llist->head;
	for (unsigned int i = 0; i < llist->size; i++) {
		memcpy(temp, current->data, struct_size);
		temp = ((char*)temp) + struct_size;
		current = current->next;
	}
	return array;


    void print_list(LIST *llist)
{
	if (llist->head == NULL) {
		printf("empty\n");
		return;
	}
	NODE *thisNode = llist->head;
	while (thisNode != NULL) {
		printf("%d, ", thisNode->data);
		thisNode = thisNode->next;
	}
}

  * @param d a pointer to the dllist structure.
  * @param data pointer to data the user wants to store in the dllist.
  * @return true if successful false otherwise
  */
 #endif
bool push_front(dllist* d, void* data) {
    if (d == NULL) {
        return NULL;
    } else if (d->head == NULL) {
        node* newNode = create_node(data);
        if (newNode == NULL) {
            return NULL; //CHECK IF NEWNODE IS NOT CREATED.
        }
        d->head = newNode;

        if (d->tail == NULL) {
            d->tail = newNode; //Is the if statement necessary...defensive?
        }

        d->size++; //AKA d->size += 1;
        return 1;
    } else {
        node* newNode = create_node(data);
        newNode->next = d->head;
        d->head->prev = newNode; //DON'T FORGET THAT IT'S DOUBLY
        d->head = newNode;
        d->size++;
        return 1;
    }
}

/** push_back
  *
  * Adds the data to the back of the dllist.

  @Override
  public void addToBack(T data) {
      if (data == null) {
          throw new java.lang.IllegalArgumentException("Null is not a valid"
                  + " element that can be added to the list");
      }
      if (head == null) {
          addToFront(data);
      } else {
          tail.setNext(new LinkedListNode<T>(data));
          tail = tail.getNext();
          size++;
      }
  }

  * @param d a pointer to the dllist structure.
  * @param data pointer to data the user wants to store in the dllist.
  * @return true if successful false otherwise
  */
bool push_back(dllist* d, void* data) {
    if (d == NULL) {
        return NULL;
    } else if (d->head == NULL) {
        return push_front(d, data);
    } else if (d->size == 1) {
        node* newNode = create_node(data);
        newNode->prev = d->head;
        //newNode->next = NULL; newNode->next already points to null
        d->head->next = newNode;
        d->tail = newNode; //is this necessary?
        d->size++;
        return 1;
    } else {
        node* newNode = create_node(data);
        newNode->prev = d->tail;
        d->tail->next = newNode;
        d->tail = newNode;
        d->size++;
        return 1;
    }
}

/** front
  *
  * Gets the data at the front of the dllist
  * If the dllist is empty return NULL.
  *
  * @param d a pointer to the dllist
  * @return The data at the first node in the dllist or NULL.
  */
void *front(dllist* d) {
    /// @note you are returning the HEAD's DATA not the head node. Remember, the
    /// user should never deal with the dllist nodes.
    if (d == NULL) {
        return NULL;
    } else if (d->head == NULL) {
        return NULL;
    } else {
        return ((size(d)) == 0) ? NULL : d->head->data;
    }

    /*
    //Long way
    if (size(d) == 0) {
        return NULL;
    } else {
        return d->head->data;
    }
    */
}

/** back
  *
  * Gets the data at the "end" of the dllist
  * If the dllist is empty return NULL.
  *
  * @param d a pointer to the dllist structure
  * @return The data at the last node in the dllist or NULL.
  */
void *back(dllist* d) {
    if (d == NULL) {
        return NULL;
    } else if (d->tail == NULL) {
        return NULL;
    } else {
        return (is_empty(d)) ? NULL : d->tail->data;
    }
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
void *get(dllist *d, int index) {
    // do you want us to handle the case where index is negative or where it's // >= size?
    if (d == NULL) {
        return NULL;
    } else if (index < 0 || index >= d->size) {
        return NULL;
    } else if (index == 0) {
        return d->head->data;
    } else if (index == (d->size - 1)) {
        return d->tail->data;
    } else {
        int i; //counter to iterate
        node* current = d->head; //temp node. DO I NEED TO FREE?
        for (i = 0; i <= index; i++) {
            if (i == index) {
                return current->data;
            }
            current = current->next;
        }
        return current;
    }
}
/** add
  *
  * Adds the data at the specified index in the dllist.
  * Adding at index size should have the same behavior as push_back

  @Override
      public void addAtIndex(int index, T data) {
          if (index < 0) {
              throw new java.lang.IndexOutOfBoundsException("You have "
                      + "specified an index smaller than 0");
          } else if (index > size) {
              throw new java.lang.IndexOutOfBoundsException("The index you "
                      + "specified bigger than/equal the capacity of the list");
          } else if (data == null) {
              throw new java.lang.IllegalArgumentException("Null is not a valid"
                      + "input");
          } else if (index == 0) {
              addToFront(data);
          } else if (index == size) {
              addToBack(data);
          } else {
              LinkedListNode<T> current = head;
              for (int i = 0; i < index - 1; i++) {
                  current = current.getNext();
              }
              LinkedListNode<T> newNode = new LinkedListNode<T>(data, current
                      .getNext());
              current.setNext(newNode);
              size++;
          }
      }

  * @param d a pointer to the dllist structure
  * @param index 0-based, starting from the head, to size.
  * @return true if insertion was successful or false if index is
  *         out of range of the dllist or malloc fails.

    malloc would fail when you run out of memory on the heap and malloc returns null meaning you don't have any more space on the heap. Therefore, you return NULL
  */
bool add(dllist *d, void* data, int index) {
    if (d == NULL) {
        return 0;
    } else if (index < 0 || index > d->size) {
        return 0;
    } else if (index == 0) {
        return push_front(d, data);
    } else if (index == d->size) {
        return push_back(d, data);
    } else {
        node* current = d->head;
        for (int i = 0; i < index - 1; i++) {
            current = current->next;
        }
        node* newNode = create_node(data);
        //this is the case where Malloc fails.
        if (newNode == NULL) {
            return NULL;
        }
        newNode->next = current->next;
        newNode->prev = current;

        //Set current's next prev point to newNode
        current->next->prev = newNode;
        current->next = newNode;
        d->size++;
        return 1;
    }
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
    if (d == NULL || data == NULL || d->size == 0) {
        return NULL;
    } else {
        node* current = d->head;
        int count;
        for (count = 0; count < d->size; count++) {
            if ((eq_func(current->data, data)) == 0) {
                return current->data;
            }
            current = current->next;
        }
        return NULL;
        /* find out why the shit below doesn't work
        while (current->next != NULL && count != (d->size - 1)) {
            if ((eq_func(current->data, data)) == 0) {
                return current->data;
            }
            if (current->next != NULL) {
                current = current->next;
                count++;
            }
        }
        return NULL;
        */
    }
}

/** pop_front
  *
  * Removes the node at the front of the dllist, and returns its data to the user

   @Override
   public T removeFromFront() {
       if (size == 0) {
           return null;
       }
       if (head == tail) {
           T toBeReturned = head.getData();
           head = null;
           tail = null;
           size--;
           return toBeReturned;
       } else {
           T toBeReturned = head.getData();
           head = head.getNext();
           size--;
           return toBeReturned;
       }
   }

  * @param d a pointer to the dllist.
  * @return The data in the first node, or NULL if the dllist is NULL or empty
  */
void *pop_front(dllist* d) {
    if (d == NULL) {
        return NULL;
    } else if (is_empty(d) || d->head == NULL) {
        return NULL;
    } else if (d->size == 1) {
        node* temp = d->head;
        void* toBeReturned = temp->data;
        d->head = NULL;
        d->tail = NULL;
        d->size--;
        free(temp);
        return toBeReturned;
    } else {
        node* temp = d->head;
        void* toBeReturned = temp->data;
        d->head = d->head->next;
        d->size--;
        free(temp);
        return toBeReturned;
    }
}

/** pop_back
  *
  * Removes the node at the end of the dllist, and returns its data to the user
  *
  * @param d a pointer to the dllist.
  * @return The data in the first node, or NULL if the dllist is NULL or empty
  */
void *pop_back(dllist *d) {
    if (d == NULL) {
        return NULL;
    } else if (is_empty(d) || d->head == NULL) {
        return NULL;
    } else if (d->size == 1) {
        node* temp = d->tail;
        void* toBeReturned = temp->data;
        d->head = NULL;
        d->tail = NULL;
        d->size--;
        free(temp);
        return toBeReturned;
    } else {
        node* temp = d->tail;
        void* toBeReturned = temp->data;
        d->tail = d->tail->prev;
        d->tail->next = NULL;
        d->size--;
        free(temp);
        return toBeReturned;
    }
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
dllist* copy_reverse_dllist(dllist *d, dllist_copy copy_func, dllist_op free_func) {
    if (d == NULL || copy_func == NULL) {
        return NULL;
    } else {
        node* current = d->head;
        dllist* doublyCopy = create_dllist(); //create the copy
        if (doublyCopy == NULL) {
            return NULL;
        }
        for(int i = 0; i < d->size; i++) {
            if (current->data == NULL) {
                //REVERSE ORDER
                push_front(doublyCopy, NULL);

                //inside is what happens if you fail
                if (!(push_front(doublyCopy, NULL))) {
                    empty_dllist(doublyCopy, free_func);
                    return NULL;
                }
                current = current->next; //is this needed?
                continue; // This will hop onto the next iteration of for loop
            } else {
                void* copy = copy_func(current->data);
                if (copy == NULL) {
                    empty_dllist(doublyCopy, free_func);
                    /*If free_func fails then return NULL and ensure there are no memory leaks by freeing the newly created list and the elements that would be in it.*/
                    free(doublyCopy);
                    free(copy);
                    return NULL;
                }
                push_front(doublyCopy, copy);
            }
        }
        return doublyCopy;
    }
}

/** size
  *
  * Gets the size of the dllist
  *
  * @param d a pointer to the dllist structure
  * @return The size of the dllist
  */
int size(dllist* d) {
    if (d == NULL) {
        return 0;
    } else {
        return d->size;
    }
}

/** is_empty
  *
  * Checks to see if the dllist is empty.
  *
  * @param d a pointer to the dllist structure
  * @return true if the dllist is indeed empty, or false otherwise.
  */
bool is_empty(dllist* d) {
    /// @note an empty dllist should have a size of zero and head points to NULL.
    if (d == NULL) {
        return 0;
    }
    return (d->size == 0) ? true : false;
}

/** empty_dllist
  *
  * Empties the dllist. After this is called, the dllist should be empty.
  * This does not free the dllist struct itself, just all nodes and data within.

    The reason we don't free the node itself is because you don't know how the user has set up their program and you might end up freeing something which forces you to lose the pointers to free other things in which case, is a disaster. There could be a chain of things that you need to free after you free the node, so then you can't do it. Basically, avoid this when possible.
    You would have a memory leak if you allowed this to happen.
  *
  * @param d a pointer to the dllist structure
  * @param free_func function used to free the nodes' data.
  */
void empty_dllist(dllist *d, dllist_op free_func) {
    /// @todo Implement
    /// @note Free all of the nodes, not the dllist structure itself.
    /// @note do not free the dllist structure itself.

//Algorithm: Go one by one you can use pop_front to take care of the list stuff.
    if (d == NULL || d->head == NULL || d->size == 0) {
        return;
    } else {
         //i screwed up and only emptied half the list because of the condition
        for(int i = 0; d->size > 0; i++) {
            void* toBeReturned = pop_front(d);
            free_func(toBeReturned);
        }
        //FREE FUNC TAKES NODE'S DATA, NOT NODE.
    }
}

/** traverse
  *
  * Traverses the dllist, calling a function on each node's data.
  *
  * @param d a pointer to the dllist structure (if null don't traverse)
  * @param do_func a function that does something to each node's data.

  "This function is supposed to do something with the data at each node in the list. However, the library has no idea what the user wants to do with the data, so instead takes in a function pointer, which is stored in the parameter do_func. This is a pointer to a function that the user themselves wrote, defining what to do with each piece of data in the doubly linked list.

  do_func should be called on the node's data and not the node iteself. The user of your list library shouldn't have to deal or have the knowledge of the node implementations in the list, so do_func is written by the user to run on the node's data.""
  */
void traverse(dllist *d, dllist_op do_func) {
    if (d == NULL || d->head == NULL || d->size == 0) {
        return;
    } else {
        node* current = d->head;
        for(int i = 0; i < d->size; i++) {
            do_func(current->data); //do_func takes in an argument
            current = current->next;
        }
        free(current);
    }

    //You can set non-constant pointer = const pointer.
}

/*
    collin said you can do void** array = malloc(dllist->size * sizeof(void*));
    then run a for loop and do the arrary[i] = current->data;

    Brandon said to run this command: v valgrind --leak-check=yes --show-reachable=yes --tool=memcheck ./test
    
*/
