package logicaDeNegocio.Interfaces;

import logicaDeNegocio.Clases.Alumno;

public interface AlumnoImplementacion {
    
    public Alumno ObtenerAlumnoPorNombreCompleto(String nomnre);
    
    public int RegistrarAlumno(Alumno alumno);
    
    public int VerificarExistenciaDeAlumno(String nombre);
}
