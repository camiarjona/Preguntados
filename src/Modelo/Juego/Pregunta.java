package Modelo.Juego;

import Interfaces.IJson;
import Modelo.Enum.Categoria;

public abstract class Pregunta implements IJson<Pregunta> {

    protected String enunciado;
    protected Categoria categoria;



}
