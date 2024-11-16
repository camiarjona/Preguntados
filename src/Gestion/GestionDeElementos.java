package Gestion;

import java.util.ArrayList;

public class GestionDeElementos <T>{

   private ArrayList<T> elementos;

    public GestionDeElementos() {
        this.elementos = new ArrayList<>();
    }

    public ArrayList<T> getElementos() {
        return elementos;
    }

    public void agregar(T t) {

            if(elementos.contains(t)) {

                //tirar excepcion

            }
            elementos.add(t);
    }

    public void eliminar(T t) {

        if(elementos.contains(t)) {

            //tirar excepcion

        }
        elementos.remove(t);
    }


    public void buscar(T t) {

            boolean flag = false;
            if(elementos.isEmpty()) {
                // tirar excepcion
            }

            for(  T elemento : elementos) {

                    if(elemento.equals(t)) {

                        //elemento encontrado mensajito y si se quiere retornar el elemento buscado
                        flag = true;
                    }
            }
            if(!flag) {

                /// exepcion con mensaje que no se encontro el elemento.

            }




    }



}
