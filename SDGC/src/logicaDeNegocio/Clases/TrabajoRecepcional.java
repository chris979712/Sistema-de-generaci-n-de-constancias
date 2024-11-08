package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class TrabajoRecepcional {
    
    private int idTrabajoRecepcional;
    private String resultadoObtenido;
    private String tituloDeTrabajo;
    private String fechaDePresentacion;
    private int Profesor_Profesor;
    private int PeriodoEscolar_idPeriodoEscolar;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
    private static final String FECHA_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";    

    public TrabajoRecepcional(int idTrabajoRecepcional, String trabajoRecepcional, String resultadoObtenido, String tituloDeTrabajo, String fechaDePresentacion, int Profesor_Profesor, int PeriodoEscolar_idPeriodoEscolar) {
        this.idTrabajoRecepcional = idTrabajoRecepcional;
        this.resultadoObtenido = resultadoObtenido;
        this.tituloDeTrabajo = tituloDeTrabajo;
        this.fechaDePresentacion = fechaDePresentacion;
        this.Profesor_Profesor = Profesor_Profesor;
        this.PeriodoEscolar_idPeriodoEscolar = PeriodoEscolar_idPeriodoEscolar;
    }
    
    public TrabajoRecepcional(){
        
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

    public int getProfesor_Profesor() {
        return Profesor_Profesor;
    }

    public void setProfesor_Profesor(int Profesor_Profesor) {
       if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(Profesor_Profesor))){
            this.Profesor_Profesor = Profesor_Profesor;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getPeriodoEscolar_idPeriodoEscolar() {
        return PeriodoEscolar_idPeriodoEscolar;
    }

    public void setPeriodoEscolar_idPeriodoEscolar(int PeriodoEscolar_idPeriodoEscolar) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(PeriodoEscolar_idPeriodoEscolar))){
            this.PeriodoEscolar_idPeriodoEscolar = PeriodoEscolar_idPeriodoEscolar;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    
            
}
