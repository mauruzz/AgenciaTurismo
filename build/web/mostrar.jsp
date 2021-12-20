<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar</title>
    </head>
    <body>
        <% 
    // ------------ SEGURIDAD LOGIN
    HttpSession miSesion = request.getSession();
    if (miSesion.getAttribute("usuario") != null){
        Controladora control = new Controladora ();
        miSesion.setAttribute("control", control);
    } else {
        response.sendRedirect("SinLogin.jsp");
    }
    %>
        <h2>Datos ingresados</h2>
        
        <p>Nombre: <%= session.getAttribute("nombre") %></p>
        <p>Descripción: <%= session.getAttribute("descripcion") %></p>  
        <p>Destino: <%= session.getAttribute("destino") %></p>  
        
        <p>Costo: <%= session.getAttribute("costo") %></p>  
        
    </body>
</html>