package Gestion;

import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;

public class GestionUsuario {

    private GestionDeElementos<Usuario> usuarios;

    public GestionUsuario() {
        usuarios = new GestionDeElementos<>();
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.agregarElemento(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.eliminarElemento(usuario);
    }

    public boolean buscarUsuario(Usuario usuario) {
        return usuarios.buscarElemento(usuario);
    }

    // Método login
    public boolean verificarLogin(String nombreUsuario, String contrasenia) throws UsuarioIncorrecto, ContraseniaInconrrecta {
        for (Usuario usuario : usuarios.getElementos()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                // Si el nombre de usuario es correcto, verifico la contraseña
                if (usuario.getContrasenia().equals(contrasenia)) {
                    return true; //Si ambos coinciden, retorno verdadero
                } else {
                    throw new ContraseniaInconrrecta("Contraseña incorrecta.");
                }
            }
        }
        // Si sale del bucle, significa que el usuario es incorrecto
        throw new UsuarioIncorrecto("Nombre de usuario incorrecto.");
    }

    public void existeUsuario(String nombreUsuario) throws UsuarioExistente {
        for (Usuario usuario : usuarios.getElementos()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                throw new UsuarioExistente("\n\u001B[31mNombre de usuario existente.\u001B[0m");
            }
        }
    }

    public void existeMail(String mail) throws CorreoExistente {
        for (Usuario usuario : usuarios.getElementos()) {
            if (usuario.getEmail().equalsIgnoreCase(mail)) {
                throw new CorreoExistente("\n\u001B[31mEl correo ya se encuentra registrado.\u001B[0m");
            }
        }
    }


    //metodo para obtener el jugador una vez inciada la sesion
    public Jugador obtenerJugadorPorNombre(String nombreUsuario) throws UsuarioIncorrecto {
        for (Usuario usuario : usuarios.getElementos()) {
            if (usuario instanceof Jugador && usuario.getNombreUsuario().equals(nombreUsuario)) {
                return (Jugador) usuario;
            }
        }
        throw new UsuarioIncorrecto("El usuario no existe.");
    }






}
