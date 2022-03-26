package Logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class PaqueteTuristico implements Serializable {

// ---------  ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int codigo_paquete;
    
    @Basic
    public String nombre;
    public double costo_paquete;
    public boolean habilitado;
    
    @ManyToMany
    public List<ServicioTuristico> lista_servicios;
    
    @OneToMany
    public List<Venta> listaVentas;
    
    
    
    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="CONTRUCTORES">
    
    public PaqueteTuristico() {
    }

    public PaqueteTuristico(int codigo_paquete, String nombre, double costo_paquete, boolean habilitado, List<ServicioTuristico> lista_servicios, List<Venta> listaVentas) {
        this.codigo_paquete = codigo_paquete;
        this.nombre = nombre;
        this.costo_paquete = costo_paquete;
        this.habilitado = habilitado;
        this.lista_servicios = lista_servicios;
        this.listaVentas = listaVentas;
    }

    //</editor-fold>
    
    
    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="SETTERS Y GETTERS">
    
    public int getCodigo_paquete() {
        return codigo_paquete;
    }

    public void setCodigo_paquete(int codigo_paquete) {
        this.codigo_paquete = codigo_paquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCosto_paquete() {
        return costo_paquete;
    }

    public void setCosto_paquete(double costo_paquete) {
        this.costo_paquete = costo_paquete;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public List<ServicioTuristico> getLista_servicios() {
        return lista_servicios;
    }

    public void setLista_servicios(List<ServicioTuristico> lista_servicios) {
        this.lista_servicios = lista_servicios;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    //</editor-fold>
   
    
    // ----------------------------------------------------- //
    
    
    
    
}
