package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class PeriodoEscolar {
     private String periodoEscolar;
    private int idPeriodoEscolar;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public PeriodoEscolar(){

    }
    
    public PeriodoEscolar(int idPeriodoEscolar,String periodoEscolar){
        this.idPeriodoEscolar=idPeriodoEscolar;
        this.periodoEscolar=periodoEscolar;
    }
    
    public String getTipo() {
        return periodoEscolar;
    }

    public void setTipo(String periodoEscolar)throws IllegalArgumentException {
        if(periodoEscolar!=null&&!periodoEscolar.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, periodoEscolar.trim())&&periodoEscolar.trim().length()<=150){
            this.periodoEscolar = periodoEscolar.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdTipoColaboracion() {
        return idPeriodoEscolar;
    }

    public void setIdTipoColaboracion(int idPeriodoEscolar)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idPeriodoEscolar))){
            this.idPeriodoEscolar = idPeriodoEscolar;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PeriodoEscolar)){
            return false;
        }
        PeriodoEscolar periodoEscolarTemporal=(PeriodoEscolar)obj;
        if(periodoEscolar==null||periodoEscolarTemporal.getTipo()==null){
            return false;
        }
        return periodoEscolar.equals(periodoEscolarTemporal.getTipo())&&
                idPeriodoEscolar==periodoEscolarTemporal.getIdTipoColaboracion();
    }
    
    @Override
    public String toString(){
        return periodoEscolar;        
    }
}
