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
import javafx.scene.text.Text;
import modelo.PlanAcademico;
import utilidades.SCRUDusuarios;

public class controladorInfoPlanAdmin implements Initializable  {

	@FXML
	private Label txtTitulo;
	
	@FXML
	private Label txtFecha;
	
	@FXML
	private Label txtHora;
	
	@FXML
	private Label txtModulo;
	
	@FXML
	private Label txtCurso;
	
	@FXML
	private Label txtDescripcion;
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		rellenarInformacion();
		
		
	}
	
	public void rellenarInformacion() {
		
		List<PlanAcademico> listaPlan = SCRUDusuarios.rellanarListaAdminEspecifico(controladorLoggin.nombreUsuGlobal);
		
		System.out.println(1);
		
		String input = null;
		
		for (PlanAcademico a : listaPlan) {
			
			// La cadena original
			
			if (com.GTime.GTime.controladorPrincipal.infoSeleccionada != null) {
				input = com.GTime.GTime.controladorPrincipal.infoSeleccionada;
			} else if (com.GTime.GTime.controladorUsuario.infoSeleccionada != null) {
				input = com.GTime.GTime.controladorUsuario.infoSeleccionada;
				
				listaPlan = null;
				listaPlan = SCRUDusuarios.obtenerPlanDeCursoEspecifico(controladorLoggin.nombreUsuGlobal);
				
			}
	        

	        // Encontrar las partes usando substring
	        String fechaHoraStr = input.substring(0, input.indexOf(" - ")).replace(",", "").trim(); // "2024-11-27 09:10"
	        String nombre = input.substring(input.indexOf(" - ") + 3).trim(); // "gimnasia"

	        // Convertir la fecha y hora a LocalDateTime
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
	        LocalDateTime fechaHora = LocalDateTime.parse(fechaHoraStr, formatter);
	        
	        if (a.getNombrePlan().equals(nombre) && a.getFechahorasPlan().equals(fechaHora)) {
				
	        	System.out.println(2);
	        	
	        	txtTitulo.setText(a.getNombrePlan()+ " - " +a.getTipo());
	        	
	        	txtFecha.setText(fechaHora.getDayOfMonth()+ " - " +fechaHora.getMonthValue()+ " - " +fechaHora.getYear());
	        	
	        	txtHora.setText(fechaHora.getHour()+ " - " +fechaHora.getMinute());
	        	
	        	txtModulo.setText(a.getAsignatura());
	        	
	        	txtCurso.setText(a.getCurso());
	        	
	        	txtDescripcion.setText(a.getDescripcion());
	        	
	        	System.out.println("Entro en el if");
	        	
			}

		}
	}



}
