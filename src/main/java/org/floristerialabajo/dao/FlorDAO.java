package org.floristerialabajo.dao;

import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Flor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlorDAO {

    public List<Flor> listarTodas() {
        String sql = "SELECT id_flor, nombre, precio, cantidad FROM flores";

        try (Connection con = DBConnection.getConnection()) {
            return listarTodas(con);
        } catch (SQLException e) {
            System.out.println("Error al listar flores: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Flor> listarTodas(Connection con) throws SQLException {
        List<Flor> flores = new ArrayList<>();
        String sql = "SELECT id_flor, nombre, precio, cantidad FROM flores";

        try (
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Flor flor = new Flor(
                        rs.getInt("id_flor"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                );
                flores.add(flor);
            }
        }

        return flores;
    }

    public Flor buscarPorId(int idFlor) {
        try (Connection con = DBConnection.getConnection()) {
            return buscarPorId(con, idFlor);
        } catch (SQLException e) {
            System.out.println("Error al buscar flor por id: " + e.getMessage());
            return null;
        }
    }

    public Flor buscarPorId(Connection con, int idFlor) throws SQLException {
        String sql = "SELECT id_flor, nombre, precio, cantidad FROM flores WHERE id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFlor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Flor(
                            rs.getInt("id_flor"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(Flor flor) {
        try (Connection con = DBConnection.getConnection()) {
            return insertar(con, flor);
        } catch (SQLException e) {
            System.out.println("Error al insertar flor: " + e.getMessage());
            return false;
        }
    }

    public boolean insertar(Connection con, Flor flor) throws SQLException {
        String sql = "INSERT INTO flores (nombre, precio, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, flor.getNombre());
            ps.setDouble(2, flor.getPrecio());
            ps.setInt(3, flor.getCantidad());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(Flor flor) {
        try (Connection con = DBConnection.getConnection()) {
            return actualizar(con, flor);
        } catch (SQLException e) {
            System.out.println("Error al actualizar flor: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Connection con, Flor flor) throws SQLException {
        String sql = "UPDATE flores SET nombre = ?, precio = ?, cantidad = ? WHERE id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, flor.getNombre());
            ps.setDouble(2, flor.getPrecio());
            ps.setInt(3, flor.getCantidad());
            ps.setInt(4, flor.getIdFlor());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizarStock(int idFlor, int nuevaCantidad) {
        try (Connection con = DBConnection.getConnection()) {
            return actualizarStock(con, idFlor, nuevaCantidad);
        } catch (SQLException e) {
            System.out.println("Error al actualizar stock de la flor: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizarStock(Connection con, int idFlor, int nuevaCantidad) throws SQLException {
        String sql = "UPDATE flores SET cantidad = ? WHERE id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevaCantidad);
            ps.setInt(2, idFlor);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idFlor) {
        try (Connection con = DBConnection.getConnection()) {
            return eliminar(con, idFlor);
        } catch (SQLException e) {
            System.out.println("Error al eliminar flor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Connection con, int idFlor) throws SQLException {
        String sql = "DELETE FROM flores WHERE id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idFlor);

            return ps.executeUpdate() > 0;
        }
    }
}