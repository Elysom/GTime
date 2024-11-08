package GTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import utilidades.SCRUDusuarios;

public class controladorRegister {
	@FXML
	Button btnRegister;
	@FXML
	TextField txtNombre;
	@FXML
	TextField txtMail;
	@FXML
	TextField txtNombreReal;
	@FXML
	TextField txtApellidos;
	@FXML
	TextField txtContrasenia;
	@FXML
	TextField txtConfContrasenia;
	
	
	
	private void register() {
		String nombre=txtNombre.getText();
		String apellidos=txtApellidos.getText();
		String mail=txtMail.getText();
		String nombreReal=txtNombreReal.getText();
		String contrasenia=txtContrasenia.getText();
		String confContrasenia=txtContrasenia.getText();
		SCRUDusuarios register = new SCRUDusuarios();
		register.registerSCRUD(nombre, apellidos, mail, nombreReal, confContrasenia);
		
		
		
	
	}
	private void volverLogin() {
		Main.setRoot("/vista/loggin");
	}

}
