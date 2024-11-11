package Modelo.Juego;

import Excepciones.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;

public class PreguntaMultipleChoice extends Pregunta implements IEvaluable {

    private GestionDeElementos<String> opciones;
    private final String respuestaCorrecta = "";


    @Override
    public boolean evaluarRespuesta(String respuesta) throws RespuestaIncorrecta{
        if(!respuesta.equals(respuestaCorrecta)){
            throw new RespuestaIncorrecta("Respuesta incorrecta");
        }
        return true;
    }
}
