package org.floristerialabajo.service;

import org.floristerialabajo.dao.FlorCompraDAO;
import org.floristerialabajo.model.FlorCompra;

import java.util.List;

public class FlorCompraService {

    private final FlorCompraDAO florCompraDAO = new FlorCompraDAO();

    public List<FlorCompra> obtenerTodosLosDetalles() {
        return florCompraDAO.listarTodas();
    }

    public List<FlorCompra> obtenerDetallesPorCompra(int idCompra) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return List.of();
        }

        return florCompraDAO.listarPorCompra(idCompra);
    }

    public FlorCompra obtenerDetalle(int idCompra, int idFlor) {
        if (!idsValidos(idCompra, idFlor)) {
            return null;
        }

        return florCompraDAO.buscarPorId(idCompra, idFlor);
    }

    public boolean agregarDetalle(int idCompra, int idFlor, int cantidad) {
        if (!datosValidos(idCompra, idFlor, cantidad)) {
            return false;
        }

        FlorCompra existente = florCompraDAO.buscarPorId(idCompra, idFlor);
        if (existente != null) {
            System.out.println("Ya existe un detalle para esa compra y esa flor.");
            return false;
        }

        FlorCompra florCompra = new FlorCompra(idCompra, idFlor, cantidad);
        return florCompraDAO.insertar(florCompra);
    }

    public boolean actualizarDetalle(int idCompra, int idFlor, int cantidad) {
        if (!datosValidos(idCompra, idFlor, cantidad)) {
            return false;
        }

        FlorCompra existente = florCompraDAO.buscarPorId(idCompra, idFlor);
        if (existente == null) {
            System.out.println("No existe ese detalle de compra.");
            return false;
        }

        FlorCompra florCompraActualizada = new FlorCompra(idCompra, idFlor, cantidad);
        return florCompraDAO.actualizar(florCompraActualizada);
    }

    public boolean eliminarDetalle(int idCompra, int idFlor) {
        if (!idsValidos(idCompra, idFlor)) {
            return false;
        }

        FlorCompra existente = florCompraDAO.buscarPorId(idCompra, idFlor);
        if (existente == null) {
            System.out.println("No existe ese detalle de compra.");
            return false;
        }

        return florCompraDAO.eliminar(idCompra, idFlor);
    }

    public boolean eliminarDetallesDeCompra(int idCompra) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return false;
        }

        List<FlorCompra> detalles = florCompraDAO.listarPorCompra(idCompra);
        if (detalles.isEmpty()) {
            System.out.println("No hay detalles asociados a esa compra.");
            return false;
        }

        return florCompraDAO.eliminarPorCompra(idCompra);
    }

    private boolean idsValidos(int idCompra, int idFlor) {
        if (idCompra <= 0) {
            System.out.println("El id de la compra debe ser mayor que 0.");
            return false;
        }

        if (idFlor <= 0) {
            System.out.println("El id de la flor debe ser mayor que 0.");
            return false;
        }

        return true;
    }

    private boolean datosValidos(int idCompra, int idFlor, int cantidad) {
        if (!idsValidos(idCompra, idFlor)) {
            return false;
        }

        if (cantidad <= 0) {
            System.out.println("La cantidad debe ser mayor que 0.");
            return false;
        }

        return true;
    }
}