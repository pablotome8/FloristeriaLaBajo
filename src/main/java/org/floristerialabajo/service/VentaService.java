package org.floristerialabajo.service;

import org.floristerialabajo.dao.ClienteDAO;
import org.floristerialabajo.dao.CompraDAO;
import org.floristerialabajo.dao.FlorCompraDAO;
import org.floristerialabajo.dao.FlorDAO;
import org.floristerialabajo.dao.TrabajadorDAO;
import org.floristerialabajo.database.DBConnection;
import org.floristerialabajo.model.Cliente;
import org.floristerialabajo.model.Compra;
import org.floristerialabajo.model.Flor;
import org.floristerialabajo.model.FlorCompra;
import org.floristerialabajo.model.Trabajador;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VentaService {

    private final CompraDAO compraDAO = new CompraDAO();
    private final FlorCompraDAO florCompraDAO = new FlorCompraDAO();
    private final FlorDAO florDAO = new FlorDAO();
    private final ClienteDAO clienteDAO = new ClienteDAO();
    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public int registrarVenta(int idTrabajador, int idCliente, String metodoPago, Date fecha, List<FlorCompra> detalles) {
        if (!datosCabeceraValidos(idTrabajador, idCliente, metodoPago, fecha)) {
            return -1;
        }

        if (detalles == null || detalles.isEmpty()) {
            System.out.println("La venta debe contener al menos una flor.");
            return -1;
        }

        Connection con = null;

        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            Trabajador trabajador = trabajadorDAO.buscarPorId(idTrabajador);
            if (trabajador == null) {
                System.out.println("No existe el trabajador con id: " + idTrabajador);
                con.rollback();
                return -1;
            }

            Cliente cliente = clienteDAO.buscarPorId(idCliente);
            if (cliente == null) {
                System.out.println("No existe el cliente con id: " + idCliente);
                con.rollback();
                return -1;
            }

            Map<Integer, Integer> cantidadesPorFlor = new HashMap<>();

            for (FlorCompra detalle : detalles) {
                if (detalle.getIdFlor() <= 0) {
                    System.out.println("El id de la flor debe ser mayor que 0.");
                    con.rollback();
                    return -1;
                }

                if (detalle.getCantidad() <= 0) {
                    System.out.println("La cantidad debe ser mayor que 0.");
                    con.rollback();
                    return -1;
                }

                cantidadesPorFlor.merge(detalle.getIdFlor(), detalle.getCantidad(), Integer::sum);
            }

            Map<Integer, Flor> floresValidadas = new HashMap<>();
            double importeTotal = 0.0;

            for (Map.Entry<Integer, Integer> entry : cantidadesPorFlor.entrySet()) {
                int idFlor = entry.getKey();
                int cantidadTotal = entry.getValue();

                Flor flor = florDAO.buscarPorId(con, idFlor);

                if (flor == null) {
                    System.out.println("No existe la flor con id: " + idFlor);
                    con.rollback();
                    return -1;
                }

                if (flor.getCantidad() < cantidadTotal) {
                    System.out.println("Stock insuficiente para la flor: " + flor.getNombre());
                    con.rollback();
                    return -1;
                }

                floresValidadas.put(idFlor, flor);
                importeTotal += flor.getPrecio() * cantidadTotal;
            }

            Compra compra = new Compra(0, idTrabajador, idCliente, metodoPago, fecha, importeTotal);
            int idCompraGenerado = compraDAO.insertar(con, compra);

            if (idCompraGenerado <= 0) {
                System.out.println("No se pudo registrar la compra.");
                con.rollback();
                return -1;
            }

            List<FlorCompra> detallesAgrupados = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : cantidadesPorFlor.entrySet()) {
                detallesAgrupados.add(new FlorCompra(idCompraGenerado, entry.getKey(), entry.getValue()));
            }

            for (FlorCompra detalle : detallesAgrupados) {
                boolean detalleInsertado = florCompraDAO.insertar(con, detalle);
                if (!detalleInsertado) {
                    System.out.println("No se pudo insertar el detalle de la compra.");
                    con.rollback();
                    return -1;
                }

                Flor flor = floresValidadas.get(detalle.getIdFlor());
                int nuevoStock = flor.getCantidad() - detalle.getCantidad();

                boolean stockActualizado = florDAO.actualizarStock(con, detalle.getIdFlor(), nuevoStock);
                if (!stockActualizado) {
                    System.out.println("No se pudo actualizar el stock de la flor con id: " + detalle.getIdFlor());
                    con.rollback();
                    return -1;
                }
            }

            con.commit();
            return idCompraGenerado;

        } catch (SQLException e) {
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException ex) {
                    System.out.println("Error al hacer rollback: " + ex.getMessage());
                }
            }

            System.out.println("Error al registrar la venta: " + e.getMessage());
            return -1;

        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }

    private boolean datosCabeceraValidos(int idTrabajador, int idCliente, String metodoPago, Date fecha) {
        if (idTrabajador <= 0) {
            System.out.println("El id del trabajador debe ser mayor que 0.");
            return false;
        }

        if (idCliente <= 0) {
            System.out.println("El id del cliente debe ser mayor que 0.");
            return false;
        }

        if (metodoPago == null || metodoPago.isBlank()) {
            System.out.println("El método de pago no puede estar vacío.");
            return false;
        }

        if (fecha == null) {
            System.out.println("La fecha no puede ser nula.");
            return false;
        }

        return true;
    }
}