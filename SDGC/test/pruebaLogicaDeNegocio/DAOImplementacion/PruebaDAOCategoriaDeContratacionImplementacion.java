package pruebaLogicaDeNegocio.DAOImplementacion;

import java.util.ArrayList;
import java.util.List;
import logicaDeNegocio.Clases.CategoriaDeContratacion;
import logicaDeNegocio.Clases.Cuenta;
import logicaDeNegocio.Clases.CuentaSingleton;
import logicaDeNegocio.DAOImplementacion.DAOCategoriaDeContratacionImplementacion;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;


public class PruebaDAOCategoriaDeContratacionImplementacion {
    
    @BeforeClass
    public static void inicializar() {
        Cuenta usuarioPrueba = new Cuenta();
        usuarioPrueba.setNombreUsuario("cuentapruebauno@gmail.com");
        usuarioPrueba.setContrasenia("Contrasenia123*");
        usuarioPrueba.setTipoDeUsuario("Administrativo");
        CuentaSingleton.getInstancia(usuarioPrueba);
    }
    
    @Test
    public void PruebaObtenerCategoriasDeContratacion(){
        List<CategoriaDeContratacion> categoriasDeContratacion = new ArrayList<CategoriaDeContratacion>();
        CategoriaDeContratacion categoriaDeContratacion1 = new CategoriaDeContratacion();
        categoriaDeContratacion1.setIdCategoriaDeContratacion(1);
        categoriaDeContratacion1.setTipo("Docente T.C.");

        CategoriaDeContratacion categoriaDeContratacion2 = new CategoriaDeContratacion();
        categoriaDeContratacion2.setIdCategoriaDeContratacion(2);
        categoriaDeContratacion2.setTipo("Investigador T.C.");

        CategoriaDeContratacion categoriaDeContratacion3 = new CategoriaDeContratacion();
        categoriaDeContratacion3.setIdCategoriaDeContratacion(3);
        categoriaDeContratacion3.setTipo("Docente Investigador T.C.");

        CategoriaDeContratacion categoriaDeContratacion4 = new CategoriaDeContratacion();
        categoriaDeContratacion4.setIdCategoriaDeContratacion(4);
        categoriaDeContratacion4.setTipo("Ejecutante T.C.");

        CategoriaDeContratacion categoriaDeContratacion5 = new CategoriaDeContratacion();
        categoriaDeContratacion5.setIdCategoriaDeContratacion(5);
        categoriaDeContratacion5.setTipo("Técnico Académico T.C.");

        CategoriaDeContratacion categoriaDeContratacion6 = new CategoriaDeContratacion();
        categoriaDeContratacion6.setIdCategoriaDeContratacion(6);
        categoriaDeContratacion6.setTipo("Docente M.T.");

        CategoriaDeContratacion categoriaDeContratacion7 = new CategoriaDeContratacion();
        categoriaDeContratacion7.setIdCategoriaDeContratacion(7);
        categoriaDeContratacion7.setTipo("Investigador M.T.");

        CategoriaDeContratacion categoriaDeContratacion8 = new CategoriaDeContratacion();
        categoriaDeContratacion8.setIdCategoriaDeContratacion(8);
        categoriaDeContratacion8.setTipo("Docente - Investigador M.T.");

        CategoriaDeContratacion categoriaDeContratacion9 = new CategoriaDeContratacion();
        categoriaDeContratacion9.setIdCategoriaDeContratacion(9);
        categoriaDeContratacion9.setTipo("Ejecutante M.T.");

        CategoriaDeContratacion categoriaDeContratacion10 = new CategoriaDeContratacion();
        categoriaDeContratacion10.setIdCategoriaDeContratacion(10);
        categoriaDeContratacion10.setTipo("Técnico Académico M.T.");

        CategoriaDeContratacion categoriaDeContratacion11 = new CategoriaDeContratacion();
        categoriaDeContratacion11.setIdCategoriaDeContratacion(11);
        categoriaDeContratacion11.setTipo("Docente P.A.");

        CategoriaDeContratacion categoriaDeContratacion12 = new CategoriaDeContratacion();
        categoriaDeContratacion12.setIdCategoriaDeContratacion(12);
        categoriaDeContratacion12.setTipo("Académico Instructor (T.C. 40 HRS)");

        CategoriaDeContratacion categoriaDeContratacion13 = new CategoriaDeContratacion();
        categoriaDeContratacion13.setIdCategoriaDeContratacion(13);
        categoriaDeContratacion13.setTipo("Académico Instructor (T.C. 30 HRS)");

        CategoriaDeContratacion categoriaDeContratacion14 = new CategoriaDeContratacion();
        categoriaDeContratacion14.setIdCategoriaDeContratacion(14);
        categoriaDeContratacion14.setTipo("Eventual");

        CategoriaDeContratacion categoriaDeContratacion15 = new CategoriaDeContratacion();
        categoriaDeContratacion15.setIdCategoriaDeContratacion(15);
        categoriaDeContratacion15.setTipo("Otra");categoriasDeContratacion.add(categoriaDeContratacion1);
        categoriasDeContratacion.add(categoriaDeContratacion2);
        categoriasDeContratacion.add(categoriaDeContratacion3);
        categoriasDeContratacion.add(categoriaDeContratacion4);
        categoriasDeContratacion.add(categoriaDeContratacion5);
        categoriasDeContratacion.add(categoriaDeContratacion6);
        categoriasDeContratacion.add(categoriaDeContratacion7);
        categoriasDeContratacion.add(categoriaDeContratacion8);
        categoriasDeContratacion.add(categoriaDeContratacion9);
        categoriasDeContratacion.add(categoriaDeContratacion10);
        categoriasDeContratacion.add(categoriaDeContratacion11);
        categoriasDeContratacion.add(categoriaDeContratacion12);
        categoriasDeContratacion.add(categoriaDeContratacion13);
        categoriasDeContratacion.add(categoriaDeContratacion14);
        categoriasDeContratacion.add(categoriaDeContratacion15);
        DAOCategoriaDeContratacionImplementacion dao = new DAOCategoriaDeContratacionImplementacion();
        List<CategoriaDeContratacion> categoriasDeContratacionObtenidas = dao.ObtenerCategoriasDeContratacion();
        assertEquals(categoriasDeContratacion,categoriasDeContratacionObtenidas);
    }
    
    
}
