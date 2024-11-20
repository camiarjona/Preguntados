package Interfaces;

import Modelo.Usuario.Usuario;
import org.json.JSONObject;

public interface IJson {

    ///firma metodo para convertir a json los objetos
    JSONObject toJson();

}
