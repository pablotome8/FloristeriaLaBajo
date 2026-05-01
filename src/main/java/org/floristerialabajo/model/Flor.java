package org.floristerialabajo.model;

public class Flor {

    private int idFlor;
    private String nombre;
    private double precio;
    private int cantidad;

    public Flor() {
    }

    public Flor(int idFlor, String nombre, double precio, int cantidad) {
        this.idFlor = idFlor;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public int getIdFlor() {
        return idFlor;
    }

    public void setIdFlor(int idFlor) {
        this.idFlor = idFlor;
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
                "idFlor=" + idFlor +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                '}';
    }
}