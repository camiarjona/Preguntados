package Gestion;

import Excepciones.ElementoDuplicado;
import Interfaces.IJson;

import java.util.ArrayList;

public class GestionDeElementos <T>{

   private ArrayList<T> elementos;

    public GestionDeElementos() {
        this.elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    /**
     * Agrega un elemento a la lista si no está presente previamente.
     * @param t el elemento que se desea agregar a la lista.
     * @throws ElementoDuplicado si el elemento ya se encuentra en la lista
     */
    public void agregarElemento(T t) throws ElementoDuplicado {
        if(elementos.contains(t)) {
            throw new ElementoDuplicado("El elemento ya se encuentra en la lista");
        }
        elementos.add(t);
    }


    public void eliminarElemento (T t) {
        if(elementos.contains(t)) {
            elementos.remove(t);
        }
        else{
            throw new IllegalArgumentException("El elemento no se encuentra en la lista");
        }
    }

    public T buscarElemento(T t) {
            if(elementos.isEmpty()) {
                throw new IllegalArgumentException("La lista no contiene elementos");
            }
            if(elementos.contains(t)) {
                return elementos.get(elementos.indexOf(t));
            }
            else{
                throw new IllegalArgumentException("El elemento no se encuentra en la lista");
            }
    }

    public ArrayList<T> obtenerTodosLosElementos() {
        return new ArrayList<>(this.elementos);
    }
}

