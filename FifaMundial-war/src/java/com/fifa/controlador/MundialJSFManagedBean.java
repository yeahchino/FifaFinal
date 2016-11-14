/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Mundial;
import com.fifa.datos.Pais;
import com.fifa.negocio.MundialSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Alexia
 */
@Named(value = "mundialJSFManagedBean")
@SessionScoped
public class MundialJSFManagedBean implements Serializable {

    @EJB
    private MundialSessionBean mundialSessionBean;

    private List<Mundial> mundial;
    private int idMundial;
    private Date fechaInicio;
    private Date fechaFin;

    @Inject
    private PaisJSFManagedBean paisJSF;

    public MundialJSFManagedBean() {
    }

    /**
     * @return the mundialSessionBean
     */
    public MundialSessionBean getMundialSessionBean() {
        return mundialSessionBean;
    }

    /**
     * @param mundialSessionBean the mundialSessionBean to set
     */
    public void setMundialSessionBean(MundialSessionBean mundialSessionBean) {
        this.mundialSessionBean = mundialSessionBean;
    }

    /**
     * @return the mundial
     */
    public List<Mundial> getMundial() {
        if (this.mundial == null) {
            this.mundial = this.mundialSessionBean.ObtenerMundiales();
        }
        return mundial; 
    }
    
    //ver
    public String nombreMundial() {
        if (null == fechaInicio) {
            return null;
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
            int año= Integer.parseInt(dateFormat.format(fechaInicio));
            return paisJSF.getNombre()+" "+año;
        }
    }

    /**
     * @param mundial the mundial to set
     */
    public void setMundial(List<Mundial> mundial) {
        this.mundial = mundial;
    }

    /**
     * @return the idMundial
     */
    public int getIdMundial() {
        return idMundial;
    }

    /**
     * @param idMundial the idMundial to set
     */
    public void setIdMundial(int idMundial) {
        this.idMundial = idMundial;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    //Para calendario
    public void onDateSelect(SelectEvent event) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
    }

    //click en calendario
    public void click() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.update("form:display");
        requestContext.execute("PF('dlg').show()");
    }

    /**
     * @return the paisJSF
     */
    public PaisJSFManagedBean getPaisJSF() {
        return paisJSF;
    }

    /**
     * @param paisJSF the paisJSF to set
     */
    public void setPaisJSF(PaisJSFManagedBean paisJSF) {
        this.paisJSF = paisJSF;
    }

    public String guardar() {
        FacesContext.getCurrentInstance().addMessage(
                null, new FacesMessage(FacesMessage.SEVERITY_WARN, fechaInicio + " " + fechaFin + " " + paisJSF.getIdPais(), ""));
        if (paisJSF.getIdPais() <= -1) {
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Debe seleccionar un país", ""));
        } else {
            this.mundialSessionBean.agregarMundial(fechaInicio, fechaFin, paisJSF.getIdPais());

//            this.fechaInicio = null;
//            this.fechaFin = null;
//            this.paisJSF = null;
            FacesContext.getCurrentInstance().addMessage(
                    null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El mundial ha sido guardado con éxito", ""));
        }
        return null;
    }

//    public void onRowSelect(SelectEvent event) {
//        Mundial m = ((Mundial) event.getObject());
//        this.idMundial = m.getIdMundial();
//        //this.nombre = p.getNombre();
//        FacesContext.getCurrentInstance().addMessage(
//                null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Selecciono " + idMundial, ""));
//    }

}
