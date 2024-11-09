package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.DAOImplementacion.DAOPeriodoEscolarImplementacion;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

public class PruebaDAOPeriodoEscolarImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    } 
    
    @Test
    public void PruebaObtenerPeriodosEscolaresExitosa(){
        List<PeriodoEscolar> periodosEsperados = new ArrayList<PeriodoEscolar>();
        PeriodoEscolar periodoEscolar1 = new PeriodoEscolar();
        periodoEscolar1.setTipo("AGO2022-ENE2023");
        periodoEscolar1.setIdPeriodoEscolar(1);
        PeriodoEscolar periodoEscolar2 = new PeriodoEscolar();
        periodoEscolar2.setTipo("FEB2023-JUL2023");
        periodoEscolar2.setIdPeriodoEscolar(2);
        PeriodoEscolar periodoEscolar3 = new PeriodoEscolar();
        periodoEscolar3.setTipo("AGO2023-ENE2024");
        periodoEscolar3.setIdPeriodoEscolar(3);
        PeriodoEscolar periodoEscolar4 = new PeriodoEscolar();
        periodoEscolar4.setTipo("FEB2024-JUL2024");
        periodoEscolar4.setIdPeriodoEscolar(4);
        periodosEsperados.add(periodoEscolar1);
        periodosEsperados.add(periodoEscolar2);
        periodosEsperados.add(periodoEscolar3);
        periodosEsperados.add(periodoEscolar4);
        DAOPeriodoEscolarImplementacion daoPeriodo = new DAOPeriodoEscolarImplementacion();
        List<PeriodoEscolar> periodosObtenidos = daoPeriodo.ObtenerPeriodosEscolares();
        assertEquals(periodosEsperados,periodosObtenidos);
    }
}
