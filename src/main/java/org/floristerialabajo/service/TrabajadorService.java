package org.floristerialabajo.service;

import org.floristerialabajo.dao.TrabajadorDAO;
import org.floristerialabajo.model.Trabajador;

import java.sql.Date;
import java.util.List;

public class TrabajadorService {

    private final TrabajadorDAO trabajadorDAO = new TrabajadorDAO();

    public List<Trabajador> obtenerTrabajadores() {
        return trabajadorDAO.listarTodos();
    }

    public boolean agregarTrabajador(String dni, String nombre, String apellidos, String telefono, String email, Date fechaContratacion) {
        if (!datosValidos(dni, nombre, apellidos)) {
            return false;
        }

        Trabajador trabajador = new Trabajador(0, dni, nombre, apellidos, telefono, email, fechaContratacion, null);
        return trabajadorDAO.insertar(trabajador);
    }

    private boolean datosValidos(String dni, String nombre, String apellidos) {
        if (dni == null || dni.length() != 9) {
            System.out.println("Error: El DNI debe tener 9 caracteres.");
            return false;
        }

        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre no puede estar vacío.");
            return false;
        }

        if (apellidos == null || apellidos.isBlank()) {
            System.out.println("Error: Los apellidos no pueden estar vacíos.");
            return false;
        }

        return true;
    }
}