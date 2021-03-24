package com.aprendiendoando.firebaseandroid.models;


// Este es el metodo constructor, que poco a poco ire aprendiendo
//  se declaran las caracteristicas del objeto, que en este caso son los string de anajo

// Despues se creo los getter a setter. que es donde se obtienen los nombres y se colocan

// El metodo String nose para que sirve jajajja :(
public class persona {

    // Firebase arroja error si estas varibles son publicas asi que las colocamos privadas
    private String uid;
    private String Nombre;
    private String Apellido;
    private String Correo;
    private String Password;


    public persona() {

    }

    public persona(String uid, String nombre, String apellido, String correo, String password) {
        this.uid = uid;
        Nombre = nombre;
        Apellido = apellido;
        Correo = correo;
        Password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return Nombre;
    }
}
