package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Alumno;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;
import logicaDeNegocio.DAOImplementacion.DAOTrabajoRecepcionalImplementacion;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaDAOTrabajoRecepcionalImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void PruebaRegistrarTrabajoRecepcionalExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        Profesor profesorTrabajo = new Profesor();
        profesorTrabajo.setIdProfesor(1);
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        periodoEscolar.setIdPeriodoEscolar(2);
        TrabajoRecepcional trabajoRecepcional = new TrabajoRecepcional();
        trabajoRecepcional.setAlumno(alumno);
        trabajoRecepcional.setProfesor(profesorTrabajo);
        trabajoRecepcional.setPeriodoEscolar(periodoEscolar);
        trabajoRecepcional.setFechaDePresentacion("2023-04-23");
        trabajoRecepcional.setModalidad("Virtual");
        trabajoRecepcional.setResultadoObtenido("Aprobada");
        trabajoRecepcional.setTituloDeTrabajo("IA aplicada a la Ingenieria en software");
        trabajoRecepcional.setCarrera("Ingenieria de software");
        trabajoRecepcional.setRol("Jurado");
        int resultadoEsperado = 1;
        int resultadoInsercion = dao.RegistrarTrabajoRecepcional(trabajoRecepcional);
        assertEquals(resultadoEsperado,resultadoInsercion);
    }
    
    @Test 
    public void PruebaObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolarExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        List<TrabajoRecepcional> trabajosEsperados = new ArrayList<TrabajoRecepcional>();
        TrabajoRecepcional trabajoObtenido = new TrabajoRecepcional();
        trabajoObtenido.setIdTrabajoRecepcional(1);
        trabajoObtenido.setModalidad("Virtual");
        trabajoObtenido.setResultadoObtenido("Aprobada");
        trabajoObtenido.setTituloDeTrabajo("IA aplicada a la Ingenieria en software");
        trabajoObtenido.setFechaDePresentacion("2023-04-23");
        trabajoObtenido.setCarrera("Ingenieria de software");
        trabajoObtenido.setRol("Jurado");
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        alumno.setNombreCompleto("Christopher Vasquez Zapata");
        trabajoObtenido.setAlumno(alumno);
        PeriodoEscolar periodoObtenido = new PeriodoEscolar();
        periodoObtenido.setIdPeriodoEscolar(2);
        periodoObtenido.setTipo("FEB2023-JUL2023");
        trabajoObtenido.setPeriodoEscolar(periodoObtenido);
        Profesor profesorObtenido = new Profesor();
        profesorObtenido.setIdProfesor(1);
        profesorObtenido.setNombre("Christopher");
        profesorObtenido.setPrimerApellido("Vasquez");
        profesorObtenido.setSegundoApellido("Zapata");
        profesorObtenido.setCorreoInstitucional("chrisvasquez985@gmail.com");
        trabajoObtenido.setProfesor(profesorObtenido);
        trabajosEsperados.add(trabajoObtenido);
        List<TrabajoRecepcional> trabajosObtenidos = dao.ObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolar(profesorObtenido, periodoObtenido);
        assertEquals(trabajosEsperados,trabajosObtenidos);
    }
    
    @Test
    public void PruebaSinObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolarExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        List<TrabajoRecepcional> trabajosEsperados = new ArrayList<TrabajoRecepcional>();
        PeriodoEscolar periodoObtenido = new PeriodoEscolar();
        periodoObtenido.setIdPeriodoEscolar(1);
        Profesor profesor = new Profesor();
        profesor.setIdProfesor(1);
        List<TrabajoRecepcional> trabajosObtenidos = dao.ObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolar(profesor, periodoObtenido);
        assertEquals(trabajosEsperados,trabajosObtenidos);
    }
    
    @Test
    public void PruebaVerificarParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolarExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        Profesor profesorTrabajo = new Profesor();
        profesorTrabajo.setIdProfesor(1);
        PeriodoEscolar periodo = new PeriodoEscolar();
        periodo.setIdPeriodoEscolar(2);
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.VerificarParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolar(profesorTrabajo, periodo);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void PruebaVerificarSinParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolarExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        Profesor profesorTrabajo = new Profesor();
        profesorTrabajo.setIdProfesor(1);
        PeriodoEscolar periodo = new PeriodoEscolar();
        periodo.setIdPeriodoEscolar(3);
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.VerificarParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolar(profesorTrabajo, periodo);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void PruebaVerificarDuplicidadEnTrabajoRecepcionalExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        Profesor profesorTrabajo = new Profesor();
        profesorTrabajo.setIdProfesor(1);
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        periodoEscolar.setIdPeriodoEscolar(2);
        TrabajoRecepcional trabajoRecepcional = new TrabajoRecepcional();
        trabajoRecepcional.setAlumno(alumno);
        trabajoRecepcional.setProfesor(profesorTrabajo);
        trabajoRecepcional.setPeriodoEscolar(periodoEscolar);
        trabajoRecepcional.setFechaDePresentacion("2023-04-23");
        trabajoRecepcional.setModalidad("Virtual");
        trabajoRecepcional.setResultadoObtenido("Aprobada");
        trabajoRecepcional.setTituloDeTrabajo("IA aplicada a la Ingenieria en software");
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.VerificarDuplicidadEnTrabajoRecepcional(trabajoRecepcional);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void PruebaVerificarSinDuplicidadEnTrabajoRecepcionalExitosa(){
        DAOTrabajoRecepcionalImplementacion dao = new DAOTrabajoRecepcionalImplementacion();
        Profesor profesorTrabajo = new Profesor();
        profesorTrabajo.setIdProfesor(1);
        Alumno alumno = new Alumno();
        alumno.setIdAlumno(1);
        PeriodoEscolar periodoEscolar = new PeriodoEscolar();
        periodoEscolar.setIdPeriodoEscolar(2);
        TrabajoRecepcional trabajoRecepcional = new TrabajoRecepcional();
        trabajoRecepcional.setAlumno(alumno);
        trabajoRecepcional.setProfesor(profesorTrabajo);
        trabajoRecepcional.setPeriodoEscolar(periodoEscolar);
        trabajoRecepcional.setFechaDePresentacion("2023-05-23");
        trabajoRecepcional.setModalidad("En linea");
        trabajoRecepcional.setResultadoObtenido("Rechazada");
        trabajoRecepcional.setTituloDeTrabajo("IA en las tecnologías de la información");
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.VerificarDuplicidadEnTrabajoRecepcional(trabajoRecepcional);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
}
