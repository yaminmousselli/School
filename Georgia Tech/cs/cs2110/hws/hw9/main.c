#include "myLib.h"
#include <stdlib.h>
#include <stdio.h>
#include "text.h"
#include "paddle.h"
#include "title.h"
#include "start1.h"
#include "start2.h"
#include "start3.h"
#include "gameOver.h"

extern int buttons;
extern int oldButtons;

/* Player */
PLAYER player;

/* Obstacles */
int brickCount = 40;
int deadBricks = 0;
BRICK bricks[40]; //array of BRICK structs

/* Ball */
BALL ball;
int dir[2] = {0, 0}; //an array for ball direction [x, y]

/* Game Variables */
unsigned short bgColor = GREY;
unsigned short bkColors[4] = {RED, GREEN, BLUE, YELLOW};
enum {STARTSCREEN, GAMESCREEN, LOSESCREEN, WINSCREEN, PAUSESCREEN};
/*
seed used to generate a random number that is used to determine how the ball is initially declared
*/
int seed;
int startState; //this has to do with the flashing start button
int startDir;
int state; //this is the actual game state that you are in.
int highScore = 0;

//Must declare this before main if you don't have them in a header file.
void start();
void game();
void lose();
void win();
void pause();

int main() {
	initialize();
	while(1) { //anything other than 0 is true

		oldButtons = buttons;
		buttons = BUTTONS;

		if (BUTTON_PRESSED(BUTTON_SELECT)) {
			initialize();
		}
		switch(state) {
			case STARTSCREEN:
				start();
				break;
			case GAMESCREEN:
				game();
				break;
			case PAUSESCREEN:
				pause();
				break;
			case LOSESCREEN:
				lose();
				/* break isn't needed here. Game reinitializes upon the user pressing enter and the win and lose states are static so you don't need to break to change anything
				*/
			case WINSCREEN:
				win();
				/* break isn't needed here. Game reinitializes upon the user pressing enter and the win and lose states are static so you don't need to break to change anything
				*/
		}
	}
}

/*
Initializes the start screen.
*/
void initialize() {
	REG_DISPCTL = MODE3 | BG2_ENABLE;
	// fillScreen(BLACK);
	drawImage3(title, 0, 0, 160, 240);
	drawImage3(start1, 100, 60, 20, 100);
	startState = 0;
	startDir = 1;
	seed = 0;
	brickCount = 40;
	state = STARTSCREEN;
}

/*
Starts the actual game play.
*/
void initializeGame() {
	//INITIALIZING EVERYTHING IN THE GAME WHEN TRANSISTION TO GAMESTATE SCREEN

	//Game Variables
	deadBricks = 0;

	//Player Variables
	player.width = SCREEN_WIDTH/8;
	player.height = 6;
	// old row and column exist so you can erase the player old image. Otherswise the old player image would be persistent
	player.oldRow = SCREEN_HEIGHT - 20;
	player.oldCol = SCREEN_WIDTH/2 - player.width/2;
	player.row = SCREEN_HEIGHT - 20;
	player.col = SCREEN_WIDTH/2 - player.width/2;
	//player.color = WHITE; For the brick which I changed to a picture.
	player.dead = 0;
	player.dir = 0;
	player.speed = 4;

	//Ball Variables
	ball.width = 4;
	ball.height = 4;
	ball.row = player.row - ball.height - 1;
	ball.col = player.col + player.width/2 - ball.width/2;
	ball.oldRow = ball.row;
	ball.oldCol = ball.col;
	ball.color = YELLOW;
	ball.active = 0;
	ball.speed = 1;

	//Bricks
	BRICK brick;
	brick.row = 0;
	brick.col = 5;
	brick.active = 1;
	brick.height = 10;
	brick.width = SCREEN_WIDTH/10 - 2;
	brick.layers = 4;
	for (int i = 0; i < brickCount; i++) {
		if ((!(i%10)) && (!(i== 0))) { //After every 10 bricks
			brick.row += brick.height + 1; //Move down 1 Row
			--brick.layers; //Decrease toughness of bricks
			brick.col = 5; //Reset the column
		}
		bricks[i] = brick;
		brick.col += brick.width + 1;
	}

	for (int i = 0; i < 2; i++) {
		dir[i] = 0;
	}
}

void start() {
	if(BUTTON_PRESSED(BUTTON_START)) {
		fillScreen(bgColor); //bgColor is gray.
		drawString(148, 4, "Score: 0", CYAN);
		srand(seed);
		initializeGame();
		state = GAMESCREEN;
	}

	if (!BUTTON_HELD(BUTTON_START)) {
		++seed;
	}
	/*startState has to do with the flashing of the start button. When you're in state, it runs the cycle over and over. When it runs a cycle in the main, it checks thte state you're in and executes a function based on the state you're in. Until you haven't pressed start, it'll increment seed which is a measure of time. Everytime it changes state, 15000 cycles have passed. That's when it switches from start 1, to start 2, to start 3, etc.
	*/
	if ((seed % 15000) == 0) {
		if ((startState == 2) || (startState == 0 && (startDir == (-1)))) {
			startDir *= (-1);
		}

		startState += startDir;
		switch(startState) {
			case 0: //when it is fully lit. 100% opaque
				drawImage3(start1, 100, 60, 20, 100);
				break;
			case 1: //when it dims. made it more opaque. 50% opaque.
				drawImage3(start2, 100, 60, 20, 100);
				break;
			case 2: //when the image is gone. 0% opaque.
				drawImage3(start3, 100, 60, 20, 100);
				break;
		}
	}
}

void game() {
	update();
	waitForVBlank(); // we wait for vblank to prevent tearing of what which checks the SCANLINECOUNTER
	draw();

	if(BUTTON_PRESSED(BUTTON_START)) {
		fillScreen(BLACK);
		drawString(76, 102, "PAUSED", WHITE);
		state = PAUSESCREEN;
	}

	if (player.dead) {
		drawImage3(gameOver, 0, 0, 160, 240);
		/*I moved this up here because it was only displaying YOU LOSE everytime you hit a high score which is not what I wanted to happen.
		Mention to Preston that I accidently drew "You Lose" only when you beat your high score which is wrong. You show it every time ball dies.*/
		drawString(30, 70, "Not everyone gets a trophy! How does it feel?", BLACK);
		if (deadBricks > highScore) {
			char hiScore[41];
			highScore = deadBricks;
			drawString(50, 78, "New High Score!", BLACK);
			sprintf(hiScore,"High Score: %d", highScore);
			drawString(70, 84, hiScore, BLACK);

		} else {
			char hiScore[41];
			sprintf(hiScore,"High Score: %d", highScore);
			drawString(92, 84, hiScore, BLACK);
		}
		state = LOSESCREEN;
	}

	if (deadBricks == brickCount) {
		fillScreen(BLUE);
		drawString(76, 96, "YOU WIN!", BLACK);
		state = WINSCREEN;
	}
}

void pause() {
	if (BUTTON_PRESSED(BUTTON_START)) {
		fillScreen(bgColor);
		char score[41];
		sprintf(score, "Score: %d", deadBricks);
		drawRectInBounds(148, 46, 8, 24, bgColor);
		drawString(148, 4, score, CYAN);
		state = GAMESCREEN;
	}
}

void lose() {
	if (BUTTON_PRESSED(BUTTON_START)) {
		initialize();
		state = STARTSCREEN;
	}
}

void win() {
	if (BUTTON_PRESSED(BUTTON_START)) {
		initialize();
		state = STARTSCREEN;
	}
}


void update() {
	updatePlayer(&player);
	updateBall(&ball, &player);
	updateBricks(&ball);
}

void updatePlayer(PLAYER* p) {
	//Update old vars
	p->oldRow = p->row;
	p->oldCol = p->col;

	//Movement of the player
	if (BUTTON_HELD(BUTTON_RIGHT)) {
			/*if player column is less than 240 - width, then go to the right and set the direction.*/
   		if (p->col < 240 - p->width) {
   			p->col += p->speed;
   			if (p->col  > (240 - p->width)) {
   				p->col = (240 - p->width);
   			}
   			p->dir = 1;

   		}
   	}

   	if (BUTTON_HELD(BUTTON_LEFT)) {
   		if (p->col > 0 ) {
   			p->col -= p->speed;
   			if (p->col < 0) {
   				p->col = 0;
   			}
   			p->dir = -1;
   		}
   	}
   	//resets the direction to 0 when the player is stationary. otherwise say you moved left and then stoppped, ball would still move left instead of right.
   	if (!BUTTON_HELD(BUTTON_LEFT) && !BUTTON_HELD(BUTTON_RIGHT)) {
   		p->dir = 0;
   	}
}
//BALL MOVEMENT
void updateBall(BALL* b, PLAYER* p) {
	//Update old variables
	b->oldRow = b->row;
	b->oldCol = b->col;
	// This is for if the ball hits the bottom of the screen.
	if (b->row + b->height >= SCREEN_HEIGHT || deadBricks == brickCount) {
		p->dead = 1;
	}

	/* Before the Ball is released */
	if (!(b->active == 1)) {
		if (BUTTON_PRESSED(BUTTON_Q)) {
			int randy = (rand() % 3); //Randomizes direction ball is shot.
			switch (randy)
			{
				case 0:
					dir[0] = 0;
					break;
				case 1:
					dir[0] = -1;
					break;
				case 2:
					dir[0] = 1;
					break;
			}
			dir[1] = -1; //Ball moves upwards
			b->active = 1; //Activates the ball.
		}
		//Keeps ball in the middle of the paddle.
		b->col = p->col + (p->width/2) - (b->width/2);
	}

	ballCollidePlayer(&ball, &player);
	moveBall(&ball);

}

void updateBricks() {
	 for (int i = 0; i < brickCount; i++) {
		//bk points to the address of each brick
	 	BRICK * bk = &bricks[i];
		if (bk->active) {
			//Checks for collisions.
			ballCollideBrick(bk, &ball); /*this function deals with lowering the layer and deactivating bricks */

			//Changes colors based off of layers left.
			if (bk->layers == 4) {
				bk->color = bkColors[3];
			} else if (bk->layers == 3) {
				bk->color = bkColors[2];
			} else if (bk->layers == 2) {
				bk->color = bkColors[1];
			} else {
				bk->color = bkColors[0];
			}
		}
	}
}

void draw() {
	erasePlayer(&player);
	eraseBall(&ball);
	eraseBricks();
	drawBall(&ball);
	drawPlayer(&player);
	drawBricks();


}

/* Erases old image */
void erasePlayer(PLAYER* p) {
	drawRectInBounds(p->oldRow, p->oldCol, p->height, p->width, bgColor);
}

void eraseBall(BALL* b) {
	drawRectInBounds(b->oldRow, b->oldCol, b->height, b->width, bgColor);
}

void eraseBricks() {
	for (int i = 0; i < brickCount; i++) {
		BRICK * bk = &bricks[i];
		if (!(bk->active)) {
			drawRectInBounds(bk->row, bk->col, bk->height, bk->width, bgColor);
		}
	}
}

/* Draws new frame's image of player */
void drawPlayer(PLAYER* p) {
	drawImage3( paddle,p->row, p->col, p->height, p->width);
}

void drawBall(BALL* b) {
	drawRectInBounds(b->row, b->col, b->height, b->width, b->color);
}

void drawBricks() {
	for (int i = 0; i < brickCount; i++) {
		BRICK* bk = &bricks[i];
		if (bk->active) {
			drawRectInBounds(bk->row, bk->col, bk->height, bk->width, bk->color); //Base brick
		}
	}
}

void moveBall(BALL* b) {
	if (b->row < 1) {
		dir[1] = 1; //Ball hits the top wall
	}

	if (b->col > SCREEN_WIDTH - b->width) {
		dir[0] = -1; //Ball hits the right wall
	}

	if (b->col < 1) {
		dir[0] = 1; //Ball hits the left wall
	}

	//Applies velocity to ball.
	b->col += dir[0]*b->speed;
	b->row += dir[1]*b->speed;
}

void ballCollidePlayer(BALL* b, PLAYER* p) {
	/*
	FIRST THING IS TO CHECK COLLISION --> FIRST IF STATEMENT WILL move ball up after collision through changing to go up.

	second and third if statements give direction to ball when player is moving



	conditions to see if the ball is touching the player and is confined with the PLAYER

	*/
	if ((b->row + b->height >= p->row) && (b->row <= p->row + p->height) && (b->col + b->width >= p->col) && (b->col <= p->col + p->width)) {
		dir[1] = (-1); //
		//If player is moving, then give ball same direction
		if (p->dir == 1)  {
			dir[0] = 1; // ball goes right when player goes right, IF THEY COLLIDE.
		}

		if (p->dir == -1)  {
			dir[0] = -1; //Left
		}
	}
}

void ballCollideBrick(BRICK* bk, BALL* b) {
	/* This method checks for collisions on every side of the brick
		Count is used because there was a problem with corners that double counted the hit as 2 collisions. If a hit is detected, we increment count, if count is some positive number, then that is what is used to indiciate collision  detection when updating the brick
	*/
	int count = 0;
	int x = dir[0];
	int y = dir[1];
	//Top
	if ((b->row <= (bk->row)) && ((b->row + b->height) >= bk->row) && (b->col < bk->col + bk->width) && ((b->col + b->width) > bk->col)) {
		dir[1] = (-1);
		++count;
	}

	//Bottom
	if ((b->row >= (bk->row + bk->height - b->height)) && ((b->row) <= (bk->row + bk->height)) && (b->col < bk->col + bk->width) && ((b->col + b->width) > bk->col)) {
		dir[1] = (1);
		++count;
	}

	//Left
	if ((b->col <= bk->col) && (b->col >= (bk->col - b->width)) && (b->row < (bk->row + bk->height)) && (b->row > bk->row)) {
		dir[0] = (-1);
		++count;
	}
	// Right
	if ((b->col <= (bk->col + bk->width)) && (b->col >= (bk->col + bk->width - b->width)) && (b->row < (bk->row + bk->height)) && (b->row > bk->row)) {
		dir[0] = (1);
		++count;
	}

	/*
	Part of the corner countermeasure.
	Reduces brick layer by 1. */
	if (count >= 1) {
		--bk->layers;
	}

	/*

	EDGE CASE FOR WHEN THE BALL HITS THE CORNER OF THE BRICK
	Changes the ball velocity when the ball is bouncing off the corner based off of the balls old Velocity.

	It only reverses velocity if the ball has least greater than 0 velocity
	*/
	if (count >= 1) {
		if (x == 0) {
			dir[0] = 0;
		}
		if (y == 0) {
			dir[1] = 0;
		}
	}

	//Clears the dead Bricks.
	if (!(bk->layers) && (bk->active)) {
		bk->active = 0;
		++deadBricks;
		char score[41];
		sprintf(score, "Score: %d", deadBricks);
		drawRectInBounds(148, 46, 8, 24, bgColor);
		drawString(148, 4, score, CYAN);
	}
}
