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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.CategoriaDeContratacion;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TipoDeContratacion;
import logicaDeNegocio.DAOImplementacion.DAOCategoriaDeContratacionImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOTipoDeContratacionImplementacion;

public class VentanaRegistroDocenteControlador implements Initializable {
    private Stage escenario;    
    
    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_guardar;

    @FXML
    private ComboBox<String> cmb_categoriaContratacion;

    @FXML
    private ComboBox<String> cmb_tipoContratacion;

    @FXML
    private TextField txf_correo;

    @FXML
    private TextField txf_nombre;

    @FXML
    private TextField txf_numeroPersonal;

    @FXML
    private TextField txf_primerApellido;

    @FXML
    private TextField txf_segundoApellido;
    
    @FXML
    private AnchorPane ventanaRegistroDocente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxTipoContratacion();
        llenarComboBoxCategoriaContratacion();
    } 
    
    public void registrarDocente()
    {
        if(verificarCampos()){
            Profesor profesor=obtenerProfesor();
            DAOProfesorImplementacion daoProfesor=new DAOProfesorImplementacion();
            //Generador de contraseña al azar siguiendo politica y enviar correo
            int resultadoProfesorDuplicado=daoProfesor.VerificarDuplicidadDeProfesor(profesor);
            switch(resultadoProfesorDuplicado){
                case 0:
                    int resultadoRegistro=daoProfesor.RegistrarProfesor(profesor, "Contrasenia123*");
                    if(resultadoRegistro==1){
                        Alertas.mostrarRegistroDocenteExitoso();                      
                    }else{
                        Alertas.mostrarMensajeErrorEnLaConexion();                
                    }
                    break;
                case 1:
                    Alertas.mostrarMensajeDocenteConCuenta();
                    break;
                case -1:
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    break;                                    
            }                                                                                                                                 
            salirAlMenuPrincipal();            
        }else
        {
            Alertas.mostrarMensajeDatosInvalidos();
        }        
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
        escenario = (Stage) ventanaRegistroDocente.getScene().getWindow();
        escenario.close();        
    }
        
    private Profesor obtenerProfesor(){
        Profesor profesor=new Profesor();
        profesor.setNombre(txf_nombre.getText());
        profesor.setPrimerApellido(txf_primerApellido.getText());
        profesor.setSegundoApellido(txf_segundoApellido.getText());
        profesor.setNumeroDePersonal(txf_numeroPersonal.getText());
        profesor.setCorreoInstitucional(txf_correo.getText());        
        profesor.setTipoDeContratacion_idTipoDeContratacion(cmb_tipoContratacion.getSelectionModel().getSelectedIndex()+1);
        profesor.setCategoriaDeContracion_idCategoriaDeContratacion(cmb_categoriaContratacion.getSelectionModel().getSelectedIndex()+1);
        return profesor;
    }        
    
    public boolean verificarCampos()
    {
        limpiarCampos();
        boolean validacion=true;
        Profesor profesor=new Profesor();        
        try
        {
            profesor.setNombre(txf_nombre.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_nombre.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }        
        try
        {
            profesor.setPrimerApellido(txf_primerApellido.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_primerApellido.setStyle("-fx-border-color: red; -fx-border-width: 2px;");            
            validacion=false;
        }
        try
        {
            profesor.setSegundoApellido(txf_segundoApellido.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_segundoApellido.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }
        try
        {
            profesor.setNumeroDePersonal(txf_numeroPersonal.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_numeroPersonal.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }
        try
        {
            profesor.setCorreoInstitucional(txf_correo.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_correo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }            
        if (cmb_tipoContratacion.getSelectionModel().getSelectedItem() == null) {
            cmb_tipoContratacion.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion = false;
        }
        if (cmb_categoriaContratacion.getSelectionModel().getSelectedItem() == null) {
            cmb_categoriaContratacion.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion = false;
        }        
        return validacion;
    }
    
    private void limpiarCampos()
    {
        txf_nombre.setStyle(""); 
        txf_primerApellido.setStyle(""); 
        txf_segundoApellido.setStyle(""); 
        txf_numeroPersonal.setStyle(""); 
        txf_correo.setStyle(""); 
        cmb_tipoContratacion.setStyle("");
        cmb_categoriaContratacion.setStyle("");                
    }
    
    public void llenarComboBoxTipoContratacion(){
        DAOTipoDeContratacionImplementacion daoTipoContratacion=new DAOTipoDeContratacionImplementacion();
        List<TipoDeContratacion> tiposContratacion=daoTipoContratacion.ObtenerTiposDeContratacion();
        ObservableList<String> tiposContratacionObservable = FXCollections.observableArrayList();
        for(TipoDeContratacion tipoContratacion : tiposContratacion){
            tiposContratacionObservable.add(tipoContratacion.getTipo());            
        }
        cmb_tipoContratacion.setItems(tiposContratacionObservable);
    }
    
    public void llenarComboBoxCategoriaContratacion(){
        DAOCategoriaDeContratacionImplementacion daoCategoriaContratacion=new DAOCategoriaDeContratacionImplementacion();
        List<CategoriaDeContratacion> categoriasContratacion=daoCategoriaContratacion.ObtenerCategoriasDeContratacion();
        ObservableList<String> categoriasContratacionObservable = FXCollections.observableArrayList();
        for(CategoriaDeContratacion categoriaContratacion : categoriasContratacion){
            categoriasContratacionObservable.add(categoriaContratacion.getTipo());
        }
        cmb_categoriaContratacion.setItems(categoriasContratacionObservable);                       
    } 
    
}
