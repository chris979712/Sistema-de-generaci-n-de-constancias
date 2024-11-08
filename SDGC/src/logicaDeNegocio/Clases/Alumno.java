package logicaDeNegocio.Clases;

import java.util.regex.Pattern;


public class Alumno {
   private String nombreCompleto;
    private int idAlumno;
    private int idTrabajoRecepcional;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public Alumno(){

    }
    
    public Alumno(int idAlumno,String nombreCompleto){
        this.idAlumno=idAlumno;
        this.nombreCompleto=nombreCompleto;
    }
    
    public int getIdTrabajoRecepcional() {
        return idTrabajoRecepcional;
    }

    public void setIdTrabajoRecepcional(int idTrabajoRecepcional)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTrabajoRecepcional))){
            this.idTrabajoRecepcional = idTrabajoRecepcional;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    public String getTipo() {
        return nombreCompleto;
    }

    public void setTipo(String nombreCompleto)throws IllegalArgumentException {
        if(nombreCompleto!=null&&!nombreCompleto.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreCompleto.trim())&&nombreCompleto.trim().length()<=150){
            this.nombreCompleto = nombreCompleto.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdTipoColaboracion() {
        return idAlumno;
    }

    public void setIdTipoColaboracion(int idAlumno)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idAlumno))){
            this.idAlumno = idAlumno;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Alumno)){
            return false;
        }
        Alumno nombreCompletoTemporal=(Alumno)obj;
        if(nombreCompleto==null||nombreCompletoTemporal.getTipo()==null){
            return false;
        }
        return nombreCompleto.equals(nombreCompletoTemporal.getTipo())&&
                idAlumno==nombreCompletoTemporal.getIdTipoColaboracion();
    }
    
    @Override
    public String toString(){
        return nombreCompleto;        
    } 
}
