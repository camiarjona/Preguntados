package Modelo.Juego;

import Excepciones.Preguntas.RespuestaIncorrecta;
import Gestion.GestionPreguntas;
import Modelo.Enum.Categoria;
import Modelo.Usuario.Jugador;

import java.util.*;

public class Juego {

    private static final int nroMaxRondas = 3;
    private GestionPreguntas preguntas;
    private Puntaje puntaje;
    private Jugador jugador;

    //CONSTRUCTORES
    public Juego(Jugador jugador, GestionPreguntas preguntas) {
        this.preguntas = preguntas;
        this.puntaje = new Puntaje(0);
        this.jugador = jugador;
    }

    public Juego() {
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
    public void iniciarJuego(Scanner scanner) {
        System.out.println("\n\u001B[42m\u001B[30m\uD83C\uDFC1\u001B[1m ¡Comienza el juego! \uD83C\uDFC1\u001B[0m");
        for (int rondaActual = 1; rondaActual <= nroMaxRondas; rondaActual++) {
            System.out.println("\n\033[43m\033[30m Ronda " + rondaActual + " de " + nroMaxRondas + " \u001B[0m");
            Categoria categoriaAux = obtenerCategoria(); // Selección de categoría
            System.out.println("\uD83D\uDD0D Categoría seleccionada: " + categoriaAux);
            jugarPorCategoria(categoriaAux, scanner);
        }
        // Finaliza el juego mostrando el puntaje total y guardándolo en el historial del jugador
        System.out.println("\n\u001B[47m\u001B[30m\uD83D\uDEA8 Fin del juego. Puntaje total acumulado: " + puntaje.getPuntaje() + " \uD83D\uDEA8\u001B[0m");
        this.jugador.agregarPuntaje(puntaje);// Agregar puntaje al historial del jugador

        puntaje = new Puntaje(0);
    }

    //metodo para obtener una categoria aleatoria
    public Categoria obtenerCategoria(){
        Random random = new Random();
        int numeroAleatorio = random.nextInt(6) + 1; //esto es para que se genere un numero del 1 al 6, sin tener en cuenta el 0.
        return Categoria.obtenerCategoriaPorId(numeroAleatorio);
    }

    //metodo para manejar las preguntas de una categoria especifica
    private void jugarPorCategoria(Categoria categoria, Scanner scanner) {
        desordenarPreguntas();
        int racha = 0;
        // Filtramos las preguntas para la categoría seleccionada
        List<Pregunta> preguntasFiltradas = new ArrayList<>();
        for (Pregunta pregunta : preguntas.getPreguntas()) {
            if (pregunta.categoria == categoria) {
                preguntasFiltradas.add(pregunta);
            }
        }

        //limitamos a solo 3 preguntas por ronda
        for (int preguntaActual = 0; preguntaActual < 3 && preguntaActual < preguntasFiltradas.size(); preguntaActual++) {
            System.out.println("\n\uD83D\uDCA1 Pregunta " + (preguntaActual + 1) + " de 3 en la categoría " + categoria + "\n");
            racha = manejarPregunta(preguntasFiltradas.get(preguntaActual), racha, scanner); // Procesa la pregunta
        }

        System.out.println("\uD83D\uDD62 Fin de la categoría: " + categoria + " \uD83D\uDD62");

    }

    // Desordena la lista de preguntas para añadir variedad al juego.
    private void desordenarPreguntas() {
        Collections.shuffle(preguntas.getPreguntas());
    }

    // Procesa las preguntas filtradas por categoría, mostrando y evaluando cada una.
    private int procesarPreguntasPorCategoria(Categoria categoria, Scanner scanner, int racha) {
        List<Pregunta> preguntasFiltradas = new ArrayList<>();
        for(Pregunta pregunta : preguntas.getPreguntas()) {
            if(pregunta.categoria == categoria) {
                preguntasFiltradas.add(pregunta);
            }
        }

        //Procesamos solo las preguntas de la categoria seleccionada
        for(Pregunta preguntaAux : preguntasFiltradas) {
            racha = manejarPregunta(preguntaAux, racha, scanner);
        }
        return racha;
    }

    // Maneja una pregunta específica: muestra el enunciado, obtiene la respuesta y la evalúa.
    private int manejarPregunta(Pregunta preguntaAux, int racha, Scanner scanner) {
        System.out.println(preguntaAux.getEnunciado());
        System.out.println(preguntaAux.mostrarOpciones());
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
        System.out.println("\n\uD83D\uDC47 Ingrese la opcion correcta: ");
        int opcionRespuesta = -1;
       while (true){
           try{
               System.out.println("Opciones disponibles: " + obtenerMaxOpciones(pregunta));
               opcionRespuesta = scanner.nextInt();
               if(opcionRespuesta < 1 || opcionRespuesta > obtenerMaxOpciones(pregunta)){
                   throw new IllegalArgumentException("La opcion ingresada no es valida");
               }
               break;
           }catch(InputMismatchException | IllegalArgumentException e) {
               System.out.println("Error: " + e.getMessage() + "\n\uD83D\uDC47 Ingrese la opcion correcta: ");
               scanner.next();
           }
       }
       return obtenerRespuesta(pregunta, opcionRespuesta);
    }

    //metodo general para obtener respuesta
    private String obtenerRespuesta(Pregunta pregunta, int opcionRespuesta) {
        if(pregunta instanceof PreguntaMultipleChoice){
            return ((PreguntaMultipleChoice) pregunta).getOpciones().getElementos().get(opcionRespuesta-1);
        }else if(pregunta instanceof PreguntaVerdaderoOFalso){
            return (opcionRespuesta == 1) ? "Verdadero" : "Falso";
        }
        return "";
    }

    //metodo para obtener la cantidad maxima de opciones disponibles por pregunta
    private int obtenerMaxOpciones(Pregunta pregunta) {
        if(pregunta instanceof PreguntaMultipleChoice){
            return ((PreguntaMultipleChoice) pregunta).getOpciones().getElementos().size();
        }else if(pregunta instanceof PreguntaVerdaderoOFalso){
            return 2; //verdadero o falso
        }
        throw new IllegalArgumentException("Tipo de pregunta no reconocido");
    }

    //Procesa las respuestas correctas, calculando el puntaje y actualizando la racha.
    private int manejarRespuestaCorrecta(Pregunta preguntaAux, int racha) {
        System.out.println("\u001B[32m¡Correcto! Calculando puntos...\u001B[0m");
        if (racha != 0) {
            puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase() + 2);
            System.out.println("\uD83D\uDCA3¡Bonus por racha! Puntos obtenidos: " + (preguntaAux.getPuntajeBase() + 2) + "\uD83D\uDCA3");
        } else {
            puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase());
            System.out.println("Puntos obtenidos: " + preguntaAux.getPuntajeBase());

        }
        racha++;
        System.out.println("Racha actual: " + racha + " | Puntaje acumulado: " + obtenerPuntaje());

        return racha;
    }


    //Procesa las respuestas incorrectas, aplicando penalización y reiniciando la racha.
    private int manejarRespuestaIncorrecta(int racha, RespuestaIncorrecta e) {
        System.out.println(e.getMessage());
        puntaje.setPuntaje(puntaje.getPuntaje() - 5);
        System.out.println("\u001B[31mPenalización de 5 puntos. \u001B[0m Puntaje acumulado: " + obtenerPuntaje());
        if(racha != 0){
            System.out.println("\u001B[31m¡Racha perdida! ☹\uFE0F\u001B[0m Racha restablecida a 0.");
            racha = 0;
        }

        return racha;
    }


}
