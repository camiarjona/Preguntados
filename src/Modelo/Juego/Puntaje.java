package Modelo.Juego;

import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.jar.JarException;

public class Puntaje implements IJson {

    private int puntaje;

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

   public static Puntaje fromJson(JSONObject j) {
        Puntaje puntaje = new Puntaje();
        try {
            puntaje.setPuntaje(j.getInt("Puntaje"));
        }catch (JSONException e) {
           e.printStackTrace();
        }
       return puntaje;
   }
}
