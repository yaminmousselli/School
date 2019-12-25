typedef unsigned char u8;
typedef unsigned short u16;
typedef unsigned int u32;

typedef volatile unsigned char vu8;
typedef volatile unsigned short vu16;
typedef volatile unsigned int vu32;

typedef signed char s8;
typedef signed short s16;
typedef signed int s32;

typedef volatile signed char vs8;
typedef volatile signed short vs16;
typedef volatile signed int vs32;

#define REG_DISPCNT *((u16*) 0x4000000)
#define SCANLINECOUNTER (*(vu16 *) 0x4000006)

#define BG2_ENABLE (1 << 10)
#define MODE3 3

#define RGB(r, g, b) ((r) | ((g) << 5) | ((b) << 10))
#define WIDTH 240
#define HEIGHT 160

/* Buttons */
#define BUTTON_A      (1<<0)
#define BUTTON_B      (1<<1)
#define BUTTON_SELECT (1<<2)
#define BUTTON_START  (1<<3)
#define BUTTON_RIGHT  (1<<4)
#define BUTTON_LEFT   (1<<5)
#define BUTTON_UP     (1<<6)
#define BUTTON_DOWN   (1<<7)
#define BUTTON_R      (1<<8)
#define BUTTON_L      (1<<9)

#define BUTTONS (*(vu16*) 0x04000130)

#define KEY_DOWN_NOW(key) (~(BUTTONS) & key)
 
#define START_ON_FIFO_EMPTY 0x30000000

extern u16* videoBuffer;

void setPixel(int x, int y, u16 color);
void waitForVblank();
