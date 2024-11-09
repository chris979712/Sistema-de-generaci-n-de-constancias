package logicaDeNegocio.Clases;

import java.util.regex.Pattern;

public class CategoriaDeContratacion {
    private String tipoCategoriaDeContratacion;
    private int idCategoriaDeContratacion;
    private static final String SOLO_LETRAS_Y_NUMEROS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-\\.\\d]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-\\.\\d]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "-?\\d+";


    public CategoriaDeContratacion(){

    }
    
    public CategoriaDeContratacion(int idCategoriaDeContratacion,String tipoCategoriaDeContratacion){
        this.idCategoriaDeContratacion=idCategoriaDeContratacion;
        this.tipoCategoriaDeContratacion=tipoCategoriaDeContratacion;
    }
    
    public String getTipo() {
        return tipoCategoriaDeContratacion;
    }

    public void setTipo(String tipoCategoriaDeContratacion)throws IllegalArgumentException {
        if(tipoCategoriaDeContratacion!=null&&!tipoCategoriaDeContratacion.isEmpty()&&Pattern.matches(SOLO_LETRAS_Y_NUMEROS_PATTERN, tipoCategoriaDeContratacion.trim())&&tipoCategoriaDeContratacion.trim().length()<=150){
            this.tipoCategoriaDeContratacion = tipoCategoriaDeContratacion.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }    

    public int getIdCategoriaDeContratacion() {
        return idCategoriaDeContratacion;
    }

    public void setIdCategoriaDeContratacion(int idCategoriaDeContratacion)throws IllegalArgumentException {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idCategoriaDeContratacion))){
            this.idCategoriaDeContratacion = idCategoriaDeContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof CategoriaDeContratacion)){
            return false;
        }
        CategoriaDeContratacion tipoCategoriaDeContratacionTemporal=(CategoriaDeContratacion)obj;
        if(tipoCategoriaDeContratacion==null||tipoCategoriaDeContratacionTemporal.getTipo()==null){
            return false;
        }
        return tipoCategoriaDeContratacion.equals(tipoCategoriaDeContratacionTemporal.getTipo())&&
                idCategoriaDeContratacion==tipoCategoriaDeContratacionTemporal.getIdCategoriaDeContratacion();
    }
    
    @Override
    public String toString(){
        return tipoCategoriaDeContratacion;        
    }
}
