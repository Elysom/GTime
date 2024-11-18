package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PlanAcademico {
    private int idPlan;           // ID único para el plan académico
    private int idUsuario;        // ID del usuario al que pertenece el plan académico
    private String nombrePlan;    // Nombre del plan académico
    private LocalDateTime fechahorasPlan;  // Fecha y hora en la que se crea o actualiza el plan
    private String color;         // Color asociado al plan académico (para representaciones visuales)
    private String tipo;          // Tipo de plan (opcional)
    private String asignatura;    // Asignatura relacionada al plan académico
    private String curso;         // Curso o nivel educativo asociado
    private String descripcion;

    // Constructor con los identificadores incluidos

	public PlanAcademico(int idPlan, int idUsuario, String nombrePlan, LocalDateTime fechahorasPlan, String color,
			String tipo, String asignatura, String curso, String descripcion) {
		super();
		this.idPlan = idPlan;
		this.idUsuario = idUsuario;
		this.nombrePlan = nombrePlan;
		this.fechahorasPlan = fechahorasPlan;
		this.color = color;
		this.tipo = tipo;
		this.asignatura = asignatura;
		this.curso = curso;
		this.descripcion = descripcion;
	}

	public PlanAcademico() {
		super();
		// TODO Auto-generated constructor stub
	}

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

	public LocalDateTime getFechahorasPlan() {
		return fechahorasPlan;
	}

	public void setFechahorasPlan(LocalDateTime fechahorasPlan) {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	
	@Override
	public String toString() {
	    DateTimeFormatter fechaFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

	    String fecha = fechahorasPlan != null ? fechahorasPlan.format(fechaFormatter) : "N/A";
	    String hora = fechahorasPlan != null ? fechahorasPlan.format(horaFormatter) : "N/A";

	    return String.format(
	        "%s, %s - %s",
	        fecha,
	        hora,
	        nombrePlan != null ? nombrePlan : "N/A"
	       
	    );
	}

	

   
}

