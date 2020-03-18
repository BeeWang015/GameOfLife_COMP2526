package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A Carnivore class that will implement the Alive interface and eats both a
 * Omnivore and Herbivore. It can also give birth if there are least 1 other
 * Carnivore neighbors, at least 3 free neighboring cells, and 2 neighboring
 * cells with food (Herbivores or Omnivores)
 * 
 * @author Benson
 * @version 2018
 */
public class Carnivore extends Alive implements OmnivoreEddible {

	public static final int MINIMUM_CARNIVORE_HUNGER = 5;

	/**
	 * Constructs an object of a Carnivore.
	 * 
	 * @param cell represents the block of which the carnivore is in.
	 */
	public Carnivore(Block cell) {
		super(cell);

		updateLastTimeAte(MINIMUM_CARNIVORE_HUNGER);
	}

	/**
	 * Will create the block of the Carnivore.
	 */
	public void createCanvas() {
		GraphicsContext box = cell.getGraphicsContext2D();

		box.setFill(Color.RED);
		box.fillRect(0.0, 0.0, 9.0, 9.0);
	}

	/**
	 * Gives birth to other Carnivores. This method is a little different because we
	 * can only have a Carnivore born if there is at least 1 other Carnivore
	 * neighbour, at least 3 neighbouring cells, and 2 neighbouring cells with food
	 * (for this type of lifeform: Herbivores or Omnivores)
	 * 
	 * @param block
	 */
	public void lifeCreation(ArrayList<Block> block) {

		int numberOfFoodLife = 0;
		int numberOfCarnivores = 0;

		ArrayList<Block> nothing;
		nothing = new ArrayList<Block>();

		for (Block b : block) {
			Alive lifeForm = b.getTypeOfLife();

			if ((lifeForm != null) && (lifeForm.isalive)) {
				if (lifeForm instanceof Carnivore) {
					numberOfCarnivores += 1;
				} else if (lifeForm instanceof CarnivoreEddible) {
					numberOfFoodLife += 1;
				}
			} else {
				nothing.add(b);
			}
		}

		// add more carnivores if it can create life
		if ((numberOfCarnivores >= 1) && (numberOfFoodLife >= 2) && (nothing.size() >= 3)) {
			int make = RandomGenerator.nextNumber(nothing.size());

			Block b = (Block) nothing.get(make);
			Carnivore c = new Carnivore(b);

			b.addLife(c);
		}
	}

	/**
	 * Method to determine which specific blocks will be eaten by the carnivore.
	 */
	public boolean canEat(Alive lifeForm) {
		if (lifeForm instanceof CarnivoreEddible) {
			updateLastTimeAte(MINIMUM_CARNIVORE_HUNGER);

			return true;
		}
		return false;
	}

}
