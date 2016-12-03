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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    
    public String guardar() {
       
        if 
         (this.idJugador != -1) {
            this.jugadorSessionBean.modificarJugador(idJugador, nombre, apellido, fechaNac, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador modificado con exito", ""));
        } else {
    
            if (jugadorSessionBean.Validator(dni)==false){
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Jugador ya existe", ""));
            } 
         
            else{
                if (validarDni(dni)==false){
                           FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El dni debe contener 8 caracteres", ""));
     
                }
                else{
           this.jugadorSessionBean.agregarJugador(nombre, apellido, fechaNac, dni);
            this.nombre = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jugador agregado con exito", ""));
       
            }
            }
        }
        this.jugadorlist = null;
        return null;
    }

public boolean validarDni(int dni){
     
String cadena = "";
 
 
 
cadena = String.valueOf(dni);
 
cadena= Integer.toString(dni);
      if (cadena.length()!=8){
            
            return false;
        }
      return true;
    
}
}
