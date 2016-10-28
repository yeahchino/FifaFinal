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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author dfeitt
 */
@Entity
@Table(name = "cuerpotecnico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cuerpotecnico.findAll", query = "SELECT c FROM Cuerpotecnico c")
    , @NamedQuery(name = "Cuerpotecnico.findByIdCuerpoTecnico", query = "SELECT c FROM Cuerpotecnico c WHERE c.idCuerpoTecnico = :idCuerpoTecnico")
    , @NamedQuery(name = "Cuerpotecnico.findByNombre", query = "SELECT c FROM Cuerpotecnico c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cuerpotecnico.findByApellido", query = "SELECT c FROM Cuerpotecnico c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Cuerpotecnico.findByDni", query = "SELECT c FROM Cuerpotecnico c WHERE c.dni = :dni")})
public class Cuerpotecnico implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCuerpoTecnico")
    private Integer idCuerpoTecnico;
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
    @JoinTable(name = "cuerpotxpuesto", joinColumns = {
        @JoinColumn(name = "CuerpoTecnico_idCuerpoTecnico", referencedColumnName = "idCuerpoTecnico")}, inverseJoinColumns = {
        @JoinColumn(name = "PuestoCTecnico_idPuestoCTecnico", referencedColumnName = "idPuestoCTecnico")})
    @ManyToMany
    private Collection<Puestoctecnico> puestoctecnicoCollection;
    @JoinTable(name = "ctecnicoxequipo", joinColumns = {
        @JoinColumn(name = "CuerpoTecnico_idCuerpoTecnico", referencedColumnName = "idCuerpoTecnico")}, inverseJoinColumns = {
        @JoinColumn(name = "Equipo_idEquipo", referencedColumnName = "idEquipo")})
    @ManyToMany
    private Collection<Equipo> equipoCollection;

    public Cuerpotecnico() {
    }

    public Cuerpotecnico(Integer idCuerpoTecnico) {
        this.idCuerpoTecnico = idCuerpoTecnico;
    }

    public Cuerpotecnico(Integer idCuerpoTecnico, String nombre, String apellido, int dni) {
        this.idCuerpoTecnico = idCuerpoTecnico;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Integer getIdCuerpoTecnico() {
        return idCuerpoTecnico;
    }

    public void setIdCuerpoTecnico(Integer idCuerpoTecnico) {
        this.idCuerpoTecnico = idCuerpoTecnico;
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
    public Collection<Puestoctecnico> getPuestoctecnicoCollection() {
        return puestoctecnicoCollection;
    }

    public void setPuestoctecnicoCollection(Collection<Puestoctecnico> puestoctecnicoCollection) {
        this.puestoctecnicoCollection = puestoctecnicoCollection;
    }

    @XmlTransient
    public Collection<Equipo> getEquipoCollection() {
        return equipoCollection;
    }

    public void setEquipoCollection(Collection<Equipo> equipoCollection) {
        this.equipoCollection = equipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuerpoTecnico != null ? idCuerpoTecnico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuerpotecnico)) {
            return false;
        }
        Cuerpotecnico other = (Cuerpotecnico) object;
        if ((this.idCuerpoTecnico == null && other.idCuerpoTecnico != null) || (this.idCuerpoTecnico != null && !this.idCuerpoTecnico.equals(other.idCuerpoTecnico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Cuerpotecnico[ idCuerpoTecnico=" + idCuerpoTecnico + " ]";
    }
    
}
