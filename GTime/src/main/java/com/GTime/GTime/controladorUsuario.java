package com.GTime.GTime;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class controladorUsuario {

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnCrearCurso;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button atrasMes;

    @FXML
    private Button alanteMes;

    @FXML
    private Text year;

    @FXML
    private Text month;

    @FXML
    private ListView<String> taskList;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Label lblNUsuario; 

    // Método para manejar el texto ingresado en el campo de búsqueda
    
}