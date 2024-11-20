package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Modelo.Enum.Categoria;
import org.json.JSONException;
import org.json.JSONObject;

public class PreguntaVerdaderoOFalso extends Pregunta   {
    /// atributos
    private static final int puntajeBase = 10;
    private String respuestaCorrecta;

    /// constructores
    public PreguntaVerdaderoOFalso(String enunciado, Categoria categoria, String respuestaCorrecta) {
        super(enunciado, categoria);
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public PreguntaVerdaderoOFalso()
    {
        super();
    }

    /// metodo para evaluar respuesta vof
    @Override
    public boolean evaluarRespuesta(String respuesta) throws RespuestaIncorrecta {

        if(!respuestaCorrecta.equalsIgnoreCase(respuesta)){
            throw new RespuestaIncorrecta("\033[31m\uD83D\uDEAB Respuesta Incorrecta \uD83D\uDEAB\033[0m");
        }
        return true;
    }
    /// get y set
    @Override
    public int getPuntajeBase() {
        return puntajeBase;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    /// metodo para mostrar las opciones vof
    @Override
    public String mostrarOpciones() {
        StringBuilder op = new StringBuilder();

        op.append("1. Verdadero. \n 2. Falso");

        return op.toString();
    }

    /// metodo para convertir una pregunta vof a json object
    @Override
    public JSONObject toJson() {

        JSONObject  jsonObject = new JSONObject();
        try
        {
            jsonObject.put("Enunciado", enunciado);
            jsonObject.put("Categoria", categoria);
            jsonObject.put("RespuestaCorrecta", respuestaCorrecta);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }

    /// metodo para traer una pregunta vof desde el archivo json
    public static PreguntaVerdaderoOFalso jsonToObject(JSONObject jsonObject)
    {
        PreguntaVerdaderoOFalso nuevaPreguntaVoF = new PreguntaVerdaderoOFalso();
        try
        {
            String enunciado = jsonObject.getString("Enunciado");
            Categoria categoria = Categoria.valueOf(jsonObject.getString("Categoria"));
            String respuestaCorrecta = jsonObject.getString("RespuestaCorrecta");

            nuevaPreguntaVoF.setEnunciado(enunciado);
            nuevaPreguntaVoF.setCategoria(categoria);
            nuevaPreguntaVoF.setRespuestaCorrecta(respuestaCorrecta);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return nuevaPreguntaVoF;
    }
}
