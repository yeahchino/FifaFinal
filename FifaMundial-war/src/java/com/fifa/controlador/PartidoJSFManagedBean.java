/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Equipo;
import com.fifa.datos.Partido;
import com.fifa.negocio.PartidoSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Alexia
 */
@Named(value = "partidoJSFManagedBean")
@SessionScoped
public class PartidoJSFManagedBean implements Serializable {

    @EJB
    private PartidoSessionBean partidoSessionBean;

    @Inject
    private EquipoJSFManagedBean eqJSF;
    @Inject
    private ZonaJSFManagedBean zonaJSF;
    private List<Partido> partido;
     private Date fecha;
    private String idPartido;
    private Date hora;
    private int rdoA;
    private int rdoB;
    private int penalesA;
    private int penalesB;

   @Inject
    private EquipoJSFManagedBean equipoJSF;
   @Inject
    private RondaJSFManagedBean rondaJSF;
   @Inject
    private MundialJSFManagedBean mundialJSF;
   @Inject
    private EstadioJSFManagedBean estadioJSF;
     @Inject
    private JugadorJSFManagedBean jugadorJSF;

    /**
     * Creates a new instance of PartidoJSFManagedBean
     */
    public PartidoJSFManagedBean() {
    }

    /**
     * @return the partidoSessionBean
     */
    public PartidoSessionBean getPartidoSessionBean() {
        return partidoSessionBean;
    }

    /**
     * @param partidoSessionBean the partidoSessionBean to set
     */
    public void setPartidoSessionBean(PartidoSessionBean partidoSessionBean) {
        this.partidoSessionBean = partidoSessionBean;
    }

    /**
     * @return the eqJSF
     */
    public EquipoJSFManagedBean getEqJSF() {
        return eqJSF;
    }

    /**
     * @param eqJSF the eqJSF to set
     */
    public void setEqJSF(EquipoJSFManagedBean eqJSF) {
        this.eqJSF = eqJSF;
    }

    /**
     * @return the zonaJSF
     */
    public ZonaJSFManagedBean getZonaJSF() {
        return zonaJSF;
    }

    /**
     * @param zonaJSF the zonaJSF to set
     */
    public void setZonaJSF(ZonaJSFManagedBean zonaJSF) {
        this.zonaJSF = zonaJSF;
    }
    
//    public String guardar() {
//        this.partidoSessionBean.agregarPartido(null, null, 1, 2, 0, 0, 0, 0, 0, 0, 1, 1, 0);
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Alumno modificado con exito", ""));
//        
//        return null;
//    }

    public void generarCruces() {
       //1v2,1v3,1v4v,2v3,2v4,3v4
       
       for(int i=1;i<9;i++){
       List<Equipo> List = eqJSF.getEqXzona(i);
       
       int r=1;
       int eq1 = List.get(0).getIdEquipo();
       int eq2 = List.get(1).getIdEquipo();
       int eq3 = List.get(2).getIdEquipo();
       int eq4 = List.get(3).getIdEquipo();
       
       
       partidoSessionBean.agregarPartido(null, null, eq1, eq2, 0, 0, 0, 0, 0, 0, 1, r, 0);
       partidoSessionBean.agregarPartido(null, null, eq3, eq4, 0, 0, 0, 0, 0, 0, 1, r, 0);
       
       partidoSessionBean.agregarPartido(null, null, eq2, eq3, 0, 0, 0, 0, 0, 0, 1, r, 0);
       partidoSessionBean.agregarPartido(null, null, eq4, eq1, 0, 0, 0, 0, 0, 0, 1, r, 0);
       
       partidoSessionBean.agregarPartido(null, null, eq1, eq3, 0, 0, 0, 0, 0, 0, 1, r, 0);
       partidoSessionBean.agregarPartido(null, null, eq2, eq4, 0, 0, 0, 0, 0, 0, 1, r, 0);
       }
     FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Fixture generado con exito", ""));
     
    }

    

    public List<Partido> getPartido() {
         if (this.partido == null) {
            this.partido = this.partidoSessionBean.obtenerPartidos();
        }
        return partido;
    }
    
 public String guardar() {
        this.partidoSessionBean.agregaPartidos(fecha, rdoA, rdoB,mundialJSF.getIdMundial() , rondaJSF.getIdRonda(), estadioJSF.getIdEstadio(),equipoJSF.getIdEquipo(), equipoJSF.getIdEquipo());
            
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Partido agregado con éxito", ""));
              this.partido = null;
                  return null;
            }
    
    
    public void setPartido(List<Partido> partido) {
        this.partido = partido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getRdoA() {
        return rdoA;
    }

    public void setRdoA(Integer rdoA) {
        this.rdoA = rdoA;
    }

    public Integer getRdoB() {
        return rdoB;
    }

    public void setRdoB(Integer rdoB) {
        this.rdoB = rdoB;
    }

    public Integer getPenalesA() {
        return penalesA;
    }

    public void setPenalesA(Integer penalesA) {
        this.penalesA = penalesA;
    }

    public Integer getPenalesB() {
        return penalesB;
    }

    public void setPenalesB(Integer penalesB) {
        this.penalesB = penalesB;
    }
}
