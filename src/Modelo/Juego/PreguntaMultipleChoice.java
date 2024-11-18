package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IEvaluable;
import Interfaces.IObtener;
import Modelo.Enum.Categoria;
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

    @Override
    public JSONObject toJson() {
        return null;
    }


}
