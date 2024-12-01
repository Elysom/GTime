package utilidades;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import com.GTime.GTime.controladorLoggin;
import com.GTime.GTime.controladorPrincipal;
import com.GTime.GTime.controladorRegister;

import modelo.PlanAcademico;
import modelo.Rutina;
import modelo.Tarea;
import modelo.Usuarios;
//import src.sql.alumnos;
public class SCRUDusuarios {


//Método para loggear a un usario a partir del usuarfio y password

public static String loginSCRUD(String usuario,String contrasenia) {
	
	String resultado = "";
	
	Connection cnn= utilidades.DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario,contrasenia,tipo FROM usuarioslista WHERE nombreUsuario = ? && contrasenia = ?";
	
	try (PreparedStatement StatementInicio = cnn.prepareStatement(sql)){
		
		StatementInicio.setString(1, usuario);
		
		StatementInicio.setString(2, contrasenia);
		
		ResultSet rs = StatementInicio.executeQuery();
		
		while (rs.next()) {
			
			if (rs.getString("nombreUsuario").equals(usuario) && rs.getString("contrasenia").equals(contrasenia) && rs.getString("tipo").equals("Administrador")) {
				controladorPrincipal.tipoDeUsuario="Administrador";
				return "Inicio de sesión exitoso, modo Administrador";
				
			} else if (rs.getString("nombreUsuario").equals(usuario) && rs.getString("contrasenia").equals(contrasenia) && rs.getString("tipo").equals("Usuario")) {
				controladorPrincipal.tipoDeUsuario="Usuario";
				return "Inicio de sesión exitoso, modo Usuario";
 
			}
			
		}
		StatementInicio.close();
		rs.close();
		cnn.close();
		
	} catch (Exception e) {
		// TODO: handle exception
        return ("Error al iniciar sesión: " + e.getMessage());

	} 
	
	return "Usuario o contrasenia invalido";
	
}


public static void registerSCRUD(String nombre,String apellidos,String mail,String nombreReal,String contrasenia,String curso){
	
	Connection cnn= utilidades.DatabaseConnector.dameConexion();
	//Logica para la insercion del usuario  en la base de datos. (como solo vamos a crear usuarios alumno se le pone por defecto usuario)
	String sql = "INSERT INTO usuarioslista (nombreUsuario, apellidos, mail, nombreReal, contrasenia,curso,tipo) VALUES (?, ?, ?, ?, ?, ?,'Usuario')";
	
	try (PreparedStatement StatementRegister = cnn.prepareStatement(sql)){
		
		StatementRegister.setString(1, nombre);
		StatementRegister.setString(2, apellidos);
		StatementRegister.setString(3, mail);
		StatementRegister.setString(4, nombreReal);
		StatementRegister.setString(5, contrasenia);
		StatementRegister.setString(6, curso);
		StatementRegister.executeUpdate();
		System.out.println("Usuario  registrado");
		StatementRegister.close();
		cnn.close();
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
	            return "La contraseña debe contener al menos una letra mayúscula,\n además de una minúscula, un número y un carácter especial.";
	        } else if (!minusculaValidator.isValid(confContrasenia)) { // Verificar si tiene al menos una minúscula
	            return "La contraseña debe contener al menos una letra minúscula,\n además de una mayúscula, un número y un carácter especial.";
	        } else if (!numeroValidator.isValid(confContrasenia)) {
	            return "La contraseña debe contener al menos un número, además de\n una mayúscula, una minúscula y un carácter especial.";
	        } else if (!especialValidator.isValid(confContrasenia)) {
	            return "La contraseña debe contener al menos un carácter especial,\n además de una mayúscula, una minúscula y un número.";
	        } else if (curso == null) {
				return "Debes seleccionar un curso";
			}

	        return "¡Registro exitoso!";
	    }

public static String verificarNombreUsusario(String NombreUsuario) {
	
	Connection cnn = DatabaseConnector.dameConexion();
	
	String sql = "SELECT nombreUsuario FROM usuarioslista";
	
	try (PreparedStatement StatementInicio = cnn.prepareStatement(sql)) {
		
		ResultSet rs = StatementInicio.executeQuery();
		
		while (rs.next()) {
			if (rs.getString("nombreUsuario").equals(NombreUsuario)) {
				StatementInicio.close();
				rs.close();
				cnn.close();
				
				return("El Usuario ya existe, ingrese otro distinto");
				
			} 
		}
		StatementInicio.close();
		rs.close();
		cnn.close();
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} 
	return "Nombre Usuario Disponible";
	
}

public static void agregarEsquemaDatos(String nombre_usuario, String apellidos, String mail, String nombreReal, String contrasenia, String confContrasenia, String curso) {
	
	// Iniciamos conexion 
	
	try (Connection cnn = DatabaseConnector.dameConexionRamaPrincipal()){ // connectate a la rama principal no a listausuari0){
		Statement st = cnn.createStatement();
		
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
		        "  descripcion VARCHAR(20)," +
		        "  CONSTRAINT fk_PLANACADEMICO_USUARIO FOREIGN KEY (IDUsuario) REFERENCES USUARIO(IDusuario)" +
		        ");";
		
		String crearTablaTarea = 
		        "CREATE TABLE TAREA (" +
		        "  IDTarea INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
		        "  IDUsuario INT NOT NULL," +
		        "  nombreTarea VARCHAR(100) NOT NULL," +
		        "  fecha DATETIME NOT NULL," +
		        "  color VARCHAR(20)," +
		        " descripcion VARCHAR(200)," +
		        "  CONSTRAINT fk_tarea_usuario FOREIGN KEY (IDUsuario) REFERENCES USUARIO(IDusuario)" +
		        ");";
		
		String crearTablaRutina = 
                "CREATE TABLE Rutina (" +
                "  IDTarea INT NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                "  IDUsuario INT NOT NULL," +
                "  nombreTarea VARCHAR(100) NOT NULL," +
                "  diaSemana VARCHAR(20) NOT NULL," +
                "	hora TIME not null," +
                "  color VARCHAR(20)," +
                "  descripcion VARCHAR(200)," +
                "  fechaExcepcion DATE," +
                "  motivoExcepcion VARCHAR(200)," +
                "  CONSTRAINT fk_rutina_usuario FOREIGN KEY (IDUsuario) REFERENCES USUARIO(IDusuario)" +
                ");";

		// ejecutar el statement
		
		st.executeUpdate(crearTablaUsuario);
		
		st.executeUpdate(crearTablaPlanAcademico);
		
		st.executeUpdate(crearTablaTarea);
		
		st.executeUpdate(crearTablaRutina);
		st.close();
		// Insertar los datos del usuario en la tabla USUARIO
        String insertUsuario = 
            "INSERT INTO USUARIO (mail, nombreReal, apellidos, contrasenia, curso, nombreUsuario) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pst = cnn.prepareStatement(insertUsuario)) {
            pst.setString(1, mail);
            pst.setString(2, nombreReal);
            pst.setString(3, apellidos);
            pst.setString(4, contrasenia);
            pst.setString(5, curso);
            pst.setString(6, nombre_usuario);
            
            pst.executeUpdate();
            cnn.close();
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

public static void agregarPlan(String usuActual, String nombrePlan, Timestamp fechaHoraMinutos, String tipo, String curso, String asignatura,String color,String descripcion) {

	// nos conectamos a la esquema de datos del usuario admin que nos hayamos loggeado
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(usuActual);
	
	String introducirPlan = "INSERT INTO plan_academico (IDUsuario,nombrePlan,fecha,color,tipo,curso,asignatura,descripcion) VALUES (1, ?, ?, ?, ?, ?, ?, ?)";
	
	try (PreparedStatement stm = cnn.prepareStatement(introducirPlan) ){
		
		stm.setString(1, nombrePlan);
		stm.setTimestamp(2,fechaHoraMinutos);
		stm.setString(3, color);
		stm.setString(4, tipo);
		stm.setString(5, curso);
		stm.setString(6, asignatura);
		stm.setString(7, descripcion);
		
		stm.executeUpdate();
			
		System.out.println("Se agrego con exito");

		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	}
	
	// Ahora agregaremos el plan a la tabla general de todos los planes
	
	cnn = DatabaseConnector.dameConexionDatabaseEspecifica("listausuarios");
	
	introducirPlan = "INSERT INTO plan_academico (nombrePlan,fecha,color,tipo,curso,asignatura,descripcion) VALUES (?, ?, ?, ?, ?, ?, ?)";
	try (PreparedStatement stm = cnn.prepareStatement(introducirPlan) ){
		
		stm.setString(1, nombrePlan);
		stm.setTimestamp(2,fechaHoraMinutos);
		stm.setString(3, color);
		stm.setString(4, tipo);
		stm.setString(5, curso);
		stm.setString(6, asignatura);
		stm.setString(7, descripcion);
		
		stm.executeUpdate();
		cnn.close();
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	} 
	
	
}


public static List<PlanAcademico> rellanarListaAdminEspecifico (String nombreUsuGlobal) {
	
	List<PlanAcademico> listaDePlanesAcademico = new ArrayList<>();

	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
    String sql = "Select IDPlan,IDUsuario,nombrePlan,fecha,color,tipo,asignatura,curso,descripcion from plan_academico WHERE fecha >= NOW() order by fecha asc";

	
	// preparamos el statement
	
	try (PreparedStatement stm = cnn.prepareStatement(sql)) {
		
		ResultSet rs = stm.executeQuery(sql);
		
		while (rs.next()) {
			
			 PlanAcademico objetoPlan = new PlanAcademico(rs.getInt("IDPlan"), rs.getInt("IDUsuario") , rs.getString("nombrePlan"), rs.getTimestamp("fecha").toLocalDateTime(), rs.getString("color"), rs.getString("tipo"), rs.getString("asignatura"), rs.getString("curso"), rs.getString("descripcion"));
			
			listaDePlanesAcademico.add(objetoPlan);
			
		}
		stm.close();
		rs.close();
		cnn.close();
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	}
	
	return listaDePlanesAcademico;
}

public static void eliminarPlan(String ItemList) {
	
	String nombre = obtenerNombre(ItemList);
	
	Timestamp fecha = obtenerFechaHora(ItemList);
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(com.GTime.GTime.controladorLoggin.nombreUsuGlobal);
	
	String sql = "DELETE FROM plan_academico where nombrePlan = ? and fecha = ?";
	
	try (PreparedStatement eliminarRegistro = cnn.prepareStatement(sql)){
		eliminarRegistro.setString(1, nombre);
		eliminarRegistro.setTimestamp(2, fecha);
		eliminarRegistro.executeUpdate();
		
		System.out.println("Eliminacion con exito");
		eliminarRegistro.close();
		cnn.close();
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	}
	
}

//Función para obtener la fecha y hora
public static Timestamp obtenerFechaHora(String input) {
    String fechaHoraStr = input.substring(0, input.indexOf(" - ")).replace(",", "").trim(); // "2024-11-27 09:10"
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
	return Timestamp.valueOf(fechaHora);
}

//Función para obtener el nombre
public static String obtenerNombre(String input) {
 return input.substring(input.indexOf(" - ") + 3).trim();
}

// Funcion para obtener el curso especifico de un usuario

public static String obtenerCursoAlumno(String nombreUsuGlobal) {
	
	// Creamos la Connection
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(nombreUsuGlobal);
	
	String sql = "select curso from usuario";
	
	String nombreCurso = null;
	
	try (PreparedStatement st = cnn.prepareStatement(sql)){
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			nombreCurso = rs.getString("curso");
		}
		st.close();
		rs.close();
		cnn.close();
		return nombreCurso;
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
		return nombreCurso;
	}
}

// Funcion para obtener un listado plan academico filtrado por el curso

public static List<PlanAcademico> obtenerPlanDeCursoEspecifico (String curso) {
	
	// creamos connection
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica("listausuarios");
	
	String sql = "Select * from plan_academico where curso = ? and fecha >= NOW() order by fecha asc";
	
	List<PlanAcademico> ObjetosPlanesAcademicos = new ArrayList();
	
	try (PreparedStatement st = cnn.prepareStatement(sql)){
		
		st.setString(1, curso);
		
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
			
			PlanAcademico objetoPlan = new PlanAcademico(rs.getInt("IDPlan"), 1 , rs.getString("nombrePlan"), rs.getTimestamp("fecha").toLocalDateTime(), rs.getString("color"), rs.getString("tipo"), rs.getString("asignatura"), rs.getString("curso"), rs.getString("descripcion"));
			
			
			ObjetosPlanesAcademicos.add(objetoPlan);
			
		}
		st.close();
		rs.close();
		cnn.close();
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	} 
	return ObjetosPlanesAcademicos;
}

public static void agregarTareaUsuario(String nombreTarea, Timestamp fecha_hora,String color, String descripcion) {
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "INSERT INTO tarea (IDUsuario,nombreTarea,fecha,color,descripcion) VALUES (1, ?, ?, ?, ?)";
	
	try (PreparedStatement stm = cnn.prepareStatement(sql)){
		
		stm.setString(1, nombreTarea);
		stm.setTimestamp(2, fecha_hora);
		stm.setString(3, color);
		stm.setString(4, descripcion);
		
		
		if (stm.executeUpdate() > 0) {
			System.out.println("Datos Actualizados");
		} else {
			System.out.println("No se actualizo ningun dato");
		}
		stm.close();
		cnn.close();
		
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	} 
}

public static Timestamp obtenerFechaHoraSQL(LocalDate fecha, int horas, int minutos) {
	
	LocalDateTime fechaHorayMinutos = LocalDateTime.of(fecha, LocalTime.of(horas, minutos));
	
	return Timestamp.valueOf(fechaHorayMinutos);
	
}

// rellenar lista de tareas de usuario especifico

public static List<Tarea> rellenarTareaUsuEspecifico() {
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "SELECT * FROM tarea WHERE fecha >= NOW() order by fecha asc";
	
	List<Tarea> listaTarea = new ArrayList();
	
	try (PreparedStatement stm = cnn.prepareStatement(sql)) {
		
		ResultSet rs = stm.executeQuery();
		
		while (rs.next()) {
			
			Tarea introducirTarea = new Tarea(rs.getInt("IDTarea"), rs.getInt("IDUsuario"),rs.getString("nombreTarea"),rs.getTimestamp("fecha").toLocalDateTime(),rs.getString("color"),rs.getString("descripcion"));
			
			listaTarea.add(introducirTarea);
			
			}
		
		stm.close();
		rs.close();
		cnn.close();
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	}
	return listaTarea;
}

public static void creacionRutina(String nombreTarea,String diaSemana, LocalTime hora, String color, String descripcion, Timestamp fechaExcepcion, String motivoExcepcion) {
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "INSERT INTO rutina (IDUsuario,nombreTarea,diaSemana,hora,color,descripcion,fechaExcepcion,motivoExcepcion) VALUES (1,? , ?, ?, ?, ?, ?, ?)";
	
	try (PreparedStatement stm = cnn.prepareStatement(sql)){
		
		stm.setString(1, nombreTarea);
		stm.setString(2, diaSemana);
		stm.setTime(3, Time.valueOf(hora));
		stm.setString(4, color);
		stm.setString(5, descripcion);
		stm.setTimestamp(6, fechaExcepcion);
		stm.setString(7, motivoExcepcion);
		
		stm.executeUpdate();
		stm.close();
		cnn.close();
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
		System.out.println(e.getMessage());
	} 
	
}

// Rellenar Lista Rutinas de Usu Especifico

public static List<Rutina> rellenarRutinaUsuEspecifico() {
	
	Connection cnn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "SELECT IDUsuario,nombreTarea,diaSemana,hora,color,descripcion,fechaExcepcion,motivoExcepcion from rutina";
	
	List<Rutina> rutinaPoo = new ArrayList<Rutina>();
	
	try (PreparedStatement stm = cnn.prepareStatement(sql)){
		
		ResultSet rs = stm.executeQuery();
		
		while (rs.next()) {
			
			Rutina agregarRutina = new Rutina(rs.getInt("IDUsuario"),rs.getString("nombreTarea"), rs.getString("diaSemana"), rs.getTime("hora").toLocalTime() , rs.getString("color"),rs.getString("descripcion"), rs.getTimestamp("fechaExcepcion"), rs.getString("motivoExcepcion"));
			
			rutinaPoo.add(agregarRutina);
			
		}
		stm.close();
		rs.close();
		cnn.close();
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
		
	}
	
	return rutinaPoo;
}

// Desglosar el string y eliminar el elemento - Rutinas

public static void eliminarRutinas(String nombreTarea, String diaSemana, LocalTime hora ) {
	
	Connection cn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "DELETE FROM rutina where nombreTarea = ? and diaSemana = ? and hora = ?";
	
	try {
		PreparedStatement stm = cn.prepareStatement(sql);
		
		stm.setString(1, nombreTarea);
		stm.setString(2, diaSemana);
		stm.setTime(3, Time.valueOf(hora));
		
		stm.executeUpdate();
		
		System.out.println("Se elimino con exito");
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
        utilidades.DatabaseConnector.cerrarConexion(cn); // Aseguramos el cierre de la conexión
    }
}


public static void eliminarTareas(LocalDateTime fechaHora, String nombreTarea) {
	
	Connection cn = DatabaseConnector.dameConexionDatabaseEspecifica(controladorLoggin.nombreUsuGlobal);
	
	String sql = "DELETE FROM tarea WHERE fecha = ? and nombreTarea = ?";
	
	Timestamp timestamp = Timestamp.valueOf(fechaHora);
	
	try (PreparedStatement stm = cn.prepareStatement(sql)){
		
		stm.setTimestamp(1, timestamp);
		stm.setString(2, nombreTarea);
		
		stm.executeUpdate();
		
		
		
	} catch (SQLException e) {
		// TODO: handle exception
		System.out.println(e.getLocalizedMessage());
	} finally {
        utilidades.DatabaseConnector.cerrarConexion(cn); // Aseguramos el cierre de la conexión
    }
	
}


}