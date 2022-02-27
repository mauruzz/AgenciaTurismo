package Servlets;

import Logica.Controladora;
import Logica.MedioPago;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletMedioPago", urlPatterns = {"/ServletMedioPago"})
public class ServletMedioPago extends HttpServlet {
    
    // ---------- VARIABLES GLOBALES
    
    Controladora control = new Controladora();
    MedioPago medio_pago = new MedioPago ();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // ------------- CARGO LOS DATOS EN EL FORMULARIO
        
        if(request.getParameter("mostrar") != null){
            if(request.getParameter("mostrar").equals("ok")){

                medio_pago = control.getMedioPagoById(Integer.parseInt(request.getParameter("id")));

                request.getSession().setAttribute("medio_pago_Id", request.getParameter("id"));
                request.getSession().setAttribute("medio_pago_Habilitado", medio_pago.isHabilitado());
                request.getSession().setAttribute("medio_pago_Nombre", medio_pago.getNombre());
                request.getSession().setAttribute("medio_pago_Descuento", medio_pago.getDescuento());
                request.getSession().setAttribute("boton", "Mostrar");
                
                response.sendRedirect("mediosPago.jsp");
            }
        }
        
        // -------------- ELIMINO EL REGISTRO
        /*
        
        REVISAR COMO HACER EL ELIMINADO LOGICO O TOTAL DEL MEDIO DE PAGO
        
        if(request.getParameter("eliminar") != null){
            if(request.getParameter("eliminar").equals("ok")){
                
                control.eliminarLogicoServicioTuristico(request.getParameter("id"));
                response.sendRedirect("servicios.jsp");
            }
        }
        */
        // ------------- HABILITO EL BOTON PARA EDITAR
        
        if(request.getParameter("editar") != null){
            if(request.getParameter("editar").equals("ok")){
                
                request.getSession().setAttribute("boton", "Editar");
            }
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         // -------------- GUARDO LOS DATOS DEL REGISTRO
        
        if (request.getParameter("boton_Form_Medio_Pago").equals("Guardar")){
            
            String Nombre = request.getParameter("inputNombre");
            String Descripcion = request.getParameter("inputDescripcion");
            String Destino = request.getParameter("inputDestino");
            String Costo = request.getParameter("inputCosto");
            String Fecha = request.getParameter("inputFecha");

            control.crearServicioTuristico(Nombre, Descripcion, Destino, Costo, Fecha);        

            response.sendRedirect("servicios.jsp");
        }
        
        // -------------- EDITO EL REGISTRO
        
        if (request.getParameter("boton_Form_Medio_Pago").equals("Editar")){
            
            String Id = (String) request.getSession().getAttribute("servicio_Id");
            String Nombre = request.getParameter("inputNombre");
            String Descripcion = request.getParameter("inputDescripcion");
            String Destino = request.getParameter("inputDestino");
            String Costo = request.getParameter("inputCosto");
            String Fecha = request.getParameter("inputFecha");

            control.editarServicioTuristico(Id, Nombre, Descripcion, Destino, Costo, Fecha);        

            response.sendRedirect("servicios.jsp");
        }
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
