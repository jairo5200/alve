package com.entidades;

import com.entidades.Alquiler;
import com.entidades.Linea;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Vehiculo.class)
public class Vehiculo_ { 

    public static volatile CollectionAttribute<Vehiculo, Alquiler> alquilerCollection;
    public static volatile SingularAttribute<Vehiculo, BigDecimal> costo;
    public static volatile SingularAttribute<Vehiculo, byte[]> imagen;
    public static volatile SingularAttribute<Vehiculo, String> modelo;
    public static volatile SingularAttribute<Vehiculo, Linea> linea;
    public static volatile SingularAttribute<Vehiculo, String> placa;
    public static volatile SingularAttribute<Vehiculo, String> disponible;

}