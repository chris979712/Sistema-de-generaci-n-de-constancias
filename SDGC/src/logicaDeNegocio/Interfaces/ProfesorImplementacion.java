package logicaDeNegocio.Interfaces;

import java.util.List;
import logicaDeNegocio.Clases.Profesor;

public interface ProfesorImplementacion {
    
    public int RegistrarProfesor(Profesor profesor, String contrasenia);
    
    public int VerificarDuplicidadDeProfesor(Profesor profesor);
    
    public Profesor ObtenerProfesorPorCorreo(String correo);
    
    public List<Profesor> ObtenerProfesores();
}
