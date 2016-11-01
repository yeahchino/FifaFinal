/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Mundial;
import com.fifa.datos.Pais;
import com.fifa.negocio.MundialSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Alexia
 */
@Named(value = "mundialJSFManagedBean")
@SessionScoped
public class MundialJSFManagedBean implements Serializable {

    @EJB
    private MundialSessionBean mundialSessionBean;

    private List<Mundial> mundial;
    private int idMundial;
    private Date fechaInicio;
    private Date fechaFin;
    private Pais idPais;
    
    public MundialJSFManagedBean() {
    }

    /**
     * @return the mundialSessionBean
     */
    public MundialSessionBean getMundialSessionBean() {
        return mundialSessionBean;
    }

    /**
     * @param mundialSessionBean the mundialSessionBean to set
     */
    public void setMundialSessionBean(MundialSessionBean mundialSessionBean) {
        this.mundialSessionBean = mundialSessionBean;
    }
    
    /**
     * @return the mundial
     */
    public List<Mundial> getMundial() {
        return mundial;
    }

    /**
     * @param mundial the mundial to set
     */
    public void setMundial(List<Mundial> mundial) {
        this.mundial = mundial;
    }

    /**
     * @return the idMundial
     */
    public int getIdMundial() {
        return idMundial;
    }

    /**
     * @param idMundial the idMundial to set
     */
    public void setIdMundial(int idMundial) {
        this.idMundial = idMundial;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the idPais
     */
    public Pais getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(Pais idPais) {
        this.idPais = idPais;
    }

    public String guardar(){
        this.mundialSessionBean.agregarMundial(getFechaInicio(), getFechaFin(), getIdPais().getIdPais());
        return null;
    }
    
    
}
