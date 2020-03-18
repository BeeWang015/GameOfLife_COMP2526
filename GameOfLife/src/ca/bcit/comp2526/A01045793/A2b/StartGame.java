package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

/**
 * The purpose of this abstract class is to make sure that the game starts by
 * creating an object of a world and implementing rules.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public abstract class StartGame {

	World world;
	ArrayList<Laws> law;

	/**
	 * To get the world reference in the game
	 * 
	 * @return the world
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * This method should be able to keep track of the turns taken
	 */
	abstract void turns();

	/**
	 * Initialize the game through this method
	 */
	abstract void intialize();

}
