/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "tarjeta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tarjeta.findAll", query = "SELECT t FROM Tarjeta t"),
    @NamedQuery(name = "Tarjeta.findByIdTarjeta", query = "SELECT t FROM Tarjeta t WHERE t.tarjetaPK.idTarjeta = :idTarjeta"),
    @NamedQuery(name = "Tarjeta.findByCantTarjAmariila", query = "SELECT t FROM Tarjeta t WHERE t.cantTarjAmariila = :cantTarjAmariila"),
    @NamedQuery(name = "Tarjeta.findByTarjetaRoja", query = "SELECT t FROM Tarjeta t WHERE t.tarjetaRoja = :tarjetaRoja"),
    @NamedQuery(name = "Tarjeta.findBySancion", query = "SELECT t FROM Tarjeta t WHERE t.sancion = :sancion"),
    @NamedQuery(name = "Tarjeta.findByJugadoresXPartidoidJugadorXpartido", query = "SELECT t FROM Tarjeta t WHERE t.tarjetaPK.jugadoresXPartidoidJugadorXpartido = :jugadoresXPartidoidJugadorXpartido")})
public class Tarjeta implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TarjetaPK tarjetaPK;
    @Column(name = "cantTarjAmariila")
    private Integer cantTarjAmariila;
    @Column(name = "tarjetaRoja")
    private Integer tarjetaRoja;
    @Column(name = "sancion")
    private Integer sancion;
    @JoinColumn(name = "JugadoresXPartido_idJugadorXpartido", referencedColumnName = "idJugadorXpartido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jugadoresxpartido jugadoresxpartido;

    public Tarjeta() {
    }

    public Tarjeta(TarjetaPK tarjetaPK) {
        this.tarjetaPK = tarjetaPK;
    }

    public Tarjeta(int idTarjeta, String jugadoresXPartidoidJugadorXpartido) {
        this.tarjetaPK = new TarjetaPK(idTarjeta, jugadoresXPartidoidJugadorXpartido);
    }

    public TarjetaPK getTarjetaPK() {
        return tarjetaPK;
    }

    public void setTarjetaPK(TarjetaPK tarjetaPK) {
        this.tarjetaPK = tarjetaPK;
    }

    public Integer getCantTarjAmariila() {
        return cantTarjAmariila;
    }

    public void setCantTarjAmariila(Integer cantTarjAmariila) {
        this.cantTarjAmariila = cantTarjAmariila;
    }

    public Integer getTarjetaRoja() {
        return tarjetaRoja;
    }

    public void setTarjetaRoja(Integer tarjetaRoja) {
        this.tarjetaRoja = tarjetaRoja;
    }

    public Integer getSancion() {
        return sancion;
    }

    public void setSancion(Integer sancion) {
        this.sancion = sancion;
    }

    public Jugadoresxpartido getJugadoresxpartido() {
        return jugadoresxpartido;
    }

    public void setJugadoresxpartido(Jugadoresxpartido jugadoresxpartido) {
        this.jugadoresxpartido = jugadoresxpartido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tarjetaPK != null ? tarjetaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tarjeta)) {
            return false;
        }
        Tarjeta other = (Tarjeta) object;
        if ((this.tarjetaPK == null && other.tarjetaPK != null) || (this.tarjetaPK != null && !this.tarjetaPK.equals(other.tarjetaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Tarjeta[ tarjetaPK=" + tarjetaPK + " ]";
    }
    
}
