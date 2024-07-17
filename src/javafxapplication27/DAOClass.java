
package javafxapplication27;

import java.lang.reflect.Method;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class DAOClass {
   
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("JavaFXApplication27PU");
    
    // DOCENTE
    public void insertDocente(Docente docente) throws Exception {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            em.persist(docente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("No se pudo insertar el docente: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public Docente buscarDocente(int codigo) throws Exception {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Docente docente = em.find(Docente.class, codigo);
            return docente;
        } catch (Exception e) {
            throw new Exception("No se puede encontrar al docente: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Docente> getAllDocentes() throws Exception {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            List<Docente> docentes = em.createQuery("SELECT d FROM Docente d", Docente.class)
                    .getResultList();
            return docentes;
        } catch (Exception e) {
            throw new Exception("No se pudieron encontrar los docentes: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void eliminarDocente(int codigo) throws Exception {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            Docente docente = em.find(Docente.class, codigo);
            if (docente != null) {
                em.getTransaction().begin();
                em.remove(docente);
                em.getTransaction().commit();
            } else {
                throw new Exception("No se encontró el docente con el código: " + codigo);
            }
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("No se pudo eliminar el docente: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
   
    public void actualizarDocente(Docente docente) throws Exception {
        EntityManager em = null;
        try {
            em = emf.createEntityManager();
            em.getTransaction().begin();
            Docente docenteMerged = em.merge(docente);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new Exception("No se pudo actualizar el docente: " + e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    // Método para cerrar el EntityManagerFactory
    public void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
    
    
}
