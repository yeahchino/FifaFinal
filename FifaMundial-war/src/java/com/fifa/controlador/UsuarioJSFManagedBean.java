/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.controlador;

import com.fifa.datos.Usuario;
import com.fifa.negocio.UsuarioSessionBean;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Adriana
 */
@Named(value = "usuarioJSFManagedBean")
@SessionScoped
public class UsuarioJSFManagedBean implements Serializable {

    @EJB
    private UsuarioSessionBean usuarioSessionBean;
    private Usuario usuario;
    private int idTipoUsuario;
    private String nombre;
    private String contraseña;
    private String tipo;
    private List<Usuario> usuarios;

    @PostConstruct
    public void init() {

        usuario = new Usuario();

    }

    public UsuarioJSFManagedBean() {
    }
    //observar las creaciones de los objetos, no se como se hace para crear dos objetos en la misma clase

    /**
     * @return the idTipoUsuario
     */
    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    /**
     * @param idTipoUsuario the idTipoUsuario to set
     */
    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
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
     * @return the contraseña
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * @param contraseña the contraseña to set
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    //para saber que id tiene el usuario elegido
    public int idTipo() {
        if (tipo.compareTo("Admin") == 0) {
            return 1;
        } else {
            return 2;
        }
    }

    public boolean validarPass() {

        int cantEspeciales = 0;
        int cantLetrasMayus = 0;
        int cantLetrasMinus = 0;
        int cantNumeros = 0;
        int cantEspacios = 0;

        byte[] bytes = contraseña.getBytes();
        for (byte tempByte : bytes) {
            if (tempByte >= 33 && tempByte <= 47) {
                cantEspeciales++;
            }

            char tempChar = (char) tempByte;
            if (Character.isDigit(tempChar)) {
                cantNumeros++;
            }

            if (Character.isUpperCase(tempChar)) {
                cantLetrasMayus++;
            }

            if (Character.isLowerCase(tempChar)) {
                cantLetrasMinus++;
            }

            if (Character.isSpaceChar(tempChar)) {
                cantEspacios++;
            }
        }

        int cantCaracteres = contraseña.trim().length();

        if (cantCaracteres < 5) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener 5 caracteres como mínimo", ""));

        } else if (cantEspacios != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña no puede contener espacios", ""));
        } else if (cantEspeciales < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un caracter especial", ""));

        } else if (cantLetrasMayus < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un letra mayúscula", ""));
        } else if (cantLetrasMinus < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un letra minúscula", ""));
        } else if (cantNumeros < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un número", ""));
        }
        
        return true;
    }

    //*val nom usuario
    public String guardar() {

        this.usuarioSessionBean.agregarUsuario(getNombre(), getContraseña(), idTipo());
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado con éxito", ""));
        this.nombre = "";
        this.contraseña = "";
        this.tipo = "";
        return null;
    }

    /**
     * @return the usuarios
     */
    public List<Usuario> getUsuarios() {
        if (this.usuarios == null) {
            this.usuarios = this.usuarioSessionBean.obtenerUsuario();
        }
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public String eliminar(int id) {
        this.usuarioSessionBean.borrarUsuario(idTipoUsuario);
        this.usuarios = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario eliminado con éxito", ""));
        return null;
    }

    /**
     * @return the usuario
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String inciarSesion() {
        Usuario us;
        String redireccion = null;
        //redireccion = "IndexAdm.xhtml";
        try {
            us = usuarioSessionBean.iniciarSesion(usuario);
            if (us != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
                nombre = us.getNombre();
                tipo = Integer.toString(us.getTipoUsuarioidTipo().getIdTipo());

                redireccion = "IndexAdm.xhtml";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Usuario o contraseña erronea", ""));
            }
        } catch (Exception e) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", ""));
        }

        return redireccion;

    }

    public String bienvenido() {
        return "¡¡Bienvenido " + nombre + "!!";
    }

    public boolean verTipoUsuario() {
        if (tipo.compareTo("1") == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void verificarSesion() {
        try {
            Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if (us == null) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/LogUser.xhtml");
            }
        } catch (Exception e) {
        }
    }

    public void cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }

}
