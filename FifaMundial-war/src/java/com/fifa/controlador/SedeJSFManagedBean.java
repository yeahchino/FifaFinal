/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Sede;
import com.fifa.negocio.SedeSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Adriana
 */
@Named(value = "sedeJSFManagedBean")
@SessionScoped
public class SedeJSFManagedBean implements Serializable {

    @EJB
    private SedeSessionBean sedeSessionBean;

     private List<Sede> sede;
    private boolean editar = false;
    private int idSede = -1;
    private String nombre;
    
     public SedeJSFManagedBean() {
    }

    /**
     * @return the sede
     */
    public List<Sede> getSede() {
        return sede;
    }

    /**
     * @param sede the sede to set
     */
    public void setSede(List<Sede> sede) {
        this.sede = sede;
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
     * @return the idSede
     */
    public int getIdSede() {
        return idSede;
    }

    /**
     * @param idSede the idSede to set
     */
    public void setIdSede(int idSede) {
        this.idSede = idSede;
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
    
    public String verEditar(boolean ver, int idSede, String nombre) {
        this.setEditar(ver);
        this.idSede = idSede;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idSede) {
        this.sedeSessionBean.borrarSede(idSede);
        this.setSede(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede eliminada con exito", ""));
        return null;
    }

    public String guardarSede() {
        if (this.getIdSede() == -1) {
            this.sedeSessionBean.agregarSede(nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede agregada con exito", ""));
        } else {
            this.sedeSessionBean.modificarSede(idSede, nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sede modificada con exito", ""));
        }
        this.setEditar(false);
        this.setSede(null);
        return null;
    }
 
   
}
