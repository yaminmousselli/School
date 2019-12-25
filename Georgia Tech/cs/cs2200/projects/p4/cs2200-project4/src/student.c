/*
 * student.c
 * Multithreaded OS Simulation for CS 2200
 *
 * This file contains the CPU scheduler for the simulation.
 */

#include <assert.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#include "os-sim.h"

/** Function prototypes **/
extern void idle(unsigned int cpu_id);
extern void preempt(unsigned int cpu_id);
extern void yield(unsigned int cpu_id);
extern void terminate(unsigned int cpu_id);
extern void wake_up(pcb_t *process);

//mine
extern void enqueue_pcb(pcb_t* pcb);
extern void wait_pcb(void);
extern pcb_t* dequeue_pcb(void);
extern pcb_t* release_pcb(unsigned int cpu_id);
extern void mark_and_schedule(unsigned int cpu_id, process_state_t state);

/*
 * current[] is an array of pointers to the currently running processes.
 * There is one array element corresponding to each CPU in the simulation.
 *
 * current[] should be updated by schedule() each time a process is scheduled
 * on a CPU.  Since the current[] array is accessed by multiple threads, you
 * will need to use a mutex to protect it.  current_mutex has been provided
 * for your use.
 */
static pcb_t** current;
static pthread_mutex_t current_mutex;


/*
 * create head and tail pointers to maintain ready queue
 * pcb_t struct is typedefed in os-sim.h
*/

static pcb_t* head = NULL;
static pcb_t* tail = NULL;

/* A lock and a condition variable each have a queue associated with them.
 * To have ownership of the lock, you have to be at the front of the lock queue.
 */
//this is for the ready Q not empty
static pthread_cond_t has_pcb;
//this is the mutex for the ready queue
static pthread_mutex_t pcb_lock;


static int time_slice;
static int round_robin;
static int sRTF;
//unsigned can't hold a negative value
static unsigned int cpu_count;


void enqueue_pcb(pcb_t* pcb) {
    pthread_mutex_lock(&pcb_lock);
    if (!tail) {
        pcb_t* new_tail = pcb;
        new_tail->next = NULL;
        tail = new_tail;
        head = new_tail;
    } else {
        //encountered major problem here because of not setting next to null
        pcb->next = NULL;
        tail->next = pcb;
        tail = tail->next;
    }
    pthread_cond_signal(&has_pcb);
    pthread_mutex_unlock(&pcb_lock);
}

pcb_t* dequeue_pcb() {
    pthread_mutex_lock(&pcb_lock);
    if (!head) {
        pthread_mutex_unlock(&pcb_lock);
        return NULL;
    } else {
        pcb_t* toBeReturned = head;
        head = head->next;
        // if size == 1
        if (!head) {
            tail = NULL;
        }

        pthread_mutex_unlock(&pcb_lock);
        //toBeReturned->next = NULL;
        return toBeReturned;
    }
}

extern void wait_pcb() {
    /* Must lock the ready queue before null check b/c there's a chance that the
     * ready queue head could become null before you get the lock, which would
     * result in an error if you hadn't locked before null check 
     */
    pthread_mutex_lock(&pcb_lock);
    int has_head = head != NULL;
    if (!has_head) {
        //implicit unlock and lock.
        pthread_cond_wait(&has_pcb, &pcb_lock);
    }
    pthread_mutex_unlock(&pcb_lock);
}

extern pcb_t* release_pcb(unsigned int cpu_id) {
    pthread_mutex_lock(&current_mutex);
    pcb_t* pcb = current[cpu_id];
    current[cpu_id] = NULL;
    pthread_mutex_unlock(&current_mutex);

    return pcb;
}

//static declaration follows non-static declaration
static void schedule(unsigned int cpu_id);

extern void mark_and_schedule(unsigned int cpu_id, process_state_t state) {
    pcb_t* pcb = release_pcb(cpu_id);
    pcb->state = state;
    schedule(cpu_id);
}

/*
 * schedule() is your CPU scheduler.  It should perform the following tasks:
 *
 *   1. Select and remove a runnable process from your ready queue which
 *	you will have to implement with a linked list or something of the sort.
 *
 *   2. Set the process state to RUNNING
 *
 *   3. Call context_switch(), to tell the simulator which process to execute
 *      next on the CPU.  If no process is runnable, call context_switch()
 *      with a pointer to NULL to select the idle process.
 *	The current array (see above) is how you access the currently running process indexed by the cpu id.
 *	See above for full description.
 *	context_switch() is prototyped in os-sim.h. Look there for more information
 *	about it and its parameters.
 */
static void schedule(unsigned int cpu_id) {
    pcb_t* process = dequeue_pcb();

    //@1615
    pthread_mutex_lock(&current_mutex);

    if (process) {
        process->state = PROCESS_RUNNING;
    }

    current[cpu_id] = process;
    pthread_mutex_unlock(&current_mutex);

    context_switch(cpu_id, process, time_slice);
}


/*
 * idle() is your idle process.  It is called by the simulator when the idle
 * process is scheduled.
 *pcb
 * This function should block until a process is added to your ready queue.
 * It should then call schedule() to select the process to run on the CPU.
 */
extern void idle(unsigned int cpu_id) {
    wait_pcb();
    schedule(cpu_id);
}


/*
 * preempt() is the handler called by the simulator when a process is
 * preempted due to its time_slice expiring.
 *
 * This function should place the currently running process back in the
 * ready queue, and call schedule() to select a new runnable process.
 */
extern void preempt(unsigned int cpu_id) {
    pthread_mutex_lock(&current_mutex);
    pcb_t* process = current[cpu_id];
    process->state = PROCESS_READY;
    pthread_mutex_unlock(&current_mutex);

    enqueue_pcb(process);
    schedule(cpu_id);
}


/*
 * yield() is the handler called by the simulator when a process yields the
 * CPU to perform an I/O request.
 *
 * It should mark the process as WAITING, then call schedule() to select
 * a new process for the CPU.
 */
extern void yield(unsigned int cpu_id) {
    mark_and_schedule(cpu_id, PROCESS_WAITING);
}


/*
 * terminate() is the handler called by the simulator when a process completes.
 * It should mark the process as terminated, then call schedule() to select
 * a new process for the CPU.
 */
extern void terminate(unsigned int cpu_id) {
    mark_and_schedule(cpu_id, PROCESS_TERMINATED);
}


/*
 * wake_up() is the handler called by the simulator when a process's I/O
 * request completes.  It should perform the following tasks:
 *
 *   1. Mark the process as READY, and insert it into the ready queue.
 *
 *   2. If the scheduling algorithm is SRTF, wake_up() may need
 *      to preempt the CPU with the highest remaining time left to allow it to
 *      execute the process which just woke up.  However, if any CPU is
 *      currently running idle, or all of the CPUs are running processes
 *      with a lower remaining time left than the one which just woke up, wake_up()
 *      should not preempt any CPUs.
 *	To preempt a process, use force_preempt(). Look in os-sim.h for
 * 	its prototype and the parameters it takes in.
 */
extern void wake_up(pcb_t *process) {
    process->state = PROCESS_READY;
    enqueue_pcb(process);

    if (sRTF) {
        int cpu_with_max_time_slice = -1;
        unsigned int max_time_slice = process->time_remaining;
        process->state = PROCESS_WAITING;
        pthread_mutex_lock(&current_mutex);

        //locate cpu with maximum time slice which is the SRT
        for (int i = 0; i < cpu_count; i++) {
            //skip over idle CPUs
            if (current[i] == NULL) {
                cpu_with_max_time_slice = -1;
                break;
            }
            //save the maximum time slice
            if (current[i]->time_remaining < max_time_slice) {
                max_time_slice = current[i]->time_remaining;
                cpu_with_max_time_slice = i;
            }
        }
        pthread_mutex_unlock(&current_mutex);

        //preempt the CPU with the maxiumum time slice
        if (cpu_with_max_time_slice > -1) {
            force_preempt(cpu_with_max_time_slice);
        }
    }
}


/*
 * main() simply parses command line arguments, then calls start_simulator().
 * You will need to modify it to support the -r and -s command-line parameters.
 */
int main(int argc, char* argv[]) {
    time_slice = -1;
    /* Parse command-line arguments */
    if (argc < 2)
    {
        fprintf(stderr, "CS 2200 OS Sim -- Multithreaded OS Simulator\n"
            "Usage: ./os-sim <# CPUs> [ -r <time slice> | -s ]\n"
            "    Default : FIFO Scheduler\n"
            "         -r : Round-Robin Scheduler\n"
            "         -s : Shortest Remaining Time First Scheduler\n\n");
        return -1;
    }
    //cpu_count = strtoul(argv[1], NULL, 0);
    cpu_count = (unsigned int) atoi(argv[1]);

    if (argc > 2) {
        if (argc > 3 && strcmp(argv[2], "-r") == 0) {
            round_robin = 1;
            time_slice = atoi(argv[3]);
        }
        if (strcmp(argv[2], "-s") == 0) {
            sRTF = 1;
        }
    }

    /* FIX ME - Add support for -r and -s parameters*/
    pthread_cond_init(&has_pcb, NULL);
    pthread_mutex_init(&pcb_lock, NULL);

    /* Allocate the current[] array and its mutex */
    current = malloc(sizeof(pcb_t*) * cpu_count);
    assert(current != NULL);
    pthread_mutex_init(&current_mutex, NULL);

    /* Start the simulator in the library */
    // I'm getting a compiler warning without the cast.
    // that's why I casted in your statement
    start_simulator((unsigned int) cpu_count);

    return 0;
}
