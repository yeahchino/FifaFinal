/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.negocio.UsuarioSessionBean;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author Adriana
 */
@Named(value = "usuarioJSFManagedBean")
@SessionScoped
public class UsuarioJSFManagedBean implements Serializable {

    @EJB
    private UsuarioSessionBean usuarioSessionBean;

    
    public UsuarioJSFManagedBean() {
    }
    
}
