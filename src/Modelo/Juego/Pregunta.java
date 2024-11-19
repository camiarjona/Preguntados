package Modelo.Juego;

import Interfaces.IEvaluable;
import Interfaces.IJson;
import Interfaces.IObtener;
import Modelo.Enum.Categoria;

import java.util.Objects;

public abstract class Pregunta implements IJson, IEvaluable, IObtener {

    protected  static  int idAuto = 0;
    protected int id;
    protected String enunciado;
    protected Categoria categoria;

    public Pregunta(String enunciado, Categoria categoria) {
        this.id = idAuto++;
        this.enunciado = enunciado;
        this.categoria = categoria;
    }

    public Pregunta() {
        this.id = idAuto++;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String mostrarOpciones(){
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;
        return id == pregunta.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ID: " + id +
                " Enunciado: " + enunciado +
                " Categoria: " + categoria;
    }
}
