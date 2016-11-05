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
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Usuario
 */
@Stateless
@LocalBean
public class UsuarioSessionBean {

    @PersistenceContext(unitName = "FifaMundial-ejbPU")
    private EntityManager em;

     public List<Usuario> obtenerUsuario()
     {
         try {
             Query q= em.createNamedQuery("Usuario.findAll");
             return q.getResultList();
         } catch (Exception e) {
             return null;
         }
     }
    
    public List<Usuario> obtenerUsuarioNombre(String nombre)
     {
         try {
             Query q= em.createNamedQuery("Usuario.findByNombre");
             q.setParameter("nombre", "%" + nombre);
             return q.getResultList();
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


    public boolean agregarUsuario(String nombre, String contraseña, int idTipoUsuario) {
        try {
            Tipousuario tipo = em.find(Tipousuario.class, idTipoUsuario);
            if (tipo != null) {
            Usuario p = new Usuario();
            p.setNombre(nombre);
            p.setContraseña(contraseña);
            p.setTipoUsuarioidTipo(tipo);
            em.persist(p);
            em.flush();
            }
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
