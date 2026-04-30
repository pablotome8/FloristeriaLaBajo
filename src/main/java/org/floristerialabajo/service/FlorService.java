package org.floristerialabajo.service;

import org.floristerialabajo.dao.FlorDAO;
import org.floristerialabajo.model.Flor;

import java.util.List;

public class FlorService {

    private final FlorDAO florDAO = new FlorDAO();

    public List<Flor> obtenerCatalogo() {
        return florDAO.listarTodas();
    }

    public boolean agregarFlor(String nombre, double precio, int cantidad) {
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

        Flor flor = new Flor(0, nombre, precio, cantidad);
        return florDAO.insertar(flor);
    }
}