package Excepciones.Usuario;

public class CorreoExistente extends RuntimeException {
    public CorreoExistente(String message) {
        super(message);
    }
}
