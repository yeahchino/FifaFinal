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
import com.fifa.datos.Organizador;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class OrganizadorSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Organizador> obtenerOrganizador()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Organizador.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Organizador> obtenerOrganizadorNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Organizador.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Organizador obtenerOrganizadorId(int idOrganizador)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Organizador.class);
            Organizador a = em.find(Organizador.class, idOrganizador);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarOrganizador(int idOrganizador) {
        try {
            em.getEntityManagerFactory().getCache().evict(Organizador.class);
            Organizador p = em.find(Organizador.class, idOrganizador);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarOrganizador ( String nombre, String apellido, int dni) {
        try {
            Organizador p = new Organizador();
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
     
     public boolean modificarOrganizador(int idOrganizador, String nombre, String apellido, int dni) {
        try {
            Organizador p = em.find(Organizador.class, idOrganizador);
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
