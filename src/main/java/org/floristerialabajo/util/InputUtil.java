package org.floristerialabajo.util;

import java.util.Scanner;

public class InputUtil {

    private static final Scanner sc = new Scanner(System.in);

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine();
    }

    public static int leerEntero(String mensaje) {
        System.out.print(mensaje);
        return Integer.parseInt(sc.nextLine());
    }

    public static double leerDouble(String mensaje) {
        System.out.print(mensaje);
        return Double.parseDouble(sc.nextLine());
    }
}