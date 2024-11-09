package pruebaLogicaDeNegocio.DAOImplementacion;


import logicaDeNegocio.DAOImplementacion.DAOCuentaImplementacion;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.BeforeClass;

public class PruebaDAOCuentaImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaRegistrarUsuarioExitosa() {
        Cuenta usuarioPrueba = new Cuenta();
        DAOCuentaImplementacion implementacion = new DAOCuentaImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        usuarioPrueba.setIdTipoDeUsuario(2);
        int resultado = implementacion.RegistrarUsuario(usuarioPrueba);
        assertEquals(1, resultado);
    }
    
    @Test
    public void PruebaValidarCredencialesExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        DAOCuentaImplementacion implementacion = new DAOCuentaImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        usuarioPrueba.setIdTipoDeUsuario(2);
        Cuenta logger=new Cuenta();
        logger.setTipoDeUsuario("Logger");
        int resultadoEsperado = 1;
        int resultadoObtenido = implementacion.ValidarCredenciales(usuarioPrueba, logger);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaValidarCredencialesInexistentesExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        DAOCuentaImplementacion implementacion = new DAOCuentaImplementacion();
        usuarioPrueba.setNombreUsuario("profesorDos@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        usuarioPrueba.setIdTipoDeUsuario(2);
        Cuenta logger=new Cuenta();
        logger.setTipoDeUsuario("Logger");
        int resultadoEsperado = 0;
        int resultadoObtenido = implementacion.ValidarCredenciales(usuarioPrueba, logger);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaObtenerTipoDeUsuarioExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        DAOCuentaImplementacion implementacion = new DAOCuentaImplementacion();
        usuarioPrueba.setNombreUsuario("profesorpruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        usuarioPrueba.setIdTipoDeUsuario(2);
        Cuenta logger=new Cuenta();
        logger.setTipoDeUsuario("Logger");
        String resultadoEsperado = "Profesor";
        String resultadoObtenido = implementacion.ObtenerTipoDeUsuario(usuarioPrueba, logger);
        assertEquals(resultadoObtenido,resultadoEsperado);
    }
    
    @Test
    public void PruebaConfirmarConexionBaseDeDatosExitosa(){
        DAOCuentaImplementacion daoUsuario=new DAOCuentaImplementacion();
        boolean resultadoObtenido = daoUsuario.ConfirmarConexionBaseDeDatos();
        assertTrue(resultadoObtenido);
    }
    
    @Test
    public void PruebaconfirmarConexionDeInicioDeSesionExitosa(){
        DAOCuentaImplementacion daoUsuario=new DAOCuentaImplementacion();
        Cuenta logger=new Cuenta();
        logger.setTipoDeUsuario("Logger");
        boolean resultado = daoUsuario.confirmarConexionDeInicioDeSesion(logger);
        assertTrue(resultado);
    }
    
    
}
