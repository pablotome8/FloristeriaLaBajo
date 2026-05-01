package org.floristerialabajo.dao;

import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.FlorCompra;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlorCompraDAO {

    public List<FlorCompra> listarTodas() {
        String sql = "SELECT id_compra, id_flor, cantidad FROM florescompras";

        try (Connection con = DBConnection.getConnection()) {
            return listarTodas(con);
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de compra: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<FlorCompra> listarTodas(Connection con) throws SQLException {
        List<FlorCompra> detalles = new ArrayList<>();
        String sql = "SELECT id_compra, id_flor, cantidad FROM florescompras";

        try (
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                FlorCompra detalle = new FlorCompra(
                        rs.getInt("id_compra"),
                        rs.getInt("id_flor"),
                        rs.getInt("cantidad")
                );
                detalles.add(detalle);
            }
        }

        return detalles;
    }

    public List<FlorCompra> listarPorCompra(int idCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return listarPorCompra(con, idCompra);
        } catch (SQLException e) {
            System.out.println("Error al listar detalles de la compra: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<FlorCompra> listarPorCompra(Connection con, int idCompra) throws SQLException {
        List<FlorCompra> detalles = new ArrayList<>();
        String sql = "SELECT id_compra, id_flor, cantidad FROM florescompras WHERE id_compra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    FlorCompra detalle = new FlorCompra(
                            rs.getInt("id_compra"),
                            rs.getInt("id_flor"),
                            rs.getInt("cantidad")
                    );
                    detalles.add(detalle);
                }
            }
        }

        return detalles;
    }

    public FlorCompra buscarPorId(int idCompra, int idFlor) {
        try (Connection con = DBConnection.getConnection()) {
            return buscarPorId(con, idCompra, idFlor);
        } catch (SQLException e) {
            System.out.println("Error al buscar detalle de compra: " + e.getMessage());
            return null;
        }
    }

    public FlorCompra buscarPorId(Connection con, int idCompra, int idFlor) throws SQLException {
        String sql = "SELECT id_compra, id_flor, cantidad " +
                "FROM florescompras WHERE id_compra = ? AND id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ps.setInt(2, idFlor);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new FlorCompra(
                            rs.getInt("id_compra"),
                            rs.getInt("id_flor"),
                            rs.getInt("cantidad")
                    );
                }
            }
        }

        return null;
    }

    public boolean insertar(FlorCompra florCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return insertar(con, florCompra);
        } catch (SQLException e) {
            System.out.println("Error al insertar detalle de compra: " + e.getMessage());
            return false;
        }
    }

    public boolean insertar(Connection con, FlorCompra florCompra) throws SQLException {
        String sql = "INSERT INTO florescompras (id_compra, id_flor, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, florCompra.getIdCompra());
            ps.setInt(2, florCompra.getIdFlor());
            ps.setInt(3, florCompra.getCantidad());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean actualizar(FlorCompra florCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return actualizar(con, florCompra);
        } catch (SQLException e) {
            System.out.println("Error al actualizar detalle de compra: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Connection con, FlorCompra florCompra) throws SQLException {
        String sql = "UPDATE florescompras SET cantidad = ? WHERE id_compra = ? AND id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, florCompra.getCantidad());
            ps.setInt(2, florCompra.getIdCompra());
            ps.setInt(3, florCompra.getIdFlor());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idCompra, int idFlor) {
        try (Connection con = DBConnection.getConnection()) {
            return eliminar(con, idCompra, idFlor);
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalle de compra: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Connection con, int idCompra, int idFlor) throws SQLException {
        String sql = "DELETE FROM florescompras WHERE id_compra = ? AND id_flor = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            ps.setInt(2, idFlor);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminarPorCompra(int idCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return eliminarPorCompra(con, idCompra);
        } catch (SQLException e) {
            System.out.println("Error al eliminar detalles de la compra: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminarPorCompra(Connection con, int idCompra) throws SQLException {
        String sql = "DELETE FROM florescompras WHERE id_compra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);

            return ps.executeUpdate() > 0;
        }
    }
}