package Logica_test;

import Logica.Cliente;
import Logica.Empleado;
import Logica.MedioPago;
import Logica.PaqueteTuristico;
import Logica.Persona;
import Logica.ServicioTuristico;
import Logica.Usuario;
import Logica.Venta;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class EmpleadoTest {
    
    /*      AQUÍ SE ENCUENTRAN TODOS LOS TEST DE LA CLASE EMPLEADO      */
    
    
    //<editor-fold defaultstate="collapsed" desc="VARIABLES GLOBALES">
    
    private Persona perso_1, perso_2, perso_3;
    private Usuario usu_1, usu_2;
    private Empleado emple_1, emple_2;
    private ServicioTuristico servTuri_1, servTuri_2, servTuri_3;
    private Cliente clien_1, clien_2, clien_3;
    private MedioPago medio_pago_1, medio_pago_2, medio_pago_3;
    private Venta venta_1, venta_2, venta_3;
    
    /*      DATOS DEL EMPLEADOS     */
    
    int id_1 = 1;
    String cargo_1 = "Vendedor";
    double sueldo_1 = 100.59;
    boolean habilitado_1 = true;
    
    int id_2 = 2;
    String cargo_2 = "Encargado";
    double sueldo_2 = 1000.59;
    boolean habilitado_2 = true;
    
    
    /*      DATOS SERVICIOS TURISTICOS      */
    
    String nombre_serv_1 = "Hotel 1";
    String descripcion_serv_1 = "Descripcion Hotel 1";
    String destino_serv_1 = "Buenos Aires";
    double costo_1 = 101.59;
    
    String nombre_serv_2 = "Hotel 2";
    String descripcion_serv_2 = "Descripcion Hotel 2";
    String destino_serv_2 = "Salta";
    double costo_2 = 102.59;
    
    int id_3 = 3;
    String nombre_serv_3 = "Hotel 3";
    String descripcion_serv_3 = "Descripcion Hotel 3";
    String destino_serv_3 = "Jujuy";
    double costo_3 = 103.59;
    boolean habilitado_3 = false;
    
    
    /*      DATOS PAQUETES TURISTICOS       */
    
    String nombre_paq_1 = "Combo hoteles 1";
    
    String nombre_paq_2 = "Combo hoteles 2";
    
    String nombre_paq_3 = "Combo hoteles 3";
    
    double descuento_paq_tur = 0.9;     //  Se trata de un 10% de descuento por ser un paquete
    
    
    /*      DATOS PERSONAS       */
    
    String nombre_perso_1 = "Jose";
    String apellido_perso_1 = "Perez";
    String direccion_perso_1 = "Av. Mayo 1000";
    int dni_perso_1 = 12345678;
    String nacionalidad_perso_1 = "Argentino";
    String celu_perso_1 = "1512345678";
    String mail_perso_1 = "jose_perez@gmail.com";
    
    String nombre_perso_2 = "Mariano";
    String apellido_perso_2 = "Arraga";
    String direccion_perso_2 = "Av. Cruz 2000";
    int dni_perso_2 = 87654321;
    String nacionalidad_perso_2 = "Uruguayo";
    String celu_perso_2 = "1511223344";
    String mail_perso_2 = "marian_URU@gmail.com";

    String nombre_perso_3 = "Sofia";
    String apellido_perso_3 = "Arevalo";
    String direccion_perso_3 = "Av. Pedro Diaz 3000";
    int dni_perso_3 = 63636363;
    String nacionalidad_perso_3 = "Suiza";
    String celu_perso_3 = "1599889988";
    String mail_perso_3 = "sofichula@gmail.com";
    
    
    /*      DATOS USUARIOS       */
    
    String usuario_1 = "usuario1";
    String contrasenia_1 = "usuario1";
    
    String usuario_2 = "usuario2";
    String contrasenia_2 = "usuario2";
    
    
    /*      DATOS VENTAS       */
    
    int cantidad_1 = 1;
    int cantidad_2 = 2;
    int cantidad_3 = 3;
    
    
    /*      DATOS MEDIO PAGO       */
    
    String nombre_medio_1 = "medio de pago 1";
    double descuento_1 = 10;
    
    String nombre_medio_2 = "medio de pago 2";
    double descuento_2 = 20;
    
    String nombre_medio_3 = "medio de pago 3";
    double descuento_3 = 30;
        
    
    /*      FECHAS      */
    
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd"); //formato
    String str_date_1 = "2020-01-01";                   //valor
    String str_date_2 = "2020-01-02";                   //valor
    String str_date_3 = "2020-01-02";                   //valor
    Date fecha_1 = null;
    Date fecha_2 = null;
    Date fecha_3 = null;
    
    //</editor-fold>
    
    public EmpleadoTest() {
    }
    
    @Before
    public void setUp() {
    
        //  SETEO DE FECHAS
        try {
            fecha_1 = df.parse(str_date_1);
            fecha_2 = df.parse(str_date_2);
            fecha_3 = df.parse(str_date_3);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        //  SETEO DE INSTANCIAS VARIAS
        
        perso_1 = new Persona (id_1, nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1 , nacionalidad_perso_1, celu_perso_1, mail_perso_1, fecha_1);
        perso_2 = new Persona (id_2, nombre_perso_2, apellido_perso_2, direccion_perso_2, dni_perso_2 , nacionalidad_perso_2, celu_perso_2, mail_perso_2, fecha_2);
        perso_3 = new Persona (id_3, nombre_perso_3, apellido_perso_3, direccion_perso_3, dni_perso_3 , nacionalidad_perso_3, celu_perso_3, mail_perso_3, fecha_3);
        
        usu_1 = new Usuario (id_1, usuario_1, contrasenia_1);
        usu_2 = new Usuario (id_2, usuario_2, contrasenia_2);
        
        emple_1 = new Empleado (id_1, cargo_1, sueldo_1, habilitado_1, usu_1, perso_1, null);
        emple_2 = new Empleado (id_2, cargo_2, sueldo_2, habilitado_2, usu_2, perso_2, null);
        
        servTuri_1 = new ServicioTuristico (id_1, nombre_serv_1, descripcion_serv_1, destino_serv_1, costo_1, habilitado_1, fecha_1, /*List<PaqueteTuristico> listaPaquetes*/null, /*List<Venta> listaVentas*/null);
        servTuri_2 = new ServicioTuristico (id_2, nombre_serv_2, descripcion_serv_2, destino_serv_2, costo_2, habilitado_2, fecha_2, /*List<PaqueteTuristico> listaPaquetes*/null, /*List<Venta> listaVentas*/null);
        servTuri_3 = new ServicioTuristico (id_3, nombre_serv_3, descripcion_serv_3, destino_serv_3, costo_3, habilitado_3, fecha_3, /*List<PaqueteTuristico> listaPaquetes*/null, /*List<Venta> listaVentas*/null);
        
        clien_1 = new Cliente (id_1, habilitado_1, perso_1, null);
        clien_2 = new Cliente (id_2, habilitado_2, perso_2, null);
        clien_3 = new Cliente (id_3, habilitado_3, perso_3, null);
        
        medio_pago_1 = new MedioPago (id_1, nombre_medio_1, habilitado_1, descuento_1);
        medio_pago_2 = new MedioPago (id_2, nombre_medio_2, habilitado_2, descuento_2);
        medio_pago_3 = new MedioPago (id_3, nombre_medio_3, habilitado_3, descuento_3);
        
        venta_1 = new Venta(id_1, cantidad_1, fecha_1, medio_pago_1);
        venta_2 = new Venta(id_2, cantidad_2, fecha_2, medio_pago_2);
        venta_3 = new Venta(id_3, cantidad_3, fecha_3, medio_pago_3);
    
    }
    
    @After
    public void tearDown() {
        
        //      PARA ELIMINAR LAS INSTANCIAS DE TODAS LAS CLASES SOLO HAY QUE APUNTAR TODO A NULL
        //      LA JVM SE ENCARGA DE BORRAR TODOS LOS DATOS A LOS QUE NO SE APUNTAN CON EL GARBAGE COLLECTOR
        //      System.gc();
        
        perso_1 = null;
        perso_2 = null;
        perso_3 = null;
        
        usu_1 = null;
        usu_2 = null;
        
        emple_1 = null;
        emple_2 = null;
        
        servTuri_1 = null;
        servTuri_2 = null;
        servTuri_3 = null;
        
        clien_1 = null;
        clien_2 = null;
        clien_3 = null;
        
        medio_pago_1 = null;
        medio_pago_2 = null;
        medio_pago_3 = null;
        
        venta_1 = null;
        venta_2 = null;
        venta_3 = null;
        
    }
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON Empleado">
    
    @Test
    public void testEmpleado() {
        
        /*      EJECUCIÓN       */
        Empleado emple = new Empleado(id_1, cargo_1, sueldo_1, habilitado_1, usu_1, perso_1, null);
        
        /*      PRUEBAS
        
        no se controla cada una de las variables involucradas por ser demasiadas    */
        
        assertEquals(1, emple.getId_empleado());
        assertEquals("Vendedor", emple.getCargo());
        assertThat(emple.getSueldo(), is(sueldo_1));
        assertEquals(true, emple.isHabilitado());
        assertEquals(null, emple.getListaVentas());
        
        assertThat(emple.getUsuario().getId_usuario(), is(usu_1.getId_usuario()));
        assertEquals(usu_1.getUsuario(), emple.getUsuario().getUsuario());
        
        assertThat(emple.getPersona().getId_Persona(), is(perso_1.getId_Persona()));
        assertEquals(perso_1.getNombre(), emple.getPersona().getNombre());
        assertThat(emple.getPersona().getDni(), is(perso_1.getDni()));
        assertEquals(perso_1.getEmail(), emple.getPersona().getEmail());
        
    }

    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON ServicioTuristico">
    
    @Test
    public void testCrearServicioTuristico() {
        
        ServicioTuristico servTuri = new ServicioTuristico ();
        
        /*      EJECUCIÓN       */
        
        servTuri = emple_1.crearServicioTuristico(nombre_serv_1, descripcion_serv_1, destino_serv_1, costo_1, fecha_1);
        
        /*      PRUEBAS     */
        
        assertEquals(nombre_serv_1, servTuri.getNombre());
        assertEquals(descripcion_serv_1, servTuri.getDescripcion_breve());
        assertEquals(destino_serv_1, servTuri.getDestino_servicio());
        assertThat(servTuri.getCosto_servicio(), is(costo_1));
        assertEquals(fecha_1, servTuri.getFecha_servicio());
        
    }
    
    @Test
    public void testGetListaServiciosHabilitados() {
        
        /*      CREANDO ESCENARIO       */
        List<ServicioTuristico> listaServTuriHabilitados = new ArrayList<> ();
        List<ServicioTuristico> listaServTuri = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2, servTuri_3)); //El 3er ServicioTuristico esta deshabilitado
        
        /*      EJECUCIÓN       */
        
        listaServTuriHabilitados = emple_1.getListaServiciosHabilitados(listaServTuri);
        
        /*      PRUEBAS     */
        
        for (ServicioTuristico serv : listaServTuriHabilitados) {
            assertTrue(serv.isHabilitado());        //Todos los servicios tienen que estas habilitados (en "true")
        }
        
    }
    
    @Test
    public void testEliminarLogicoServicioTuristico() {
        
        /*      CREANDO ESCENARIO       */
        
        ServicioTuristico servTuri = new ServicioTuristico(id_1, nombre_serv_1, descripcion_serv_1, destino_serv_1, costo_1, habilitado_1, fecha_1, /*List<PaqueteTuristico> listaPaquetes*/ null, /*List<Venta> listaVentas*/ null);
        
        /*      EJECUCIÓN       */
        
        servTuri = emple_1.eliminarLogicoServicioTuristico(servTuri);
        
        /*      PRUEBAS     */
        
        assertFalse(servTuri.isHabilitado());       //Todos los servicios tienen que estas habilitados (en "true")
        
    }
    
    @Test
    public void testEditarServicioTuristico() {
        
        /*      CREANDO ESCENARIO       */
        
        ServicioTuristico servTuri = new ServicioTuristico(id_1, nombre_serv_1, descripcion_serv_1, destino_serv_1, costo_1, habilitado_1, fecha_1, /*List<PaqueteTuristico> listaPaquetes*/ null, /*List<Venta> listaVentas*/ null);
        
        /*      EJECUCIÓN       */
        
        servTuri = emple_1.editarServicioTuristico(id_2, nombre_serv_2, descripcion_serv_2, destino_serv_2, costo_2, fecha_2);
        
        /*      PRUEBAS     */
        
        assertEquals(id_2, servTuri.getCodigo_servicio());
        assertEquals(nombre_serv_2, servTuri.getNombre());
        assertEquals(descripcion_serv_2, servTuri.getDescripcion_breve());
        assertEquals(destino_serv_2, servTuri.getDestino_servicio());
        assertThat(servTuri.getCosto_servicio(), is(costo_2));
        assertEquals(fecha_2, servTuri.getFecha_servicio());
        
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON PaqueteTuristico">
    
    @Test
    public void testCrearPaqueteTuristico() {
        
        /*      CREANDO ESCENARIO       */
        
        double costo = 0;
        List<ServicioTuristico> listaServTuri = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2)); //El 3er ServicioTuristico esta deshabilitado
        PaqueteTuristico paqTuri = new PaqueteTuristico ();
        
        costo = (servTuri_1.getCosto_servicio() + servTuri_2.getCosto_servicio()) * descuento_paq_tur;      //  Calculo el costo del paquete "a mano"
        
        /*      EJECUCIÓN       */
        
        paqTuri = emple_1.crearPaqueteTuristico(nombre_paq_1, descuento_paq_tur, listaServTuri);
        
        /*      PRUEBAS     */
        
        assertEquals(nombre_paq_1, paqTuri.getNombre());
        assertThat(paqTuri.getCosto_paquete(), is(costo));
        assertTrue(paqTuri.getLista_servicios().contains(servTuri_1));
        assertTrue(paqTuri.getLista_servicios().contains(servTuri_2));
        
    }
    
    @Test
    public void testGetListaPaquetesHabilitados() {
        
        /*      CREANDO ESCENARIO       */
        
        List<ServicioTuristico> listaServTuri_1 = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2));
        List<ServicioTuristico> listaServTuri_2 = new ArrayList<> (Arrays.asList(servTuri_2, servTuri_3));
        
        PaqueteTuristico paqTuri_1, paqTuri_2 = new PaqueteTuristico ();
        paqTuri_1 = emple_1.crearPaqueteTuristico(nombre_paq_1, descuento_paq_tur, listaServTuri_1);
        paqTuri_2 = emple_1.crearPaqueteTuristico(nombre_paq_2, descuento_paq_tur, listaServTuri_2);
        paqTuri_2.setHabilitado(false);     //  Deshabilito el 2do paquete turistico
        
        List<PaqueteTuristico> listaPaqTuri = new ArrayList<> (Arrays.asList(paqTuri_1, paqTuri_2)); //El 3er ServicioTuristico esta deshabilitado
        List<PaqueteTuristico> listaPaqTuriHabilitados = new ArrayList<> ();
        
        /*      EJECUCIÓN       */
        
        listaPaqTuriHabilitados = emple_1.getListaPaquetesHabilitados(listaPaqTuri);
        
        /*      PRUEBAS     */
        
        for (PaqueteTuristico paq : listaPaqTuriHabilitados) {
            assertTrue(paq.isHabilitado());     //  Todos los servicios tienen que estas habilitados (en "true")
        }
        
        assertFalse(listaPaqTuriHabilitados.contains(paqTuri_2));
        
    }
    
    @Test
    public void testEliminarLogicoPaqueteTuristico() {
        
        /*      CREANDO ESCENARIO       */
        List<ServicioTuristico> listaServTuri = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2));
        PaqueteTuristico paqTuri = emple_1.crearPaqueteTuristico(nombre_paq_1, descuento_paq_tur, listaServTuri);
                
        /*      EJECUCIÓN       */
        
        paqTuri = emple_1.eliminarLogicoPaqueteTuristico(paqTuri);
        
        /*      PRUEBAS     */
        
        assertEquals(false, paqTuri.isHabilitado());       
        
    }
    
    @Test
    public void testEditarPaqueteTuristico() {
        
        /*      CREANDO ESCENARIO       */
        
        List<ServicioTuristico> listaServTuri_1 = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2));
        List<ServicioTuristico> listaServTuri_2 = new ArrayList<> (Arrays.asList(servTuri_2, servTuri_3));
        PaqueteTuristico paqTuri = emple_1.crearPaqueteTuristico(nombre_paq_1, descuento_paq_tur, listaServTuri_1);
        
        double costo = ( servTuri_2.getCosto_servicio() + servTuri_3.getCosto_servicio() ) * descuento_paq_tur;
        
        /*      EJECUCIÓN       */
        
        paqTuri = emple_1.editarPaqueteTuristico(id_2, nombre_paq_2, listaServTuri_2, descuento_paq_tur);
        
        /*      PRUEBAS     */
        
        assertEquals(id_2, paqTuri.getCodigo_paquete());
        assertEquals(nombre_paq_2, paqTuri.getNombre());
        assertThat(paqTuri.getCosto_paquete(), is(costo));
        assertTrue(paqTuri.getLista_servicios().contains(servTuri_2));
        assertTrue(paqTuri.getLista_servicios().contains(servTuri_3));
        assertFalse(paqTuri.getLista_servicios().contains(servTuri_1));
        
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON Persona">
    
    @Test
    public void testCrearPersona() {
        
        /*      CREANDO ESCENARIO       */
        
        Persona perso = new Persona ();
        
        /*      EJECUCIÓN       */
        
        perso = emple_1.crearPersona(nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1, fecha_1, nacionalidad_perso_1, celu_perso_1, mail_perso_1);
                
        /*      PRUEBAS     */
        
        assertFalse(perso == null);
        assertEquals(nombre_perso_1, perso.getNombre());
        assertEquals(apellido_perso_1, perso.getApellido());
        assertEquals(direccion_perso_1, perso.getDireccion());
        assertThat(perso.getDni(), is(dni_perso_1));
        assertEquals(fecha_1, perso.getFecha_nacimiento());
        assertEquals(nacionalidad_perso_1, perso.getNacionalidad());
        assertEquals(celu_perso_1, perso.getCelular());
        assertEquals(mail_perso_1, perso.getEmail());
        
    }
    
    @Test
    public void testEditarPersona() {
        
        /*      CREANDO ESCENARIO       */
        
        Persona perso = new Persona ();
        perso = emple_1.crearPersona(nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1, fecha_1, nacionalidad_perso_1, celu_perso_1, mail_perso_1);
        
        /*      EJECUCIÓN       */
        
        perso = emple_1.editarPersona(id_1, nombre_perso_2, apellido_perso_2, dni_perso_2, direccion_perso_2, fecha_2, nacionalidad_perso_2, celu_perso_2, mail_perso_2);
        
        /*      PRUEBAS     */
        
        assertFalse(perso == null);
        assertEquals(id_1, perso.getId_Persona());
        assertEquals(nombre_perso_2, perso.getNombre());
        assertEquals(apellido_perso_2, perso.getApellido());
        assertEquals(direccion_perso_2, perso.getDireccion());
        assertThat(perso.getDni(), is(dni_perso_2));
        assertEquals(fecha_2, perso.getFecha_nacimiento());
        assertEquals(nacionalidad_perso_2, perso.getNacionalidad());
        assertEquals(celu_perso_2, perso.getCelular());
        assertEquals(mail_perso_2, perso.getEmail());
        
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //    
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON Cliente">
    
    @Test
    public void testCrearCliente() {
        
        /*      CREANDO ESCENARIO       */
        
        Persona perso = new Persona ();
        perso = emple_1.crearPersona(nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1, fecha_1, nacionalidad_perso_1, celu_perso_1, mail_perso_1);
        Cliente clien = new Cliente ();
        
        /*      EJECUCIÓN       */
        
        clien = emple_1.crearCliente(perso);
                
        /*      PRUEBAS     */
        
        assertFalse(clien == null);
        assertEquals(nombre_perso_1, clien.getPersona().getNombre());
        assertEquals(apellido_perso_1, clien.getPersona().getApellido());
        assertEquals(direccion_perso_1, clien.getPersona().getDireccion());
        assertThat(clien.getPersona().getDni(), is(dni_perso_1));
        assertEquals(fecha_1, clien.getPersona().getFecha_nacimiento());
        assertEquals(nacionalidad_perso_1, clien.getPersona().getNacionalidad());
        assertEquals(celu_perso_1, clien.getPersona().getCelular());
        assertEquals(mail_perso_1, clien.getPersona().getEmail());
        assertTrue(clien.isHabilitado());
        
    }
    
    @Test
    public void testEliminarLogicoCliente() {
        
        /*      CREANDO ESCENARIO       */
        
        Persona perso = new Persona ();
        perso = emple_1.crearPersona(nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1, fecha_1, nacionalidad_perso_1, celu_perso_1, mail_perso_1);
        Cliente clien = new Cliente ();
        
        /*      EJECUCIÓN       */
        
        clien = emple_1.eliminarLogicoCliente(clien);
                
        /*      PRUEBAS     */
        
        assertFalse(clien == null);
        assertFalse(clien.isHabilitado());
    }
    
    @Test
    public void testEditarCliente() {
        
        /*      CREANDO ESCENARIO       */
        
        Persona perso = new Persona ();
        perso = emple_1.crearPersona(nombre_perso_1, apellido_perso_1, direccion_perso_1, dni_perso_1, fecha_1, nacionalidad_perso_1, celu_perso_1, mail_perso_1);
        Cliente clien = new Cliente ();
        
        /*      EJECUCIÓN       */
        
        clien = emple_1.editarCliente(id_1, perso_2);          
        
        /*      PRUEBAS     */
        
        assertFalse(clien == null);
        assertEquals(nombre_perso_2, clien.getPersona().getNombre());
        assertEquals(apellido_perso_2, clien.getPersona().getApellido());
        assertEquals(direccion_perso_2, clien.getPersona().getDireccion());
        assertThat(clien.getPersona().getDni(), is(dni_perso_2));
        assertEquals(fecha_2, clien.getPersona().getFecha_nacimiento());
        assertEquals(nacionalidad_perso_2, clien.getPersona().getNacionalidad());
        assertEquals(celu_perso_2, clien.getPersona().getCelular());
        assertEquals(mail_perso_2, clien.getPersona().getEmail());
        assertTrue(clien.isHabilitado());
        
    }
    
    @Test
    public void testGetListaClientesHabilitados() {
        
        /*      CREANDO ESCENARIO       */
        
        List<Cliente> listaClientes = new ArrayList<> (Arrays.asList(clien_1, clien_2, clien_3));    // El 3er cliente no esta habilitado
        List<Cliente> listaClientesHabilitados = new ArrayList<> ();
        /*      EJECUCIÓN       */
        
        listaClientesHabilitados = emple_1.getListaClientesHabilitados(listaClientes);
        
        /*      PRUEBAS     */
        
        for (Cliente clien : listaClientesHabilitados) {
            assertTrue(clien.isHabilitado());     //  Todos los clientes tienen que estas habilitados (en "true")
        }
        
        assertFalse(listaClientesHabilitados == null);
        assertFalse(listaClientesHabilitados.contains(clien_3));
        
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //    
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON Usuarios">
    
    @Test
    public void testCrearUsuario() {
        
        /*      CREANDO ESCENARIO       */
        
        Usuario usu = new Usuario ();
        
        /*      EJECUCIÓN       */
        
        usu = emple_1.crearUsuario(usuario_1, contrasenia_1);
                
        /*      PRUEBAS     */
        
        assertFalse(usu == null);
        assertEquals(usuario_1, usu.getUsuario());
        assertEquals(contrasenia_1, usu.getContrasenia());
        
    }
    
    @Test
    public void testEditarUsuario() {
        
        /*      CREANDO ESCENARIO       */
        
        Usuario usu = new Usuario ();
        usu = emple_1.crearUsuario(usuario_1, contrasenia_1);
        
        /*      EJECUCIÓN       */
        
        usu = emple_1.editarUsuario(id_1, usuario_2, contrasenia_2);
        
        /*      PRUEBAS     */
        
        assertFalse(usu == null);
        assertEquals(id_1, usu.getId_usuario());
        assertEquals(usuario_2, usu.getUsuario());
        assertEquals(contrasenia_2, usu.getContrasenia());
        
    }
    
    //</editor-fold>
    
    
    // ----------------------------------------------------- //  
    
    
    //<editor-fold defaultstate="collapsed" desc="TEST RELACIONADOS CON Venta">
    
    @Test
    public void testCrearVenta() {
        
        /*      CREANDO ESCENARIO       */
        
        Venta venta = new Venta ();
        
        /*      EJECUCIÓN       */
        
        venta = emple_1.crearVenta(fecha_1, cantidad_1, medio_pago_1);
                
        /*      PRUEBAS     */
        
        assertFalse(venta == null);
        assertEquals(fecha_1, venta.getFecha_venta());
        assertEquals(cantidad_1, venta.getCantidad());
        assertThat(venta.getMedio_pago(), is(medio_pago_1));
        
    }
    
    @Test
    public void testGetClienteFromVenta() {
        
        /*      CREANDO ESCENARIO       */
        
        //  Creo listas de ventas para asignarselas a cada cliente
        List<Venta> listaVentas_1 = new ArrayList<> (Arrays.asList(venta_1));
        List<Venta> listaVentas_2 = new ArrayList<> ();
        List<Venta> listaVentas_3 = new ArrayList<> (Arrays.asList(venta_2, venta_3));
        
        clien_1.setListaVentas(listaVentas_1);
        clien_2.setListaVentas(listaVentas_2);
        clien_3.setListaVentas(listaVentas_3);
        
        //Creo lista de clientes
        List<Cliente> listaClientes_1 = new ArrayList<> (Arrays.asList(clien_1, clien_2, clien_3)); 
        List<Cliente> listaClientes_2 = new ArrayList<> (Arrays.asList(clien_1, clien_2)); 
        
        Cliente clien = new Cliente ();
        
        /*      EJECUCIÓN       */
        
        clien = emple_1.getClienteFromVenta(venta_3, listaClientes_1);
                
        /*      PRUEBAS     */
        
        assertFalse(clien == null);
        assertTrue(clien.getListaVentas().contains(venta_3));
        
        
        /*      EJECUCIÓN       */
        
        clien = emple_1.getClienteFromVenta(venta_3, listaClientes_2);
                
        /*      PRUEBAS     */
        
        assertTrue(clien == null);
        
        /*      Reset de variables globales utilizadas      */
        
        clien_1.setListaVentas(null);
        clien_2.setListaVentas(null);
        clien_3.setListaVentas(null);
        
    }
    
    @Test
    public void testGetEmpleadoFromVenta() {
        
        /*      CREANDO ESCENARIO       */
        
        //  Creo listas de ventas para asignarselas a cada empleado
        List<Venta> listaVentas_1 = new ArrayList<> (Arrays.asList(venta_1));
        List<Venta> listaVentas_2 = new ArrayList<> (Arrays.asList(venta_2, venta_3));
        
        emple_1.setListaVentas(listaVentas_1);
        emple_2.setListaVentas(listaVentas_2);
        
        //Creo lista de clientes
        List<Empleado> listaEmpleados_1 = new ArrayList<> (Arrays.asList(emple_1, emple_2)); 
        List<Empleado> listaEmpleados_2 = new ArrayList<> (Arrays.asList(emple_1)); 
        
        Empleado emple = new Empleado ();
        
        /*      EJECUCIÓN       */
        
        emple = emple_1.getEmpleadoFromVenta(venta_3, listaEmpleados_1);
                
        /*      PRUEBAS     */
        
        assertFalse(emple == null);
        assertTrue(emple.getListaVentas().contains(venta_3));
        
        
        /*      EJECUCIÓN       */
        
        emple = emple_1.getEmpleadoFromVenta(venta_3, listaEmpleados_2);
                
        /*      PRUEBAS     */
        
        assertTrue(emple == null);
        
        /*      Reset de variables globales utilizadas      */
        
        emple_1.setListaVentas(null);
        emple_2.setListaVentas(null);
        
    }
    
    @Test
    public void testGetServicioFromVenta() {
        
        /*      CREANDO ESCENARIO       */
        
        //  Creo listas de ventas para asignarselas a cada servicio
        List<Venta> listaVentas_1 = new ArrayList<> (Arrays.asList(venta_1));
        List<Venta> listaVentas_2 = new ArrayList<> (Arrays.asList(venta_2, venta_3));
        
        servTuri_1.setListaVentas(listaVentas_1);
        servTuri_2.setListaVentas(listaVentas_2);
        servTuri_3.setListaVentas(null);
        
        //  Creo lista de servicios
        List<ServicioTuristico> listaServiciosTuristicos_1 = new ArrayList<> (Arrays.asList(servTuri_1)); 
        List<ServicioTuristico> listaServiciosTuristicos_2 = new ArrayList<> (Arrays.asList(servTuri_2, servTuri_3)); 
        
        ServicioTuristico servTuri = new ServicioTuristico ();
        
        /*      EJECUCIÓN       */
        
        servTuri = emple_1.getServicioFromVenta(venta_3, listaServiciosTuristicos_2);
                
        /*      PRUEBAS     */
        
        assertFalse(servTuri == null);
        assertTrue(servTuri.getListaVentas().contains(venta_3));
        
        
        /*      EJECUCIÓN       */
        
        servTuri = emple_1.getServicioFromVenta(venta_3, listaServiciosTuristicos_1);
                
        /*      PRUEBAS     */
        
        assertTrue(servTuri == null);
        
    }
    
    @Test
    public void testGetPaqueteFromVenta() {
        
        /*      CREANDO ESCENARIO       */
        
        //  Creo listas de servicios y se las asigno a paquetes
        List<ServicioTuristico> listaServTuri_1 = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2));
        List<ServicioTuristico> listaServTuri_2 = new ArrayList<> (Arrays.asList(servTuri_2, servTuri_3));
        List<ServicioTuristico> listaServTuri_3 = new ArrayList<> (Arrays.asList(servTuri_1, servTuri_2, servTuri_3));
        
        PaqueteTuristico paqTuri_1, paqTuri_2, paqTuri_3 = new PaqueteTuristico ();
        paqTuri_1 = emple_1.crearPaqueteTuristico(nombre_paq_1, descuento_paq_tur, listaServTuri_1);
        paqTuri_2 = emple_1.crearPaqueteTuristico(nombre_paq_2, descuento_paq_tur, listaServTuri_2);
        paqTuri_3 = emple_1.crearPaqueteTuristico(nombre_paq_3, descuento_paq_tur, listaServTuri_3);
        
        //  Creo listas de ventas para asignarselas a cada paquete
        List<Venta> listaVentas_1 = new ArrayList<> (Arrays.asList(venta_1));
        List<Venta> listaVentas_2 = new ArrayList<> (Arrays.asList(venta_2, venta_3));
        
        paqTuri_1.setListaVentas(listaVentas_1);
        paqTuri_2.setListaVentas(listaVentas_2);
        paqTuri_3.setListaVentas(null);
        
        //  Creo lista de paquetes
        List<PaqueteTuristico> listaPaquetesTuristicos_1 = new ArrayList<PaqueteTuristico> (Arrays.asList(paqTuri_1)); 
        List<PaqueteTuristico> listaPaquetesTuristicos_2 = new ArrayList<PaqueteTuristico> (Arrays.asList(paqTuri_2, paqTuri_3)); 
        
        PaqueteTuristico paqTuri = new PaqueteTuristico ();
        
        /*      EJECUCIÓN       */
        
        paqTuri = emple_1.getPaqueteFromVenta(venta_3, listaPaquetesTuristicos_2);
                
        /*      PRUEBAS     */
        
        assertFalse(paqTuri == null);
        assertTrue(paqTuri.getListaVentas().contains(venta_3));
        
        
        /*      EJECUCIÓN       */
        
        paqTuri = emple_1.getPaqueteFromVenta(venta_3, listaPaquetesTuristicos_1);
                
        /*      PRUEBAS     */
        
        assertTrue(paqTuri == null);
        
    }
    
    //</editor-fold>
    
    
    
}
