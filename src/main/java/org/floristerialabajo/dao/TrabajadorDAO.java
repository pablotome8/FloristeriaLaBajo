package org.floristerialabajo.dao;

import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Trabajador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrabajadorDAO {

    public List<Trabajador> listarTodos() {
        List<Trabajador> trabajadores = new ArrayList<>();
        String sql = "SELECT id_trabajador, dni, nombre, apellidos, telefono, email, fecha_contratacion, fecha_baja " +
                "FROM trabajadores";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Trabajador trabajador = new Trabajador(
                        rs.getInt("id_trabajador"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getDate("fecha_contratacion"),
                        rs.getDate("fecha_baja")
                );
                trabajadores.add(trabajador);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar trabajadores: " + e.getMessage());
        }

        return trabajadores;
    }

    public Trabajador buscarPorId(int idTrabajador) {
        String sql = "SELECT id_trabajador, dni, nombre, apellidos, telefono, email, fecha_contratacion, fecha_baja " +
                "FROM trabajadores WHERE id_trabajador = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idTrabajador);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Trabajador(
                            rs.getInt("id_trabajador"),
                            rs.getString("dni"),
                            rs.getString("nombre"),
                            rs.getString("apellidos"),
                            rs.getString("telefono"),
                            rs.getString("email"),
                            rs.getDate("fecha_contratacion"),
                            rs.getDate("fecha_baja")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar trabajador por id: " + e.getMessage());
        }

        return null;
    }

    public boolean insertar(Trabajador trabajador) {
        String sql = "INSERT INTO trabajadores (dni, nombre, apellidos, telefono, email, fecha_contratacion, fecha_baja) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, trabajador.getDni());
            ps.setString(2, trabajador.getNombre());
            ps.setString(3, trabajador.getApellidos());
            ps.setString(4, trabajador.getTelefono());
            ps.setString(5, trabajador.getEmail());
            ps.setDate(6, trabajador.getFechaContratacion());

            if (trabajador.getFechaBaja() != null) {
                ps.setDate(7, trabajador.getFechaBaja());
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar trabajador: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Trabajador trabajador) {
        String sql = "UPDATE trabajadores " +
                "SET dni = ?, nombre = ?, apellidos = ?, telefono = ?, email = ?, fecha_contratacion = ?, fecha_baja = ? " +
                "WHERE id_trabajador = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, trabajador.getDni());
            ps.setString(2, trabajador.getNombre());
            ps.setString(3, trabajador.getApellidos());
            ps.setString(4, trabajador.getTelefono());
            ps.setString(5, trabajador.getEmail());
            ps.setDate(6, trabajador.getFechaContratacion());

            if (trabajador.getFechaBaja() != null) {
                ps.setDate(7, trabajador.getFechaBaja());
            } else {
                ps.setNull(7, java.sql.Types.DATE);
            }

            ps.setInt(8, trabajador.getIdTrabajador());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar trabajador: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idTrabajador) {
        String sql = "DELETE FROM trabajadores WHERE id_trabajador = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, idTrabajador);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar trabajador: " + e.getMessage());
            return false;
        }
    }
}