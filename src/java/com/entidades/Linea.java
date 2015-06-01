/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
 * @author jairo
 */
@Entity
@Table(name = "linea")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Linea.findAll", query = "SELECT l FROM Linea l"),
    @NamedQuery(name = "Linea.findByNombreLinea", query = "SELECT l FROM Linea l WHERE l.nombreLinea = :nombreLinea")})
public class Linea implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "nombre_linea")
    private String nombreLinea;
    @OneToMany(mappedBy = "linea")
    private Collection<Vehiculo> vehiculoCollection;
    @JoinColumn(name = "nombre_marca", referencedColumnName = "nombre_marca")
    @ManyToOne
    private Marca nombreMarca;

    public Linea() {
    }

    public Linea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    public String getNombreLinea() {
        return nombreLinea;
    }

    public void setNombreLinea(String nombreLinea) {
        this.nombreLinea = nombreLinea;
    }

    @XmlTransient
    public Collection<Vehiculo> getVehiculoCollection() {
        return vehiculoCollection;
    }

    public void setVehiculoCollection(Collection<Vehiculo> vehiculoCollection) {
        this.vehiculoCollection = vehiculoCollection;
    }

    public Marca getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(Marca nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nombreLinea != null ? nombreLinea.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Linea)) {
            return false;
        }
        Linea other = (Linea) object;
        if ((this.nombreLinea == null && other.nombreLinea != null) || (this.nombreLinea != null && !this.nombreLinea.equals(other.nombreLinea))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Linea[ nombreLinea=" + nombreLinea + " ]";
    }
    
}
