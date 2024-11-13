package interfazDeUsuario.Controladores;

import interfazDeUsuario.Alertas.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.DAOImplementacion.DAOCuentaImplementacion;

public class VentanaInicioSesionControlador implements Initializable {
    private Stage escenario;
    
    @FXML
    private Button btn_ingresar;

    @FXML
    private Hyperlink hpl_restablecerContrasenia;

    @FXML
    private PasswordField pwd_contrasenia;

    @FXML
    private TextField txf_usuario;
    
    @FXML
    private AnchorPane ventanaInicioSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void ingresar(){
        if(verificarCampos())
        {
            DAOCuentaImplementacion daoCuenta=new DAOCuentaImplementacion();
            Cuenta cuentaAIngresar=obtenerCuenta();
            Cuenta logger=new Cuenta();
            logger.setTipoDeUsuario("Logger");
            int resultadoValidacion=daoCuenta.ValidarCredenciales(cuentaAIngresar, logger);
            switch(resultadoValidacion)
            {
                case 0:
                    Alertas.mostrarMensajeUsuarioNoEncontrado();
                    break;
                case 1:
                    obtenerCuentaSingleton(cuentaAIngresar,logger);  
                    desplegarVentanaCorrespondiente();
                    break;
                case -1:
                    Alertas.mostrarMensajeErrorEnLaConexion();
                    break;
            }                                                            
        }else
        {
            Alertas.mostrarMensajeDatosInvalidos();
        }
            
    }
    
    private void obtenerCuentaSingleton(Cuenta cuenta,Cuenta logger){    
        DAOCuentaImplementacion daoCuenta=new DAOCuentaImplementacion();
        String tipoUsuario=daoCuenta.ObtenerTipoDeUsuario(cuenta, logger);
        cuenta.setTipoDeUsuario(tipoUsuario);
        CuentaSingleton.getInstancia(cuenta);               
    }
    
    private Cuenta obtenerCuenta()
    {
        Cuenta cuenta=new Cuenta();
        cuenta.setNombreUsuario(txf_usuario.getText());
        cuenta.setContrasenia(pwd_contrasenia.getText());
        return cuenta;
    }
    
    private boolean verificarCampos()
    {
        limpiarCampos();
        boolean validacion=true;
        Cuenta cuenta=new Cuenta();
        try{
            cuenta.setNombreUsuario(txf_usuario.getText());            
        }catch(IllegalArgumentException excepcionArgumentoIlegal){
            txf_usuario.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            validacion=false;
        }
        try{
            cuenta.setContrasenia(pwd_contrasenia.getText());
        }catch(IllegalArgumentException excepcionArgumentoIlegal){
            pwd_contrasenia.setStyle("-fx-border-color: red; -fx-border-width: 2px;");            
            validacion=false;
        }
        return validacion;
    }
    
    private void limpiarCampos(){
        txf_usuario.setStyle("");
        pwd_contrasenia.setStyle("");        
    }
    
    private void desplegarVentanaCorrespondiente(){
        String tipoUsuario=CuentaSingleton.getInstancia().getTipoDeUsuario();
        String rutaVentanaFXML=null;
        try{
            if(tipoUsuario.equals("Administrativo")){
                rutaVentanaFXML = "/interfazDeUsuario/VentanaMenuPersonalAdministrativo.fxml";
            }else {
                rutaVentanaFXML = "/interfazDeUsuario/VentanaSolicitudConstancia.fxml";
            }
            Parent root=FXMLLoader.load(getClass().getResource(rutaVentanaFXML));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();            
        }
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        escenario = (Stage) ventanaInicioSesion.getScene().getWindow();
        escenario.close();        
    }
    
    
}
