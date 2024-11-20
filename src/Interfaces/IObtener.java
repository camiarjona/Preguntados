package Interfaces;

public interface IObtener {

    /// firma metodo para obtener puntaje base
    default int getPuntajeBase() {
        return 0;
    }

}
