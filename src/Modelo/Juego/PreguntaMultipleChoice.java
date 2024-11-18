package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import Modelo.Enum.Categoria;
import Modelo.Usuario.Jugador;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreguntaMultipleChoice extends Pregunta{

    private static final int puntajeBase = 20;
    private GestionDeElementos<String> opciones;
    private String respuestaCorrecta;

    public PreguntaMultipleChoice(String enunciado, Categoria categoria, String respuestaCorrecta) {
        super(enunciado, categoria);
        this.opciones = new GestionDeElementos<>();
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public PreguntaMultipleChoice() {
        super();
        this.opciones = new GestionDeElementos<>();
    }


    public void setOpciones(GestionDeElementos<String> opciones) {
        this.opciones = opciones;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public GestionDeElementos<String> getOpciones() {
        return opciones;
    }


    public void agregarOpcion(String opcion) {
        opciones.agregarElemento(opcion);
    }


    @Override
    public boolean evaluarRespuesta(String respuesta) throws RespuestaIncorrecta{
        if(!respuesta.equals(respuestaCorrecta)){
            throw new RespuestaIncorrecta("Respuesta incorrecta");
        }
        return true;
    }

    @Override
    public int getPuntajeBase() {
        return puntajeBase;
    }

    @Override
   public String mostrarOpciones(){
       StringBuilder op = new StringBuilder();
        int i = 1;
        for(String aux : opciones.getElementos()){
            op.append(i +". " + aux + "\n");
            i++;
        }
        return op.toString();
    }

    public JSONArray toJsonArray() {

            JSONArray jsonArray = new JSONArray();
            for (String opcion  : opciones.getElementos()) {
                jsonArray.put(opcion);
            }
            return jsonArray;
        }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        try{
            json.put("Opciones", toJsonArray());
            json.put("RespuestaCorrecta", respuestaCorrecta);
            json.put("Enunciado", enunciado);
            json.put("Categoria", categoria);

        }catch (JSONException e){
            e.printStackTrace();
        }
        return json;
    }

    public static PreguntaMultipleChoice jsonToObj(JSONObject json){

            PreguntaMultipleChoice objPregunta = new PreguntaMultipleChoice();

            try {
                JSONArray array = json.getJSONArray("Opciones");
                for(int i = 0; i < array.length(); i++){

                    String opcion = array.getString(i);
                    objPregunta.agregarOpcion(opcion);
                }

               objPregunta.setRespuestaCorrecta(json.getString("RespuestaCorrecta"));
                objPregunta.setCategoria(Categoria.valueOf(json.getString("Categoria")));
                objPregunta.setEnunciado(json.getString("Enunciado"));

            }catch (JSONException e) {
                e.printStackTrace();
            }
        return objPregunta;
    }


    }



