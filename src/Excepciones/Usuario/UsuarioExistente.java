package Excepciones.Usuario;

public class UsuarioExistente extends RuntimeException {
    public UsuarioExistente(String message) {
        super(message);
    }
}
