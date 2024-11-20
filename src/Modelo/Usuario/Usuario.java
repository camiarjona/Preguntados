package Modelo.Usuario;

import java.util.Objects;

public class Usuario {
    /// atributos
    protected String nombreUsuario;
    protected String email;
    protected String contrasenia;

    /// constructores
    public Usuario(String nombreUsuario, String email, String contrasenia) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.contrasenia = contrasenia;
    }

    public Usuario() {
    }

    /// get y set
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /// equals
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(nombreUsuario, usuario.nombreUsuario) || Objects.equals(email, usuario.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombreUsuario, email);
    }

    /// to string
    @Override
    public String toString() {
        return "Usuario: " +
                "\nEmail: " + email +
                "\nNombre de usuario: " + nombreUsuario;
    }
}
