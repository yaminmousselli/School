#ifndef __MY_MALLOC_H__
#define __MY_MALLOC_H__

/* We need this *ONLY* for the size_t typedef */
#include <stdio.h>
/* We need this for uintptr_t */
#include <stdint.h>

typedef struct metadata
{
  short block_size;
  short request_size;
  struct metadata* next;
  struct metadata* prev;
} metadata_t;

enum my_malloc_err {
	NO_ERROR,
	OUT_OF_MEMORY ,
	SINGLE_REQUEST_TOO_LARGE,
	CANARY_CORRUPTED,
};

extern enum my_malloc_err ERRNO;

#endif /* __MY_MALLOC_H__ */
