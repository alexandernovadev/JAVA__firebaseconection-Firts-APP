package com.aprendiendoando.subirimagenesfirebase;

import java.io.Serializable;

public class ModeloDatos implements Serializable
{

    private String ID;
    private String nombre;
    private String apellido;
    private String urlimagen;
    private String nameimagen;

    public ModeloDatos() {
    }


    public ModeloDatos(String ID,
                       String nombre,
                       String apellido,
                       String urlimagen,
                       String nameimagen) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlimagen = urlimagen;
        this.nameimagen = nameimagen;
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

    public String getUrlimagen() {
        return urlimagen;
    }

    public void setUrlimagen(String urlimagen) {
        this.urlimagen = urlimagen;
    }

    public String getNameimagen() {
        return nameimagen;
    }

    public void setNameimagen(String nameimagen) {
        this.nameimagen = nameimagen;
    }
}
