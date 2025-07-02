
package Controlador;

import Dao.UsuarioDAO;
import Modelo.Usuario;
import Modelo.EnumRoles;
import Modelo.EnumEstadoUsuario;
import Modelo.CifradoAES;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDateTime;

   @WebServlet("/UsuarioController") 
    public class UsuarioController extends HttpServlet {
       
       UsuarioDAO dao= new UsuarioDAO();
       
       @Override
       protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException{
           
           String accion = request.getParameter("accion");
           
           if("registrar".equals(accion)){
               String nombre = request.getParameter("nombre");
               String email = request.getParameter("email");
               String password = request.getParameter("password");
              
             
               LocalDateTime fechaActual = LocalDateTime.now();
               
               
               Usuario u = new Usuario();
               
               String passwordCifrado = CifradoAES.cifrar(password);
               
               u.setNombre(nombre);
               u.setEmail(email);
               u.setFechaCreacion(fechaActual);
               u.setFechaActualizacion(fechaActual);
               u.setRol(EnumRoles.huesped);
               u.setPassword(passwordCifrado);
               u.setEstado(EnumEstadoUsuario.Activo);
               
               boolean exito = dao.registrarUsuario(u);
               
               if(exito){
                   response.sendRedirect("vistas/login.jsp");
               }
               else{
                    request.setAttribute("error", "Error al registrar usuario");
                    request.getRequestDispatcher("vistas/registro.jsp").forward(request, response);
               }

           } else if ("login".equals(accion)) {
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            
            
            String passwordCifrado = CifradoAES.cifrar(password);

            Usuario u = dao.ValidarLogin(email, passwordCifrado);

            if (u != null) { // se autentico exitosamente
                HttpSession session = request.getSession();
                session.setAttribute("usuario", u);
                
                if (u.getRol()==EnumRoles.admin) {
                    response.sendRedirect("vistas/HomeAdmin.jsp"); 
                }
                else
                {
                    response.sendRedirect("vistas/HomeHuesped.jsp"); 
                }
                
            } else {
                request.setAttribute("error", "Correo o contraseña incorrectos");
                request.getRequestDispatcher("vistas/login.jsp").forward(request, response);
            }
         }
       }
       
       


   }