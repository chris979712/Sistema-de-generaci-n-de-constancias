package PruebaAccesoADatos;

import accesoADatos.ManejadorBaseDeDatos;
import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.Cuenta;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PruebaManejadorBaseDeDatos{
    
    @Test
    public void pruebaConectarBaseDeDatosAdministrativoExitosa() throws SQLException{
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaConectarBaseDeDatosProfesorExitosa() throws SQLException{
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatos();
        assertNotNull(resultado);
        resultado.close();
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosProfesorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Profesor");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaValidarConectarBaseDeDatosAdministradorExitosa(){
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentaadmin@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton usuario = CuentaSingleton.getInstancia(usuarioPrueba);
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        int resultado = baseDeDatosPrueba.validarConectarBaseDeDatos();
        int resultadoEsperado = 1;
        assertEquals(resultadoEsperado,resultado);
    }
    
    @Test
    public void pruebaConectarBaseDeDatosLoggerExitosa() throws SQLException{
        Cuenta logger = new Cuenta();
        logger.setTipoDeUsuario("Logger");
        ManejadorBaseDeDatos baseDeDatosPrueba = new ManejadorBaseDeDatos();
        Connection resultado = baseDeDatosPrueba.conectarBaseDeDatosLogger(logger);
        assertNotNull(resultado);
    }
   
}