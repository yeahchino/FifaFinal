/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Estadio;

import com.fifa.negocio.EstadioSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Adriana
 */
@Named(value = "estadioJSFManagedBean")
@SessionScoped
public class EstadioJSFManagedBean implements Serializable {

    @EJB
    private EstadioSessionBean estadioSessionBean;

      @Inject
    private SedeJSFManagedBean sedeJSF;

      private List<Estadio> estadio;
    private boolean editar = false;
    private int idEstadio = -1;
     private String nombre;
     private int aforo;
 

    
       public EstadioJSFManagedBean() {
    }

    /**
     * @return the estadio
     */
    public List<Estadio> getEstadio() {
        {
        if (this.estadio == null) {
            this.estadio = this.estadioSessionBean.obtenerEstadio();
        }return estadio;
    }

    }

    /**
     * @param estadio the estadio to set
     */
    public void setEstadio(List<Estadio> estadio) {
        this.estadio = estadio;
    }

    /**
     * @return the editar
     */
    public boolean isEditar() {
        return editar;
    }

    /**
     * @param editar the editar to set
     */
    public void setEditar(boolean editar) {
        this.editar = editar;
    }

    /**
     * @return the idEstadio
     */
    public int getIdEstadio() {
        return idEstadio;
    }

    /**
     * @param idEstadio the idEstadio to set
     */
    public void setIdEstadio(int idEstadio) {
        this.idEstadio = idEstadio;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the aforo
     */
    public int getAforo() {
        return aforo;
    }

    /**
     * @param aforo the aforo to set
     */
    public void setAforo(int aforo) {
        this.aforo = aforo;
    }
     public String verEditar(boolean ver, int idEstadio, String nombre, int aforo) {
        this.setEditar(ver);
        this.idEstadio = idEstadio;
        this.setNombre(nombre);
        this.setAforo(aforo);
       
        return null;
    }

    public String eliminar(int idEstadio) {
        this.estadioSessionBean.borrarEstadio(getIdEstadio());
        this.setEstadio(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estadio eliminada con exito", ""));
        return null;
    }

    public String guardarEstadio() {
        if (this.getIdEstadio()== -1) {
            this.estadioSessionBean.agregarEstadio(nombre, aforo, sedeJSF.getIdSede());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estadio agregada con exito", ""));
        } else {
            this.estadioSessionBean.modificarEstadio(idEstadio,nombre, aforo);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Estadio modificada con exito", ""));
        }
        this.setEditar(false);
        this.setEstadio(null);
        return null;
    }
}
