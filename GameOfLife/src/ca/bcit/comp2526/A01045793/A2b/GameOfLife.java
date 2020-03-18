package ca.bcit.comp2526.A01045793.A2b;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import java.io.File;
import java.io.IOException;

/**
 * The class that will start the game and display a displayer with the game on
 * it that we can play with.
 * 
 * @author BWang015
 * @version 2018
 *
 */
public class GameOfLife extends Application {

	public void start(Stage stage) throws Exception {
		Game game = new Start();
		game.initialize();

		Scene scene = new Scene(game.getWorld().gridpane, 500.0, 500.0);
		stage.setScene(scene);
		stage.show();
	}

	public static void main(String[] args) throws IOException{
		launch(args);
	}
}
