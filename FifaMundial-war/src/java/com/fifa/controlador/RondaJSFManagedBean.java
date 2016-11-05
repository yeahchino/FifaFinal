/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Pais;
import com.fifa.datos.Ronda;
import com.fifa.negocio.RondaSessionBean;
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
@Named(value = "rondaJSFManagedBean")
@SessionScoped
public class RondaJSFManagedBean implements Serializable {

    @EJB
    private RondaSessionBean rondaSessionBean;

     private List<Ronda> ronda;
    private boolean editar = false;
    private int idRonda = -1;
    private String nombre;
    
    public RondaJSFManagedBean() {
    }

    /**
     * @return the ronda
     */
    public List<Ronda> getRonda() {
       {
        if (this.ronda == null) {
            this.ronda = this.rondaSessionBean.obtenerRonda();
        }return ronda;
    }

    }

    /**
     * @param ronda the ronda to set
     */
    public void setRonda(List<Ronda> ronda) {
        this.ronda = ronda;
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
     * @return the idRonda
     */
    public int getIdRonda() {
        return idRonda;
    }

    /**
     * @param idRonda the idRonda to set
     */
    public void setIdRonda(int idRonda) {
        this.idRonda = idRonda;
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
    public String verEditar(boolean ver, int idRonda, String nombre) {
        this.setEditar(ver);
        this.idRonda = idRonda;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idRonda) {
        this.rondaSessionBean.borrarRonda(idRonda);
        this.setRonda(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ronda eliminada con exito", ""));
        return null;
    }

    public String guardarRonda() {
        if (this.getIdRonda() == -1) {
            this.rondaSessionBean.agregarRonda(nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ronda agregada con exito", ""));
        } else {
            this.rondaSessionBean.modificarRonda(idRonda, nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ronda modificada con exito", ""));
        }
        this.setEditar(false);
        this.setRonda(null);
        return null;
    }
}
