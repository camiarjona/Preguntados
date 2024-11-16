package Modelo.Usuario;

import Interfaces.IJson;
import org.json.JSONObject;

public class Usuario implements IJson<Usuario> {

    protected String nombreUsuario;
    protected String email;
    protected String contrasenia;

    public Usuario(String nombreUsuario, String email, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public Usuario toObj() {
        return null;
    }
}
