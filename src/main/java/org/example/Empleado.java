package org.example;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Empleado {

    private int id;
    private String nombre;
    private String genero;
    private String rol;
    private int edad;

    public Empleado(){}

    public Empleado(int id, String nombre, String genero, String rol,int edad) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.rol = rol;
        this.edad = edad;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", genero='" + genero + '\'' +
                ", edad='" + edad + '\'' +
                ", rol='" + rol + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return id == empleado.id && edad == empleado.edad && Objects.equals(nombre, empleado.nombre) && Objects.equals(genero, empleado.genero) && Objects.equals(rol, empleado.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, genero, rol, edad);
    }


}
