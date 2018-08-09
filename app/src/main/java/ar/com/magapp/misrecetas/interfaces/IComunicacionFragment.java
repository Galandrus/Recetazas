package ar.com.magapp.misrecetas.interfaces;

import ar.com.magapp.misrecetas.entidades.Receta;

public interface IComunicacionFragment {

    void enviarReceta(Receta receta);
    void enviarCategoria(String Categoria, String idCategoria);

}
