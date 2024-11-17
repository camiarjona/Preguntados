package Excepciones.Usuario;

public class UsuarioIncorrecto extends RuntimeException {
    public UsuarioIncorrecto(String message) {
        super(message);
    }
}
