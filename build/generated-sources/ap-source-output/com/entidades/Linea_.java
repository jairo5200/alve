package com.entidades;

import com.entidades.Marca;
import com.entidades.Vehiculo;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Linea.class)
public class Linea_ { 

    public static volatile SingularAttribute<Linea, String> nombreLinea;
    public static volatile SingularAttribute<Linea, Marca> nombreMarca;
    public static volatile CollectionAttribute<Linea, Vehiculo> vehiculoCollection;

}