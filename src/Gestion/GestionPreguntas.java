package Gestion;

import JSON.JSONUtiles;
import Modelo.Juego.Pregunta;
import org.json.JSONArray;
import org.json.JSONException;

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

    public void cargarPreguntasDesdeJson(String rutaArchivo) {
        try {
            // Leer el contenido del archivo JSON
            String contenido = JSONUtiles.leerArchivo(rutaArchivo);

            // Convertir el contenido en un JSONArray
            JSONArray jsonArray = new JSONArray(contenido);

            // Convertir el JSONArray en un objeto GestionPreguntas
            GestionPreguntas gestionPreguntas= toObj(jsonArray);

            // Copiar las preguntas cargadas a la lista de preguntas actual
            this.preguntas = gestionPreguntas.preguntas;

            System.out.println("Preguntas cargadas con éxito.");

        }catch (JSONException e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
        }
    }

    public void guardarPreguntas(String rutaArchivo) {
        JSONArray jsonArray = preguntasAJson();
        JSONUtiles.guardarJSONArray(jsonArray, rutaArchivo);
    }
}