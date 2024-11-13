package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Alumno;
import logicaDeNegocio.Clases.AuxiliarPeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;
import java.time.LocalDate;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.DAOImplementacion.DAOAlumnoImplementacion;
import logicaDeNegocio.DAOImplementacion.DAOTrabajoRecepcionalImplementacion;

public class VentanaRegistroConstanciaTrabajoRecepcionalControlador implements Initializable {
    private Stage escenario;
    
    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_guardar;

    @FXML
    private ComboBox<String> cmb_cargo;
    
    @FXML
    private ComboBox<String> cmb_carrera;


    @FXML
    private DatePicker dtp_fechaPresentacion;

    @FXML
    private TextField txf_busquedaDocente;

    @FXML
    private TextField txf_modalidad;

    @FXML
    private TextField txf_nombreAlumno;

    @FXML
    private TextField txf_resultadoObtenido;

    @FXML
    private TextField txf_titulo;

    @FXML
    private AnchorPane ventanaRegistroConstancia;
    
    @FXML
    private Label lbl_nombreDocente;
    
    private List<AuxiliarPeriodoEscolar> periodosEscolares;
    
    private int idPeriodoEscolar;
    private String nombrePeriodoEscolar;
    private int idProfesor;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llenarComboBoxCargo();
        llenarComboBoxCarrera();
        LocalDate fechaInicio = LocalDate.of(2022, 8, 1);  
        LocalDate fechaFin = LocalDate.of(2025, 1, 31);
        bloquearFechasFueraDeRango(dtp_fechaPresentacion, fechaInicio, fechaFin);
        periodosEscolares = new ArrayList<>();
        periodosEscolares.add(new AuxiliarPeriodoEscolar("AGO2022-ENE2023", LocalDate.of(2022, 8, 1), LocalDate.of(2023, 1, 31)));
        periodosEscolares.add(new AuxiliarPeriodoEscolar("FEB2023-JUL2023", LocalDate.of(2023, 2, 1), LocalDate.of(2023, 7, 31)));
        periodosEscolares.add(new AuxiliarPeriodoEscolar("AGO2023-ENE2024", LocalDate.of(2023, 8, 1), LocalDate.of(2024, 1, 31)));
        periodosEscolares.add(new AuxiliarPeriodoEscolar("FEB2024-JUL2024", LocalDate.of(2024, 2, 1), LocalDate.of(2024, 7, 31)));
        periodosEscolares.add(new AuxiliarPeriodoEscolar("AGO2024-ENE2025", LocalDate.of(2024, 8, 1), LocalDate.of(2025, 1, 31)));
        txf_busquedaDocente.setOnAction(event -> buscarProfesorPorCorreo());
        dtp_fechaPresentacion.setOnAction(event -> asignarPeriodoEscolar());        
    }    
    
    private void bloquearFechasFueraDeRango(DatePicker datePicker, LocalDate fechaInicio, LocalDate fechaFin) {
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker picker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate fecha, boolean empty) {
                        super.updateItem(fecha, empty);                        
                        if (fecha.isBefore(fechaInicio) || fecha.isAfter(fechaFin)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Color para indicar fechas bloqueadas
                        }
                    }
                };
            }
        });
    }
    
    
    
    private void asignarPeriodoEscolar() {
        LocalDate fechaSeleccionada = dtp_fechaPresentacion.getValue();
        if (fechaSeleccionada != null) {
            int contador=1;
            for (AuxiliarPeriodoEscolar periodo : periodosEscolares) {
                if (periodo.fechaDentroDelPeriodo(fechaSeleccionada)) {                    
                    idPeriodoEscolar=contador;   
                    nombrePeriodoEscolar=periodo.getNombre();
                }
                contador++;
            }            
        }
    }

    
    private void buscarProfesorPorCorreo() {
        String correoBuscado = txf_busquedaDocente.getText().trim();                
        if (validarCorreo()) {
            if(verificarExistenciaCorreo()){
                DAOProfesorImplementacion daoProfesor = new DAOProfesorImplementacion();
                Profesor profesorEncontrado = daoProfesor.ObtenerProfesorPorCorreo(correoBuscado);
                if (profesorEncontrado != null) {
                    lbl_nombreDocente.setText(profesorEncontrado.getNombreCompleto());
                    idProfesor=profesorEncontrado.getIdProfesor();
                } else {
                    Alertas.mostrarMensajeSinResultadosEncontrados("No se ha encontrado un docente con el correo especificado.");                    
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
    
    
    private void llenarComboBoxCargo() {
        cmb_cargo.getItems().addAll("Jurado", "Director", "Co-director");
    }
    
    private void llenarComboBoxCarrera(){
        cmb_carrera.getItems().addAll("Ingeniería de software","Ingeniería en ciencia de datos","Estadística","Ingeniería en sistemas y tecnologías de la información","Ingeniería de ciberseguridad e infraestructura de cómputo");
    }
    
    public void registrarNuevaConstancia(){
        if(validarCampos()){
            TrabajoRecepcional trabajoRecepcional=obtenerTrabajoRecepcional();
            DAOTrabajoRecepcionalImplementacion daoTrabajoRecepcional=new DAOTrabajoRecepcionalImplementacion();
            int resultadoRegistroTrabajoRecepcional=daoTrabajoRecepcional.RegistrarTrabajoRecepcional(trabajoRecepcional);
            if(resultadoRegistroTrabajoRecepcional==1){
                Alertas.mostrarRegistroConstanciaExitoso(); 
                salirAlMenuPrincipal();            
            }else{
                Alertas.mostrarMensajeErrorEnLaConexion(); 
            }
        }else{
            Alertas.mostrarMensajeDatosInvalidos();
        }
    }        
    
    private TrabajoRecepcional obtenerTrabajoRecepcional(){
        TrabajoRecepcional trabajoRecepcional=new TrabajoRecepcional();
        trabajoRecepcional.setRol(cmb_cargo.getSelectionModel().getSelectedItem());
        PeriodoEscolar periodoEscolar=new PeriodoEscolar();
        periodoEscolar.setIdPeriodoEscolar(idPeriodoEscolar);
        periodoEscolar.setPeriodoEscolar(nombrePeriodoEscolar);        
        trabajoRecepcional.setPeriodoEscolar(periodoEscolar);
        trabajoRecepcional.setTituloDeTrabajo(txf_titulo.getText());
        trabajoRecepcional.setModalidad(txf_modalidad.getText());
        trabajoRecepcional.setResultadoObtenido(txf_resultadoObtenido.getText());
        trabajoRecepcional.setCarrera(cmb_carrera.getSelectionModel().getSelectedItem());
        trabajoRecepcional.setFechaDePresentacion(dtp_fechaPresentacion.getValue().toString());
        Profesor profesor=new Profesor();
        profesor.setIdProfesor(idProfesor);
        trabajoRecepcional.setProfesor(profesor);
        Alumno alumno=obtenerAlumno();  
        trabajoRecepcional.setAlumno(alumno);
        return trabajoRecepcional;
    }
    
    private Alumno obtenerAlumno(){
        DAOAlumnoImplementacion daoAlumno=new DAOAlumnoImplementacion();
        int resultadoVerificacion=daoAlumno.VerificarExistenciaDeAlumno(txf_nombreAlumno.getText());
        if(resultadoVerificacion==0){
            Alumno alumno=new Alumno();
            alumno.setNombreCompleto(txf_nombreAlumno.getText());
            daoAlumno.RegistrarAlumno(alumno);            
        }
        return daoAlumno.ObtenerAlumnoPorNombreCompleto(txf_nombreAlumno.getText());                
    }
    
    
    
    
    private boolean validarCampos(){
        limpiarCampos();
        boolean validacion=true;
        String nombreDocente=lbl_nombreDocente.getText();
        if(nombreDocente.isEmpty()){
            validacion=false;
            txf_busquedaDocente.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
        Alumno alumno=new Alumno();
        TrabajoRecepcional trabajoRecepcional=new TrabajoRecepcional();
        try{
            trabajoRecepcional.setTituloDeTrabajo(txf_titulo.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal){            
            txf_titulo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }
        try{
            trabajoRecepcional.setModalidad(txf_modalidad.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal){            
            txf_modalidad.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;            
        }
        try{
            trabajoRecepcional.setResultadoObtenido(txf_resultadoObtenido.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal){            
            txf_resultadoObtenido.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;            
        }
        try{    
            alumno.setNombreCompleto(txf_nombreAlumno.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal){
            txf_nombreAlumno.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;                        
        }          
        if (cmb_cargo.getSelectionModel().getSelectedItem() == null) {
            cmb_cargo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion = false;
        }        
        if (cmb_carrera.getSelectionModel().getSelectedItem() == null) {
            cmb_carrera.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion = false;
        }                
        if(dtp_fechaPresentacion.getValue()==null){
            dtp_fechaPresentacion.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;            
        }        
        return validacion;                
    }
    
    private void limpiarCampos(){
        txf_busquedaDocente.setStyle("");
        txf_titulo.setStyle("");
        txf_modalidad.setStyle("");
        txf_resultadoObtenido.setStyle("");
        txf_nombreAlumno.setStyle("");
        cmb_cargo.setStyle("");
        cmb_carrera.setStyle("");
        dtp_fechaPresentacion.setStyle("");
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
        escenario = (Stage) ventanaRegistroConstancia.getScene().getWindow();
        escenario.close();        
    }
        
    
}
