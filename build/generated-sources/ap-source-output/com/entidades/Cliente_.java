package com.entidades;

import com.entidades.Alquiler;
import com.entidades.Ciudad;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-06-01T08:04:32")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, String> apellidos;
    public static volatile CollectionAttribute<Cliente, Alquiler> alquilerCollection;
    public static volatile SingularAttribute<Cliente, Ciudad> ciudad;
    public static volatile SingularAttribute<Cliente, String> identificacion;
    public static volatile SingularAttribute<Cliente, String> telefono;
    public static volatile SingularAttribute<Cliente, String> email;
    public static volatile SingularAttribute<Cliente, String> nombres;

}