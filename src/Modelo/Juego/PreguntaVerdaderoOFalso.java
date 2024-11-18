package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import org.json.JSONObject;

public class PreguntaVerdaderoOFalso extends Pregunta {

    private static final int puntajeBase = 10;
    private String respuestaCorrecta;


    @Override
    public String mostrarOpciones() {
        StringBuilder op = new StringBuilder();

        op.append("1. Verdadero. \n 2. Falso");

        return op.toString();
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



    @Override
    public JSONObject toJson() {
        return null;
    }


}
