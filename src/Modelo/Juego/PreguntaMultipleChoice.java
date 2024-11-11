package Modelo.Juego;

import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;

public class PreguntaMultipleChoice extends Pregunta implements IEvaluable {

    private GestionDeElementos<String> opciones;
    private final String respuestaCorrecta = "";


    @Override
    public boolean evaluarRespuesta(String respuesta) {
        return false;
    }
}
