package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import GTime.controladorRegister;




public class SCRUDusuarios {	
	

//Método para loggear a un usario a partir del usuarfio y password

public static String loginSCRUD(String usuario,String contrasenia) {
	
	String resultado = "";
	
	Connection cn= utilidades.DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario,contrasenia FROM usuarioslista WHERE nombreUsuario = ? && contrasenia = ?";
	
	try (PreparedStatement StatementInicio = cn.prepareStatement(sql)){
		
		StatementInicio.setString(1, usuario);
		
		StatementInicio.setString(2, contrasenia);
		
		ResultSet rs = StatementInicio.executeQuery();
		
		while (rs.next()) {
			
			if (rs.getString("nombreUsuario").equals(usuario) && rs.getString("contrasenia").equals(contrasenia)) {
				
				return "Inicio de sesión exitoso";
				
			}
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
        return ("Error al iniciar sesión: " + e.getMessage());

	}
	
	return "Usuario o contrasenia invalido";
	
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
	        } else if (noNumerosValidator.isValid(nombreReal) || especialValidator.isValid(nombreReal)) {
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

public static void agregarEsquemaDatos(String nombre_usuario) {
	
	// Iniciamos conexion 
	
	try (Connection cn = DatabaseConnector.dameConexionRamaPrincipal()){ // connectate a la rama principal no a listausuari0){
		Statement st = cn.createStatement();
		
		// Crear la Database y guardar cambios
		String sql = "CREATE DATABASE " +nombre_usuario+ ";";
		st.executeUpdate(sql);
		
		// Usar la database Gonzalo
		String useDatase = "USE" +nombre_usuario;
		st.executeUpdate(useDatase);
		
		// declarar las tablas
		
		String createTableNormal = "CREATE TABLE IF NOT EXISTS normal (IDusuario INT AUTO_INCREMENT PRIMARY KEY, mail VARCHAR(40) NOT NULL, nombreReal VARCHAR(40) NOT NULL, apellidos VARCHAR(90) NOT NULL, contrasenia VARCHAR(30) NOT NULL, nombreUsario VARCHAR(90) NOT NULL UNIQUE, fotoPerfil BLOB, CONSTRAINT chk_mail CHECK (mail LIKE '%@gtime.com'), CONSTRAINT chk_contrasenia CHECK (LENGTH(contrasenia) >= 8 AND contrasenia REGEXP '[A-Z]' AND contrasenia REGEXP '[a-z]' AND contrasenia REGEXP '[0-9]' AND contrasenia REGEXP '[^A-Za-z0-9]'))";

		String createTableEducacion = "CREATE TABLE IF NOT EXISTS EDUCACION (IDeducacion INT AUTO_INCREMENT PRIMARY KEY, FKusuario INT NOT NULL, CONSTRAINT fk_educacion_usuario FOREIGN KEY (FKusuario) REFERENCES normal(IDusuario))";

		String createTablePlanAcademico = "CREATE TABLE IF NOT EXISTS plan_academico (IDPlan INT NOT NULL AUTO_INCREMENT, IDCalendareducacion INT NOT NULL, nombrePlan VARCHAR(200) DEFAULT NULL, horasPlanInicio VARCHAR(7) DEFAULT NULL, horasPlanFin VARCHAR(7) DEFAULT NULL, diasSemanas VARCHAR(20) DEFAULT NULL, color VARCHAR(7) NOT NULL, tipo VARCHAR(20) DEFAULT NULL, PRIMARY KEY (IDPlan), CONSTRAINT fk_calendar_educacion FOREIGN KEY (IDCalendareducacion) REFERENCES EDUCACION(IDeducacion))";

		String createTablePersonal = "CREATE TABLE IF NOT EXISTS PERSONAL (IDPersonal INT NOT NULL AUTO_INCREMENT, IDUsuario INT NOT NULL, PRIMARY KEY (IDPersonal), CONSTRAINT fk_personal_normal FOREIGN KEY (IDUsuario) REFERENCES normal(IDusuario))";

		String createTableRutina = "CREATE TABLE IF NOT EXISTS RUTINA (IDrutina INT NOT NULL AUTO_INCREMENT, nombreRutina VARCHAR(100) NOT NULL, diasSemanas VARCHAR(20) NOT NULL, horasRutinaInicio TIME NOT NULL, horasRutinaFin TIME NOT NULL, color VARCHAR(20), IDPersonal INT, PRIMARY KEY (IDrutina), CONSTRAINT fk_calendario_personal FOREIGN KEY (IDPersonal) REFERENCES PERSONAL(IDPersonal), CONSTRAINT chk_dias_semana_rutina CHECK (diasSemanas IN ('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo')))";

		String createTableTarea = "CREATE TABLE IF NOT EXISTS TAREA (IDTarea INT NOT NULL AUTO_INCREMENT, nombreTarea VARCHAR(100) NOT NULL, diasSemanas VARCHAR(20) NOT NULL, color VARCHAR(20), IDPersonal INT, PRIMARY KEY (IDTarea), CONSTRAINT fk_calendario_personal2 FOREIGN KEY (IDPersonal) REFERENCES PERSONAL(IDPersonal), CONSTRAINT chk_dias_semana_tarea CHECK (diasSemanas IN ('Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado', 'Domingo')))";		
		
		// ejecutar el statement
		
		st.executeUpdate(createTableNormal);
		
		st.executeUpdate(createTableEducacion);
		
		st.executeUpdate(createTablePlanAcademico);
		
		st.executeUpdate(createTablePersonal);
		
		st.executeUpdate(createTableRutina);
		
		st.executeUpdate(createTableTarea);
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}




}