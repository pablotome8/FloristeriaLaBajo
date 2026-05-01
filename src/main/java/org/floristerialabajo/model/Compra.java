package org.floristerialabajo.model;

import java.sql.Date;

public class Compra {

    private int idCompra;
    private int idTrabajador;
    private int idCliente;
    private String metodoPago;
    private Date fecha;
    private double importe;

    // Referencias opcionales para trabajar mejor en Java
    private Trabajador trabajador;
    private Cliente cliente;

    public Compra() {
    }

    public Compra(int idCompra, int idTrabajador, int idCliente, String metodoPago, Date fecha, double importe) {
        this.idCompra = idCompra;
        this.idTrabajador = idTrabajador;
        this.idCliente = idCliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.importe = importe;
    }

    public Compra(int idCompra, int idTrabajador, int idCliente, String metodoPago, Date fecha, double importe, Trabajador trabajador, Cliente cliente) {
        this.idCompra = idCompra;
        this.idTrabajador = idTrabajador;
        this.idCliente = idCliente;
        this.metodoPago = metodoPago;
        this.fecha = fecha;
        this.importe = importe;
        this.trabajador = trabajador;
        this.cliente = cliente;
    }

    public int getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(int idCompra) {
        this.idCompra = idCompra;
    }

    public int getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(int idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", idTrabajador=" + idTrabajador +
                ", idCliente=" + idCliente +
                ", metodoPago='" + metodoPago + '\'' +
                ", fecha=" + fecha +
                ", importe=" + importe +
                '}';
    }
}