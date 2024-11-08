package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.HashSet;
import java.util.Set;
import logicaDeNegocio.DAOImplementacion.DAOCuentaImplementacion;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import org.junit.After;
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
        usuarioPrueba.setIdTipoDeUsuario(1);
        int resultado = implementacion.RegistrarUsuario(usuarioPrueba);
        assertEquals(1, resultado);
    }
}
