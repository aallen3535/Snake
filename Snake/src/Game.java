//This project was built with the assistance of a tutorial. I do not claim ownership of all code

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Game implements KeyListener {

	private Snake player;
	private Food food;
	private Graphics graphics;
	
	private JFrame window;
	public static final int width = 30;
	public static final int height = 30;
	public static final int dimension = 20;
	
	
	public Game() {
		window = new JFrame();
		
		player = new Snake();		//generate snake and default movement to inactive (move = "NOTHING")
		food = new Food(player);	//spawn food randomly in game window checking the spawn is not on the snake
		graphics = new Graphics(this); //
		
		window.add(graphics);
		
		window.setTitle("Snake");
		window.setSize(width * dimension + 17, height * dimension + 20);
		window.setVisible(true);
		window.setResizable(false);	//new addition
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void start() {
		graphics.state = "RUNNING";
	}
	
	public void update() {
		if(graphics.state == "RUNNING") {
			if(check_food_collision()) {
				player.grow();
				food.random_spawn(player);
			}
			else if(check_wall_collision() || check_self_collision()) {
				graphics.state = "END";
			}
			else {
				player.move();
			}
		}
	}

	private boolean check_wall_collision() {
		if(player.getX() < 1 || player.getX() >= width * dimension 	-20				
				|| player.getY() < 1 || player.getY() >= height * dimension - 30) {		
			return true;
		}
		return false;
	}
	
	private boolean check_food_collision() {
		if(player.getX() == food.getX() * dimension 
				&& player.getY() == food.getY() * dimension) {
			return true;
		}
		return false;
	}

	private boolean check_self_collision()	{
		for(int i = 1; i < player.getBody().size(); i++) {
			if(player.getX() == player.getBody().get(i).x 
					&& player.getY() == player.getBody().get(i).y) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {  }


	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		
		if(graphics.state == "RUNNING") {
		
			if(keyCode == KeyEvent.VK_W) {
				player.up();
			}
			else if(keyCode == KeyEvent.VK_S) {
				player.down();
			}
			else if(keyCode == KeyEvent.VK_A) {
				player.left();
			}
			else {
				player.right();
			}
		}
		else {
			this.start();
		}
	}


	public Snake getPlayer() {
		return player;
	}


	public void setPlayer(Snake player) {
		this.player = player;
	}


	public Food getFood() {
		return food;
	}


	public void setFood(Food food) {
		this.food = food;
	}


	public JFrame getWindow() {
		return window;
	}


	public void setWindow(JFrame window) {
		this.window = window;
	}


	@Override
	public void keyReleased(KeyEvent e) {  }
}
