package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

/**
 * Starts the game of life by implementing some methods and has laws that it
 * must follow.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public class Start extends Game {

	/**
	 * Constructs a Start object.
	 */
	public Start() {
		this.w = new World(this, 50, 50);
	}

	/**
	 * Will initialize the world
	 */
	public void initialize() {
		this.w.initialize();
		this.w.createWorld();
	}

	/**
	 * Will implement the laws onto the start of the game.
	 */
	public void makeLaws() {
		new Laws() {

			public boolean okay() {
				boolean success = false;

				return success;
			}
		};
	}

	/**
	 * Will move the turns of the game and update it to the next.
	 */
	public void nextTurn() {
		ArrayList<Alive> alive;
		alive = this.w.getAllLife();

		for (Alive a : alive) {
			if ((a != null) && (a.isalive)) {
				a.move(this.w.getNeighbourLifeForm(a.getPosition()));
			}
		}

			this.w.updateGame();
	}
}
