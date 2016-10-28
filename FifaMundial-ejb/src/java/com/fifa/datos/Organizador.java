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
 * @author Usuario
 */
@Entity
@Table(name = "organizador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Organizador.findAll", query = "SELECT o FROM Organizador o"),
    @NamedQuery(name = "Organizador.findByIdOrganizador", query = "SELECT o FROM Organizador o WHERE o.idOrganizador = :idOrganizador"),
    @NamedQuery(name = "Organizador.findByNombre", query = "SELECT o FROM Organizador o WHERE o.nombre = :nombre"),
    @NamedQuery(name = "Organizador.findByApellido", query = "SELECT o FROM Organizador o WHERE o.apellido = :apellido"),
    @NamedQuery(name = "Organizador.findByDni", query = "SELECT o FROM Organizador o WHERE o.dni = :dni")})
public class Organizador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrganizador")
    private Integer idOrganizador;
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
    @ManyToMany(mappedBy = "organizadorCollection")
    private Collection<Mundial> mundialCollection;
    @JoinTable(name = "organizadorxpuesto", joinColumns = {
        @JoinColumn(name = "Organizador_idOrganizador", referencedColumnName = "idOrganizador")}, inverseJoinColumns = {
        @JoinColumn(name = "PuestoOrganizador_idPuestoOrg", referencedColumnName = "idPuestoOrg")})
    @ManyToMany
    private Collection<Puestoorganizador> puestoorganizadorCollection;

    public Organizador() {
    }

    public Organizador(Integer idOrganizador) {
        this.idOrganizador = idOrganizador;
    }

    public Organizador(Integer idOrganizador, String nombre, String apellido, int dni) {
        this.idOrganizador = idOrganizador;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Integer getIdOrganizador() {
        return idOrganizador;
    }

    public void setIdOrganizador(Integer idOrganizador) {
        this.idOrganizador = idOrganizador;
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
    public Collection<Puestoorganizador> getPuestoorganizadorCollection() {
        return puestoorganizadorCollection;
    }

    public void setPuestoorganizadorCollection(Collection<Puestoorganizador> puestoorganizadorCollection) {
        this.puestoorganizadorCollection = puestoorganizadorCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrganizador != null ? idOrganizador.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organizador)) {
            return false;
        }
        Organizador other = (Organizador) object;
        if ((this.idOrganizador == null && other.idOrganizador != null) || (this.idOrganizador != null && !this.idOrganizador.equals(other.idOrganizador))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fifa.datos.Organizador[ idOrganizador=" + idOrganizador + " ]";
    }
    
}
