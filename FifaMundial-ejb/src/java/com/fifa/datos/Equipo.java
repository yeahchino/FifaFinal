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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfeitt
 */
@Entity
@Table(name = "equipo")
@XmlRootElement
@NamedQueries({
       @NamedQuery(name = "Equipo.findAll", query = "SELECT e FROM Equipo e"),
    @NamedQuery(name = "Equipo.findByIdEquipo", query = "SELECT e FROM Equipo e WHERE e.idEquipo = :idEquipo"),
    @NamedQuery(name = "Equipo.findEquipoByZona", query = "SELECT e FROM Equipo e order by e.zonaidZona, e.idEquipo"),
    @NamedQuery(name = "Equipo.findEquipoByIdZona", query = "SELECT e FROM Equipo e WHERE e.zonaidZona.idZona = :idZona order by e.zonaidZona, e.idEquipo"),
    @NamedQuery(name = "Equipo.findByIdZona", query = "SELECT e FROM Equipo e JOIN e.zonaidZona z where z.idZona = :idZona")})

public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idEquipo")
    private Integer idEquipo;
    @JoinTable(name = "jugadorxequipo", joinColumns = {
        @JoinColumn(name = "Equipo_idEquipo", referencedColumnName = "idEquipo")}, inverseJoinColumns = {
        @JoinColumn(name = "Jugador_idJugador", referencedColumnName = "idJugador")})
    @ManyToMany
    private Collection<Jugador> jugadorCollection;
    @ManyToMany(mappedBy = "equipoCollection")
    private Collection<Cuerpotecnico> cuerpotecnicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoidEquipo")
    private Collection<Jugadoresxpartido> jugadoresxpartidoCollection;
    @JoinColumn(name = "Mundial_idMundial", referencedColumnName = "idMundial")
    @ManyToOne(optional = false)
    private Mundial mundialidMundial;
    @JoinColumn(name = "Pais_idPais", referencedColumnName = "idPais")
    @ManyToOne(optional = false)
    private Pais paisidPais;
    @JoinColumn(name = "Zona_idZona", referencedColumnName = "idZona")
    @ManyToOne
    private Zona zonaidZona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoidEquipoA")
    private Collection<Partido> partidoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipoidEquipoB")
    private Collection<Partido> partidoCollection1;

    public Equipo() {
    }

    public Equipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    public Integer getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(Integer idEquipo) {
        this.idEquipo = idEquipo;
    }

    @XmlTransient
    public Collection<Jugador> getJugadorCollection() {
        return jugadorCollection;
    }

    public void setJugadorCollection(Collection<Jugador> jugadorCollection) {
        this.jugadorCollection = jugadorCollection;
    }

    @XmlTransient
    public Collection<Cuerpotecnico> getCuerpotecnicoCollection() {
        return cuerpotecnicoCollection;
    }

    public void setCuerpotecnicoCollection(Collection<Cuerpotecnico> cuerpotecnicoCollection) {
        this.cuerpotecnicoCollection = cuerpotecnicoCollection;
    }

    @XmlTransient
    public Collection<Jugadoresxpartido> getJugadoresxpartidoCollection() {
        return jugadoresxpartidoCollection;
    }

    public void setJugadoresxpartidoCollection(Collection<Jugadoresxpartido> jugadoresxpartidoCollection) {
        this.jugadoresxpartidoCollection = jugadoresxpartidoCollection;
    }

    public Mundial getMundialidMundial() {
        return mundialidMundial;
    }

    public void setMundialidMundial(Mundial mundialidMundial) {
        this.mundialidMundial = mundialidMundial;
    }

    public Pais getPaisidPais() {
        return paisidPais;
    }

    public void setPaisidPais(Pais paisidPais) {
        this.paisidPais = paisidPais;
    }

    public Zona getZonaidZona() {
        return zonaidZona;
    }

    public void setZonaidZona(Zona zonaidZona) {
        this.zonaidZona = zonaidZona;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection1() {
        return partidoCollection1;
    }

    public void setPartidoCollection1(Collection<Partido> partidoCollection1) {
        this.partidoCollection1 = partidoCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEquipo != null ? idEquipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Equipo)) {
            return false;
        }
        Equipo other = (Equipo) object;
        if ((this.idEquipo == null && other.idEquipo != null) || (this.idEquipo != null && !this.idEquipo.equals(other.idEquipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Equipo[ idEquipo=" + idEquipo + " ]";
    }

}
