
package javafxapplication27;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "DOCENTE")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDDOCENTE")
    private int IdDocente;
    @Column(name = "NOMBREDOCENTE")
    private String nombreDocente;
    @Column(name = "APELLIDODOCENTE")
    private String apellidoDocente;
    @Column(name = "DNIDOCENTE")
    private int dniDocente;
    @Column(name = "fechanacimientodocente")
    private LocalDate fechaNacimientoDocente;
    @Column(name = "DIRECCIONDOCENTE")
    private String direccionDocente;
    @Column(name = "LEGAJODOCENTE")
    private int legajoDocente;
    @ManyToOne
    private Instituto instituto;

    public Docente() {
    }

    public Docente(String nombreDocente, String apellidoDocente, int dniDocente, LocalDate fechaNacimientoDocente, String direccionDocente, int legajoDocente, Instituto instituto) {
        this.nombreDocente = nombreDocente;
        this.apellidoDocente = apellidoDocente;
        this.dniDocente = dniDocente;
        this.fechaNacimientoDocente = fechaNacimientoDocente;
        this.direccionDocente = direccionDocente;
        this.legajoDocente = legajoDocente;
        this.instituto = instituto;
    }

    

    public Instituto getInstituto() {
        return instituto;
    }

    public void setInstituto(Instituto instituto) {
        this.instituto = instituto;
    }

    public int getIdDocente() {
        return IdDocente;
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

    public LocalDate getFechaNacimientoDocente() {
        return fechaNacimientoDocente;
    }

    public void setFechaNacimientoDocente(LocalDate fechaNacimientoDocente) {
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
