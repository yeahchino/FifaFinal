/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Equipo;
import com.fifa.datos.Pais;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fifa.datos.Zona;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class ZonaSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Zona> obtenerZona()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Zona.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Zona> obtenerZonaNombre(Character nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Zona.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Zona obtenerZonaId(int idZona)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Zona.class);
            Zona a = em.find(Zona.class, idZona);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarZona(int idZona) {
        try {
            em.getEntityManagerFactory().getCache().evict(Zona.class);
            Zona p = em.find(Zona.class, idZona);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarZona( Character nombre) {
        try {
            if (Validator(nombre)==true){
            Zona p = new Zona();
            p.setNombre(nombre);
            em.persist(p);
            em.flush();
            return true;
            }
        } catch (Exception e) {
            return false;
        }
                    return false;
    }
    
      public Boolean  Validator (char nombre){
     String p1=null;
     String consulta;
        try {
            consulta = "FROM Zona z WHERE z.nombre= ?1 ";
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
     public boolean modificarZona(int idZona, Character nombre) {
        try {
            Zona p = em.find(Zona.class, idZona);
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
