package com.GTime.GTime;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import utilidades.SCRUDusuarios;

public class controladorFormularioTarea implements Initializable {

	@FXML 
	private Button btnCrear; 
	@FXML
	private ColorPicker txtColor; 
	@FXML 
	private DatePicker txtFecha; 
	@FXML 
	private Spinner<Integer> txtHoras;
	@FXML
	private Spinner<Integer> txtMinutos; 
	@FXML 
	private TextArea txtDescripcion; 
	@FXML 
	private TextField txtTarea; 
	@FXML 
	private Label txtValidacion;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		// Confiurar las horas del spinner tipo de 00 a 23
		
		SpinnerValueFactory<Integer> horas = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		txtHoras.setValueFactory(horas);
		
		// Lo mismo con los minutos
		
		SpinnerValueFactory<Integer> minutos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		txtMinutos.setValueFactory(minutos);
		
		
		
	}
	
	@FXML
	public void recogerDatosTareas(ActionEvent event) {
		
		// agregamos los datos bonitos de nuestras bonita variable
		
		SCRUDusuarios.agregarTareaUsuario(txtTarea.getText(),SCRUDusuarios.obtenerFechaHoraSQL(txtFecha.getValue(), txtHoras.getValue(), txtMinutos.getValue()), transformarColor(), txtDescripcion.getText() );
		
	}
	
	@FXML
	public String transformarColor() {
		
		Color colorSeleccionado = txtColor.getValue();
		
		String colorHex = String.format("#%02X%02X%02X", (int)(colorSeleccionado.getRed() * 255), 
		                                (int)(colorSeleccionado.getGreen() * 255), 
		                                (int)(colorSeleccionado.getBlue() * 255));
		
		return colorHex;

	}

	
}
