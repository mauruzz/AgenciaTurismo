<%@page import="java.time.LocalDateTime"%>
<%@page import="java.util.Date"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="Logica.Empleado"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">  
        <title>ARGENTOUR - Home</title>
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
        String usuario = (String) miSesion.getAttribute("usuario");

        if (usuario == null){
            response.sendRedirect("SinLogin.jsp");
        } else {
            Controladora control = new Controladora ();
            miSesion.setAttribute("control", control);

            //------------ CALCULO GANANCIA DIARIA

            double ganancia_Diaria = 0;
            List<Empleado> listaEmpleados = control.getListaEmpleados();

            for (Empleado empleado : listaEmpleados){
                ganancia_Diaria = ganancia_Diaria + empleado.getGananciaDiaria();
            }
            
            //------------ CALCULO GANANCIA FACTURADO MENSUAL
            
            int meses = 12;
            double [] vec_Ganancia_Mensual  = new double [meses];
            double [] vec_Facturado_Mensual  = new double [meses];

            for (int i = 0; i < meses; i++) {
                vec_Ganancia_Mensual [i] = 0;
                vec_Facturado_Mensual [i] = 0;
            }

            for (int i = 0; i < meses; i++) {
                for (Empleado empleado : listaEmpleados){

                    vec_Ganancia_Mensual [i] = vec_Ganancia_Mensual [i] + empleado.getGananciaMensual(i + 1);

                    vec_Facturado_Mensual [i] = vec_Facturado_Mensual [i] + empleado.getFacturadoMensual(i + 1);
                }
            }

            // --------------SEPARO VENTAS POR MEDIO DE PAGO
            
            String [] medios_Pago = {"efectivo", "credito", "debito", "monedero", "transferencia"};
            double [] vec_Facturado_Medio_Pago  = new double [medios_Pago.length];

            for (int i = 0; i < medios_Pago.length; i++) {
                vec_Facturado_Medio_Pago [i] = 0;
            }

            for (int i = 0; i < medios_Pago.length; i++) {
                for (Empleado empleado : listaEmpleados){

                    vec_Facturado_Medio_Pago [i] = vec_Facturado_Medio_Pago [i] + empleado.getFacturadoMedioPago(medios_Pago[i]);
                }
            }

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
                            <li><a href="#" class="active"><img class="iconos-menu" src="iconos/home.png">Principal</a></li>
                            <li><a href="empleados.jsp"><img class="iconos-menu" src="iconos/vendedor.png">Empleados</a></li>
                            <li><a href="clientes.jsp"><img class="iconos-menu" src="iconos/clientes.png">Clientes</a></li>
                            <li><a href="servicios.jsp"><img class="iconos-menu" src="iconos/servicios.png">Servicios</a></li>
                            <li><a href="paquetes.jsp"><img class="iconos-menu" src="iconos/paquetes.png">Paquetes</a></li>
                            <li><a href="mediosPago.jsp"><img class="iconos-menu" src="iconos/billetera.png">Medios de pago</a></li>
                            <li><a href="ventas.jsp"><img class="iconos-menu" src="iconos/ventas.png">Ventas</a></li>
                            <li><a href="ServletLogout"><img class="iconos-menu" src="iconos/logout.png">Salir</a></li>
                        </ul>  
                    </nav>
                </div>
                
                <!-- Main content --> 
                
                <div class="templatemo-content col-1 light-gray-bg">

                    <div class="div-usuario">Bienvenido&nbsp;<%=session.getAttribute("nombreUsuario")%></div>
                    <div class="templatemo-content-container">
                        <div class="templatemo-flex-row flex-content-row">
                            <div class="col-1">              
                                <div class="templatemo-content-widget white-bg text-center">
                                    <h2 class="text-uppercase">Ganancia diaria</h2>
                                    <h3 class="text-uppercase"><%=ganancia_Diaria%></h3><hr>  

                                    <!-- GRAFICO DE TORTA -->
                                    
                                    <div id="pie_chart_div" class="templatemo-chart"></div> 

                                </div>            
                            </div>
                            <div class="col-1">
                                <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                                    <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Ranking de empleados</h2></div>
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered">
                                            <thead>
                                                <tr>
                                                    <td>N°</td>
                                                    <td>Nombre</td>
                                                    <td>Apellido</td>
                                                    <td>Facturado</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <%
                                                    List<Empleado> listaEmpleadosHabilitados = control.getListaEmpleadosHabilitados();
                                                    for (Empleado emple : listaEmpleadosHabilitados) {
                                                %>
                                                <tr>
                                                    <td><%=emple.getId_empleado()%></td>
                                                    <td><%=emple.getPersona().getNombre()%></td>
                                                    <td><%=emple.getPersona().getApellido()%></td>
                                                    <td><%=emple.getFacturado()%></td>
                                                </tr>
                                                <%
                                                    }
                                                %>
                                            </tbody>
                                        </table>    
                                    </div>                          
                                </div>
                            </div>           
                        </div> 

                        <div class="templatemo-flex-row flex-content-row">
                            <div class="col-1">
                                <div class="panel panel-default templatemo-content-widget white-bg no-padding templatemo-overflow-hidden">
                                    <div class="panel-heading templatemo-position-relative"><h2 class="text-uppercase">Ventas mensuales</h2></div>
                                    <div class="table-responsive">
                                        <table class="table table-striped table-bordered" style="margin-bottom: 30px">
                                            <thead>
                                                <tr>
                                                    <td>Mes</td>
                                                    <td>Enero</td>
                                                    <td>Febrero</td>
                                                    <td>Marzo</td>
                                                    <td>Abril</td>
                                                    <td>Mayo</td>
                                                    <td>Junio</td>
                                                    <td>Julio</td>
                                                    <td>Agosto</td>
                                                    <td>Septiembre</td>
                                                    <td>Octubre</td>
                                                    <td>Noviembre</td>
                                                    <td>Diciembre</td>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>Facturado</td>
                                                    <%
                                                        for (int i = 0; i < meses; i++) {
                                                    %>
                                                        <td>$&nbsp;<%=vec_Facturado_Mensual[i]%></td>
                                                    <%
                                                        }
                                                    %>
                                                </tr>
                                                <tr>
                                                    <td>Ganancia</td>

                                                    <%
                                                        for (int i = 0; i < meses; i++) {
                                                    %>
                                                        <td>$&nbsp;<%=vec_Ganancia_Mensual[i]%></td>
                                                    <%
                                                        }
                                                    %>
                                                </tr>
                                            </tbody>
                                        </table>    
                                    </div>


                                    <!--   GRAFICO DE BARRAS   -->  
                                    
                                    <div class="templatemo-flex-row flex-content-row">
                                        <div class="col-1 col-lg-6 col-md-12">
                                          <div id="columnchart_material" class="templatemo-chart"></div> <!-- Bar chart div -->
                                        </div>  
                                    </div>
                                </div>
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
            <script src="js/jquery-1.11.2.min.js"></script>      <!-- jQuery -->
            <script src="js/jquery-migrate-1.2.1.min.js"></script> <!--  jQuery Migrate Plugin -->
            <script src="https://www.google.com/jsapi"></script> <!-- Google Chart -->
            <script>
              /* Google Chart 
              -------------------------------------------------------------------*/
              // Load the Visualization API and the piechart package.
              google.load('visualization', '1.0', {'packages':['corechart']});

              // Set a callback to run when the Google Visualization API is loaded.
              google.setOnLoadCallback(drawChart); 

              // Callback that creates and populates a data table,
              // instantiates the pie chart, passes in the data and
              // draws it.



              google.charts.load('current', {'packages':['bar']});
              google.charts.setOnLoadCallback(drawChart);


              function drawChart() {

                  // Create the data table.
                  var data = new google.visualization.DataTable();
                  data.addColumn('string', 'Topping');
                  data.addColumn('number', 'Monto ');
                  data.addRows([
                    ['Efectivo', <%=vec_Facturado_Medio_Pago[0]%>],
                    ['Crédito', <%=vec_Facturado_Medio_Pago[1]%>],
                    ['Débito', <%=vec_Facturado_Medio_Pago[2]%>],
                    ['Monedero', <%=vec_Facturado_Medio_Pago[3]%>],
                    ['Transferencia', <%=vec_Facturado_Medio_Pago[4]%>]
                  ]);

                  // Set chart options
                  var options = {'title':'Medios de pago elegidos'};

                  // Instantiate and draw our chart, passing in some options.
                  var pieChart = new google.visualization.PieChart(document.getElementById('pie_chart_div'));
                  pieChart.draw(data, options);




                var data = google.visualization.arrayToDataTable([
                  ['Mes', 'Facturado', 'Ganancia'],
                  ['Enero', <%=vec_Facturado_Mensual[0]%>, <%=vec_Ganancia_Mensual[0]%>],
                  ['Febrero', <%=vec_Facturado_Mensual[1]%>, <%=vec_Ganancia_Mensual[1]%>],
                  ['Marzo', <%=vec_Facturado_Mensual[2]%>, <%=vec_Ganancia_Mensual[2]%>],
                  ['Abril', <%=vec_Facturado_Mensual[3]%>, <%=vec_Ganancia_Mensual[3]%>],
                  ['Mayo', <%=vec_Facturado_Mensual[4]%>, <%=vec_Ganancia_Mensual[4]%>],
                  ['Junio', <%=vec_Facturado_Mensual[5]%>, <%=vec_Ganancia_Mensual[5]%>],
                  ['Julio', <%=vec_Facturado_Mensual[6]%>, <%=vec_Ganancia_Mensual[6]%>],
                  ['Agosto', <%=vec_Facturado_Mensual[7]%>, <%=vec_Ganancia_Mensual[7]%>],
                  ['Septiembre', <%=vec_Facturado_Mensual[8]%>, <%=vec_Ganancia_Mensual[8]%>],
                  ['Octubre', <%=vec_Facturado_Mensual[9]%>, <%=vec_Ganancia_Mensual[9]%>],
                  ['Noviembre', <%=vec_Facturado_Mensual[10]%>, <%=vec_Ganancia_Mensual[10]%>],
                  ['Diciembre', <%=vec_Facturado_Mensual[11]%>, <%=vec_Ganancia_Mensual[11]%>],
                ]);

                var options = {
                  chart: {
                    title: ''
                  }
                };

                var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

                chart.draw(data, google.charts.Bar.convertOptions(options));
              }

              $(document).ready(function(){
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
                    drawChart();
                  });  
                }   
              });

            </script>
            <script type="text/javascript" src="js/templatemo-script.js"></script>      <!-- Templatemo Script -->
        <% 
        } 
        %>
    </body>
</html>