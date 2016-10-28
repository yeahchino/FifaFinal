/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Zona;
import static com.fifa.datos.Zona_.idZona;
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
public class ZonaSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

public boolean agregarZona(Character nombre) {
        try {
            Zona a = new Zona();
            a.setNombre(nombre);
            em.persist(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarZona(int idUsuario, Character nombre) {
        try {
            Zona a = em.find(Zona.class, idZona);
            a.setNombre(nombre);
            em.merge(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarZona(int idTipoUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Zona.class);
            Zona a = em.find(Zona.class, idZona);
            em.remove(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Zona> obtenerZona()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Zona.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Zona> obtenerZonaNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Zona.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Zona> obtenerZonaId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Zona.findByIdZona");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
}