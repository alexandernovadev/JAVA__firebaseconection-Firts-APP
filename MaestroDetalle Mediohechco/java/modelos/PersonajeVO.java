package com.aprendiendoando.maestrodetalle.entidades;

import java.io.Serializable;


// Serializable sirve para transportar el objeto
public class PersonajeVO implements Serializable {

    private String nombre;
    private String info;
    private String descripcion;

    private int imagenId;
    private int imagenDetalle;

    public PersonajeVO(String nombre, String info, String descripcion, int imagenId, int imagenDetalle) {
        this.nombre = nombre;
        this.info = info;
        this.descripcion = descripcion;
        this.imagenId = imagenId;
        this.imagenDetalle = imagenDetalle;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagenId() {
        return imagenId;
    }

    public void setImagenId(int imagenId) {
        this.imagenId = imagenId;
    }

    public int getImagenDetalle() {
        return imagenDetalle;
    }

    public void setImagenDetalle(int imagenDetalle) {
        this.imagenDetalle = imagenDetalle;
    }


}
