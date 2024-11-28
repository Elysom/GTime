package com.GTime.GTime;

import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import utilidades.SCRUDusuarios;

public class controladorFormularioRutina implements Initializable {


    @FXML
    private TextField txtRutinas; // Campo de texto para el nombre de la rutina.

    @FXML
    private Spinner<Integer> txtHoras; // Spinner para seleccionar las horas.

    @FXML
    private Spinner<Integer> txtMinutos; // Spinner para seleccionar los minutos.

    @FXML
    private ColorPicker txtColor; // Selector de color.

    @FXML
    private Button btnCrear; // Botón para crear la rutina.

    @FXML
    private Label txtValidacion; // Etiqueta para mostrar mensajes de validación.

    @FXML
    private TextArea txtDescripcion; // Área de texto para la descripción.

    @FXML
    private CheckBox chbLunes; // Checkbox para Lunes.

    @FXML
    private CheckBox chbMartes; // Checkbox para Martes.

    @FXML
    private CheckBox chbMiercoles; // Checkbox para Miércoles.

    @FXML
    private CheckBox chbJueves; // Checkbox para Jueves.

    @FXML
    private CheckBox chbViernes; // Checkbox para Viernes.

    @FXML
    private CheckBox chbSabado; // Checkbox para Sábado.

    @FXML
    private CheckBox chbDomingo; // Checkbox para Domingo.

    @FXML
    private HBox hboxSpinners; // Contenedor para los Spinners de hora y minutos.

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
		
	}
    
	// Depende del dia de la Semana
	public void repeticionRutinas(ActionEvent event) {
		
		if (chbLunes.isSelected()) {
			
			agregarRutinas("L");
			
		}
		
		if (chbMartes.isSelected()) {
			
			agregarRutinas("M");
			
		}
		
		if (chbMiercoles.isSelected()) {
			
			agregarRutinas("X");
			
		}
		
		if (chbJueves.isSelected()) {
			
			agregarRutinas("J");
			
		}
		
		if (chbViernes.isSelected()) {
			
			agregarRutinas("V");
			
		}
		
		if (chbSabado.isSelected()) {
			
			agregarRutinas("S");
			
		}
		
		if (chbDomingo.isSelected()) {
			
			agregarRutinas("D");
			
		}
	}
	
	public void agregarRutinas(String diaSemana) {
		
		SCRUDusuarios.creacionRutina(txtRutinas.getText(), diaSemana,  LocalTime.of(txtHoras.getValue(), txtMinutos.getValue()), transformarColor(), txtDescripcion.getText(), null, null);
		
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
