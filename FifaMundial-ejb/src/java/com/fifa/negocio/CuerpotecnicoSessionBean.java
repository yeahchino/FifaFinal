/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Cuerpotecnico;
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
public class CuerpotecnicoSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
public boolean agregarCuerpotecnico(String nombre, String apellido, int dni) {
        try {
            Cuerpotecnico a = new Cuerpotecnico();
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
    
    public boolean modificarCuerpotecnico(int idCuerpotecnico, String nombre, String apellido, int dni) {
        try {
            Cuerpotecnico a = em.find(Cuerpotecnico.class, idCuerpotecnico);
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
    
    public boolean borrarCuerpotecnico(int idCuerpotecnico) {
        try {
            em.getEntityManagerFactory().getCache().evict(Cuerpotecnico.class);
            Cuerpotecnico a = em.find(Cuerpotecnico.class, idCuerpotecnico);
            em.remove(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Cuerpotecnico> obtenerCuerpotecnico()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Cuerpotecnico> obtenerArbitroId ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findByIdUsuario");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Cuerpotecnico> obtenerArbitroNombre ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
     public List<Cuerpotecnico> obtenerUsuarioApellido ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findByApellido");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
     
      public List<Cuerpotecnico> obtenerArbitroDni ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findByDni");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
          
     
}