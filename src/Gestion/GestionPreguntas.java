package Gestion;

import Excepciones.ElementoNoExiste;
import JSON.JSONUtiles;
import Modelo.Juego.Pregunta;
import Modelo.Juego.PreguntaMultipleChoice;
import Modelo.Juego.PreguntaVerdaderoOFalso;
import Modelo.Usuario.Jugador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    //metodo para mostrar la lista de preguntas
    public String mostrarPreguntas() {
        StringBuilder sb = new StringBuilder();
        int i = 1;
        for (Pregunta pregunta : preguntas.getElementos()) {
            sb.append("Pregunta ").append(i).append(". ").append(pregunta.toString()).append("\n");
            i++;
        }
        return sb.toString();
    }

    //metodo para convertir mi lista de preguntas a formato json
    public JSONArray preguntasAJson(){
        JSONArray jsonArray = new JSONArray();
        for (Pregunta pregunta : preguntas.getElementos()) {
            jsonArray.put(pregunta.toJson());
        }
        return jsonArray;
    }

    //metodo para convertir mi jsonArray de preguntas a formato Gestion de preguntas
    public static GestionPreguntas toObj(JSONArray jsonArray) {
        GestionPreguntas preguntas = new GestionPreguntas();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                if(jsonObject.has("Opciones")){
                    PreguntaMultipleChoice pregmc = PreguntaMultipleChoice.jsonToObj(jsonObject);
                    preguntas.agregarPregunta(pregmc);
                }
                else{
                    PreguntaVerdaderoOFalso pregvof = PreguntaVerdaderoOFalso.jsonToObject(jsonObject);
                    preguntas.agregarPregunta(pregvof);
                }
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return preguntas;
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