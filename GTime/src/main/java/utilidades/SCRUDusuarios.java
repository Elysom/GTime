package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;



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
public void registerSCRUD(){
	
}




}