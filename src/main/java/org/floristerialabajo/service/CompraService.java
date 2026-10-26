package org.floristerialabajo.service;

import org.floristerialabajo.dao.CompraDAO;
import org.floristerialabajo.model.Compra;

import java.sql.Date;
import java.util.List;

public class CompraService {

    private final CompraDAO compraDAO = new CompraDAO();

    public List<Compra> obtenerCompras() {
        return compraDAO.listarTodas();
    }

    public Compra obtenerCompraPorId(int idCompra) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return null;
        }

        return compraDAO.buscarPorId(idCompra);
    }

    public int agregarCompra(int idTrabajador, int idCliente, String metodoPago, Date fecha, double importe) {
        String metodoNormalizado = metodoPago != null ? metodoPago.trim().toLowerCase() : "";
        if (!datosValidos(idTrabajador, idCliente, metodoNormalizado, fecha, importe)) {
            return -1;
        }

        Compra compra = new Compra(0, idTrabajador, idCliente, metodoNormalizado, fecha, importe);
        return compraDAO.insertar(compra);
    }

    public boolean actualizarCompra(int idCompra, int idTrabajador, int idCliente, String metodoPago, Date fecha, double importe) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return false;
        }

        String metodoNormalizado = metodoPago != null ? metodoPago.trim().toLowerCase() : "";
        if (!datosValidos(idTrabajador, idCliente, metodoNormalizado, fecha, importe)) {
            return false;
        }

        Compra compraExistente = compraDAO.buscarPorId(idCompra);
        if (compraExistente == null) {
            System.out.println("No existe ninguna compra con ese id.");
            return false;
        }

        Compra compraActualizada = new Compra(idCompra, idTrabajador, idCliente, metodoNormalizado, fecha, importe);
        return compraDAO.actualizar(compraActualizada);
    }

    public boolean eliminarCompra(int idCompra) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return false;
        }

        Compra compraExistente = compraDAO.buscarPorId(idCompra);
        if (compraExistente == null) {
            System.out.println("No existe ninguna compra con ese id.");
            return false;
        }

        return compraDAO.eliminar(idCompra);
    }

    private boolean datosValidos(int idTrabajador, int idCliente, String metodoPago, Date fecha, double importe) {
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

        if (!metodoPago.equals("efectivo") && !metodoPago.equals("tarjeta") && !metodoPago.equals("transferencia")) {
            System.out.println("Método de pago no válido. Valores permitidos: efectivo, tarjeta, transferencia.");
            return false;
        }

        if (fecha == null) {
            System.out.println("La fecha no puede ser nula.");
            return false;
        }

        if (importe < 0) {
            System.out.println("El importe no puede ser negativo.");
            return false;
        }

        return true;
    }
}