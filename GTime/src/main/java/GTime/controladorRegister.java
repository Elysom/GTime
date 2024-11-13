package GTime;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import utilidades.SCRUDusuarios;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;


import utilidades.SCRUDusuarios;


public class controladorRegister implements Initializable {
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
	PasswordField txtContrasenia;
	@FXML
	PasswordField txtConfContrasenia;
	@FXML
	Label txtValidation;
	@FXML
	private ComboBox<String> txtCurso;
	
	
	
	@FXML
	private void registrer(ActionEvent event) {
		// Iniciamos las variables
		
	    String nombreUsuario = txtNombre.getText();
	    String apellidos = txtApellidos.getText();
	    String mail = txtMail.getText();
	    String nombreReal = txtNombreReal.getText();
	    String contrasenia = txtContrasenia.getText();
	    String confContrasenia = txtConfContrasenia.getText();
	    String curso = txtCurso.getValue();
	    
	    // Verificamos si el usuario existe
	    
	    String usu_compatible = SCRUDusuarios.verificarNombreUsusario(nombreUsuario);
	    
	    if (usu_compatible.equals("Nombre Usuario Disponible")) {
	    	
	    	// Indicar validacion
		    
			String resultado = SCRUDusuarios.validacionFormulario(nombreUsuario, apellidos, mail, nombreReal, contrasenia, confContrasenia, curso);
			
			System.out.println(resultado);
			
			txtValidation.setStyle("-fx-text-fill: green;");
			
			txtValidation.setText(resultado);
			
			if (txtValidation.getText().equals("¡Registro exitoso!")) {
				
				SCRUDusuarios.registerSCRUD(nombreUsuario, apellidos, mail, nombreReal, confContrasenia, curso);
				
				SCRUDusuarios.agregarEsquemaDatos(nombreUsuario);
				
			} else {
				
				txtValidation.setStyle("-fx-text-fill: red;");
				
				System.out.println(resultado);
			}
		} else {
			txtValidation.setText("El nombre de usuario " +nombreUsuario+ " no esta disponible, igrese otro");
		}
		
		
	}

	//SEPARAR LOS METODOS DE LA VALIDACION POR CAMPOS(UN METODO PARA EL NOMBRE, OTRO PARA EL APELLIDO ETC)
	


	private void volverLogin() throws IOException {
		
		
		Main.setRoot("/vista/loggin");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		txtCurso.setItems(FXCollections.observableArrayList("1.º SMR","2.º SMR","1.º DAM","2.º DAM"));


		}


}
