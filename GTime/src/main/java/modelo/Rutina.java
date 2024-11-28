package modelo;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Rutina {
    private int idUsuario;             // ID del usuario asociado.
    private String nombreTarea;        // Nombre de la rutina o tarea.
    private String diaSemana;          // Día de la semana (L, M, X, etc.).
    private LocalTime hora;            // Hora de la rutina.
    private String color;              // Color asociado en formato hexadecimal (#RRGGBB).
    private String descripcion;        // Descripción de la rutina.
    private Timestamp fechaExcepcion;  // Fecha para posibles excepciones.
    private String motivoExcepcion;    // Motivo de la excepción.

    // Constructor completo
    public Rutina(int idUsuario, String nombreTarea, String diaSemana, LocalTime hora, 
                  String color, String descripcion, Timestamp fechaExcepcion, String motivoExcepcion) {
        this.idUsuario = idUsuario;
        this.nombreTarea = nombreTarea;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.color = color;
        this.descripcion = descripcion;
        this.fechaExcepcion = fechaExcepcion;
        this.motivoExcepcion = motivoExcepcion;
    }

    // Constructor simple
    public Rutina(String nombreTarea, String diaSemana, LocalTime hora, String color, String descripcion) {
        this.nombreTarea = nombreTarea;
        this.diaSemana = diaSemana;
        this.hora = hora;
        this.color = color;
        this.descripcion = descripcion;
    }

    // Getters y setters
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

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaExcepcion() {
        return fechaExcepcion;
    }

    public void setFechaExcepcion(Timestamp fechaExcepcion) {
        this.fechaExcepcion = fechaExcepcion;
    }

    public String getMotivoExcepcion() {
        return motivoExcepcion;
    }

    public void setMotivoExcepcion(String motivoExcepcion) {
        this.motivoExcepcion = motivoExcepcion;
    }

    // Método para mostrar los datos en forma legible
    
    @Override
    public String toString() {
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        // Convertir hora al formato deseado o mostrar "N/A" si no está disponible
        String hora = this.hora != null ? this.hora.format(horaFormatter) : "N/A";

        // Manejar nombreTarea y diaSemana con valores predeterminados si son nulos
        String nombre = this.nombreTarea != null ? this.nombreTarea : "Tarea desconocida";
        String dia = this.diaSemana != null ? this.diaSemana : "Día no especificado";

        // Retornar en el formato deseado
        return String.format(
            "RUTINA - %s _ %s _ %s",
            nombre,
            dia,
            hora
        );
    }

}

