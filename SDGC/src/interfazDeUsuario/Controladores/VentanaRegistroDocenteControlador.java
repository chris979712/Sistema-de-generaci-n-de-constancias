package interfazDeUsuario.Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import logicaDeNegocio.Clases.Profesor;

public class VentanaRegistroDocenteControlador implements Initializable {

    @FXML
    private Button btn_cancelar;

    @FXML
    private Button btn_guardar;

    @FXML
    private ComboBox<?> cmb_categoriaContratacion;

    @FXML
    private ComboBox<?> cmb_tipoContratacion;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    } 
    
    public void RegistrarDocente()
    {
        if(verificarCampos()){
            
        }else
        {
            
        }
        
    }
    
    public boolean verificarCampos()
    {
        limpiarCampos();
        boolean validacion=false;
        Profesor profesor=new Profesor();
        try
        {
            profesor.setNombre(txf_nombre.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_nombre.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }        
        try
        {
            profesor.setPrimerApellido(txf_primerApellido.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_primerApellido.setStyle("-fx-border-color: red; -fx-border-width: 2px;");            
        }
        try
        {
            profesor.setSegundoApellido(txf_segundoApellido.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_segundoApellido.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
        try
        {
            profesor.setNumeroDePersonal(txf_numeroPersonal.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_numeroPersonal.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
        }
        try
        {
            profesor.setCorreoInstitucional(txf_correo.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal)
        {
            txf_correo.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
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
    }
    
}
