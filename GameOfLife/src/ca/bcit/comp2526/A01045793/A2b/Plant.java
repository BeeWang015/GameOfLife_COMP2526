package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * The Plant class is responsible for creating the Plants in our program that
 * will be eaten by the herbivore cells. They will be colored yellow and shall
 * implement Alive.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public class Plant extends Alive implements HerbivoreEddible, OmnivoreEddible {

	public static final int MINIMUM_TURNS = 8;//large number

	final int turns = MINIMUM_TURNS;

	/**
	 * Constructs an object of type Plant
	 * 
	 * @param b sets the blocks to be type Plant
	 */
	public Plant(Block b) {
		super(b);

		updateLastTimeAte(turns);
	}

	/**
	 * Plants cannot eat therefore when implementing this method must return false
	 */
	public boolean canEat(Alive alive) {
		return false;
	}

	/**
	 * Have a method that shows that Plant can move. If the plant moves for too long
	 * by its own, it will die.
	 * 
	 * @param blocks will determine which block the plant is currently in.
	 */
	public void move(ArrayList<Block> blocks) {
		hungry -= 1;

		if (hungry <= 0) {

			die();
		}
	}

	/**
	 * Implement the createCanvas for the plant class
	 */
	public void createCanvas() {
		GraphicsContext block = cell.getGraphicsContext2D();

		block.setFill(Color.GREEN);

		block.fillRect(0.0, 0.0, 9.0, 9.0);
	}

	/**
	 * Implement the lifeCreation method which 'birth' new plants into our game
	 * 
	 * @param blocks
	 */
	public void lifeCreation(ArrayList<Block> blocks) {

		int numberOfPlants = 0;

		ArrayList<Block> nothing;
		nothing = new ArrayList<Block>();

		for (Block b : blocks) {
			Alive lifeForm = b.getTypeOfLife();

			if ((lifeForm != null) && (lifeForm.isalive)) {
				if (lifeForm instanceof Plant) {

					numberOfPlants += 1;
				}
			} else {
				nothing.add(b);
			}
		}

		if ((numberOfPlants >= 4) && (nothing.size() >= 3)) {
			int selection = RandomGenerator.nextNumber(nothing.size());

			Block createdPlant = (Block) nothing.get(selection);
			Plant plant = new Plant(createdPlant);

			createdPlant.addLife(plant);
		}
	}
}
