package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import logicaDeNegocio.Clases.Alumno;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import logicaDeNegocio.Interfaces.AlumnoImplementacion;
import logicaDeNegocio.Utilidades.Constantes;

public class DAOAlumnoImplementacion implements AlumnoImplementacion{

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    
    @Override
    public Alumno ObtenerAlumnoPorNombreCompleto(String nombre) {
        Alumno alumnoObtenido = new Alumno();
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from alumno where  nombreCompleto = ?")){
            sentencia.setString(1, nombre);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    alumnoObtenido.setIdAlumno(resultado.getInt("idAlumno"));
                    alumnoObtenido.setNombreCompleto(resultado.getString("nombreCompleto"));
                }
            }
        } catch (SQLException ex) {
            alumnoObtenido.setIdAlumno(Constantes.OPERACION_FALLIDA);
        }
        return alumnoObtenido;
    }

    @Override
    public int RegistrarAlumno(Alumno alumno) {
        int resultadoInsercion = Constantes.OPERACION_FALLIDA;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Insert into alumno (nombreCompleto) values (?)")){
            sentencia.setString(1, alumno.getNombreCompleto());
            resultadoInsercion = sentencia.executeUpdate();
        }catch (SQLException ex) {
            resultadoInsercion = Constantes.OPERACION_FALLIDA; 
        }

        return resultadoInsercion;
    }

    @Override
    public int VerificarExistenciaDeAlumno(String nombre) {
        int resultadoVerificacion = Constantes.SIN_RESULTADOS_ENCONTRADOS;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from alumno where nombreCompleto = ?")){
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    resultadoVerificacion++;
                }
            }
        }catch(SQLException sqlException){
            resultadoVerificacion = Constantes.OPERACION_FALLIDA;
        }
        return resultadoVerificacion;
    }
    
}
