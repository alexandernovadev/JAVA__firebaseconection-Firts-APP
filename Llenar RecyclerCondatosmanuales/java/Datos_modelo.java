package com.aprendiendoando.firebaseimageslist;

public class Datos_modelo {


    private String nombre;
    private String apellido;

    public Datos_modelo(String nombre, String apellido) {
        this.nombre = nombre;
        this.apellido = apellido;
    }


    public Datos_modelo() {

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
}
