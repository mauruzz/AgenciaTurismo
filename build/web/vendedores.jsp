<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">  
    <title>Visual Admin Dashboard - Manage Users</title>
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
    if (usuario != null){
        Controladora control = new Controladora ();
        miSesion.setAttribute("control", control);
    } else {
        response.sendRedirect("SinLogin.jsp");
    }
    %>
    <!-- Left column -->
    <div class="templatemo-flex-row">
      <div class="templatemo-sidebar">
        <header class="templatemo-site-header">
          <!--    LOGO VIEJO
          <div class="square"></div>
          -->
          <img src="iconos/icono.png">
          <h1>ARGENTOUR</h1>
        </header>
        <div class="profile-photo-container">
          <img src="images/foto-menu-lateral.jpg" alt="Profile Photo" class="img-responsive">  
          <div class="profile-photo-overlay"></div>
        </div>      
        <!-- Search box -->
        <!--
        <form class="templatemo-search-form" role="search">
          <div class="input-group">
              <button type="submit" class="fa fa-search"></button>
              <input type="text" class="form-control" placeholder="Search" name="srch-term" id="srch-term">           
          </div>
        </form>
        -->
        <div class="mobile-menu-icon">
            <i class="fa fa-bars"></i>
          </div>
        <nav class="templatemo-left-nav">          
          <ul>
            <li><a href="index.jsp"><img class="iconos-menu" src="iconos/home.png">Principal</a></li>
            <li><a href="#" class="active"><img class="iconos-menu" src="iconos/vendedor.png">Vendedores</a></li>
            <li><a href="clientes.jsp"><img class="iconos-menu" src="iconos/clientes.png">Clientes</a></li>
            <li><a href="servicios.jsp"><img class="iconos-menu" src="iconos/servicios.png">Servicios</a></li>
            <li><a href="paquetes.jsp"><img class="iconos-menu" src="iconos/paquetes.png">Paquetes</a></li>
            <li><a href="ventas.jsp"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
            <li><a href="login.jsp"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
          </ul>  
        </nav>
      </div>
      <!-- Main content --> 
      <div class="templatemo-content col-1 light-gray-bg">
        <div class="templatemo-content-container">
          <div class="templatemo-content-widget white-bg" id="titulo-vendedor">
            <h2 class="margin-bottom-0">Vendedores<a href="registro.jsp"><img class="agregar" src="iconos/agregar.png" alt="Agregar vendedor" title="Agregar vendedor"></a>
              <a href="vendedores.jsp"><img class="buscar-lupa" src="iconos/lupa.png" alt="Buscar vendedor" title="Buscar vendedor"></a><div class="buscar-input"><input type="text" class="form-control" placeholder="Buscar" name="buscar_vendedor" id="buscar_vendedor"></div>
            </h2>     
          </div>  
          <div class="templatemo-content-widget no-padding">
            <div class="panel panel-default table-responsive">
              <table class="table table-striped table-bordered templatemo-user-table">
                <thead>
                  <tr>
                    <td><a href="" class="white-text templatemo-sort-by">N° de vendedor <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Nombre <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Apellido <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Celular <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Email <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Cargo <span class="caret"></span></a></td>
                    <td><a href="" class="white-text templatemo-sort-by">Sueldo <span class="caret"></span></a></td>
                    <td>Acción</td>
                  </tr>
                </thead>
                <tbody>
                  <tr>
                    <td>1.</td>
                    <td>John</td>
                    <td>Smith</td>
                    <td>1542830288</td>
                    <td>J.Smith@yahoo.com</td>
                    <td>Vendedor</td>
                    <td>$100000</td>
                    <td><a href="" class="templatemo-vender-btn">Vender</a><a href="" class="templatemo-edit-btn">Editar</a><a href="" class="templatemo-eliminar-btn">Eliminar</a></td>
                  </tr>                   
                </tbody>
              </table>    
            </div>                          
          </div>          
          

          <!--    FOMRULARIO Y NOTIFICACIONES
          <div class="templatemo-flex-row flex-content-row">
            <div class="col-1">
              <div class="panel panel-default margin-10">
                <div class="panel-heading"><h2 class="text-uppercase">Login Form</h2></div>
                <div class="panel-body">
                  <form action="index.jsp" class="templatemo-login-form">
                    <div class="form-group">
                      <label for="inputEmail">Email address</label>
                      <input type="email" class="form-control" id="inputEmail" placeholder="Enter email">
                    </div>
                    <div class="form-group">                      
                      <label for="inputEmail">Password</label>
                      <input type="password" class="form-control" placeholder="Enter password">                                 
                    </div>              
                    <div class="form-group">
                        <div class="checkbox squaredTwo">
                            <label>
                              <input type="checkbox"> Remember me
                            </label>
                        </div>            
                    </div>
                    <div class="form-group">
                      <button type="submit" class="templatemo-blue-button">Submit</button>
                    </div>
                  </form>
                </div>                
              </div>              
            </div>
            <div class="col-1">              
              <div class="templatemo-content-widget pink-bg">
                <i class="fa fa-times"></i>                
                <h2 class="text-uppercase margin-bottom-10">Latest Data</h2>
                <p class="margin-bottom-0">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc mi sapien, fringilla at orci nec, viverra rhoncus leo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus rhoncus erat non purus commodo, sit amet varius dolor sagittis.</p>                  
              </div>            
              <div class="templatemo-content-widget blue-bg">
                <i class="fa fa-times"></i>
                <h2 class="text-uppercase margin-bottom-10">Older Data</h2>
                <p class="margin-bottom-0">Phasellus dapibus nulla quis risus auctor, non placerat augue consectetur. Aliquam convallis pharetra odio, in convallis erat molestie sed. Fusce mi lacus, semper sit amet mattis eu, volutpat vitae enim.</p>                
              </div>            
            </div>                       
          </div> 
          --><!-- Second row ends -->

          <div class="templatemo-flex-row flex-content-row">
            <div class="templatemo-content-widget white-bg col-2">
              <!--    AGREGAR ESTRELLA DE BUEN COMPRADOR SI HAY TIEMPO    -->
              <div class="media margin-bottom-30">
                <!--    VER SI SE PUEDE AGREGAR IMAGEN
                <div class="media-left padding-right-25">
                  <a href="#">
                    <img class="media-object img-circle templatemo-img-bordered" src="images/person.jpg" alt="Sunset">
                  </a>
                </div>
                -->
                <div class="media-body">
                  <h2 class="media-heading text-uppercase blue-text">Nombre + Apellido</h2>
                  <p>Cliente + Numero</p>
                </div>        
              </div>
              <div class="table-responsive">
                <table class="table">
                  <tbody>
                    <tr>
                      <td id="td-datos">Dirección</td><td>asd</td>
                    </tr>
                    <tr>
                      <td>DNI</td><td>asd</td>
                    </tr>
                    <tr>
                      <td>Fecha de nacimiento</td><td>asd</td>
                    </tr>
                    <tr>
                      <td>Nacionalidad</td><td>asd</td>
                    </tr>
                    <tr>
                      <td>Celular</td><td>asd</td>
                    </tr>
                    <tr>
                      <td>Email</td><td>asd</td>                    
                    </tr>
                    <tr>
                      <td>Cargo</td><td>asd</td>                    
                    </tr>
                    <tr>
                      <td>Sueldo</td><td>asd</td>                    
                    </tr>
                    <tr>
                      <td>Usuario</td><td>asd</td>                    
                    </tr>
                    <tr>
                      <td>Contraseña</td><td>asd</td>                    
                    </tr>
                  </tbody>
                </table>
              </div>             
            </div>


            <!--       SI HAY TIEMPO TERMINAR ESTA PARTE     -->
            
            <div class="templatemo-content-widget white-bg col-1 text-center templatemo-position-relative">
              <!--      VER SI SE PUEDE AGREGAR IMAGEN
              <img src="images/person.jpg" alt="Bicycle" class="img-circle img-thumbnail margin-bottom-30">
              -->
              <h2 class="text-uppercase blue-text margin-bottom-30">Estadísticas</h2>
              <div class="table-responsive">
                <table class="table">
                  <tbody>
                    <tr>
                      <td id="td-datos">Total facturado ultimo mes</td><td>$1600000</td>
                    </tr>
                    <tr>
                      <td>Total facturado 12 meses</td><td>$25490000</td>
                    </tr>
                    <tr>
                      <td>Promedio facturado 12 meses</td><td>$1235000</td>
                    </tr>
                  </tbody>
                </table>
              </div> 

              GRAFICO ?



              <!--      AGREGAR POSIBILIDAD DE REDES SOCIALES SI HAY TIEMPO
              <div class="templatemo-social-icons-container">
                <div class="social-icon-wrap">
                  <i class="fa fa-facebook templatemo-social-icon"></i>  
                </div>
                <div class="social-icon-wrap">
                  <i class="fa fa-twitter templatemo-social-icon"></i>  
                </div>
                <div class="social-icon-wrap">
                  <i class="fa fa-google-plus templatemo-social-icon"></i>  
                </div>                
              </div>
              -->
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
  </body>
</html>