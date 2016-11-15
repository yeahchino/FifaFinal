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
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@Named(value = "paisJSFManagedBean")
@SessionScoped
public class PaisJSFManagedBean implements Serializable , Converter{

    @EJB
    private PaisSessionBean paisSessionBean;

    private List<Pais> pais;
    private boolean editar = false;
    private int idPais = -1;
    private String nombre;

    private List<Pais> paisesSel;

    /**
     * Creates a new instance of AlumnoBean
     */
    public PaisJSFManagedBean() {
    }

    /**
     * @return the pais
     */
    public List<Pais> getPais() {

        if (this.pais == null) {
            this.pais = this.paisSessionBean.obtenerPais();
        }
        return pais;
    }

    /**
     * @param paislist
     */
    public void setPais(List<Pais> paislist) {

        this.pais = paislist;
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

    public String verEditar(boolean ver, int idPais, String nombre) {
        this.editar = ver;
        this.nombre = nombre;
        this.idPais = idPais;
        return null;
    }

    public String guardar() {
        if (this.idPais != -1) {
            this.paisSessionBean.modificarPais(idPais, nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais modificado con exito", ""));
        } else {
            if (paisSessionBean.agregarPais(nombre)==false) {
                   FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Pais ya existe", ""));
            } else{
            this.paisSessionBean.agregarPais(nombre);
            this.nombre = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais agregado con éxito", ""));
            }
            }
        this.pais = null;
        return null;
    }

    public String eliminar(int id) {
        this.paisSessionBean.borrarPais(id);
        this.pais = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais eliminado con exito", ""));
        return null;
    }

    public void onRowSelect(SelectEvent event) {
        Pais p = ((Pais) event.getObject());
        this.idPais = p.getIdPais();
        this.nombre = p.getNombre();
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecciono " + nombre, ""));
    }

    /**
     * @return the paisesSel
     */
    public List<Pais> getPaisesSel() {
        return paisesSel;
    }

    /**
     * @param paisesSel the paisesSel to set
     */
    public void setPaisesSel(List<Pais> paisesSel) {
        this.paisesSel = paisesSel;
    }

    public String verPaisesSel() {

        StringBuilder sb = new StringBuilder();
        for (Pais pais : paisesSel) {
            sb.append(pais.getNombre()).append("-");
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Paises sel: " + sb.toString(), ""));
        return "Ronda.xhtml?faces-redirect=true";
        //return null;
    }
    
    }
