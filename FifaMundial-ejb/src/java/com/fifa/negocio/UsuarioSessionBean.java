/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.negocio;

import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import com.fifa.datos.Tipousuario;
import com.fifa.datos.Usuario;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class UsuarioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

    //Tipousuario
    
    public List<Tipousuario> obtenerTipousuario()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Tipousuario.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Tipousuario> obtenerTipousuarioNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Tipousuario.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Tipousuario obtenerTipousuarioId(int idTipousuario)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Tipousuario.class);
            Tipousuario a = em.find(Tipousuario.class, idTipousuario);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarTipousuario(int idTipousuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Tipousuario.class);
            Tipousuario p = em.find(Tipousuario.class, idTipousuario);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarTipousuario( String nombre) {
        try {
            Tipousuario p = new Tipousuario();
            p.setNombre(nombre);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarTipousuario(int idTipousuario, String nombre) {
        try {
            Tipousuario p = em.find(Tipousuario.class, idTipousuario);
            p.setNombre(nombre);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    // Usuario
     
     public List<Usuario> obtenerUsuario()
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Usuario> obtenerUsuarioNombre(String nombre)
     {
         try {
             javax.persistence.Query q= em.createNamedQuery("Usuario.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public Usuario obtenerUsuarioId(int idUsuario)
     {
         try {
            em.getEntityManagerFactory().getCache().evict(Usuario.class);
            Usuario a = em.find(Usuario.class, idUsuario);
            return a;
        } catch (Exception e) {
             return null;
         }
     }
    
    public boolean borrarUsuario(int idUsuario) {
        try {
            em.getEntityManagerFactory().getCache().evict(Usuario.class);
            Usuario p = em.find(Usuario.class, idUsuario);
            em.remove(p); 
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean agregarUsuario(String nombre, String contraseña, Tipousuario tipoUsuarioidTipo) {
        try {
            Usuario p = new Usuario();
            p.setNombre(nombre);
            p.setContraseña(contraseña);
            p.setTipoUsuarioidTipo(tipoUsuarioidTipo);
            em.persist(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
     public boolean modificarUsuario(int idUsuario, String nombre, String contraseña, Tipousuario tipoUsuarioidTipo) {
        try {
            Usuario p = em.find(Usuario.class, idUsuario);
            p.setNombre(nombre);
            p.setContraseña(contraseña);
            p.setTipoUsuarioidTipo(tipoUsuarioidTipo);
            em.merge(p);
            em.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     public void persist(Object object) {
        em.persist(object);
    }
         
}
