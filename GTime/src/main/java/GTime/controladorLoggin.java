package GTime;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import utilidades.SCRUDusuarios;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;


public class controladorLoggin implements Initializable {

private static final String pwdVerify = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
private static final int MAX_INPUT = 20;

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

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}

@FXML
private void loggin(ActionEvent event) {
	
	System.out.println("hola");
	
	String usuario = txtUsuario.getText();
	
	String pass = txtContrasenia.getText();
	
	String resultado = SCRUDusuarios.loginSCRUD(usuario, pass); //Llama a la funcion
	
	System.out.println(resultado);
	
	if (resultado.equals("Inicio de sesión exitoso")) {
		
		txtayuda.setText(resultado);
		
		txtayuda.setStyle("-fx-text-fill: green;");
	} else {
		
		txtayuda.setStyle("-fx-text-fill: red;");
		
		txtayuda.setText(resultado);
		
	}
	
	
	
}


@FXML
private void lanzarVentanaRegistro(ActionEvent event) throws IOException {
	Main.setRoot("/vista/register");

}



}
