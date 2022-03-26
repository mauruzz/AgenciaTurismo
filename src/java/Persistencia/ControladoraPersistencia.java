package Persistencia;

import Logica.Cliente;
import Logica.Empleado;
import Logica.MedioPago;
import Logica.PaqueteTuristico;
import Logica.Persona;
import Logica.ServicioTuristico;
import Logica.Usuario;
import Logica.Venta;
import Persistencia.exceptions.NonexistentEntityException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">  
    
    ServicioTuristicoJpaController jpaServiTur = new ServicioTuristicoJpaController ();
    ClienteJpaController jpaCliente = new ClienteJpaController ();
    EmpleadoJpaController jpaEmple = new EmpleadoJpaController ();
    PaqueteTuristicoJpaController jpaPaquete = new PaqueteTuristicoJpaController ();
    UsuarioJpaController jpaUsu = new UsuarioJpaController (); 
    VentaJpaController jpaVenta = new VentaJpaController ();
    PersonaJpaController jpaPerso = new PersonaJpaController ();
    MedioPagoJpaController jpaMedio = new MedioPagoJpaController ();
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE SERVICIO TURISTICO">   
    
    public void crearServicioTuristico(ServicioTuristico servTuri){
    
        try{
            jpaServiTur.create(servTuri);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        
    }

    public List<ServicioTuristico> getServicios() {
        
        try{
            return (jpaServiTur.findServicioTuristicoEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public ServicioTuristico getServicioTuristicoById(int Id) {
        
        try{
            return (jpaServiTur.findServicioTuristico(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        
        try{
            return (jpaUsu.findUsuarioEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public void eliminarLogicoServicioTuristico(ServicioTuristico servTuri) {
        try {
            jpaServiTur.edit(servTuri);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarServicioTuristico(ServicioTuristico servTuri) {
        try {
            jpaServiTur.edit(servTuri);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PAQUETE TURISTICO">   
    
    public void crearPaqueteTuristico(PaqueteTuristico paqTuri) {
        
        try{
            jpaPaquete.create(paqTuri);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PaqueteTuristico> getPaquetes() {
        
        try{
            return (jpaPaquete.findPaqueteTuristicoEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public PaqueteTuristico getPaqueteTuristicoById(int Id) {
        
        try{
            return (jpaPaquete.findPaqueteTuristico(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public void eliminarLogicoPaqueteTuristico(PaqueteTuristico paqTuri) {
        
        try {
            jpaPaquete.edit(paqTuri);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarPaqueteTuristico(PaqueteTuristico paqTuri) {
        
        try {
            jpaPaquete.edit(paqTuri);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //</editor-fold>
    
    
   // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE EMPLEADO">   
    
    public void crearEmpleado(Empleado emple) {
        
        try{
            jpaEmple.create(emple);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Empleado getEmpleadoById(int Id) {
        
        try{
            return (jpaEmple.findEmpleado(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    public void eliminarLogicoEmpleado(Empleado emple) {
        
        try {
            jpaEmple.edit(emple);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Empleado> getEmpleados() {
        
        try{
            return (jpaEmple.findEmpleadoEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    public void editarEmpleado(Empleado emple) {
        
        try{
            jpaEmple.edit(emple);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE USUARIO">   
    
    public void crearUsuario(Usuario usu) {
        
        try{
            jpaUsu.create(usu);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarUsuario(Usuario usu) {
        
        try{
            jpaUsu.edit(usu);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PERSONA">   
    
    public void crearPersona(Persona perso) {
        
        try{
            jpaPerso.create(perso);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarPersona(Persona perso) {
        
        try{
            jpaPerso.edit(perso);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Persona> getPersonas() {
        
        try{
            return (jpaPerso.findPersonaEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE CLIENTE">   
    
    public void crearCliente(Cliente clien) {
        
        try{
            jpaCliente.create(clien);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Cliente getClienteById(int Id) {
        
        try{
            return (jpaCliente.findCliente(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public void eliminarLogicoCliente(Cliente clien) {
        
        try {
            jpaCliente.edit(clien);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarCliente(Cliente clien) {
        
        try {
            jpaCliente.edit(clien);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> getClientes() {
        
        try{
            return (jpaCliente.findClienteEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE VENTA">   
    
    public void crearVenta(Venta venta) {
        
        try{
            jpaVenta.create(venta);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Venta getVentaById(int Id) {
        
        try{
            return (jpaVenta.findVenta(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public List<Venta> getVentas() {
        
        try{
            return (jpaVenta.findVentaEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }

    public void eliminarFisicoVenta(int id_Venta) {
        
        try {
            jpaVenta.destroy(id_Venta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void editarVenta(Venta venta) {
        try {
            jpaVenta.edit(venta);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE MEDIOPAGO">   
    
    public void crearMedioPago(MedioPago medioPago) {
        
        try{
            jpaMedio.create(medioPago);
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void editarMedioPago(MedioPago medioPago) {
        try {
            jpaMedio.edit(medioPago);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<MedioPago> getMediosPago() {
        
        try{
            return (jpaMedio.findMedioPagoEntities());
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    public MedioPago getMedioPagoById(int Id) {
        
        try{
            return (jpaMedio.findMedioPago(Id));
        }catch (Exception ex){
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        
        }
        return null;
    }
    
    //</editor-fold>
    
    
}