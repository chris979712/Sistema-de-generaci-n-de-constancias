package logicaDeNegocio.Clases;
import java.time.LocalDate;


public class AuxiliarPeriodoEscolar {
    private String nombre;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public AuxiliarPeriodoEscolar(String nombre, LocalDate fechaInicio, LocalDate fechaFin) {
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public boolean fechaDentroDelPeriodo(LocalDate fecha) {
        return (fecha.isEqual(fechaInicio) || fecha.isAfter(fechaInicio)) && 
               (fecha.isEqual(fechaFin) || fecha.isBefore(fechaFin));
    }
    
}
