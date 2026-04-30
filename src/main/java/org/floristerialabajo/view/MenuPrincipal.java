package org.floristerialabajo.view;

import org.floristerialabajo.model.Flor;
import org.floristerialabajo.service.FlorService;
import org.floristerialabajo.util.InputUtil;

import java.util.List;

public class MenuPrincipal {

    private final FlorService florService = new FlorService();

    public void iniciar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = InputUtil.leerEntero("Selecciona una opción: ");

            switch (opcion) {
                case 1 -> listarFlores();
                case 2 -> insertarFlor();
                case 0 -> System.out.println("Saliendo del programa...");
                default -> System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenu() {
        System.out.println("\n===== FLORISTERÍA LA BAJO =====");
        System.out.println("1. Listar flores");
        System.out.println("2. Insertar flor");
        System.out.println("0. Salir");
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
}