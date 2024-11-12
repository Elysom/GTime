package GTime;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class controladorPrincipal implements Initializable{
	static String nombreUsuario;
	@FXML
	Label lblNUsuario;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblNUsuario.setText(nombreUsuario);
		
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}


	
	
}
