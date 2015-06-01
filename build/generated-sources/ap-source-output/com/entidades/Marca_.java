package com.entidades;

import com.entidades.Linea;
import com.entidades.Pais;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Marca.class)
public class Marca_ { 

    public static volatile CollectionAttribute<Marca, Linea> lineaCollection;
    public static volatile SingularAttribute<Marca, Pais> paisOrigen;
    public static volatile SingularAttribute<Marca, String> nombreMarca;

}