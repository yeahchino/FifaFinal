/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Pais;
import com.fifa.negocio.PaisSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Usuario
 */
@Named(value = "paisJSFManagedBean")
@SessionScoped
public class PaisJSFManagedBean implements Serializable {

    @EJB
    private PaisSessionBean paisSessionBean;
    
    private List<Pais> pais;
    private boolean editar = false;
    private int idPais = -1;
    private String nombre;
   

    /**
     * Creates a new instance of AlumnoBean
     */
    public PaisJSFManagedBean() {
    }

    

    /**
     * @return the pais
     */
    public List<Pais> getPais() {
         
        if(this.pais == null)
        {
            this.pais = this.paisSessionBean.obtenerPais();
       }
        return pais;
    }
     

    /**
     * @param pais the pais to set
     */
    public void setPais(List<Pais> pais) {
       
        this.pais = pais;
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
     * @return the idPais
     */
    public int getIdPais() {
        return idPais;
    }

    /**
     * @param idPais the idPais to set
     */
    public void setIdPais(int idPais) {
        this.idPais = idPais;
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
  
    
    
    public String verEditar(boolean ver, int idPais, String nombre) {
        this.setEditar(ver);
        this.idPais = idPais;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idPais) {
        this.paisSessionBean.borrarPais(getIdPais());
        this.setPais(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais eliminada con exito", ""));
        return null;
    }

    public String guardarPais() {
        if (this.getIdPais() == -1) {
            this.paisSessionBean.agregarPais(getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais agregada con exito", ""));
        } else {
            this.paisSessionBean.modificarPais(getNombre());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais modificada con exito", ""));
        }
        this.setEditar(false);
        this.setPais(null);
        return null;
    }


}
