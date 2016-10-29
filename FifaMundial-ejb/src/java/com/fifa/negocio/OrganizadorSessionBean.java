/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Jugador;
import com.fifa.datos.Organizador;
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
public class OrganizadorSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    

    
    public boolean agregarOrganizador( String nombre,String apellido, int dni) {
        try {
            Organizador o = new Organizador();
            o.setNombre(nombre);
            o.setApellido(nombre);
            o.setDni(dni);
          
            em.persist(o);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarOrganizador(int idOrganizador,String nombre,String apellido, int dni) {
        try {
            Organizador o = em.find(Organizador.class, idOrganizador);
            o.setNombre(nombre);
            o.setApellido(nombre);
            o.setDni(dni);
           
            em.merge(o);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarOrganizador(int idOrganizador) {
        try {
            em.getEntityManagerFactory().getCache().evict(Jugador.class);
            Organizador o = em.find(Organizador.class, idOrganizador);
            em.remove(o); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Organizador> obtenerOrganizador()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Organizador.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Organizador> obtenerOrganizadorNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Organizador.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Organizador> obtenerOrganizadorId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Organizador.findByIdJugador");
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
