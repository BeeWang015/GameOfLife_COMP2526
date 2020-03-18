package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

/**
 * Game class that holds the idea that there is a game that must initiate the
 * world and the laws that there world follows. It will also contain the idea
 * that the world must take turns.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public abstract class Game {

	ArrayList<Laws> laws;
	World w;

	/**
	 * Method that should initialize the game with the world and everything together
	 * in a Displayer
	 */
	public abstract void initialize();

	/**
	 * Method that will keep track of the current amount of turns
	 */
	public abstract void nextTurn();

	/**
	 * get the world that the game is initializing
	 * 
	 * @return the world
	 */
	public World getWorld() {
		return this.w;
	}

}
