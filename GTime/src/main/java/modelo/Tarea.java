
package modelo;

import java.time.LocalDateTime;

public class Tarea {
    private int idTarea;        // ID único para la tarea
    private int idUsuario;      // ID del usuario al que pertenece la tarea
    private String nombreTarea;
    private LocalDateTime fecha;
    private String color;

    // Constructor con los identificadores incluidos
    public Tarea(int idTarea, int idUsuario, String nombreTarea, LocalDateTime fecha, String color) {
        this.idTarea = idTarea;
        this.idUsuario = idUsuario;
        this.nombreTarea = nombreTarea;
        this.fecha = fecha;
        this.color = color;
    }

    // Getters y Setters
    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

