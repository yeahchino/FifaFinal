/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author dfeitt
 */
@Embeddable
public class GolesPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "Partido_idPartido")
    private String partidoidPartido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Jugador_idJugador")
    private int jugadoridJugador;

    public GolesPK() {
    }

    public GolesPK(String partidoidPartido, int jugadoridJugador) {
        this.partidoidPartido = partidoidPartido;
        this.jugadoridJugador = jugadoridJugador;
    }

    public String getPartidoidPartido() {
        return partidoidPartido;
    }

    public void setPartidoidPartido(String partidoidPartido) {
        this.partidoidPartido = partidoidPartido;
    }

    public int getJugadoridJugador() {
        return jugadoridJugador;
    }

    public void setJugadoridJugador(int jugadoridJugador) {
        this.jugadoridJugador = jugadoridJugador;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partidoidPartido != null ? partidoidPartido.hashCode() : 0);
        hash += (int) jugadoridJugador;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GolesPK)) {
            return false;
        }
        GolesPK other = (GolesPK) object;
        if ((this.partidoidPartido == null && other.partidoidPartido != null) || (this.partidoidPartido != null && !this.partidoidPartido.equals(other.partidoidPartido))) {
            return false;
        }
        if (this.jugadoridJugador != other.jugadoridJugador) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.GolesPK[ partidoidPartido=" + partidoidPartido + ", jugadoridJugador=" + jugadoridJugador + " ]";
    }
    
}
