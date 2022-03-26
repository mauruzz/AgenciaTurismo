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
    
    
    
    // ----------------------------------------------------- //


    //<editor-fold defaultstate="collapsed" desc="CONSTRUCTORES">
    
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

    //</editor-fold>

    
    // ----------------------------------------------------- //
    

    //<editor-fold defaultstate="collapsed" desc="SETTERS Y GETTERS">
    
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
//</editor-fold>

    
    // ----------------------------------------------------- //    
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS PROPIOS DE EMPLEADO">
    
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
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            
            if (control.getServicioFromVenta(venta) != null)
                ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
            
            if (control.getPaqueteFromVenta(venta) != null)
                ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
        }
        
        return ganancia;
    }
    
    public double getGananciaDiaria () {
        
        DateTimeFormatter str_Fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fecha_Hoy = deStringToDate(str_Fecha.format(LocalDateTime.now()));
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            if (venta.getFecha_venta().equals(fecha_Hoy)) {
                
                if (control.getServicioFromVenta(venta) != null)
                    ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
                
                if (control.getPaqueteFromVenta(venta) != null)
                    ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
            }
        }
        
        return ganancia;
        
    }
    
    public double getGananciaMensual (int mes){
        
        Controladora control = new Controladora ();
        double ganancia = 0;
        
        for (Venta venta : listaVentas){
            if (control.getMesFromDate(venta.getFecha_venta()) == mes) {
                
                if (control.getServicioFromVenta(venta) != null)
                    ganancia = ganancia + (control.getServicioFromVenta(venta).getCosto_servicio() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
                
                if (control.getPaqueteFromVenta(venta) != null)
                    ganancia = ganancia + (control.getPaqueteFromVenta(venta).getCosto_paquete() * venta.getCantidad() * (1 - venta.getMedio_pago().getDescuento() / 100));
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
    
    public double getFacturadoMedioPago (MedioPago medio_Pago) {
        
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
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE SERVICIOS TURISTICOS">
    
    public ServicioTuristico crearServicioTuristico(String nombre, String descripcion, String destino, double costo, Date fecha) {
        
        ServicioTuristico servTuri = new ServicioTuristico();
        
        servTuri.setNombre(nombre);
        servTuri.setDescripcion_breve(descripcion);
        servTuri.setDestino_servicio(destino);
        servTuri.setCosto_servicio(costo);
        servTuri.setFecha_servicio(fecha);
        servTuri.setHabilitado(true);
        
        return servTuri;
    }
    
    public List<ServicioTuristico> getListaServiciosHabilitados (List<ServicioTuristico> listaServi) {
        
        List<ServicioTuristico> listaServiHabilitados = new ArrayList<ServicioTuristico>();
        
        for (ServicioTuristico servi : listaServi) {
            
            if (servi.isHabilitado() == true){
                
                listaServiHabilitados.add(servi);
            }
        }
        
        return listaServiHabilitados;
    }
    
    public ServicioTuristico eliminarLogicoServicioTuristico(ServicioTuristico servTuri) {
        
        servTuri.setHabilitado(false);
        
        return servTuri;
    }
    
    public ServicioTuristico editarServicioTuristico(int id, String nombre, String descripcion, String destino, double costo, Date fecha) {
        
        ServicioTuristico servTuri = new ServicioTuristico();
        
        servTuri.setCodigo_servicio(id);
        servTuri.setNombre(nombre);
        servTuri.setDescripcion_breve(descripcion);
        servTuri.setDestino_servicio(destino);
        servTuri.setCosto_servicio(costo);
        servTuri.setFecha_servicio(fecha);
        servTuri.setHabilitado(true);
        
        return servTuri;
    }
    
    //</editor-fold>

    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PAQUETE TURISTICO">
    
    public PaqueteTuristico crearPaqueteTuristico(String nombre, double descuento, List<ServicioTuristico> listaServicios) {
        
        double costo = 0;
        PaqueteTuristico paqTuri = new PaqueteTuristico ();
        
        /*--------------------------------
        Calculo el costo del PaqueteTuristico sumando cada uno de los ServicioTuristico y aplicandole el DESCUENTO (variable global) por ser un paquete
        --------------------------------*/ 
        
        for (ServicioTuristico servi : listaServicios){
            
            costo = costo + (servi.getCosto_servicio() * descuento);
        }
        
        /*--------------------------------
        Asigno valores
        --------------------------------*/ 
        
        paqTuri.setCosto_paquete(costo);
        paqTuri.setNombre(nombre);
        paqTuri.setHabilitado(true);
        paqTuri.setLista_servicios(listaServicios);
        
        return paqTuri;
    }
    
    public List<PaqueteTuristico> getListaPaquetesHabilitados (List<PaqueteTuristico> listaPaque) {
        
        List<PaqueteTuristico> listaPaqueHabilitados = new ArrayList<PaqueteTuristico>();
        
        for (PaqueteTuristico paque : listaPaque) {
            
            if (paque.isHabilitado() == true){
                
                listaPaqueHabilitados.add(paque);
            }
        }
        
        return listaPaqueHabilitados;
    }
    
    public PaqueteTuristico eliminarLogicoPaqueteTuristico(PaqueteTuristico paqTuri) {
        
        paqTuri.setHabilitado(false);
        
        return paqTuri;
    }
    
    public PaqueteTuristico editarPaqueteTuristico(int id, String nombre, List<ServicioTuristico> listaServicios, double descuento) {
        
        double costo = 0;
        PaqueteTuristico paqTuri = new PaqueteTuristico ();
        
        /*--------------------------------
        Calculo el costo del PaqueteTuristico sumando cada uno de los ServicioTuristico y aplicandole el DESCUENTO (variable global) por ser un paquete
        --------------------------------*/ 
        
        for (ServicioTuristico servi : listaServicios){
            
            costo = costo + (servi.getCosto_servicio() * descuento);
        }
        
        /*--------------------------------
        Asigno valores
        --------------------------------*/ 
        
        paqTuri.setCodigo_paquete(id);
        paqTuri.setNombre(nombre);
        paqTuri.setLista_servicios(listaServicios);
        paqTuri.setCosto_paquete(costo);
        paqTuri.setHabilitado(true);
        
        return paqTuri;
    }

    //</editor-fold>
    
    
     // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PERSONA">    
    
    public Persona crearPersona(String Nombre, String Apellido, String Direccion, int dni, Date fecha_Nac, String Nacionalidad, String Celular, String Email) {
        
        Persona perso = new Persona ();
        
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDireccion(Direccion);
        perso.setDni(dni);
        perso.setFecha_nacimiento(fecha_Nac);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
        
        return perso;
    }
    
    public Persona editarPersona(int id_Persona, String Nombre, String Apellido, int dni, String Direccion, Date fecha, String Nacionalidad, String Celular, String Email) {
        
        Persona perso = new Persona ();
        
        perso.setId_Persona(id_Persona);
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDni(dni);
        perso.setDireccion(Direccion);
        perso.setFecha_nacimiento(fecha);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
                
        return perso;
    }
    
    //</editor-fold>
    

    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE CLIENTE">
    
    public Cliente crearCliente(Persona perso) {
        
        Cliente clien = new Cliente ();
        
        clien.setPersona(perso);
        clien.setHabilitado(true);
        
        return clien;
    }
    
    public Cliente eliminarLogicoCliente(Cliente clien) {
        
        clien.setHabilitado(false);
        
        return clien;
    }
    
    public Cliente editarCliente(int id_Cliente, Persona perso) {
        
        Cliente clien = new Cliente ();
        
        clien.setPersona(perso);
        clien.setId_cliente(id_Cliente);
        clien.setHabilitado(true);
        
        return clien;
    }
    
    public List<Cliente> getListaClientesHabilitados (List<Cliente> listaClien){
        
        List<Cliente> listaClienHabilitados = new ArrayList<Cliente>();
                
        for (Cliente clien : listaClien) {
            
            if (clien.isHabilitado() == true){
                
                listaClienHabilitados.add(clien);
            }
        }
                
        return listaClienHabilitados; 
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE USUARIO">
    
    public Usuario crearUsuario(String Usuario, String Contrasenia) {
        
        Usuario usu = new Usuario ();
        
        usu.setUsuario(Usuario);
        usu.setContrasenia(Contrasenia);
        
        return usu;
    }
    
    public Usuario editarUsuario(int id_Usuario, String Usuario, String Contrasenia) {
        
        Usuario usu = new Usuario ();
        
        usu.setId_usuario(id_Usuario);
        usu.setUsuario(Usuario);
        usu.setContrasenia(Contrasenia);
        
        return usu;
    }
    
    //</editor-fold>  
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE VENTA">
    
    public Venta crearVenta(Date fecha, int cantidad, MedioPago medio_pago) {
        
        Venta venta = new Venta ();
        
        venta.setFecha_venta(fecha);
        venta.setCantidad(cantidad);
        venta.setMedio_pago(medio_pago);
        
        return venta;
    }
    
    public Cliente getClienteFromVenta(Venta venta_Actual, List<Cliente> listaClientes) {
        
        List<Venta> listaVentas = new ArrayList<Venta> ();
        
        for (Cliente cliente : listaClientes) {
            
            listaVentas = cliente.getListaVentas();
            
            for (Venta venta : listaVentas){
                
                if (venta.equals(venta_Actual)) {
                    return cliente;
                }
            }
        }
        
        return null;
    }
    
    public Empleado getEmpleadoFromVenta(Venta venta_Actual, List<Empleado> listaEmpleados) {
        
        List<Venta> listaVentas = new ArrayList<Venta> ();

        for (Empleado empleado : listaEmpleados) {
            
            listaVentas = empleado.getListaVentas();
            
            for (Venta venta : listaVentas)
                if (venta.equals(venta_Actual)) {
                    return empleado;
                }
        }
        
        return null;
    }
    
    public ServicioTuristico getServicioFromVenta(Venta venta_Actual, List<ServicioTuristico> listaServicios) {
        
        List<Venta> listaVentas = new ArrayList<Venta> ();

        for (ServicioTuristico servicio : listaServicios) {
            
            listaVentas = servicio.getListaVentas();
            
            for (Venta venta : listaVentas)
                if (venta.equals(venta_Actual)) {
                    return servicio;
                }
        }
        
        return null;
    }
    
    public PaqueteTuristico getPaqueteFromVenta(Venta venta_Actual, List<PaqueteTuristico> listaPaquetes) {
        
        List<Venta> listaVentas = new ArrayList<Venta> ();

        for (PaqueteTuristico paquete : listaPaquetes) {
            
            listaVentas = paquete.getListaVentas();
            
            for (Venta venta : listaVentas)
                if (venta.equals(venta_Actual)) {
                    return paquete;
                }
        }
        
        return null;
    }
    
    //</editor-fold>  
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE MEDIO DE PAGO">
    
    public MedioPago crearMedioPago(String nombre, double descuento, boolean habilitado) {
        
        MedioPago medioPago = new MedioPago ();
        
        medioPago.setNombre(nombre);
        medioPago.setHabilitado(habilitado);
        medioPago.setDescuento(descuento);
        
        return medioPago;
    }
    
    public MedioPago editarMedioPago(MedioPago medioPago, String nombre, double descuento) {
        
        medioPago.setNombre(nombre);
        medioPago.setDescuento(descuento);
        
        return medioPago;
    }
    
    public MedioPago deshabilitarMedioPago(MedioPago medioPago) {
        
        medioPago.setHabilitado(false);
        
        return medioPago;
    }
    
    public List<MedioPago> getListaMediosPagoHabilitados (List<MedioPago> listaMediosPago) {
        
        List<MedioPago> listaMediosPagoHabilitados = new ArrayList<MedioPago>();
        
        for (MedioPago medioPago : listaMediosPago) {
            
            if (medioPago.isHabilitado() == true){
                
                listaMediosPagoHabilitados.add(medioPago);
            }
        }
        
        return listaMediosPagoHabilitados;
    }
    
    //</editor-fold>  
    
    
    // ----------------------------------------------------- //
    
    
    
}
