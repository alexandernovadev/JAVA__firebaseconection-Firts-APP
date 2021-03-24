package com.aprendiendoando.firebasecloud;

public class ModeloDatos {

    private String ID;
    private String nombre;
    private String apellido;

    public ModeloDatos() {
    }

    public ModeloDatos(String nombre, String apellido) {

        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
