
package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Interfaces.ProfesorImplementacion;
import logicaDeNegocio.Utilidades.Constantes;
import org.apache.log4j.LogManager;
import org.apache.log4j.Priority;


public class DAOProfesorImplementacion implements ProfesorImplementacion {

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(DAOProfesorImplementacion.class); 
    
    @Override
    public int RegistrarProfesor(Profesor profesor, String contrasenia) {
        int resultadoInsercion = Constantes.OPERACION_FALLIDA;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentenciaAgregarUsuario = conexion.prepareStatement("Insert into cuenta (usuario,contrasenia,TipoDeUsuario_idTipoDeUsuario) values"
                    + " (?,sha2(?,?),?)");
            PreparedStatement sentenciaConsultarIdUsuario = conexion.prepareStatement("select * from cuenta where usuario = ?");
            PreparedStatement sentenciaAgregarProfesor = conexion.prepareStatement("Insert into Profesor (nombre, primerApellido, segundoApellido, correoInstitucional, numeroDePersonal, Cuenta_idCuenta"
                    + ",CategoriaDeContratacion_idCategoriaDeContratacion,TipoDeContratacion_idTipoDeContratacion ) values (?,?,?,?,?,?,?,?)")){
            conexion.setAutoCommit(false);
            sentenciaAgregarUsuario.setString(1, profesor.getCorreoInstitucional());
            sentenciaAgregarUsuario.setString(2, contrasenia);
            sentenciaAgregarUsuario.setInt(3, 256);
            sentenciaAgregarUsuario.setInt(4, 2);
            sentenciaAgregarUsuario.executeUpdate();
            sentenciaConsultarIdUsuario.setString(1, profesor.getCorreoInstitucional());
            ResultSet resultadoIdUsuario = sentenciaConsultarIdUsuario.executeQuery();
            int idObtenido = 0;
            while(resultadoIdUsuario.next()){
                idObtenido = resultadoIdUsuario.getInt("idCuenta");
            }
            sentenciaAgregarProfesor.setString(1, profesor.getNombre());
            sentenciaAgregarProfesor.setString(2, profesor.getPrimerApellido());
            sentenciaAgregarProfesor.setString(3, profesor.getSegundoApellido());
            sentenciaAgregarProfesor.setString(4, profesor.getCorreoInstitucional());
            sentenciaAgregarProfesor.setString(5, profesor.getNumeroDePersonal());
            sentenciaAgregarProfesor.setInt(6, idObtenido);
            sentenciaAgregarProfesor.setInt(7, profesor.getCategoriaDeContracion_idCategoriaDeContratacion());
            sentenciaAgregarProfesor.setInt(8, profesor.getTipoDeContratacion_idTipoDeContratacion());
            resultadoInsercion = sentenciaAgregarProfesor.executeUpdate();
            conexion.commit();
        }catch(SQLException sqlException){
            try{
                BASE_DE_DATOS.conectarBaseDeDatos().rollback();
            }catch(SQLException rollbackException){
               logger.log(Priority.ERROR, rollbackException);
               resultadoInsercion = Constantes.OPERACION_FALLIDA;
            }
            logger.log(Priority.ERROR, sqlException);
            resultadoInsercion = Constantes.OPERACION_FALLIDA;
        }
        return resultadoInsercion;
    }

    @Override
    public int VerificarDuplicidadDeProfesor(Profesor profesor) {
        int resultadoVerificacion = Constantes.SIN_RESULTADOS_ENCONTRADOS;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from Profesor where correoInstitucional = ?")){
            sentencia.setString(1, profesor.getCorreoInstitucional());
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoVerificacion++;
                }
            }
        }catch(SQLException sqlException){
            logger.log(Priority.ERROR, sqlException);
            resultadoVerificacion = Constantes.OPERACION_FALLIDA;
        }
        return resultadoVerificacion;
    }

    @Override
    public Profesor ObtenerProfesorPorCorreo(String correo) {
        Profesor profesorObtenido = new Profesor();
        profesorObtenido.setIdProfesor(Constantes.SIN_RESULTADOS_ENCONTRADOS);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from Profesor where correoInstitucional = ?")){
            sentencia.setString(1, correo);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    profesorObtenido.setIdProfesor(resultado.getInt("Profesor"));
                    profesorObtenido.setCategoriaDeContracion_idCategoriaDeContratacion(resultado.getInt("CategoriaDeContratacion_idCategoriaDeContratacion"));
                    profesorObtenido.setTipoDeContratacion_idTipoDeContratacion(resultado.getInt("TipoDeContratacion_idTipoDeContratacion"));
                    profesorObtenido.setCuenta_idCuenta(resultado.getInt("Cuenta_idCuenta"));
                    profesorObtenido.setCorreoInstitucional(resultado.getString("correoInstitucional"));
                    profesorObtenido.setNombre(resultado.getString("nombre"));
                    profesorObtenido.setPrimerApellido(resultado.getString("primerApellido"));
                    profesorObtenido.setSegundoApellido(resultado.getString("segundoApellido"));
                    profesorObtenido.setNumeroDePersonal(resultado.getString("numeroDePersonal"));
                }
            }
        }catch(SQLException sqlException){
            logger.log(Priority.ERROR, sqlException);
            profesorObtenido.setIdProfesor(Constantes.OPERACION_FALLIDA);
        }
        return profesorObtenido;
    }

    @Override
    public List<Profesor> ObtenerProfesores() {
        List<Profesor> profesoresObtenidos = new ArrayList<Profesor>();
        Profesor profesorObtenidoFallo = new Profesor();
        profesorObtenidoFallo.setIdProfesor(Constantes.OPERACION_FALLIDA);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select * from profesor")){
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    Profesor profesorObtenido = new Profesor();
                    profesorObtenido.setIdProfesor(resultado.getInt("Profesor"));
                    profesorObtenido.setCategoriaDeContracion_idCategoriaDeContratacion(resultado.getInt("CategoriaDeContratacion_idCategoriaDeContratacion"));
                    profesorObtenido.setTipoDeContratacion_idTipoDeContratacion(resultado.getInt("TipoDeContratacion_idTipoDeContratacion"));
                    profesorObtenido.setCuenta_idCuenta(resultado.getInt("Cuenta_idCuenta"));
                    profesorObtenido.setCorreoInstitucional(resultado.getString("correoInstitucional"));
                    profesorObtenido.setNombre(resultado.getString("nombre"));
                    profesorObtenido.setPrimerApellido(resultado.getString("primerApellido"));
                    profesorObtenido.setSegundoApellido(resultado.getString("segundoApellido"));
                    profesorObtenido.setNumeroDePersonal(resultado.getString("numeroDePersonal"));
                    profesoresObtenidos.add(profesorObtenido);
                }
            }
        }catch(SQLException sqlException){
            logger.log(Priority.ERROR, sqlException);
            profesoresObtenidos.add(0, profesorObtenidoFallo);
        }
        return profesoresObtenidos;
    }
    
}
