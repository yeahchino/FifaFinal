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
import com.fifa.datos.Equipo;
import static com.fifa.datos.Equipo_.mundialidMundial;
import com.fifa.datos.Zona;
import com.fifa.datos.Pais;
import com.fifa.datos.Mundial;
import java.util.ArrayList;
import java.util.Arrays;
import javax.persistence.Query;
import javax.servlet.jsp.tagext.TryCatchFinally;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class EquipoSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public List<Equipo> obtenerEquipo() {
        try {
            javax.persistence.Query q = em.createNamedQuery("Equipo.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Equipo> obtenerEquipoNombre(String nombre) {
        try {
            javax.persistence.Query q = em.createNamedQuery("Equipo.findByNombre");
            q.setParameter("nombre", "%" + nombre);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public Equipo obtenerEquipoId(int idEquipo) {
        try {
            em.getEntityManagerFactory().getCache().evict(Equipo.class);
            Equipo a = em.find(Equipo.class, idEquipo);
            return a;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean borrarEquipo(int idEquipo) {
        try {
            em.getEntityManagerFactory().getCache().evict(Equipo.class);
            Equipo p = em.find(Equipo.class, idEquipo);
            em.remove(p);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
 public boolean agregarEquipo(int idPais,int idZona,int idMundial) {
        try {
            Pais paisSel = em.find(Pais.class, idPais);
             Zona zonaSel = em.find(Zona.class, idZona);
              Mundial MundialSel = em.find(Mundial.class, idMundial);

            if (paisSel != null) {
                Equipo e = new Equipo();
                e.setPaisidPais(paisSel);
                e.setZonaidZona(zonaSel);
               e.setMundialidMundial(MundialSel);
                em.persist(e);
                em.flush();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public Boolean Validator(Mundial idMundial, Pais idPais) {
        String p1 = null;
        String consulta;
        try {
            consulta = "FROM Equipo p WHERE p.mundialidMundial= ?1 and p.paisidpais=2? ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, idMundial);
            query.setParameter(2, idPais);

            List<Pais> Listpa = query.getResultList();
            if (!Listpa.isEmpty()) {

                return false;
            }

        } catch (Exception e) {

        }
        return true;

    }

    public boolean modificarEquipo(int idEquipo, Pais paisidPais, Zona zonaidZona, Mundial mundialidMundial) {
        try {
            Equipo p = em.find(Equipo.class, idEquipo);
            p.setPaisidPais(paisidPais);
            p.setZonaidZona(zonaidZona);
            p.setMundialidMundial(mundialidMundial);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Equipo> obtenerEquiposOrdenZona() {
        try {
            javax.persistence.Query q = em.createNamedQuery("Equipo.findEquipoByZona");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

   

    public void persist(Object object) {
        em.persist(object);
    }

    public boolean modificarEquipoZona(int idEquipo, Zona zonaidZona) {
        {
        try {
            Equipo p = em.find(Equipo.class, idEquipo);
          
            p.setZonaidZona(zonaidZona);
            
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    }

    public boolean agregarEquipoZona(Zona zonaidZona) {{
        try {
            
                Equipo p = new Equipo();
               
                p.setZonaidZona(zonaidZona);
                
                em.persist(p);
                em.flush();
                return true;
                
                    } catch (Exception e) {

        }
        return false;
    }
    }
    
    public List<Equipo> obtenerEquipoXzona(int id) {
        try {
            javax.persistence.Query q = em.createNamedQuery("Equipo.findByIdZona");
            q.setParameter("idZona", id);
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

}
