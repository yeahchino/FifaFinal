/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Jugador;
import com.fifa.datos.Pais;
import com.fifa.negocio.JugadorSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Adriana
 */
@Named(value = "jugadorJSFManagedBean")
@SessionScoped
public class JugadorJSFManagedBean implements Serializable {

    @EJB
    private JugadorSessionBean jugadorSessionBean;

  
 
    private List<Jugador> jugador;
    private boolean editar = false;
    private int idJugador = -1;
     private String nombre;
     private String apellido;
     private int dni;
     private Date fechaNac;

  
   public JugadorJSFManagedBean() {
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
     * @return the idJugador
     */
    public int getIdJugador() {
        return idJugador;
    }

    /**
     * @param idJugador the idJugador to set
     */
    public void setIdJugador(int idJugador) {
        this.idJugador = idJugador;
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

    /**
     * @return the fechaNac
     */
    public Date getFechaNac() {
        return fechaNac;
    }

    /**
     * @param fechaNac the fechaNac to set
     */
    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    
    
    public String verEditar(boolean ver, int idZona, String nombre) {
        this.setEditar(ver);
        this.idJugador = idJugador;
        this.setNombre(nombre);
       
        return null;
    }

    public String eliminar(int idJugador) {
        this.jugadorSessionBean.borrarJugador(getIdJugador());
        this.setJugador(null); //revisar
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais eliminada con exito", ""));
        return null;
    }

    public String guardarRonda() {
        if (this.getIdJugador()== -1) {
            this.jugadorSessionBean.agregarJugador(idJugador, nombre, apellido, dni, fechaNac);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais agregada con exito", ""));
        } else {
            this.jugadorSessionBean.modificarJugador(idJugador, nombre, apellido, dni, fechaNac);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pais modificada con exito", ""));
        }
        this.setEditar(false);
        this.setJugador(null);//revisar
        return null;
    }

    /**
     * @return the jugador
     */
    public List<Jugador> getJugador() {
        return jugador;
    }

    /**
     * @param jugador the jugador to set
     */
    public void setJugador(List<Jugador> jugador) {
        this.jugador = jugador;
    }

 
  
}
