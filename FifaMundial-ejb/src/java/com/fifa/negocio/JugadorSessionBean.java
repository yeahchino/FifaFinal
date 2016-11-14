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
import com.fifa.datos.Jugador;
import com.fifa.datos.Pais;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class JugadorSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    
    public List<Jugador> obtenerJugador()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Jugador.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Jugador> obtenerJugadorNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Jugador.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Jugador obtenerJugadorId(int idJugador)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Jugador.class);
            Jugador a = em.find(Jugador.class, idJugador);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarJugador(int idJugador) {
        try {
            em.getEntityManagerFactory().getCache().evict(Jugador.class);
            Jugador p = em.find(Jugador.class, idJugador);
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
            consulta = "FROM Jugador p WHERE p.dni= ?1 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, dni);
                   
        List <Pais> Listpa = query.getResultList();
            if (!Listpa.isEmpty()) {
               
                return false;
            }

            
        } catch (Exception e) {
            
            
            
        }
        return true;
        
    }


    public boolean agregarJugador( String nombre, String apellido,Date fechaNac, int dni) {
        try {
                if (Validator(dni)==true){
                
            Jugador p = new Jugador();
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setFechaNac(fechaNac);
            p.setDni(dni);
            em.persist(p);
            em.flush();
            return true;
                }
        } catch (Exception e) {
          
        }
          return false;
    }
     
     public boolean modificarJugador(int idJugador, String nombre, String apellido,Date fechaNac , int dni) {
        try {
            Jugador p = em.find(Jugador.class, idJugador);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setFechaNac(fechaNac);
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
