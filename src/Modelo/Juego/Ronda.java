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

    private static final int nroMaxRondas = 3;
    private GestionPreguntas preguntas;
    private Puntaje puntaje;
    private Jugador jugador;

    //CONSTRUCTORES
    public Ronda(Jugador jugador, GestionPreguntas preguntas) {
        this.preguntas = preguntas;
        this.puntaje = new Puntaje(0);
        this.jugador = jugador;
    }

    public Ronda() {
        this.puntaje = new Puntaje(0);

    }

    public void setPreguntas(GestionPreguntas preguntas) {
        this.preguntas = preguntas;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    public int obtenerPuntaje() {
        return puntaje.getPuntaje();
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
        desordenarPreguntas();
        int racha = 0;
    // Desordenar preguntas
        for (int preguntaActual = 1; preguntaActual < 4; preguntaActual++) {
            System.out.println("\nPregunta " + preguntaActual + " de 3 en la categoría " + categoria);
            racha = procesarPreguntasPorCategoria(preguntas.getPreguntas(), categoria, scanner, racha); // Procesar preguntas
        }
        System.out.println("Fin de la categoría: " + categoria);

    }

    // Desordena la lista de preguntas para añadir variedad al juego.
    private void desordenarPreguntas() {
       // List<Pregunta> preguntasDesordenadas = preguntas.getPreguntas();
        Collections.shuffle(preguntas.getPreguntas());
    }

    // Procesa las preguntas filtradas por categoría, mostrando y evaluando cada una.
    private int procesarPreguntasPorCategoria(List<Pregunta> preguntasDesordenadas, Categoria categoria, Scanner scanner, int racha) {
        int i = 0;
            for (Pregunta preguntaAux : preguntasDesordenadas) {
                    while(i < 3) {
                    if (preguntaAux.categoria == categoria) {
                        racha = manejarPregunta(preguntaAux, racha, scanner);
                        i++;
                    }
                }
            }
            return racha;
    }

    // Maneja una pregunta específica: muestra el enunciado, obtiene la respuesta y la evalúa.
    private int manejarPregunta(Pregunta preguntaAux, int racha, Scanner scanner) {
        StringBuilder contenido = new StringBuilder();
        System.out.println(contenido.append(preguntaAux.getEnunciado())
                .append("\n")
                .append(preguntaAux.mostrarOpciones()));
        String respuesta = obtenerRespuestaDelJugador(preguntaAux, scanner);

        try {
            preguntaAux.evaluarRespuesta(respuesta); // Si pasa, la respuesta es correcta
            racha = manejarRespuestaCorrecta(preguntaAux, racha);
        } catch (RespuestaIncorrecta e) {
            racha = manejarRespuestaIncorrecta(racha, e);
        }

        return racha;
    }

    //obtener la respuesta seleccionada por el jugador a una pregunta, ya sea de tipo Multiple Choice o Verdadero o Falso.
    public String obtenerRespuestaDelJugador(Pregunta pregunta, Scanner scanner){
        System.out.println("\nIngrese la opcion correcta: ");
        int opcionRespuesta = -1;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                opcionRespuesta = scanner.nextInt();

                // Validar si la opción ingresada está dentro del rango permitido
                if (pregunta instanceof PreguntaMultipleChoice) {
                    int totalOpciones = ((PreguntaMultipleChoice) pregunta).getOpciones().getElementos().size();
                    if (opcionRespuesta < 1 || opcionRespuesta > totalOpciones) {
                        throw new IllegalArgumentException("La opción ingresada no está dentro del rango permitido.\nIngrese la opcion correcta: ");
                    }
                } else if (pregunta instanceof PreguntaVerdaderoOFalso) {
                    if (opcionRespuesta < 1 || opcionRespuesta > 2) { // 1 para Verdadero, 2 para Falso
                        throw new IllegalArgumentException("La opción ingresada no está dentro del rango permitido.\nIngrese la opcion correcta: ");
                    }
                }

                entradaValida = true; // Si no se lanza ninguna excepción, la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Error: Entrada inválida. Por favor, Ingrese la opcion correcta: ");
                scanner.next(); // Consumir la entrada inválida
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

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
    private int manejarRespuestaCorrecta(Pregunta preguntaAux, int racha) {
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

        return racha;
    }


    // Procesa las respuestas incorrectas, aplicando penalización y reiniciando la racha.
    private int manejarRespuestaIncorrecta(int racha, RespuestaIncorrecta e) {
        System.out.println(e.getMessage());
        puntaje.setPuntaje(puntaje.getPuntaje() - 5);
        System.out.println("Penalización de 5 puntos. Puntaje acumulado: " + obtenerPuntaje());
        if(racha != 0){
            System.out.println("¡Racha perdida! Racha restablecida a 0.");
            racha = 0;
        }

        return racha;
    }


}
