package logicaDeNegocio.Clases;

import java.util.regex.Pattern;


public class Alumno {
   private String nombreCompleto;
    private int idAlumno;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public Alumno(){

    }
    
    public Alumno(int idAlumno,String nombreCompleto){
        this.idAlumno=idAlumno;
        this.nombreCompleto=nombreCompleto;
    }
    
    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto)throws IllegalArgumentException {
        if(nombreCompleto!=null&&!nombreCompleto.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, nombreCompleto.trim())&&nombreCompleto.trim().length()<=150){
            this.nombreCompleto = nombreCompleto.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno)throws IllegalArgumentException {
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
        if(nombreCompleto==null||nombreCompletoTemporal.getNombreCompleto()==null){
            return false;
        }
        return nombreCompleto.equals(nombreCompletoTemporal.getNombreCompleto())&&
                idAlumno==nombreCompletoTemporal.getIdAlumno();
    }
    
    @Override
    public String toString(){
        return nombreCompleto;        
    } 
}
