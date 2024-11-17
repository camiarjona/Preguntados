package Modelo.Usuario;

import Interfaces.IJson;
import org.json.JSONObject;

import java.util.Objects;

public class Usuario implements IJson<Usuario> {

    protected String nombreUsuario;
    protected String email;
    protected String contrasenia;

    ///Constructor
    public Usuario(String nombreUsuario, String email, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    ///Getter and Setter
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    //Sobreescritura
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) && Objects.equals(email, usuario.email) && Objects.equals(contrasenia, usuario.contrasenia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, email, contrasenia);
    }

    //Metodos
    @Override
    public JSONObject toJson() {
        return null;
    }


    @Override
    public Usuario toObj() {
        return null;
    }


}
