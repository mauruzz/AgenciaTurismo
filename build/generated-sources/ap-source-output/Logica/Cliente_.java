package Logica;

import Logica.Persona;
import Logica.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-09T23:19:19")
@StaticMetamodel(Cliente.class)
public class Cliente_ { 

    public static volatile SingularAttribute<Cliente, Integer> id_cliente;
    public static volatile SingularAttribute<Cliente, Persona> persona;
    public static volatile SingularAttribute<Cliente, Boolean> habilitado;
    public static volatile ListAttribute<Cliente, Venta> listaVentas;

}