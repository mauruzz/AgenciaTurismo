package Logica;

import static Logica.Controladora.deStringToDate;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Empleado implements Serializable {
    
//----------  ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int id_empleado;
    
    @Basic
    public String cargo;
    public double sueldo; 
    public boolean habilitado;
    
    @OneToOne     
    public Usuario usuario;
    
    @OneToOne
    public Persona persona;
    
    @OneToMany
    public List<Venta> listaVentas;
    
    
// -------------- CONSTRUCTORES

    public Empleado() {
    }

    public Empleado(int id_empleado, String cargo, double sueldo, boolean habilitado, Usuario usuario, Persona persona, List<Venta> listaVentas) {
        this.id_empleado = id_empleado;
        this.cargo = cargo;
        this.sueldo = sueldo;
        this.habilitado = habilitado;
        this.usuario = usuario;
        this.persona = persona;
        this.listaVentas = listaVentas;
    }

    
    
    
// ----------- SETTERS Y GETTERS

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Venta> getListaVentas() {
        return listaVentas;
    }

    public void setListaVentas(List<Venta> listaVentas) {
        this.listaVentas = listaVentas;
    }

    
    
    // ---------- METODOS PROPIOS
    
    public double getFacturado () {
        
        Controladora control = new Controladora ();
        double facturado = 0;
        
        for (Venta venta : listaVentas){
            
            if (control.getServicioFromVenta(venta) != null)
                facturado = facturado + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad());
            
            if (control.getPaqueteFromVenta(venta) != null)
                facturado = facturado + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad());
        }
        
        return facturado;
    }
    
    public double getGanancia () {
        
        double medioPago = 0;
        double efectivo = 1;
        double debito = 0.97;
        double credito = 0.81;
        double monedero = 1;
        double transferencia = 0.9755;
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            switch (venta.getMedio_pago()) {
                case "efectivo":
                    medioPago = efectivo;
                    break;
                case "debito":
                    medioPago = debito;
                    break;
                case "credito":
                    medioPago = credito;
                    break;
                case "monedero":
                    medioPago = monedero;
                    break;    
                case "transferencia":
                    medioPago = transferencia;
                    break;    
            }
            
            if (control.getServicioFromVenta(venta) != null)
                ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * medioPago);
            
            if (control.getPaqueteFromVenta(venta) != null)
                ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * medioPago);
        }
        
        return ganancia;
    }
    
    public double getGananciaDiaria () {
        
        DateTimeFormatter str_Fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fecha_Hoy = deStringToDate(str_Fecha.format(LocalDateTime.now()));
        
        double medioPago = 0;
        double efectivo = 1;
        double debito = 0.97;
        double credito = 0.81;
        double monedero = 1;
        double transferencia = 0.9755;
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            if (venta.getFecha_venta().equals(fecha_Hoy)) {
                switch (venta.getMedio_pago()) {
                    case "efectivo":
                        medioPago = efectivo;
                        break;
                    case "debito":
                        medioPago = debito;
                        break;
                    case "credito":
                        medioPago = credito;
                        break;
                    case "monedero":
                        medioPago = monedero;
                        break;    
                    case "transferencia":
                        medioPago = transferencia;
                        break;    
                }

                if (control.getServicioFromVenta(venta) != null)
                    ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * medioPago);

                if (control.getPaqueteFromVenta(venta) != null)
                    ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * medioPago);
            }    
        }
        
        return ganancia;
        
    }
    
    public double getGananciaMensual (int mes){
        
        double medioPago = 0;
        double efectivo = 1;
        double debito = 0.97;
        double credito = 0.81;
        double monedero = 1;
        double transferencia = 0.9755;
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            if (control.getMesFromDate(venta.getFecha_venta()) == mes) {
                switch (venta.getMedio_pago()) {
                    case "efectivo":
                        medioPago = efectivo;
                        break;
                    case "debito":
                        medioPago = debito;
                        break;
                    case "credito":
                        medioPago = credito;
                        break;
                    case "monedero":
                        medioPago = monedero;
                        break;    
                    case "transferencia":
                        medioPago = transferencia;
                        break;    
                }

                if (control.getServicioFromVenta(venta) != null)
                    ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * medioPago);

                if (control.getPaqueteFromVenta(venta) != null)
                    ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * medioPago);
            }    
        }
        
        return ganancia;
    }
    
    public double getFacturadoMensual (int mes) {
        
        Controladora control = new Controladora ();
        double facturado = 0;
        
        for (Venta venta : listaVentas){
            
            if (control.getMesFromDate(venta.getFecha_venta()) == mes) {
                if (control.getServicioFromVenta(venta) != null)
                    facturado = facturado + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad());

                if (control.getPaqueteFromVenta(venta) != null)
                    facturado = facturado + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad());
           }
        }
        
        return facturado;
    }
    
    
   public double getFacturadoMedioPago (String medio_Pago) {
        
        Controladora control = new Controladora ();
        double facturado = 0;
        
        for (Venta venta : listaVentas){
            
            if (venta.getMedio_pago().equals(medio_Pago)) {
                if (control.getServicioFromVenta(venta) != null)
                    facturado = facturado + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad());

                if (control.getPaqueteFromVenta(venta) != null)
                    facturado = facturado + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad());
           }
        }
        
        return facturado;
    }
    
}
