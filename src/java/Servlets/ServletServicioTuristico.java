package Servlets;

import Logica.Controladora;
import Logica.ServicioTuristico;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletServicioTuristico", urlPatterns = {"/ServletServicioTuristico"})
public class ServletServicioTuristico extends HttpServlet {

    // ---------- VARIABLES GLOBALES
    
    Controladora control = new Controladora();
    ServicioTuristico serv_Turi = new ServicioTuristico ();
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // ------------- CARGO LOS DATOS EN EL FORMULARIO
        
        if(request.getParameter("mostrar") != null){
            if(request.getParameter("mostrar").equals("ok")){

                serv_Turi = control.getServicioTuristicoById(request.getParameter("id"));

                request.getSession().setAttribute("servicio_Id", request.getParameter("id"));
                request.getSession().setAttribute("servicio_Nombre", serv_Turi.getNombre());
                request.getSession().setAttribute("servicio_Descripcion", serv_Turi.getDescripcion_breve());
                request.getSession().setAttribute("servicio_Destino", serv_Turi.getDestino_servicio());
                request.getSession().setAttribute("servicio_Costo", serv_Turi.getCosto_servicio());
                String fecha = control.deDateToString(serv_Turi.getFecha_servicio());
                request.getSession().setAttribute("servicio_Fecha", fecha);
                request.getSession().setAttribute("boton", "Mostrar");
                
                response.sendRedirect("servicio.jsp");
            }
        }
        
        // -------------- ELIMINO EL REGISTRO
        
        if(request.getParameter("eliminar") != null){
            if(request.getParameter("eliminar").equals("ok")){
                
                control.eliminarLogicoServicioTuristico(request.getParameter("id"));
                response.sendRedirect("servicios.jsp");
            }
        }
        
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
        
        if (request.getParameter("boton_Form_Servicio").equals("Guardar")){
            
            String Nombre = request.getParameter("inputNombre");
            String Descripcion = request.getParameter("inputDescripcion");
            String Destino = request.getParameter("inputDestino");
            String Costo = request.getParameter("inputCosto");
            String Fecha = request.getParameter("inputFecha");

            control.crearServicioTuristico(Nombre, Descripcion, Destino, Costo, Fecha);        

            response.sendRedirect("servicios.jsp");
        }
        
        // -------------- EDITO EL REGISTRO
        
        if (request.getParameter("boton_Form_Servicio").equals("Editar")){
            
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