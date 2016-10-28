/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Estadio;
import static com.fifa.datos.Estadio_.idEstadio;
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
public class EstadioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

public boolean agregarEstadio(int idEstadio, String nombre, int aforo) {
        try {
            Estadio e = new Estadio();
            e.setNombre(nombre);
            e.setAforo(aforo);
            em.persist(e);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarEstadio(int idUsuario, String nombre, int aforo) {
        try {
            Estadio e = em.find(Estadio.class, idEstadio);
            e.setNombre(nombre);
            em.merge(e);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarZona(int idEstadio) {
        try {
            em.getEntityManagerFactory().getCache().evict(Estadio.class);
            Estadio e = em.find(Estadio.class, idEstadio);
            em.remove(e);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Estadio> obtenerEstadio()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Estadio.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Estadio> obtenerZonaNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Estadio.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Estadio> obtenerEstadioId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Estadio.findByIdZona");
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
