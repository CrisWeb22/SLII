
package javafxapplication27;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Docente {
    @Id
    private int IdDocente;
    private String nombreDocente;
    private String apellidoDocente;
    private int dniDocente;
    private String fechaNacimientoDocente;
    private String direccionDocente;
    private int legajoDocente;

    public Docente() {
    }

    public Docente(int IdDocente, String nombreDocente, String apellidoDocente, int dniDocente, String fechaNacimientoDocente, String direccionDocente, int legajoDocente) {
        this.IdDocente = IdDocente;
        this.nombreDocente = nombreDocente;
        this.apellidoDocente = apellidoDocente;
        this.dniDocente = dniDocente;
        this.fechaNacimientoDocente = fechaNacimientoDocente;
        this.direccionDocente = direccionDocente;
        this.legajoDocente = legajoDocente;
    }

    public int getIdDocente() {
        return IdDocente;
    }

    public void setIdDocente(int IdDocente) {
        this.IdDocente = IdDocente;
    }

    public String getNombreDocente() {
        return nombreDocente;
    }

    public void setNombreDocente(String nombreDocente) {
        this.nombreDocente = nombreDocente;
    }

    public String getApellidoDocente() {
        return apellidoDocente;
    }

    public void setApellidoDocente(String apellidoDocente) {
        this.apellidoDocente = apellidoDocente;
    }

    public int getDniDocente() {
        return dniDocente;
    }

    public void setDniDocente(int dniDocente) {
        this.dniDocente = dniDocente;
    }

    public String getFechaNacimientoDocente() {
        return fechaNacimientoDocente;
    }

    public void setFechaNacimientoDocente(String fechaNacimientoDocente) {
        this.fechaNacimientoDocente = fechaNacimientoDocente;
    }

    public String getDireccionDocente() {
        return direccionDocente;
    }

    public void setDireccionDocente(String direccionDocente) {
        this.direccionDocente = direccionDocente;
    }

    public int getLegajoDocente() {
        return legajoDocente;
    }

    public void setLegajoDocente(int legajoDocente) {
        this.legajoDocente = legajoDocente;
    }
    
    
}
