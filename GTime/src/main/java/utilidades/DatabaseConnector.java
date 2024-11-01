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
	
	// Variables para iniciar la conexion
	
	private static final String URL = "jdbc:mysql://localhost:3306/listausuarios";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
    // Método para conectar con la base de datos
    
    public static Connection iniciarConexion() {
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Se realizo la conexion con exito " +conexion.getCatalog()+ " = " +conexion);
        } catch (SQLException e) {
            System.out.println("Error en la conexión: " + e.getMessage());
        }
        return conexion;
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