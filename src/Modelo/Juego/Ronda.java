package Modelo.Juego;

import Gestion.GestionDeElementos;
import Interfaces.IJson;
import Modelo.Usuario.Usuario;
import org.json.JSONObject;

public class Ronda implements IJson {

    private GestionDeElementos<Pregunta> preguntas;
    private int puntaje;

    @Override
    public JSONObject toJson() {
        return null;
    }

}
