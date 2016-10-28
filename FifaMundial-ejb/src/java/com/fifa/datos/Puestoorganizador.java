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
@Table(name = "puestoorganizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puestoorganizador.findAll", query = "SELECT p FROM Puestoorganizador p"),
    @NamedQuery(name = "Puestoorganizador.findByIdPuestoOrg", query = "SELECT p FROM Puestoorganizador p WHERE p.idPuestoOrg = :idPuestoOrg"),
    @NamedQuery(name = "Puestoorganizador.findByNombre", query = "SELECT p FROM Puestoorganizador p WHERE p.nombre = :nombre")})
public class Puestoorganizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPuestoOrg")
    private Integer idPuestoOrg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "puestoorganizadorCollection")
    private Collection<Organizador> organizadorCollection;

    public Puestoorganizador() {
    }

    public Puestoorganizador(Integer idPuestoOrg) {
        this.idPuestoOrg = idPuestoOrg;
    }

    public Puestoorganizador(Integer idPuestoOrg, String nombre) {
        this.idPuestoOrg = idPuestoOrg;
        this.nombre = nombre;
    }

    public Integer getIdPuestoOrg() {
        return idPuestoOrg;
    }

    public void setIdPuestoOrg(Integer idPuestoOrg) {
        this.idPuestoOrg = idPuestoOrg;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Organizador> getOrganizadorCollection() {
        return organizadorCollection;
    }

    public void setOrganizadorCollection(Collection<Organizador> organizadorCollection) {
        this.organizadorCollection = organizadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuestoOrg != null ? idPuestoOrg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puestoorganizador)) {
            return false;
        }
        Puestoorganizador other = (Puestoorganizador) object;
        if ((this.idPuestoOrg == null && other.idPuestoOrg != null) || (this.idPuestoOrg != null && !this.idPuestoOrg.equals(other.idPuestoOrg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Puestoorganizador[ idPuestoOrg=" + idPuestoOrg + " ]";
    }
    
}
