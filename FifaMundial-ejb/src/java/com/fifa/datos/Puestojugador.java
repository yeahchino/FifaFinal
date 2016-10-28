/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "puestojugador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puestojugador.findAll", query = "SELECT p FROM Puestojugador p"),
    @NamedQuery(name = "Puestojugador.findByIdPuestoJug", query = "SELECT p FROM Puestojugador p WHERE p.idPuestoJug = :idPuestoJug"),
    @NamedQuery(name = "Puestojugador.findByNombre", query = "SELECT p FROM Puestojugador p WHERE p.nombre = :nombre")})
public class Puestojugador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPuestoJug")
    private Integer idPuestoJug;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "puestojugadorCollection")
    private Collection<Jugador> jugadorCollection;

    public Puestojugador() {
    }

    public Puestojugador(Integer idPuestoJug) {
        this.idPuestoJug = idPuestoJug;
    }

    public Puestojugador(Integer idPuestoJug, String nombre) {
        this.idPuestoJug = idPuestoJug;
        this.nombre = nombre;
    }

    public Integer getIdPuestoJug() {
        return idPuestoJug;
    }

    public void setIdPuestoJug(Integer idPuestoJug) {
        this.idPuestoJug = idPuestoJug;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Jugador> getJugadorCollection() {
        return jugadorCollection;
    }

    public void setJugadorCollection(Collection<Jugador> jugadorCollection) {
        this.jugadorCollection = jugadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuestoJug != null ? idPuestoJug.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puestojugador)) {
            return false;
        }
        Puestojugador other = (Puestojugador) object;
        if ((this.idPuestoJug == null && other.idPuestoJug != null) || (this.idPuestoJug != null && !this.idPuestoJug.equals(other.idPuestoJug))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Puestojugador[ idPuestoJug=" + idPuestoJug + " ]";
    }
    
}
