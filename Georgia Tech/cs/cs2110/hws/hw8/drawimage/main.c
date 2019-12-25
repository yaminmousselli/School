// Draw an image from bmptoc
// Name: Yamin Mousselli

/*
Mode 3 is good for basic shit
Mode 4 is good for sprites and animation
*/

// If you want to test this with one of your own images, you may do so by
// including the appropriate header file. However, when you submit this file,
// it must contain ONLY #include "balloon.h" or else our grader will not work
// and you will not receive credit for this file.
#include "balloon.h"

#define REG_DISPCNT *(unsigned short*) 0x4000000
#define MODE_3 3
#define BG2_EN (1 << 10)



unsigned short *videoBuffer = (unsigned short*) 0x6000000;

//x is row and y is column
void drawImage3(int x, int y, int width, int height, const unsigned short *image) {

    // TODO Draw the image with the top left corner at (x,y).
    // Recall that the GBA screen is 240 pixels wide, and 160 tall.
    // The videoBuffer is a 1-dimensional array of these pixels.
    // For example, to set pixel (37,21) to white:
    //   videoBuffer[21 * 240 + 37] = 0x7FFF;

    /*
        You need a videobuffer because that's not always the same color when you're going through every single pixel in the rectangle of the image.
    */
	for(int ix = 0; ix < width; ix++) {
		for(int iy = 0; iy < height; iy++) {
			// MAKE SURE YOU DON'T WRITE TO INVALID MEMORY!
			if (!(iy + y >= 160 || ix + x >= 240)) {
				videoBuffer[(y+iy) * 240 + ix + x] = image[ix + iy * width];
			}
		}
	}
}

int main() {

    // 1. TODO Set REG_DISPCNT appropriately for drawing in mode 3.
    // Use the #define fields near the top of this file to accomplish this.

    /*
        We're setting the display mode to mode 3. We're showing this background.
    */
    REG_DISPCNT = MODE_3 | BG2_EN;

    // 2. TODO Call your drawing function using parameters from the header.
    // You can use whatever you want for X and Y. If you use a high X and the width
    // goes beyond the edge of the screen, it will wrap around onto the next line.
    // This is normal.
    // You may test your own images with calls to drawImage3 here using the
    // appropriate fields from your header, but when you submit this file it
    // must contain ONLY a valid call using the balloon.h parameters, or else
    // our grader will not work and you will not receive credit for this file.

    //row and then column. Should it be 0, 0 or 1,1
    drawImage3(0,0, BALLOON_WIDTH, BALLOON_HEIGHT, balloon_data);

    // 3. (already done) wait forever after drawing.
    while(1);

    return 0;
}
