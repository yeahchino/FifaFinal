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
import com.fifa.datos.Pais;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class PaisSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public List<Pais> obtenerPais() {
        try {
            javax.persistence.Query q = em.createNamedQuery("Pais.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Pais> obtenerPaisNombre(String nombre) {
        try {
            javax.persistence.Query q = em.createNamedQuery("Pais.findByNombre");
            q.setParameter("nombre", "%" + nombre);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Pais obtenerPaisId(int idPais) {
        try {
            em.getEntityManagerFactory().getCache().evict(Pais.class);
            Pais a = em.find(Pais.class, idPais);
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean borrarPais(int idPais) {
        try {
            em.getEntityManagerFactory().getCache().evict(Pais.class);
            Pais p = em.find(Pais.class, idPais);
            em.remove(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean agregarPais(String nombre) {
        try {
            
            if (Validator(nombre)==true){
                
            
            Pais p = new Pais();
            p.setNombre(nombre);
            em.persist(p);
            em.flush();
                return true;
            }
        
        } catch (Exception e) {
          
        }
          return false;
    }

    
        public Boolean  Validator (String nombre){
     String p1=null;
     String consulta;
        try {
            consulta = "FROM Pais p WHERE p.nombre= ?1 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, nombre);
                   
        List <Pais> Listpa = query.getResultList();
            if (!Listpa.isEmpty()) {
               
                return false;
            }

            
        } catch (Exception e) {
            
            
            
        }
        return true;
        
    }

    
    public boolean modificarPais(int idPais, String nombre) {
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

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Pais> PaisXnombre(String nombre) {
        try {
            Query q = em.createNamedQuery("Pais.buscarXnombre");

            if (nombre != null) {
                q.setParameter("nombre", nombre + "%");
            } else {
                q.setParameter("nombre", "%");
            }
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
