<%@page import="Logica.Cliente"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">  
        <title>ARGENTOUR - Clientes</title>
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

            // -------- LIMPIO TODAS VARIABLES DE SESSION DESCARTABLES   
            session.removeAttribute("registro_Id");
            session.removeAttribute("registro_Nombre");
            session.removeAttribute("registro_Apellido");
            session.removeAttribute("registro_Direccion");
            session.removeAttribute("registro_Dni");
            session.removeAttribute("registro_Nacionalidad");
            session.removeAttribute("registro_Celular");
            session.removeAttribute("registro_Email");
            session.removeAttribute("registro_Fecha");
            session.removeAttribute("cuenta");
            session.removeAttribute("boton");

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
                            <li><a href="#" class="active"><img class="iconos-menu" src="iconos/clientes.png">Clientes</a></li>
                            <li><a href="servicios.jsp"><img class="iconos-menu" src="iconos/servicios.png">Servicios</a></li>
                            <li><a href="paquetes.jsp"><img class="iconos-menu" src="iconos/paquetes.png">Paquetes</a></li>
                            <li><a href="ventas.jsp"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
                            <li><a href="ServletLogout"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
                        </ul>  
                    </nav>
                </div>
                <!-- Main content --> 
                <div class="templatemo-content col-1 light-gray-bg">
                    <div class="div-usuario">Bienvenido&nbsp;<%=session.getAttribute("nombreUsuario")%></div>  
                    <div class="templatemo-content-container">
                        <div class="templatemo-content-widget white-bg" id="titulo-cliente">
                            <h2 class="margin-bottom-0">Clientes<a href="ServletRegistro?cuenta=cliente"><img class="agregar" src="iconos/agregar.png" alt="Agregar cliente" title="Agregar cliente"></a>
                                <a href="clientes.jsp"><img class="buscar-lupa" src="iconos/lupa.png" alt="Buscar cliente" title="Buscar cliente"></a><div class="buscar-input"><input type="text" class="form-control" placeholder="Buscar" name="buscar_cliente" id="buscar_cliente"></div>
                            </h2>       
                        </div>  
                        <div class="templatemo-content-widget no-padding">
                            <div class="panel panel-default table-responsive">
                                <table class="table table-striped table-bordered templatemo-user-table">
                                    <thead>
                                        <tr>
                                            <td><a href="" class="white-text templatemo-sort-by">N° de cliente <span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Nombre <span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Apellido <span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Dirección <span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Celular <span class="caret"></span></a></td>
                                            <td><a href="" class="white-text templatemo-sort-by">Email <span class="caret"></span></a></td>
                                            <td>Acción</td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                          List<Cliente> listaClientes = control.getListaClientesHabilitados();
                                          for (Cliente clien : listaClientes) {
                                        %> 
                                        <tr>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getId_cliente()%></a></td>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getPersona().getNombre()%></a></td>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getPersona().getApellido()%></a></td>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getPersona().getDireccion()%></a></td>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getPersona().getCelular()%></a></td>
                                            <td><a class="link-negro" href="ServletRegistro?cuenta=cliente&mostrar=ok&id=<%=clien.getId_cliente()%>"><%=clien.getPersona().getEmail()%></a></td>
                                            <td><!--<a href="" class="templatemo-vender-btn">Vender</a>--><a href="ServletRegistro?cuenta=cliente&mostrar=ok&editar=ok&id=<%=clien.getId_cliente()%>" class="templatemo-edit-btn">Editar</a><a href="ServletRegistro?cuenta=cliente&eliminar=ok&id=<%=clien.getId_cliente()%>" class="templatemo-eliminar-btn">Eliminar</a></td>
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
