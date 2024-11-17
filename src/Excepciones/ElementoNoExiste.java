package Excepciones;

public class ElementoNoExiste extends RuntimeException {
    public ElementoNoExiste(String message) {
        super(message);
    }
}
