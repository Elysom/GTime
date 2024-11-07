package GTime;

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
public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/loggin.fxml"));
			AnchorPane root = loader.load();
			
			//BorderPane root = new BorderPane();
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
    public static void main(String[] args) {
    
    	// Test
    	
    	//testConexion();
        
    	launch();
    }
    
    public static void testConexion() {
    	
    	DatabaseConnector conexion = new DatabaseConnector();
    	
    	try (Connection conexionLoggin = conexion.iniciarConexion();){
    		
			conexion.cerrarConexion(conexionLoggin);
					
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ConexionFallida");
		}
    	
    	
    }

}