/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Equipo;
import com.fifa.datos.Estadio;
import com.fifa.datos.Jugador;
import com.fifa.datos.Mundial;
import com.fifa.datos.Partido;
import com.fifa.datos.Ronda;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Alexia
 */
@Stateless
@LocalBean
public class PartidoSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;
    
    public void persist(Object object) {
        em.persist(object);
    }

    public boolean agregarPartido(Date fecha, Date hora, int eqA, int eqB, int rdoA, int rdoB,
            int penalA, int penalB, int capA, int capB, int mundial, int ronda, int estadio) {
        try {
            Equipo idEqA = em.find(Equipo.class, eqA);
            Equipo idEqB = em.find(Equipo.class, eqB);
            Jugador idCapA = em.find(Jugador.class, capA);
            Jugador idCapB = em.find(Jugador.class, capB);
            Mundial idM = em.find(Mundial.class, mundial);
            Ronda idR = em.find(Ronda.class, ronda);
            Estadio idEstadio = em.find(Estadio.class, estadio);

           if (idEqA != null && idEqB != null && idM != null &&idR != null) { 
                Partido p = new Partido();
                p.setIdPartido("P" + eqA + eqB + mundial +ronda);
                p.setFecha(fecha);
                p.setHora(hora);
                p.setEquipoidEquipoA(idEqA);
                p.setEquipoidEquipoB(idEqB);
                p.setRdoA(rdoA);
                p.setRdoB(rdoB);
                p.setPenalesA(penalA);
                p.setPenalesB(penalB);
                p.setJugadorcapitanA(idCapA);
                p.setJugadorcapitanB(idCapB);
                p.setMundialidMundial(idM);
                p.setRondaidRonda(idR);
                p.setEstadioidEstadio(idEstadio);
                em.persist(p);
                em.flush();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    
    
     public boolean agregaPartidos( Date fecha, int rdoA, int rdoB,int idMundial, int idRonda, int idEstadio, int equiA, int equiB) {
        try {
            Ronda RondaSel = em.find(Ronda.class, idRonda);
             Estadio Estadioel = em.find(Estadio.class, idEstadio);
              Mundial MundialSel = em.find(Mundial.class, idMundial);
              Equipo EquipoA= em.find(Equipo.class, equiA);
              Equipo EquipoB= em.find(Equipo.class, equiB);
           

                     
            if (EquipoA!= null && EquipoB != null) { 
              Partido p = new Partido();
              p.setIdPartido("P" + EquipoA + EquipoB + idMundial +idRonda);
              p.setMundialidMundial(MundialSel);
              p.setFecha(fecha);
              p.setEquipoidEquipoA(EquipoA);
              p.setEquipoidEquipoB(EquipoB);
              p.setEstadioidEstadio(Estadioel);
              p.setRdoA(rdoA);
              p.setRdoB(rdoB);
              p.setRondaidRonda(RondaSel);
                        
                em.persist(p);
                em.flush();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }
       
     
  
//
//    public boolean modificarEquipoZona(int idEquipo, Zona zonaidZona) {
//        {
//            try {
//                Equipo p = em.find(Equipo.class, idEquipo);
//
//                p.setZonaidZona(zonaidZona);
//
//                em.merge(p);
//                em.flush();
//                return true;
//            } catch (Exception e) {
//                return false;
//            }
//        }
//    }
//

     
 public List<Partido> obtenerPartidos() {
        try {
            javax.persistence.Query q = em.createNamedQuery("Equipo.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }
}
