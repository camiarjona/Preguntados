package Excepciones.Preguntas;

public class RespuestaIncorrecta extends RuntimeException {
    public RespuestaIncorrecta(String message) {
        super(message);
    }
}
