package ca.bcit.comp2526.A01045793.A2b;

import java.util.ArrayList;

/**
 * The interface of being Alive. This will include methods that both herbivore
 * and plants will have to implement on their own.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public abstract class Alive {

	protected int hungry = 0;
	boolean isalive = true;
	protected Block cell;

	/**
	 * Constructor for Alive class but cannot construct an object for it because it
	 * is
	 * 
	 * @param cell
	 */
	public Alive(Block cell) {
		this.cell = cell;
	}

	/**
	 * Allow the space to die
	 */
	public void die() {
		isalive = false;
	}

	/**
	 * Allow the space to be born
	 */
	public abstract void lifeCreation(ArrayList<Block> typeOfLife);

	/**
	 * Children must implement their own Canvas color.
	 */
	protected abstract void createCanvas();

	/**
	 * Dictate where the life form will move every turn
	 * 
	 * @param neighbouringBlocks
	 */
	public void move(ArrayList<Block> neighbouringBlocks) {

		if (this.cell == null) {
			this.isalive = false;
			return;
		}

		if (!this.isalive) {
			return;
		}
		
		Block b = chooseCoordinates(neighbouringBlocks);
		if (b != null) {
			
			Alive lifeForm = b.getTypeOfLife();
			if (lifeForm != null) {
				consume(lifeForm);
			} else {
				this.hungry--;
			}
			if (this.hungry <= 0) {
				
				die();
			} else {
				
				this.cell.disableLife();
				b.addLife(this);
				this.cell = b;
			}		
		} 
		
	}

	/**
	 * set the position of the current block
	 */
	public void setPosition(Block position) {
		this.cell = position;
	}

	/**
	 * get the current position of the block
	 */
	public Block getPosition() {
		return this.cell;
	}

	/**
	 * Set the coordinates of the blocks and the type of life it contains.
	 * 
	 * @param blocks which is an ArrayList of blocks
	 * @return the type of Block it is
	 */
	public Block chooseCoordinates(ArrayList<Block> blocks) {
		Block spot = null;

		if (blocks == null) {
			return spot;
		}

		while(blocks.size() > 0) {
			
			int selector = RandomGenerator.nextNumber(blocks.size());
			spot = (Block) blocks.remove(selector);

			if (canEat(spot.getTypeOfLife())) {
				return spot;
			}

			if (spot.getTypeOfLife() == null) {
				return spot;
			}

		}
		return spot;
	}

	/**
	 * Determine the last time the herbivore has eaten
	 * 
	 */
	public int lastTimeEatten() {
		return this.hungry;
	}

	/**
	 * Method determines how many turns the herbivore must eat by
	 * 
	 * @param eat
	 */
	public void updateLastTimeAte(int eat) {
		this.hungry = eat;
	}

	/**
	 * Determine what type of Alive life can be eaten
	 * 
	 * @param type which is the type of life
	 * @return true if it can be eaten
	 */
	public abstract boolean canEat(Alive type);

	/**
	 * A method to consume a lifeForm
	 * 
	 * @param lifeForm which will be disabled or 'consumed'
	 */
	public void consume(Alive lifeForm) {
		lifeForm.isalive = false;
		lifeForm.cell.disableLife();
	}

	/**
	 * Method that will determine whether cell is alive.
	 * 
	 * @return true
	 */
	public boolean isAlive() {
		return true;
	}

}