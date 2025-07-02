package Dao;

import Modelo.Habitacion;
import Modelo.EnumEstadoHabitacion;
import Modelo.EnumTipoHabitacion;
import config.Conexion;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HabitacionDAO {

    private Connection conn;

    public HabitacionDAO() {
        conn = Conexion.getConnection();
    }

    public boolean registrar(Habitacion h) {
        String sql = "INSERT INTO habitacion (numHabitacion, estado, fechaCreacion, fechaActualizacion, tipoHabitacion) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, h.getNumHabitacion());
            ps.setString(2, h.getEstado().name());
            ps.setTimestamp(3, Timestamp.valueOf(h.getFechaCreacion()));
            ps.setTimestamp(4, Timestamp.valueOf(h.getFechaActualizacion()));
            ps.setString(5, h.getTipoHabitacion().name());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar habitación: " + e.getMessage());
            return false;
        }
    }

    public List<Habitacion> listar() {
        List<Habitacion> lista = new ArrayList<>();
        String sql = "SELECT * FROM habitacion";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Habitacion h = new Habitacion();
                h.setIdHabitacion(rs.getInt("idHabitacion"));
                h.setNumHabitacion(rs.getInt("numHabitacion"));
                h.setEstado(EnumEstadoHabitacion.valueOf(rs.getString("estado")));
                h.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                h.setFechaActualizacion(rs.getTimestamp("fechaActualizacion").toLocalDateTime());
                h.setTipoHabitacion(EnumTipoHabitacion.valueOf(rs.getString("tipoHabitacion")));

                lista.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar habitaciones: " + e.getMessage());
        }

        return lista;
    }

    public Habitacion buscarPorId(int id) {
        String sql = "SELECT * FROM habitacion WHERE idHabitacion = ?";
        Habitacion h = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                h = new Habitacion();
                h.setIdHabitacion(rs.getInt("idHabitacion"));
                h.setNumHabitacion(rs.getInt("numHabitacion"));
                h.setEstado(EnumEstadoHabitacion.valueOf(rs.getString("estado")));
                h.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
                h.setFechaActualizacion(rs.getTimestamp("fechaActualizacion").toLocalDateTime());
                h.setTipoHabitacion(EnumTipoHabitacion.valueOf(rs.getString("tipoHabitacion")));
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar habitación: " + e.getMessage());
        }

        return h;
    }

    public boolean actualizar(Habitacion h) {
        String sql = "UPDATE habitacion SET numHabitacion=?, estado=?, fechaActualizacion=?, tipoHabitacion=? WHERE idHabitacion=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, h.getNumHabitacion());
            ps.setString(2, h.getEstado().name());
            ps.setTimestamp(3, Timestamp.valueOf(h.getFechaActualizacion()));
            ps.setString(4, h.getTipoHabitacion().name());
            ps.setInt(5, h.getIdHabitacion());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar habitación: " + e.getMessage());
            return false;
        }
    }
public boolean eliminar(int idHabitacion) {
    String sql = "DELETE FROM habitacion WHERE idHabitacion = ?";
    try (Connection conn = Conexion.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idHabitacion);
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
