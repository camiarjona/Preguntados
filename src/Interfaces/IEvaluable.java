package Interfaces;

public interface IEvaluable {

    default boolean evaluarRespuesta(String respuesta){
        return false;
    }
    }
