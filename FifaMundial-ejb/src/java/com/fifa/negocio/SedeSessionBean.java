/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;


import com.fifa.datos.Ronda;
import com.fifa.datos.Sede;
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
public class SedeSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    
    
    
       
    public boolean agregarSede ( String nombre) {
        try {
            Sede s = new Sede ();
            s.setNombre(nombre);
          
          
            em.persist(s);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarSede (int idSede , String nombre) {
        try {
            Sede  s = em.find(Sede.class, idSede);
            s.setNombre(nombre);
                   
            em.merge(s);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarSede(int idSede) {
        try {
            em.getEntityManagerFactory().getCache().evict(Sede.class);
            Sede s = em.find(Sede.class, idSede);
            em.remove(s); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Sede> obtenerSede()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Sede.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Sede> obtenerSedeNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Sede.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Ronda> obtenerSedeId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Sede.findByIdJugador");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    
    
    public void persist(Object object) {
        em.persist(object);
    }

}
