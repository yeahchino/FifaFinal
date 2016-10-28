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
@Table(name = "puestoctecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Puestoctecnico.findAll", query = "SELECT p FROM Puestoctecnico p"),
    @NamedQuery(name = "Puestoctecnico.findByIdPuestoCTecnico", query = "SELECT p FROM Puestoctecnico p WHERE p.idPuestoCTecnico = :idPuestoCTecnico"),
    @NamedQuery(name = "Puestoctecnico.findByNombre", query = "SELECT p FROM Puestoctecnico p WHERE p.nombre = :nombre")})
public class Puestoctecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPuestoCTecnico")
    private Integer idPuestoCTecnico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @ManyToMany(mappedBy = "puestoctecnicoCollection")
    private Collection<Cuerpotecnico> cuerpotecnicoCollection;

    public Puestoctecnico() {
    }

    public Puestoctecnico(Integer idPuestoCTecnico) {
        this.idPuestoCTecnico = idPuestoCTecnico;
    }

    public Puestoctecnico(Integer idPuestoCTecnico, String nombre) {
        this.idPuestoCTecnico = idPuestoCTecnico;
        this.nombre = nombre;
    }

    public Integer getIdPuestoCTecnico() {
        return idPuestoCTecnico;
    }

    public void setIdPuestoCTecnico(Integer idPuestoCTecnico) {
        this.idPuestoCTecnico = idPuestoCTecnico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Cuerpotecnico> getCuerpotecnicoCollection() {
        return cuerpotecnicoCollection;
    }

    public void setCuerpotecnicoCollection(Collection<Cuerpotecnico> cuerpotecnicoCollection) {
        this.cuerpotecnicoCollection = cuerpotecnicoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPuestoCTecnico != null ? idPuestoCTecnico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Puestoctecnico)) {
            return false;
        }
        Puestoctecnico other = (Puestoctecnico) object;
        if ((this.idPuestoCTecnico == null && other.idPuestoCTecnico != null) || (this.idPuestoCTecnico != null && !this.idPuestoCTecnico.equals(other.idPuestoCTecnico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Puestoctecnico[ idPuestoCTecnico=" + idPuestoCTecnico + " ]";
    }
    
}
