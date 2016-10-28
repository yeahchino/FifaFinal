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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "arbitro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arbitro.findAll", query = "SELECT a FROM Arbitro a"),
    @NamedQuery(name = "Arbitro.findByIdArbitro", query = "SELECT a FROM Arbitro a WHERE a.idArbitro = :idArbitro"),
    @NamedQuery(name = "Arbitro.findByNombre", query = "SELECT a FROM Arbitro a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Arbitro.findByApellido", query = "SELECT a FROM Arbitro a WHERE a.apellido = :apellido"),
    @NamedQuery(name = "Arbitro.findByDni", query = "SELECT a FROM Arbitro a WHERE a.dni = :dni")})
public class Arbitro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idArbitro")
    private Integer idArbitro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dni")
    private int dni;
    @JoinTable(name = "arbitroxmundial", joinColumns = {
        @JoinColumn(name = "Arbitro_idArbitro", referencedColumnName = "idArbitro")}, inverseJoinColumns = {
        @JoinColumn(name = "Mundial_idMundial", referencedColumnName = "idMundial")})
    @ManyToMany
    private Collection<Mundial> mundialCollection;
    @JoinTable(name = "partidoxarbitro", joinColumns = {
        @JoinColumn(name = "Arbitro_idArbitro", referencedColumnName = "idArbitro")}, inverseJoinColumns = {
        @JoinColumn(name = "Partido_idPartido", referencedColumnName = "idPartido")})
    @ManyToMany
    private Collection<Partido> partidoCollection;
    @JoinColumn(name = "Pais_idPais", referencedColumnName = "idPais")
    @ManyToOne(optional = false)
    private Pais paisidPais;

    public Arbitro() {
    }

    public Arbitro(Integer idArbitro) {
        this.idArbitro = idArbitro;
    }

    public Arbitro(Integer idArbitro, String nombre, String apellido, int dni) {
        this.idArbitro = idArbitro;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Integer getIdArbitro() {
        return idArbitro;
    }

    public void setIdArbitro(Integer idArbitro) {
        this.idArbitro = idArbitro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    @XmlTransient
    public Collection<Mundial> getMundialCollection() {
        return mundialCollection;
    }

    public void setMundialCollection(Collection<Mundial> mundialCollection) {
        this.mundialCollection = mundialCollection;
    }

    @XmlTransient
    public Collection<Partido> getPartidoCollection() {
        return partidoCollection;
    }

    public void setPartidoCollection(Collection<Partido> partidoCollection) {
        this.partidoCollection = partidoCollection;
    }

    public Pais getPaisidPais() {
        return paisidPais;
    }

    public void setPaisidPais(Pais paisidPais) {
        this.paisidPais = paisidPais;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArbitro != null ? idArbitro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arbitro)) {
            return false;
        }
        Arbitro other = (Arbitro) object;
        if ((this.idArbitro == null && other.idArbitro != null) || (this.idArbitro != null && !this.idArbitro.equals(other.idArbitro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Arbitro[ idArbitro=" + idArbitro + " ]";
    }
    
}
