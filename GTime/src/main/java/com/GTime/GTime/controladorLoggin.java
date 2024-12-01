
package com.GTime.GTime;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import utilidades.SCRUDusuarios;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class controladorLoggin implements Initializable {

private static final String pwdVerify = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
private static final int MAX_INPUT = 20;

public static String nombreUsuGlobal;//Se puede llamar desde cualquier clase para obtener el nombre de usuario
@FXML
private Pane pane;
@FXML
private TextField txtUsuario;

@FXML
private PasswordField txtContrasenia;

@FXML
private Button btnLogin;

@FXML
private Button btnRegistrar;

@FXML
Label txtayuda;
private TextField txtContraseniaVisible = new TextField();//Nuevo TextField para cambiarlo por el de la contraseña para que se vea
private boolean mostrandoContrasenia=false;
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
    // Configura el campo de texto visible
	//Poner los valores de posicion de la contraseña
    txtContraseniaVisible.setLayoutX(txtContrasenia.getLayoutX());
    txtContraseniaVisible.setLayoutY(txtContrasenia.getLayoutY());
    txtContraseniaVisible.setPrefWidth(txtContrasenia.getPrefWidth());
    txtContraseniaVisible.setVisible(false); // Oculto inicialmente
    txtContraseniaVisible.setManaged(false); // No participa en el layout

    txtContrasenia.textProperty().bindBidirectional(txtContraseniaVisible.textProperty());//Conseguir el texto de la contraseña
    pane.getChildren().add(txtContraseniaVisible);//Añadir el textfield creado a el pane
	
}

@FXML
private void loggin(ActionEvent event) throws IOException {
	
	
	nombreUsuGlobal = txtUsuario.getText();
	
	String pass = txtContrasenia.getText();
	
	String resultado = SCRUDusuarios.loginSCRUD(nombreUsuGlobal, pass); //Llama a la funcion
	
	System.out.println(resultado);
	
	// loggin para administradores
	
	if (resultado.equals("Inicio de sesión exitoso, modo Administrador")) {
		
		txtayuda.setText(resultado);
		
		txtayuda.setStyle("-fx-text-fill: green;");
		
		try {
			Thread.sleep(1000);
			
			Main.setRoot("/vista/principal");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	// loggin para usuarios normales
		
	resultado = SCRUDusuarios.loginSCRUD(nombreUsuGlobal, pass);
		
	} else if (resultado.equals("Inicio de sesión exitoso, modo Usuario")){	
		
		txtayuda.setText(resultado);
		
		txtayuda.setStyle("-fx-text-fill: green;");
		
		try {
			Thread.sleep(1000);
			
			Main.setRoot("/vista/usuarioPrincipal");
			
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	} else {
		
		txtayuda.setStyle("-fx-text-fill: red;");
		
		txtayuda.setText(resultado);
		
		
	}
	
	
	
}


@FXML
private void lanzarVentanaRegistro(ActionEvent event) throws IOException {
	Main.setRoot("/vista/register");

}
@FXML
private void mostrarContrasenia() {
    if (mostrandoContrasenia) {
        txtContrasenia.setVisible(true);
        txtContrasenia.setManaged(true);
        txtContraseniaVisible.setVisible(false);
        txtContraseniaVisible.setManaged(false);
    } else {
        txtContrasenia.setVisible(false);
        txtContrasenia.setManaged(false);
        txtContraseniaVisible.setVisible(true);
        txtContraseniaVisible.setManaged(true);
    }
    mostrandoContrasenia = !mostrandoContrasenia;
}

// PRUEBAS 





}
