package com.mongomythos.mongo_mythos.domain;

import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

public class Mytho {
    //Variables
    private ObjectId id;
    private String nombre;
    private String genero;
    private String tipo;
    private String origen;
    //Constructores
    public Mytho(){

    }

    public Mytho(ObjectId id, String nombre, String genero, String tipo, String origen) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.origen = origen;
    }

    public Mytho(String nombre, String genero, String tipo, String origen) {
        this.nombre = nombre;
        this.genero = genero;
        this.tipo = tipo;
        this.origen = origen;
    }


    //Getter y setters
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        return "Mytho{" + nombre + genero + tipo + origen + '}';
    }
}
