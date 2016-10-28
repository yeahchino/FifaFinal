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
@Table(name = "jugadoresxpartido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jugadoresxpartido.findAll", query = "SELECT j FROM Jugadoresxpartido j"),
    @NamedQuery(name = "Jugadoresxpartido.findByIdJugadorXpartido", query = "SELECT j FROM Jugadoresxpartido j WHERE j.idJugadorXpartido = :idJugadorXpartido"),
    @NamedQuery(name = "Jugadoresxpartido.findByTitular", query = "SELECT j FROM Jugadoresxpartido j WHERE j.titular = :titular"),
    @NamedQuery(name = "Jugadoresxpartido.findBySuplente", query = "SELECT j FROM Jugadoresxpartido j WHERE j.suplente = :suplente")})
public class Jugadoresxpartido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idJugadorXpartido")
    private String idJugadorXpartido;
    @Column(name = "titular")
    private Boolean titular;
    @Column(name = "suplente")
    private Boolean suplente;
    @JoinColumn(name = "Jugador_idJugador", referencedColumnName = "idJugador")
    @ManyToOne(optional = false)
    private Jugador jugadoridJugador;
    @JoinColumn(name = "Equipo_idEquipo", referencedColumnName = "idEquipo")
    @ManyToOne(optional = false)
    private Equipo equipoidEquipo;
    @JoinColumn(name = "Partido_idPartido", referencedColumnName = "idPartido")
    @ManyToOne(optional = false)
    private Partido partidoidPartido;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "jugadoresxpartido")
    private Collection<Tarjeta> tarjetaCollection;

    public Jugadoresxpartido() {
    }

    public Jugadoresxpartido(String idJugadorXpartido) {
        this.idJugadorXpartido = idJugadorXpartido;
    }

    public String getIdJugadorXpartido() {
        return idJugadorXpartido;
    }

    public void setIdJugadorXpartido(String idJugadorXpartido) {
        this.idJugadorXpartido = idJugadorXpartido;
    }

    public Boolean getTitular() {
        return titular;
    }

    public void setTitular(Boolean titular) {
        this.titular = titular;
    }

    public Boolean getSuplente() {
        return suplente;
    }

    public void setSuplente(Boolean suplente) {
        this.suplente = suplente;
    }

    public Jugador getJugadoridJugador() {
        return jugadoridJugador;
    }

    public void setJugadoridJugador(Jugador jugadoridJugador) {
        this.jugadoridJugador = jugadoridJugador;
    }

    public Equipo getEquipoidEquipo() {
        return equipoidEquipo;
    }

    public void setEquipoidEquipo(Equipo equipoidEquipo) {
        this.equipoidEquipo = equipoidEquipo;
    }

    public Partido getPartidoidPartido() {
        return partidoidPartido;
    }

    public void setPartidoidPartido(Partido partidoidPartido) {
        this.partidoidPartido = partidoidPartido;
    }

    @XmlTransient
    public Collection<Tarjeta> getTarjetaCollection() {
        return tarjetaCollection;
    }

    public void setTarjetaCollection(Collection<Tarjeta> tarjetaCollection) {
        this.tarjetaCollection = tarjetaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJugadorXpartido != null ? idJugadorXpartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jugadoresxpartido)) {
            return false;
        }
        Jugadoresxpartido other = (Jugadoresxpartido) object;
        if ((this.idJugadorXpartido == null && other.idJugadorXpartido != null) || (this.idJugadorXpartido != null && !this.idJugadorXpartido.equals(other.idJugadorXpartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Jugadoresxpartido[ idJugadorXpartido=" + idJugadorXpartido + " ]";
    }
    
}
