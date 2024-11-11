package Excepciones;

public class ContraseniaInconrrecta extends RuntimeException {
    public ContraseniaInconrrecta(String message) {
        super(message);
    }
}
