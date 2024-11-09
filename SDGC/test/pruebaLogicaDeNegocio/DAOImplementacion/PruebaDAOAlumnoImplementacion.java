package pruebaLogicaDeNegocio.DAOImplementacion;

import logicaDeNegocio.Clases.Alumno;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.DAOImplementacion.DAOAlumnoImplementacion;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaDAOAlumnoImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void PruebaRegistrarAlumnoExitosa(){
        Alumno alumnoARegistrar = new Alumno();
        alumnoARegistrar.setNombreCompleto("Christopher Vasquez Zapata");
        DAOAlumnoImplementacion dao = new DAOAlumnoImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.RegistrarAlumno(alumnoARegistrar);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaObtenerAlumnoPorNombreCompletoExitosa(){
        String nombre = "Christopher Vasquez Zapata";
        DAOAlumnoImplementacion dao = new DAOAlumnoImplementacion();
        Alumno alumnoEsperado = new Alumno();
        alumnoEsperado.setIdAlumno(1);
        alumnoEsperado.setNombreCompleto(nombre);
        Alumno alumnoObtenido = dao.ObtenerAlumnoPorNombreCompleto(nombre);
        assertEquals(alumnoEsperado,alumnoObtenido);
    }
    
    @Test
    public void PruebaSinObtenerAlumnoPorNombreCompletoExitosa(){
        String nombre = "Jose Gonzalez Garcia";
        DAOAlumnoImplementacion dao = new DAOAlumnoImplementacion();
        Alumno alumnoObtenido = dao.ObtenerAlumnoPorNombreCompleto(nombre);
        assertEquals(alumnoObtenido.getIdAlumno(),0);
    }
    
    @Test
    public void PruebaVerificarExistenciaDeAlumnoExitosa(){
        String nombre = "Christopher Vasquez Zapata";
        DAOAlumnoImplementacion dao = new DAOAlumnoImplementacion();
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.VerificarExistenciaDeAlumno(nombre);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    @Test
    public void PruebaVerificarSinExistenciaDeAlumnoExitosa(){
        String nombre = "Jose Gonzalez Garcia";
        DAOAlumnoImplementacion dao = new DAOAlumnoImplementacion();
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.VerificarExistenciaDeAlumno(nombre);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
}
