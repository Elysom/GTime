package GTime;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import utilidades.SCRUDusuarios;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;


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
	@FXML
	Label txtValidation;
	
	@FXML
	private void registrer(ActionEvent event) {
		// Iniciamos las variables
		
	    String nombreUsuario = txtNombre.getText();
	    String apellidos = txtApellidos.getText();
	    String mail = txtMail.getText();
	    String nombreReal = txtNombreReal.getText();
	    String contrasenia = txtContrasenia.getText();
	    String confContrasenia = txtConfContrasenia.getText();
	    
		// Indicar validacion
		
	    
	    
		String resultado = SCRUDusuarios.validacionFormulario(nombreUsuario, apellidos, mail, nombreReal, contrasenia, confContrasenia);
		
		System.out.println(resultado);
		
		txtValidation.setText(resultado);
		
		
		
		if (txtValidation.getText().equals("Registro exitoso.")) {
			
			SCRUDusuarios.registerSCRUD(nombreUsuario, apellidos, mail, nombreReal, confContrasenia);
			
			SCRUDusuarios.agregarEsquemaDatos(nombreUsuario);
			
		} else {
			System.out.println(resultado);
		}
		
	}

	//SEPARAR LOS METODOS DE LA VALIDACION POR CAMPOS(UN METODO PARA EL NOMBRE, OTRO PARA EL APELLIDO ETC)
	


	private void volverLogin() throws IOException {
		Main.setRoot("/vista/loggin");
	}


}
