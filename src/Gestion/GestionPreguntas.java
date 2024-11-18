package Gestion;

import Modelo.Juego.Pregunta;

import java.util.ArrayList;

public class GestionPreguntas {
    private GestionDeElementos<Pregunta> preguntas;

    public GestionPreguntas() {
        preguntas = new GestionDeElementos<>();
    }

    public void agregarPregunta(Pregunta pregunta) {
        preguntas.agregarElemento(pregunta);
    }

    public void eliminarPregunta(Pregunta pregunta) {
        preguntas.eliminarElemento(pregunta);
    }

    public void buscarPregunta(Pregunta pregunta) {
        preguntas.buscarElemento(pregunta);
    }

    public ArrayList<Pregunta> getPreguntas() {
        return preguntas.getElementos();
    }

    @Override
    public String toString() {
        return "Preguntas en la lista: \n" + preguntas.getElementos();
    }

    public String mostrarPreguntas() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Pregunta pregunta : preguntas.getElementos()) {
            sb.append(i).append(". ").append(pregunta.toString());
            i++;
        }
        return sb.toString();
    }

    public boolean eliminarPreguntaPorId(int id) {

        boolean eliminar = false;

        for (Pregunta p : preguntas.getElementos()) {
            if (p.getId() == id) {
                preguntas.eliminarElemento(p);
                eliminar = true;
            }

        }
        return eliminar;
    }
}