package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Gestion.GestionPreguntas;
import Interfaces.IJson;
import Modelo.Enum.Categoria;
import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;
import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.*;

public class Ronda {

    private static final int nroMaxRondas = 4;
    private GestionPreguntas preguntas;
    private Puntaje puntaje;
    private Jugador jugador;

    //CONSTRUCTORES
    public Ronda(Jugador jugador, GestionPreguntas preguntas) {
        this.preguntas = preguntas;
        this.puntaje = new Puntaje();
        this.jugador = jugador;
    }

    public Puntaje obtenerPuntaje() {
        return puntaje;
    }


    // Metodo principal que controla el flujo de las rondas en el juego.

    public void jugarRonda(Scanner scanner) {
        System.out.println("¡Comienza el juego!");
        for (int rondaActual = 1; rondaActual <= nroMaxRondas; rondaActual++) {
            System.out.println("\nRonda " + rondaActual + " de " + nroMaxRondas);
            Categoria categoriaAux = obtenerCategoria(); // Selección de categoría
            System.out.println("Categoría seleccionada: " + categoriaAux);
            jugarPorCategoria(categoriaAux, scanner);
        }
        // Finaliza el juego mostrando el puntaje total y guardándolo en el historial del jugador
        System.out.println("\nFin del juego. Puntaje total acumulado: " + puntaje.getPuntaje());
        this.jugador.agregarPuntaje(puntaje); // Agregar puntaje al historial del jugador
    }


    public Categoria obtenerCategoria(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1; //esto es para que se genere un numero del 1 al 6, sin tener en cuenta el 0.
        return Categoria.obtenerCategoriaPorId(numeroAleatorio);
    }

    // Maneja las preguntas de una categoría específica
    private void jugarPorCategoria(Categoria categoria, Scanner scanner) {
        List<Pregunta> preguntasDesordenadas = desordenarPreguntas(); // Desordenar preguntas

        for (int preguntaActual = 1; preguntaActual < 4; preguntaActual++) {
            System.out.println("\nPregunta " + preguntaActual + " de 3 en la categoría " + categoria);
            procesarPreguntasPorCategoria(preguntasDesordenadas, categoria, scanner); // Procesar preguntas
        }
        System.out.println("Fin de la categoría: " + categoria);

    }

    // Desordena la lista de preguntas para añadir variedad al juego.
    private List<Pregunta> desordenarPreguntas() {
        List<Pregunta> preguntasDesordenadas = new ArrayList<>(preguntas.getPreguntas());
        Collections.shuffle(preguntasDesordenadas);
        return preguntasDesordenadas;
    }

    // Procesa las preguntas filtradas por categoría, mostrando y evaluando cada una.
    private void procesarPreguntasPorCategoria(List<Pregunta> preguntasDesordenadas, Categoria categoria, Scanner scanner) {
        int racha = 0;
        for (Pregunta preguntaAux : preguntasDesordenadas) {
            if (preguntaAux.categoria == categoria) {
                manejarPregunta(preguntaAux, racha, scanner);
            }
        }
    }

    // Maneja una pregunta específica: muestra el enunciado, obtiene la respuesta y la evalúa.
    private void manejarPregunta(Pregunta preguntaAux, int racha, Scanner scanner) {
        StringBuilder contenido = new StringBuilder();
        System.out.println(contenido.append(preguntaAux.getEnunciado())
                .append("\n")
                .append(preguntaAux.mostrarOpciones()));
        String respuesta = obtenerRespuestaDelJugador(preguntaAux, scanner);

        try {
            preguntaAux.evaluarRespuesta(respuesta); // Si pasa, la respuesta es correcta
            manejarRespuestaCorrecta(preguntaAux, racha);
        } catch (RespuestaIncorrecta e) {
            manejarRespuestaIncorrecta(racha, e);
        }
    }

    //obtener la respuesta seleccionada por el jugador a una pregunta, ya sea de tipo Multiple Choice o Verdadero o Falso.
    public String obtenerRespuestaDelJugador(Pregunta pregunta, Scanner scanner){
        System.out.println("\nIngrese la opcion correcta: ");
        int opcionRespuesta = scanner.nextInt();
        String respuesta = "";

        if(pregunta instanceof PreguntaMultipleChoice){
            respuesta = obtenerRespuestaMultipleChoice(pregunta, opcionRespuesta);
        } else if (pregunta instanceof PreguntaVerdaderoOFalso) {
            respuesta= obtenerRespuestaVoF(pregunta, opcionRespuesta);
        }
        return respuesta;
    }

    public String obtenerRespuestaMultipleChoice(Pregunta pregunta, int opcionRespuesta){
        ArrayList<String> opcionesAux = ((PreguntaMultipleChoice) pregunta).getOpciones().getElementos();
        return opcionesAux.get(opcionRespuesta-1);
    }


    public String obtenerRespuestaVoF(Pregunta pregunta, int opcionRespuesta){
        if(opcionRespuesta == 1){
            return "Verdadero";
        }
        else{
            return "Falso";
        }
    }


    // Procesa las respuestas correctas, calculando el puntaje y actualizando la racha.
    private void manejarRespuestaCorrecta(Pregunta preguntaAux, int racha) {
        System.out.println("¡Correcto! Calculando puntos...");
        if (racha != 0) {
            puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase() + 2);
            System.out.println("¡Bonus por racha! Puntos obtenidos: " + (preguntaAux.getPuntajeBase() + 2));
        } else {
            puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase());
            System.out.println("Puntos obtenidos: " + preguntaAux.getPuntajeBase());

        }
        racha++;
        System.out.println("Racha actual: " + racha + " | Puntaje acumulado: " + obtenerPuntaje());
    }


    // Procesa las respuestas incorrectas, aplicando penalización y reiniciando la racha.
    private void manejarRespuestaIncorrecta(int racha, RespuestaIncorrecta e) {
        System.out.println(e.getMessage());
        puntaje.setPuntaje(puntaje.getPuntaje() - 5);
        System.out.println("Penalización de 5 puntos. Puntaje acumulado: " + obtenerPuntaje());

        System.out.println("¡Racha perdida! Racha restablecida a 0.");
        racha = 0;
    }
}
