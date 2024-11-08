package logicaDeNegocio.Interfaces;

import logicaDeNegocio.Clases.Cuenta;

public interface CuentaInterface {
    
    public int ValidarCredenciales(Cuenta cuenta, Cuenta logger);
    
    public int RegistrarUsuario(Cuenta cuenta);
    
    public String ObtenerTipoDeUsuario(Cuenta cuenta, Cuenta logger);
    
    public boolean ConfirmarConexionBaseDeDatos();
    
    public boolean confirmarConexionDeInicioDeSesion(Cuenta logger);
    
}
