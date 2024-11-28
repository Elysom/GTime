package com.GTime.GTime;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
	
	@FXML
	private CheckBox chbTareas;
	
	@FXML 
	private CheckBox chbRutinas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		// Confiurar las horas del spinner tipo de 00 a 23
		
		SpinnerValueFactory<Integer> horas = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23);
		txtHoras.setValueFactory(horas);
		
		// Lo mismo con los minutos
		
		SpinnerValueFactory<Integer> minutos = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59);
		txtMinutos.setValueFactory(minutos);
		
		chbTareas.setSelected(true);
		
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
	
	@FXML
	public void abrirFormularioRutina(ActionEvent event) {
		
		try {
			Main.setRoot("/vista/formularioRutinas");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	
}
