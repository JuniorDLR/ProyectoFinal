/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author Junior de la Rocha
 */
public class Cliente {
    private int id;
    private String cedular;
    private String nombre;
    private String apellido;
    private String celular;
    private int estado;

    public Cliente() {
    }

    public Cliente(int id, String cedular, String nombre, String apellido, String celular, int estado) {
        this.id = id;
        this.cedular = cedular;
        this.nombre = nombre;
        this.apellido = apellido;
        this.celular = celular;
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedular() {
        return cedular;
    }

    public void setCedulr(String cedula) {
        this.cedular = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", cedula=" + cedular + ", nombre=" + nombre + ", apellido=" + apellido + ", celular=" + celular + ", estado=" + estado + '}';
    }

    public boolean verificarCodigoR(int codigo)
    {
        return this.id == codigo;
    }
 
    
}
