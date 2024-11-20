package Gestion;

import Excepciones.ElementoDuplicado;
import Excepciones.ElementoNoExiste;

import java.util.ArrayList;

public class GestionDeElementos <T>{
    ///atributo
   private ArrayList<T> elementos;

   ///constructor
    public GestionDeElementos() {
        this.elementos = new ArrayList<>();
    }

    ///metodo para obtener la lista de elementos
    public ArrayList<T> getElementos() {
        return elementos;
    }

   ///metodo para agregar un elemento a la lista
    public boolean agregarElemento(T t) throws ElementoDuplicado {
        if(!elementos.contains(t)){
             elementos.add(t);
             return true;
        }
        else{
            throw new ElementoDuplicado("Elemento duplicado, no se puede añadir");
        }
    }

   ///metodo para eliminar un elemento de la lista
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

    ///metodo para buscar un elemento
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

