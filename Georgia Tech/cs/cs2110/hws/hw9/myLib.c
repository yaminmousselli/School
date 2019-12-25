#include "myLib.h"

unsigned int oldButtons;
unsigned int buttons;
unsigned short *videoBuffer = (u16 *)0x6000000;

/*
channel is the mode. source is where the image is coming from (pointer to first pixel), destination is always the videoBuffer (holds what you want to put on the screen). DMA iterates over the column, but you must iterate over the row.

*/
void DMANow(int channel, volatile const void* source, volatile const void* destination, unsigned int control) {

	DMA[channel].src = source;
	DMA[channel].dst = destination;
	DMA[channel].cnt = control | DMA_ON;

}

void drawRectInBounds(int row, int col, int height, int width, unsigned short color) {

    /* DMANow requires color to be volatile. r in the for loop is the offset.
    width | DMA_SOURCE_FIXED | DMA_ON is setting the control register
    if DMA SOURCE is not fixed, then leave this part out and it doesn't need to loop.
    */
	volatile unsigned short c = color;
	for(int r = 0; r < height; r++) {
		DMANow(3, &c, &videoBuffer[OFFSET(row + r, col, SCREEN_WIDTH)], width | DMA_SOURCE_FIXED | DMA_ON);
	}

}

//Fills the screen with whatever color is passed in
void fillScreen(unsigned short color) {
	volatile unsigned short c = color;

	DMANow(3, &c, videoBuffer, DMA_SOURCE_FIXED);

}

void setPixel(int row, int col, unsigned short color) {
    //setting 1 pixel.
	VIDEO_BUFFER[OFFSET(row, col, 240)] = color;
}
/*
DMA iterates over the column, but you must iterate over the row.
*/
void drawImage3(const unsigned short* image, int row, int col, int height, int width) {

    /* If you don't implement DMANOW, then the three lines in DMANOW would go in here.

    (unsigned short*)&image[OFFSET(i,0, width)]: This gets the address of some pixel in the image and casts to unsigned short pointer. This will be the src

    &videoBuffer[OFFSET(row+i, col, SCREEN_WIDTH)], (width)): Grabbing the input from above and putting it at a destination. videobuffer takes in the address of where to start drawing
     */
	for(int i = 0; i < height; i++) {
		DMANow(3, (unsigned short*)&image[OFFSET(i,0, width)], &videoBuffer[OFFSET(row+i, col, SCREEN_WIDTH)], (width));
	}
}

// this method gives you a period of time to display pixels. Limits tearing. Has SCANLINECOUNTER go across each row
void waitForVBlank() {
	while(SCANLINECOUNTER > 160);
	while(SCANLINECOUNTER < 160);
}
