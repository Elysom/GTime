package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;



public class SCRUDusuarios {	
	

//Método para loggear a un usario a partir del usuarfio y password

public boolean inicioSesionScrud(String usuario,String contrasenia) {
	
	DatabaseConnector alumnosGestion = new DatabaseConnector();

	Connection conexionDeDatos = alumnosGestion.iniciarConexion();

	String sql = "SELECT nombreUsuario,contrasenia FROM usuarioslista WHERE nombreUsuario = ? && contrasenia = ?";
	
	try (PreparedStatement StatementInicio = conexionDeDatos.prepareStatement(sql)){
		
		StatementInicio.setString(1, usuario);
		
		StatementInicio.setString(2, contrasenia);
		
		ResultSet rs = StatementInicio.executeQuery();
		
		if (rs.next()) {
			
			if (rs.getString("nombreUsuario").equals(usuario) && rs.getString("contrasenia").equals(contrasenia)) {
				
				System.out.println("Inicio de sesión exitoso");
				
				alumnosGestion.cerrarConexion(conexionDeDatos);
				
				return true;
			
			} else {
				
				System.out.println("Nombre de usuario y contrasenia invalido");
				
				return false;
				
			}
			
		}
		
	} catch (Exception e) {
		// TODO: handle exception
        System.out.println("Error al iniciar sesión: " + e.getMessage());

	}
	
	return false;
	
}
}