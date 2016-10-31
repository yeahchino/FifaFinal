/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Jugador;
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
 * @author Usuario
 */
@Named(value = "jugadorJSFManagedBean")
@SessionScoped
public class JugadorJSFManagedBean implements Serializable {

    @EJB
    private JugadorSessionBean jugadorSessionBean;
    
    private List<Jugador> jugadorlist;
    private boolean editar = false;
    private int idJugador = -1;
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private int dni;

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
   

    /**
     * Creates a new instance of AlumnoBean
     */
    public JugadorJSFManagedBean() {
    }

    

    /**
     * @return the jugador
     */
    public List<Jugador> getJugador() {
         
        if(this.jugadorlist == null)
        {
            this.jugadorlist = this.jugadorSessionBean.obtenerJugador();
       }
        return jugadorlist;
    }
     

    /**
     * @param jugadorlist
     */
    public void setJugador(List<Jugador> jugadorlist) {
       
        this.jugadorlist = jugadorlist;
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

    public String verEditar(boolean ver, int idJugador, String nombre, String apellido, Date fechaNac, int dni) {
        this.editar = ver;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.dni = dni;
        this.idJugador = idJugador;
        return null;
    }
    
    public String guardar() {
        if (this.idJugador != -1) {
            this.jugadorSessionBean.modificarJugador(idJugador, nombre, apellido, fechaNac, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador modificado con exito", ""));
        } else {
            this.jugadorSessionBean.agregarJugador(nombre, apellido, fechaNac, dni);
            this.nombre = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador agregado con exito", ""));
        }
        this.jugadorlist = null;
        return null;
    }

    public String eliminar(int id) {
        this.jugadorSessionBean.borrarJugador(id);
        this.jugadorlist = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador eliminado con exito", ""));
        return null;
    }

    


}
