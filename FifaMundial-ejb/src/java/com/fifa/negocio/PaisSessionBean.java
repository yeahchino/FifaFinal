/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Pais;
import static com.fifa.datos.Pais_.idPais;
import java.util.Date;
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
public class PaisSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;



    public boolean agregarPais( String nombre) {
        try {
            Pais p = new Pais();
            p.setNombre(nombre);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarPais(String nombre) {
        try {
            Pais p = em.find(Pais.class, idPais);
            p.setNombre(nombre);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarPais(int idJugador) {
        try {
            em.getEntityManagerFactory().getCache().evict(Pais.class);
            Pais p = em.find(Pais.class, idPais);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     

    public List<Pais> obtenerPaisNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Pais.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Pais> obtenerPaisId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Pais.findByIdPais");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
 
    public List<Pais> obtenerPais()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Pais.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
}
