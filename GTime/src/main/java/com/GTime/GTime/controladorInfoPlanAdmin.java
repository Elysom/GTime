package com.GTime.GTime;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import modelo.PlanAcademico;
import modelo.Rutina;
import modelo.Tarea;
import utilidades.SCRUDusuarios;

public class controladorInfoPlanAdmin implements Initializable  {

	@FXML
	private Label txtTitulo;
	
	@FXML
	private Label txtFecha;
	
	@FXML
	private Label txtModulo;
	
	@FXML
	private Label txtCurso;
	
	@FXML
	private TextArea txtDescripcion;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		rellenarInformacion();
		
		
	}
	
	public void rellenarInformacion() {
		
		
		List<PlanAcademico> listaPlan = SCRUDusuarios.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);
		
		String input = null;
		
		
		if (controladorUsuario.infoSeleccionada != null) {
			
			input = controladorUsuario.infoSeleccionada;
			
			String cursoNombre = SCRUDusuarios.obtenerCursoAlumno(controladorLoggin.nombreUsuGlobal);
			
			listaPlan = SCRUDusuarios.ObtenerPlanDeCursoEspecifico(cursoNombre);
			
			for (PlanAcademico b : listaPlan) {
				
				if (b.toString().equals(input)) {
					
					txtTitulo.setText(b.getNombrePlan());
					
					txtFecha.setText(b.getFechahorasPlan().toString());
					
					txtCurso.setText("Curso: " +b.getCurso());
					
					txtModulo.setText("Modulo: " +b.getAsignatura());
					
					txtDescripcion.setText(b.getDescripcion());
					
				}
				
			}
			
			List <Tarea> listaTarea = SCRUDusuarios.rellenarTareaUsuEspecifico();
			
			for (Tarea a : listaTarea) {
				
				if (a.toString().equals(input)) {
					
					txtTitulo.setText(a.getNombreTarea());
					
					txtFecha.setText(a.getFecha().toString());
					
					txtCurso.setText("Tarea");
					
					txtModulo.setText(controladorLoggin.nombreUsuGlobal);
					
					txtDescripcion.setText(a.getDescripcion());
					
				}
			}
			
			List<Rutina> listaRutina = SCRUDusuarios.rellenarRutinaUsuEspecifico();
			
			for (Rutina a : listaRutina) {
				
				if (a.toString().equals(input)) {
					
					txtTitulo.setText(a.getNombreTarea());
					
					txtFecha.setText(a.getDiaSemana() + " - " + a.getHora().toString());
					
					txtCurso.setText("Rutina");
					
					txtModulo.setText(controladorLoggin.nombreUsuGlobal);
					
					txtDescripcion.setText(a.getDescripcion());
				}
			}
		} else {
			System.out.println("No encontro nada " +input);
		}
		
		
		if (controladorPrincipal.infoSeleccionada != null) {
			
			input = controladorPrincipal.infoSeleccionada;
			
			for (PlanAcademico b : listaPlan) {
				
				if (b.toString().equals(input)) {
					
					txtTitulo.setText(b.getNombrePlan());
					
					txtFecha.setText(b.getFechahorasPlan().toString());
					
					txtCurso.setText(b.getCurso());
					
					txtModulo.setText(b.getAsignatura());
					
					txtDescripcion.setText(b.getDescripcion());
					
				}
			}
		} else {
			System.out.println("No encontro nada " +input);
		}
				
			
		}
		
		
	}




