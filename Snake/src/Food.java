//This project was built with the assistance of a tutorial. I do not claim ownership of all code

import java.awt.Rectangle;

public class Food {
	private int x;
	private int y;
	
	public Food(Snake player) {
		this.random_spawn(player);
	}
	
	public void random_spawn(Snake player)	{	//generates random spawn for food, checks for bad spawn (within snake)

		boolean onSnake = true;
		while(onSnake) {
			onSnake = false;
			
			x = (int)(Math.random() * Game.width - 2);	//generate random x coordinate within the game grid //- 1
			y = (int)(Math.random() * Game.height - 2);		//generate random y coordinate within the game grid //- 1
			
			for (Rectangle r : player.getBody()){		//cross check food coord. w/ body of snake to prevent bad food spawn
				if(r.x == x && r.y == y) {
					onSnake = true;					//if bad food spawn, re-generate food coord.
				}
			}
		}
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
