package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logicaDeNegocio.Clases.CategoriaDeContratacion;
import logicaDeNegocio.Interfaces.CategoriaDeContratacionInterface;
import logicaDeNegocio.Utilidades.Constantes;


public class DAOCategoriaDeContratacionImplementacion implements CategoriaDeContratacionInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    
    @Override
    public List<CategoriaDeContratacion> ObtenerCategoriasDeContratacion() {
        List<CategoriaDeContratacion> categoriasObtenidas = new ArrayList<CategoriaDeContratacion>();
        CategoriaDeContratacion categoriaFallida = new CategoriaDeContratacion();
        categoriaFallida.setIdCategoriaDeContratacion(Constantes.OPERACION_FALLIDA);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from CategoriaDeContratacion")){
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    CategoriaDeContratacion categoriaObtenida = new CategoriaDeContratacion();
                    categoriaObtenida.setIdCategoriaDeContratacion(resultado.getInt("idCategoriaDeContratacion"));
                    categoriaObtenida.setTipo(resultado.getString("tipoCategoriaDeContratación"));
                    categoriasObtenidas.add(categoriaObtenida);
                }
            }
        }catch(SQLException sqlException){
            categoriasObtenidas.set(0, categoriaFallida);
        }
        return categoriasObtenidas;
    }
    
}