package Servlets;

import Logica.Controladora;
import Logica.Empleado;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ServletLogin", urlPatterns = {"/ServletLogin"})
public class ServletLogin extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id_sesion_Activa = 0;
        Controladora control = new Controladora ();
        
        id_sesion_Activa = control.login(request.getParameter("input_Usuario"), request.getParameter("input_Contrasenia"));
        
        if (id_sesion_Activa != 0) {
            
            HttpSession miSesion = request.getSession(true);
            miSesion.setAttribute("usuario", request.getParameter("input_Usuario"));
            miSesion.setAttribute("control", control);
            miSesion.setAttribute("idUsuario", id_sesion_Activa);
            
            Empleado empleado = new Empleado ();
            List<Empleado> listaEmpleados = control.getListaEmpleadosHabilitados();
            for (Empleado emple : listaEmpleados) {
                if (emple.getUsuario().getId_usuario() == id_sesion_Activa){
                    miSesion.setAttribute("nombreUsuario", emple.getPersona().getNombre());
                    miSesion.setAttribute("idEmpleado", emple.getId_empleado());
                    break;
                }
            }
            
                        
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("LoginError.jsp");
        }
                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
