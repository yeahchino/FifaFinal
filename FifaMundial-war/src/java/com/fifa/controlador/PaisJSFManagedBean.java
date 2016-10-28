/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Pais;
import com.fifa.negocio.PaisSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Usuario
 */
@Named(value = "paisJSFManagedBean")
@SessionScoped
public class PaisJSFManagedBean implements Serializable {

    @EJB
    private PaisSessionBean paisSessionBean;

    private List<Pais> Pais;
    /**
     * Creates a new instance of PaisJSFManagedBean
     */
    public PaisJSFManagedBean() {
    }
    
    /**
     * @return the Pais
     */
    public List<Pais> getPais() {
        if(this.Pais == null)
        {
            this.Pais = this.paisSessionBean.obtenerPais();
        }
        return Pais;
    }
    
    /**
     * @param Pais the Pais to set
     */
    public void setPais(List<Pais> Pais) {
        this.Pais = Pais;
    }
}
