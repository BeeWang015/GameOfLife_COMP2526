package ca.bcit.comp2526.A01045793.A2b;

import javafx.scene.layout.GridPane;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * The World class is where our world will be and will contain a Cells. The
 * World class will also be responsible for creating the
 * 
 * @author BWang015
 * @version 2018
 * 
 */
public class World {

	private World.Cell[][] world;
	private int width;
	private int height;
	protected GridPane gridpane;
	protected Game game;
	World w;

	/**
	 * Constructs an object of type World which extends Block
	 * 
	 * @param width  sets the width of the world
	 * 
	 * @param height sets the height of the world
	 */
	public World(Game game, int width, int height) {
		this.gridpane = new GridPane();
		this.game = game;
		this.width = width;
		this.height = height;

		this.world = new World.Cell[width][height];
	}

	/**
	 * Inner Class Cell that will extend a block because a Cell must exist within a
	 * World and cannot exist outside of it.
	 * 
	 * @author Benson
	 * @version 2018
	 *
	 */
	class Cell extends Block {

		/**
		 * Constructs an object of type Cell and must be called using World.
		 * 
		 * @param w calls back to the World class
		 * @param x calls its super class's constructor
		 * @param y calls its super class's constructor
		 */
		public Cell(int x, int y) {
			super(x, y);
		}
	}

	/**
	 * Initialize the world
	 */
	public void initialize() {
		int width = this.width;
		int height = this.height;
		int numberValue = 0;

		RandomGenerator.reset();
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				this.world[i][j] = new World.Cell(i, j);
				numberValue = RandomGenerator.nextNumber(99);

				if (numberValue >= 80) {
					this.world[i][j].addLife(new Herbivore(this.world[i][j]));
				} else if (numberValue >= 60) {
					this.world[i][j].addLife(new Plant(this.world[i][j]));
				} else if (numberValue >= 50) {
					this.world[i][j].addLife(new Carnivore(this.world[i][j]));
				} else if (numberValue >= 45) {
					this.world[i][j].addLife(new Omnivore(this.world[i][j]));
				}

				this.gridpane.add(this.world[i][j], i, j);

				this.world[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
					@Override
					/**
					 * the handle method that must be implemented from the MouseEvent superclass
					 */
					public void handle(MouseEvent event) {
						World.this.game.nextTurn();
					}
				});

			}
		}
	}

	/**
	 * Create the world's size
	 */
	public void createWorld() {
		int width = this.width;
		int height = this.height;

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				this.world[i][j].createCanvas();
			}
		}
	}

	/**
	 * return the neighbouring blocks of the current cell that the lifeform is
	 * currently in
	 * 
	 * @param block the block that the cell is in
	 * @return the neighbouring life of that cell
	 */
	public ArrayList<Block> getNeighbourLifeForm(Block block) {

		ArrayList<Block> neighbourLife = new ArrayList<Block>();

		if (block == null) {
			return null;
		}
		int x = block.x;
		int y = block.y;

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				int newx = x + i;
				int newy = y + j;

				if ((newx >= 0) && (newx < this.width) && (newy >= 0) && (newy < this.height)) {
					World.Cell cell = this.world[newx][newy];
					if (cell != block) {
						neighbourLife.add(this.world[newx][newy]);
					}
				}
			}
		}
		return neighbourLife;
	}

	/**
	 * Update the current game with the next turn
	 */
	public void updateGame() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Cell cell = this.world[i][j];

				GraphicsContext graphics = cell.getGraphicsContext2D();

				graphics.clearRect(0.0, 0.0, 10.0, 10.0);
				graphics.setFill(Color.WHITE);
				graphics.fillRect(0.0, 0.0, 10.0, 10.0);
				graphics.setStroke(Color.BLACK);
				graphics.strokeRect(0.0, 0.0, 10.0, 10.0);

				cell.createCanvas();
				Alive lifeForm = cell.getTypeOfLife();

				if ((lifeForm != null) && (lifeForm.isAlive())) {
					lifeForm.lifeCreation(getNeighbourLifeForm(lifeForm.getPosition()));
				}
			}
		}
	}

	/**
	 * Get the types of lives currently in the game
	 * 
	 * @return the types of lives
	 */
	public ArrayList<Alive> getAllLife() {
		ArrayList<Alive> life = new ArrayList<Alive>();

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {

				if ((world[i][j].getTypeOfLife() != null) && (world[i][j].getTypeOfLife().isAlive())) {
					life.add(world[i][j].getTypeOfLife());
				}
			}
		}
		return life;
	}

}
