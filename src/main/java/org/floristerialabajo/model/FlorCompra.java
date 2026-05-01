package org.floristerialabajo.model;

public class FlorCompra {

    private int idCompra;
    private int idFlor;
    private int cantidad;

    // Referencias opcionales para uso en Java
    private Compra compra;
    private Flor flor;

    public FlorCompra() {
    }

    public FlorCompra(int idCompra, int idFlor, int cantidad) {
        this.idCompra = idCompra;
        this.idFlor = idFlor;
        this.cantidad = cantidad;
    }

    public FlorCompra(int idCompra, int idFlor, int cantidad, Compra compra, Flor flor) {
        this.idCompra = idCompra;
        this.idFlor = idFlor;
        this.cantidad = cantidad;
        this.compra = compra;
        this.flor = flor;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdFlor() {
        return idFlor;
    }

    public void setIdFlor(int idFlor) {
        this.idFlor = idFlor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

    @Override
    public String toString() {
        return "FlorCompra{" +
                "idCompra=" + idCompra +
                ", idFlor=" + idFlor +
                ", cantidad=" + cantidad +
                '}';
    }
}