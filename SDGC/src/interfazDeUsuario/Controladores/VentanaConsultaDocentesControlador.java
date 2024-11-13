package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;

public class VentanaConsultaDocentesControlador implements Initializable {
    private Stage escenario;
    
    @FXML
    private Button btn_regresar;

    @FXML
    private TableColumn column_correo;

    @FXML
    private TableColumn column_nombre;

    @FXML
    private TableView<Profesor> table_docente;

    @FXML
    private TextField txf_busquedaDocente;
    
    @FXML
    private AnchorPane ventanaConsultaDocentes;
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        column_correo.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));
        column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombreCompleto"));
        
        cargarProfesores(obtenerProfesores());
        
        txf_busquedaDocente.setOnAction(event -> buscarProfesorPorCorreo());
    }

    private List<Profesor> obtenerProfesores() {        
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        return daoProfesor.ObtenerProfesores();                     
    }

    private void cargarProfesores(List<Profesor> profesores) {
        table_docente.getItems().clear(); 
        if (profesores.isEmpty()) {
            Alertas.mostrarMensajeSinResultadosEncontrados("No se han encontrado docentes registrados");
        } else {
            table_docente.getItems().addAll(profesores);
        }
    }

    private void buscarProfesorPorCorreo() {
        String correoBuscado = txf_busquedaDocente.getText().trim();        
        if(correoBuscado.isEmpty()){
            cargarProfesores(obtenerProfesores());            
        }
        if (validarCorreo()) {
            if(verificarExistenciaCorreo()){
                DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
                Profesor profesorEncontrado = daoProfesor.ObtenerProfesorPorCorreo(correoBuscado);
                if (profesorEncontrado != null) {
                    cargarProfesores(List.of(profesorEncontrado)); 
                } else {
                    Alertas.mostrarMensajeSinResultadosEncontrados("No se ha encontrado un docente con el correo especificado.");
                    cargarProfesores(obtenerProfesores());
                }                
            }                        
        }        
    }
    
    private boolean validarCorreo(){
        boolean validacion=true;
        Profesor profesor=new Profesor();
        try{
            profesor.setCorreoInstitucional(txf_busquedaDocente.getText().trim());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal){
            validacion=false;
        }
        return validacion;                
    }
    
    private boolean verificarExistenciaCorreo(){
        boolean verificacion=false;
        DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
        Profesor profesor=new Profesor();
        profesor.setCorreoInstitucional(txf_busquedaDocente.getText().trim());
        if(daoProfesor.VerificarDuplicidadDeProfesor(profesor)==1){
            verificacion=true;
        }
        return verificacion;                
    }
    
    public void salirAlMenuPrincipal(){
        String rutaVentanaFXML = null;
        try{
            rutaVentanaFXML = "/interfazDeUsuario/VentanaMenuPersonalAdministrativo.fxml";
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            cerrarVentana();
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();                
        }
    }
    
    private void cerrarVentana(){
        escenario = (Stage) ventanaConsultaDocentes.getScene().getWindow();
        escenario.close();        
    }
    
}
