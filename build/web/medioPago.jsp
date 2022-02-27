<%@page import="Logica.ServicioTuristico"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ARGENTOUR - Paquete</title>
        <meta name="description" content="">
        <meta name="author" content="templatemo">
        <!-- 
        Visual Admin Template
        https://templatemo.com/tm-455-visual-admin
        -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/templatemo-style.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
    <body>
        <% 
        // ------------ SEGURIDAD LOGIN
        
        HttpSession miSesion = request.getSession();
        String usuario = (String) request.getSession().getAttribute("usuario");

        if (usuario == null){
            response.sendRedirect("SinLogin.jsp");
        } else {
            Controladora control = new Controladora ();
            miSesion.setAttribute("control", control);
            
            

            %>
            
            <!-- Left column -->
            
            <div class="templatemo-flex-row">
                <div class="templatemo-sidebar">
                    <header class="templatemo-site-header">
                        <img src="iconos/icono.png">
                        <h1>ARGENTOUR</h1>
                    </header>
                    <div class="profile-photo-container">
                        <img src="images/foto-menu-lateral.jpg" alt="Profile Photo" class="img-responsive">
                        <div class="profile-photo-overlay"></div>
                    </div>
                    <div class="mobile-menu-icon">
                        <i class="fa fa-bars"></i>
                    </div>
                    <nav class="templatemo-left-nav">
                        <ul>
                            <li><a href="index.jsp"><img class="iconos-menu" src="iconos/home.png">Principal</a></li>
                            <li><a href="empleados.jsp"><img class="iconos-menu" src="iconos/vendedor.png">Empleados</a></li>
                            <li><a href="clientes.jsp"><img class="iconos-menu" src="iconos/clientes.png">Clientes</a></li>
                            <li><a href="servicios.jsp"><img class="iconos-menu" src="iconos/servicios.png">Servicios</a></li>
                            <li><a href="paquetes.jsp"><img class="iconos-menu" src="iconos/paquetes.png">Paquetes</a></li>
                            <li><a href="mediosPago.jsp" class="active"><img class="iconos-menu" src="iconos/billetera.png">Medios de pago</a></li>
                            <li><a href="ventas.jsp"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
                            <li><a href="ServletLogout"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
                        </ul>
                    </nav>
                </div>
                
                <!-- Main content -->
                
                <div class="templatemo-content col-1 light-gray-bg">
                    <div class="div-usuario">Bienvenido&nbsp;<%=session.getAttribute("nombreUsuario")%></div>
                    <div class="templatemo-content-container">
                        <div class="templatemo-content-widget white-bg">
                            <h2 class="margin-bottom-30">Medio de pago</h2>
                            <form action="ServletMedioPago" class="templatemo-login-form" method="POST" id="form-medio-pago">
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputId">Id</label>
                                        <input type="text" class="form-control margin-bottom-5" id="inputId" placeholder="Numero de identificación" title="Identificador generado de forma automática" <%if(session.getAttribute("medio_pago_Id") != null){%>value="<%= session.getAttribute("medio_pago_Id")%>"<%}%> disabled>
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">
                                        <label class="control-label templatemo-block">Habilitado</label> 
                                        <div class="templatemo-block margin-top-10">
                                            <input type="checkbox" name="checkHabilitado" id="checkHabilitado" value="1" <%if((session.getAttribute("medio_pago_Habilitado") != null) && (session.getAttribute("medio_pago_Habilitado").equals("1"))){%>checked<%}%>>
                                            <label for="checkHabilitado" class="font-weight-400"><span></span></label>
                                        </div>                 
                                    </div>    
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputNombre">Nombre</label>
                                        <input type="text" class="form-control margin-bottom-5" name="inputNombre" id="inputNombre" placeholder="Nombre descriptivo del medio de pago" <%if(session.getAttribute("medio_pago_Nombre") != null){%>value="<%= session.getAttribute("medio_pago_Nombre")%>"<%}%>>
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputDescuento">Descuento</label>
                                        <input type="text" class="form-control margin-bottom-5" name="inputDescuento" id="inputDescuento" placeholder="Descuento del medio de pago" <%if(session.getAttribute("medio_pago_Descuento") != null){%>value="<%= session.getAttribute("medio_pago_Descuento")%>"<%}%>>
                                    </div>
                                </div>
                                
                                
                                <div class="form-group text-right">
                                    <!--<table class="float-left text-left">
                                        <tr class="margin-bottom-10"><td><h3>TOTAL</h3></td></tr>
                                        <tr><td><h2>$100000</h2></td></tr>
                                    </table>-->

                                    <%
                                    if(session.getAttribute("boton") != null){

                                        if (session.getAttribute("boton").equals("Editar")) {
                                    %>

                                            <input type="submit" value="Editar" name="boton_Form_Medio_Pago" class="templatemo-blue-button">
                                            <button type="reset" class="templatemo-white-button">Limpiar</button>
                                    <%    
                                        }
                                    } else {
                                    %>

                                        <input type="submit" value="Guardar" name="boton_Form_Medio_Pago" class="templatemo-blue-button">
                                        <button type="reset" class="templatemo-white-button">Limpiar</button>
                                    <%
                                    }
                                    %>

                                </div>                           
                            </form>
                        </div>
                        <footer class="text-right">
                            <p>Copyright &copy; 2084 Company Name 
                            | Design: Template Mo</p>
                        </footer>
                    </div>
                </div>
            </div>

            <!-- JS -->
            <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>        <!-- jQuery -->
            <script type="text/javascript" src="js/bootstrap-filestyle.min.js"></script>  <!-- http://markusslima.github.io/bootstrap-filestyle/ -->
            <script type="text/javascript" src="js/templatemo-script.js"></script>        <!-- Templatemo Script -->
    
        <%
        }
        %>
    </body>
</html>
