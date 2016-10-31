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
import com.fifa.datos.Cuerpotecnico;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class CuerpotecnicoSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Cuerpotecnico> obtenerCuerpotecnico()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Cuerpotecnico> obtenerCuerpotecnicoNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Cuerpotecnico.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Cuerpotecnico obtenerCuerpotecnicoId(int idCuerpotecnico)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Cuerpotecnico.class);
            Cuerpotecnico a = em.find(Cuerpotecnico.class, idCuerpotecnico);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarCuerpotecnico(int idCuerpotecnico) {
        try {
            em.getEntityManagerFactory().getCache().evict(Cuerpotecnico.class);
            Cuerpotecnico p = em.find(Cuerpotecnico.class, idCuerpotecnico);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarCuerpotecnico( String nombre, String apellido, int dni) {
        try {
            Cuerpotecnico p = new Cuerpotecnico();
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarCuerpotecnico(int idCuerpotecnico, String nombre, String apellido, int dni) {
        try {
            Cuerpotecnico p = em.find(Cuerpotecnico.class, idCuerpotecnico);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);
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
