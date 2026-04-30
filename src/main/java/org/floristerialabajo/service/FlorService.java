package org.floristerialabajo.service;

import org.floristerialabajo.dao.FlorDAO;
import org.floristerialabajo.model.Flor;

import java.util.List;

public class FlorService {

    private final FlorDAO florDAO = new FlorDAO();

    public List<Flor> obtenerCatalogo() {
        return florDAO.listarTodas();
    }

    public Flor obtenerFlorPorId(int id) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return null;
        }
        return florDAO.buscarPorId(id);
    }

    public boolean agregarFlor(String nombre, double precio, int cantidad) {
        if (!datosValidos(nombre, precio, cantidad)) {
            return false;
        }

        Flor flor = new Flor(0, nombre, precio, cantidad);
        return florDAO.insertar(flor);
    }

    public boolean actualizarFlor(int id, String nombre, double precio, int cantidad) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return false;
        }

        if (!datosValidos(nombre, precio, cantidad)) {
            return false;
        }

        Flor florExistente = florDAO.buscarPorId(id);
        if (florExistente == null) {
            System.out.println("No existe ninguna flor con ese id.");
            return false;
        }

        Flor florActualizada = new Flor(id, nombre, precio, cantidad);
        return florDAO.actualizar(florActualizada);
    }

    public boolean eliminarFlor(int id) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return false;
        }

        Flor florExistente = florDAO.buscarPorId(id);
        if (florExistente == null) {
            System.out.println("No existe ninguna flor con ese id.");
            return false;
        }

        return florDAO.eliminar(id);
    }

    private boolean datosValidos(String nombre, double precio, int cantidad) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        if (precio <= 0) {
            System.out.println("El precio debe ser mayor que 0.");
            return false;
        }

        if (cantidad < 0) {
            System.out.println("La cantidad no puede ser negativa.");
            return false;
        }

        return true;
    }
}