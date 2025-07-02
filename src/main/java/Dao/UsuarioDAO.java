/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;


import Modelo.EnumEstadoUsuario;
import Modelo.EnumRoles;
import Modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.Conexion;
import java.sql.SQLException;

public class UsuarioDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    
    //Registrar usuario
    public boolean registrarUsuario(Usuario u) {
    String sql = "INSERT INTO usuario (nombre, email, fechaCreacion, fechaActualizacion, rol, password, estado) " +
                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try {
        con = Conexion.getConnection();
        if (con == null) {
            System.out.println("Conexión fallida");
            return false;
        }

        con.setAutoCommit(false);

        ps = con.prepareStatement(sql);
        ps.setString(1, u.getNombre());
        ps.setString(2, u.getEmail());
        ps.setObject(3, u.getFechaCreacion()); 
        ps.setObject(4, u.getFechaActualizacion()); 
        ps.setString(5, u.getRol().toString()); 
        ps.setString(6, u.getPassword());
        ps.setString(7, u.getEstado().toString()); 

        ps.executeUpdate();
        con.commit();
        return true;

    } catch (SQLException e) {
        try {
            if (con != null) con.rollback();
        } catch (SQLException ex) {
            System.out.println("Rollback fallido: " + ex.getMessage());
        }
        System.out.println("Error al registrar usuario: " + e.getMessage());
        return false;
    } finally {
        try {
            if (ps != null) ps.close();
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        } catch (SQLException ex) {
            System.out.println("Error al cerrar: " + ex.getMessage());
        }
    }
}
     // VALIDAR LOGIN
    public Usuario ValidarLogin(String email, String password) {
        Usuario u = null;
        String sql = "SELECT * FROM usuario WHERE email = ? AND password = ? AND estado = 'Activo' ";

        try {
            con = Conexion.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                  u = new Usuario();
            u.setIdUsuario(rs.getInt("idUsuario"));
            u.setNombre(rs.getString("nombre"));
            u.setEmail(rs.getString("email"));
            u.setFechaCreacion(rs.getTimestamp("fechaCreacion").toLocalDateTime());
            u.setFechaActualizacion(rs.getTimestamp("fechaActualizacion").toLocalDateTime());
            u.setRol(EnumRoles.valueOf(rs.getString("rol")));
            u.setPassword(rs.getString("password"));
            u.setEstado(EnumEstadoUsuario.valueOf(rs.getString("estado")));
            }

        } catch (SQLException e) {
            System.out.println("Error al validar login: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException ex) {
                System.out.println("Error al cerrar recursos: " + ex.getMessage());
            }
        }

        return u;
    }


}
