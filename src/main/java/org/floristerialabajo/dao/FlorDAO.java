package org.floristerialabajo.dao;

import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Flor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlorDAO {

    public List<Flor> listarTodas() {
        List<Flor> flores = new ArrayList<>();
        String sql = "SELECT id_flores, nombre, precio, cantidad FROM flores";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Flor flor = new Flor(
                        rs.getInt("id_flores"),
                        rs.getString("nombre"),
                        rs.getDouble("precio"),
                        rs.getInt("cantidad")
                );
                flores.add(flor);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar flores: " + e.getMessage());
        }

        return flores;
    }

    public Flor buscarPorId(int id) {
        String sql = "SELECT id_flores, nombre, precio, cantidad FROM flores WHERE id_flores = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Flor(
                            rs.getInt("id_flores"),
                            rs.getString("nombre"),
                            rs.getDouble("precio"),
                            rs.getInt("cantidad")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar flor: " + e.getMessage());
        }

        return null;
    }

    public boolean insertar(Flor flor) {
        String sql = "INSERT INTO flores (nombre, precio, cantidad) VALUES (?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, flor.getNombre());
            ps.setDouble(2, flor.getPrecio());
            ps.setInt(3, flor.getCantidad());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar flor: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Flor flor) {
        String sql = "UPDATE flores SET nombre = ?, precio = ?, cantidad = ? WHERE id_flores = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setString(1, flor.getNombre());
            ps.setDouble(2, flor.getPrecio());
            ps.setInt(3, flor.getCantidad());
            ps.setInt(4, flor.getIdFlor());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar flor: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM flores WHERE id_flores = ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar flor: " + e.getMessage());
            return false;
        }
    }

}