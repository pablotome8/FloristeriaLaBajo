package org.floristerialabajo.view;

import org.floristerialabajo.model.Cliente;
import org.floristerialabajo.model.Compra;
import org.floristerialabajo.model.Flor;
import org.floristerialabajo.model.FlorCompra;
import org.floristerialabajo.model.Trabajador;
import org.floristerialabajo.service.ClienteService;
import org.floristerialabajo.service.CompraService;
import org.floristerialabajo.service.FlorCompraService;
import org.floristerialabajo.service.FlorService;
import org.floristerialabajo.service.TrabajadorService;
import org.floristerialabajo.service.VentaService;
import org.floristerialabajo.util.InputUtil;
import java.util.LinkedHashMap;
import java.util.Map;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal {

    private final FlorService florService = new FlorService();
    private final ClienteService clienteService = new ClienteService();
    private final TrabajadorService trabajadorService = new TrabajadorService();
    private final CompraService compraService = new CompraService();
    private final FlorCompraService florCompraService = new FlorCompraService();
    private final VentaService ventaService = new VentaService();

    public void iniciar() {
        int opcion;

        do {
            mostrarMenuPrincipal();
            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> menuFlores();
                case 2 -> menuClientes();
                case 3 -> menuTrabajadores();
                case 4 -> menuCompras();
                case 5 -> menuDetallesCompra();
                case 6 -> registrarVenta();
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
        System.out.println("4. Gestión de compras");
        System.out.println("5. Gestión de detalles de compra");
        System.out.println("6. Registrar venta");
        System.out.println("0. Salir");
    }

    // =========================
    // MENÚ FLORES
    // =========================
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
        double precio = InputUtil.leerDoublePositivo("Precio: ");
        int cantidad = InputUtil.leerEntero("Cantidad: ");

        boolean insertada = florService.agregarFlor(nombre, precio, cantidad);

        if (insertada) {
            System.out.println("Flor insertada correctamente.");
        } else {
            System.out.println("No se pudo insertar la flor.");
        }
    }

    private void actualizarFlor() {
        int id = InputUtil.leerEnteroPositivo("Id de la flor a actualizar: ");
        String nombre = InputUtil.leerTexto("Nuevo nombre: ");
        double precio = InputUtil.leerDoublePositivo("Nuevo precio: ");
        int cantidad = InputUtil.leerEntero("Nueva cantidad: ");

        boolean actualizada = florService.actualizarFlor(id, nombre, precio, cantidad);

        if (actualizada) {
            System.out.println("Flor actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la flor.");
        }
    }

    private void eliminarFlor() {
        int id = InputUtil.leerEnteroPositivo("Id de la flor a eliminar: ");

        boolean eliminada = florService.eliminarFlor(id);

        if (eliminada) {
            System.out.println("Flor eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la flor.");
        }
    }

    private void buscarFlorPorId() {
        int id = InputUtil.leerEnteroPositivo("Id de la flor a buscar: ");
        Flor flor = florService.obtenerFlorPorId(id);

        if (flor != null) {
            System.out.println(flor);
        } else {
            System.out.println("No se encontró ninguna flor con ese id.");
        }
    }

    // =========================
    // MENÚ CLIENTES
    // =========================
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
        String email = InputUtil.leerTextoOpcional("Email (opcional): ");

        boolean insertado = clienteService.agregarCliente(dni, nombre, apellidos, email);

        if (insertado) {
            System.out.println("Cliente insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el cliente.");
        }
    }

    private void actualizarCliente() {
        int id = InputUtil.leerEnteroPositivo("Id del cliente a actualizar: ");
        String dni = InputUtil.leerTexto("Nuevo DNI: ");
        String nombre = InputUtil.leerTexto("Nuevo nombre: ");
        String apellidos = InputUtil.leerTexto("Nuevos apellidos: ");
        String email = InputUtil.leerTextoOpcional("Nuevo email (opcional): ");

        boolean actualizado = clienteService.actualizarCliente(id, dni, nombre, apellidos, email);

        if (actualizado) {
            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el cliente.");
        }
    }

    private void eliminarCliente() {
        int id = InputUtil.leerEnteroPositivo("Id del cliente a eliminar: ");

        boolean eliminado = clienteService.eliminarCliente(id);

        if (eliminado) {
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el cliente.");
        }
    }

    private void buscarClientePorId() {
        int id = InputUtil.leerEnteroPositivo("Id del cliente a buscar: ");
        Cliente cliente = clienteService.obtenerClientePorId(id);

        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("No se encontró ningún cliente con ese id.");
        }
    }

    // =========================
    // MENÚ TRABAJADORES
    // =========================
    private void menuTrabajadores() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE TRABAJADORES =====");
            System.out.println("1. Listar trabajadores");
            System.out.println("2. Insertar trabajador");
            System.out.println("3. Buscar trabajador por id");
            System.out.println("4. Actualizar trabajador");
            System.out.println("5. Eliminar trabajador");
            System.out.println("6. Dar de baja trabajador");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarTrabajadores();
                case 2 -> insertarTrabajador();
                case 3 -> buscarTrabajadorPorId();
                case 4 -> actualizarTrabajador();
                case 5 -> eliminarTrabajador();
                case 6 -> darDeBajaTrabajador();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
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
        String dni = InputUtil.leerTexto("DNI: ");
        String nombre = InputUtil.leerTexto("Nombre: ");
        String apellidos = InputUtil.leerTexto("Apellidos: ");
        String telefono = InputUtil.leerTextoOpcional("Teléfono (opcional): ");
        String email = InputUtil.leerTextoOpcional("Email (opcional): ");
        Date fechaContratacion = InputUtil.leerFecha("Fecha de contratación (yyyy-mm-dd): ");

        boolean insertado = trabajadorService.agregarTrabajador(
                dni, nombre, apellidos, telefono, email, fechaContratacion
        );

        if (insertado) {
            System.out.println("Trabajador insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el trabajador.");
        }
    }

    private void buscarTrabajadorPorId() {
        int id = InputUtil.leerEnteroPositivo("Id del trabajador a buscar: ");
        Trabajador trabajador = trabajadorService.obtenerTrabajadorPorId(id);

        if (trabajador != null) {
            System.out.println(trabajador);
        } else {
            System.out.println("No se encontró ningún trabajador con ese id.");
        }
    }

    private void actualizarTrabajador() {
        int id = InputUtil.leerEnteroPositivo("Id del trabajador a actualizar: ");
        String dni = InputUtil.leerTexto("Nuevo DNI: ");
        String nombre = InputUtil.leerTexto("Nuevo nombre: ");
        String apellidos = InputUtil.leerTexto("Nuevos apellidos: ");
        String telefono = InputUtil.leerTextoOpcional("Nuevo teléfono (opcional): ");
        String email = InputUtil.leerTextoOpcional("Nuevo email (opcional): ");
        Date fechaContratacion = InputUtil.leerFecha("Nueva fecha de contratación (yyyy-mm-dd): ");
        Date fechaBaja = InputUtil.leerFechaOpcional("Fecha de baja (yyyy-mm-dd, opcional): ");

        boolean actualizado = trabajadorService.actualizarTrabajador(
                id, dni, nombre, apellidos, telefono, email, fechaContratacion, fechaBaja
        );

        if (actualizado) {
            System.out.println("Trabajador actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el trabajador.");
        }
    }

    private void eliminarTrabajador() {
        int id = InputUtil.leerEnteroPositivo("Id del trabajador a eliminar: ");

        boolean eliminado = trabajadorService.eliminarTrabajador(id);

        if (eliminado) {
            System.out.println("Trabajador eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el trabajador.");
        }
    }

    private void darDeBajaTrabajador() {
        int id = InputUtil.leerEnteroPositivo("Id del trabajador a dar de baja: ");
        Date fechaBaja = InputUtil.leerFecha("Fecha de baja (yyyy-mm-dd): ");

        boolean baja = trabajadorService.darDeBajaTrabajador(id, fechaBaja);

        if (baja) {
            System.out.println("Trabajador dado de baja correctamente.");
        } else {
            System.out.println("No se pudo dar de baja al trabajador.");
        }
    }

    // =========================
    // MENÚ COMPRAS
    // =========================
    private void menuCompras() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE COMPRAS =====");
            System.out.println("1. Listar compras");
            System.out.println("2. Insertar compra");
            System.out.println("3. Actualizar compra");
            System.out.println("4. Eliminar compra");
            System.out.println("5. Buscar compra por id");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarCompras();
                case 2 -> insertarCompra();
                case 3 -> actualizarCompra();
                case 4 -> eliminarCompra();
                case 5 -> buscarCompraPorId();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarCompras() {
        List<Compra> compras = compraService.obtenerCompras();

        if (compras.isEmpty()) {
            System.out.println("No hay compras registradas.");
            return;
        }

        for (Compra compra : compras) {
            System.out.println(compra);
        }
    }

    private void insertarCompra() {
        int idTrabajador = InputUtil.leerEnteroPositivo("Id del trabajador: ");
        int idCliente = InputUtil.leerEnteroPositivo("Id del cliente: ");
        String metodoPago = InputUtil.leerTexto("Método de pago: ");
        Date fecha = InputUtil.leerFecha("Fecha (yyyy-mm-dd): ");
        double importe = InputUtil.leerDoublePositivo("Importe: ");

        int idCompra = compraService.agregarCompra(idTrabajador, idCliente, metodoPago, fecha, importe);

        if (idCompra > 0) {
            System.out.println("Compra insertada correctamente con id: " + idCompra);
        } else {
            System.out.println("No se pudo insertar la compra.");
        }
    }

    private void actualizarCompra() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra a actualizar: ");
        int idTrabajador = InputUtil.leerEnteroPositivo("Nuevo id del trabajador: ");
        int idCliente = InputUtil.leerEnteroPositivo("Nuevo id del cliente: ");
        String metodoPago = InputUtil.leerTexto("Nuevo método de pago: ");
        Date fecha = InputUtil.leerFecha("Nueva fecha (yyyy-mm-dd): ");
        double importe = InputUtil.leerDoublePositivo("Nuevo importe: ");

        boolean actualizada = compraService.actualizarCompra(
                idCompra, idTrabajador, idCliente, metodoPago, fecha, importe
        );

        if (actualizada) {
            System.out.println("Compra actualizada correctamente.");
        } else {
            System.out.println("No se pudo actualizar la compra.");
        }
    }

    private void eliminarCompra() {
        int id = InputUtil.leerEnteroPositivo("Id de la compra a eliminar: ");
        boolean eliminada = compraService.eliminarCompra(id);

        if (eliminada) {
            System.out.println("Compra eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la compra.");
        }
    }

    private void buscarCompraPorId() {
        int id = InputUtil.leerEnteroPositivo("Id de la compra a buscar: ");
        Compra compra = compraService.obtenerCompraPorId(id);

        if (compra != null) {
            System.out.println(compra);
        } else {
            System.out.println("No se encontró ninguna compra con ese id.");
        }
    }

    // =========================
    // MENÚ DETALLES DE COMPRA
    // =========================
    private void menuDetallesCompra() {
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE DETALLES DE COMPRA =====");
            System.out.println("1. Listar todos los detalles");
            System.out.println("2. Listar detalles por compra");
            System.out.println("3. Buscar detalle");
            System.out.println("4. Insertar detalle");
            System.out.println("5. Actualizar detalle");
            System.out.println("6. Eliminar detalle");
            System.out.println("7. Eliminar todos los detalles de una compra");
            System.out.println("0. Volver");

            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarTodosLosDetalles();
                case 2 -> listarDetallesPorCompra();
                case 3 -> buscarDetalle();
                case 4 -> insertarDetalle();
                case 5 -> actualizarDetalle();
                case 6 -> eliminarDetalle();
                case 7 -> eliminarDetallesDeCompra();
                case 0 -> System.out.println("Volviendo al menú principal...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void listarTodosLosDetalles() {
        List<FlorCompra> detalles = florCompraService.obtenerTodosLosDetalles();

        if (detalles.isEmpty()) {
            System.out.println("No hay detalles de compra registrados.");
            return;
        }

        for (FlorCompra detalle : detalles) {
            System.out.println(detalle);
        }
    }

    private void listarDetallesPorCompra() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");
        List<FlorCompra> detalles = florCompraService.obtenerDetallesPorCompra(idCompra);

        if (detalles.isEmpty()) {
            System.out.println("No hay detalles para esa compra.");
            return;
        }

        for (FlorCompra detalle : detalles) {
            System.out.println(detalle);
        }
    }

    private void buscarDetalle() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");
        int idFlor = InputUtil.leerEnteroPositivo("Id de la flor: ");

        FlorCompra detalle = florCompraService.obtenerDetalle(idCompra, idFlor);

        if (detalle != null) {
            System.out.println(detalle);
        } else {
            System.out.println("No se encontró ese detalle de compra.");
        }
    }

    private void insertarDetalle() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");
        int idFlor = InputUtil.leerEnteroPositivo("Id de la flor: ");
        int cantidad = InputUtil.leerEnteroPositivo("Cantidad: ");

        boolean insertado = florCompraService.agregarDetalle(idCompra, idFlor, cantidad);

        if (insertado) {
            System.out.println("Detalle insertado correctamente.");
        } else {
            System.out.println("No se pudo insertar el detalle.");
        }
    }

    private void actualizarDetalle() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");
        int idFlor = InputUtil.leerEnteroPositivo("Id de la flor: ");
        int cantidad = InputUtil.leerEnteroPositivo("Nueva cantidad: ");

        boolean actualizado = florCompraService.actualizarDetalle(idCompra, idFlor, cantidad);

        if (actualizado) {
            System.out.println("Detalle actualizado correctamente.");
        } else {
            System.out.println("No se pudo actualizar el detalle.");
        }
    }

    private void eliminarDetalle() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");
        int idFlor = InputUtil.leerEnteroPositivo("Id de la flor: ");

        boolean eliminado = florCompraService.eliminarDetalle(idCompra, idFlor);

        if (eliminado) {
            System.out.println("Detalle eliminado correctamente.");
        } else {
            System.out.println("No se pudo eliminar el detalle.");
        }
    }

    private void eliminarDetallesDeCompra() {
        int idCompra = InputUtil.leerEnteroPositivo("Id de la compra: ");

        boolean eliminados = florCompraService.eliminarDetallesDeCompra(idCompra);

        if (eliminados) {
            System.out.println("Detalles eliminados correctamente.");
        } else {
            System.out.println("No se pudieron eliminar los detalles.");
        }
    }

    // =========================
    // REGISTRAR VENTA
    // =========================
    private void registrarVenta() {
        System.out.println("\n===== REGISTRAR VENTA =====");

        int idTrabajador = InputUtil.leerEnteroPositivo("Id del trabajador: ");
        int idCliente = InputUtil.leerEnteroPositivo("Id del cliente: ");
        String metodoPago = InputUtil.leerTexto("Método de pago: ");
        Date fecha = InputUtil.leerFecha("Fecha (yyyy-mm-dd): ");

        Map<Integer, Integer> cantidadesPorFlor = new LinkedHashMap<>();

        do {
            int idFlor = InputUtil.leerEnteroPositivo("Id de la flor: ");
            int cantidad = InputUtil.leerEnteroPositivo("Cantidad: ");

            Flor flor = florService.obtenerFlorPorId(idFlor);

            if (flor == null) {
                System.out.println("No existe ninguna flor con ese id.");
                continue;
            }

            cantidadesPorFlor.merge(idFlor, cantidad, Integer::sum);

            System.out.println("Añadido/actualizado:");
            System.out.println("- Flor: " + flor.getNombre());
            System.out.println("- Cantidad acumulada: " + cantidadesPorFlor.get(idFlor));

        } while (InputUtil.leerBoolean("¿Añadir otra flor?"));

        if (cantidadesPorFlor.isEmpty()) {
            System.out.println("No se puede registrar una venta sin detalles.");
            return;
        }

        List<FlorCompra> detalles = new ArrayList<>();
        double totalEstimado = 0.0;

        System.out.println("\n===== RESUMEN DE LA VENTA =====");
        for (Map.Entry<Integer, Integer> entry : cantidadesPorFlor.entrySet()) {
            int idFlor = entry.getKey();
            int cantidadTotal = entry.getValue();

            Flor flor = florService.obtenerFlorPorId(idFlor);
            if (flor != null) {
                double subtotal = flor.getPrecio() * cantidadTotal;
                totalEstimado += subtotal;

                System.out.println(
                        "Flor: " + flor.getNombre() +
                                " | Precio: " + String.format("%.2f", flor.getPrecio()) +
                                " | Cantidad: " + cantidadTotal +
                                " | Subtotal: " + String.format("%.2f", subtotal)
                );

                detalles.add(new FlorCompra(0, idFlor, cantidadTotal));
            }
        }

        System.out.println("Total estimado: " + String.format("%.2f", totalEstimado));

        boolean confirmar = InputUtil.leerBoolean("¿Confirmar venta?");
        if (!confirmar) {
            System.out.println("Venta cancelada por el usuario.");
            return;
        }

        int idVenta = ventaService.registrarVenta(idTrabajador, idCliente, metodoPago, fecha, detalles);

        if (idVenta > 0) {
            System.out.println("Venta registrada correctamente con id de compra: " + idVenta);
        } else {
            System.out.println("No se pudo registrar la venta.");
        }
    }}