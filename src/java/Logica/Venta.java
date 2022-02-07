package Logica;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Venta implements Serializable {
    
// ------------ ATRIBUTOS
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int num_venta;
    
    @Basic
    public int cantidad;
    
    @Temporal(TemporalType.DATE)
    public Date fecha_venta;
    
    @OneToOne
    private MedioPago medio_pago;
    
    
// ----------- CONSTRUCTORES

    public Venta() {
    }

    public Venta(int num_venta, int cantidad, Date fecha_venta, MedioPago medio_pago) {
        this.num_venta = num_venta;
        this.cantidad = cantidad;
        this.fecha_venta = fecha_venta;
        this.medio_pago = medio_pago;
    }

    
    
// ---------- SETTERS Y GETTERS 

    public int getNum_venta() {
        return num_venta;
    }

    public void setNum_venta(int num_venta) {
        this.num_venta = num_venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(Date fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public MedioPago getMedio_pago() {
        return medio_pago;
    }

    public void setMedio_pago(MedioPago medio_pago) {
        this.medio_pago = medio_pago;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.num_venta;
        hash = 41 * hash + Objects.hashCode(this.medio_pago);
        hash = 41 * hash + this.cantidad;
        hash = 41 * hash + Objects.hashCode(this.fecha_venta);
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
        final Venta other = (Venta) obj;
        if (this.num_venta != other.num_venta) {
            return false;
        }
        if (this.cantidad != other.cantidad) {
            return false;
        }
        if (!Objects.equals(this.medio_pago, other.medio_pago)) {
            return false;
        }
        if (!Objects.equals(this.fecha_venta, other.fecha_venta)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
}
