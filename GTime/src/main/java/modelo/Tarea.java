
package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarea {
    private int idTarea;        // ID único para la tarea
    private int idUsuario;      // ID del usuario al que pertenece la tarea
    private String nombreTarea;
    private LocalDateTime fechahorasTarea;
    private String color;
    private String descripcion;

    // Constructor sin parámetros
    public Tarea() {
    }

    // Constructor con parámetros
    public Tarea(int idTarea, int idUsuario, String nombreTarea, LocalDateTime fecha, String color, String descripcion) {
        this.idTarea = idTarea;
        this.idUsuario = idUsuario;
        this.nombreTarea = nombreTarea;
        this.fechahorasTarea = fecha;
        this.color = color;
        this.descripcion = descripcion;
    }

    // Getters
    public int getIdTarea() {
        return idTarea;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public LocalDateTime getFecha() {
        return fechahorasTarea;
    }

    public String getColor() {
        return color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    // Setters
    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fechahorasTarea = fecha;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

	@Override
	public String toString() {
		
		DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

	    String fecha = fechahorasTarea != null ? fechahorasTarea.format(fechaFormatter) : "N/A";
	    String hora = fechahorasTarea != null ? fechahorasTarea.format(horaFormatter) : "N/A";
	    
	    return String.format(
		        "%s, %s - %s",
		        fecha,
		        hora,
		        nombreTarea != null ? nombreTarea : "N/A"
		       
		    );
	}
    
    
}
