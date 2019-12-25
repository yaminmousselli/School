CC = gcc
CFLAGS = -std=c99 -pedantic -Wall -Werror -Wextra
DEBUG_FLAGS = -g -DDEBUG

run-test : build-debug
	./test

run-debug : build-debug
	./test

run-gdb : build-debug
	gdb ./test

run-valgrind : build-debug
	valgrind --leak-check=yes --show-reachable=yes --tool=memcheck ./test

debug: build-debug

build-debug : CFLAGS += $(DEBUG_FLAGS)
build-debug : arraylist.o test.o arraylist.h
	$(CC) $(CFLAGS) test.o arraylist.o -o test

arraylist.o : arraylist.c arraylist.h
	$(CC) $(CFLAGS) -c arraylist.c

test.o : test.c arraylist.h
	$(CC) $(CFLAGS) -c test.c

clean :
	@rm -f *.o test

submit :
	tar -czf tl4_submission.tar.gz arraylist.c
