package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.TipoDeContratacion;
import logicaDeNegocio.DAOImplementacion.DAOTipoDeContratacionImplementacion;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaDAOTipoDeContratacionImplementacion {

    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }   
    
    @Test
    public void PruebaObtenerTiposDeContratacionExitosa(){
        List<TipoDeContratacion> tiposDeContratacion = new ArrayList<TipoDeContratacion>();
        TipoDeContratacion tipoDeContratacion1 = new TipoDeContratacion();
        tipoDeContratacion1.setIdTipoContratacion(1);
        tipoDeContratacion1.setTipo("PLANTA");
        TipoDeContratacion tipoDeContratacion2 = new TipoDeContratacion();
        tipoDeContratacion2.setIdTipoContratacion(2);
        tipoDeContratacion2.setTipo("INTERINO POR PLAZA");
        TipoDeContratacion tipoDeContratacion3 = new TipoDeContratacion();
        tipoDeContratacion3.setIdTipoContratacion(3);
        tipoDeContratacion3.setTipo("INTERINO POR PERSONA");
        TipoDeContratacion tipoDeContratacion4 = new TipoDeContratacion();
        tipoDeContratacion4.setIdTipoContratacion(4);
        tipoDeContratacion4.setTipo("INTERINO POR TIEMPO DETERMINADO");
        TipoDeContratacion tipoDeContratacion5 = new TipoDeContratacion();
        tipoDeContratacion5.setIdTipoContratacion(5);
        tipoDeContratacion5.setTipo("INTERINO POR OBRA DETERMINADA");
        TipoDeContratacion tipoDeContratacion6 = new TipoDeContratacion();
        tipoDeContratacion6.setIdTipoContratacion(6);
        tipoDeContratacion6.setTipo("INTERINO POR FALTA DE GRADO");
        TipoDeContratacion tipoDeContratacion7 = new TipoDeContratacion();
        tipoDeContratacion7.setIdTipoContratacion(7);
        tipoDeContratacion7.setTipo("SUPLENTE O SUSTITUTO");
        TipoDeContratacion tipoDeContratacion8 = new TipoDeContratacion();
        tipoDeContratacion8.setIdTipoContratacion(8);
        tipoDeContratacion8.setTipo("TRABAJOS ESPECIFICOS");
        TipoDeContratacion tipoDeContratacion9 = new TipoDeContratacion();
        tipoDeContratacion9.setIdTipoContratacion(9);
        tipoDeContratacion9.setTipo("INTERINO POR PLAZA CON PLAZA");
        TipoDeContratacion tipoDeContratacion10 = new TipoDeContratacion();
        tipoDeContratacion10.setIdTipoContratacion(10);
        tipoDeContratacion10.setTipo("INTERINO POR PERSONA CON PLAZA");
        TipoDeContratacion tipoDeContratacion11 = new TipoDeContratacion();
        tipoDeContratacion11.setIdTipoContratacion(11);
        tipoDeContratacion11.setTipo("SUPLENTE O SUSTITUTO CON PLAZA");
        TipoDeContratacion tipoDeContratacion12 = new TipoDeContratacion();
        tipoDeContratacion12.setIdTipoContratacion(12);
        tipoDeContratacion12.setTipo("EVENTUAL");
        TipoDeContratacion tipoDeContratacion13 = new TipoDeContratacion();
        tipoDeContratacion13.setIdTipoContratacion(13);
        tipoDeContratacion13.setTipo("BECA TRABAJO");
        TipoDeContratacion tipoDeContratacion14 = new TipoDeContratacion();
        tipoDeContratacion14.setIdTipoContratacion(14);
        tipoDeContratacion14.setTipo("APOYO");
        TipoDeContratacion tipoDeContratacion15 = new TipoDeContratacion();
        tipoDeContratacion15.setIdTipoContratacion(15);
        tipoDeContratacion15.setTipo("BECA SUBSIDIO");
        TipoDeContratacion tipoDeContratacion16 = new TipoDeContratacion();
        tipoDeContratacion16.setIdTipoContratacion(16);
        tipoDeContratacion16.setTipo("BECA POSGRADO");
        TipoDeContratacion tipoDeContratacion17 = new TipoDeContratacion();
        tipoDeContratacion17.setIdTipoContratacion(17);
        tipoDeContratacion17.setTipo("BECA SISTEMA NACIONAL DE INVESTIGACIÓN");
        TipoDeContratacion tipoDeContratacion18 = new TipoDeContratacion();
        tipoDeContratacion18.setIdTipoContratacion(18);
        tipoDeContratacion18.setTipo("BECA PROFESIONAL");
        tiposDeContratacion.add(tipoDeContratacion1);
        tiposDeContratacion.add(tipoDeContratacion2);
        tiposDeContratacion.add(tipoDeContratacion3);
        tiposDeContratacion.add(tipoDeContratacion4);
        tiposDeContratacion.add(tipoDeContratacion5);
        tiposDeContratacion.add(tipoDeContratacion6);
        tiposDeContratacion.add(tipoDeContratacion7);
        tiposDeContratacion.add(tipoDeContratacion8);
        tiposDeContratacion.add(tipoDeContratacion9);
        tiposDeContratacion.add(tipoDeContratacion10);
        tiposDeContratacion.add(tipoDeContratacion11);
        tiposDeContratacion.add(tipoDeContratacion12);
        tiposDeContratacion.add(tipoDeContratacion13);
        tiposDeContratacion.add(tipoDeContratacion14);
        tiposDeContratacion.add(tipoDeContratacion15);
        tiposDeContratacion.add(tipoDeContratacion16);
        tiposDeContratacion.add(tipoDeContratacion17);
        tiposDeContratacion.add(tipoDeContratacion18);
        DAOTipoDeContratacionImplementacion daoPrueba = new DAOTipoDeContratacionImplementacion();
        List<TipoDeContratacion> tiposDeContratacionObtenidos = daoPrueba.ObtenerTiposDeContratacion();
        assertEquals(tiposDeContratacion,tiposDeContratacionObtenidos);
    }
}
