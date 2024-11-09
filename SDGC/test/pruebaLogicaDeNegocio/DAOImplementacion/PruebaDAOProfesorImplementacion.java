package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.DAOImplementacion.DAOProfesorImplementacion;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaDAOProfesorImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void PruebaRegistrarProfesorExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setCategoriaDeContracion_idCategoriaDeContratacion(1);
        profesor.setTipoDeContratacion_idTipoDeContratacion(2);
        profesor.setCorreoInstitucional("chrisvasquez985@gmail.com");
        profesor.setNombre("Christopher");
        profesor.setPrimerApellido("Vasquez");
        profesor.setSegundoApellido("Zapata");
        profesor.setNumeroDePersonal("123456");
        String contrasenia = "Contrasenia123*";
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.RegistrarProfesor(profesor, contrasenia);
        assertEquals(resultadoEsperado,resultadoObtenido);
    }
    
    @Test
    public void PruebaVerificarDuplicidadDeProfesorExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setCorreoInstitucional("chrisvasquez985@gmail.com");
        int resultadoEsperado = 1;
        int resultadoObtenido = dao.VerificarDuplicidadDeProfesor(profesor);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaVerificarSinDuplicidadDeProfesorExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setCorreoInstitucional("vasquezchris985@gmail.com");
        int resultadoEsperado = 0;
        int resultadoObtenido = dao.VerificarDuplicidadDeProfesor(profesor);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaObtenerProfesorPorCorreoExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        Profesor profesor = new Profesor();
        profesor.setCategoriaDeContracion_idCategoriaDeContratacion(1);
        profesor.setTipoDeContratacion_idTipoDeContratacion(2);
        profesor.setCorreoInstitucional("chrisvasquez985@gmail.com");
        profesor.setNombre("Christopher");
        profesor.setPrimerApellido("Vasquez");
        profesor.setSegundoApellido("Zapata");
        profesor.setNumeroDePersonal("123456");
        profesor.setCuenta_idCuenta(1);
        profesor.setIdProfesor(1);
        String correo = "chrisvasquez985@gmail.com";
        Profesor profesorObtenido = dao.ObtenerProfesorPorCorreo(correo);
        assertEquals(profesor,profesorObtenido);
    }
    
    @Test
    public void PruebaSinObtenerProfesorPorCorreoExitosa(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        String correo = "vasquezchris985@gmail.com";
        Profesor profesorObtenido = dao.ObtenerProfesorPorCorreo(correo);
        assertEquals(0,profesorObtenido.getIdProfesor());
    }
    
    
    
    @Test
    public void PruebaObtenerProfesores(){
        DAOProfesorImplementacion dao = new DAOProfesorImplementacion();
        List<Profesor> profesores = new ArrayList<Profesor>();
        Profesor profesor = new Profesor();
        profesor.setCategoriaDeContracion_idCategoriaDeContratacion(1);
        profesor.setTipoDeContratacion_idTipoDeContratacion(2);
        profesor.setCorreoInstitucional("chrisvasquez985@gmail.com");
        profesor.setNombre("Christopher");
        profesor.setPrimerApellido("Vasquez");
        profesor.setSegundoApellido("Zapata");
        profesor.setNumeroDePersonal("123456");
        profesor.setCuenta_idCuenta(11);
        profesor.setIdProfesor(1);
        profesores.add(profesor);
        List<Profesor> profesoresObtenido = dao.ObtenerProfesores();
        assertEquals(profesores, profesoresObtenido);
    }
    
}
