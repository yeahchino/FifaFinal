/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import com.fifa.datos.Usuario;
import com.fifa.datos.Tipousuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class UsuarioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    public void persist(Object object) {
        em.persist(object);
    }
    
    //Capa de Uuario
    
    public boolean agregarUsuario(String nombre, String contraseña, Tipousuario Tipousuario) {
        try {
            Usuario a = new Usuario();
            a.setNombre(nombre);
            a.setContraseña(contraseña);
            a.setTipoUsuarioidTipo(Tipousuario);
            em.persist(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean modificarUsuario(int idUsuario, String nombre, String contraseña, Tipousuario Tipousuario) {
        try {
            Usuario a = em.find(Usuario.class, idUsuario);
            a.setNombre(nombre);
            a.setContraseña(contraseña);
            a.setTipoUsuarioidTipo(Tipousuario);
            em.merge(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean borrarUsuario(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Usuario.class);
            Usuario a = em.find(Usuario.class, idUsuario);
            em.remove(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<Usuario> obtenerUsuario()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Usuario> obtenerUsuarioId ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findByIdUsuario");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Usuario> obtenerUsuarioNombre ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
     public List<Usuario> obtenerUsuarioContraseña ()
            {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findByContraseña");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
          
     //Capa de TipoUsuario
     
     public boolean agregarTipoUsuario(String nombre) {
        try {
            Usuario a = new Usuario();
            a.setNombre(nombre);
            em.persist(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarTipoUsuario(int idUsuario, String nombre) {
        try {
            Usuario a = em.find(Usuario.class, idUsuario);
            a.setNombre(nombre);
            em.merge(a);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean borrarTipoUsuario(int idTipoUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Usuario.class);
            Usuario a = em.find(Usuario.class, idTipoUsuario);
            em.remove(a);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public List<Tipousuario> obtenerTipoUsuario()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Tipousuario.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Tipousuario> obtenerTipoUsuarioNombre()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Tipousuario.findByNombre");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Tipousuario> obtenerTipoUsuarioXTipo()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Tipousuario.findByIdTipo");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
}