package org.floristerialabajo.dao;

import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Compra;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompraDAO {

    public List<Compra> listarTodas() {
        String sql = "SELECT id_compra, id_trabajador, id_cliente, metodo_pago, fecha, importe FROM compras";

        try (Connection con = DBConnection.getConnection()) {
            return listarTodas(con);
        } catch (SQLException e) {
            System.out.println("Error al listar compras: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Compra> listarTodas(Connection con) throws SQLException {
        List<Compra> compras = new ArrayList<>();
        String sql = "SELECT id_compra, id_trabajador, id_cliente, metodo_pago, fecha, importe FROM compras";

        try (
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Compra compra = new Compra(
                        rs.getInt("id_compra"),
                        rs.getInt("id_trabajador"),
                        rs.getInt("id_cliente"),
                        rs.getString("metodo_pago"),
                        rs.getDate("fecha"),
                        rs.getDouble("importe")
                );
                compras.add(compra);
            }
        }

        return compras;
    }

    public Compra buscarPorId(int idCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return buscarPorId(con, idCompra);
        } catch (SQLException e) {
            System.out.println("Error al buscar compra por id: " + e.getMessage());
            return null;
        }
    }

    public Compra buscarPorId(Connection con, int idCompra) throws SQLException {
        String sql = "SELECT id_compra, id_trabajador, id_cliente, metodo_pago, fecha, importe " +
                "FROM compras WHERE id_compra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Compra(
                            rs.getInt("id_compra"),
                            rs.getInt("id_trabajador"),
                            rs.getInt("id_cliente"),
                            rs.getString("metodo_pago"),
                            rs.getDate("fecha"),
                            rs.getDouble("importe")
                    );
                }
            }
        }

        return null;
    }

    public int insertar(Compra compra) {
        try (Connection con = DBConnection.getConnection()) {
            return insertar(con, compra);
        } catch (SQLException e) {
            System.out.println("Error al insertar compra: " + e.getMessage());
            return -1;
        }
    }

    public int insertar(Connection con, Compra compra) throws SQLException {
        String sql = "INSERT INTO compras (id_trabajador, id_cliente, metodo_pago, fecha, importe) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, compra.getIdTrabajador());
            ps.setInt(2, compra.getIdCliente());
            ps.setString(3, compra.getMetodoPago());
            ps.setDate(4, compra.getFecha());
            ps.setDouble(5, compra.getImporte());

            int filasAfectadas = ps.executeUpdate();

            if (filasAfectadas == 0) {
                return -1;
            }

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }

        return -1;
    }

    public boolean actualizar(Compra compra) {
        try (Connection con = DBConnection.getConnection()) {
            return actualizar(con, compra);
        } catch (SQLException e) {
            System.out.println("Error al actualizar compra: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Connection con, Compra compra) throws SQLException {
        String sql = "UPDATE compras " +
                "SET id_trabajador = ?, id_cliente = ?, metodo_pago = ?, fecha = ?, importe = ? " +
                "WHERE id_compra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, compra.getIdTrabajador());
            ps.setInt(2, compra.getIdCliente());
            ps.setString(3, compra.getMetodoPago());
            ps.setDate(4, compra.getFecha());
            ps.setDouble(5, compra.getImporte());
            ps.setInt(6, compra.getIdCompra());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int idCompra) {
        try (Connection con = DBConnection.getConnection()) {
            return eliminar(con, idCompra);
        } catch (SQLException e) {
            System.out.println("Error al eliminar compra: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(Connection con, int idCompra) throws SQLException {
        String sql = "DELETE FROM compras WHERE id_compra = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, idCompra);
            return ps.executeUpdate() > 0;
        }
    }
}