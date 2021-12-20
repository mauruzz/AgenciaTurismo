package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletLogout", urlPatterns = {"/ServletLogout"})
public class ServletLogout extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        if (request.getSession().getAttribute("usuario") != null) {
            
            request.getSession().removeAttribute("usuario");
            request.getSession().removeAttribute("control");
            request.getSession().removeAttribute("idUsuario");
            request.getSession().removeAttribute("nombreUsuario");
            request.getSession().removeAttribute("idEmpleado");
            
            response.sendRedirect("login.jsp");
        }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
