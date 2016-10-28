/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Alexia
 */
@Named(value = "menuTopJSFManagedBean")
@SessionScoped
public class MenuTopJSFManagedBean implements Serializable {

    private String orientation = "horizontal";
 
    public String getOrientation() {
        return orientation;
    }
 
    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }
    public MenuTopJSFManagedBean() {
    }
    
}
