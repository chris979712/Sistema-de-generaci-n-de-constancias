package pruebasLogicaDeNegocio.ConstanciaImplementacion;

import com.itextpdf.text.Document;
import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Alumno;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;
import logicaDeNegocio.ConstanciaImplementacion.ConstanciaImplementacion;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaConstanciaImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
     @Test
    public void pruebaCrearInformeDeColaboracionExitoso(){
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
        profesorObtenido.setNombre("Mireya Jannet");
        profesorObtenido.setPrimerApellido("Zapata");
        profesorObtenido.setSegundoApellido("Rodriguez");
        profesorObtenido.setCorreoInstitucional("chrisvasquez985@gmail.com");
        trabajoObtenido.setProfesor(profesorObtenido);
        trabajosEsperados.add(trabajoObtenido);
        ConstanciaImplementacion constancia = new ConstanciaImplementacion();
        Document documento = constancia.crearInformeDeColaboracion(profesorObtenido, trabajosEsperados);
        assertNotNull(documento);
    }
}
