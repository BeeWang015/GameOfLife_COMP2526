package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Omnivore class that will create an Omnivore and implements alive. It will
 * also be repesented by the colour
 * 
 * @author Benson
 * @version 2018
 */
public class Omnivore extends Alive implements CarnivoreEddible {

	public static final int MINIMUM_OMNIVORE_EAT = 5;

	/**
	 * Constructs an object of a Carnivore.
	 * 
	 * @param cell represents the block of which the carnivore is in.
	 */
	public Omnivore(Block cell) {
		super(cell);

		updateLastTimeAte(MINIMUM_OMNIVORE_EAT);
	}

	/**
	 * Gives birth to more Omnivores in the game. The condition for the omnivore is
	 * if there is at least one other omnivore neighbour, at least three free
	 * neighbouring cells, and one neighbouring cell with food (which will be
	 * plants, herbivores, or carnivores).
	 * 
	 * @param block
	 */
	public void lifeCreation(ArrayList<Block> block) {
		int numberOfOmnivores = 0;
		int numberOfFoodLife = 0;

		ArrayList<Block> nothing = new ArrayList<Block>();

		for (Block b : block) {
			Alive lifeForm = b.getTypeOfLife();

			if ((lifeForm == null)  || (!lifeForm.isalive)) {

				nothing.add(b);
			} else if ((lifeForm != null) && lifeForm.isalive) {
				if (lifeForm instanceof Omnivore) {

					numberOfOmnivores += 1;
				} else if (lifeForm instanceof OmnivoreEddible) {

					numberOfFoodLife += 1;
				}
			}
		}
		if ((numberOfOmnivores >= 1) && (numberOfFoodLife >= 2) && (nothing.size() >= 3)) {
			int make = RandomGenerator.nextNumber(nothing.size());

			Block b = (Block) nothing.get(make);
			Omnivore omni = new Omnivore(b);

			b.addLife(omni);
		}
	}

	/**
	 * Creates the colour of what the Omnivore will be represented as in the game.
	 */
	public void createCanvas() {
		GraphicsContext block = cell.getGraphicsContext2D();

		block.setFill(Color.BLUE);

		block.fillRect(0.0, 0.0, 9.0, 9.0);
	}

	/**
	 * Determines what type of food the Omnivore can eat, which will be plants and
	 * animals.
	 */
	public boolean canEat(Alive lifeForm) {
		if (lifeForm instanceof OmnivoreEddible) {
			updateLastTimeAte(MINIMUM_OMNIVORE_EAT);

			return true;
		}
		return false;
	}
}
