/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Cuerpotecnico;
import com.fifa.datos.Estadio;
import com.fifa.negocio.CuerpotecnicoSessionBean;
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
@Named(value = "cuerpoTecnicoJSFManagedBean")
@SessionScoped
public class CuerpoTecnicoJSFManagedBean implements Serializable {

    @EJB
    private CuerpotecnicoSessionBean cuerpotecnicoSessionBean;

   
    public CuerpoTecnicoJSFManagedBean() {
    }

   private List<Cuerpotecnico> cuerpotecnico;
    private boolean editar = false;
    private int idCuerpotecnico = -1;
     private String nombre;
    private String apellido;
     private int dni;

    /**
     * @return the cuerpotecnico
     */
    public List<Cuerpotecnico> getCuerpotecnico() {
        return cuerpotecnico;
    }

    /**
     * @param cuerpotecnico the cuerpotecnico to set
     */
    public void setCuerpotecnico(List<Cuerpotecnico> cuerpotecnico) {
        this.cuerpotecnico = cuerpotecnico;
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
     * @return the idCuerpotecnico
     */
    public int getIdCuerpotecnico() {
        return idCuerpotecnico;
    }

    /**
     * @param idCuerpotecnico the idCuerpotecnico to set
     */
    public void setIdCuerpotecnico(int idCuerpotecnico) {
        this.idCuerpotecnico = idCuerpotecnico;
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
     * @return the apellido
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * @param apellido the apellido to set
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return the dni
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param dni the dni to set
     */
    public void setDni(int dni) {
        this.dni = dni;
    }
  public String verEditar(boolean ver, int idCuerpotecni, String nombre,String apel, int dni) {
        this.setEditar(ver);
        this.idCuerpotecnico = idCuerpotecni;
        this.setNombre(nombre);
        this.setApellido(apel);
        this.setDni(dni);
       
        return null;
    }

    public String eliminar(int idCuerpotecnico) {
        this.cuerpotecnicoSessionBean.borrarCuerpotecnico(idCuerpotecnico);
        this.setCuerpotecnico(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuerpotecnico eliminada con exito", ""));
        return null;
    }

    public String guardarRonda() {
        if (this.getIdCuerpotecnico()== -1) {
            this.cuerpotecnicoSessionBean.agregarCuerpotecnico(nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuerpotecnico agregada con exito", ""));
        } else {
            this.cuerpotecnicoSessionBean.modificarCuerpotecnico(idCuerpotecnico, nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cuerpotecnico modificada con exito", ""));
        }
        this.setEditar(false);
        this.setCuerpotecnico(null);
        return null;
    }


    
}
