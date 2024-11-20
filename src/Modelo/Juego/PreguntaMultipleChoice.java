package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Modelo.Enum.Categoria;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PreguntaMultipleChoice extends Pregunta{
    /// atributos
    private static final int puntajeBase = 20;
    private GestionDeElementos<String> opciones;
    private String respuestaCorrecta;

    /// constructores
    public PreguntaMultipleChoice(String enunciado, Categoria categoria, String respuestaCorrecta) {
        super(enunciado, categoria);
        this.opciones = new GestionDeElementos<>();
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public PreguntaMultipleChoice() {
        super();
        this.opciones = new GestionDeElementos<>();
    }

    /// set y get
    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public GestionDeElementos<String> getOpciones() {
        return opciones;
    }

    /// metodo para agregar opciones a la lista
    public void agregarOpcion(String opcion) {
        opciones.agregarElemento(opcion);
    }

    /// metodo para evaluar respuesta
    @Override
    public boolean evaluarRespuesta(String respuesta) throws RespuestaIncorrecta{
        if(!respuesta.equals(respuestaCorrecta)){
            throw new RespuestaIncorrecta("\033[31m\uD83D\uDEAB Respuesta Incorrecta \uD83D\uDEAB\033[0m");
        }
        return true;
    }

    /// metodo para obtener puntaje base
    @Override
    public int getPuntajeBase() {
        return puntajeBase;
    }

    /// metodo para mostrar opciones
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

    ///  metodo para convertir la lista de opciones en un json array
    public JSONArray toJsonArray() {

            JSONArray jsonArray = new JSONArray();
            for (String opcion  : opciones.getElementos()) {
                jsonArray.put(opcion);
            }
            return jsonArray;
        }

        /// metodo para convertir una pregunta mc a json object
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

    /// metodo para traer una pregunta mc del archivo json
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



