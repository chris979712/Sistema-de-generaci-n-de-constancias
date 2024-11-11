package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class VentanaSolicitudConstanciaControlador implements Initializable {

    @FXML
    private Button btn_constanciaImparticion;

    @FXML
    private Button btn_constanciaJurado;

    @FXML
    private Button btn_constanciaPladea;

    @FXML
    private Button btn_constanciaProyecto;

    @FXML
    private Button btn_regresar;

    @FXML
    private ComboBox<?> cmb_periodoEscolar;

    @FXML
    private Label lbl_nombreProfesor;

    @FXML
    private Label lbl_numeroPersonal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
