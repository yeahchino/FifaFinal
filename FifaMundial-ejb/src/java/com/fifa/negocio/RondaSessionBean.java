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
import com.fifa.datos.Ronda;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class RondaSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public List<Ronda> obtenerRonda() {
        try {
            javax.persistence.Query q = em.createNamedQuery("Ronda.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Ronda> obtenerRondaNombre(String nombre) {
        try {
            javax.persistence.Query q = em.createNamedQuery("Ronda.findByNombre");
            q.setParameter("nombre", "%" + nombre);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Ronda obtenerRondaId(int idRonda) {
        try {
            em.getEntityManagerFactory().getCache().evict(Ronda.class);
            Ronda a = em.find(Ronda.class, idRonda);
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean borrarRonda(int idRonda) {
        try {
            em.getEntityManagerFactory().getCache().evict(Ronda.class);
            Ronda p = em.find(Ronda.class, idRonda);
            em.remove(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean agregarRonda(String nombre) {
        try {
            Ronda p = new Ronda();
            p.setNombre(nombre);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean modificarRonda(int idRonda, String nombre) {
        try {
            Ronda p = em.find(Ronda.class, idRonda);
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

    /**
     *
     */
 
        
    }
    

