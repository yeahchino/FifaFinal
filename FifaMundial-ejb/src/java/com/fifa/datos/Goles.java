/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dfeitt
 */
@Entity
@Table(name = "goles")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Goles.findAll", query = "SELECT g FROM Goles g")
    , @NamedQuery(name = "Goles.findByPartidoidPartido", query = "SELECT g FROM Goles g WHERE g.golesPK.partidoidPartido = :partidoidPartido")
    , @NamedQuery(name = "Goles.findByJugadoridJugador", query = "SELECT g FROM Goles g WHERE g.golesPK.jugadoridJugador = :jugadoridJugador")
    , @NamedQuery(name = "Goles.findByCantGoles", query = "SELECT g FROM Goles g WHERE g.cantGoles = :cantGoles")})
public class Goles implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GolesPK golesPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantGoles")
    private int cantGoles;
    @JoinColumn(name = "Jugador_idJugador", referencedColumnName = "idJugador", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Jugador jugador;
    @JoinColumn(name = "Partido_idPartido", referencedColumnName = "idPartido", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Partido partido;

    public Goles() {
    }

    public Goles(GolesPK golesPK) {
        this.golesPK = golesPK;
    }

    public Goles(GolesPK golesPK, int cantGoles) {
        this.golesPK = golesPK;
        this.cantGoles = cantGoles;
    }

    public Goles(String partidoidPartido, int jugadoridJugador) {
        this.golesPK = new GolesPK(partidoidPartido, jugadoridJugador);
    }

    public GolesPK getGolesPK() {
        return golesPK;
    }

    public void setGolesPK(GolesPK golesPK) {
        this.golesPK = golesPK;
    }

    public int getCantGoles() {
        return cantGoles;
    }

    public void setCantGoles(int cantGoles) {
        this.cantGoles = cantGoles;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public Partido getPartido() {
        return partido;
    }

    public void setPartido(Partido partido) {
        this.partido = partido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (golesPK != null ? golesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Goles)) {
            return false;
        }
        Goles other = (Goles) object;
        if ((this.golesPK == null && other.golesPK != null) || (this.golesPK != null && !this.golesPK.equals(other.golesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Goles[ golesPK=" + golesPK + " ]";
    }
    
}
