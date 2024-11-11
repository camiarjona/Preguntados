package Modelo.Juego;

import Interfaces.IEvaluable;

public class PreguntaVerdaderoOFalso extends Pregunta implements IEvaluable {

    private boolean respuestaCorrecta;


    @Override
    public boolean evaluarRespuesta(String respuesta) {
        return false;
    }
}
