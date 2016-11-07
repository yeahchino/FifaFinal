/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;


import com.fifa.datos.Organizador;
import com.fifa.negocio.OrganizadorSessionBean;
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
@Named(value = "organizadorJSFManagedBean")
@SessionScoped
public class OrganizadorJSFManagedBean implements Serializable {

    @EJB
    private OrganizadorSessionBean organizadorSessionBean;
    private List<Organizador> organizador;
    private boolean editar = false;
    private int idOrganizador = -1;
     private String nombre;
     private String apellido;
     private int dni;
    
    public OrganizadorJSFManagedBean() {
    }
    
    
    
    public String verEditar(boolean ver, int idOrganizador, String nombre) {
        this.setEditar(ver);
        this.idOrganizador = idOrganizador;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idOrganizador) {
        this.organizadorSessionBean.borrarOrganizador(idOrganizador);
        this.setOrganizador(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizador eliminada con exito", ""));
        return null;
    }

    public String guardarOrganizador() {
        if (this.getIdOrganizador()== -1) {
            this.organizadorSessionBean.agregarOrganizador(nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizador agregada con exito", ""));
        } else {
            this.organizadorSessionBean.modificarOrganizador(idOrganizador, nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Organizador modificada con exito", ""));
        }
        this.setEditar(false);
        this.setOrganizador(null);
        return null;
    }

    /**
     * @return the organizador
     */
    public List<Organizador> getOrganizador(){
         if (this.organizador == null) {
            this.organizador = this.organizadorSessionBean.obtenerOrganizador();
        }
        return organizador;
    }

    /**
     * @param organizador the organizador to set
     */
    public void setOrganizador(List<Organizador> organizador) {
        this.organizador = organizador;
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
     * @return the idOrganizador
     */
    public int getIdOrganizador() {
        return idOrganizador;
    }

    /**
     * @param idOrganizador the idOrganizador to set
     */
    public void setIdOrganizador(int idOrganizador) {
        this.idOrganizador = idOrganizador;
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
}
