package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import logicaDeNegocio.Enums.TipoDeUsuario;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;

public class ManejadorBaseDeDatos {
    
    private static final org.apache.log4j.Logger LOG=org.apache.log4j.Logger.getLogger(ManejadorBaseDeDatos.class);
    private static ManejadorBaseDeDatos instancia;
    private Connection conexion;
    
    public Connection conectarBaseDeDatos(){
        CuentaSingleton usuario = CuentaSingleton.getInstancia();
        Properties datosUsuario = new Properties();
        if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Administrativo.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionAdministrativo();
        }else if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Profesor.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionProfesor();
        }
        try{
            String nombreBaseDeDatos = datosUsuario.getProperty("Direccion");
            String nombreUsuario = datosUsuario.getProperty("Usuario");
            String contrasenia = datosUsuario.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLException excepcion){
            LOG.fatal(excepcion);
        }
        return conexion;
    }
    
    public int validarConectarBaseDeDatos(){
        int resultadoValidacion=1;
        CuentaSingleton usuario = CuentaSingleton.getInstancia();
        Properties datosUsuario = new Properties();
        if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Administrativo.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionAdministrativo();
        }else if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Profesor.toString())){
            datosUsuario = new ManejadorBaseDeDatos().obtenerArchivoConexionProfesor();
        }
        try{
            String nombreBaseDeDatos = datosUsuario.getProperty("Direccion");
            String nombreUsuario = datosUsuario.getProperty("Usuario");
            String contrasenia = datosUsuario.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLException excepcion){
            LOG.fatal(excepcion.getCause());
            if(excepcion.getSQLState().equals("28000")){
                resultadoValidacion = 0;
            }else{
                resultadoValidacion = -1;
            }
        }
        return resultadoValidacion;
    }
    
    
    public Connection conectarBaseDeDatosLogger(Cuenta usuario){
        Properties datosLogger = new Properties();
        if(usuario.getTipoDeUsuario().equals(TipoDeUsuario.Logger.toString())){
            datosLogger = new ManejadorBaseDeDatos().obtenerArchivoConexionLogger();
        }
        try{
            String nombreBaseDeDatos = datosLogger.getProperty("Direccion");
            String nombreUsuario = datosLogger.getProperty("Usuario");
            String contrasenia = datosLogger.getProperty("Contrasenia");
            conexion = DriverManager.getConnection(nombreBaseDeDatos,nombreUsuario,contrasenia);
        }catch(SQLException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return conexion;
    }
    
    public Properties obtenerArchivoConexionAdministrativo(){
        Properties propiedadesAdministrativo = new Properties();
        try{
           InputStream archivoUsuario = getClass().getClassLoader().getResourceAsStream("accesoADatos/Administrativo.txt");
           propiedadesAdministrativo.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            LOG.fatal(archivoNoEncontrado.getMessage());
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesAdministrativo;
    }
    
    public Properties obtenerArchivoConexionLogger(){
        Properties propiedadesLogger = new Properties();
          try{
           InputStream archivoUsuario = getClass().getClassLoader().getResourceAsStream("accesoADatos/Logger.txt");
           propiedadesLogger.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            Logger.getLogger(ManejadorBaseDeDatos.class.getName()).log(Level.WARNING, null, archivoNoEncontrado);
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesLogger;
    }
    
    public Properties obtenerArchivoConexionProfesor(){
        Properties propiedadesProfesor = new Properties();
        try{
           InputStream archivoUsuario = getClass().getClassLoader().getResourceAsStream("accesoADatos/Profesor.txt");
           propiedadesProfesor.load(archivoUsuario);
        }catch(FileNotFoundException archivoNoEncontrado){
            LOG.fatal(archivoNoEncontrado.getCause());
        }catch(IOException excepcion){
            LOG.fatal(excepcion.getCause());
        }
        return propiedadesProfesor;
    }
    
}