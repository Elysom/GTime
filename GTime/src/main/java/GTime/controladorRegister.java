package GTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import utilidades.SCRUDusuarios;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;


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
	public static Text txtValidacion;
	
	
	private void register() {
		String nombre=txtNombre.getText();
		String apellidos=txtApellidos.getText();
		String mail=txtMail.getText();
		String nombreReal=txtNombreReal.getText();
		String contrasenia=txtContrasenia.getText();
		String confContrasenia=txtContrasenia.getText();
		
		validacionRegister(nombre, apellidos, mail, nombreReal, contrasenia, confContrasenia);
		
		
	}
	//SEPARAR LOS METODOS DE LA VALIDACION POR CAMPOS(UN METODO PARA EL NOMBRE, OTRO PARA EL APELLIDO ETC)
	
	
	public static void validacionRegister(String nombreUsuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia)  {
		
		// Validacion de correo electrónico (funcion de commons validator)
		
				EmailValidator emailValidator = EmailValidator.getInstance();
				
				// Crear validadores con expresiones regulares para cada requisito
		        RegexValidator mayusculaValidator = new RegexValidator(".*[A-Z].*");  // Al menos una mayúscula
		        RegexValidator minusculaValidator = new RegexValidator(".*[a-z].*");  // Al menos una minúscula
		        RegexValidator numeroValidator = new RegexValidator(".*[0-9].*");      // Al menos un dígito
		        RegexValidator especialValidator = new RegexValidator(".*[^A-Za-z0-9].*"); // Al menos un carácter especial
		        RegexValidator noNumerosValidator = new RegexValidator("^[^0-9]*$");
		        
		        if (nombreUsuario.isEmpty()) {
		        	txtValidacion.setText("Por favor, rellene el campo 'nombre'.");
		        } else if (apellidos.isEmpty()) {
		        	txtValidacion.setText("Por favor, rellene el campo 'apellidos'.");
		        } else if (!emailValidator.isValid(mail)) {
		        	txtValidacion.setText("Correo electrónico no válido.");
		        } else if (!noNumerosValidator.isValid(nombreReal) || !especialValidator.isValid(nombreReal)) {
		        	txtValidacion.setText("El nombre real no puede tener numeros o caracteres especiales"); 
				} else if (!contrasenia.equals(confContrasenia)) {
					txtValidacion.setText("Las contraseñas no coinciden."); 
		        } else if (!mayusculaValidator.isValid(confContrasenia)) { // Verificar si la contraseña tiene una mayúscula
		        	txtValidacion.setText("La contraseña debe contener al menos una letra mayúscula, además de una minúscula, un número y un carácter especial."); 
		        } else if (!minusculaValidator.isValid(confContrasenia)) { // Verificar si tiene al menos una minúscula
		        	txtValidacion.setText("La contraseña debe contener al menos una letra mayúscula, además de una minúscula, un número y un carácter especial.");
		        } else if (!numeroValidator.isValid(confContrasenia)) {
		        	txtValidacion.setText("La contraseña debe contener al menos un número, además de una mayúscula, una minúscula y un carácter especial.");
		        } else if (!especialValidator.isValid(confContrasenia)) {
		        	txtValidacion.setText("La contraseña debe contener al menos un carácter especial, además de una mayúscula, una minúscula y un número.");
		        }

		     
		    }


}
