package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import Modelo.Enum.Categoria;
import org.json.JSONException;
import org.json.JSONObject;

public class PreguntaVerdaderoOFalso extends Pregunta   {

    private static final int puntajeBase = 10;
    private String respuestaCorrecta;

    public PreguntaVerdaderoOFalso(String enunciado, Categoria categoria, String respuestaCorrecta) {
        super(enunciado, categoria);
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public PreguntaVerdaderoOFalso()
    {
        super();
    }

    @Override
    public boolean evaluarRespuesta(String respuesta) throws RespuestaIncorrecta {

        if(!respuestaCorrecta.equalsIgnoreCase(respuesta)){
            throw new RespuestaIncorrecta("Respuesta Incorrecta");
        }
        return true;
    }


    @Override
    public int getPuntajeBase() {
        return puntajeBase;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String mostrarOpciones() {
        StringBuilder op = new StringBuilder();

        op.append("1. Verdadero. \n 2. Falso");

        return op.toString();
    }


    @Override
    public JSONObject toJson() {

        JSONObject  jsonObject = new JSONObject();
        try
        {
            jsonObject.put("Enunciado", enunciado);
            jsonObject.put("Categoria", categoria);
            jsonObject.put("Respuesta Correcta", respuestaCorrecta);

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static PreguntaVerdaderoOFalso jsonToObject(JSONObject jsonObject)
    {
        PreguntaVerdaderoOFalso nuevaPreguntaVoF = new PreguntaVerdaderoOFalso();
        try
        {
            String enunciado = jsonObject.getString("Enunciado");
            Categoria categoria = Categoria.valueOf(jsonObject.getString("Categoria"));
            String respuestaCorrecta = jsonObject.getString("Respuesta Correcta");

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
