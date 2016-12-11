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
import com.fifa.negocio.PaisSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Usuario
 */
@Named(value = "equipoJSFManagedBean")
@SessionScoped
public class EquipoJSFManagedBean implements Serializable {

    @EJB
    private PaisSessionBean paisSessionBean;

    @EJB
    private EquipoSessionBean equipoSessionBean;
   
        @Inject
    private PaisJSFManagedBean paisJSF;
            @Inject
    private ZonaJSFManagedBean zonaJSF;
                @Inject
    private MundialJSFManagedBean mundialJSF;
    
    
    
 private String[] equipos= new String[4];
    private String l;
    private List<Equipo> equipolist;
    private List<Equipo> zona;
    private boolean editar = false;
    private int idEquipo = -1;
    private Pais paisidPais;
    private Zona zonaidZona;
    private Mundial mundialidMundial;
    private List<String> list;
    private List<Equipo> equipoOrdenado;
    private List<Equipo> eqXzona;
    private List<Equipo> Zonas;
    private int idEquipo2 =-1;

    //Lista origen que se aplica para el uso del DragandDrop 
    private List<Equipo> equiposSource = new ArrayList<>();

    //Listas destino que se utilizan para el DragandDrop por Zpna
    private List<Equipo> equiposTargetA = new ArrayList<>();
    private List<Equipo> equiposTargetB = new ArrayList<>();
    private List<Equipo> equiposTargetC = new ArrayList<>();
    private List<Equipo> equiposTargetD = new ArrayList<>();
    private List<Equipo> equiposTargetE = new ArrayList<>();
    private List<Equipo> equiposTargetF = new ArrayList<>();
    private List<Equipo> equiposTargetG = new ArrayList<>();
    private List<Equipo> equiposTargetH = new ArrayList<>();

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

        if (this.equipolist == null) {
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

    public void onRowSelect(SelectEvent event) {
        Equipo e = ((Equipo) event.getObject());
        this.idEquipo = e.getIdEquipo();
    }
     
 public void onRowSelect2(SelectEvent event) {
        Equipo e2 = ((Equipo) event.getObject());
        this.idEquipo2 = e2.getIdEquipo();
       
        }
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
    /**
     * MÃ©todo que devuelve el fixture generado
     * @return 
     */
    public List<List<Equipo>> getObtenerFix() {

        //Pase en duro la zona 1 Uds. deben pasar la zona que deseen en cada caso.
        List<List<Equipo>> fixs =  this.equipoSessionBean.obtenerfix(9);
        
        return fixs;
    }

    public String guardar() {
        if (this.idEquipo != -1) {
            this.equipoSessionBean.modificarEquipo(idEquipo, paisidPais, zonaidZona, mundialidMundial);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo modificado con exito", ""));
        } else {
            this.equipoSessionBean.agregarEquipo(paisJSF.getIdPais(), zonaJSF.getIdZona(), mundialJSF.getIdMundial());
            this.paisidPais = null;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Equipo agregado con exito", ""));
        }
        this.equipolist = null;
        return null;
    }


    public List<Equipo> getEquiposOrdenadosPorZona() {
        return this.equipoSessionBean.obtenerEquiposOrdenZona().subList(0, 4);

    }

    public List<Equipo> getEquiposOrdenadosPorZona2() {
        return this.equipoSessionBean.obtenerEquiposOrdenZona().subList(4, 8);

    }

    public List<String> getList() {

    return null;
    }
    
    
    

    /**
     * @param list the list to set
     */
    public void setList(List<String> list) {
        this.list = list;
    }

    public void setEquipoOrdenado(List<Equipo> equipoOrdenado) {
        this.equipoOrdenado = equipoOrdenado;
    }

    public List<Equipo> getEquiposSource() {

        if (equiposSource.isEmpty()) {
            List<Pais> paisesAux = paisSessionBean.obtenerPais();
            for (Pais pais : paisesAux) {
                Equipo eAux = new Equipo();
                eAux.setPaisidPais(pais);
                equiposSource.add(eAux);
            }
        }
        return equiposSource;
    }

    public void onEquipoDrop(DragDropEvent ddEvent) {
        Equipo equi = ((Equipo) ddEvent.getData());

        equiposTargetA.add(equi);
        List<Equipo> equiposAux = new ArrayList<>();
        for (Equipo equipo : equiposSource) {
            if (!equipo.getPaisidPais().getNombre().equals(equi.getPaisidPais().getNombre())) {
                equiposAux.add(equipo);
            }
        }
        equiposSource = equiposAux;

    }

    //Get y Set de las listas de equipos destino        //Get y Set de las listas de equipos destino
    public List<Equipo> getEquiposTargetA() {
        return equiposTargetA;
    }

    public void setEquiposTargetA(List<Equipo> equiposTargetA) {
        this.equiposTargetA = equiposTargetA;
    }

    public List<Equipo> getEquiposTargetB() {
        return equiposTargetB;
    }

    public void setEquiposTargetB(List<Equipo> equiposTargetB) {
        this.equiposTargetB = equiposTargetB;
    }

    public List<Equipo> getEquiposTargetC() {
        return equiposTargetC;
    }

    public void setEquiposTargetC(List<Equipo> equiposTargetC) {
        this.equiposTargetC = equiposTargetC;
    }

    public List<Equipo> getEquiposTargetD() {
        return equiposTargetD;
    }

    public void setEquiposTargetD(List<Equipo> equiposTargetD) {
        this.equiposTargetD = equiposTargetD;
    }

    public List<Equipo> getEquiposTargetE() {
        return equiposTargetE;
    }

    public void setEquiposTargetE(List<Equipo> equiposTargetE) {
        this.equiposTargetE = equiposTargetE;
    }

    public List<Equipo> getEquiposTargetF() {
        return equiposTargetF;
    }

    public void setEquiposTargetF(List<Equipo> equiposTargetF) {
        this.equiposTargetF = equiposTargetF;
    }

    public List<Equipo> getEquiposTargetG() {
        return equiposTargetG;
    }

    public void setEquiposTargetG(List<Equipo> equiposTargetG) {
        this.equiposTargetG = equiposTargetG;
    }

    public List<Equipo> getEquiposTargetH() {
        return equiposTargetH;
    }

    public void setEquiposTargetH(List<Equipo> equiposTargetH) {
        this.equiposTargetH = equiposTargetH;
    }

    /**
     * @return the eqXzona
     */
    public List<Equipo> getEqXzona(int zona) {
        switch (zona) {
            //A
            case 1:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //B
            case 2:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //C
            case 3:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //D
            case 4:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //E
            case 5:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //F
            case 6:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //G
            case 7:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            //H
            case 8:
                this.eqXzona = this.equipoSessionBean.obtenerEquipoXzona(zona);
                break;
            default:
                return null;
        }
        return eqXzona;
    }

    /**
     * @param eqXzona the eqXzona to set
     */
    public void setEqXzona(List<Equipo> eqXzona) {
        this.eqXzona = eqXzona;
    }

    public List<Equipo> getZona(int zona1) {
        
         this.zona = this.equipoSessionBean.obtenerEquipoXzona(zona1);
        return zona;
    }

    public void setZona(List<Equipo> zona) {
        this.zona = zona;
    }

    public int getIdEquipo2() {
        return idEquipo2;
    }

    public void setIdEquipo2(int idEquipo2) {
        this.idEquipo2 = idEquipo2;
    }
  
    }

  

