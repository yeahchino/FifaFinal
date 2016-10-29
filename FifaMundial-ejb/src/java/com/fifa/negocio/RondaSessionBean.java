/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Jugador;
import com.fifa.datos.Organizador;
import com.fifa.datos.Ronda;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Adriana
 */
@Stateless
@LocalBean
public class RondaSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

       
    public boolean agregarRonda ( String nombre) {
        try {
            Ronda r = new Ronda();
            r.setNombre(nombre);
          
          
            em.persist(r);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarRonda(int idRonda, String nombre) {
        try {
            Ronda r = em.find(Ronda.class, idRonda);
            r.setNombre(nombre);
                   
            em.merge(r);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarRonda(int idRonda) {
        try {
            em.getEntityManagerFactory().getCache().evict(Ronda.class);
            Ronda r = em.find(Ronda.class, idRonda);
            em.remove(r); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Ronda> obtenerRonda()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Ronda.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Ronda> obtenerRondaNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Ronda.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Ronda> obtenerRondaId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Ronda.findByIdJugador");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    
    
    
    
    public void persist(Object object) {
        em.persist(object);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
