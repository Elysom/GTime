package com.GTime.GTime;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import utilidades.SCRUDusuarios;

public class controladorFormularioTarea implements Initializable {

	@FXML Button btnCrear; 
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
	private CheckBox chbRutinas;
	
    // Referencia al controlador de usuario
    private controladorUsuario controladorUsuario;

    // Método para establecer la referencia al controladorUsuario
    public void setControladorUsuario(controladorUsuario controlador) {
        this.controladorUsuario = controlador;
    }
    
    
	
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
	
	    actualizarLista();
	    
		// Cerrar la ventana (escena actual)
	    cerrarVentana(event);
	    
	}
	
	public void actualizarLista() {
		
		
		
		List<String> AmbasOpciones = controladorUsuario.rellenarYordenarAmbasListas();
		
		controladorUsuario.chbTareas.setSelected(true);
		
		controladorUsuario.chbPlanAcademico.setSelected(true);
		
	    controladorUsuario.taskList.getItems().setAll(AmbasOpciones);
	    
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
	        // Crear un cargador de FXML para tener acceso al controlador
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/formularioRutinas.fxml"));

	        // Cargar el nuevo contenido
	        Parent newRoot = loader.load();

	        // Obtener el controlador asociado al nuevo FXML
	        controladorFormularioRutina controladorRutina = loader.getController();

	        // (Opcional) Si necesitas pasar datos al nuevo controlador, hazlo aquí
	        controladorRutina.setControladorUsuario(this.controladorUsuario);

	        // Obtener la escena actual y reemplazar su raíz con el nuevo contenido
	        Scene scene = btnCrear.getScene();
	        scene.setRoot(newRoot);

	        // Ajustar el tamaño de la ventana al contenido
	        Stage stage = (Stage) scene.getWindow();
	        
	        Image icon = new Image(getClass().getResourceAsStream("/vista/resources/logo.png"));
	        stage.getIcons().add(icon);
	        
	        stage.sizeToScene();
	    } catch (IOException e) {
	        e.printStackTrace();
	        System.err.println("Error al cargar el formulario de rutinas: " + e.getMessage());
	    }
	}

	

public void cerrarVentana(ActionEvent event) {
    // Obtener el nodo de la escena (en este caso el botón que disparó el evento)
    Node source = (Node) event.getSource();
    // Obtener el Stage (ventana) de la escena
    Stage stage = (Stage) source.getScene().getWindow();
    // Cerrar la ventana
    stage.close();
}



	
}
