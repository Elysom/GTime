package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import GTime.controladorRegister;
import modelo.Usuarios;




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
public static void registerSCRUD(String nombre,String apellidos,String mail,String nombreReal,String contrasenia,String curso){
	
	Connection cn= utilidades.DatabaseConnector.dameConexion();
	//Logica para la insercion del usuario  en la base de datos
	String sql = "INSERT INTO usuarioslista (nombreUsuario, apellidos, mail, nombreReal, contrasenia,curso) VALUES (?, ?, ?, ?, ?, ?)";
	
	try (PreparedStatement StatementRegister = cn.prepareStatement(sql)){
		
		StatementRegister.setString(1, nombre);
		StatementRegister.setString(2, apellidos);
		StatementRegister.setString(3, mail);
		StatementRegister.setString(4, nombreReal);
		StatementRegister.setString(5, contrasenia);
		StatementRegister.setString(6, curso);
		StatementRegister.executeUpdate();
		System.out.println("Usuario  registrado");
		StatementRegister.close();
	} catch (Exception e) {
		// TODO: handle exception
        System.out.println("Error al registrarse: " + e.getMessage());

	}
}


public static String validacionFormulario(String nombreUsuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia, String curso)  {
	
	// Validacion de correo electrónico (funcion de commons validator)
	
			EmailValidator emailValidator = EmailValidator.getInstance(false, false);
			
			// Crear validadores con expresiones regulares para cada requisito
			RegexValidator mayusculaValidator = new RegexValidator(".*[A-Z].*");  // Al menos una mayúscula
			RegexValidator minusculaValidator = new RegexValidator(".*[a-z].*");  // Al menos una minúscula
			RegexValidator numeroValidator = new RegexValidator(".*[0-9].*");     // Al menos un dígito
			RegexValidator especialValidator = new RegexValidator(".*[^A-Za-z0-9].*"); // Al menos un carácter especial
			RegexValidator noNumerosValidator = new RegexValidator("^[^0-9]*$"); // Sin números
	        
	        if (nombreUsuario.isEmpty()) {
	            return "Por favor, rellene el campo 'nombre'.";
	        } else if (apellidos.isEmpty()) {
	            return "Por favor, rellene el campo 'apellidos'.";
	        } else if (!emailValidator.isValid(mail)) {
	            return "Correo electrónico no válido.";
	        } else if (!noNumerosValidator.isValid(nombreReal) || especialValidator.isValid(nombreReal)) {
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
	        } else if (curso == null) {
				return "Debes seleccionar un curso";
			}

	        return "¡Registro exitoso!";
	    }

public static String verificarNombreUsusario(String NombreUsuario) {
	
	Connection cn = DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario FROM usuarioslista";
	
	try (PreparedStatement StatementInicio = cn.prepareStatement(sql)) {
		
		ResultSet rs = StatementInicio.executeQuery();
		
		while (rs.next()) {
			if (rs.getString("nombreUsuario").equals(NombreUsuario)) {
				
				utilidades.DatabaseConnector.cerrarConexion(cn);
				
				return("El Usuario ya existe, ingrese otro distinto");
				
			} 
		}
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	return "Nombre Usuario Disponible";
	
}

public static void agregarEsquemaDatos(String nombre_usuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia, String curso) {
	
	// Iniciamos conexion 
	
	try (Connection cn = DatabaseConnector.dameConexionRamaPrincipal()){ // connectate a la rama principal no a listausuari0){
		Statement st = cn.createStatement();
		
		// Crear la Database y guardar cambios
		String sql = "CREATE DATABASE " +nombre_usuario+ ";";
		st.executeUpdate(sql);
		
		// Usar la database Gonzalo
		String useDatase = "USE " +nombre_usuario;
		st.execute(useDatase);
		
		// declarar las tablas
		
		String crearTablaUsuario = 
		        "CREATE TABLE USUARIO (" +
		        "  IDusuario INT AUTO_INCREMENT PRIMARY KEY," +
		        "  mail VARCHAR(40) NOT NULL," +
		        "  nombreReal VARCHAR(40) NOT NULL," +
		        "  apellidos VARCHAR(90) NOT NULL," +
		        "  contrasenia VARCHAR(30) NOT NULL," +
		        "  curso VARCHAR(20) NOT NULL," +
		        "  fotoPerfil BLOB," +
		        "  nombreUsuario VARCHAR(90) NOT NULL UNIQUE" +
		        ");";
		

		String crearTablaPlanAcademico = 
		        "CREATE TABLE PLAN_ACADEMICO (" +
		        "  IDPlan INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
		        "  IDUsuario INT NOT NULL," +
		        "  nombrePlan VARCHAR(200) DEFAULT NULL," +
		        "  fecha DATETIME NOT NULL," +
		        "  color VARCHAR(7) NOT NULL," +
		        "  tipo VARCHAR(20) DEFAULT NULL," +
		        "  asignatura VARCHAR(20) NOT NULL," +
		        "  curso VARCHAR(20) NOT NULL," +
		        "  CONSTRAINT fk_PLANACADEMICO_USUARIO FOREIGN KEY (IDUsuario) REFERENCES USUARIO(IDusuario)" +
		        ");";
		
		String crearTablaTarea = 
		        "CREATE TABLE TAREA (" +
		        "  IDTarea INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
		        "  IDUsuario INT NOT NULL," +
		        "  nombreTarea VARCHAR(100) NOT NULL," +
		        "  fecha DATETIME NOT NULL," +
		        "  color VARCHAR(20)," +
		        "  CONSTRAINT fk_tarea_usuario FOREIGN KEY (IDUsuario) REFERENCES USUARIO(IDusuario)" +
		        ");";
		// ejecutar el statement
		
		st.executeUpdate(crearTablaUsuario);
		
		st.executeUpdate(crearTablaPlanAcademico);
		
		st.executeUpdate(crearTablaTarea);
		
		// Insertar los datos del usuario en la tabla USUARIO
        String insertUsuario = 
            "INSERT INTO USUARIO (mail, nombreReal, apellidos, contrasenia, curso, nombreUsuario) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = cn.prepareStatement(insertUsuario)) {
            pst.setString(1, mail);
            pst.setString(2, nombreReal);
            pst.setString(3, apellidos);
            pst.setString(4, contrasenia);
            pst.setString(5, curso);
            pst.setString(6, nombre_usuario);
            
            pst.executeUpdate();
            
        } catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    		System.out.println("Error al ingresar la informacion");
    	}        
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
}

// Solo los admin pueden usar esta funcion

public static void agregarPlan(String usuActual, String nombrePlan, Timestamp fechaHoraMinutos, String tipo, String Curso, String Asignatura,String Color) {
	
}

}