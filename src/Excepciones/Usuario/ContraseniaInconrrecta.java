package Excepciones.Usuario;

public class ContraseniaInconrrecta extends RuntimeException {
    public ContraseniaInconrrecta(String message) {
        super(message);
    }
}
