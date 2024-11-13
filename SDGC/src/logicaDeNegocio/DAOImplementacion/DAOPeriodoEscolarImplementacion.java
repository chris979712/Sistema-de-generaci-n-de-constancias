package logicaDeNegocio.DAOImplementacion;

import accesoADatos.ManejadorBaseDeDatos;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Interfaces.PeriodoEscolarInterface;
import logicaDeNegocio.Utilidades.Constantes;
import org.apache.log4j.LogManager;
import org.apache.log4j.Priority;


public class DAOPeriodoEscolarImplementacion implements PeriodoEscolarInterface{
    
    private static final ManejadorBaseDeDatos BASE_DE_DATOS = new ManejadorBaseDeDatos();
    private static final org.apache.log4j.Logger logger = LogManager.getLogger(DAOPeriodoEscolarImplementacion.class); 
    
    @Override
    public List<PeriodoEscolar> ObtenerPeriodosEscolares() {
        List<PeriodoEscolar> periodoEscolarObtenido = new ArrayList<PeriodoEscolar>();
        PeriodoEscolar periodoFallido = new PeriodoEscolar();
        periodoFallido.setIdPeriodoEscolar(Constantes.OPERACION_FALLIDA);
        try(Connection conexion = BASE_DE_DATOS.conectarBaseDeDatos();
            PreparedStatement sentencia = conexion.prepareStatement("Select * from PeriodoEscolar")){
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.isBeforeFirst()){
                while(resultado.next()){
                    PeriodoEscolar periodoObtenido = new PeriodoEscolar();
                    periodoObtenido.setPeriodoEscolar(resultado.getString("periodoEscolar"));
                    periodoObtenido.setIdPeriodoEscolar(resultado.getInt("idPeriodoEscolar"));
                    periodoEscolarObtenido.add(periodoObtenido);
                }
            }
        }catch(SQLException sqlException){
            logger.log(Priority.ERROR, sqlException);
            periodoEscolarObtenido.set(0, periodoFallido);
        }
        return periodoEscolarObtenido;
    }
    
}
