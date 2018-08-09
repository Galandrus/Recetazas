package ar.com.magapp.misrecetas.entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Receta implements Serializable {

    private String id;
    private String nombre;
    private String descripcion;
    private int foto;
    private String categoria;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<String> pasos;
    private ArrayList<String> tips;

    public Receta() {
    }

    public Receta(String nombre, String descripcion, int foto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
    }

    public Receta(String nombre, String descripcion, int foto, String categoria, ArrayList<Ingrediente> ingredientes, ArrayList<String> pasos, ArrayList<String> tips) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.foto = foto;
        this.categoria = categoria;
        this.ingredientes = ingredientes;
        this.pasos = pasos;
        this.tips = tips;

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

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public ArrayList<String> getPasos() {
        return pasos;
    }

    public void setPasos(ArrayList<String> pasos) {
        this.pasos = pasos;
    }

    public ArrayList<String> getTips() {
        return tips;
    }

    public void setTips(ArrayList<String> tips) {
        this.tips = tips;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
