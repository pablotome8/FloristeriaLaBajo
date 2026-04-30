package org.floristerialabajo.view;

import org.floristerialabajo.model.Cliente;
import org.floristerialabajo.model.Flor;
import org.floristerialabajo.model.Trabajador;
import org.floristerialabajo.service.ClienteService;
import org.floristerialabajo.service.FlorService;
import org.floristerialabajo.service.TrabajadorService;
import org.floristerialabajo.util.InputUtil;

import java.sql.Date;
import java.util.List;

public class MenuPrincipal {

    private final FlorService florService = new FlorService();
    private final ClienteService clienteService = new ClienteService();
    private final TrabajadorService trabajadorService = new TrabajadorService();

    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> menuFlores();
                case 2 -> menuClientes();
                case 3 -> menuTrabajadores();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuPrincipal() {
        System.out.println("\n===== FLORISTERÍA LA BAJO =====");
        System.out.println("1. Gestión de flores");
        System.out.println("2. Gestión de clientes");
        System.out.println("3. Gestión de trabajadores");
        System.out.println("0. Salir");
    }

    private void menuFlores() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE FLORES =====");
            System.out.println("1. Listar flores");
            System.out.println("2. Insertar flor");
            System.out.println("3. Actualizar flor");
            System.out.println("4. Eliminar flor");
            System.out.println("5. Buscar flor por id");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarFlores();
                case 2 -> insertarFlor();
                case 3 -> actualizarFlor();
                case 4 -> eliminarFlor();
                case 5 -> buscarFlorPorId();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuClientes() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE CLIENTES =====");
            System.out.println("1. Listar clientes");
            System.out.println("2. Insertar cliente");
            System.out.println("3. Actualizar cliente");
            System.out.println("4. Eliminar cliente");
            System.out.println("5. Buscar cliente por id");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarClientes();
                case 2 -> insertarCliente();
                case 3 -> actualizarCliente();
                case 4 -> eliminarCliente();
                case 5 -> buscarClientePorId();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void menuTrabajadores() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE TRABAJADORES =====");
            System.out.println("1. Listar trabajadores");
            System.out.println("2. Insertar trabajador");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarTrabajadores();
                case 2 -> insertarTrabajador();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarFlores() {
        List<Flor> flores = florService.obtenerCatalogo();

        if (flores.isEmpty()) {
            System.out.println("No hay flores registradas.");
            return;
        }

        for (Flor flor : flores) {
            System.out.println(flor);
        }
    }

    private void insertarFlor() {
        String nombre = InputUtil.leerTexto("Nombre: ");
        double precio = InputUtil.leerDouble("Precio: ");
        int cantidad = InputUtil.leerEntero("Cantidad: ");

        boolean insertada = florService.agregarFlor(nombre, precio, cantidad);

        if (insertada) {
            System.out.println("Flor insertada correctamente.");
        } else {
            System.out.println("No se pudo insertar la flor.");
        }
    }

    private void actualizarFlor() {
        int id = InputUtil.leerEntero("Id de la flor a actualizar: ");
        String nombre = InputUtil.leerTexto("Nuevo nombre: ");
        double precio = InputUtil.leerDouble("Nuevo precio: ");
        int cantidad = InputUtil.leerEntero("Nueva cantidad: ");

        boolean actualizada = florService.actualizarFlor(id, nombre, precio, cantidad);

        if (actualizada) {
            System.out.println("Flor actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la flor.");
        }
    }

    private void eliminarFlor() {
        int id = InputUtil.leerEntero("Id de la flor a eliminar: ");

        boolean eliminada = florService.eliminarFlor(id);

        if (eliminada) {
            System.out.println("Flor eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la flor.");
        }
    }

    private void buscarFlorPorId() {
        int id = InputUtil.leerEntero("Id de la flor a buscar: ");
        Flor flor = florService.obtenerFlorPorId(id);

        if (flor != null) {
            System.out.println(flor);
        } else {
            System.out.println("No se encontró ninguna flor con ese id.");
        }
    }

    private void listarClientes() {
        List<Cliente> clientes = clienteService.obtenerClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    private void insertarCliente() {
        String dni = InputUtil.leerTexto("DNI: ");
        String nombre = InputUtil.leerTexto("Nombre: ");
        String apellidos = InputUtil.leerTexto("Apellidos: ");
        String email = InputUtil.leerTexto("Email (opcional): ");

        boolean insertado = clienteService.agregarCliente(dni, nombre, apellidos, email);

        if (insertado) {
            System.out.println("Cliente insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el cliente.");
        }
    }

    private void actualizarCliente() {
        int id = InputUtil.leerEntero("Id del cliente a actualizar: ");
        String dni = InputUtil.leerTexto("Nuevo DNI: ");
        String nombre = InputUtil.leerTexto("Nuevo nombre: ");
        String apellidos = InputUtil.leerTexto("Nuevos apellidos: ");
        String email = InputUtil.leerTexto("Nuevo email (opcional): ");

        boolean actualizado = clienteService.actualizarCliente(id, dni, nombre, apellidos, email);

        if (actualizado) {
            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }
    }

    private void eliminarCliente() {
        int id = InputUtil.leerEntero("Id del cliente a eliminar: ");

        boolean eliminado = clienteService.eliminarCliente(id);

        if (eliminado) {
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el cliente.");
        }
    }

    private void buscarClientePorId() {
        int id = InputUtil.leerEntero("Id del cliente a buscar: ");
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con ese id.");
        }
    }

    private void listarTrabajadores() {
        List<Trabajador> trabajadores = trabajadorService.obtenerTrabajadores();

        if (trabajadores.isEmpty()) {
            System.out.println("No hay trabajadores registrados.");
            return;
        }

        for (Trabajador trabajador : trabajadores) {
            System.out.println(trabajador);
        }
    }

    private void insertarTrabajador() {
        try {
            String dni = InputUtil.leerTexto("DNI: ");
            String nombre = InputUtil.leerTexto("Nombre: ");
            String apellidos = InputUtil.leerTexto("Apellidos: ");
            String telefono = InputUtil.leerTexto("Teléfono (opcional): ");
            String email = InputUtil.leerTexto("Email (opcional): ");
            String fechaTexto = InputUtil.leerTexto("Fecha de contratación (yyyy-mm-dd): ");

            Date fechaContratacion = null;
            if (!fechaTexto.isBlank()) {
                fechaContratacion = Date.valueOf(fechaTexto);
            }

            boolean insertado = trabajadorService.agregarTrabajador(
                    dni,
                    nombre,
                    apellidos,
                    telefono,
                    email,
                    fechaContratacion
            );

            if (insertado) {
                System.out.println("Trabajador insertado correctamente.");
            } else {
                System.out.println("No se pudo insertar el trabajador.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha incorrecto. Usa yyyy-mm-dd.");
        }
    }
}