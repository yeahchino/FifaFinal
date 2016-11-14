/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Mundial;
import com.fifa.datos.Pais;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import javax.persistence.Query;

/**
 *
 * @author Alexia
 */
@Stateless
@LocalBean
public class MundialSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }

    public List<Mundial> ObtenerMundiales() {
        try {
            Query q = em.createNamedQuery("Mundial.findAll");
            return q.getResultList();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean agregarMundial(Date fechaInicio, Date fechaFin, int idPais) {
        try {
            Pais paisSel = em.find(Pais.class, idPais);

            if (paisSel != null) {
                Mundial m = new Mundial();
                m.setFechaInicio(fechaInicio);
                m.setFechaFin(fechaFin);
                m.setPaisidPais(paisSel);
                em.persist(m);
                em.flush();
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
