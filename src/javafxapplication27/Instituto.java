
package javafxapplication27;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Instituto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstituto;
    private String nombreInstituto;
    @OneToMany(mappedBy="instituto")
    private List<Docente> listaDocentes = new ArrayList<Docente>();

    public Instituto() {
    }

    public Instituto(String nombreInstituto) {
        this.nombreInstituto = nombreInstituto;
    }

    public int getIdInstituto() {
        return idInstituto;
    }

    public void setIdInstituto(int idInstituto) {
        this.idInstituto = idInstituto;
    }

    public String getNombreInstituto() {
        return nombreInstituto;
    }

    public void setNombreInstituto(String nombreInstituto) {
        this.nombreInstituto = nombreInstituto;
    }

    public List<Docente> getListaDocentes() {
        return listaDocentes;
    }

    public void setListaDocentes(List<Docente> listaDocentes) {
        this.listaDocentes = listaDocentes;
    }
    
    
}
