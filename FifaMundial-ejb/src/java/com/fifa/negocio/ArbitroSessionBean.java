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
import com.fifa.datos.Arbitro;
import com.fifa.datos.Pais;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class ArbitroSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Arbitro> obtenerArbitro()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Arbitro> obtenerArbitroNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Arbitro.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Arbitro obtenerArbitroId(int idArbitro)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Arbitro.class);
            Arbitro a = em.find(Arbitro.class, idArbitro);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarArbitro(int idArbitro) {
        try {
            em.getEntityManagerFactory().getCache().evict(Arbitro.class);
            Arbitro p = em.find(Arbitro.class, idArbitro);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }

public Boolean  Validator (int dni){
     String p1=null;
     String consulta;
        try {
            consulta = "FROM Arbitro p WHERE p.dni= ?1 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, dni);
                   
        List <Arbitro> Listpa = query.getResultList();
            if (!Listpa.isEmpty()) {
               
                return false;
            }

            
        } catch (Exception e) {
            
            
            
        }
      
        return true;
        
    }


    public boolean agregarArbitro(String nombre, String apellido, int dni, int idPais) {
        try {
            Pais paisSel = em.find(Pais.class, idPais);
            Arbitro p = new Arbitro();
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);
            p.setPaisidPais(paisSel);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarArbitro(int idArbitro, String nombre, String apellido, int dni) {
        try {
            Arbitro p = em.find(Arbitro.class, idArbitro);
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
