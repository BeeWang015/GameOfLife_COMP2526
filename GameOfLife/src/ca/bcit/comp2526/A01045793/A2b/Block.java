package ca.bcit.comp2526.A01045793.A2b;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The block class is the blocks of spaces used in the game. It will hold
 * either a herbivore or plant or nothing at all.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public class Block extends Canvas{
	
	Alive alive;
	/* positional variables */
	int y;
	int x;
	
	/**
	 * Constructs an object of type Block.
	 * 
	 * @param space
	 * @param spaces
	 * @param x
	 * @param y
	 */
	public Block(int x, int y) {
		super(10.0, 10.0);
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Add life onto the blocks
	 * @param alive
	 */
	public void addLife(Alive alive) {
		this.alive = alive;
		this.alive.setPosition(this);
	}
	
	public void disableLife() {
		
		this.alive.setPosition(null);
		this.alive = null;
	}

	/**
	 * Get the type of life
	 * 
	 * @return the type of life
	 */
	public Alive getTypeOfLife() {
		return this.alive;
	}

	/**
	 * Create the canvas using the Canvas java.fx library
	 */
	public void createCanvas() {
		GraphicsContext gui = getGraphicsContext2D(); // part of the library

		gui.setFill(Color.WHITE);
		gui.fillRect(0.0, 0.0, 10.0, 10.0);
		gui.setStroke(Color.BLACK);
		gui.strokeRect(0.0, 0.0, 10.0, 10.0);
		
		if(((this.alive != null)) && (this.alive.isalive)) {
			this.alive.createCanvas();
		}
	}
	
	
}
