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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    private String nombreBienvenida;
    private String contraseña;
    private String tipo;
    private List<Usuario> usuarios;
    private String codSeguridad;

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

    public boolean passContenga() {
        //Contenga
        Pattern p1 = Pattern.compile(".*012.*");
        Pattern p2 = Pattern.compile(".*123.*");
        Pattern p3 = Pattern.compile(".*234.*");
        Pattern p4 = Pattern.compile(".*345.*");
        Pattern p5 = Pattern.compile(".*456.*");
        Pattern p6 = Pattern.compile(".*567.*");
        Pattern p7 = Pattern.compile(".*678.*");
        Pattern p8 = Pattern.compile(".*789.*");
        Pattern p9 = Pattern.compile(".*987.*");
        Pattern p10 = Pattern.compile(".*876.*");
        Pattern p11 = Pattern.compile(".*765.*");
        Pattern p12 = Pattern.compile(".*654.*");
        Pattern p13 = Pattern.compile(".*543.*");
        Pattern p14 = Pattern.compile(".*432.*");
        Pattern p15 = Pattern.compile(".*321.*");
        Pattern p16 = Pattern.compile(".*210.*");

        Matcher m1 = p1.matcher(contraseña);
        Matcher m2 = p2.matcher(contraseña);
        Matcher m3 = p3.matcher(contraseña);
        Matcher m4 = p4.matcher(contraseña);
        Matcher m5 = p5.matcher(contraseña);
        Matcher m6 = p6.matcher(contraseña);
        Matcher m7 = p7.matcher(contraseña);
        Matcher m8 = p8.matcher(contraseña);
        Matcher m9 = p9.matcher(contraseña);
        Matcher m10 = p10.matcher(contraseña);
        Matcher m11 = p11.matcher(contraseña);
        Matcher m12 = p12.matcher(contraseña);
        Matcher m13 = p13.matcher(contraseña);
        Matcher m14 = p14.matcher(contraseña);
        Matcher m15 = p15.matcher(contraseña);
        Matcher m16 = p16.matcher(contraseña);

        if (m1.matches() || m2.matches() || m3.matches() || m4.matches() || m5.matches() || m6.matches() || m7.matches() || m8.matches()
                || m9.matches() || m10.matches() || m11.matches() || m12.matches() || m13.matches() || m14.matches() || m15.matches() || m16.matches()) {
            //contiene
            return true;
        } else {
            //no contiene
            return false;
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
            return false;
        } else if (cantEspacios != 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña no puede contener espacios", ""));
            return false;
        } else if (cantEspeciales < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un caracter especial", ""));
            return false;
        } else if (cantLetrasMayus < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un letra mayúscula", ""));
            return false;
        } else if (cantLetrasMinus < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un letra minúscula", ""));
            return false;
        } else if (cantNumeros < 1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña debe contener al menos un número", ""));
            return false;
        } else if (passContenga()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "La contraseña no puede contener más 3 números consecutivos en orden ascendente o descendente ", ""));
            return false;
        } else {
            return true; //pass valida
        }
    }

    //*val nom usuario
    public void guardar() {

        if (validarPass()) {
            this.usuarioSessionBean.agregarUsuario(nombre, contraseña, idTipo());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado con éxito", ""));
            this.nombre = "";
            this.contraseña = "";
        }
    }

    public void mensaje() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario guardado con éxito", ""));
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

    int c = 0;

    public String inciarSesion() {
        Usuario us;
        Usuario us2;
        String redireccion = null;
        String msj= usuarioSessionBean.generarPass();
        
        us = usuarioSessionBean.iniciarSesion(usuario);
        us2 = usuarioSessionBean.verEmail(usuario);
        if (us != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", us);
            setNombreBienvenida(us.getNombre());
            tipo = Integer.toString(us.getTipoUsuarioidTipo().getIdTipo());
            redireccion = "IndexAdm.xhtml";
        } else if (us2 == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "El usuario no existe", ""));
        } else if (c < 3) {
            c++;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Contraseña incorrecta", ""));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Usuario bloqueado. Se enviara un e-mail a su casilla de correo para restablecer su contraseña", ""));
            String mail = us2.getEmail();
            usuarioSessionBean.bloqueoCuenta(us2.getNombre());
            usuarioSessionBean.SendMail(mail,us2.getNombre());
            
            c=0;
        }
        return redireccion;
    }

    public String bienvenido() {
        return "¡¡Bienvenido " + getNombreBienvenida() + "!!";
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

    /**
     * @return the nombreBienvenida
     */
    public String getNombreBienvenida() {
        return nombreBienvenida;
    }

    /**
     * @param nombreBienvenida the nombreBienvenida to set
     */
    public void setNombreBienvenida(String nombreBienvenida) {
        this.nombreBienvenida = nombreBienvenida;
    }

    /**
     * @return the codSeguridad
     */
    public String getCodSeguridad() {
        return codSeguridad;
    }

    /**
     * @param codSeguridad the codSeguridad to set
     */
    public void setCodSeguridad(String codSeguridad) {
        this.codSeguridad = codSeguridad;
    }
    
    public void cambiarPass(){
        
    }

}
