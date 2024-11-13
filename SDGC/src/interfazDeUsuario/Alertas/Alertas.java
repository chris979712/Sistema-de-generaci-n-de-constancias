package interfazDeUsuario.Alertas;

import java.util.Optional;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class Alertas extends Application {
    
    public static void mostrarMensajeUsuarioNoEncontrado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Usuario no encontrado");
            mensaje.setContentText("El usuario no se ha podido conectar a la base de datos\nInténtelo más tarde.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeUsuarioInvalido(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Usuario inválido");
            mensaje.setContentText("Revise que el nombre de usuario y la contraseña sean correctos");
            mensaje.showAndWait();
        });
    }        
                           
    public static void mostrarMensajeUsuarioDuplicado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Usuario duplicado");
            mensaje.setContentText("El correo ingresado ya se ha registrado previamente en un Profesor u Administrador");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosInvalidos(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Datos inválidos");
            mensaje.setContentText("Verifique que los datos ingresados sean los correctos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosDuplicados(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Duplicado de datos");
            mensaje.setContentText("Los datos que desea ingresar ya han sido previamente insertados");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeProfesorConUsuario(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Profesor con usuario");
            mensaje.setContentText("El profesor al que desea asignar un usuario, ya cuenta con una cuenta");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeDatosIngresados(){
         Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Datos correctos");
            mensaje.setContentText("Los datos han sido registrados correctamente");
            mensaje.showAndWait();
        
        });
    }
    public static void mostrarMensajeDatosIncompletos(){
        Platform.runLater(() ->{
        Alert mensaje = new Alert (AlertType.ERROR);
        mensaje.setTitle("Información invalida");
        mensaje.setContentText("Asegúrese de llenar correctamente todos los campos");
        mensaje.showAndWait();
        });
    }        
    
    
    public static void mostrarMensajeErrorEnLaConexion(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en la conexion");
            mensaje.setContentText("No se ha podido conectar de forma correcta a la base de datos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarCorreoDeProfesorInexistente(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Correo inválido");
            mensaje.setContentText("El correo asociado no pertenece a ningún profesor registrado");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarRegistroProfesorExitoso(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Registro de profesor correcto");
            mensaje.setContentText("El profesor fue registrado correctamente");
            mensaje.showAndWait();
        });
    }      
        
    public static void mostrarMensajeErrorAlObtenerDatos(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Fallo en la obtención de datos");
            mensaje.setContentText("No se han podido recuperar los datos de manera correcta");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeSinResultadosEncontrados(String mensajeAlerta){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("No se han encontrado resultados");
            mensaje.setContentText(mensajeAlerta);
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarErrorEnLaCreacionDeInforme(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en la creación de informe");
            mensaje.setContentText("No se ha podido generar el informe, intentelo más tarde.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeInformeGuardadoExitosamente(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Archivo guardado");
            mensaje.setContentText("El informe ha sido guardado exitosamente.");
            mensaje.showAndWait();
        });
    }
    
     public static void mostrarMensajeErrorAlGuardarInforme(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error en el guadado");
            mensaje.setContentText("No se ha podido guardar el archivo");
            mensaje.showAndWait();
        });
    }
    
    public static boolean mostrarMensajeDescargaDeArchivo() {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Elaboración de archivo exitoso");
        mensaje.setContentText("El archivo se creó correctamente.\n ¿Desea descargarlo?");
        Optional<ButtonType> resultado = mensaje.showAndWait();

        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
    
    public static void mostrarMensajeAccesoDenegado(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("ACCESO DENEGADO");
            mensaje.setContentText("Su cuenta se encuentra archivada, si desea ingresar contacte a los administrativos.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeSinModificarDatos(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Datos similares");
            mensaje.setContentText("Realice una modificación en los campos de texto");
            mensaje.showAndWait();
        });
    }
    
    public static boolean mostrarConfirmacionDeAccion(String mensajeAlerta) {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Confirmación de accion");
        mensaje.setContentText(mensajeAlerta);
        Optional<ButtonType> resultado = mensaje.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
    
    public static void mostrarColaboracionCerrada(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("¡Colaboracion Cerrada!");
            mensaje.setContentText("Felicidades, la colaboración ha sido cerrada con éxito, será redirigido al menú principal");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarColaboracionFinalizada(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("¡Colaboracion finalizada!");
            mensaje.setContentText("Se ha dado de baja la colaboración, puede encontrarla como colaboración finalizada.");
            mensaje.showAndWait();
        });
    }
     
    public static void mostrarColaboracionIniciada(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Colaboración iniciada");
            mensaje.setContentText("La colaboración ha iniciado correctamente");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarSinConexionAInternet(String mensajeAlerta){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Sin conexion a internet");
            mensaje.setContentText(mensajeAlerta);
            mensaje.showAndWait();
        });
    }
    

    public static void mostrarLimitePeticionesColaboracion(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.INFORMATION);
            mensaje.setTitle("Limite de peticiones");
            mensaje.setContentText("Ha aceptado el limite de peticiones para una colaboracion");
            mensaje.showAndWait();
        });
    
    
    }
            
    public static void mostrarMensajeSinConexion(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR); 
            DialogPane dialogPane = mensaje.getDialogPane();
            dialogPane.setMinWidth(400); 
            dialogPane.setMinHeight(200);
            mensaje.setTitle("Error en la conexion");
            mensaje.setContentText("Se ha perdido la conexión a la base de datos, volverá al inicio de sesión.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeErrorAlDesplegarVentana(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Error al cambiar de ventana");
            mensaje.setContentText("No se ha podido abrir la ventana correspondiente, inténtelo de nuevo.");
            mensaje.showAndWait();
        });
    }
    public static void mostrarMensajeFechaDeCierreColaboracion(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.ERROR);
            mensaje.setTitle("Fecha de cierre excedida");
            mensaje.setContentText("La colaboración ha llegado a su fecha de cierre. \nNo se puede acceder al inicio o modificación de actividades.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeAccesoAVentanaInvalida(){
        Platform.runLater(() -> {
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Sin permiso");
            mensaje.setContentText("No ha realizado una propuesta de colaboración");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarBaseDatosSinCatalogos(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Base de datos sin catálogos");
            mensaje.setContentText("La base de datos se encuentra sin catalogos");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarRepresentanteSinRegistrar(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Representante sin registrar");
            mensaje.setContentText("Debe registrar un representante institucional");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeUniversidadVeracruzana(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Sistema de la Universidad Veracruzana");
            mensaje.setContentText("No se puede agregar a la Universidad Veracruzana como representante institucional");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeNumeroDePersonalDuplicado(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Numero de personal duplicado");
            mensaje.setContentText("El numero de personal que desea registrar ya ha sido registrado previamente.");
            mensaje.showAndWait();
        });
    }
    
    public static boolean confirmarActualizarCredenciales() {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Reenvío de credenciales");
        mensaje.setContentText("¿Está seguro de reenviar las credenciales al profesor seleccionado?, esto hará que su contraseña se actualice.");

        Optional<ButtonType> resultado = mensaje.showAndWait();

        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
    
    public static void mostrarMensajeArchivoASubirExistente(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Archivo duplicado");
            mensaje.setContentText("El nombre del archivo que desea subir ya ha sido guardado previamente.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeActividadExistente(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.WARNING);
            mensaje.setTitle("Actividad existente");
            mensaje.setContentText("El numero o nombre de la actividad que desea subir, ya se ha registrado previamente.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeNotificacionEnviada(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("Notificación enviada");
            mensaje.setContentText("La notificación ha sido enviada correctamente.");
            mensaje.showAndWait();
        });
    }
    
    public static void mostrarMensajeNoSePuedeCerrarColaboracion(String mensajeAlerta){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("No es posible cerrar la colaboracion");
            mensaje.setContentText(mensajeAlerta);
            mensaje.showAndWait();
        });
    }
    
    public static boolean mostrarMensajeArchivoGeneradoPreviamente() {
        Alert mensaje = new Alert(AlertType.CONFIRMATION);
        mensaje.setTitle("Archivo generado previamente");
        mensaje.setContentText("El informe ya se ha generado previamente.\n¿Desea descargarlo?");

        Optional<ButtonType> resultado = mensaje.showAndWait();

        return resultado.isPresent() && resultado.get() == ButtonType.OK;
    }
    
    public static void mostrarMensajeNoSePuedeGuardarLaEvidencia(){
        Platform.runLater(() ->{
            Alert mensaje = new Alert(AlertType.CONFIRMATION);
            mensaje.setTitle("No es posible guardar la evidencia");
            mensaje.setContentText("No se puede acceder a la carpeta de guardado de evidencia.\n Intente de nuevo.");
            mensaje.showAndWait();
        });
    }
    
    @Override
    public void start(Stage stage){
        try{
            stage.show();
        }catch(Exception excepcion){   
        }
    }
    
}