package utilidades;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {

	public static Connection dameConexion(String bbdd) {
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
	        		("jdbc:mysql://localhost:3306/"+bbdd+"?useSSL=false",
	        				"root","");
	  
	    } catch (SQLException ex) {
	        ex.printStackTrace() ;
	        System.out.println("SQLException : " + ex.getMessage());
	    }
		return conn;
	}
	
	/**
	 * 
	 * @param bbdd : Nombre de la base de datos a la que nos conectaremos
	 * @param archivoScript , direccion y nombre del archivo de script a ejecutar
	 */
	public static void lanzaScript(String bbdd, String archivoScript) {
		        //archivoScript = "C:/utilidades/primero/ejercicios/primerodam.sql";

		        try {
		            Connection conn = dameConexion(bbdd);
		            
		            if (conn!=null) {
			            Statement stmt = conn.createStatement();
			            
			            // Leer el archivo SQL
			            String script = new String(Files.readAllBytes(Paths.get(archivoScript)),
			            		StandardCharsets.UTF_8);
			            
			            // Ejecutar el script SQL
			            stmt.execute(script);
			            
			            System.out.println("Script SQL ejecutado correctamente.");
			            
			            conn.close();
			            }else
			            	System.out.println("No se ha podido establecer la conexion");
		        } catch (SQLException | IOException e) {
		            e.printStackTrace();
		        }
		    		
	} // lanza script
}