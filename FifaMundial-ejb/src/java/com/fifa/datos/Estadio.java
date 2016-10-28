/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "estadio")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estadio.findAll", query = "SELECT e FROM Estadio e"),
    @NamedQuery(name = "Estadio.findByIdEstadio", query = "SELECT e FROM Estadio e WHERE e.idEstadio = :idEstadio"),
    @NamedQuery(name = "Estadio.findByNombre", query = "SELECT e FROM Estadio e WHERE e.nombre = :nombre"),
    @NamedQuery(name = "Estadio.findByAforo", query = "SELECT e FROM Estadio e WHERE e.aforo = :aforo")})
public class Estadio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEstadio")
    private Integer idEstadio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "Aforo")
    private Integer aforo;
    @JoinColumn(name = "Sede_idSede", referencedColumnName = "idSede")
    @ManyToOne(optional = false)
    private Sede sedeidSede;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estadioidEstadio")
    private Collection<Partido> partidoCollection;

    public Estadio() {
    }

    public Estadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }

    public Estadio(Integer idEstadio, String nombre) {
        this.idEstadio = idEstadio;
        this.nombre = nombre;
    }

    public Integer getIdEstadio() {
        return idEstadio;
    }

    public void setIdEstadio(Integer idEstadio) {
        this.idEstadio = idEstadio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public Sede getSedeidSede() {
        return sedeidSede;
    }

    public void setSedeidSede(Sede sedeidSede) {
        this.sedeidSede = sedeidSede;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEstadio != null ? idEstadio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estadio)) {
            return false;
        }
        Estadio other = (Estadio) object;
        if ((this.idEstadio == null && other.idEstadio != null) || (this.idEstadio != null && !this.idEstadio.equals(other.idEstadio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Estadio[ idEstadio=" + idEstadio + " ]";
    }
    
}
