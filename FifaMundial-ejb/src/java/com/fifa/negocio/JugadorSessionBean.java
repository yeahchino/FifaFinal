/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Jugador;
import java.util.Date;
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
public class JugadorSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

   public void persist(Object object) {
        em.persist(object);
    }

    public boolean agregarJugador(int idJugador, String nombre,String apellido, int dni,Date fechaNac) {
        try {
            Jugador j = new Jugador();
            j.setNombre(nombre);
            j.setApellido(nombre);
            j.setDni(dni);
            j.setFechaNac(fechaNac);
            em.persist(j);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarJugador(int idJugador, String nombre,String apellido, int dni,Date fechaNac) {
        try {
            Jugador j = em.find(Jugador.class, idJugador);
            j.setNombre(nombre);
            j.setApellido(nombre);
            j.setDni(dni);
            j.setFechaNac(fechaNac);
            em.merge(j);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarJugador(int idJugador) {
        try {
            em.getEntityManagerFactory().getCache().evict(Jugador.class);
            Jugador j = em.find(Jugador.class, idJugador);
            em.remove(j); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Jugador> obtenerJugador()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Jugador.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Jugador> obtenerJugadorNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Jugador.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Jugador> obtenerJugadorId()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Jugador.findByIdJugador");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
 
}
