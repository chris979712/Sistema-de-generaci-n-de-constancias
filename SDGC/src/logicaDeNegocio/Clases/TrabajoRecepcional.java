package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class TrabajoRecepcional {
    
    private int idTrabajoRecepcional;
    private String modalidad;
    private String resultadoObtenido;
    private String tituloDeTrabajo;
    private String fechaDePresentacion;
    private Profesor profesor;
    private PeriodoEscolar periodoEscolar;
    private Alumno alumno;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";    


    
    public TrabajoRecepcional(){
        
    }

    public TrabajoRecepcional(int idTrabajoRecepcional, String resultadoObtenido, String tituloDeTrabajo, String fechaDePresentacion, Profesor profesor, PeriodoEscolar periodoEscolar, Alumno alumno, String modalidad) {
        this.idTrabajoRecepcional = idTrabajoRecepcional;
        this.resultadoObtenido = resultadoObtenido;
        this.tituloDeTrabajo = tituloDeTrabajo;
        this.fechaDePresentacion = fechaDePresentacion;
        this.profesor = profesor;
        this.periodoEscolar = periodoEscolar;
        this.alumno = alumno;
        this.modalidad = modalidad;
    }
    
    public int getIdTrabajoRecepcional() {
        return idTrabajoRecepcional;
    }

    public void setIdTrabajoRecepcional(int idTrabajoRecepcional) {
       if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTrabajoRecepcional))){
            this.idTrabajoRecepcional = idTrabajoRecepcional;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getResultadoObtenido() {
        return resultadoObtenido;
    }

    public void setResultadoObtenido(String resultadoObtenido) {
        if(resultadoObtenido!=null&&!resultadoObtenido.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, resultadoObtenido.trim())&&resultadoObtenido.trim().length()<=150){
            this.resultadoObtenido = resultadoObtenido.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getTituloDeTrabajo() {
        return tituloDeTrabajo;
    }

    public void setTituloDeTrabajo(String tituloDeTrabajo) {
       if(tituloDeTrabajo!=null&&!tituloDeTrabajo.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, tituloDeTrabajo.trim())&&tituloDeTrabajo.trim().length()<=150){
            this.tituloDeTrabajo = tituloDeTrabajo.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getFechaDePresentacion() {
        return fechaDePresentacion;
    }

    public void setFechaDePresentacion(String fechaDePresentacion) {
        if(fechaDePresentacion!=null&&!fechaDePresentacion.isEmpty()&&Pattern.matches(FECHA_PATTERN, fechaDePresentacion.trim())&&fechaDePresentacion.trim().length()<=150){
            this.fechaDePresentacion = fechaDePresentacion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        if(modalidad!=null&&!modalidad.isEmpty()&&Pattern.matches(FECHA_PATTERN, modalidad.trim())&&modalidad.trim().length()<=150){
            this.modalidad = modalidad.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public PeriodoEscolar getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(PeriodoEscolar periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    
    
    
            
}
