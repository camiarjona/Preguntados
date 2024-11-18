package Modelo.Juego;

import Interfaces.IJson;
import org.json.JSONObject;

public class Puntaje implements IJson<Puntaje> {

    private int puntaje;


    public Puntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public Puntaje toObj() {
        return null;
    }
}
