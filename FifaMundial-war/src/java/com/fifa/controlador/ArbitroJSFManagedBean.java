/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Arbitro;
import com.fifa.negocio.ArbitroSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Usuario
 */
@Named(value = "arbitroJSFManagedBean")
@SessionScoped
public class ArbitroJSFManagedBean implements Serializable {

    @EJB
    private ArbitroSessionBean ArbitroSessionBean;
    
       @Inject
    private PaisJSFManagedBean paisJSF;
    private List<Arbitro> arbitrolist;
    private Arbitro arbitro;
    private boolean editar = false;
    private int idArbitro = -1;
    private String nombre;
    private String apellido;
    private int dni;

    
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
    public ArbitroJSFManagedBean() {
    }

    

    /**
     * @return the arbitro
     */
    public List<Arbitro> getArbitro() {
         
        if(this.arbitrolist == null)
        {
            this.arbitrolist = this.ArbitroSessionBean.obtenerArbitro();
       }
        return arbitrolist;
    }
     

    /**
     * @param arbitrolist
     */
    public void setArbitro(List<Arbitro> arbitrolist) {
       
        this.arbitrolist = arbitrolist;
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

    public String verEditar(boolean ver, int idArbitro, String nombre, String apellido, int dni) {
        this.editar = ver;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.idArbitro = idArbitro;
        return null;
    }
    
    public String guardar() {
        if (this.idArbitro != -1) {
            this.ArbitroSessionBean.modificarArbitro(idArbitro, nombre, apellido, dni);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro modificado con exito", ""));
        } else {
              if (ArbitroSessionBean.Validator(dni)==false){
                  FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El Arbitro ya existe", ""));
            } 
              else{
             this.ArbitroSessionBean.agregarArbitro(nombre, apellido, dni, paisJSF.getIdPais());
            this.nombre = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro agregado con exito", ""));
        }
        }
        this.arbitrolist = null;
        return null;
    }

    public String eliminar(int id) {
        this.ArbitroSessionBean.borrarArbitro(id);
        this.arbitrolist = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Arbitro eliminado con exito", ""));
        return null;
    }

    


}
