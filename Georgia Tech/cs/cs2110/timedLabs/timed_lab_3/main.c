#include "myLib.h"
#include "DMA.h"
#include "rle.h"

//run, rundata, run, rundata....
//Use DMA to draw any run that is greater than 2


void drawDecompressedImage(const u16* data);

int main(void) {

    REG_DISPCNT = 1027;

    // Call this function with rle2 and rle3 also to ensure it works!
    drawDecompressedImage(rle3);
    /*
        You don't call this function three times. You just swap out the argument image.

        drawDecompressedImage(rle2);
        drawDecompressedImage(rle3);
    */
    while(1);
}

/**
 * Draws an RLE encoded image decompressed onto the videoBuffer.
 * Remember you may only call setPixel when the run length is 2 or less
 * You must use DMA if the run length is 3 or greater!
 *
 * (Heavy penalty if you don't follow these rules)
 */
void drawDecompressedImage(const u16* data) {
    /*
        The videobuffer is used for us to put the pixels in there and it displayus the image. For any run that is less than or equal to 2, than we use the videobuffer.

        DMA uses memory-mapped IO which makes it faster using a buffer.
    */
    int run = 0; //this is for the run-length
    /*
    The variable below tells us where we are within the data array which contains the runs and the pixel data.
    */
    int indexInDataArray = 0;
    /*
        We wait for vblank to prevent tearing and it gives us time to draw the image that's based on scanline counter. You draw when scanline counter is outside the range of your screen and that exactly is what prevents tearing.
    */
    waitForVblank();
    //i represents how many pixels you've displayed.
    for (int i = 0; i < 240*160; i += run) {
        run = data[indexInDataArray];

        if (run > 2) {
        /*
            For a larger run (> 2), draw with DMA for efficiency.

            videobuffer stores pixel data. videobuffer IS A PLACE IN MEMORY.
            The videoBuffer pointer defined in myLib.c starts at address 0x6000000. The last pixel that you draw to would be at 0x6000000 + (240*160)

            For DMA.src: It needs a pointer to what it's going to draw.

            For DMA.dst: It needs a pointer to where it's going to draw. Pointer + i.

            For DMA.cnt: you are telling it whether or not your source is fixed, destination is fixed and the width to which it's going to be drawing. DMA_DESTINATION_INCREMENT occurs by default (it is optional to put)
        */
        DMA[3].src = &(data[indexInDataArray + 1]); // REMEMBER ACCESS PIXEL DATA
        DMA[3].dst = videoBuffer + i;

        DMA[3].cnt = run | DMA_SOURCE_FIXED | DMA_ON;
        } else {
            /*
                For smaller run (<= 2), draw the image using videoBuffer. Having a short run would defeat the purpose of using DMA.

                We need to loop run number of times.
            */
            u16 iterate = 0;
            while (iterate < run) {
                /*
                i is the offset to where you start drawing and iterate would be the traditional loop control in a for loop where you're using it to move across the array until it hits 2 runs.

                indexInDataArray + 1 gives you pixel data. indexInDataArray is being incremented by 2 so it always points to run length
                */
                videoBuffer[i + iterate] = data[indexInDataArray + 1];
                iterate++;
            }

        }
        indexInDataArray += 2;
    }


  //   if (*data <= 2) {
  //       setPixel(row, col, *data);
  //       waitForVblank();
  //   } else {
  //     DMA[3].src =
  //     DMA[3].dst = videoBuffer +
  //     DMA[3].cnt = 240 | DMA_ON;
  // }

}
