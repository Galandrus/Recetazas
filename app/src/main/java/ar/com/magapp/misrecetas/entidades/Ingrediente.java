package ar.com.magapp.misrecetas.entidades;

public class Ingrediente {

    private String nombre;
    private String cant;

    public Ingrediente() {
    }

    public Ingrediente(String nombre, String cant) {
        this.nombre = nombre;
        this.cant = cant;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCant() {
        return cant;
    }

    public void setCant(String cant) {
        this.cant = cant;
    }
}

