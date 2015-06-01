package com.entidades;

import com.entidades.Cliente;
import com.entidades.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Alquiler.class)
public class Alquiler_ { 

    public static volatile SingularAttribute<Alquiler, Long> horas;
    public static volatile SingularAttribute<Alquiler, Cliente> cliente;
    public static volatile SingularAttribute<Alquiler, Long> idAlquiler;
    public static volatile SingularAttribute<Alquiler, Vehiculo> vehiculo;

}