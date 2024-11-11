package Excepciones;

public class CorreoDuplicado extends RuntimeException {
    public CorreoDuplicado(String message) {
        super(message);
    }
}
