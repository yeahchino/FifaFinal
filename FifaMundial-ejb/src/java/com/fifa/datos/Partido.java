/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fifa.datos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alexia
 */
@Entity
@Table(name = "partido")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partido.findAll", query = "SELECT p FROM Partido p"),
    @NamedQuery(name = "Partido.findByIdPartido", query = "SELECT p FROM Partido p WHERE p.idPartido = :idPartido"),
    @NamedQuery(name = "Partido.findByFecha", query = "SELECT p FROM Partido p WHERE p.fecha = :fecha"),
    @NamedQuery(name = "Partido.findByHora", query = "SELECT p FROM Partido p WHERE p.hora = :hora"),
    @NamedQuery(name = "Partido.findByRdoA", query = "SELECT p FROM Partido p WHERE p.rdoA = :rdoA"),
    @NamedQuery(name = "Partido.findByRdoB", query = "SELECT p FROM Partido p WHERE p.rdoB = :rdoB"),
    @NamedQuery(name = "Partido.findByPenalesA", query = "SELECT p FROM Partido p WHERE p.penalesA = :penalesA"),
    @NamedQuery(name = "Partido.findByPenalesB", query = "SELECT p FROM Partido p WHERE p.penalesB = :penalesB")})
public class Partido implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "idPartido")
    private String idPartido;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "Hora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date hora;
    @Column(name = "rdoA")
    private Integer rdoA;
    @Column(name = "rdoB")
    private Integer rdoB;
    @Column(name = "penalesA")
    private Integer penalesA;
    @Column(name = "penalesB")
    private Integer penalesB;
    @JoinColumn(name = "Estadio_idEstadio", referencedColumnName = "idEstadio")
    @ManyToOne
    private Estadio estadioidEstadio;
    @JoinColumn(name = "Jugador_capitanA", referencedColumnName = "idJugador")
    @ManyToOne
    private Jugador jugadorcapitanA;
    @JoinColumn(name = "Jugador_capitanB", referencedColumnName = "idJugador")
    @ManyToOne
    private Jugador jugadorcapitanB;
    @JoinColumn(name = "Equipo_idEquipoA", referencedColumnName = "idEquipo")
    @ManyToOne(optional = false)
    private Equipo equipoidEquipoA;
    @JoinColumn(name = "Equipo_idEquipoB", referencedColumnName = "idEquipo")
    @ManyToOne(optional = false)
    private Equipo equipoidEquipoB;
    @JoinColumn(name = "Mundial_idMundial", referencedColumnName = "idMundial")
    @ManyToOne(optional = false)
    private Mundial mundialidMundial;
    @JoinColumn(name = "Ronda_idRonda", referencedColumnName = "idRonda")
    @ManyToOne(optional = false)
    private Ronda rondaidRonda;

    public Partido() {
    }

    public Partido(String idPartido) {
        this.idPartido = idPartido;
    }

    public String getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(String idPartido) {
        this.idPartido = idPartido;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Integer getRdoA() {
        return rdoA;
    }

    public void setRdoA(Integer rdoA) {
        this.rdoA = rdoA;
    }

    public Integer getRdoB() {
        return rdoB;
    }

    public void setRdoB(Integer rdoB) {
        this.rdoB = rdoB;
    }

    public Integer getPenalesA() {
        return penalesA;
    }

    public void setPenalesA(Integer penalesA) {
        this.penalesA = penalesA;
    }

    public Integer getPenalesB() {
        return penalesB;
    }

    public void setPenalesB(Integer penalesB) {
        this.penalesB = penalesB;
    }

    public Estadio getEstadioidEstadio() {
        return estadioidEstadio;
    }

    public void setEstadioidEstadio(Estadio estadioidEstadio) {
        this.estadioidEstadio = estadioidEstadio;
    }

    public Jugador getJugadorcapitanA() {
        return jugadorcapitanA;
    }

    public void setJugadorcapitanA(Jugador jugadorcapitanA) {
        this.jugadorcapitanA = jugadorcapitanA;
    }

    public Jugador getJugadorcapitanB() {
        return jugadorcapitanB;
    }

    public void setJugadorcapitanB(Jugador jugadorcapitanB) {
        this.jugadorcapitanB = jugadorcapitanB;
    }

    public Equipo getEquipoidEquipoA() {
        return equipoidEquipoA;
    }

    public void setEquipoidEquipoA(Equipo equipoidEquipoA) {
        this.equipoidEquipoA = equipoidEquipoA;
    }

    public Equipo getEquipoidEquipoB() {
        return equipoidEquipoB;
    }

    public void setEquipoidEquipoB(Equipo equipoidEquipoB) {
        this.equipoidEquipoB = equipoidEquipoB;
    }

    public Mundial getMundialidMundial() {
        return mundialidMundial;
    }

    public void setMundialidMundial(Mundial mundialidMundial) {
        this.mundialidMundial = mundialidMundial;
    }

    public Ronda getRondaidRonda() {
        return rondaidRonda;
    }

    public void setRondaidRonda(Ronda rondaidRonda) {
        this.rondaidRonda = rondaidRonda;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartido != null ? idPartido.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partido)) {
            return false;
        }
        Partido other = (Partido) object;
        if ((this.idPartido == null && other.idPartido != null) || (this.idPartido != null && !this.idPartido.equals(other.idPartido))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Partido[ idPartido=" + idPartido + " ]";
    }

    public void setIdPartido(int idPartido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
