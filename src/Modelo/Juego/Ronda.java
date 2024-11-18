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

    //  METODO PARA JUGAR RONDA
    public void jugarRonda(){
        Categoria categoriaAux;
        String mensaje = "";
        StringBuilder contenido = new StringBuilder();

        for(int j = 0; j < nroMaxRondas ; j++){
            categoriaAux = obtenerCategoria();

            for (int i = 1; i < 4; i++) {
                List<Pregunta> preguntasDesordenadas = new ArrayList<>(preguntas.getPreguntas());
                Collections.shuffle(preguntasDesordenadas);
                int racha = 0;

                for (Pregunta preguntaAux : preguntasDesordenadas) {

                    if (preguntaAux.categoria == categoriaAux) {
                        System.out.println(contenido.append(preguntaAux.getEnunciado()).append("\n").append(preguntaAux.mostrarOpciones()).append("\nIngrese la opcion correcta: "));
                        System.out.println(contenido.toString()); //Se imprime por aca porque ya retornamos un mensaje
                        String respuesta = obtenerRespuestaDelJugador(preguntaAux);

                        try {
                            if (preguntaAux.evaluarRespuesta(respuesta)) {
                                if(racha != 0){
                                    puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase() + 2);
                                }else {
                                    puntaje.setPuntaje(puntaje.getPuntaje() + preguntaAux.getPuntajeBase());
                                }
                                racha++;
                                System.out.println("¡Respuesta correcta!\n Su puntaje es: " + obtenerPuntaje() + "con una racha de: " + racha + "preguntas correctas!");

                            }
                        } catch (RespuestaIncorrecta e) {
                            puntaje.setPuntaje(puntaje.getPuntaje() - 5);
                            racha = 0;
                            System.out.println(e.getMessage() + ", Pierde 5 puntos! Su puntaje es: " + obtenerPuntaje() + "Racha perdida!");
                        }
                    }

                }
            }
        }
         this.jugador.agregarPuntaje(puntaje);

    }

    public String obtenerRespuestaDelJugador(Pregunta pregunta){
        Scanner scanner = new Scanner(System.in);
        int opcionRespuesta = scanner.nextInt();
        ArrayList<String> opcionesAux;
        String respuesta = "";

        if( pregunta instanceof PreguntaMultipleChoice){
            opcionesAux = ((PreguntaMultipleChoice) pregunta).getOpciones().getElementos(); //me traigo el arraylist correspondiente a las opciones de la pregunta
            respuesta = opcionesAux.get(opcionRespuesta-1);
        } else if (pregunta instanceof PreguntaVerdaderoOFalso) {
            if(opcionRespuesta == 1){
                respuesta= "Verdadero";
            }else{
                respuesta = "Falso";
            }
        }
        return respuesta;
    }

    //Tratando de modularizar
    public String obtenerRespuestaDelJugador1(Pregunta pregunta, Scanner scanner){
        System.out.println("Ingrese una opcion: ");
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

    public Categoria obtenerCategoria(){
        Random random = new Random();
        Categoria categoriaAleatoria = null;
        int numeroAleatorio = random.nextInt(6) + 1; //esto es para que se genere un numero del 1 al 6, sin tener en cuenta el 0.

        for(Categoria categoria : Categoria.values()){
            if(categoria.getId() == numeroAleatorio){
                categoriaAleatoria = categoria;
            }
        }
        return categoriaAleatoria;
    }

}
