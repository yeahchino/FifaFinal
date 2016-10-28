/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfeitt
 */
@Entity
@Table(name = "mundial")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mundial.findAll", query = "SELECT m FROM Mundial m")
    , @NamedQuery(name = "Mundial.findByIdMundial", query = "SELECT m FROM Mundial m WHERE m.idMundial = :idMundial")
    , @NamedQuery(name = "Mundial.findByFechaInicio", query = "SELECT m FROM Mundial m WHERE m.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "Mundial.findByFechaFin", query = "SELECT m FROM Mundial m WHERE m.fechaFin = :fechaFin")})
public class Mundial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMundial")
    private Integer idMundial;
    @Column(name = "fechaInicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @ManyToMany(mappedBy = "mundialCollection")
    private Collection<Arbitro> arbitroCollection;
    @JoinTable(name = "organizadorxmundial", joinColumns = {
        @JoinColumn(name = "Mundial_idMundial", referencedColumnName = "idMundial")}, inverseJoinColumns = {
        @JoinColumn(name = "Organizador_idOrganizador", referencedColumnName = "idOrganizador")})
    @ManyToMany
    private Collection<Organizador> organizadorCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mundialidMundial")
    private Collection<Equipo> equipoCollection;
    @JoinColumn(name = "Pais_idPais", referencedColumnName = "idPais")
    @ManyToOne(optional = false)
    private Pais paisidPais;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mundialidMundial")
    private Collection<Partido> partidoCollection;

    public Mundial() {
    }

    public Mundial(Integer idMundial) {
        this.idMundial = idMundial;
    }

    public Integer getIdMundial() {
        return idMundial;
    }

    public void setIdMundial(Integer idMundial) {
        this.idMundial = idMundial;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @XmlTransient
    public Collection<Arbitro> getArbitroCollection() {
        return arbitroCollection;
    }

    public void setArbitroCollection(Collection<Arbitro> arbitroCollection) {
        this.arbitroCollection = arbitroCollection;
    }

    @XmlTransient
    public Collection<Organizador> getOrganizadorCollection() {
        return organizadorCollection;
    }

    public void setOrganizadorCollection(Collection<Organizador> organizadorCollection) {
        this.organizadorCollection = organizadorCollection;
    }

    @XmlTransient
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    public Pais getPaisidPais() {
        return paisidPais;
    }

    public void setPaisidPais(Pais paisidPais) {
        this.paisidPais = paisidPais;
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
        hash += (idMundial != null ? idMundial.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mundial)) {
            return false;
        }
        Mundial other = (Mundial) object;
        if ((this.idMundial == null && other.idMundial != null) || (this.idMundial != null && !this.idMundial.equals(other.idMundial))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Mundial[ idMundial=" + idMundial + " ]";
    }
    
}
