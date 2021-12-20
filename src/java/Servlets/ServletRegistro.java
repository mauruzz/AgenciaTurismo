package Servlets;

import Logica.Cliente;
import Logica.Controladora;
import Logica.Empleado;
import Logica.Persona;
import Logica.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ServletRegistro", urlPatterns = {"/ServletRegistro"})
public class ServletRegistro extends HttpServlet {

    // ------------- VARIABLES GLOBALES 
    
    Controladora control = new Controladora();
    Empleado emple = new Empleado ();
    Cliente clien = new Cliente ();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        // ------------- CARGO LOS DATOS EN EL FORMULARIO DIFERENCIANDO ENTRE CLIENTE Y EMPLEADO
        
        if(request.getParameter("mostrar") != null){
            if(request.getParameter("mostrar").equals("ok")){
                
                if(request.getParameter("cuenta").equals("empleado")){
                    
                    emple = control.getEmpleadoById(request.getParameter("id"));

                    request.getSession().setAttribute("registro_Id", request.getParameter("id"));
                    
                    Usuario usu = emple.getUsuario();
                    request.getSession().setAttribute("registro_Usuario", usu.getUsuario());
                    request.getSession().setAttribute("registro_Contrasenia", usu.getContrasenia());

                    Persona perso = emple.getPersona();
                    request.getSession().setAttribute("registro_Nombre", perso.getNombre());
                    request.getSession().setAttribute("registro_Apellido", perso.getApellido());
                    request.getSession().setAttribute("registro_Direccion", perso.getDireccion());
                    request.getSession().setAttribute("registro_Dni", perso.getDni());
                    request.getSession().setAttribute("registro_Nacionalidad", perso.getNacionalidad());
                    request.getSession().setAttribute("registro_Celular", perso.getCelular());
                    request.getSession().setAttribute("registro_Email", perso.getEmail());
                    String fechaNac = control.deDateToString(perso.getFecha_nacimiento());
                    request.getSession().setAttribute("registro_Fecha", fechaNac);
                    
                    request.getSession().setAttribute("registro_Cargo", emple.getCargo());
                    request.getSession().setAttribute("registro_Sueldo", emple.getSueldo());

                    request.getSession().setAttribute("boton", "Mostrar");
                    
                    request.getSession().setAttribute("cuenta", request.getParameter("cuenta"));
                    
                }
                
                if(request.getParameter("cuenta").equals("cliente")){
                    clien = control.getClienteById(request.getParameter("id"));


                    request.getSession().setAttribute("registro_Id", request.getParameter("id"));
                    
                    Persona perso = clien.getPersona();
                    request.getSession().setAttribute("registro_Nombre", perso.getNombre());
                    request.getSession().setAttribute("registro_Apellido", perso.getApellido());
                    request.getSession().setAttribute("registro_Direccion", perso.getDireccion());
                    request.getSession().setAttribute("registro_Dni", perso.getDni());
                    request.getSession().setAttribute("registro_Nacionalidad", perso.getNacionalidad());
                    request.getSession().setAttribute("registro_Celular", perso.getCelular());
                    request.getSession().setAttribute("registro_Email", perso.getEmail());
                    String fechaNac = control.deDateToString(perso.getFecha_nacimiento());
                    request.getSession().setAttribute("registro_Fecha", fechaNac);
                    
                    request.getSession().setAttribute("boton", "Mostrar");
                    
                    request.getSession().setAttribute("cuenta", request.getParameter("cuenta"));

                }
                
            }
        }
        
        // ---------------- HABILITO EL BOTON EDITAR
        
        if(request.getParameter("editar") != null){
            if(request.getParameter("editar").equals("ok")){

                request.getSession().setAttribute("boton", "Editar");
            }
        }
        
        // -------------- ELIMINO EL REGISTRO DIFERENCIENDO ENTRE CLIENTE Y EMPLEADO
        
        if(request.getParameter("eliminar") != null){
            if(request.getParameter("eliminar").equals("ok")){
                if(request.getParameter("cuenta").equals("empleado")){
                
                    control.eliminarLogicoEmpleado(request.getParameter("id"));
                    response.sendRedirect("empleados.jsp");
                }
                
                if(request.getParameter("cuenta").equals("cliente")){
                
                    control.eliminarLogicoCliente(request.getParameter("id"));
                    response.sendRedirect("clientes.jsp");
                }
            }
        } else {
        
            request.getSession().setAttribute("cuenta", request.getParameter("cuenta"));
            response.sendRedirect("registro.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    
        // --------------- GUARDO LOS DATOS DEL REGISTRO 
        
        if (request.getParameter("boton_Form_Registro").equals("Guardar")){
           
            String Nombre = request.getParameter("inputNombre");
            String Apellido = request.getParameter("inputApellido");
            String Direccion = request.getParameter("inputDireccion");
            String Dni = request.getParameter("inputDni");
            String Fecha_Nac = request.getParameter("inputFechaNacimiento");
            String Nacionalidad = request.getParameter("inputNacionalidad");
            String Celular = request.getParameter("inputCelular");
            String Email = request.getParameter("inputEmail");
            String Usuario = request.getParameter("inputUsuario");
            String Contrasenia = request.getParameter("inputContrasenia");
            String Cargo = request.getParameter("inputCargo");
            String Sueldo = request.getParameter("inputSueldo");
               
            
            if ( request.getSession().getAttribute("cuenta").equals("empleado") ){
                control.crearEmpleado(Nombre, Apellido, Direccion, Dni, Fecha_Nac, Nacionalidad, Celular, Email, Usuario, Contrasenia, Cargo, Sueldo); 
                response.sendRedirect("empleados.jsp");
            }
            
            if ( request.getSession().getAttribute("cuenta").equals("cliente") ){
                control.crearCliente(Nombre, Apellido, Direccion, Dni, Fecha_Nac, Nacionalidad, Celular, Email); 
                response.sendRedirect("clientes.jsp");
            }
            
        }
        
        // --------------- EDITO EL REGISTRO
        
        if (request.getParameter("boton_Form_Registro").equals("Editar")){
            
            String Id = (String)request.getSession().getAttribute("registro_Id");
            String Nombre = request.getParameter("inputNombre");
            String Apellido = request.getParameter("inputApellido");
            String Direccion = request.getParameter("inputDireccion");
            String Dni = request.getParameter("inputDni");
            String Fecha_Nac = request.getParameter("inputFechaNacimiento");
            String Nacionalidad = request.getParameter("inputNacionalidad");
            String Celular = request.getParameter("inputCelular");
            String Email = request.getParameter("inputEmail");
            String Usuario = request.getParameter("inputUsuario");
            String Contrasenia = request.getParameter("inputContrasenia");
            String Cargo = request.getParameter("inputCargo");
            String Sueldo = request.getParameter("inputSueldo");
               
            if ( request.getSession().getAttribute("cuenta").equals("empleado") == true ){
                control.editarEmpleado(Id, Nombre, Apellido, Direccion, Dni, Fecha_Nac, Nacionalidad, Celular, Email, Usuario, Contrasenia, Cargo, Sueldo); 
                response.sendRedirect("empleados.jsp");
            }
            
            if ( request.getSession().getAttribute("cuenta").equals("cliente") == true){
                control.editarCliente(Id, Nombre, Apellido, Direccion, Dni, Fecha_Nac, Nacionalidad, Celular, Email); 
                response.sendRedirect("clientes.jsp");
            }
            
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
