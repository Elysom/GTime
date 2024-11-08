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
import javafx.scene.control.Button;


public class controladorLoggin implements Initializable {

private static final String pwdVerify = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
private static final int MAX_INPUT = 20;

@FXML
private TextField usuarioLogin;

@FXML
private TextField contraseniaLogin;

@FXML
private Button botonLogin;

@FXML
private Button botonRegister;

@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}

@FXML
private void loggin(ActionEvent event) {
	
	
	String usuario = usuarioLogin.getText();
	
	String pass = contraseniaLogin.getText();
	
	SCRUDusuarios loggin = new SCRUDusuarios();
	
	System.out.println("ejemplo");
	if (loggin.inicioSesionScrud(usuario, pass)) {
		System.out.println("Se inicio sesion con exito");
	} else {
		System.out.println("valores incrrectos");
	}

}


@FXML
private void lanzarVentanaRegistro(ActionEvent event) throws IOException {
	// Obtener la referencia del controlador de la nueva pestaña
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/register.fxml"));
    Parent root = loader.load();
    
    /*
    // Obtener la instancia del controlador de la nueva pestaña
    controladorNuevaPestaña controladorNueva = loader.getController(); // esta linea es IMPORTANTE, Al obtener el controlador podremos iniciar las funciones del segundo controlador y cambiarla
    */
}



}
