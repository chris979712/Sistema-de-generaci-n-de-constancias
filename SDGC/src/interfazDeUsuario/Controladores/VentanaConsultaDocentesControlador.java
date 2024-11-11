package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VentanaConsultaDocentesControlador implements Initializable {

    @FXML
    private Button btn_regresar;

    @FXML
    private TableColumn<?, ?> column_correo;

    @FXML
    private TableColumn<?, ?> column_nombre;

    @FXML
    private TableView<?> table_docente;

    @FXML
    private TextField txf_busquedaDocente;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
