package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import GTime.controladorRegister;




public class SCRUDusuarios {	
	

//Método para loggear a un usario a partir del usuarfio y password

public void loginSCRUD(String usuario,String contrasenia) {
	
	
	Connection cn= utilidades.DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario,contrasenia FROM usuarioslista WHERE nombreUsuario = ? && contrasenia = ?";
	
	try (PreparedStatement StatementInicio = cn.prepareStatement(sql)){
		
		StatementInicio.setString(1, usuario);
		
		StatementInicio.setString(2, contrasenia);
		
		ResultSet rs = StatementInicio.executeQuery();
		
		if (rs.next()) {
			
			if (rs.getString("nombreUsuario").equals(usuario) && rs.getString("contrasenia").equals(contrasenia)) {
				
				System.out.println("Inicio de sesión exitoso");
				
				utilidades.DatabaseConnector.cerrarConexion(cn);
				
				
			
			} else {
				
				System.out.println("Nombre de usuario y contrasenia invalido");
				
				
			}
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
        System.out.println("Error al iniciar sesión: " + e.getMessage());

	}
	
	
}
public static void registerSCRUD(String nombre,String apellidos,String mail,String nombreReal,String contrasenia){
	
	Connection cn= utilidades.DatabaseConnector.dameConexion();
	//Logica para la insercion del usuario  en la base de datos
	String sql = "INSERT INTO usuarioslista (nombre, apellidos, mail, nombreReal, contrasenia) VALUES (?, ?, ?, ?, ?)";
	
	try (PreparedStatement StatementRegister = cn.prepareStatement(sql)){
		
		StatementRegister.setString(1, nombre);
		StatementRegister.setString(2, apellidos);
		StatementRegister.setString(3, mail);
		StatementRegister.setString(4, nombreReal);
		StatementRegister.setString(5, contrasenia);
		StatementRegister.executeQuery();
		System.out.println("Usuario  registrado");
		StatementRegister.close();
	} catch (Exception e) {
		// TODO: handle exception
        System.out.println("Error al registrarse: " + e.getMessage());

	}
}


public static String validacionFormulario(String nombreUsuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia)  {
	
	// Validacion de correo electrónico (funcion de commons validator)
	
			EmailValidator emailValidator = EmailValidator.getInstance();
			
			// Crear validadores con expresiones regulares para cada requisito
	        RegexValidator mayusculaValidator = new RegexValidator(".[A-Z].");  // Al menos una mayúscula
	        RegexValidator minusculaValidator = new RegexValidator(".[a-z].");  // Al menos una minúscula
	        RegexValidator numeroValidator = new RegexValidator(".[0-9].");      // Al menos un dígito
	        RegexValidator especialValidator = new RegexValidator(".[^A-Za-z0-9]."); // Al menos un carácter especial
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

public static String verificarNombreUsusario(String NombreUsuario) {
	
	Connection cn = DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario FROM usuarioslista";
	
	try (PreparedStatement StatementInicio = cn.prepareStatement(sql)) {
		
		ResultSet rs = StatementInicio.executeQuery();
		
		if (rs.next()) {
			if (rs.getString("nombreUsuario").equals(NombreUsuario)) {
				
				utilidades.DatabaseConnector.cerrarConexion(cn);
				
				return("El Usuario ya existe, ingrese otro distinto");
				
			} else {
				
				return("");

			}
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "error, no se ha podido ejecutar la setencia";
	
}




}