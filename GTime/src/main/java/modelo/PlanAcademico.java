package modelo;

import java.time.LocalDateTime;

public class PlanAcademico {
    private int idPlan;           // ID único para el plan académico
    private int idUsuario;        // ID del usuario al que pertenece el plan académico
    private String nombrePlan;    // Nombre del plan académico
    private LocalDateTime fechahorasPlan;  // Fecha y hora en la que se crea o actualiza el plan
    private String color;         // Color asociado al plan académico (para representaciones visuales)
    private String tipo;          // Tipo de plan (opcional)
    private String asignatura;    // Asignatura relacionada al plan académico
    private String curso;         // Curso o nivel educativo asociado


    // Constructor con los identificadores incluidos
   public PlanAcademico(int idPlan, int idUsuario, String nombrePlan, String horasPlan, String diasSemanas,
                         LocalDateTime fecha, String color, String tipo, String asignatura, String curso) {
        this.idPlan = idPlan;
        this.idUsuario = idUsuario;
        this.nombrePlan = nombrePlan;
        this.fechahorasPlan = fechahorasPlan;
        this.color = color;
        this.tipo = tipo;
        this.asignatura = asignatura;
        this.curso = curso;
    }

    // Getters y Setters
    public int getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(int idPlan) {
        this.idPlan = idPlan;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }


    public LocalDateTime getFecha() {
        return fechahorasPlan;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fechahorasPlan = fechahorasPlan;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(String asignatura) {
        this.asignatura = asignatura;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}

