package com.example.firebaseapp;

public class User {

    public String nombre;
    public String apellido;
    public int edad;
    public int telefono;
    public String direccion;
    public String foto;


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
    public int getEdad() {

        return edad;
    }
    public void setEdad(int edad)
    {
        this.edad = edad;
    }
    public int getTelefono() {

        return telefono;
    }
    public void setTelefono(int telefono) {

        this.telefono = telefono;
    }

    public String getDireccion() {

        return direccion;
    }
    public void setDireccion(String direccion) {

        this.direccion = direccion;
    }
    public String getFoto() {

        return foto;
    }
    public void setFoto(String foto) {

        this.foto = foto;
    }

    public User(String nombre, String apellido, int edad, int telefono, String direccion, String foto ) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.telefono = telefono;
        this.direccion = direccion;
        this.foto = foto;
    }


}
