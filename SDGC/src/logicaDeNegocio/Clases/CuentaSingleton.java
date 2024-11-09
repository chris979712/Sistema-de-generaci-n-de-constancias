package logicaDeNegocio.Clases;

public final class CuentaSingleton {

    private static CuentaSingleton instancia;
    private int idUsuario;
    private String nombreUsuario;
    private String contrasenia;
    private String tipoDeUsuario;

    private CuentaSingleton(Cuenta usuario) {
        setIdUsuario(usuario.getIdUsuario());
        setNombreUsuario(usuario.getNombreUsuario());
        setContrasenia(usuario.getContrasenia());
        setTipoDeUsuario(usuario.getTipoDeUsuario());
    }

    public static CuentaSingleton getInstancia(Cuenta usuario) {
        if (instancia == null) {
            instancia = new CuentaSingleton(usuario);
        }
        return instancia;
    }

    private void setIdUsuario(int idUsuario) throws IllegalArgumentException {
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    private void setNombreUsuario(String nombreUsuario) throws IllegalArgumentException {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    private void setContrasenia(String contrasenia) throws IllegalArgumentException {
        this.contrasenia = contrasenia;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    private void setTipoDeUsuario(String tipoDeUsuario) throws IllegalArgumentException {
        this.tipoDeUsuario = tipoDeUsuario;
    }

    public String getTipoDeUsuario() {
        return tipoDeUsuario;
    }

    public static CuentaSingleton getInstancia() {
        return CuentaSingleton.instancia;
    }

    public static void resetSingleton() {
        CuentaSingleton.instancia = null;
    }

}

