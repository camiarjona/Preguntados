package Modelo.Usuario;

import Interfaces.IJson;
import org.json.JSONObject;

import java.util.Objects;

public class Usuario implements IJson<Usuario> {

    protected String nombreUsuario;
    protected String email;
    protected String contrasenia;

    public Usuario(String nombreUsuario, String email, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Usuario usuario = (Usuario) object;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(contrasenia, usuario.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, contrasenia);
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
