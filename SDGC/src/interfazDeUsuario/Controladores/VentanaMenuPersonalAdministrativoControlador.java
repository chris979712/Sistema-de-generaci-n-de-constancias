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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logicaDeNegocio.Clases.CuentaSingleton;

public class VentanaMenuPersonalAdministrativoControlador implements Initializable {    
    private Stage escenario;    
    @FXML
    private Button btn_registrarConstanciaTrabajo;

    @FXML
    private Button btn_salir;

    @FXML
    private Button btn_consultarDocente;

    @FXML
    private Button btn_registrarDocente;
    
    @FXML
    private AnchorPane ventanaMenuPersonalAdministrativo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btn_registrarDocente.setOnAction(Event ->{ 
            registrarDocente();            
        });
        btn_consultarDocente.setOnAction(Event ->{ 
            consultarDocentes();           
        });
        btn_registrarConstanciaTrabajo.setOnAction(Event ->{ 
            registrarConstanciaTrabajoRecepcional();
        });
        btn_salir.setOnAction(Event ->{ 
            regresarAlInicioDeSesion();
        });                
    }    
    
    public void registrarDocente(){
        String ruta="/interfazDeUsuario/VentanaRegistroDocente.fxml";
        desplegarVentana(ruta);
    }
    
    public void consultarDocentes(){
        String ruta="/interfazDeUsuario/VentanaConsultaDocentes.fxml";
        desplegarVentana(ruta);        
    }
    
    public void registrarConstanciaTrabajoRecepcional(){
        String ruta="/interfazDeUsuario/VentanaRegistroConstanciaTrabajoRecepcional.fxml";
        desplegarVentana(ruta);
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
            cerrarVentana();
        }catch(IOException excepcion){
            Alertas.mostrarMensajeErrorAlDesplegarVentana();                
        }
    }
        
    private void desplegarVentana(String rutaFxml){
        try{
            Parent root=FXMLLoader.load(getClass().getResource(rutaFxml));
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
        escenario = (Stage) ventanaMenuPersonalAdministrativo.getScene().getWindow();
        escenario.close();        
    }
}
