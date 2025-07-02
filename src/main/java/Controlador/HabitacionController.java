package Controlador;

import Modelo.Habitacion;
import Modelo.EnumEstadoHabitacion;
import Modelo.EnumTipoHabitacion;
import Dao.HabitacionDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/HabitacionController")
public class HabitacionController extends HttpServlet {

    HabitacionDAO dao = new HabitacionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {
            List<Habitacion> habitaciones = dao.listar();
            request.setAttribute("habitaciones", habitaciones);//
            request.getRequestDispatcher("vistas/HomeAdmin.jsp").forward(request, response);

        } else if (accion.equals("crear")) {
            request.setAttribute("estados", EnumEstadoHabitacion.values());
            request.setAttribute("tipos", EnumTipoHabitacion.values());
            request.getRequestDispatcher("vistas/create.jsp").forward(request, response);

        } else if (accion.equals("ver")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Habitacion h = dao.buscarPorId(id);
            request.setAttribute("habitacion", h);
            request.getRequestDispatcher("vistas/show.jsp").forward(request, response);

        } else if (accion.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Habitacion h = dao.buscarPorId(id);
            request.setAttribute("habitacion", h);
            request.setAttribute("estados", EnumEstadoHabitacion.values());
            request.setAttribute("tipos", EnumTipoHabitacion.values());
            request.getRequestDispatcher("vistas/edit.jsp").forward(request, response);

        } else if (accion.equals("eliminar")) {
    int id = Integer.parseInt(request.getParameter("id"));
    boolean eliminado = dao.eliminar(id);
    if (eliminado) {
        response.sendRedirect("HabitacionController?accion=listar");
    } else {
        request.setAttribute("error", "No se pudo eliminar la habitación");
        request.getRequestDispatcher("vistas/HomeAdmin.jsp").forward(request, response);
    }
}//

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion.equals("registrar")) {
            Habitacion h = new Habitacion();

            h.setNumHabitacion(Integer.parseInt(request.getParameter("numHabitacion")));
            h.setEstado(EnumEstadoHabitacion.valueOf(request.getParameter("estado")));
            h.setTipoHabitacion(EnumTipoHabitacion.valueOf(request.getParameter("tipo")));
            h.setFechaCreacion(LocalDateTime.now());
            h.setFechaActualizacion(LocalDateTime.now());

            boolean registrado = dao.registrar(h);
            if (registrado) {
                response.sendRedirect("HabitacionController?accion=listar");
            } else {
                request.setAttribute("error", "No se pudo registrar la habitación");
                request.setAttribute("estados", EnumEstadoHabitacion.values());
                request.setAttribute("tipos", EnumTipoHabitacion.values());
                request.getRequestDispatcher("vistas/create.jsp").forward(request, response);
            }

        } else if (accion.equals("actualizar")) {
            Habitacion h = new Habitacion();

            h.setIdHabitacion(Integer.parseInt(request.getParameter("idHabitacion")));
            h.setNumHabitacion(Integer.parseInt(request.getParameter("numHabitacion")));
            h.setEstado(EnumEstadoHabitacion.valueOf(request.getParameter("estado")));
            h.setTipoHabitacion(EnumTipoHabitacion.valueOf(request.getParameter("tipo")));
            h.setFechaActualizacion(LocalDateTime.now());

            boolean actualizado = dao.actualizar(h);
            if (actualizado) {
                response.sendRedirect("HabitacionController?accion=listar");
            } else {
                request.setAttribute("error", "No se pudo actualizar la habitación");
                request.setAttribute("habitacion", h);
                request.setAttribute("estados", EnumEstadoHabitacion.values());
                request.setAttribute("tipos", EnumTipoHabitacion.values());
                request.getRequestDispatcher("vistas/edit.jsp").forward(request, response);
            }
        }
    }
}
