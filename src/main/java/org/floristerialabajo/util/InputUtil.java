package org.floristerialabajo.util;

import java.sql.Date;
import java.util.Scanner;

public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static String leerTextoOpcional(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes introducir un número entero.");
            }
        }
    }

    public static int leerEnteroPositivo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("El valor debe ser mayor que 0.");
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(sc.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida. Debes introducir un número decimal.");
            }
        }
    }

    public static double leerDoublePositivo(String mensaje) {
        while (true) {
            double valor = leerDouble(mensaje);
            if (valor > 0) {
                return valor;
            }
            System.out.println("El valor debe ser mayor que 0.");
        }
    }

    public static Date leerFecha(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String texto = sc.nextLine().trim();
                return Date.valueOf(texto);
            } catch (IllegalArgumentException e) {
                System.out.println("Fecha no válida. Usa el formato yyyy-mm-dd.");
            }
        }
    }

    public static Date leerFechaOpcional(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                String texto = sc.nextLine().trim();

                if (texto.isBlank()) {
                    return null;
                }

                return Date.valueOf(texto);
            } catch (IllegalArgumentException e) {
                System.out.println("Fecha no válida. Usa el formato yyyy-mm-dd o deja vacío.");
            }
        }
    }

    public static boolean leerBoolean(String mensaje) {
        while (true) {
            System.out.print(mensaje + " (s/n): ");
            String respuesta = sc.nextLine().trim().toLowerCase(java.util.Locale.ROOT);

            if (respuesta.equals("s") || respuesta.equals("si") || respuesta.equals("sí")) {
                return true;
            }

            if (respuesta.equals("n") || respuesta.equals("no")) {
                return false;
            }

            System.out.println("Respuesta no válida. Escribe 's' o 'n'.");
        }
    }
}