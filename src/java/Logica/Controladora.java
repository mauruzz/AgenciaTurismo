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
    MedioPago medioPago = new MedioPago ();
    int maxServicioXPaquete = 5;  //------ Cantidad maxima de servicios por paquete turistico
    double descuento = 0.90;  //------ Descuento por realizar compra de paquete turistico
    
    
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS LOGIN">
    
    public int login(String usuario, String contrasenia) {
        
        List<Usuario> listaUsuarios = controlPersis.getUsuarios();
        boolean usuHabilitado = false;
        
        for (Usuario usu : listaUsuarios) {
            if(usu.getUsuario().equals(usuario) && usu.getContrasenia().equals(contrasenia))
                /*
                usuHabilitado = validarUsuario (usu.getId_usuario());
            
                if (usuHabilitado) {
                
                }
            }*/
                return usu.getId_usuario();
        }
        return 0;
    }
    
    public boolean validarUsuario(int idUsuario) {
        
        //      TERMINAR !!
        
        //      RECORDAR TOCAR EL SERVLET TAMBIEN
        
        return false;
    }
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE SERVICIO TURISTICO">
    
    public void crearServicioTuristico(String Nombre, String Descripcion, String Destino, String str_Costo, String str_Fecha) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        double Costo = Double.parseDouble(str_Costo);
        Date Fecha = deStringToDate(str_Fecha);
        
        /*--------------------------------
        Creo el ServicioTuristico y lo mando a la persistencia
        --------------------------------*/ 
        
        servTuri = emple.crearServicioTuristico(Nombre, Descripcion, Destino, Costo, Fecha);
        
        controlPersis.crearServicioTuristico(servTuri);
        
    }
    
    public List<ServicioTuristico> getListaServicios () {
        
        return (this.controlPersis.getServicios());
    }
    
    public List<ServicioTuristico> getListaServiciosHabilitados () {
        /*--------------------------------
        Busco la lista completa de servicios, se la paso a empleado para que la filtre y retorne los habilitados
        --------------------------------*/ 
        
        List<ServicioTuristico> listaServi = getListaServicios();
        
        return emple.getListaServiciosHabilitados(listaServi);
    }
    
    public ServicioTuristico getServicioTuristicoById(String str_Id) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int Id = Integer.parseInt(str_Id);
        
        servTuri = controlPersis.getServicioTuristicoById(Id);
        
        return servTuri;
    }
    
    public void eliminarLogicoServicioTuristico(String str_Id) {
        /*--------------------------------
        Hago un eliminado logico del registro desabilitandolo, de esta manera las ventas realizadas con este 
        ServicioTuristico se seguiran viendo sin tener que modificar el historial de ventas
        --------------------------------*/ 
        
        servTuri = getServicioTuristicoById(str_Id);
        servTuri = emple.eliminarLogicoServicioTuristico(servTuri);
        
        controlPersis.eliminarLogicoServicioTuristico(servTuri);
    }
    
    public void editarServicioTuristico(String str_Id, String Nombre, String Descripcion, String Destino, String str_Costo, String str_Fecha) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int Id = Integer.parseInt(str_Id);
        double Costo = Double.parseDouble(str_Costo);
        Date Fecha = deStringToDate(str_Fecha);
        
        /*--------------------------------
        Edito el registro y lo env?o a la persistencia
        --------------------------------*/ 
        
        servTuri = emple.editarServicioTuristico(Id, Nombre, Descripcion, Destino, Costo, Fecha);
        
        controlPersis.editarServicioTuristico(servTuri);
    }
    
    //</editor-fold>

    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PAQUETE TURISTICO">
    
    public void crearPaqueteTuristico(String Nombre, String Servi_1_Id, String Servi_2_Id, String Servi_3_Id, String Servi_4_Id, String Servi_5_Id) {
        
        /*--------------------------------
        Variables locales
        --------------------------------*/ 
        
        String str_Ids [] = {Servi_1_Id, Servi_2_Id, Servi_3_Id, Servi_4_Id, Servi_5_Id};
        List<ServicioTuristico> listaServicios = new ArrayList<ServicioTuristico>();

        /*--------------------------------
        Cargo los ServicioTuristico en una lista segun las IDs pedidas
        --------------------------------*/ 
        
        for (int i = 0; i < maxServicioXPaquete; i++) {
            
            if (!str_Ids[i].equals("")) {
                
                servTuri = getServicioTuristicoById(str_Ids[i]);
                listaServicios.add(servTuri);
            }
        }
        
        /*--------------------------------
        Creo el PaqueteTuristico y lo mando a la persistencia
        --------------------------------*/ 
        
        paqTuri = emple.crearPaqueteTuristico(Nombre, descuento, listaServicios);
        
        controlPersis.crearPaqueteTuristico(paqTuri);
        
    }
    
    public List<PaqueteTuristico> getListaPaquetes (){
        
        return (this.controlPersis.getPaquetes());
    }
    
    public List<PaqueteTuristico> getListaPaquetesHabilitados () {
        /*--------------------------------
        Busco la lista completa de paquetes, se la paso a empleado para que la filtre y retorne los habilitados
        --------------------------------*/ 
        
        List<PaqueteTuristico> listaPaque = getListaPaquetes();
        
        return emple.getListaPaquetesHabilitados(listaPaque);
    }
        
    public PaqueteTuristico getPaqueteTuristicoById(String str_Id) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int Id = Integer.parseInt(str_Id);
        
        paqTuri = controlPersis.getPaqueteTuristicoById(Id);
        
        return paqTuri;
    }

    public void eliminarLogicoPaqueteTuristico(String str_Id) {
        /*--------------------------------
        Hago un eliminado logico del registro desabilitandolo, de esta manera las ventas realizadas con este 
        PaqueteTuristico se seguiran viendo sin tener que modificar el historial de ventas
        --------------------------------*/ 
        
        paqTuri = getPaqueteTuristicoById(str_Id);
        paqTuri = emple.eliminarLogicoPaqueteTuristico(paqTuri);
        
        controlPersis.eliminarLogicoPaqueteTuristico(paqTuri);
    }
    
    public void editarPaqueteTuristico(String str_Id, String Nombre, String str_Servicio_1, String str_Servicio_2, String str_Servicio_3, String str_Servicio_4, String str_Servicio_5) {
        
        /*--------------------------------
        Variables locales
        --------------------------------*/ 
        
        int Id = Integer.parseInt(str_Id);
        String str_Ids [] = {str_Servicio_1, str_Servicio_2, str_Servicio_3, str_Servicio_4, str_Servicio_5};
        List<ServicioTuristico> listaServicios = new ArrayList<ServicioTuristico>();
        double costo = 0;
        
        /*--------------------------------
        Cargo los ServicioTuristico en una lista segun las IDs pedidas
        --------------------------------*/ 
        
        for (int i = 0; i < maxServicioXPaquete; i++) {
            
            if (!str_Ids[i].equals("")) {
                
                servTuri = getServicioTuristicoById(str_Ids[i]);
                listaServicios.add(servTuri);
            }
        }
        
        /*--------------------------------
        Edito el PaqueteTuristico y lo mando a la persistencia
        --------------------------------*/ 
        
        paqTuri = emple.editarPaqueteTuristico(Id, Nombre, listaServicios, descuento);
        
        controlPersis.editarPaqueteTuristico(paqTuri);
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE EMPLEADO">
    
    public void crearEmpleado(String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email, String Usuario, String Contrasenia, String Cargo, String str_Sueldo) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int dni = Integer.parseInt(str_Dni);
        Date fecha = deStringToDate(str_Fecha_Nac);
        
        /*--------------------------------
        Creo a Persona y se la envio a la persistencia
        --------------------------------*/ 
        
        perso = emple.crearPersona(Nombre, Apellido, Direccion, dni, fecha, Nacionalidad, Celular, Email);
        crearPersona(perso);    //Metodo que llama a Persistencia
        
        /*--------------------------------
        Creo a Usuario y se la envio a la persistencia
        --------------------------------*/ 
        
        usu = emple.crearUsuario(Usuario, Contrasenia);
        crearUsuario(usu);
        
        /*--------------------------------
        Asigno atributos del empleado y envio el Empleado a la persistencia
        --------------------------------*/ 
        
        emple.setPersona(perso);
        emple.setUsuario(usu);
        emple.setCargo(Cargo);
        emple.setSueldo(Double.parseDouble(str_Sueldo));
        emple.setHabilitado(true);
        controlPersis.crearEmpleado(emple);
        
    }
    
    public Empleado getEmpleadoById(String str_Id) {
        
        emple = controlPersis.getEmpleadoById(Integer.parseInt(str_Id));
        
        return emple;
    }
    
    public void eliminarLogicoEmpleado(String str_Id) {
        /*--------------------------------
        Hago un eliminado logico del registro desabilitandolo, de esta manera las ventas realizadas con este 
        Empleado se seguiran viendo sin tener que modificar el historial de ventas
        --------------------------------*/ 
        
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
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int id_Empleado = Integer.parseInt(str_Id);
        int id_Persona = getIdPersonaFromIdEmpleado(id_Empleado);
        int id_Usuario = getIdUsuarioFromIdEmpleado(id_Empleado);
        int dni = Integer.parseInt(str_Dni);
        Date fecha = deStringToDate(str_Fecha_Nac);
        
        /*--------------------------------
        Edito a Persona y se la envio a la persistencia
        --------------------------------*/ 
        
        perso = emple.editarPersona(id_Persona, Nombre, Apellido, dni, Direccion, fecha, Nacionalidad, Celular, Email);
        editarPersona(perso);
        
        /*--------------------------------
        Edito a Usuario y se la envio a la persistencia
        --------------------------------*/ 
        
        usu = emple.editarUsuario(id_Usuario, Usuario, Contrasenia);
        editarUsuario(usu);
        
        /*--------------------------------
        Asigno atributos editados del empleado y envio el Empleado a la persistencia
        --------------------------------*/ 
        
        emple.setUsuario(usu);
        emple.setPersona(perso);
        emple.setId_empleado(id_Empleado);
        emple.setCargo(Cargo);
        emple.setSueldo(Double.parseDouble(str_Sueldo));
        emple.setHabilitado(true);
        
        controlPersis.editarEmpleado(emple);
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE USUARIO">

    public void crearUsuario(Usuario usu) {
        
        controlPersis.crearUsuario(usu);
    }
    
    public void editarUsuario(Usuario usu) {
        
        controlPersis.editarUsuario(usu);
    }
    
    
    /*
    
    IMPORTANTE !! 
    
    FALTA SEGURIDAD PARA QUE LOS EMPLEAOS DESHABILITADOS NO SE PUEDAN CONECTAR
    HAYQ UE MODIFICAR LA SEGURIDAD DE CADA PAGINA Y LA DEL SERVLET
    
    */
    
    
    
    //</editor-fold>    
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE PERSONA">
    
    public void crearPersona(Persona perso) {
        
        controlPersis.crearPersona(perso);
    }
    
    public void editarPersona(Persona perso) {
        
        controlPersis.editarPersona(perso);
    }
    
    public List<Persona> getListaPersonas() {
        
        return (this.controlPersis.getPersonas()); 
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE CLIENTE">
       
    public void crearCliente(String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int dni = Integer.parseInt(str_Dni);
        Date fecha = deStringToDate(str_Fecha_Nac);
        
        /*--------------------------------
        Creo a Persona y se la envio a la persistencia
        --------------------------------*/ 
        
        perso = emple.crearPersona(Nombre, Apellido, Direccion, dni, fecha, Nacionalidad, Celular, Email);
        crearPersona(perso);    //Metodo que llama a Persistencia
        
        /*--------------------------------
        Creo Cliente, asigno atributos y lo env?o a la persistencia 
        --------------------------------*/ 
        
        clien = emple.crearCliente(perso);
        controlPersis.crearCliente(clien);
    }
    
    public Cliente getClienteById(int Id) {
        
        clien = controlPersis.getClienteById(Id);
        
        return clien;
    }
    
    public void eliminarLogicoCliente(String str_Id) {
        /*--------------------------------
        Hago un eliminado logico del registro desabilitandolo, de esta manera las ventas realizadas con este 
        Cliente se seguiran viendo sin tener que modificar el historial de ventas
        --------------------------------*/ 
        
        int Id = Integer.parseInt(str_Id);
        clien = getClienteById(Id);
        clien = emple.eliminarLogicoCliente(clien);
        
        controlPersis.eliminarLogicoCliente(clien);
    }
    
    public int getIdPersonaFromIdCliente(int Id) {
        
        clien = getClienteById(Id);
        
        perso = clien.getPersona();
        
        int id = (int)perso.getId_Persona();
        
        return id;
    }
    
    public void editarCliente(String str_Id, String Nombre, String Apellido, String Direccion, String str_Dni, String str_Fecha_Nac, String Nacionalidad, String Celular, String Email) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int id_Cliente = Integer.parseInt(str_Id);
        int id_Persona = (int)getIdPersonaFromIdCliente(id_Cliente);
        int dni = Integer.parseInt(str_Dni);
        Date fecha = deStringToDate(str_Fecha_Nac);
            
        /*--------------------------------
        Edito a Persona y se la envio a la persistencia
        --------------------------------*/ 
        
        perso = emple.editarPersona(id_Persona, Nombre, Apellido, dni, Direccion, fecha, Nacionalidad, Celular, Email);
        editarPersona(perso);
        
        /*--------------------------------
        Edito a Cliente asignandole a Persona previamente creada 
        --------------------------------*/ 
        
        clien = emple.editarCliente(id_Cliente, perso);
        controlPersis.editarCliente(clien);
    }
    
    public List<Cliente> getListaClientes() {
        
        return (this.controlPersis.getClientes()); 
    }
    
    public List<Cliente> getListaClientesHabilitados (){
        
        List<Cliente> listaClien = getListaClientes();
        
        return emple.getListaClientesHabilitados(listaClien); 
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE VENTA">
    
    public void crearVenta(String IdEmpleado, String IdCliente, String ServPaq, String Cantidad, String MedioPago) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        DateTimeFormatter str_Fecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Date fecha = deStringToDate(str_Fecha.format(LocalDateTime.now()));
        int cantidad = Integer.parseInt(Cantidad);
        int Id_Cliente = Integer.parseInt(IdCliente);
        medioPago = getMedioPagoById(Integer.parseInt(MedioPago));
        
        /*--------------------------------
        Creo venta y lo env?o a la persistencia 
        --------------------------------*/ 
        
        venta = emple.crearVenta(fecha, cantidad, medioPago); 
        controlPersis.crearVenta(venta);
        
        /*--------------------------------
        Agrego la venta al Empleado correspondiente y lo mando a la persistencia
        --------------------------------*/ 
        
        emple = getEmpleadoById(IdEmpleado);
        emple.getListaVentas().add(venta);
        controlPersis.editarEmpleado(emple);
        
        /*--------------------------------
        Agrego la venta al Cliente correspondiente y lo env?o a la persistencia 
        --------------------------------*/ 
        
        clien = getClienteById(Id_Cliente);
        clien.getListaVentas().add(venta);
        controlPersis.editarCliente(clien);
        
        /*--------------------------------
        Agrego la venta al Servicio o PaqueteTuristico que corresponda, donde:
        
        La Posicion [0] corresponde al tipo de producto (servicio o paquete)
        La posicion [1] corresponde al Id del producto
        --------------------------------*/ 
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
    
    public Venta getVentaById(int id) {
        
        venta = controlPersis.getVentaById(id);
        
        return venta;
    }
    
    public List<Venta> getListaVentas() {
        
        return (this.controlPersis.getVentas()); 
    }
    
    public Cliente getClienteFromVenta(Venta venta_Actual) {
                
        List<Cliente> listaClientes = getListaClientes();
        
        clien = emple.getClienteFromVenta(venta_Actual, listaClientes);
            
        return clien;
    }
    
    public Empleado getEmpleadoFromVenta(Venta venta_Actual) {
        
        List<Empleado> listaEmpleados = getListaEmpleados();
        Empleado empleAux = new Empleado ();
        
        empleAux = emple.getEmpleadoFromVenta(venta_Actual, listaEmpleados);
        
        return empleAux;
    }
    
    public ServicioTuristico getServicioFromVenta(Venta venta_Actual) {
        
        List<ServicioTuristico> listaServicios = getListaServicios();
        
        servTuri = emple.getServicioFromVenta(venta_Actual, listaServicios);
        
        return servTuri;
    }

    public PaqueteTuristico getPaqueteFromVenta(Venta venta_Actual) {
        
        List<PaqueteTuristico> listaPaquetes = getListaPaquetes();
        
        paqTuri = emple.getPaqueteFromVenta(venta_Actual, listaPaquetes);
        
        return paqTuri;
    }
    
    public void eliminarVenta(String str_Id) {
        
        venta = getVentaById(Integer.parseInt(str_Id));
        
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
        
        venta = getVentaById(Integer.parseInt(IdVenta));
        medioPago = getMedioPagoById(Integer.parseInt(MedioPago));
        
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
        venta.setMedio_pago(medioPago);
        
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
    //</editor-fold>    
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS DE MEDIOPAGO">
    
    public void crearMedioPago(String nombre, String str_descuento, String str_habilitado) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        double descuento = Double.parseDouble(str_descuento);
        boolean habilitado = false;
        if (str_habilitado.equals("1")){
            habilitado=true;
        }   
        
        /*--------------------------------
        Creo a MedioPago y se la envio a la persistencia
        --------------------------------*/ 
        
        medioPago = emple.crearMedioPago(nombre, descuento, habilitado);
        controlPersis.crearMedioPago(medioPago);        //Metodo que llama a Persistencia
        
    }
    
    public void editarMedioPago(String str_id, String nombre, String str_descuento, String str_habilitado) {
        
        /*--------------------------------
        Transformo los datos ingresados por teclado al tipo necesario
        --------------------------------*/ 
        
        int id = Integer.parseInt(str_id);
        double descuento = Double.parseDouble(str_descuento);
        boolean habilitado = false;
        if (str_habilitado.equals("1")){
            habilitado = true;
        }   
        
        /*--------------------------------
        Busco a MedioPago y le cargo las modificaciones
        --------------------------------*/ 
        
        medioPago = getMedioPagoById(id);
        medioPago.setNombre(nombre);
        medioPago.setDescuento(descuento);
        medioPago.setHabilitado(habilitado);
        
        /*--------------------------------
        Env??o todo a la persistencia
        --------------------------------*/ 
        
        controlPersis.editarMedioPago(medioPago); 
        
    }
    
    public List<MedioPago> getListaMediosPago() {
        
        return (this.controlPersis.getMediosPago()); 
    }
    
    public List<MedioPago> getListaMediosPagoHabilitados () {
        
        List<MedioPago> listaMediosPago = getListaMediosPago();
        
        return emple.getListaMediosPagoHabilitados(listaMediosPago);
    }
    
    public MedioPago getMedioPagoById(int Id) {
        
        medioPago = controlPersis.getMedioPagoById(Id);
        
        return medioPago;
    }
    
    //</editor-fold>  
        
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="METODOS PARA MANEJO DE FECHA/DATE   SIN VER">
    
    //Convierte un String a un tipo DATE en formato dd-MM-yyyy, se puede cambiar el formato a / 
    public static synchronized java.util.Date deStringToDate (String fecha) {
        
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //formato gui??n
        Date fechaEnviar = null;
        try {
            fechaEnviar = df.parse(fecha);
            return fechaEnviar;
        } catch (ParseException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    //Convertir un DATE a String (ser??a de la DB al HTML)
    public static String deDateToString (Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd"); //formato de gui??n
        return formatoFecha.format(fecha);
    }
    
    public int getMesFromDate (Date fecha) {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("MM"); 
        return Integer.parseInt(formatoFecha.format(fecha));
    }
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
}