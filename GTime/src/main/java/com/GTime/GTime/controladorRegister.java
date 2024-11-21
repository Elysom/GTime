package com.GTime.GTime;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import utilidades.SCRUDusuarios;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import utilidades.SCRUDusuarios;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.RegexValidator;


import utilidades.SCRUDusuarios;


public class controladorRegister implements Initializable {
	@FXML
	private AnchorPane pane;
	@FXML
	Button btnRegister;
	@FXML
	TextField txtNombre;
	@FXML
	TextField txtMail;
	@FXML
	TextField txtNombreReal;
	@FXML
	TextField txtApellidos;
	@FXML
	PasswordField txtContrasenia;
	@FXML
	PasswordField txtConfContrasenia;
	@FXML
	Label txtValidation;
	@FXML
	private ComboBox<String> txtCurso;
	private TextField txtContraseniaVisible = new TextField();//Nuevo TextField para cambiarlo por el de la contraseña para que se vea
	private TextField txtConfContraseniaVisible = new TextField();
	private boolean mostrandoConfContrasenia=false;
	private boolean mostrandoContrasenia=false;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		txtCurso.setItems(FXCollections.observableArrayList("1.º SMR","2.º SMR","1.º DAM","2.º DAM"));
	    // Configura el campo de texto visible
		//Poner los valores de posicion de la contraseña
	    txtContraseniaVisible.setLayoutX(txtContrasenia.getLayoutX());
	    txtContraseniaVisible.setLayoutY(txtContrasenia.getLayoutY());
	    txtContraseniaVisible.setPrefWidth(txtContrasenia.getPrefWidth());
	    txtContraseniaVisible.setVisible(false); // Oculto inicialmente
	    txtContraseniaVisible.setManaged(false); // No participa en el layout
	    //Lo mismo para confContrasenia
	    txtConfContraseniaVisible.setLayoutX(txtConfContrasenia.getLayoutX());
	    txtConfContraseniaVisible.setLayoutY(txtConfContrasenia.getLayoutY());
	    txtConfContraseniaVisible.setPrefWidth(txtConfContrasenia.getPrefWidth());
	    txtConfContraseniaVisible.setVisible(false);
	    txtConfContraseniaVisible.setManaged(false);
	    txtContrasenia.textProperty().bindBidirectional(txtContraseniaVisible.textProperty());//Conseguir el texto de la contraseña
	    txtConfContrasenia.textProperty().bindBidirectional(txtConfContraseniaVisible.textProperty());
	    pane.getChildren().add(txtContraseniaVisible);//Añadir el textfield creado a el pane
		pane.getChildren().add(txtConfContraseniaVisible);

		}
	
	@FXML
	private void registrer(ActionEvent event) {
		// Iniciamos las variables
		
	    String nombreUsuario = txtNombre.getText();
	    String apellidos = txtApellidos.getText();
	    String mail = txtMail.getText();
	    String nombreReal = txtNombreReal.getText();
	    String contrasenia = txtContrasenia.getText();
	    String confContrasenia = txtConfContrasenia.getText();
	    String curso = txtCurso.getValue();
	    
	    // Verificamos si el usuario existe
	    
	    String usu_compatible = SCRUDusuarios.verificarNombreUsusario(nombreUsuario);
	    
	    if (usu_compatible.equals("Nombre Usuario Disponible")) {
	    	
	    	// Indicar validacion
		    
			String resultado = SCRUDusuarios.validacionFormulario(nombreUsuario, apellidos, mail, nombreReal, contrasenia, confContrasenia, curso);
			
			System.out.println(resultado);
			
			txtValidation.setStyle("-fx-text-fill: green;");
			
			txtValidation.setText(resultado);
			
			if (txtValidation.getText().equals("¡Registro exitoso!")) {
				
				SCRUDusuarios.registerSCRUD(nombreUsuario, apellidos, mail, nombreReal, confContrasenia, curso);
				
				SCRUDusuarios.agregarEsquemaDatos(nombreUsuario,apellidos,mail,nombreReal,contrasenia,confContrasenia,curso);
				
			} else {
				
				txtValidation.setStyle("-fx-text-fill: red;");
				
				System.out.println(resultado);
			}
		} else {
			txtValidation.setText("El nombre de usuario " +nombreUsuario+ " no esta disponible, igrese otro");
		}
		
		
	}

	//SEPARAR LOS METODOS DE LA VALIDACION POR CAMPOS(UN METODO PARA EL NOMBRE, OTRO PARA EL APELLIDO ETC)
	


	private void volverLogin() throws IOException {
		
		
		Main.setRoot("/vista/loggin");
	}
	//SEPARAR LOS METODOS DE LA VALIDACION POR CAMPOS(UN METODO PARA EL NOMBRE, OTRO PARA EL APELLIDO ETC)
	@FXML
	private void mostrarContrasenia() {
	    if (mostrandoContrasenia) {
	        txtContrasenia.setVisible(true);
	        txtContrasenia.setManaged(true);
	        txtContraseniaVisible.setVisible(false);
	        txtContraseniaVisible.setManaged(false);
	    } else {
	        txtContrasenia.setVisible(false);
	        txtContrasenia.setManaged(false);
	        txtContraseniaVisible.setVisible(true);
	        txtContraseniaVisible.setManaged(true);
	    }
	    mostrandoContrasenia = !mostrandoContrasenia;
	}
	@FXML
	private void mostrarConfContrasenia() {
	    if (mostrandoConfContrasenia) {
	        txtConfContrasenia.setVisible(true);
	        txtConfContrasenia.setManaged(true);
	        txtConfContraseniaVisible.setVisible(false);
	        txtConfContraseniaVisible.setManaged(false);
	    } else {
	        txtConfContrasenia.setVisible(false);
	        txtConfContrasenia.setManaged(false);
	        txtConfContraseniaVisible.setVisible(true);
	        txtConfContraseniaVisible.setManaged(true);
	    }
	    mostrandoConfContrasenia = !mostrandoConfContrasenia;
	}




}
