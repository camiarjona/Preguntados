package Modelo.Juego;

import Gestion.GestionDeElementos;
import Modelo.Usuario.Jugador;

public class JuegoPreguntas {

    private GestionDeElementos<Ronda> rondas;
    private Jugador jugador;

    //CONSTRUCTOR


    public JuegoPreguntas(Jugador jugador) {
        this.jugador = jugador;
        this.rondas = new GestionDeElementos<Ronda>();
    }

    //hacer metodo para agregar puntaje al finalizar la ronda
}
