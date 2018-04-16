package main;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		try {
		      // Localisation du fichier FXML.
		      final URL url = getClass().getClassLoader().getResource("vue/LoginFXML.fxml");
		      
		      // Création du loader.
		      final FXMLLoader fxmlLoader = new FXMLLoader(url);
		      
		      // Chargement du FXML.
		      final AnchorPane root = (AnchorPane) fxmlLoader.load();
			
		      final Scene scene = new Scene(root);
		      arg0.setTitle("Authentification");
		      arg0.setScene(scene);
		      arg0.show();
	    	
		} catch(Exception e) {
			System.err.println("Erreur au chargement: " + e);
		}
	}

}
