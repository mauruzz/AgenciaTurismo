package Logica;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class MedioPago implements Serializable {

// ---------  ATRIBUTOS   
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo_medio_pago;
    
    @Basic
    private String nombre;
    private boolean habilitado;
    private double descuento;
    
    
    
    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="CONTRUCTORES">
    
    public MedioPago() {
    }

    public MedioPago(int codigo_medio_pago, String nombre, boolean habilitado, double descuento) {
        this.codigo_medio_pago = codigo_medio_pago;
        this.nombre = nombre;
        this.habilitado = habilitado;
        this.descuento = descuento;
    }
    
    //</editor-fold>
    

    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="SETTERS Y GETTERS">
    
    public int getCodigo_medio_pago() {
        return codigo_medio_pago;
    }
    
    public void setCodigo_medio_pago(int codigo_medio_pago) {
        this.codigo_medio_pago = codigo_medio_pago;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public boolean isHabilitado() {
        return habilitado;
    }
    
    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }
    
    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
    
    //</editor-fold>

    
    // ----------------------------------------------------- //
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MedioPago other = (MedioPago) obj;
        if (this.codigo_medio_pago != other.codigo_medio_pago) {
            return false;
        }
        if (this.habilitado != other.habilitado) {
            return false;
        }
        if (Double.doubleToLongBits(this.descuento) != Double.doubleToLongBits(other.descuento)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
    
    
    
}
