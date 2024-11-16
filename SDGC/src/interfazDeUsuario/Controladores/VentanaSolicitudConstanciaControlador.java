package interfazDeUsuario.Controladores;

import com.itextpdf.text.Document;
import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;
import logicaDeNegocio.ConstanciaImplementacion.ConstanciaImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOPeriodoEscolarImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOTrabajoRecepcionalImplementacion;

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
    
    @FXML
    private Pane pane_solicitarConstanciaJurado;
    
    @FXML
    private ComboBox<String> cmb_tipoDeParticipacion;
   
    @FXML
    private Button btn_solicitar;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarCampos();
        cargarComboBoxPeriodoEscolar();
        CargarComboboxTipoDeParticipacion();
        pane_solicitarConstanciaJurado.setVisible(false);
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
    
    public void CargarComboboxTipoDeParticipacion(){
        List<String> tipoDeParticipaciones = new ArrayList<String>();
        tipoDeParticipaciones.add("Jurado");
        tipoDeParticipaciones.add("Director");
        tipoDeParticipaciones.add("Co-director");
        ObservableList<String> periodosEscolaresObservables = FXCollections.observableArrayList();
        for(String tipoDeParticipacion : tipoDeParticipaciones){
            periodosEscolaresObservables.add(tipoDeParticipacion);
        }
        cmb_tipoDeParticipacion.setItems(periodosEscolaresObservables);
    }
    
    public void solicitarConstanciaDeJurado(){
        pane_solicitarConstanciaJurado.setVisible(true);
    }
    
    public void SolicitarConstanciaDeJurado(){
        String tipoDeParticipacion = cmb_tipoDeParticipacion.getSelectionModel().getSelectedItem();
        String periodoEscolar = cmb_periodoEscolar.getSelectionModel().getSelectedItem();
        int indexSeleccionado = cmb_periodoEscolar.getSelectionModel().getSelectedIndex() + 1;
        if(periodoEscolar == null || tipoDeParticipacion==null){
            Alertas.mostrarMensajeOpcionesSinSeleccionar("No se ha seleccionado un periodo escolar o tipo de participación. Por favor seleccione una opcion");
        }else{
            DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
            DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
            Profesor profesor = daoProfesor.ObtenerProfesorPorCorreo(CuentaSingleton.getInstancia().getNombreUsuario());
            PeriodoEscolar periodo = new PeriodoEscolar();
            periodo.setPeriodoEscolar(periodoEscolar);
            periodo.setIdPeriodoEscolar(indexSeleccionado);
            List<TrabajoRecepcional> trabajosRecepcionales = dao.ObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolar(profesor, periodo);
            switch(tipoDeParticipacion){
                case "Director":
                    SolicitarConstanciaDeJuradoDirector(trabajosRecepcionales,profesor);
                    break;
                case "Jurado":
                    SolicitarConstanciaDeJuradoJurado(trabajosRecepcionales,profesor);
                    break;
                case "Co-director":
                    SolicitarConstanciaDeJuradoCoDirector(trabajosRecepcionales,profesor);
                    break;
                default:
                    break;
            }
        }
    }
    
    public void SolicitarConstanciaDeJuradoDirector(List<TrabajoRecepcional> trabajos, Profesor profesor){
        Document documetoInforme = null;
        if(profesor.getIdProfesor()!=-1){
            List<TrabajoRecepcional> trabajosDelTipoEspecificado = ObtenerTrabajosRecepcionalesPorTipo(trabajos,"Director");
            if(trabajosDelTipoEspecificado.size()==0){
                Alertas.mostrarMensajeSinTrabajosRecepcionalesAsociados();
            }else{
                ConstanciaImplementacion generacionDeConstancia = new ConstanciaImplementacion();
                documetoInforme = generacionDeConstancia.crearInformeDeColaboracion(profesor, trabajos);
                if(documetoInforme==null){
                Alertas.mostrarMensajeNoSePudoCrearLaConstancia();
                }else{
                    Alertas.mostrarMensajeConstanciaGenerada();
                }
            }
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
 
    }
    
    public void SolicitarConstanciaDeJuradoJurado(List<TrabajoRecepcional> trabajos, Profesor profesor){
        Document documetoInforme = null;
        if(profesor.getIdProfesor()!=-1){
            List<TrabajoRecepcional> trabajosDelTipoEspecificado = ObtenerTrabajosRecepcionalesPorTipo(trabajos,"Jurado");
            if(trabajosDelTipoEspecificado.size()==0){
                Alertas.mostrarMensajeSinTrabajosRecepcionalesAsociados();
            }else{
                ConstanciaImplementacion generacionDeConstancia = new ConstanciaImplementacion();
                documetoInforme = generacionDeConstancia.crearInformeDeColaboracion(profesor, trabajos);
                if(documetoInforme==null){
                Alertas.mostrarMensajeNoSePudoCrearLaConstancia();
                }else{
                    Alertas.mostrarMensajeConstanciaGenerada();
                }
            }
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    public void SolicitarConstanciaDeJuradoCoDirector(List<TrabajoRecepcional> trabajos, Profesor profesor){
        Document documetoInforme = null;
        if(profesor.getIdProfesor()!=-1){
            List<TrabajoRecepcional> trabajosDelTipoEspecificado = ObtenerTrabajosRecepcionalesPorTipo(trabajos,"Co-director");
            if(trabajosDelTipoEspecificado.size()==0){
                Alertas.mostrarMensajeSinTrabajosRecepcionalesAsociados();
            }else{
                ConstanciaImplementacion generacionDeConstancia = new ConstanciaImplementacion();
                documetoInforme = generacionDeConstancia.crearInformeDeColaboracion(profesor, trabajos);
                if(documetoInforme==null){
                Alertas.mostrarMensajeNoSePudoCrearLaConstancia();
                }else{
                    Alertas.mostrarMensajeConstanciaGenerada();
                }
            }
        }else{
            Alertas.mostrarMensajeErrorEnLaConexion();
        }
    }
    
    public List<TrabajoRecepcional> ObtenerTrabajosRecepcionalesPorTipo(List<TrabajoRecepcional> trabajosObtenidos, String tipo){
        List<TrabajoRecepcional> trabajosDelTipoEspecificado = new ArrayList<TrabajoRecepcional>();
        for(int indexTrabajosObtenidos = 0;indexTrabajosObtenidos<trabajosObtenidos.size();indexTrabajosObtenidos++){
            if(trabajosObtenidos.get(indexTrabajosObtenidos).getRol().equals(tipo)){
                TrabajoRecepcional trabajo = trabajosObtenidos.get(indexTrabajosObtenidos);
                trabajosDelTipoEspecificado.add(trabajo);
            }
        }
        return trabajosDelTipoEspecificado;
    }
    
    public void regresarAlInicioDeSesion(){
        boolean resultadoEleccion = Alertas.mostrarConfirmacionDeAccion("¿Desea regresar al inicio de sesión?");
         if(resultadoEleccion){
             salirAlMenuPrincipal();
         }
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
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();                
        }
        cerrarVentana();
    }

    private void cerrarVentana(){
        escenario = (Stage) ventanaSolicitudConstancia.getScene().getWindow();
        escenario.close();        
    }    
}