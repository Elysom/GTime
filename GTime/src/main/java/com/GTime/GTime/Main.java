package com.GTime.GTime;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

import utilidades.DatabaseConnector;

/**
 * JavaFX App
 */

// .
public class Main extends Application {
	  private static Scene scene;
	  private static Parent root;
	@Override
	public void start(Stage primaryStage) {
			try {

				scene = new Scene(loadFXML("/vista/loggin"));
				primaryStage.sizeToScene();
		        primaryStage.setScene(scene);
		        primaryStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}
	
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxml + ".fxml"));
        
        return fxmlLoader.load();
    }
    static void setRoot(String fxml) throws IOException {
        // Cargar el nuevo contenido
        Parent newRoot = loadFXML(fxml);
        scene.setRoot(newRoot);
        
        // Ajustar el tamaño de la escena al contenido
        Stage stage = (Stage) scene.getWindow();
        stage.sizeToScene();
    }
    
	public static void main(String[] args) {
    
    	// Test
    	
    	//testConexion();
        
    	launch();
    }
    
    public static void testConexion() {
    	
    	try (Connection conexionLoggin = utilidades.DatabaseConnector.dameConexion();){
    		
			utilidades.DatabaseConnector.cerrarConexion(conexionLoggin);
					
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ConexionFallida");
		}
    	
    	
    }

}