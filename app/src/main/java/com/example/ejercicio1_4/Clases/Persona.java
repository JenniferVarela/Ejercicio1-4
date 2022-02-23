package com.example.ejercicio1_4.Clases;

import java.sql.Blob;

public class Persona {
    private Integer id;
    private String nombre;
    private String descripcion;
    private byte[] foto;

    public Persona() {
    }

    public Persona(Integer id, String nombre, String descripcion, byte[] foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
