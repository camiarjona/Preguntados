package Gestion;

import Excepciones.ElementoNoExiste;
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
                }
                else{
                    PreguntaVerdaderoOFalso pregvof = PreguntaVerdaderoOFalso.jsonToObject(jsonObject);
                }

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return preguntas;
    }



}