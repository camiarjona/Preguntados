package Gestion;

import Excepciones.ElementoDuplicado;
import Excepciones.ElementoNoExiste;
import Interfaces.IJson;

import java.util.ArrayList;

public class GestionDeElementos <T>{
    //atributo
   private ArrayList<T> elementos;

   //Constructor
    public GestionDeElementos() {
        this.elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    /**
     * Este metodo agrega un elemento a la lista
     * @param t tipo
     * @return boolean
     * @throws ElementoDuplicado excepcion
     */
    public boolean agregarElemento(T t) throws ElementoDuplicado {
        if(!elementos.contains(t)){
             elementos.add(t);
             return true;
        }
        else{
            throw new ElementoDuplicado("Elemento duplicado, no se puede añadir");
        }
    }

    /**
     *
     * @param t
     * @return
     * @throws ElementoNoExiste
     */
    public boolean eliminarElemento (T t) throws ElementoNoExiste {
        boolean eliminado = false;
        if(t != null){
            if(elementos.remove(t)) {
                eliminado =  true;
            }
        }
        else{
            throw new ElementoNoExiste("El elemento no se encuentra en la lista");
        }
        return eliminado;
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

    public ArrayList<T> obtenerTodosLosElementos() {
        return new ArrayList<>(this.elementos);
    }
}

