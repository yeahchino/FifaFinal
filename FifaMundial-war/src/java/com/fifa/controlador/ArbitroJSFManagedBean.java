/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Arbitro;
import com.fifa.datos.Estadio;
import com.fifa.negocio.ArbitroSessionBean;
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
@Named(value = "arbitroJSFManagedBean")
@SessionScoped
public class ArbitroJSFManagedBean implements Serializable {

    @EJB
    private ArbitroSessionBean arbitroSessionBean;
    
    public ArbitroJSFManagedBean() {
    }
       private List<Arbitro> arbitro;
    private boolean editar = false;
    private int idArbitro = -1;
     private String nombre;
     private String apellido;
     private int dni;

    /**
     * @return the arbitro
     */
    public List<Arbitro> getArbitro()
    {
               
        if(this.arbitro == null)
        {
            this.arbitro = this.arbitroSessionBean.obtenerArbitro();
       }
        return arbitro;
    }
        
        
 
    /**
     * @param arbitro the arbitro to set
     */
    public void setArbitro(List<Arbitro> arbitro) {
        this.arbitro = arbitro;
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
     * @return the idArbitro
     */
    public int getIdArbitro() {
        return idArbitro;
    }

    /**
     * @param idArbitro the idArbitro to set
     */
    public void setIdArbitro(int idArbitro) {
        this.idArbitro = idArbitro;
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
     * @return the aforo
     */
    public int getDni() {
        return dni;
    }

    /**
     * @param aforo the aforo to set
     */
    public void setDni(int aforo) {
        this.dni = aforo;
    }
     
      public String verEditar(boolean ver, int idArbitros, String nombre,String apellido, int dni) {
        this.setEditar(ver);
        this.idArbitro = idArbitros;
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.setDni(dni);
       
        return null;
    }

    public String eliminar(int idArbitro) {
        this.arbitroSessionBean.borrarArbitro(idArbitro);
        this.setApellido(null);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro eliminada con exito", ""));
        return null;
    }

    public String guardarArbitro() {
        if (this.getIdArbitro()== -1) {
            this.arbitroSessionBean.agregarArbitro(nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro agregada con exito", ""));
        } else {
            this.arbitroSessionBean.modificarArbitro(idArbitro, nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro modificada con exito", ""));
        }
        this.setEditar(false);
        this.setArbitro(null);
        return null;
    }

 
     
     
}
