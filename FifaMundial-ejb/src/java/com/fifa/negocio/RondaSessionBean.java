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
    public void generarFixture() {
        int equipos = 4;
        int totalDeFechas = equipos - 1;
        int partidosPorFecha = equipos / 2;
        String[][] fechas = new String[totalDeFechas][partidosPorFecha];
        //List<String> resultadoRonda;
        
        //iteracion de la matriz entre partidos por fecha y total de fechas
        for (int fecha = 0; fecha < totalDeFechas; fecha++) {
            for (int partido = 0; partido < partidosPorFecha; partido++) {
                int local = (fecha + partido) % (equipos - 1);
                int vistante = (equipos - 1 - partido + fecha) % (equipos - 1);
                
                 
                //Esto hace el efecto de que se quede frenado el primer equipo y los demas giren
                if (partido == 0) {
                    vistante = equipos - 1;
                }

                fechas[fecha][partido] = (local + 1) + " v " + (vistante + 1);
                //List<String> resultadoronda;

               // for (int i = 0; i < fechas.length; i++) {
               //resultadoRonda = Arrays.asList(fechas[i]);
                    
                    
                }
                

            }
        }
        
    }
    

