/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fifa.datos.Estadio;
import com.fifa.datos.Pais;
import com.fifa.datos.Sede;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class EstadioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Estadio> obtenerEstadio()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Estadio.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Estadio> obtenerEstadioNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Estadio.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Estadio obtenerEstadioId(int idEstadio)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Estadio.class);
            Estadio a = em.find(Estadio.class, idEstadio);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarEstadio(int idEstadio) {
        try {
            em.getEntityManagerFactory().getCache().evict(Estadio.class);
            Estadio p = em.find(Estadio.class, idEstadio);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarEstadio( String nombre, int aforo,int idSede) {
        try {
           Sede SedeSel = em.find(Sede.class, idSede);
            Estadio p = new Estadio();
            p.setNombre(nombre);
            p.setAforo(aforo);
            p.setSedeidSede(SedeSel);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarEstadio(int idEstadio, String nombre, int aforo) {
        try {
            Estadio p = em.find(Estadio.class, idEstadio);
            p.setNombre(nombre);
            p.setAforo(aforo);
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
