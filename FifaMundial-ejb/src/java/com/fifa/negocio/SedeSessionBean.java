/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Pais;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fifa.datos.Sede;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class SedeSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Sede> obtenerSede()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Sede.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Sede> obtenerSedeNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Sede.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Sede obtenerSedeId(int idSede)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Sede.class);
            Sede a = em.find(Sede.class, idSede);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarSede(int idSede) {
        try {
            em.getEntityManagerFactory().getCache().evict(Sede.class);
            Sede p = em.find(Sede.class, idSede);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarSede( String nombre, int idPais) {
        try {
              Pais paisSel = em.find(Pais.class, idPais);
            Sede p = new Sede();
            p.setNombre(nombre);
            p.setPaisidPais(paisSel);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarSede(int idSede, String nombre) {
        try {
            Sede p = em.find(Sede.class, idSede);
            p.setNombre(nombre);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
          
     public void persist(Object object) {
        em.persist(object);
    }
         
}
