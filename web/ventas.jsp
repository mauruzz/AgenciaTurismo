<%@page import="Logica.MedioPago"%>
<%@page import="Logica.PaqueteTuristico"%>
<%@page import="Logica.ServicioTuristico"%>
<%@page import="Logica.Empleado"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.Venta"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">  
        <title>ARGENTOUR - Ventas</title>
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
        

            // ---------    LIMPIO TODAS VARIABLES DE SESSION DESCARTABLES
            
            session.removeAttribute("venta_Id_Venta");
            session.removeAttribute("venta_Id_Cliente");
            session.removeAttribute("venta_Nombre_Cliente");
            session.removeAttribute("venta_Apellido_Cliente");
            session.removeAttribute("venta_Direccion_Cliente");
            session.removeAttribute("venta_Dni_Cliente");
            session.removeAttribute("venta_Cantidad");
            session.removeAttribute("venta_Medio_Pago");
            session.removeAttribute("venta_Id_Servicio");
            session.removeAttribute("venta_Id_Paquete");
            session.removeAttribute("boton"); 

            //--------- DECLARO VARIABLES AUXILIARES
            
            Cliente clien = new Cliente ();
            Empleado emple = new Empleado (); 
            ServicioTuristico servTuri = new ServicioTuristico ();
            PaqueteTuristico paqTuri = new PaqueteTuristico ();
            MedioPago medioPago = new MedioPago ();
            String producto = "";
            double costo = 0;

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
                            <li><a href="mediosPago.jsp"><img class="iconos-menu" src="iconos/billetera.png">Medios de pago</a></li>
                            <li><a href="#" class="active"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
                            <li><a href="ServletLogout"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
                        </ul>  
                    </nav>
                </div>
                
                <!-- Main content --> 
                
                <div class="templatemo-content col-1 light-gray-bg">
                    <div class="div-usuario">Bienvenido&nbsp;<%=session.getAttribute("nombreUsuario")%></div>
                    <div class="templatemo-content-container">
                        <div class="templatemo-content-widget white-bg" id="titulo-cliente">
                            <h2 class="margin-bottom-0">Ventas<a href="factura.jsp"><img class="agregar" src="iconos/agregar.png" alt="Vender" title="Vender"></a>
                                <a href="ventas.jsp"><img class="buscar-lupa" src="iconos/lupa.png" alt="Buscar factura" title="Buscar cliente"></a><div class="buscar-input"><input type="text" class="form-control" placeholder="Buscar" name="buscar_factura" id="buscar_factura"></div>
                            </h2>       
                        </div>  
                        <div class="templatemo-content-widget no-padding">
                            <div class="panel panel-default table-responsive">
                                <table class="table table-striped table-bordered templatemo-user-table">
                                    <thead>
                                        <tr>
                                            <td><a href="" class="white-text templatemo-sort-by">N° de factura<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Fecha<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Nombre de cliente<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Servicio/Paquete<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Vendedor<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Medio de pago<span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Costo<span class="caret"></span></a></td>
                                            <td>Acción</td>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <%
                                        List<Venta> listaVentas = control.getListaVentas();
                                        
                                        for (Venta venta : listaVentas) {
                                            clien = control.getClienteFromVenta(venta);
                                            emple = control.getEmpleadoFromVenta(venta);
                                            
                                            servTuri = control.getServicioFromVenta(venta);
                                            
                                            if (servTuri != null) {
                                                producto = servTuri.getNombre();
                                                costo = servTuri.getCosto_servicio();
                                            }

                                            paqTuri = control.getPaqueteFromVenta(venta);
                                            
                                            if (paqTuri != null) {
                                                producto = paqTuri.getNombre();
                                                costo = paqTuri.getCosto_paquete();
                                            }
                                        %> 
                                        <tr>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=venta.getNum_venta()%></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=control.deDateToString(venta.getFecha_venta())%></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=clien.getPersona().getNombre()%>&nbsp;<%=clien.getPersona().getApellido()%></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=producto%></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=emple.getPersona().getNombre()%>&nbsp;<%=emple.getPersona().getApellido()%></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=venta.getMedio_pago().getNombre() %></a></td>
                                            <td><a class="link-negro" href="ServletVentas?mostrar=ok&id=<%=venta.getNum_venta()%>"><%=(costo*venta.getCantidad())%></a></td>
                                            <td><a href="ServletVentas?mostrar=ok&editar=ok&id=<%=venta.getNum_venta()%>" class="templatemo-edit-btn">Editar</a><a href="ServletVentas?eliminar=ok&id=<%=venta.getNum_venta()%>" class="templatemo-eliminar-btn">Eliminar</a></td>
                                        </tr>  
                                        <%
                                        }
                                        %>  

                                    </tbody>
                                </table>    
                            </div>                          
                        </div>          
                        <footer class="text-right">
                            <p>Copyright &copy; 2084 Company Name 
                            | Design: Template Mo</p>
                        </footer>         
                    </div>
                </div>
            </div>

            <!-- JS -->
            <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
            <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
            <script>
                $(document).ready(function(){
                    // Content widget with background image
                    var imageUrl = $('img.content-bg-img').attr('src');
                    $('.templatemo-content-img-bg').css('background-image', 'url(' + imageUrl + ')');
                    $('img.content-bg-img').hide();        
                });
            </script>
        <%
        }
        %>
    </body>
</html>