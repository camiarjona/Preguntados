package Gestion;

import Excepciones.ElementoDuplicado;
import Excepciones.ElementoNoExiste;
import Interfaces.IJson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GestionDeElementos <T>{

   private Set<T> elementos;

    public GestionDeElementos() {
        this.elementos = new HashSet<T>();
    }

    public Set<T> getElementos() {
        return elementos;
    }

    /**
     * Agrega un elemento a la lista si no está presente previamente.
     * @param t el elemento que se desea agregar a la lista.
     */
    public boolean agregarElemento(T t) throws ElementoDuplicado {
        if(elementos.add(t)){
            return true;
        }
        else{
            throw new ElementoDuplicado("Elemento duplicado, no se puede añadir");
        }
    }

    public boolean eliminarElemento (T t) throws ElementoNoExiste {
        if(elementos.remove(t)) {
            return true;
        }
        else{
            throw new ElementoNoExiste("El elemento no se encuentra en la lista");
        }
    }

    public boolean buscarElemento(T t) throws ElementoNoExiste {
            if(elementos.isEmpty()) {
                throw new IllegalArgumentException("La lista no contiene elementos");
            }
            if(elementos.contains(t)) {
                return true;
            }
            else{
                throw new ElementoNoExiste("El elemento no se encuentra en la lista");
            }
    }
}

