package logicaDeNegocio.Clases;

import java.util.regex.Pattern;


public class TipoDeContratacion {
        
    private String tipoDeContratacion;
    private int idTipoContratacion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";

    public TipoDeContratacion(){

    }
    
    public TipoDeContratacion(int idTipoContratacion,String tipoDeContratacion){
        this.idTipoContratacion=idTipoContratacion;
        this.tipoDeContratacion=tipoDeContratacion;
    }
    
    public String getTipo() {
        return tipoDeContratacion;
    }

    public void setTipo(String tipoDeContratacion)throws IllegalArgumentException {
        if(tipoDeContratacion!=null&&!tipoDeContratacion.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, tipoDeContratacion.trim())&&tipoDeContratacion.trim().length()<=150){
            this.tipoDeContratacion = tipoDeContratacion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idTipoContratacion))){
            this.idTipoContratacion = idTipoContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof TipoDeContratacion)){
            return false;
        }
        TipoDeContratacion tipoDeContratacionTemporal=(TipoDeContratacion)obj;
        if(tipoDeContratacion==null||tipoDeContratacionTemporal.getTipo()==null){
            return false;
        }
        return tipoDeContratacion.equals(tipoDeContratacionTemporal.getTipo())&&
                idTipoContratacion==tipoDeContratacionTemporal.getIdTipoContratacion();
    }
    
    @Override
    public String toString(){
        return tipoDeContratacion;        
    }
}
