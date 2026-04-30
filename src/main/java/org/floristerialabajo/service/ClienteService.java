package org.floristerialabajo.service;

import org.floristerialabajo.dao.ClienteDAO;
import org.floristerialabajo.model.Cliente;

import java.util.List;

public class ClienteService {

    private final ClienteDAO clienteDAO = new ClienteDAO();

    public List<Cliente> obtenerClientes() {
        return clienteDAO.listarTodos();
    }

    public Cliente obtenerClientePorId(int id) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return null;
        }
        return clienteDAO.buscarPorId(id);
    }

    public boolean agregarCliente(String dni, String nombre, String apellidos, String email) {
        if (!datosValidos(dni, nombre, apellidos)) {
            return false;
        }

        Cliente cliente = new Cliente(0, dni, nombre, apellidos, email);
        return clienteDAO.insertar(cliente);
    }

    public boolean actualizarCliente(int id, String dni, String nombre, String apellidos, String email) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return false;
        }

        if (!datosValidos(dni, nombre, apellidos)) {
            return false;
        }

        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe ningún cliente con ese id.");
            return false;
        }

        Cliente clienteActualizado = new Cliente(id, dni, nombre, apellidos, email);
        return clienteDAO.actualizar(clienteActualizado);
    }

    public boolean eliminarCliente(int id) {
        if (id <= 0) {
            System.out.println("El id debe ser mayor que 0.");
            return false;
        }

        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe ningún cliente con ese id.");
            return false;
        }

        return clienteDAO.eliminar(id);
    }

    private boolean datosValidos(String dni, String nombre, String apellidos) {
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

        return true;
    }
}