package PruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaManejadorBaseDeDatosSinAcceso {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosSinConexionAdministrativoExitosa(){
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNull(resultado);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosSinConexionProfesorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNull(resultado);
    }
    
    @Test 
    public void pruebaValidarConectarBaseDeDatosSinConexionProfesorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinConexionAdministradorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinCuentaProfesorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosSinCuentaAdministradorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = -1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test 
    public void pruebaFalloConectarBaseDeDatosSinConexionLoggerExitosa() {
        Cuenta logger = new Cuenta();
        logger.setTipoDeUsuario("Logger");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNull(resultado);
    }

}
