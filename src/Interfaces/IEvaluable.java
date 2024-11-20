package Interfaces;

public interface IEvaluable {

    /// firma metodo para evaluar respuesta del jugador
    default boolean evaluarRespuesta(String respuesta){
        return false;
    }
    }
