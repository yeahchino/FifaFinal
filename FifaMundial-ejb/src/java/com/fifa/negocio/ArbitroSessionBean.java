/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Arbitro;
import static com.fifa.datos.Arbitro_.idArbitro;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class ArbitroSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    public boolean agregarArbitro(String nombre, String apellido, int dni) {
        try {
            Arbitro a = new Arbitro();
            a.setNombre(nombre);
            a.setApellido(apellido);
            a.setDni(dni);
            em.persist(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean modificarArbitro(int idArbitro, String nombre, String apellido, int dni) {
        try {
            Arbitro a = em.find(Arbitro.class, idArbitro);
            a.setNombre(nombre);
            a.setApellido(apellido);
            a.setDni(dni);
            em.merge(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean borrarArbitro(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Arbitro.class);
            Arbitro a = em.find(Arbitro.class, idArbitro);
            em.remove(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Arbitro> obtenerArbitro()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Arbitro> obtenerArbitroId ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findByIdUsuario");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Arbitro> obtenerArbitroNombre ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
     public List<Arbitro> obtenerArbitroApellido ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findByApellido");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
     
      public List<Arbitro> obtenerArbitroDni ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findByDni");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
          
     
}