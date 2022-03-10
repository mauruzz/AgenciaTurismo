package Logica;

import Logica.ServicioTuristico;
import Logica.Venta;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2022-03-09T23:19:19")
@StaticMetamodel(PaqueteTuristico.class)
public class PaqueteTuristico_ { 

    public static volatile SingularAttribute<PaqueteTuristico, Double> costo_paquete;
    public static volatile SingularAttribute<PaqueteTuristico, Integer> codigo_paquete;
    public static volatile ListAttribute<PaqueteTuristico, ServicioTuristico> lista_servicios;
    public static volatile SingularAttribute<PaqueteTuristico, Boolean> habilitado;
    public static volatile ListAttribute<PaqueteTuristico, Venta> listaVentas;
    public static volatile SingularAttribute<PaqueteTuristico, String> nombre;

}