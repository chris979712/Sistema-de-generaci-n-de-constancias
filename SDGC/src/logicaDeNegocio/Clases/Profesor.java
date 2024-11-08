package logicaDeNegocio.Clases;

import java.util.regex.Pattern;


public class Profesor {
    
    private int idProfesor;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String correoInstitucional;
    private String numeroDePersonal;
    private int Cuenta_idCuenta;
    private int CategoriaDeContracion_idCategoriaDeContratacion;
    private int TipoDeContratacion_idTipoDeContratacion;
    private static final String SOLO_LETRAS_PATTERN = "^[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+(?:\\s[\\p{L}\\sáéíóúÁÉÍÓÚüÜ'-]+)*$";
    private static final String SOLO_NUMEROS_PATTERN = "\\d+";
     private static final String EMAIL_PATTERN = "^[a-zA-Z0-9]+([._][a-zA-Z0-9]+)*@[a-zA-Z0-9]+(\\.[a-zA-Z0-9]+)*\\.[a-zA-Z]{2,}$";
     
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(idProfesor))){
            this.idProfesor = idProfesor;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if(nombre!=null&&!nombre.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, nombre.trim())&&nombre.trim().length()<=150){
            this.nombre = nombre.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        if(primerApellido!=null&&!primerApellido.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, primerApellido.trim())&&primerApellido.trim().length()<=150){
            this.primerApellido = primerApellido.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        if(segundoApellido!=null&&!segundoApellido.isEmpty()&&Pattern.matches(SOLO_LETRAS_PATTERN, segundoApellido.trim())&&segundoApellido.trim().length()<=150){
            this.segundoApellido = segundoApellido.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        if(correoInstitucional!=null&&!correoInstitucional.isEmpty()&&Pattern.matches(EMAIL_PATTERN, correoInstitucional.trim())&&correoInstitucional.trim().length()<=150){
            this.correoInstitucional = correoInstitucional.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }

    }

    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        if(numeroDePersonal!=null&&!numeroDePersonal.isEmpty()&&Pattern.matches(SOLO_NUMEROS_PATTERN, numeroDePersonal.trim())&&numeroDePersonal.trim().length()<=150){
            this.numeroDePersonal = numeroDePersonal.trim().replaceAll("\\s+", " ");
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getCuenta_idCuenta() {
        return Cuenta_idCuenta;
    }

    public void setCuenta_idCuenta(int Cuenta_idCuenta) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(Cuenta_idCuenta))){
            this.Cuenta_idCuenta = Cuenta_idCuenta;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getCategoriaDeContracion_idCategoriaDeContratacion() {
        return CategoriaDeContracion_idCategoriaDeContratacion;
    }

    public void setCategoriaDeContracion_idCategoriaDeContratacion(int CategoriaDeContracion_idCategoriaDeContratacion) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(CategoriaDeContracion_idCategoriaDeContratacion))){
            this.CategoriaDeContracion_idCategoriaDeContratacion = CategoriaDeContracion_idCategoriaDeContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }

    public int getTipoDeContratacion_idTipoDeContratacion() {
        return TipoDeContratacion_idTipoDeContratacion;
    }

    public void setTipoDeContratacion_idTipoDeContratacion(int TipoDeContratacion_idTipoDeContratacion) {
        if(Pattern.matches(SOLO_NUMEROS_PATTERN, String.valueOf(TipoDeContratacion_idTipoDeContratacion))){
            this.TipoDeContratacion_idTipoDeContratacion = TipoDeContratacion_idTipoDeContratacion;
        }else{
            throw new IllegalArgumentException();
        }
    }
    
    
}
