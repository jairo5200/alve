package com.entidades;

import com.entidades.Marca;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Pais.class)
public class Pais_ { 

    public static volatile CollectionAttribute<Pais, Marca> marcaCollection;
    public static volatile SingularAttribute<Pais, String> nombre;
    public static volatile SingularAttribute<Pais, Long> idCiudad;

}