package Servlets;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Empleado;
import Logica.PaqueteTuristico;
import Logica.Persona;
import Logica.ServicioTuristico;
import Logica.Venta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletVentas", urlPatterns = {"/ServletVentas"})
public class ServletVentas extends HttpServlet {

// ------------ VARIABLES GLOBALES
    
    Controladora control = new Controladora ();
    Cliente clien = new Cliente ();
    Venta venta = new Venta ();
    Persona perso = new Persona ();
    Empleado emple = new Empleado ();
    ServicioTuristico servTuri = new ServicioTuristico ();
    PaqueteTuristico paqueTuri = new PaqueteTuristico ();
    
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        // --------- GUARDO LOS DATOS EN EL FORMULARIO
        
        if(request.getParameter("mostrar") != null){
            if(request.getParameter("mostrar").equals("ok")){
                
                venta = control.getVentaById(request.getParameter("id"));
                
                clien = control.getClienteFromVenta(venta);
                servTuri = control.getServicioFromVenta(venta);
                paqueTuri = control.getPaqueteFromVenta(venta);
                
                request.getSession().setAttribute("venta_Id_Venta", request.getParameter("id"));
                request.getSession().setAttribute("venta_Id_Cliente", clien.getId_cliente());
                request.getSession().setAttribute("venta_Nombre_Cliente", clien.getPersona().getNombre());
                request.getSession().setAttribute("venta_Apellido_Cliente", clien.getPersona().getApellido());
                request.getSession().setAttribute("venta_Direccion_Cliente", clien.getPersona().getDireccion());
                request.getSession().setAttribute("venta_Dni_Cliente", clien.getPersona().getDni());
                request.getSession().setAttribute("venta_Cantidad", venta.getCantidad());
                request.getSession().setAttribute("venta_Medio_Pago", venta.getMedio_pago());
                
                if (servTuri != null) {
                    request.getSession().setAttribute("venta_Id_Servicio", servTuri.getCodigo_servicio());
                }
                if (paqueTuri != null) {
                    request.getSession().setAttribute("venta_Id_Paquete", paqueTuri.getCodigo_paquete());
                }
                
                request.getSession().setAttribute("boton", "Mostrar");
                
                response.sendRedirect("factura.jsp");
            }
        }
        
        // -------------- ELIMINO EL REGISTRO
        
        if(request.getParameter("eliminar") != null){
            if(request.getParameter("eliminar").equals("ok")){
                
                control.eliminarVenta(request.getParameter("id"));
                response.sendRedirect("ventas.jsp");
            }
        }
        
        // --------------- HABILITO EL BOTON PARA EDITAR
        
        if(request.getParameter("editar") != null){
            if(request.getParameter("editar").equals("ok")){
                
                request.getSession().setAttribute("boton", "Editar");
            }
        }
        
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        // ---------- CARGO LOS DATOS DEL REGISTRO
        
        if (request.getParameter("boton_Form_Factura_Selecionar") != null){
            if (request.getParameter("boton_Form_Factura_Selecionar").equals("Seleccionar")){

                String Id_Cliente = request.getParameter("inputIdCliente");

                clien = control.getClienteById(Id_Cliente);

                request.getSession().setAttribute("venta_Id_Cliente", Id_Cliente);
                request.getSession().setAttribute("venta_Nombre_Cliente", clien.getPersona().getNombre());
                request.getSession().setAttribute("venta_Apellido_Cliente", clien.getPersona().getApellido());
                request.getSession().setAttribute("venta_Direccion_Cliente", clien.getPersona().getDireccion());
                request.getSession().setAttribute("venta_Dni_Cliente", clien.getPersona().getDni());

                response.sendRedirect("factura.jsp");
            }
        }
        
        // ------------- CREO Y EDITO EL REGISTRO
        
        if (request.getParameter("boton_Form_Factura") != null){
            if (request.getParameter("boton_Form_Factura").equals("Vender")){
                
                String IdEmpleado = request.getSession().getAttribute("idEmpleado").toString();
                String ServPaq = request.getParameter("ServicioPaquete");
                String Cantidad = request.getParameter("inputCantidad");
                String MedioPago = request.getParameter("medioPago");
                String IdCliente = request.getSession().getAttribute("venta_Id_Cliente").toString();
                
                control.crearVenta(IdEmpleado, IdCliente, ServPaq, Cantidad, MedioPago);
                
                response.sendRedirect("ventas.jsp");
            }
            
            if (request.getParameter("boton_Form_Factura").equals("Editar")){

                String IdVenta = request.getSession().getAttribute("venta_Id_Venta").toString();
                String ServPaq = request.getParameter("ServicioPaquete");
                String Cantidad = request.getParameter("inputCantidad");
                String MedioPago = request.getParameter("medioPago");
                String IdCliente = request.getSession().getAttribute("venta_Id_Cliente").toString();
                
                control.editarVenta(IdVenta, IdCliente, ServPaq, Cantidad, MedioPago);
                
                response.sendRedirect("ventas.jsp");
            }
            
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
