package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class VentanaInicioSesionControlador implements Initializable {
    @FXML
    private Button btn_ingresar;

    @FXML
    private Hyperlink hpl_restablecerContrasenia;

    @FXML
    private PasswordField pwd_contrasenia;

    @FXML
    private TextField txf_usuario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
