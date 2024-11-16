package Excepciones;

public class UsuarioIncorrecto extends RuntimeException {
    public UsuarioIncorrecto(String message) {
        super(message);
    }
}
