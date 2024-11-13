package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logicaDeNegocio.Clases.Alumno;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;
import logicaDeNegocio.Interfaces.TrabajoRecepcionalInterface;
import logicaDeNegocio.Utilidades.Constantes;
import org.apache.log4j.LogManager;
import org.apache.log4j.Priority;


public class DAOTrabajoRecepcionalImplementacion implements TrabajoRecepcionalInterface{

    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(DAOTipoDeContratacionImplementacion.class); 
    private static final String QueryParaObtenerLosTrabajosRecepcionalesDelProfesor = "SELECT " +
                       "tr.idTrabajoRecepcional, tr.modalidad, tr.resultadoObtenido, " +
                       "tr.tituloDeTrabajo, tr.fechaDePresentación, tr.carrera, tr.rol, " +
                       "a.idAlumno, a.nombreCompleto AS nombreAlumno, " +
                       "pe.idPeriodoEscolar, pe.periodoEscolar, " +
                       "p.Profesor, p.nombre, p.primerApellido, p.segundoApellido, p.correoInstitucional " +
                       "FROM TrabajoRecepcional tr " +
                       "JOIN Alumno a ON tr.Alumno_idAlumno = a.idAlumno " +
                       "JOIN PeriodoEscolar pe ON tr.PeriodoEscolar_idPeriodoEscolar = pe.idPeriodoEscolar " +
                       "JOIN Profesor p ON tr.Profesor_Profesor = p.Profesor "
                        + "where pe.idPeriodoEscolar = ? and p.Profesor = ?";
    
    @Override
    public int RegistrarTrabajoRecepcional(TrabajoRecepcional trabajo) {
        int resultadoInsercion = Constantes.SIN_RESULTADOS_ENCONTRADOS;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Insert into TrabajoRecepcional (modalidad,resultadoObtenido,tituloDeTrabajo,fechaDePresentación,"
                    + "Profesor_Profesor,PeriodoEscolar_idPeriodoEscolar,Alumno_idAlumno, carrera, rol) values (?,?,?,?,?,?,?,?,?)")){
            sentencia.setString(1, trabajo.getModalidad());
            sentencia.setString(2,trabajo.getResultadoObtenido());
            sentencia.setString(3, trabajo.getTituloDeTrabajo());
            sentencia.setString(4, trabajo.getFechaDePresentacion());
            sentencia.setInt(5, trabajo.getProfesor().getIdProfesor());
            sentencia.setInt(6, trabajo.getPeriodoEscolar().getIdPeriodoEscolar());
            sentencia.setInt(7, trabajo.getAlumno().getIdAlumno());
            sentencia.setString(8, trabajo.getCarrera());
            sentencia.setString(9, trabajo.getRol());
            resultadoInsercion = sentencia.executeUpdate();
        }catch(SQLException sqlException){
            System.out.println(sqlException);
            logger.log(Priority.ERROR, sqlException);
            resultadoInsercion = Constantes.OPERACION_FALLIDA;
        }
        return resultadoInsercion;
    }

    @Override
    public int VerificarParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolar(Profesor profesor, PeriodoEscolar periodo) {
        int resultadoVerificacion = Constantes.SIN_RESULTADOS_ENCONTRADOS;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from TrabajoRecepcional where Profesor_Profesor = ? and PeriodoEscolar_idPeriodoEscolar = ?")){
            sentencia.setInt(1, profesor.getIdProfesor());
            sentencia.setInt(2, periodo.getIdPeriodoEscolar());
            ResultSet trabajosObtenidos = sentencia.executeQuery();
            if(trabajosObtenidos.isBeforeFirst()){
                while(trabajosObtenidos.next()){
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
    public List<TrabajoRecepcional> ObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolar(Profesor profesor, PeriodoEscolar periodo) {
        List<TrabajoRecepcional> trabajosRecepcionales = new ArrayList<>();
        TrabajoRecepcional trabajoRecepcionalFallido = new TrabajoRecepcional();
        trabajoRecepcionalFallido.setIdTrabajoRecepcional(Constantes.OPERACION_FALLIDA);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement(QueryParaObtenerLosTrabajosRecepcionalesDelProfesor)){
            sentencia.setInt(1, periodo.getIdPeriodoEscolar());
            sentencia.setInt(2, profesor.getIdProfesor());
            ResultSet trabajosObtenidos = sentencia.executeQuery();
            if(trabajosObtenidos.isBeforeFirst()){
                while(trabajosObtenidos.next()){
                    TrabajoRecepcional trabajoObtenido = new TrabajoRecepcional();
                    trabajoObtenido.setIdTrabajoRecepcional(trabajosObtenidos.getInt("idTrabajoRecepcional"));
                    trabajoObtenido.setModalidad(trabajosObtenidos.getString("modalidad"));
                    trabajoObtenido.setResultadoObtenido(trabajosObtenidos.getString("resultadoObtenido"));
                    trabajoObtenido.setTituloDeTrabajo(trabajosObtenidos.getString("tituloDeTrabajo"));
                    trabajoObtenido.setFechaDePresentacion(trabajosObtenidos.getString("fechaDePresentación"));
                    trabajoObtenido.setCarrera(trabajosObtenidos.getString("carrera"));
                    trabajoObtenido.setRol(trabajosObtenidos.getString("rol"));
                    Alumno alumno = new Alumno();
                    alumno.setIdAlumno(trabajosObtenidos.getInt("idAlumno"));
                    alumno.setNombreCompleto(trabajosObtenidos.getString("nombreAlumno"));
                    trabajoObtenido.setAlumno(alumno);  
                    PeriodoEscolar periodoObtenido = new PeriodoEscolar();
                    periodoObtenido.setIdPeriodoEscolar(trabajosObtenidos.getInt("idPeriodoEscolar"));
                    periodoObtenido.setPeriodoEscolar(trabajosObtenidos.getString("periodoEscolar"));
                    trabajoObtenido.setPeriodoEscolar(periodoObtenido); 
                    Profesor profesorObtenido = new Profesor();
                    profesorObtenido.setIdProfesor(trabajosObtenidos.getInt("Profesor"));
                    profesorObtenido.setNombre(trabajosObtenidos.getString("nombre"));
                    profesorObtenido.setPrimerApellido(trabajosObtenidos.getString("primerApellido"));
                    profesorObtenido.setSegundoApellido(trabajosObtenidos.getString("segundoApellido"));
                    profesorObtenido.setCorreoInstitucional(trabajosObtenidos.getString("correoInstitucional"));
                    trabajoObtenido.setProfesor(profesorObtenido);  
                    trabajosRecepcionales.add(trabajoObtenido);
                }
            }
        }catch(SQLException sqlException){
            logger.log(Priority.ERROR, sqlException);
            trabajosRecepcionales.add(trabajoRecepcionalFallido);
        }
        return trabajosRecepcionales;
    }

    @Override
    public int VerificarDuplicidadEnTrabajoRecepcional(TrabajoRecepcional trabajo) {
        int resultadoVerificacion = Constantes.SIN_RESULTADOS_ENCONTRADOS;
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from TrabajoRecepcional where tituloDeTrabajo = ? and (fechaDePresentación = ? or modalidad = ?)")){
            sentencia.setString(1, trabajo.getTituloDeTrabajo());
            sentencia.setString(2, trabajo.getFechaDePresentacion());
            sentencia.setString(3, trabajo.getModalidad());
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
    
}
