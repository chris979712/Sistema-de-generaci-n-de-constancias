package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logicaDeNegocio.Clases.TipoDeContratacion;
import logicaDeNegocio.Interfaces.TipoDeContratacionInterface;
import logicaDeNegocio.Utilidades.Constantes;

public class DAOTipoDeContratacionImplementacion implements TipoDeContratacionInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    
    @Override
    public List<TipoDeContratacion> ObtenerTiposDeContratacion() {
        List<TipoDeContratacion> tiposDeContratacion = new ArrayList<TipoDeContratacion>();
        TipoDeContratacion tipoDeContratacionFallida = new TipoDeContratacion();
        tipoDeContratacionFallida.setIdTipoContratacion(Constantes.OPERACION_FALLIDA);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("select * from TipoDeContratacion")){
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    TipoDeContratacion tipoDeContratacionObtenida = new TipoDeContratacion();
                    tipoDeContratacionObtenida.setIdTipoContratacion(resultado.getInt("idTipoDeContratacion"));
                    tipoDeContratacionObtenida.setTipo("tipoDeContratacion");
                    tiposDeContratacion.add(tipoDeContratacionObtenida);
                }
            }
        }catch(SQLException sqlException){
            tiposDeContratacion.add(0, tipoDeContratacionFallida);
        }
        return tiposDeContratacion;
    }
    
}