package ch.pg.notes2.view;

import java.io.IOException;
import java.net.URL;

import ch.pg.notes2.controller.Controllerstart;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Patrick
 * 
 * The first Class.
 */
public class Start extends Application {
	
	public static Stage mainStage;
	
	public static void main(String... args) {
		launch(args);
	}
	
	/**
	 * Starts the fxml file Main
	 * @throws IOException 
	 */
	@Override
	public void start(Stage primaryStage) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Notes");
		primaryStage.centerOnScreen();
		primaryStage.setScene(scene);
		primaryStage.show();
		mainStage = primaryStage;
	}
	
	/**
	 * @param name
	 * 
	 * changes the FXML scene.
	 */
	public void loadScene(String name) {
		Parent parentToLoad = null;
		
		try {
			URL resourcePath = Controllerstart.class.getResource(name + ".fxml");
			parentToLoad = FXMLLoader.load(resourcePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Scene sceneToLoad = new Scene(parentToLoad);
		mainStage.setScene(sceneToLoad);
	}
}
