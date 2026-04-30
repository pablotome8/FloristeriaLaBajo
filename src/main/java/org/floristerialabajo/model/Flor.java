package org.floristerialabajo.model;

public class Flor {
    private int id_flor;
    private String nombre;
    private double precio;
    private int cantidad;

    public Flor() {
    }

    public Flor(int idFlor, String nombre, double precio, int cantidad) {
        this.id_flor = id_flor;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdFlor() {
        return id_flor;
    }

    public void setIdFlor(int idFlor) {
        this.id_flor = id_flor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Flor{" +
                "id_flor=" + id_flor +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}
