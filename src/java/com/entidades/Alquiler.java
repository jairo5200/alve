/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jairo
 */
@Entity
@Table(name = "alquiler")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alquiler.findAll", query = "SELECT a FROM Alquiler a"),
    @NamedQuery(name = "Alquiler.findByIdAlquiler", query = "SELECT a FROM Alquiler a WHERE a.idAlquiler = :idAlquiler"),
    @NamedQuery(name = "Alquiler.findByHoras", query = "SELECT a FROM Alquiler a WHERE a.horas = :horas")})
public class Alquiler implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_alquiler")
    private Long idAlquiler;
    @Column(name = "horas")
    private Long horas;
    @JoinColumn(name = "cliente", referencedColumnName = "identificacion")
    @ManyToOne
    private Cliente cliente;
    @JoinColumn(name = "vehiculo", referencedColumnName = "placa")
    @ManyToOne
    private Vehiculo vehiculo;

    public Alquiler() {
    }

    public Alquiler(Long idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Long getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(Long idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public Long getHoras() {
        return horas;
    }

    public void setHoras(Long horas) {
        this.horas = horas;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idAlquiler != null ? idAlquiler.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alquiler)) {
            return false;
        }
        Alquiler other = (Alquiler) object;
        if ((this.idAlquiler == null && other.idAlquiler != null) || (this.idAlquiler != null && !this.idAlquiler.equals(other.idAlquiler))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entidades.Alquiler[ idAlquiler=" + idAlquiler + " ]";
    }
    
}
