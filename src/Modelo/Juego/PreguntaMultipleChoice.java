package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import org.json.JSONObject;

public class PreguntaMultipleChoice extends Pregunta implements IEvaluable, IObtener {

    private static final int puntajeBase = 20;
    private GestionDeElementos<String> opciones;
    private String respuestaCorrecta;


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

    @Override
    public JSONObject toJson() {
        return null;
    }


}
