package Logica;

import Persistencia.ControladoraPersistencia;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controladora {

// --------- VARIABLES GLOBALES
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    ServicioTuristico servTuri = new ServicioTuristico();
    PaqueteTuristico paqTuri = new PaqueteTuristico ();
    Empleado emple = new Empleado ();
    Usuario usu = new Usuario ();
    Persona perso = new Persona ();
    Cliente clien = new Cliente ();
    Venta venta = new Venta ();
    int maxServicioXPaquete = 5;  //------ Cantidad maxima de servicios por paquete turistico
    double descuento = 0.90;  //------ Descuento por realizar compra de paquete turistico
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS LOGIN">
    public int login(String usuario, String contrasenia) {
        
        List<Usuario> listaUsuarios = controlPersis.getUsuarios();
        
        for (Usuario usu : listaUsuarios) {
            if(usu.getUsuario().equals(usuario) && usu.getContrasenia().equals(contrasenia))
                return usu.getId_usuario();
        }
        return 0;
    }
//</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE SERVICIO TURISTICO">
    public void crearServicioTuristico(String Nombre, String Descripcion, String Destino, String str_Costo, String str_Fecha) {
        
        double Costo = Double.parseDouble(str_Costo);
        Date Fecha = deStringToDate(str_Fecha);
        
        servTuri = emple.crearServicioTuristico(Nombre, Descripcion, Destino, Costo, Fecha);
        
        controlPersis.crearServicioTuristico(servTuri);
        
    }
    
    public List<ServicioTuristico> getListaServicios () {
        
        return (this.controlPersis.getServicios());
    }
    
    public List<ServicioTuristico> getListaServiciosHabilitados () {
        
        List<ServicioTuristico> listaServi = getListaServicios();
        
        return emple.getListaServiciosHabilitados(listaServi);
    }
    
    public ServicioTuristico getServicioTuristicoById(String str_Id) {
        
        int Id = Integer.parseInt(str_Id);
        servTuri = controlPersis.getServicioTuristicoById(Id);
        
        return servTuri;
    }
    
    public void eliminarLogicoServicioTuristico(String str_Id) {
        
        servTuri = getServicioTuristicoById(str_Id);
        
        controlPersis.eliminarLogicoServicioTuristico(emple.eliminarLogicoServicioTuristico(servTuri));
    }
    
    public void editarServicioTuristico(String str_Id, String Nombre, String Descripcion, String Destino, String str_Costo, String str_Fecha) {
        
        int Id = Integer.parseInt(str_Id);
        double Costo = Double.parseDouble(str_Costo);
        Date Fecha = deStringToDate(str_Fecha);
        
        servTuri = emple.editarServicioTuristico(Id, Nombre, Descripcion, Destino, Costo, Fecha);
        
        controlPersis.editarServicioTuristico(servTuri);
    }
//</editor-fold>

    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PAQUETE TURISTICO">
    public void crearPaqueteTuristico(String Nombre, String Servi_1_Id, String Servi_2_Id, String Servi_3_Id, String Servi_4_Id, String Servi_5_Id) {
        
        String str_Ids [] = {Servi_1_Id, Servi_2_Id, Servi_3_Id, Servi_4_Id, Servi_5_Id};
        List<ServicioTuristico> listaServicios = new ArrayList<ServicioTuristico>();
        double costo = 0;
        
        for (int i = 0; i < maxServicioXPaquete; i++) {
            
            if (!str_Ids[i].equals("")) {
                
                servTuri = getServicioTuristicoById(str_Ids[i]);
                listaServicios.add(servTuri);
            }
        }
        
        for (ServicioTuristico servi : listaServicios){
            
            costo = costo + (servi.getCosto_servicio() * descuento);
        }
        
        paqTuri = emple.crearPaqueteTuristico(Nombre, costo, listaServicios);
        
        controlPersis.crearPaqueteTuristico(paqTuri);
        
    }
    
    public List<PaqueteTuristico> getListaPaquetes (){
        
        return (this.controlPersis.getPaquetes());
    }
    
    public PaqueteTuristico getPaqueteTuristicoById(String str_Id) {
        
        int Id = Integer.parseInt(str_Id);
        paqTuri = controlPersis.getPaqueteTuristicoById(Id);
        
        return paqTuri;
    }
    
    public List<PaqueteTuristico> getListaPaquetesHabilitados () {
        
        List<PaqueteTuristico> listaPaque = getListaPaquetes();
        
        return emple.getListaPaquetesHabilitados(listaPaque);
    }
    
    public void eliminarLogicoPaqueteTuristico(String str_Id) {
        
        paqTuri = getPaqueteTuristicoById(str_Id);
        
        controlPersis.eliminarLogicoPaqueteTuristico(emple.eliminarLogicoPaqueteTuristico(paqTuri));
    }
    
    public void editarPaqueteTuristico(String str_Id, String Nombre, String str_Servicio_1, String str_Servicio_2, String str_Servicio_3, String str_Servicio_4, String str_Servicio_5) {
        
        String str_Ids [] = {str_Servicio_1, str_Servicio_2, str_Servicio_3, str_Servicio_4, str_Servicio_5};
        List<ServicioTuristico> listaServicios = new ArrayList<ServicioTuristico>();
        double costo = 0;
        
        for (int i = 0; i < maxServicioXPaquete; i++) {
            
            if (!str_Ids[i].equals("")) {
                
                servTuri = getServicioTuristicoById(str_Ids[i]);
                listaServicios.add(servTuri);
            }
        }
        
        for (ServicioTuristico servi : listaServicios){
            
            costo = costo + (servi.getCosto_servicio() * descuento);
        }
        
        int Id = Integer.parseInt(str_Id);
        
        paqTuri = emple.editarPaqueteTuristico(Id, Nombre, listaServicios, costo);
        
        controlPersis.editarPaqueteTuristico(paqTuri);
    }
//</editor-fold>
    
    
    
// ------------- METODOS DE EMPLEADO
    
    public void crearEmpleado(String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email, String Usuario, String Contrasenia, String Cargo, String str_Sueldo) {
        
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDireccion(Direccion);
        perso.setDni(Integer.parseInt(str_Dni));
        Date fecha = deStringToDate(str_Fecha_Nac);
        perso.setFecha_nacimiento(fecha);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
        crearPersona(perso);
        emple.setPersona(perso);
        
        usu.setUsuario(Usuario);
        usu.setContrasenia(Contrasenia);
        crearUsuario(usu);
        emple.setUsuario(usu);
        
        emple.setCargo(Cargo);
        emple.setSueldo(Double.parseDouble(str_Sueldo));
        emple.setHabilitado(true);
        controlPersis.crearEmpleado(emple);
        
    }
    
    public Empleado getEmpleadoById(String str_Id) {
        
        int Id = Integer.parseInt(str_Id);
        emple = controlPersis.getEmpleadoById(Id);
        
        return emple;
    }
    
    public void eliminarLogicoEmpleado(String str_Id) {
        
        emple = getEmpleadoById(str_Id);
        emple.setHabilitado(false);
        
        controlPersis.eliminarLogicoEmpleado(emple);
    }
    
    public List<Empleado> getListaEmpleados (){
    
        return (this.controlPersis.getEmpleados()); 
    }
    
    public List<Empleado> getListaEmpleadosHabilitados () {
        
        List<Empleado> listaEmple = getListaEmpleados();
        List<Empleado> listaEmpleHabilitados = new ArrayList<Empleado>();
                
        for (Empleado emple : listaEmple) {
            
            if (emple.isHabilitado() == true){
                
                listaEmpleHabilitados.add(emple);
            }
        }
                
        return listaEmpleHabilitados; 
    }
    
    public int getIdPersonaFromIdEmpleado(int Id) {
        
        emple = controlPersis.getEmpleadoById(Id);
        perso = emple.getPersona();
                
        return perso.getId_Persona();
    }
    
    public int getIdUsuarioFromIdEmpleado(int Id) {
        
        emple = controlPersis.getEmpleadoById(Id);
        usu = emple.getUsuario();
                
        return usu.getId_usuario();
    }
    
    public void editarEmpleado(String str_Id, String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email, String Usuario, String Contrasenia, String Cargo, String str_Sueldo) {
        
        int id_Empleado = Integer.parseInt(str_Id);
        
        perso.setId_Persona(getIdPersonaFromIdEmpleado(id_Empleado));
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDireccion(Direccion);
        perso.setDni(Integer.parseInt(str_Dni));
        Date fecha = deStringToDate(str_Fecha_Nac);
        perso.setFecha_nacimiento(fecha);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
        editarPersona(perso);
        emple.setPersona(perso);
        
        usu.setId_usuario(getIdUsuarioFromIdEmpleado(id_Empleado));
        usu.setUsuario(Usuario);
        usu.setContrasenia(Contrasenia);
        editarUsuario(usu);
        emple.setUsuario(usu);
        
        emple.setId_empleado(id_Empleado);
        emple.setCargo(Cargo);
        emple.setSueldo(Double.parseDouble(str_Sueldo));
        emple.setHabilitado(true);
        
        controlPersis.editarEmpleado(emple);
    }
    
    
    
// ----------------  METODOS DE USUARIO

    public void crearUsuario(Usuario usu) {
        controlPersis.crearUsuario(usu);
    }
    
    public void editarUsuario(Usuario usu) {
        
        controlPersis.editarUsuario(usu);
    }
    
    
// ----------------  METODOS DE PERSONA
    
    public void crearPersona(Persona perso) {
        
        controlPersis.crearPersona(perso);
    }
    
    public void editarPersona(Persona perso) {
        
        controlPersis.editarPersona(perso);
    }
    
    public List<Persona> getListaPersonas() {
        
        return (this.controlPersis.getPersonas()); 
    }
    
// ----------------  METODOS DE CLIENTE    
    
    public void crearCliente(String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email) {
        
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDireccion(Direccion);
        perso.setDni(Integer.parseInt(str_Dni));
        Date fecha = deStringToDate(str_Fecha_Nac);
        perso.setFecha_nacimiento(fecha);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
        crearPersona(perso);
        clien.setPersona(perso);
        
        clien.setHabilitado(true);
        
        controlPersis.crearCliente(clien);
    }
    
    public Cliente getClienteById(String str_Id) {
        
        int Id = Integer.parseInt(str_Id);
        clien = controlPersis.getClienteById(Id);
        
        return clien;
    }
    
    public void eliminarLogicoCliente(String str_Id) {
        
        clien = getClienteById(str_Id);
        clien.setHabilitado(false);
        
        controlPersis.eliminarLogicoCliente(clien);
    }
    
    public int getIdPersonaFromIdCliente(int Id) {
        
        clien = controlPersis.getClienteById(Id);
        
        perso = clien.getPersona();
        
        int id = (int)perso.getId_Persona();
        
        return id;
    }
    
    public void editarCliente(String str_Id, String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email) {
        
        int id_Cliente = Integer.parseInt(str_Id);
        
        perso.setId_Persona((int)getIdPersonaFromIdCliente(id_Cliente));
        
        perso.setNombre(Nombre);
        perso.setApellido(Apellido);
        perso.setDireccion(Direccion);
        perso.setDni(Integer.parseInt(str_Dni));
        Date fecha = deStringToDate(str_Fecha_Nac);
        perso.setFecha_nacimiento(fecha);
        perso.setNacionalidad(Nacionalidad);
        perso.setCelular(Celular);
        perso.setEmail(Email);
        editarPersona(perso);
        clien.setPersona(perso);
                
        clien.setId_cliente(Integer.parseInt(str_Id));
        clien.setHabilitado(true);
        
        controlPersis.editarCliente(clien);
    }
    
    public List<Cliente> getListaClientes() {
        
        return (this.controlPersis.getClientes()); 
    }
    
    public List<Cliente> getListaClientesHabilitados (){
        
        List<Cliente> listaClien = getListaClientes();
        List<Cliente> listaClienHabilitados = new ArrayList<Cliente>();
                
        for (Cliente clien : listaClien) {
            
            if (clien.isHabilitado() == true){
                
                listaClienHabilitados.add(clien);
            }
        }
                
        return listaClienHabilitados; 
    }
    
    
    
// ----------------  METODOS DE VENTA
    
    public void crearVenta(String IdEmpleado, String IdCliente, String ServPaq, String Cantidad, String MedioPago) {
        
        DateTimeFormatter str_Fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fecha = deStringToDate(str_Fecha.format(LocalDateTime.now()));
        venta.setFecha_venta(fecha);
        
        venta.setCantidad(Integer.parseInt(Cantidad));
        venta.setMedio_pago(MedioPago);
        controlPersis.crearVenta(venta);
        
        emple = getEmpleadoById(IdEmpleado);
        emple.getListaVentas().add(venta);
        controlPersis.editarEmpleado(emple);
        
        clien = getClienteById(IdCliente);
        clien.getListaVentas().add(venta);
        controlPersis.editarCliente(clien);
        
        // La Posicion [0] corresponde al tipo de producto (servicio o paquete)
        // La posicion [1] corresponde al Id del producto
        String[] vec_ServPaq = ServPaq.split("-");
        
        if (vec_ServPaq[0].equals("servicio")) {
            servTuri = getServicioTuristicoById(vec_ServPaq[1]);
            servTuri.getListaVentas().add(venta);
            controlPersis.editarServicioTuristico(servTuri);
        }
        
        if (vec_ServPaq[0].equals("paquete")) {
            paqTuri = getPaqueteTuristicoById(vec_ServPaq[1]);
            paqTuri.getListaVentas().add(venta);
            controlPersis.editarPaqueteTuristico(paqTuri);
        }
          
    }
    
    public Venta getVentaById(String str_Id) {
        
        int Id = Integer.parseInt(str_Id);
        
        venta = controlPersis.getVentaById(Id);
        
        return venta;
    }
    
    public List<Venta> getListaVentas() {
        
        return (this.controlPersis.getVentas()); 
    }
    
    public Cliente getClienteFromVenta(Venta venta_Actual) {
                
        List<Cliente> listaClientes = getListaClientes();
        List<Venta> listaVentas = new ArrayList<Venta> ();
        
        for (Cliente cliente : listaClientes) {
            
            listaVentas = cliente.getListaVentas();
            
            for (Venta venta : listaVentas)
                if (venta.equals(venta_Actual)) {
                    return cliente;
                }
        }
        
        return null;
    }
    
    public Empleado getEmpleadoFromVenta(Venta venta_Actual) {
        
        List<Empleado> listaEmpleados = getListaEmpleados();
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
    
    public ServicioTuristico getServicioFromVenta(Venta venta_Actual) {
        
        List<ServicioTuristico> listaServicios = getListaServicios();
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

    public PaqueteTuristico getPaqueteFromVenta(Venta venta_Actual) {
        
        List<PaqueteTuristico> listaPaquetes = getListaPaquetes();
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
    
    public void eliminarVenta(String str_Id) {
        
        venta = getVentaById(str_Id);
        
        clien = getClienteFromVenta(venta);
        emple = getEmpleadoFromVenta(venta);
        servTuri = getServicioFromVenta(venta);
        paqTuri = getPaqueteFromVenta(venta);
        
        clien.getListaVentas().remove(venta);
        controlPersis.editarCliente(clien);
        
        emple.getListaVentas().remove(venta);
        controlPersis.editarEmpleado(emple);
        
        if (servTuri != null){
            if (servTuri.getListaVentas().contains(venta)) {
                servTuri.getListaVentas().remove(venta);
                controlPersis.editarServicioTuristico(servTuri);
            }
        }
        if (paqTuri != null){
            if (paqTuri.getListaVentas().contains(venta)) {
                paqTuri.getListaVentas().remove(venta);
                controlPersis.editarPaqueteTuristico(paqTuri);
            }
        }
            
        controlPersis.eliminarFisicoVenta(venta.getNum_venta());
    }
    
    public void editarVenta(String IdVenta, String IdCliente, String ServPaq, String Cantidad, String MedioPago) {
        
        venta = getVentaById(IdVenta);
        
        //----------- BORRO EL REGISTRO VIEJO
        
        servTuri = getServicioFromVenta(venta);
        paqTuri = getPaqueteFromVenta(venta);
        
        if (servTuri != null){
            servTuri.getListaVentas().remove(venta);
            controlPersis.editarServicioTuristico(servTuri);
        }
        
        if (paqTuri != null){
            paqTuri.getListaVentas().remove(venta);
            controlPersis.editarPaqueteTuristico(paqTuri);
        }
        
        // ------------ AGREGO EL NUEVO REGISTRO
        
        venta.setCantidad(Integer.parseInt(Cantidad));
        venta.setMedio_pago(MedioPago);
        
        // La Posicion [0] corresponde al tipo de producto (servicio o paquete)
        // La posicion [1] corresponde al Id del producto
        String[] vec_ServPaq = ServPaq.split("-");
        
        if (vec_ServPaq[0].equals("servicio")) {
            servTuri = getServicioTuristicoById(vec_ServPaq[1]);
            servTuri.getListaVentas().add(venta);
            controlPersis.editarServicioTuristico(servTuri);
        }
        
        if (vec_ServPaq[0].equals("paquete")) {
            paqTuri = getPaqueteTuristicoById(vec_ServPaq[1]);
            paqTuri.getListaVentas().add(venta);
            controlPersis.editarPaqueteTuristico(paqTuri);
        }
        
        
        controlPersis.editarVenta(venta);
    }
    
    
    
    
// ----------------  METODOS PARA MANEJO DE FECHA/DATE    

    //Convierte un String a un tipo DATE en formato dd-MM-yyyy, se puede cambiar el formato a / 
    public static synchronized java.util.Date deStringToDate (String fecha) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //formato guión
        Date fechaEnviar = null;
        try {
            fechaEnviar = df.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    //Convertir un DATE a String (sería de la DB al HTML)
    public static String deDateToString (Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); //formato de guión
        return formatoFecha.format(fecha);
    }
    
    public int getMesFromDate (Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM"); 
        return Integer.parseInt(formatoFecha.format(fecha));
    }

    
}