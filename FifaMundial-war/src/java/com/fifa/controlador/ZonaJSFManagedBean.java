/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Pais;
import com.fifa.datos.Zona;
import com.fifa.negocio.ZonaSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Adriana
 */
@Named(value = "zonaJSFManagedBean")
@SessionScoped
public class ZonaJSFManagedBean implements Serializable {

    @EJB
    private ZonaSessionBean zonaSessionBean;

    @Inject 
    PaisJSFManagedBean paisJSF;
    
    
    private List<Zona> zona;
   
    private boolean editar = false;
    private int idZona = -1;
    private char nombre;
   

    /**
     * Creates a new instance of AlumnoBean
     */
    public ZonaJSFManagedBean() {
    }

    

    
    /**
     * @return the zona
     */
    public List<Zona> getZona() {
         if (this.zona == null) {
            this.zona = this.zonaSessionBean.obtenerZona();
        }
        return zona;
    }

    
         
    
    
    /**
     * @param zona the zona to set
     */
    public void setZona(List<Zona> zona) {
        this.zona = zona;
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
     * @return the idZona
     */
    public int getIdZona() {
        return idZona;
    }

    /**
     * @param idZona the idZona to set
     */
    public void setIdZona(int idZona) {
        this.idZona = idZona;
    }

    /**
     * @return the nombre
     */
    public char getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(char nombre) {
        this.nombre = nombre;
    }
    
    
    
    
    public String verEditar(boolean ver, int idZona, char nombre) {
        this.setEditar(ver);
        this.idZona = idZona;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idZona) {
        this.zonaSessionBean.borrarZona(getIdZona());
        this.setZona(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zona eliminada con exito", ""));
        return null;
    }
    public void onRowSelect(SelectEvent event) {
        Zona z = ((Zona) event.getObject());
        this.idZona = z.getIdZona();
        this.nombre = z.getNombre();
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecciono " + nombre, ""));
    }   
 
  public String guardarZona() {
       
        if 
         (this.idZona != -1) {
            this.zonaSessionBean.modificarZona(idZona, nombre);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador modificado con exito", ""));
        } else {
    
            if (zonaSessionBean.Validator(nombre)==false){
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La zona ya existe", ""));
            } 
         
                        else{
           this.zonaSessionBean.agregarZona(nombre);
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Zona agregada con exito", ""));
       
            }
            }
         this.zona = null;
        return null;
        }
       
    }

