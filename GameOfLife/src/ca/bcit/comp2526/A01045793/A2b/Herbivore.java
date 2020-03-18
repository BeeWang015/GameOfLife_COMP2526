package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Herbivore class that will be a herbivore that eats plants and is alive.
 * It will be defined by the color yellow and can move around in the world. The
 * new herbivore will be implementing CarnivoreEddible and OmnivoreEddible as it
 * is able to be eaten by both of these new life forms. It can also now give
 * birth to new Herbivore as long as the conditions are met.
 * 
 * @author BWang015
 * @version 2018
 * 
 */
public class Herbivore extends Alive implements CarnivoreEddible, OmnivoreEddible {

	public static final int MINIMUM_HERBIVORE_HUNGER = 5;

	final int eatBefore = MINIMUM_HERBIVORE_HUNGER;

	/**
	 * Constructs an object of type Herbivore
	 * 
	 * @param b
	 */
	public Herbivore(Block b) {
		super(b);

		updateLastTimeAte(eatBefore);
	}

	/**
	 * determine whether or not the herbivore is hungry and will eat the plant space
	 */
	public boolean canEat(Alive lifeType) {
		if (lifeType instanceof HerbivoreEddible) {
			updateLastTimeAte(MINIMUM_HERBIVORE_HUNGER);

			return true;
		}

		return false;
	}

	/**
	 * Create the Herbivore variant of the box Canvas
	 */
	public void createCanvas() {
		GraphicsContext box = cell.getGraphicsContext2D();

		box.setFill(Color.YELLOW);
		box.fillRect(0.0, 0.0, 9.0, 9.0);
	}

	/**
	 * Implement the abstract method lifeCreation which will birth a herbivore. The
	 * conditions that need to be met are that there must be at least one other
	 * Herbivore neighbour, at least two free neighbouring cells, and at least 2
	 * neighbouring cells with food (which will be plants for the Herbivore).
	 * 
	 * @param blocks
	 */
	public void lifeCreation(ArrayList<Block> blocks) {

		int numberOfHerbivores = 0;
		int numberOfFoodLife = 0;

		ArrayList<Block> nothing = new ArrayList<Block>();

		for (Block b : blocks) {
			Alive lifeForm = b.getTypeOfLife();

			if ((lifeForm == null)  || (!lifeForm.isalive)) {
				nothing.add(b);
			} else if ((lifeForm != null) && (lifeForm.isalive)) {
				if (lifeForm instanceof Herbivore) {
					numberOfHerbivores += 1;
				} else if (lifeForm instanceof HerbivoreEddible) {
					numberOfFoodLife += 1;
				}
			}
		}
		if ((numberOfHerbivores >= 1) && (numberOfFoodLife >= 2) && (nothing.size() >= 1)) {
			int make = RandomGenerator.nextNumber(nothing.size());

			Block b = (Block) nothing.get(make);
			Plant plant = new Plant(b);

			b.addLife(plant);
		}
	}
}
