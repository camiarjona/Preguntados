package Modelo.Juego;

import Excepciones.RespuestaIncorrecta;
import Interfaces.IEvaluable;
import Interfaces.IJson;
import Interfaces.IObtener;
import Modelo.Enum.Categoria;

public abstract class Pregunta implements IJson<Pregunta> , IEvaluable, IObtener{

    protected String enunciado;
    protected Categoria categoria;

    public String getEnunciado() {
        return enunciado;
    }

    public String mostrarOpciones(){
        return "";
    }



}
