package org.floristerialabajo.model;
import java.sql.Date;

public class Trabajador {
    private int idTrabajador;
    private String dni;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String email;
    private Date fechaContratacion;
    private Date fechaBaja;

    public Trabajador() {}

    public Trabajador(int idTrabajador, String dni, String nombre, String apellidos, String telefono, String email, Date fechaContratacion, Date fechaBaja) {
        this.idTrabajador = idTrabajador;
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.fechaContratacion = fechaContratacion;
        this.fechaBaja = fechaBaja;
    }

    // Getters y Setters necesarios
    public int getIdTrabajador() { return idTrabajador; }
    public void setIdTrabajador(int idTrabajador) { this.idTrabajador = idTrabajador; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getFechaContratacion() { return fechaContratacion; }
    public void setFechaContratacion(Date fechaContratacion) { this.fechaContratacion = fechaContratacion; }
    public Date getFechaBaja() { return fechaBaja; }
    public void setFechaBaja(Date fechaBaja) { this.fechaBaja = fechaBaja; }

    @Override
    public String toString() {
        return "Trabajador{" + "id=" + idTrabajador + ", dni='" + dni + '\'' + ", nombre='" + nombre + '\'' + '}';
    }
}