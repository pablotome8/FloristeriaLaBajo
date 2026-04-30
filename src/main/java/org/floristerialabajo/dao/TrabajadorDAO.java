package org.floristerialabajo.dao;
import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Trabajador;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {
    public List<Trabajador> listarTodos() {
        List<Trabajador> lista = new ArrayList<>();
        String sql = "SELECT * FROM trabajadores";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_contratacion"),
                        rs.getDate("fecha_baja")
                ));
            }
        } catch (SQLException e) { System.out.println("Error: " + e.getMessage()); }
        return lista;
    }

    public boolean insertar(Trabajador t) {
        String sql = "INSERT INTO trabajadores (dni, nombre, apellidos, telefono, email, fecha_contratacion) VALUES (?,?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getDni());
            ps.setString(2, t.getNombre());
            ps.setString(3, t.getApellidos());
            ps.setString(4, t.getTelefono());
            ps.setString(5, t.getEmail());
            ps.setDate(6, t.getFechaContratacion());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }
}