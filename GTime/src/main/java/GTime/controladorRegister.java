package GTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
	
	
	
	private void register() {
		String nombre=txtNombre.getText();
		String apellidos=txtApellidos.getText();
		String mail=txtMail.getText();
		String nombreReal=txtNombreReal.getText();
		String contrasenia=txtContrasenia.getText();
		String confContrasenia=txtContrasenia.getText();
		
		String resultadoValidacion = validacionFormulario(nombre, apellidos, mail, nombreReal, contrasenia, confContrasenia);
			
	}
	
	public static String validacionFormulario(String nombreUsuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia)  {
		
		// Validacion de correo electrónico (funcion de commons validator)
		
				EmailValidator emailValidator = EmailValidator.getInstance();
				
				// Crear validadores con expresiones regulares para cada requisito
		        RegexValidator mayusculaValidator = new RegexValidator(".*[A-Z].*");  // Al menos una mayúscula
		        RegexValidator minusculaValidator = new RegexValidator(".*[a-z].*");  // Al menos una minúscula
		        RegexValidator numeroValidator = new RegexValidator(".*[0-9].*");      // Al menos un dígito
		        RegexValidator especialValidator = new RegexValidator(".*[^A-Za-z0-9].*"); // Al menos un carácter especial
		        RegexValidator noNumerosValidator = new RegexValidator("^[^0-9]*$");
		        
		        if (nombreUsuario.isEmpty()) {
		            return "Por favor, rellene el campo 'nombre'.";
		        } else if (apellidos.isEmpty()) {
		            return "Por favor, rellene el campo 'apellidos'.";
		        } else if (!emailValidator.isValid(mail)) {
		            return "Correo electrónico no válido.";
		        } else if (!noNumerosValidator.isValid(nombreReal) || !especialValidator.isValid(nombreReal)) {
					return "El nombre real no puede tener numeros o caracteres especiales";
				} else if (!contrasenia.equals(confContrasenia)) {
		            return "Las contraseñas no coinciden.";
		        } else if (!mayusculaValidator.isValid(confContrasenia)) { // Verificar si la contraseña tiene una mayúscula
		            return "La contraseña debe contener al menos una letra mayúscula, además de una minúscula, un número y un carácter especial.";
		        } else if (!minusculaValidator.isValid(confContrasenia)) { // Verificar si tiene al menos una minúscula
		            return "La contraseña debe contener al menos una letra minúscula, además de una mayúscula, un número y un carácter especial.";
		        } else if (!numeroValidator.isValid(confContrasenia)) {
		            return "La contraseña debe contener al menos un número, además de una mayúscula, una minúscula y un carácter especial.";
		        } else if (!especialValidator.isValid(confContrasenia)) {
		            return "La contraseña debe contener al menos un carácter especial, además de una mayúscula, una minúscula y un número.";
		        }

		        return "Registro exitoso.";
		    }

}
