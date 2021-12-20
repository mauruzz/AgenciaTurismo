package Servlets;

import Logica.Controladora;
import Logica.PaqueteTuristico;
import Logica.ServicioTuristico;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletPaqueteTuristico", urlPatterns = {"/ServletPaqueteTuristico"})
public class ServletPaqueteTuristico extends HttpServlet {

    // ------------- VARIABLES GLOBALES 
    
    Controladora control = new Controladora();
    ServicioTuristico serv_Turi = new ServicioTuristico ();
    PaqueteTuristico paq_Turi = new PaqueteTuristico ();
    int maxServicioXPaquete = 5;
    
    
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
                
                int[] ids = new int[maxServicioXPaquete];
                for (int i = 0; i < maxServicioXPaquete; i++) {
                    ids[i] = 0;
                }
                
                paq_Turi = control.getPaqueteTuristicoById(request.getParameter("id"));
                
                for (int i = 0; i < paq_Turi.getLista_servicios().size(); i++) {
                    ids[i] = paq_Turi.getLista_servicios().get(i).getCodigo_servicio();
                }                
                
                request.getSession().setAttribute("paquete_Id", request.getParameter("id"));
                request.getSession().setAttribute("paquete_Nombre", paq_Turi.getNombre());
                request.getSession().setAttribute("paquete_Lista_Servicios", ids);
                request.getSession().setAttribute("paquete_Costo", paq_Turi.getCosto_paquete());
                request.getSession().setAttribute("boton", "Mostrar");
                
                response.sendRedirect("paquete.jsp");
            }
        }
        
        // -------------- ELIMINO EL REGISTRO
        
        if(request.getParameter("eliminar") != null){
            if(request.getParameter("eliminar").equals("ok")){
                
                control.eliminarLogicoPaqueteTuristico(request.getParameter("id"));
                
                response.sendRedirect("paquetes.jsp");
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
        
        if (request.getParameter("boton_Form_Paquete").equals("Guardar")){
            String Nombre = request.getParameter("inputNombre");
            String Servicio_1 = request.getParameter("select_Serv_1");
            String Servicio_2 = request.getParameter("select_Serv_2");
            String Servicio_3 = request.getParameter("select_Serv_3");
            String Servicio_4 = request.getParameter("select_Serv_4");
            String Servicio_5 = request.getParameter("select_Serv_5");
            
            control.crearPaqueteTuristico(Nombre, Servicio_1, Servicio_2, Servicio_3, Servicio_4, Servicio_5);        

            response.sendRedirect("paquetes.jsp");
        }
        
        // -------------- EDITO EL REGISTRO
        
        if (request.getParameter("boton_Form_Paquete").equals("Editar")){
            
            String Id = (String) request.getSession().getAttribute("paquete_Id");
            String Nombre = request.getParameter("inputNombre");
            String Servicio_1 = request.getParameter("select_Serv_1");
            String Servicio_2 = request.getParameter("select_Serv_2");
            String Servicio_3 = request.getParameter("select_Serv_3");
            String Servicio_4 = request.getParameter("select_Serv_4");
            String Servicio_5 = request.getParameter("select_Serv_5");

            control.editarPaqueteTuristico(Id, Nombre, Servicio_1, Servicio_2, Servicio_3, Servicio_4, Servicio_5);        

            response.sendRedirect("paquetes.jsp");
            
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
