package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.PeriodoEscolar;
import logicaDeNegocio.Clases.Profesor;
import logicaDeNegocio.Clases.TrabajoRecepcional;



public interface TrabajoRecepcionalInterface {
    
    public int RegistrarTrabajoRecepcional(TrabajoRecepcional trabajo);
    
    public int VerificarParticipacionDeProfesorEnTrabajoRecepcionalEnPeriodoEscolar(Profesor profesor, PeriodoEscolar periodo);
    
    public List<TrabajoRecepcional> ObtenerTrabajosRecepcionalesDeProfesorEnPeriodoEscolar(Profesor profesor, PeriodoEscolar periodo);
    
    public int VerificarDuplicidadEnTrabajoRecepcional(TrabajoRecepcional trabajo);
    
}
