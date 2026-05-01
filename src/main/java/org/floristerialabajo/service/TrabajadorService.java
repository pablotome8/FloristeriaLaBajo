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

    public Trabajador obtenerTrabajadorPorId(int idTrabajador) {
        if (idTrabajador <= 0) {
            System.out.println("El id del trabajador debe ser mayor que 0.");
            return null;
        }

        return trabajadorDAO.buscarPorId(idTrabajador);
    }

    public boolean agregarTrabajador(String dni, String nombre, String apellidos,
                                     String telefono, String email, Date fechaContratacion) {
        if (!datosValidos(dni, nombre, apellidos, fechaContratacion)) {
            return false;
        }

        Trabajador trabajador = new Trabajador(
                0,
                dni,
                nombre,
                apellidos,
                telefono,
                email,
                fechaContratacion,
                null
        );

        return trabajadorDAO.insertar(trabajador);
    }

    public boolean actualizarTrabajador(int idTrabajador, String dni, String nombre, String apellidos,
                                        String telefono, String email, Date fechaContratacion, Date fechaBaja) {
        if (idTrabajador <= 0) {
            System.out.println("El id del trabajador debe ser mayor que 0.");
            return false;
        }

        if (!datosValidos(dni, nombre, apellidos, fechaContratacion)) {
            return false;
        }

        Trabajador existente = trabajadorDAO.buscarPorId(idTrabajador);
        if (existente == null) {
            System.out.println("No existe ningún trabajador con ese id.");
            return false;
        }

        if (fechaBaja != null && fechaContratacion != null && fechaBaja.before(fechaContratacion)) {
            System.out.println("La fecha de baja no puede ser anterior a la fecha de contratación.");
            return false;
        }

        Trabajador trabajadorActualizado = new Trabajador(
                idTrabajador,
                dni,
                nombre,
                apellidos,
                telefono,
                email,
                fechaContratacion,
                fechaBaja
        );

        return trabajadorDAO.actualizar(trabajadorActualizado);
    }

    public boolean eliminarTrabajador(int idTrabajador) {
        if (idTrabajador <= 0) {
            System.out.println("El id del trabajador debe ser mayor que 0.");
            return false;
        }

        Trabajador existente = trabajadorDAO.buscarPorId(idTrabajador);
        if (existente == null) {
            System.out.println("No existe ningún trabajador con ese id.");
            return false;
        }

        return trabajadorDAO.eliminar(idTrabajador);
    }

    public boolean darDeBajaTrabajador(int idTrabajador, Date fechaBaja) {
        if (idTrabajador <= 0) {
            System.out.println("El id del trabajador debe ser mayor que 0.");
            return false;
        }

        if (fechaBaja == null) {
            System.out.println("La fecha de baja no puede ser nula.");
            return false;
        }

        Trabajador existente = trabajadorDAO.buscarPorId(idTrabajador);
        if (existente == null) {
            System.out.println("No existe ningún trabajador con ese id.");
            return false;
        }

        if (existente.getFechaContratacion() != null && fechaBaja.before(existente.getFechaContratacion())) {
            System.out.println("La fecha de baja no puede ser anterior a la fecha de contratación.");
            return false;
        }

        existente.setFechaBaja(fechaBaja);
        return trabajadorDAO.actualizar(existente);
    }

    private boolean datosValidos(String dni, String nombre, String apellidos, Date fechaContratacion) {
        if (dni == null || dni.isBlank()) {
            System.out.println("El DNI no puede estar vacío.");
            return false;
        }

        if (dni.length() != 9) {
            System.out.println("El DNI debe tener 9 caracteres.");
            return false;
        }

        if (nombre == null || nombre.isBlank()) {
            System.out.println("El nombre no puede estar vacío.");
            return false;
        }

        if (apellidos == null || apellidos.isBlank()) {
            System.out.println("Los apellidos no pueden estar vacíos.");
            return false;
        }

        if (fechaContratacion == null) {
            System.out.println("La fecha de contratación no puede ser nula.");
            return false;
        }

        return true;
    }
}