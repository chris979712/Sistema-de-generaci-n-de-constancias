package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.DAOImplementacion.DAOPeriodoEscolarImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;

public class VentanaSolicitudConstanciaControlador implements Initializable {
    private Stage escenario;
    
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
    private ComboBox<String> cmb_periodoEscolar;

    @FXML
    private Label lbl_nombreProfesor;

    @FXML
    private Label lbl_numeroPersonal;
    
    @FXML
    private AnchorPane ventanaSolicitudConstancia;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCampos();
        cargarComboBoxPeriodoEscolar();
    }    
    
    public void llenarCampos(){
        DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
        String correo=CuentaSingleton.getInstancia().getNombreUsuario();
        Profesor profesor=daoProfesor.ObtenerProfesorPorCorreo(correo);
        lbl_nombreProfesor.setText(profesor.getNombreCompleto());
        lbl_numeroPersonal.setText(profesor.getNumeroDePersonal());
    }
    
    public void cargarComboBoxPeriodoEscolar(){
        DAOPeriodoEscolarImplementacion daoPeriodoEscolar=new DAOPeriodoEscolarImplementacion();
        List<PeriodoEscolar> periodosEscolares=daoPeriodoEscolar.ObtenerPeriodosEscolares();
        ObservableList<String> periodosEscolaresObservables = FXCollections.observableArrayList();
        for(PeriodoEscolar periodoEscolar : periodosEscolares){
            periodosEscolaresObservables.add(periodoEscolar.getPeriodoEscolar());
        }
        cmb_periodoEscolar.setItems(periodosEscolaresObservables);
    }
    
    private void salirAlMenuPrincipal(){
        String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/VentanaInicioSesion.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            CuentaSingleton.resetSingleton();
            cerrarVentana();
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();                
        }
    }
    
    private void cerrarVentana(){
        escenario = (Stage) ventanaSolicitudConstancia.getScene().getWindow();
        escenario.close();        
    }
    
}
