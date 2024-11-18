package Modelo.Juego;

import Excepciones.RespuestaIncorrecta;
import Gestion.GestionDeElementos;
import Interfaces.IJson;
import Modelo.Enum.Categoria;
import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;
import org.json.JSONObject;

import java.util.*;

public class Ronda implements IJson<Ronda> {

    private static final int nroMaxRondas = 3;

    private GestionDeElementos<Pregunta> preguntas;
    private Puntaje puntaje;
    private Jugador jugador;

    //CONSTRUCTORES


    public Ronda(Jugador jugador) {
        this.preguntas = new GestionDeElementos<Pregunta>();
        this.puntaje = new Puntaje(0);
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

        for(int j = 0; j <= nroMaxRondas ; j++){
            categoriaAux = obtenerCategoria();

            for (int i = 0; i < 3; i++) {
                List<Pregunta> preguntasDesordenadas = new ArrayList<>(preguntas.getElementos());
                Collections.shuffle(preguntasDesordenadas);
                int racha = 0;

                for (Pregunta preguntaAux : preguntasDesordenadas) {

                    if (preguntaAux.categoria == categoriaAux) {
                        contenido.append(preguntaAux.getEnunciado()).append("\n").append(preguntaAux.mostrarOpciones()).append("\nIngrese la opcion correcta: ");
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
                                mensaje = "¡Respuesta correcta!\n Su puntaje es: " + obtenerPuntaje() + "con una racha de: " + racha + "preguntas correctas!";

                            }
                        } catch (RespuestaIncorrecta e) {
                            puntaje.setPuntaje(puntaje.getPuntaje() - 5);
                            racha = 0;
                            mensaje = e.getMessage() + ", Pierde 5 puntos! Su puntaje es: " + obtenerPuntaje() + "Racha perdida!";
                        }
                    }

                }
            }
        }
//        this.jugador.getPuntajesHistorial().agregarPuntaje(puntaje);

    }

    public String obtenerRespuestaDelJugador(Pregunta pregunta){
        Scanner scanner = new Scanner(System.in);
        int opcionRespuesta = scanner.nextInt();
        ArrayList<String> opcionesAux = new ArrayList<>();
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

    public Categoria obtenerCategoria(){

        Random random = new Random();
        int numeroAleatorio;
        Categoria categoriaAleatoria = null;

        numeroAleatorio = random.nextInt(7);

        for(Categoria categoria : Categoria.values()){
            if(categoria.getId() == numeroAleatorio){
                    categoriaAleatoria = categoria;
            }
        }
            return categoriaAleatoria;
    }


    @Override
    public JSONObject toJson() {
        return null;
    }

    @Override
    public Ronda toObj() {
        return null;
    }
}
