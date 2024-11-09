package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Interfaces.CuentaInterface;
import logicaDeNegocio.Utilidades.Constantes;
import org.apache.log4j.LogManager;
import org.apache.log4j.Priority;


public class DAOCuentaImplementacion implements CuentaInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger log = LogManager.getLogger(DAOCuentaImplementacion.class); 
    
    @Override
    public int ValidarCredenciales(Cuenta cuenta, Cuenta logger) {
        int resultadoValidacion = Constantes.OPERACION_FALLIDA;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
            PreparedStatement sentencia = conexion.prepareStatement("Select * from cuenta where usuario = ? and contrasenia = sha2(?,?)")){
            sentencia.setString(1,cuenta.getNombreUsuario());
            sentencia.setString(2, cuenta.getContrasenia());
            sentencia.setInt(3, 256);
            ResultSet resultado  = sentencia.executeQuery();
            int resultadoCoincidencias=0;
            while(resultado.next()){
                resultadoCoincidencias++;
            }
            if(resultadoCoincidencias==1){
                resultadoValidacion = 1;
            }else{
                resultadoValidacion = 0;
            }
        }catch(SQLException sqlException){
            System.out.println(sqlException);
            log.log(Priority.ERROR, sqlException);
            resultadoValidacion = Constantes.OPERACION_FALLIDA;
        }
        return resultadoValidacion;
    }

    @Override
    public int RegistrarUsuario(Cuenta cuenta) {
        int resultadoDeInsercion = Constantes.OPERACION_FALLIDA;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Insert into cuenta (usuario, contrasenia,TipoDeUsuario_idTipoDeUsuario) values (?,sha2(?,?),?)")){
            sentencia.setString(1, cuenta.getNombreUsuario());
            sentencia.setString(2, cuenta.getContrasenia());
            sentencia.setInt(3, 256);
            sentencia.setInt(4, cuenta.getIdTipoDeUsuario());
            resultadoDeInsercion = sentencia.executeUpdate();
        }catch(SQLException sqlException){
            log.log(Priority.ERROR, sqlException);
            resultadoDeInsercion = Constantes.OPERACION_FALLIDA;
            System.out.println(sqlException);
        }
        return resultadoDeInsercion;
    }

    @Override
    public String ObtenerTipoDeUsuario(Cuenta cuenta, Cuenta logger) {
       String tipoDeUsuario = "";
       try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger);
           PreparedStatement sentencia = conexion.prepareStatement("select tipodeusuario.tipodeusuario from cuenta,tipodeusuario where "
                   + "cuenta.usuario = ? and cuenta.TipoDeUsuario_idTipoDeUsuario = tipodeusuario.idTipoDeUsuario;")){
           sentencia.setString(1, cuenta.getNombreUsuario());
           ResultSet resultado = sentencia.executeQuery();
           if(resultado.isBeforeFirst()){
               while(resultado.next()){
                   tipoDeUsuario = resultado.getString(1);
               }
           }
       }catch(SQLException excepcion){
            System.out.println(excepcion);
            log.log(Priority.ERROR, excepcion);
            tipoDeUsuario = Constantes.OPERACION_BASE_DE_DATOS_FALLIDA_STRING;;
        }
       return tipoDeUsuario;
    }

    @Override
    public boolean ConfirmarConexionBaseDeDatos() {
        boolean resultadoConfirmacionDeConexion = false;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos()){
            resultadoConfirmacionDeConexion = true;
        }catch(SQLException | NullPointerException excepcion){
            resultadoConfirmacionDeConexion = false;
            log.log(Priority.ERROR, excepcion);
        }
        return resultadoConfirmacionDeConexion;
    }
    
    @Override
    public boolean confirmarConexionDeInicioDeSesion(Cuenta logger) {
        boolean resultadoDeConfirmacionDeConexion=false;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatosLogger(logger)){
           resultadoDeConfirmacionDeConexion=true;
        }catch(SQLException | NullPointerException excepcion){
            log.log(Priority.ERROR, excepcion);
            resultadoDeConfirmacionDeConexion = false;
        }
        return resultadoDeConfirmacionDeConexion;
    }
    
}
