package Modelo.Juego;

import Excepciones.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import org.json.JSONObject;

public class PreguntaMultipleChoice extends Pregunta{

    private static final int puntajeBase = 20;
    private GestionDeElementos<String> opciones;
    private String respuestaCorrecta;

    public GestionDeElementos<String> getOpciones() {
        return opciones;
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
    public String mostrarOpciones(){
       StringBuilder op = new StringBuilder();
        int i = 1;
        for(String aux : opciones.getElementos()){
            op.append(i +". " + aux + "\n");
            i++;
        }
        return op.toString();
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public Pregunta toObj() {
        return null;
    }
}
