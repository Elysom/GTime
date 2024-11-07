package utilidades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	

	
    // Método para conectar con la base de datos
    
	public static Connection dameConexion() {
		Connection conn = null;
		
				try { // registro el driver de connection
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			// Establezco la conexion con la BBDD
	    try {
	    
	        conn = DriverManager.getConnection
	        		("jdbc:mysql://localhost:3306/listausuarios?useSSL=false","root","");
	  
	    } catch (SQLException ex) {
	        ex.printStackTrace() ;
	        System.out.println("SQLException : " + ex.getMessage());
	    }
		return conn;
	}
    // Funcion para cerrar la conexion
    
    public static void cerrarConexion(Connection connection) {
    	if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada.");
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexión: " + e.getMessage());
            }
    }

    }
}