<%@page import="Logica.MedioPago"%>
<%@page import="Logica.Cliente"%>
<%@page import="Logica.PaqueteTuristico"%>
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
        <title>ARGENTOUR - Facturas</title>
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
        
            //----------- BUSCO TODOS LOS SERVICIOS
            List<ServicioTuristico> listaServiciosHabilitados = control.getListaServiciosHabilitados();

            //----------- BUSCO TODOS LOS PAQUETES
            List<PaqueteTuristico> listaPaquetesHabilitados = control.getListaPaquetesHabilitados();

            //----------- BUSCO TODOS LOS CLIENTES
            List<Cliente> listaClientesHabilitados = control.getListaClientesHabilitados();
            
            //----------- BUSCO TODOS LOS CLIENTES
            List<MedioPago> listaMediosPagoHabilitados = control.getListaMediosPagoHabilitados();

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
                            <li><a href="ventas.jsp" class="active"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
                            <li><a href="ServletLogout"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
                        </ul>
                    </nav>
                </div>
                
                <!-- Main content -->
                
                <div class="templatemo-content col-1 light-gray-bg">
                    <div class="div-usuario">Bienvenido&nbsp;<%=session.getAttribute("nombreUsuario")%></div>
                    <div class="templatemo-content-container">
                        <div class="templatemo-content-widget white-bg">
                            <h2 class="margin-bottom-30">Factura <%if(session.getAttribute("venta_Id_Venta") != null){%>N° <%= session.getAttribute("venta_Id_Venta")%><%}%></h2>
                            <form action="ServletVentas" class="templatemo-login-form" method="POST">
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputIdCliente">N° Cliente</label>
                                        <input type="text" class="form-control margin-bottom-5" id="inputIdCliente" name="inputIdCliente" placeholder="Numero de identificación"  <%if(session.getAttribute("venta_Id_Cliente") != null){%>value="<%= session.getAttribute("venta_Id_Cliente")%>"<%}%>>
                                        <div class="form-group text-left">
                                            <input type="submit" value="Seleccionar" name="boton_Form_Factura_Selecionar" class="templatemo-blue-button">
                                        </div>
                                    </div> 

                                    <div class="col-lg-6 col-md-6 form-group">    
                                        <label class="control-label templatemo-block">Busqueda rápida</label>
                                        <select id="busqueda-rapida-cliente" class="form-control">

                                            <option value="">-----  Clientes  -----</option> 

                                            <%
                                                for (Cliente clien: listaClientesHabilitados){
                                            %>
                                                    <option value="<%=clien.getId_cliente()%>" <%if(session.getAttribute("paquete_Lista_Servicios")!= null)if(clien.getId_cliente() == ((int[])session.getAttribute("paquete_Lista_Servicios"))[1]){%> selected <%}%>><%=clien.getPersona().getNombre()%>&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;Id&nbsp;<%=clien.getId_cliente()%>&nbsp;)</option>
                                            <%
                                                }
                                            %>  

                                        </select>                 
                                    </div>       
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputNombre">Nombre </label>
                                        <input type="text" class="form-control" id="inputNombre" name="inputNombre" placeholder="Homer" <%if(session.getAttribute("venta_Nombre_Cliente") != null){%>value="<%= session.getAttribute("venta_Nombre_Cliente")%>"<%}%>>                  
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputApellido">Apellido</label>
                                        <input type="text" class="form-control" id="inputApellido" name="inputApellido" placeholder="Simpson" <%if(session.getAttribute("venta_Apellido_Cliente") != null){%>value="<%= session.getAttribute("venta_Apellido_Cliente")%>"<%}%>>                  
                                    </div> 
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputDireccion">Dirección</label>
                                        <input type="text" class="form-control" id="inputDireccion" name="inputDireccion" placeholder="Av. Siempreviva 742" <%if(session.getAttribute("venta_Direccion_Cliente") != null){%>value="<%= session.getAttribute("venta_Direccion_Cliente")%>"<%}%>>                  
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputDni">DNI</label>
                                        <input type="text" class="form-control" id="inputDni" name="inputDni" placeholder="12345678" <%if(session.getAttribute("venta_Dni_Cliente") != null){%>value="<%= session.getAttribute("venta_Dni_Cliente")%>"<%}%>>                  
                                    </div> 
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">    
                                        <label class="control-label templatemo-block">Servicio/Paquete</label>
                                        <select id="ServicioPaquete" name="ServicioPaquete" class="form-control">

                                            <option value="">-----  Servicios  -----</option>

                                            <%
                                            for (ServicioTuristico servi: listaServiciosHabilitados){
                                            %>
                                                <option value="servicio-<%=servi.getCodigo_servicio()%>" <%if(session.getAttribute("venta_Id_Servicio")!= null)if(servi.getCodigo_servicio() == (int)session.getAttribute("venta_Id_Servicio")){%> selected <%}%>><%=servi.getNombre()%>&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;<%=servi.getCosto_servicio()%>&nbsp;)</option>
                                            <%
                                            }
                                            %>                        

                                            <option value="">-----  Paquetes  -----</option>    

                                            <%
                                            for (PaqueteTuristico paque: listaPaquetesHabilitados){
                                            %>
                                                <option value="paquete-<%=paque.getCodigo_paquete()%>" <%if(session.getAttribute("venta_Id_Paquete")!= null)if(paque.getCodigo_paquete() == (int)session.getAttribute("venta_Id_Paquete")){%> selected <%}%>><%=paque.getNombre()%>&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;<%=paque.getCosto_paquete()%>&nbsp;)</option>
                                            <%
                                            }
                                            %>  

                                        </select>                 
                                    </div>                     
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputCantidad">Cantidad</label>
                                        <input type="text" class="form-control" id="inputCantidad" name="inputCantidad" placeholder="1" <%if(session.getAttribute("venta_Cantidad") != null){%>value="<%= session.getAttribute("venta_Cantidad")%>"<%}%>>
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label class="control-label templatemo-block">Medio de pago</label>
                                        <select id="medioPago" name="medioPago" class="form-control">
                                            
                                            <option value="">-----  Medios de pago  -----</option> 
                                            
                                            <%
                                            for (MedioPago medioPago: listaMediosPagoHabilitados){
                                            %>
                                            <option value="<%=medioPago.getCodigo_medio_pago()%>" <%if(session.getAttribute("venta_Medio_Pago")!= null)if(session.getAttribute("venta_Medio_Pago").equals(Integer.toString(medioPago.getCodigo_medio_pago()))){%> selected <%}%> ><%=medioPago.getNombre()%>&nbsp;&nbsp;&nbsp;&nbsp;(&nbsp;<%=medioPago.getDescuento()%>&nbsp;)</option>
                                            <%
                                            }
                                            %>  
                                            
                                        </select> 
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

                                            <input type="submit" value="Editar" name="boton_Form_Factura" class="templatemo-blue-button">
                                            <button type="reset" class="templatemo-white-button">Limpiar</button>
                                    <%    
                                        }
                                    } else {
                                    %>

                                        <input type="submit" value="Vender" name="boton_Form_Factura" class="templatemo-blue-button">
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

