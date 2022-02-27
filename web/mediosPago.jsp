<%@page import="Logica.MedioPago"%>
<%@page import="Logica.PaqueteTuristico"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">  
        <title>ARGENTOUR - Paquetes</title>
        <meta name="description" content="">
        <meta name="author" content="templatemo">
        <!-- 
        Visual Admin Template
        https://templatemo.com/tm-455-visual-admin
        -->
        <link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,700' rel='stylesheet' type='text/css'>
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="jqvmap/jqvmap.css" media="screen" rel="stylesheet" type="text/css" /> 
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
            
            session.removeAttribute("paquete_Id");
            session.removeAttribute("paquete_Nombre");
            session.removeAttribute("paquete_Costo");
            session.removeAttribute("paquete_Lista_Servicios");
            session.removeAttribute("boton");
            %>
            
            <!-- Left column -->
            
            <div class="templatemo-flex-row">
                <div class="templatemo-sidebar">
                    <header class="templatemo-site-header">
                        <img src="iconos/icono.png">
                        <h1>Argentour</h1>
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
                            <li><a href="#" class="active"><img class="iconos-menu" src="iconos/billetera.png">Medios de pago</a></li>
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
                            <h2 class="margin-bottom-0">Medios de pago<a href="medioPago.jsp"><img class="agregar" src="iconos/agregar.png" alt="Agregar medio de pago" title="Agregar medio de pago"></a>
                                <a href="mediosPago.jsp"><img class="buscar-lupa" src="iconos/lupa.png" alt="Buscar medio de pago" title="Buscar medio de pago"></a><div class="buscar-input"><input type="text" class="form-control" placeholder="Buscar" name="buscar_medio_pago" id="buscar_medio_pago"></div>
                            </h2>   
                        </div>

                        <%
                        List<MedioPago> listaMediosPago = control.getListaMediosPagoHabilitados();
                        for (MedioPago medioPago : listaMediosPago) {
                        %>  
                        <div class="templatemo-flex-row flex-content-row">
                            <div class="col-1">              
                                <div class="panel panel-default margin-10">
                                    <!--    RENGLON CON TACHO DE BASURA PARA ELIMINAR EL MEDIO DE PAGO 
                                    
                                    <div class="panel-heading"><h2><a href="ServletMedioPago?mostrar=ok&id=<%/*=medioPago.getCodigo_medio_pago()*/%>" class="link-blanco"><%/*=medioPago.getNombre()*/%><span></span><%/*=medioPago.getDescuento()*/%></a><a href="ServletMedioPago?mostrar=ok&editar=ok&id=<%/*=medioPago.getCodigo_medio_pago()*/%>" class="buscar-lupa"><img src="iconos/lapiz-blanco.png"></a><a href="ServletMedioPago?eliminar=ok&id=<%/*=medioPago.getCodigo_medio_pago()*/%>" class="buscar-lupa"><img src="iconos/basura-blanco.png"></a></h2></div>
                                    
                                    -->
                                    <!--    RENGLON SIN TACHO DE BASURA     --> 
                                    <div class="panel-heading"><h2><a href="ServletMedioPago?mostrar=ok&id=<%=medioPago.getCodigo_medio_pago()%>" class="link-blanco"><%=medioPago.getNombre()%><span></span><%=medioPago.getDescuento()%></a><a href="ServletMedioPago?mostrar=ok&editar=ok&id=<%=medioPago.getCodigo_medio_pago()%>" class="buscar-lupa"><img src="iconos/lapiz-blanco.png"></a></h2></div>
                                </div>
                            </div>
                        </div>
                        <%
                        }
                        %>


                        <footer class="text-right">
                            <p>Copyright &copy; 2084 Company Name 
                            | Design: Template Mo</p>
                        </footer>         
                    </div>
                </div>
            </div>

            <!-- JS -->
            <script type="text/javascript" src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
            <script type="text/javascript" src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
            <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
            <script type="text/javascript" src="jqvmap/jquery.vmap.js"></script>
            <script type="text/javascript" src="jqvmap/maps/jquery.vmap.world.js"></script>
            <script type="text/javascript" src="jqvmap/data/jquery.vmap.sampledata.js"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.africa.js" type="text/javascript"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.asia.js" type="text/javascript"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.australia.js" type="text/javascript"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.europe.js" type="text/javascript"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.north-america.js" type="text/javascript"></script>
            <script src="jqvmap/maps/continents/jquery.vmap.south-america.js" type="text/javascript"></script>
            <script src="jqvmap/maps/jquery.vmap.usa.js" type="text/javascript"></script>
            <script type="text/javascript">


                $(document).ready(function() {

                  if($.browser.mozilla) {
                    //refresh page on browser resize
                    // http://www.sitepoint.com/jquery-refresh-page-browser-resize/
                    $(window).bind('resize', function(e)
                    {
                      if (window.RT) clearTimeout(window.RT);
                      window.RT = setTimeout(function()
                      {
                        this.location.reload(false); /* false to get page from cache */
                      }, 200);
                    });      
                  } else {
                    $(window).resize(function(){
                      drawMaps();
                    });  
                  }

                  drawMaps();

                });
            </script>
        <%
        }
        %>
    </body>
</html>