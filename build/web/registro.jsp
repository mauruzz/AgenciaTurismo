<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>ARGENTOUR - Registro</title>
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
                            <h2 class="margin-bottom-30">Registro</h2>
                            <form action="ServletRegistro" method="POST" class="templatemo-login-form">
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputId">Id</label>
                                        <input type="text" class="form-control" id="inputId" name="inputId" placeholder="Numero de identificación" <%if(session.getAttribute("registro_Id") != null){%>value="<%= session.getAttribute("registro_Id")%>"<%}%> disabled>                  
                                    </div>             
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputNombre">Nombre </label>
                                        <input type="text" class="form-control" id="inputNombre" name="inputNombre" placeholder="Homer" <%if(session.getAttribute("registro_Nombre") != null){%>value="<%= session.getAttribute("registro_Nombre")%>"<%}%>>                  
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputApellido">Apellido</label>
                                        <input type="text" class="form-control" id="inputApellido" name="inputApellido" placeholder="Simpson" <%if(session.getAttribute("registro_Apellido") != null){%>value="<%= session.getAttribute("registro_Apellido")%>"<%}%>>                  
                                    </div> 
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputDireccion">Dirección</label>
                                        <input type="text" class="form-control" id="inputDireccion" name="inputDireccion" placeholder="Av. Siempreviva 742" <%if(session.getAttribute("registro_Direccion") != null){%>value="<%= session.getAttribute("registro_Direccion")%>"<%}%>>                  
                                    </div>
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputDni">DNI</label>
                                        <input type="text" class="form-control" id="inputDni" name="inputDni" placeholder="12345678" <%if(session.getAttribute("registro_Dni") != null){%>value="<%= session.getAttribute("registro_Dni")%>"<%}%>>                  
                                    </div> 
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputFechaNacimiento">Fecha de nacimiento</label>
                                        <input type="date"class="form-control" id="inputFechaNacimiento" name="inputFechaNacimiento" placeholder="1989-12-17" min="1900-01-01" <%if(session.getAttribute("registro_Fecha") != null){%>value="<%= session.getAttribute("registro_Fecha")%>"<%}%>>  
                                    </div>  
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputNacionalidad">Nacionalidad</label>
                                        <input type="text" class="form-control" id="inputNacionalidad" name="inputNacionalidad" placeholder="Estadounidense" <%if(session.getAttribute("registro_Nacionalidad") != null){%>value="<%= session.getAttribute("registro_Nacionalidad")%>"<%}%>>                  
                                    </div>               
                                </div>
                                <div class="row form-group">
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputCelular">Celular</label>
                                        <input type="text" class="form-control" id="inputCelular" name="inputCelular" placeholder="1172727272" <%if(session.getAttribute("registro_Celular") != null){%>value="<%= session.getAttribute("registro_Celular")%>"<%}%>>                  
                                    </div> 
                                    <div class="col-lg-6 col-md-6 form-group">                  
                                        <label for="inputEmail">Email</label>
                                        <input type="email" class="form-control" id="inputEmail" name="inputEmail" placeholder="homer@simpson.com" <%if(session.getAttribute("registro_Email") != null){%>value="<%= session.getAttribute("registro_Email")%>"<%}%>>                  
                                    </div>               
                                </div>
                                <%
                                if (session.getAttribute("cuenta") != null) {
                                    if (session.getAttribute("cuenta").equals("empleado")) {
                                %>  
                                        <div class="row form-group">
                                            <div class="col-lg-6 col-md-6 form-group">                  
                                                <label for="inputUsuario">Usuario</label>
                                                <input type="text" class="form-control" id="inputUsuario" name="inputUsuario" placeholder="Mr.X" <%if(session.getAttribute("registro_Usuario") != null){%>value="<%= session.getAttribute("registro_Usuario")%>"<%}%>>                  
                                            </div>
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-lg-6 col-md-6 form-group">                  
                                                <label for="inputContrasenia">Contraseña</label>
                                                <input type="password" class="form-control" id="inputContrasenia" name="inputContrasenia" placeholder="**************" <%if(session.getAttribute("registro_Contrasenia") != null){%>value="<%= session.getAttribute("registro_Contrasenia")%>"<%}%>>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">                  
                                                <label for="inputConfirmarContrasenia">Confirmar contraseña</label>
                                                <input type="password" class="form-control" id="inputConfirmarContrasenia" name="inputConfirmarContrasenia" placeholder="**************" <%if(session.getAttribute("registro_Contrasenia") != null){%>value="<%= session.getAttribute("registro_Contrasenia")%>"<%}%>>
                                            </div> 
                                        </div>
                                        <div class="row form-group">
                                            <div class="col-lg-6 col-md-6 form-group">                  
                                                <label for="inputCargo">Cargo</label>
                                                <input type="input" class="form-control" id="inputCargo" name="inputCargo" placeholder="Vendedor" <%if(session.getAttribute("registro_Cargo") != null){%>value="<%= session.getAttribute("registro_Cargo")%>"<%}%>>
                                            </div>
                                            <div class="col-lg-6 col-md-6 form-group">                  
                                                <label for="inputSueldo">Sueldo</label>
                                                <input type="input" class="form-control" id="inputSueldo" name="inputSueldo" placeholder="150000" <%if(session.getAttribute("registro_Sueldo") != null){%>value="<%= session.getAttribute("registro_Sueldo")%>"<%}%>>
                                            </div> 
                                        </div>
                                <%
                                    }
                                }
                                %>

                                <div class="form-group text-right">
                                    <%
                                    if(session.getAttribute("boton") != null){

                                        if (session.getAttribute("boton").equals("Editar")) {
                                    %>
                                            <input type="submit" value="Editar" name="boton_Form_Registro" class="templatemo-blue-button">
                                            <button type="reset" class="templatemo-white-button">Limpiar</button>
                                    <%    
                                        }
                                    } else {
                                    %>
                                        <input type="submit" value="Guardar" name="boton_Form_Registro" class="templatemo-blue-button">
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
