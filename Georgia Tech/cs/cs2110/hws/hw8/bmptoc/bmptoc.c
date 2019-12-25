// bmptoc.c
// Name: Yamin Mousselli

#include <ctype.h>
#include <errno.h>
#include <stdint.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

// Your program will only support BMP files that
// have a max size that is equivalent to the size
// of the GBA screen.
#define MAX_BMP_FILE_SIZE (0x36 + 240 * 160 * 4)
unsigned char data_arr[MAX_BMP_FILE_SIZE];

//Function prototypes
unsigned int getWidth(unsigned char*);
unsigned int getHeight(unsigned char*);

//Return codes for clarity
const int SUCESS = 0;
const int FAILURE = 1;

int main(int argc, char *argv[]) {
    // The implementation of the program is broken down into steps below.

    int toBeReturned = FAILURE;

    // 1. Make sure the user passed in the correct number of arguments
    // Hint: You'll want to see what value argc has.

    /*<arg> is standard notation and [arg] is optional
        *arg grabs the 0th argument, aka program name. It's the same as argv[0]
        *argv[] is a pointer to an array of strings aka an array of char arrays
    */
    if (argc != 2) {
        printf("Usage: ./bmptoc <file.bmp>\n");
        printf("Passed in an incorrect number of arguments.\n");
		//fprintf(stderr, "File: %s <file.bmp>\n", *argv);
		goto RETURN_LABEL;
	}


    // 2. Open the file. If it doesn't exist, tell the user and then exit
    // Hint: read the man page for fopen by typing `man fopen`
    //       either in your terminal or google

    // "rb" = reading binary file.
    FILE *infile = fopen(argv[1], "rb");
    if (!infile) {
		perror("Sorry sunshine, we can't open the input file");
		goto RETURN_LABEL;
	}


    // We've implemented this part for you. It reads in the bmp file into
    // a buffer (assuming you've open the file properly), then closes the file.
    fread(data_arr, 1, MAX_BMP_FILE_SIZE, infile);
    if(ferror(infile)) {
       printf("An error occurred when reading the file. Quitting.");
       fclose(infile);
       exit(1);
    }
    fclose(infile);

    // 3. Get the width and height of the image. Read the BMP file format
    // to determine what you should do for this one. Then implement the
    // functions at the end of the file.
    unsigned int width = getWidth(data_arr);
    unsigned int height = getHeight(data_arr);

    // We've implemented outputing the header file correctly assuming your
    // getWidth and getHeight work properly. Read this code carefully and
    // read the man pages because you'll have to do something similar
    // for generating the C file.
    FILE *outheader;
    char filename[100];

    strcpy(filename, argv[1]);
    int fname_len = strlen(filename);
    filename[fname_len - 3] = 'h';
    filename[fname_len - 2] = '\0'; // file.bmp -> file.h
    if(!(outheader = fopen(filename, "w"))) {
        printf("Error creating file %s: %s\n", filename, strerror(errno));
        exit(1);
    }
    filename[fname_len - 4] = '\0';
    char *ptr = filename;
    while(*ptr) {
        *ptr = toupper(*ptr); // file.h -> FILE
        ++ptr;
    }

    /* File format:
     *
     *  #define FILE_WIDTH 100
     *  #define FILE_HEIGHT 150
     *  const unsigned short file_data[15000];
     */

    fprintf(outheader, "#define %s_WIDTH %d\n", filename, width);
    fprintf(outheader, "#define %s_HEIGHT %d\n", filename, height);

    strcpy(filename, argv[1]);
    filename[fname_len - 4] = '\0';
    fprintf(outheader, "extern const unsigned short %s_data[%d];\n", filename, width * height);

    fclose(outheader);


    // 4. Now you are responsible for creating a c file with the same basename
    // as the bmp file that was passed in file.bmp -> file.c
    // Hint: Use the code above as a guide for how to
    //       initialize the filename and  open the file.
    FILE *outcfile;
    char cfilename[100];
    strcpy(cfilename, argv[1]);

    int fname_len2 = strlen(cfilename);
    cfilename[fname_len2 - 3] = 'c';
    cfilename[fname_len2 - 2] = '\0'; // file.bmp -> file.h
    if(!(outcfile = fopen(cfilename, "w"))) {
        printf("Error creating file %s: %s\n", cfilename, strerror(errno));
        exit(1);
    }
    cfilename[fname_len2 - 4] = '\0';
    char *ptr2 = cfilename;
    while(*ptr2) {
        *ptr2 = toupper(*ptr2); // file.h -> FILE
        ++ptr2;
    }

    /* File format:
     *
     *  const unsigned short file_data[15000] = {
     *    0x4521, 0x5B2F, 0x1F69, ...
     *  };
     */

    // 5. We've implemented the first part of the C file which will look like the
    // line above. Note that instead of 15000 we compute width * height.
    //

    fprintf(outcfile, "const unsigned short %s_data[%d] = {\n", filename, width * height);


    // 6. Iterate over the pixel data convert it to hex and write the output to the C file.
    // Hint: Pay careful attention to the format of the BMP file. The rows are stored
    // in reverse order. We've structured your loop so that if you print the pixels in
    // the order they are iterated over it will be correct.

    // Useful variables:
    // data_arr - array of bytes that contains all the data in the bmp file
    // height/width - self explanatory, just so you're aware they're there

    for (int r = height - 1; r >= 0; r--) {
        for (unsigned int c = 0; c < width; c++) {

            unsigned char blue = (data_arr[0x36+(c*4)+(4*width*r)]) >> 3 & 0x1F;
            unsigned char green = (data_arr[0x36+(c*4)+(4*width*r) +1]) >> 3 & 0x1F;
            unsigned char red = (data_arr[0x36+(c*4)+(4*width*r) +2]) >> 3 & 0x1F;

            unsigned short pixel = (unsigned int)(blue << 10) | (unsigned int) (green << 5) | (unsigned int) (red << 0);
            fprintf(outcfile, "0x%X,\n", pixel);
        }
        // SOME OTHER CODE GOES HERE ????
    }


    // Print the closing brace in the array
    fprintf(outcfile, "};");
    fclose(outcfile);


    // You're done with this part, but don't forget there are other files
    // you need to implement code in.
    RETURN_LABEL:
    return toBeReturned;
}

/*
 *
 * Format of a bmp header (data_arr)
 * file offset  |   length   |     field
 * 0x12         |  4 bytes   |  image width
 * 0x16         |  4 bytes   |  image height
 *
 */

unsigned int getWidth(unsigned char *data_arr) {
    return *(unsigned int*) (data_arr + 0x12);
}

unsigned int getHeight(unsigned char *data_arr) {
    return *(unsigned int*) (data_arr + 0x16);
}
