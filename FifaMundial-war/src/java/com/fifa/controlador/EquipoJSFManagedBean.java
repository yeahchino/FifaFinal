/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Equipo;
import com.fifa.datos.Mundial;
import com.fifa.datos.Pais;
import com.fifa.datos.Zona;
import com.fifa.negocio.EquipoSessionBean;
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
@Named(value = "equipoJSFManagedBean")
@SessionScoped
public class EquipoJSFManagedBean implements Serializable {

    @EJB
    private EquipoSessionBean equipoSessionBean;
    
    private List<Equipo> equipolist;
    private boolean editar = false;
    private int idEquipo = -1;
    private Pais paisidPais;
    private Zona zonaidZona;
    private Mundial mundialidMundial;

    public Pais getPaisidPais() {
        return paisidPais;
    }

    public void setPaisidPais(Pais paisidPais) {
        this.paisidPais = paisidPais;
    }

    public Zona getZonaidZona() {
        return zonaidZona;
    }

    public void setZonaidZona(Zona zonaidZona) {
        this.zonaidZona = zonaidZona;
    }

    public Mundial getMundialidMundial() {
        return mundialidMundial;
    }

    public void setMundialidMundial(Mundial mundialidMundial) {
        this.mundialidMundial = mundialidMundial;
    }
    
    
   

    /**
     * Creates a new instance of AlumnoBean
     */
    public EquipoJSFManagedBean() {
    }

    

    /**
     * @return the equipo
     */
    public List<Equipo> getEquipo() {
         
        if(this.equipolist == null)
        {
            this.equipolist = this.equipoSessionBean.obtenerEquipo();
       }
        return equipolist;
    }
     

    /**
     * @param equipolist
     */
    public void setEquipo(List<Equipo> equipolist) {
       
        this.equipolist = equipolist;
    }

    /**
     * @return the idEquipo
     */
    public int getIdEquipo() {
        return idEquipo;
    }

    /**
     * @param idEquipo the idEquipo to set
     */
    public void setIdEquipo(int idEquipo) {
        this.idEquipo = idEquipo;
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

    public String verEditar(boolean ver, int idEquipo, Pais paisidPais, Zona zonaidZona, Mundial mundialidMundial) {
        this.editar = ver;
        this.paisidPais = paisidPais;
        this.zonaidZona = zonaidZona;
        this.mundialidMundial = mundialidMundial;
        this.idEquipo = idEquipo;
        return null;
    }
    
    public String guardar() {
        if (this.idEquipo != -1) {
            this.equipoSessionBean.modificarEquipo(idEquipo,  paisidPais,  zonaidZona,  mundialidMundial);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo modificado con exito", ""));
        } else {
            this.equipoSessionBean.agregarEquipo( paisidPais,  zonaidZona,  mundialidMundial);
            this.paisidPais = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo agregado con exito", ""));
        }
        this.equipolist = null;
        return null;
    }

    public String eliminar(int id) {
        this.equipoSessionBean.borrarEquipo(id);
        this.equipolist = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo eliminado con exito", ""));
        return null;
    }
    
    public List<Equipo> getEquiposOrdenadosPorZona() {
        return this.equipoSessionBean.obtenerEquiposOrdenZona();
}

    


}
