/*
Header file that has everything for all the other header files as long you include this header file in the other header files.
*/
#ifndef MY_LIB_H
#define MY_LIB_H


#define REG_DISPCTL *(unsigned short *)0x4000000
#define VIDEO_BUFFER ((unsigned short *)0x6000000)

#define MODE3 3
#define BG2_ENABLE (1<<10)

/* Create a color with the specified RGB values */
#define COLOR(r, g, b) ((r) | (g)<<5 | (b)<<10)

/* Calculate the position of a pixel based on it's row and column
ROW * WIDTH + COLUMN  */
#define OFFSET(R,C,W) ((R)*W+(C))

// Predefined colors using rgb values online
#define RED COLOR(31, 0, 0)
#define GREEN COLOR(0, 31, 0)
#define DARKER_GREEN COLOR(0, 20, 0)
#define BLUE COLOR(0, 0, 31)
#define MAGENTA COLOR(31, 0, 31)
#define CYAN COLOR(0, 31, 31)
#define YELLOW COLOR(31, 31, 0)
#define BLACK 0
#define WHITE COLOR(31, 31, 31)
#define GREY COLOR(5, 5, 5)

/*
Scanline follows the row that you are at on the screen
Volatile is reserved for variables you know are going to change. Both buttons and scan line will change throughout the code. Buttons change because of them being on or off.
*/

#define SCANLINECOUNTER *(volatile unsigned short*) 0x4000006

// Buttons are volatile because the state of each button is constantly changing
#define BUTTONS *(volatile unsigned short*) 0x4000130


#define BUTTON_HELD(key)  (~(BUTTONS) & (key)) //detects 0/1 if btn is held dwn
#define BUTTON_PRESSED(key) (!(~(oldButtons)&(key)) && (~(buttons) & (key)))

#define BUTTON_Q		(1<<0) //should of been A but I changed to Q b/c of my
//keyboard configuration
#define BUTTON_SELECT	(1<<2)
#define BUTTON_START	(1<<3)
#define BUTTON_RIGHT	(1<<4)
#define BUTTON_LEFT		(1<<5)
#define BUTTON_UP		(1<<6)
#define BUTTON_DOWN		(1<<7)


/* Game Specific Macros */
#define BORDER_HEIGHT 20 //Grid goes from top to down

#define SCREEN_WIDTH 240
#define SCREEN_HEIGHT 160

typedef unsigned short u16;

typedef struct {
	int row;
	int col;
	int oldRow;
	int oldCol;
	int width;
	int height;
	int dir; // right (1), left (-1), or stay in the same place (0).
	int speed;
	//u16 color; color is relevant for PLAYER because I changed from a //rectangle to a picture.
	char dead;
} PLAYER;


typedef struct {
	int row;
	int col;
	int height;
	int width;
	/*
	goes through and checks each brick in the array of brick structs and sees if their active, which is either 0 or 1, and if it's active, it checks the layer (4 different colors...variable that dictates what color they are is called layer. This is how it the game determines what color the brick should be. This all happens in updateBricks().
	*/
	int active;
	int layers;
	u16 color;
} BRICK;

/* if you are moving left and the ball is going left then it will move the direction in which you are moving. If you aren't moving, then the ball will just be reflected.

*/
typedef struct {
	int row;
	int col;
	int oldRow;
	int oldCol;
	int width;
	int height;
	int speed;
	u16 color;
	int active;
} BALL;

// -------------------------------DMA-------------------------------------

void DMANow(int channel, volatile const void* source, volatile const void* destination, unsigned int control);

// DMA channel 3 register definitions
#define REG_DMA3SAD         *(volatile u32*)0x40000D4  // source address
#define REG_DMA3DAD         *(volatile u32*)0x40000D8  // destination address
#define REG_DMA3CNT         *(volatile u32*)0x40000DC  // controls the DMA,

typedef struct {
        const volatile void *src;
        const volatile void *dst;
        unsigned int cnt;
} DMA_CONTROLLER;

#define DMA ((volatile DMA_CONTROLLER *) 0x40000B0)

#define DMA_DESTINATION_INCREMENT (0 << 21)
#define DMA_DESTINATION_DECREMENT (1 << 21)
#define DMA_DESTINATION_FIXED (2 << 21)
#define DMA_DESTINATION_RESET (3 << 21)

#define DMA_SOURCE_INCREMENT (0 << 23)
#define DMA_SOURCE_DECREMENT (1 << 23)
#define DMA_SOURCE_FIXED (2 << 23)

#define DMA_REPEAT (1 << 25)

#define DMA_16 (0 << 26)
#define DMA_32 (1 << 26)

#define DMA_NOW (0 << 28)
#define DMA_AT_VBLANK (1 << 28)
#define DMA_AT_HBLANK (2 << 28)
#define DMA_AT_REFRESH (3 << 28)

#define DMA_IRQ (1 << 30)
#define DMA_ON (1 << 31)

//prototype: headers for methods
void setPixel(int, int, unsigned short);
void drawRectInBounds(int row, int col, int height, int width, unsigned short color);
void fillScreen(unsigned short color);
void waitForVBlank();
void drawImage3(const unsigned short* image, int row, int col, int height, int width);


//game specific prototypes which I had to implement
//game logic is in main.c (such as erase)
void initialize();
void initializeGame();
void draw();
void update();
void drawPlayer(PLAYER*);
void erasePlayer(PLAYER*);
void updatePlayer(PLAYER*);
void drawBall(BALL*);
void eraseBall(BALL*);
void updateBall(BALL*, PLAYER*);
void moveBall(BALL*);
void ballCollidePlayer(BALL*, PLAYER*);
void drawBricks();
void eraseBricks();
void updateBricks();
void ballCollideBrick(BRICK*, BALL*);

#endif
