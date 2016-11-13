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
import java.util.Arrays;
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
    private List <Equipo> zona;
    private boolean editar = false;
    private int idEquipo = -1;
    private Pais paisidPais;
    private Zona zonaidZona;
    private Mundial mundialidMundial;
    private List<String> list;
    List lista = new ArrayList();

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
     
    
   public List <String> obtenerfix(){
    
       

 int teams =4;
 
   
    // Generate the schedule using round robin algorithm.
    int totalRounds = (teams - 1)*2;
    int matchesPerRound = teams / 2;
    String[][] rounds = new String[totalRounds][matchesPerRound];

    for (int round = 0; round < totalRounds; round++) {
        for (int match = 0; match < matchesPerRound; match++) {
            int home = (round + match) % (teams - 1);
            int away = (teams - 1 - match + round) % (teams - 1);

            // Last team stays in the same place while the others
            // rotate around it.
            if (match == 0) {
                away = teams - 1;
            }

            // Add one so teams are number 1 to teams not 0 to teams - 1
            // upon display.
            rounds[round][match] = ("team " + (home + 1) + " plays against team " + (away + 1));
        }
    }
     

    // Display the rounds    
    for (int i = 0; i < rounds.length; i++) {
       
            list = Arrays.asList(rounds[i]);
           
    }
       return list;
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
     

    /**
     * @return the list
     */


    /**
     * @param list the list to set
     */
 

    


}
