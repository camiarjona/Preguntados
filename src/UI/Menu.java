package UI;

import Excepciones.ElementoDuplicado;
import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import Gestion.GestionDeElementos;
import Gestion.GestionPreguntas;
import Gestion.GestionUsuario;
import Modelo.Enum.Categoria;
import Modelo.Juego.Pregunta;
import Modelo.Juego.PreguntaMultipleChoice;
import Modelo.Juego.PreguntaVerdaderoOFalso;
import Modelo.Juego.Ronda;
import Modelo.Usuario.Jugador;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Menu {

    private static GestionUsuario gestionUsuario;
    private static GestionPreguntas gestionPreguntas;
    private Jugador jugadorAutenticado;
    private Ronda ronda;

    public Menu(){
        gestionUsuario = new GestionUsuario();
        gestionPreguntas = new GestionPreguntas();
        ronda = new Ronda();
    }

    //metodo para mostrar el menu
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        PreguntaMultipleChoice pregunta = new PreguntaMultipleChoice("Cuantos mundiales gano argentina?", Categoria.DEPORTE, "Tres");
        pregunta.agregarOpcion("Tres");
        pregunta.agregarOpcion("Dos");
        pregunta.agregarOpcion("Uno");
        pregunta.agregarOpcion("Seis");

        PreguntaMultipleChoice historiaMC1 = new PreguntaMultipleChoice(
                "¿En qué año ocurrió la caída del Imperio Romano de Occidente?",
                Categoria.HISTORIA,
                "476 d.C."
        );
        historiaMC1.agregarOpcion("395 d.C.");
        historiaMC1.agregarOpcion("476 d.C.");
        historiaMC1.agregarOpcion("1492 d.C.");
        historiaMC1.agregarOpcion("800 d.C.");

        PreguntaMultipleChoice historiaMC2 = new PreguntaMultipleChoice(
                "¿Quién fue el líder militar durante la guerra de independencia de los Estados Unidos?",
                Categoria.HISTORIA,
                "George Washington"
        );
        historiaMC2.agregarOpcion("George Washington");
        historiaMC2.agregarOpcion("Thomas Jefferson");
        historiaMC2.agregarOpcion("Benjamin Franklin");
        historiaMC2.agregarOpcion("John Adams");

        PreguntaMultipleChoice historiaMC3 = new PreguntaMultipleChoice(
                "¿Qué imperio conquistó la antigua ciudad de Cartago?",
                Categoria.HISTORIA,
                "Imperio Romano"
        );
        historiaMC3.agregarOpcion("Imperio Romano");
        historiaMC3.agregarOpcion("Imperio Egipcio");
        historiaMC3.agregarOpcion("Imperio Persa");
        historiaMC3.agregarOpcion("Imperio Griego");

        PreguntaVerdaderoOFalso historiaVF1 = new PreguntaVerdaderoOFalso(
                "La Revolución Francesa comenzó en 1800.",
                Categoria.HISTORIA, "Falso");

        PreguntaVerdaderoOFalso historiaVF2 = new PreguntaVerdaderoOFalso(
                "El Imperio Romano existió durante más de 500 años.",
                Categoria.HISTORIA, "Verdadero"
        );

        PreguntaVerdaderoOFalso historiaVF3 = new PreguntaVerdaderoOFalso(
                "Napoleón Bonaparte nació en Inglaterra.",
                Categoria.HISTORIA, "Falso"
        );

        PreguntaMultipleChoice geografiaMC1 = new PreguntaMultipleChoice(
                "¿Cuál es el río más largo del mundo?",
                Categoria.GEOGRAFIA,
                "Amazonas"
        );
        geografiaMC1.agregarOpcion("Amazonas");
        geografiaMC1.agregarOpcion("Nilo");
        geografiaMC1.agregarOpcion("Yangtsé");
        geografiaMC1.agregarOpcion("Misisipi");

        PreguntaMultipleChoice geografiaMC2 = new PreguntaMultipleChoice(
                "¿En qué continente se encuentra el desierto del Sahara?",
                Categoria.GEOGRAFIA,
                "África"
        );
        geografiaMC2.agregarOpcion("Asia");
        geografiaMC2.agregarOpcion("África");
        geografiaMC2.agregarOpcion("América");
        geografiaMC2.agregarOpcion("Australia");

        PreguntaMultipleChoice geografiaMC3 = new PreguntaMultipleChoice(
                "¿Qué país tiene la mayor cantidad de islas en el mundo?",
                Categoria.GEOGRAFIA,
                "Canadá"
        );
        geografiaMC3.agregarOpcion("Canadá");
        geografiaMC3.agregarOpcion("Suecia");
        geografiaMC3.agregarOpcion("Indonesia");
        geografiaMC3.agregarOpcion("Japón");

        PreguntaVerdaderoOFalso geografiaVF1 = new PreguntaVerdaderoOFalso(
                "La capital de Canadá es Montreal.",
                Categoria.GEOGRAFIA,
                "Falso"
        );

        PreguntaVerdaderoOFalso geografiaVF2 = new PreguntaVerdaderoOFalso(
                "El Monte Everest es la montaña más alta del mundo.",
                Categoria.GEOGRAFIA,
                "Verdadero"
        );

        PreguntaVerdaderoOFalso geografiaVF3 = new PreguntaVerdaderoOFalso(
                "El Amazonas se encuentra en Asia.",
                Categoria.GEOGRAFIA,
                "Falso"
        );

        PreguntaMultipleChoice entretenimientoMC1 = new PreguntaMultipleChoice(
                "¿Quién ganó el Oscar a Mejor Película en 2020?",
                Categoria.ENTRETENIMIENTO,
                "Parasite"
        );
        entretenimientoMC1.agregarOpcion("Joker");
        entretenimientoMC1.agregarOpcion("Parasite");
        entretenimientoMC1.agregarOpcion("1917");
        entretenimientoMC1.agregarOpcion("Once Upon a Time in Hollywood");

        PreguntaMultipleChoice entretenimientoMC2 = new PreguntaMultipleChoice(
                "¿Quién interpretó a Jack Dawson en la película 'Titanic'?",
                Categoria.ENTRETENIMIENTO,
                "Leonardo DiCaprio"
        );
        entretenimientoMC2.agregarOpcion("Brad Pitt");
        entretenimientoMC2.agregarOpcion("Leonardo DiCaprio");
        entretenimientoMC2.agregarOpcion("Tom Cruise");
        entretenimientoMC2.agregarOpcion("Johnny Depp");

        PreguntaMultipleChoice entretenimientoMC3 = new PreguntaMultipleChoice(
                "¿Cuál es el nombre de la película que protagonizó Will Smith como un genio en 2019?",
                Categoria.ENTRETENIMIENTO,
                "Aladdin"
        );
        entretenimientoMC3.agregarOpcion("Aladdin");
        entretenimientoMC3.agregarOpcion("Men in Black");
        entretenimientoMC3.agregarOpcion("The Pursuit of Happyness");
        entretenimientoMC3.agregarOpcion("I Am Legend");

        PreguntaVerdaderoOFalso entretenimientoVF1 = new PreguntaVerdaderoOFalso(
                "La película 'Titanic' fue dirigida por Steven Spielberg.",
                Categoria.ENTRETENIMIENTO,
                "Falso"
        );

        PreguntaVerdaderoOFalso entretenimientoVF2 = new PreguntaVerdaderoOFalso(
                "La saga de 'Harry Potter' está basada en libros de J.R.R. Tolkien.",
                Categoria.ENTRETENIMIENTO,
                "Falso"
        );

        PreguntaVerdaderoOFalso entretenimientoVF3 = new PreguntaVerdaderoOFalso(
                "La película 'Avatar' se estrenó en 2009.",
                Categoria.ENTRETENIMIENTO,
                "Verdadero"
        );

        try{
            gestionPreguntas.agregarPregunta(historiaMC1);
            gestionPreguntas.agregarPregunta(historiaMC2);
            gestionPreguntas.agregarPregunta(historiaMC3);

            gestionPreguntas.agregarPregunta(historiaVF1);
            gestionPreguntas.agregarPregunta(historiaVF2);
            gestionPreguntas.agregarPregunta(historiaVF3);

            gestionPreguntas.agregarPregunta(geografiaMC1);
            gestionPreguntas.agregarPregunta(geografiaMC2);
            gestionPreguntas.agregarPregunta(geografiaMC3);

//            gestionPreguntas.agregarPregunta(geografiaVF1);
//            gestionPreguntas.agregarPregunta(geografiaVF2);
//            gestionPreguntas.agregarPregunta(geografiaVF3);

            gestionPreguntas.agregarPregunta(entretenimientoMC1);
            gestionPreguntas.agregarPregunta(entretenimientoMC2);
            gestionPreguntas.agregarPregunta(entretenimientoMC3);

            gestionPreguntas.agregarPregunta(entretenimientoVF1);
            gestionPreguntas.agregarPregunta(entretenimientoVF2);
            gestionPreguntas.agregarPregunta(entretenimientoVF3);
            gestionPreguntas.agregarPregunta(entretenimientoVF3);

        }catch (ElementoDuplicado e){
            System.out.println(e.getMessage());
        }


        do{
            imprimirMenuPrincipal();
            opcion = sc.nextInt();
            sc.nextLine();


            switch (opcion){
                case 1:
                    iniciarSesion(sc);
                    break;
                case 2:
                    registrarUsuario(sc);
                    break;
                case 3:
                    System.out.println("Saliendo... Hasta la próxima! \uD83D\uDC4B");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while(opcion != 3);

        sc.close();
    }


    //metodo para mostrar las opciones principales
    private void imprimirMenuPrincipal(){
        System.out.println("\n********************");
        System.out.println("\u001B[1m✨ MENÚ PRINCIPAL ✨\u001B[0m");
        System.out.println("********************");
        System.out.println("1\uFE0F⃣ Iniciar sesión.");
        System.out.println("2\uFE0F⃣ Registrarse.");
        System.out.println("3\uFE0F⃣ Salir.");
        System.out.println("Seleccione una opción \uD83D\uDC49 ");
    }

    //metodo para mostrar las opciones del jugador
    private void imprimirMenuJugador(){
        System.out.println("\n***********************************");
        System.out.println("\u001B[1m\uD83D\uDE4C ¡¡Bienvenido " + jugadorAutenticado.getNombreUsuario() + "!! \uD83D\uDE4C \u001B[0m");
        System.out.println("***********************************");
        System.out.println("1\uFE0F⃣  Jugar partida\uD83D\uDD79\uFE0F");
        System.out.println("2\uFE0F⃣  Ver historial de partidas\uD83D\uDCDD");
        System.out.println("3\uFE0F⃣  Volver al menu principal↩\uFE0F");
        System.out.println("Seleccione una opción \uD83D\uDC49 ");
    }

    //metodo para menu jugador
    private void mostrarMenuJugador(Scanner sc){
        int opcion;
        do{
            imprimirMenuJugador();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    ronda.setJugador(jugadorAutenticado);
                    ronda.setPreguntas(gestionPreguntas);
                    ronda.jugarRonda(sc);
                    break;
                case 2:
                    System.out.println(jugadorAutenticado.mostrarHistorial());
                    break;
                case 3:
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while (opcion != 3);
    }

    //metodo para iniciar sesion
    private void iniciarSesion(Scanner sc) {
        System.out.printf("\uD83C\uDFAF Ingrese el nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.printf("\uD83C\uDFAF Ingrese la contraseña: ");
        String contrasena = sc.nextLine();

        try{
            if(gestionUsuario.verificarLogin(usuario, contrasena)){
                if(verificarAdministrador(usuario, contrasena)){
                    System.out.println("\n\u001B[32mInicio de sesion exitoso✔\uFE0F\u001B[0m\n");
                    mostrarMenuAdministrador(sc);
                }
                else{
                    jugadorAutenticado = gestionUsuario.obtenerJugadorPorNombre(usuario);
                    System.out.println("\n\u001B[32mInicio de sesion exitoso✔\uFE0F\u001B[0m\n");
                    mostrarMenuJugador(sc);
                }

            }

        }catch (UsuarioIncorrecto | ContraseniaInconrrecta e){
            System.out.println(e.getMessage());
            System.out.println("Intente nuevamente.");
        }
    }

    //verificar admin
    private boolean verificarAdministrador(String usuario, String contrasena){
        if("admin".equals(usuario) && "admin".equals(contrasena)){
            return true;
        }
        else{
            return false;
        }
    }

    //metodo para imprimir menu admin
    private void imprimirMenuAdministrador(){
        System.out.println("\n***********************************");
        System.out.println("\uD83D\uDD75\uFE0F MENÚ ADMINISTRADOR \uD83D\uDD75\uFE0F");
        System.out.println("***********************************");
        System.out.println("1\uFE0F⃣  Agregar pregunta\uD83D\uDCA1");
        System.out.println("2\uFE0F⃣  Eliminar pregunta❌");
        System.out.println("3\uFE0F⃣  Ver lista de preguntas");
        System.out.println("4\uFE0F⃣ Volver al menú principal↩\uFE0F");
        System.out.println("Seleccione una opción \uD83D\uDC49 ");
    }

    //metodo para mostrar menu admin
    private void mostrarMenuAdministrador(Scanner sc){
        int opcion;
        do{
            imprimirMenuAdministrador();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    mostrarAgregarPregunta(sc);
                    break;
                case 2:
                    //eliminar pregunta
                    break;
                case 3:
                    //ver preguntas
                    break;
                case 4:
                    System.out.println("Regresando al menu principal...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while (opcion != 4);
    }

    //metodo para agregar pregunta
    private void mostrarAgregarPregunta(Scanner sc){
        int opcion;
        do{
            imprimirPregunta();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion){
                case 1:
                    agregarPreguntaMC(sc);
                    break;
                case 2:
                    agregarPreguntaVoF(sc);
                    break;
                case 3:
                    System.out.println("Regresando al menu anterior...");
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while (opcion != 3);

    }

    //metodo para capturar datos comunes entre ambos tipos de preguntas, se usa un arreglo ya que pregunta es abstracta y no se puede instanciar.
    private String[] capturarDatosComunes(Scanner sc){
        System.out.println("Ingrese el enunciado: ");
        String enunciado = sc.nextLine();

        System.out.println("Ingrese la categoria: ");
        imprimirCategoria();
        int categoria = sc.nextInt();

        sc.nextLine();

        Categoria categoriaSeleccionada = Categoria.obtenerCategoriaPorId(categoria);

        return new String[]{enunciado, categoriaSeleccionada.toString()};
    }

    //metodo para agregar pregunta vof
    private void agregarPreguntaVoF(Scanner sc){
        String[] datos = capturarDatosComunes(sc);

        String enunciado = datos[0];
        Categoria categoria = Categoria.valueOf(datos[1]);

        System.out.println("Ingrese la respuesta correcta: ");
        String respuesta = sc.nextLine();

        PreguntaVerdaderoOFalso nuevavof = new PreguntaVerdaderoOFalso(enunciado, categoria, respuesta);

        try{
            gestionPreguntas.agregarPregunta(nuevavof);
            System.out.println("Pregunta verdadero o falso agregada correctamente.");
        }catch(ElementoDuplicado e){
            System.out.println(e.getMessage());
        }

        System.out.println(nuevavof.toString());

    }

    //metodo para agregar pregunta multiple choice
    private void agregarPreguntaMC(Scanner sc){
        String[] datos = capturarDatosComunes(sc);

        String enunciado = datos[0];
        Categoria categoria = Categoria.valueOf(datos[1]);

        System.out.println("Ingrese la respuesta correcta: ");
        String respuesta = sc.nextLine();

        PreguntaMultipleChoice nueva = new PreguntaMultipleChoice(enunciado, categoria , respuesta);
        nueva.agregarOpcion(respuesta);

        System.out.println("Agregue las opciones: ");
        agregarOpcionesMC(sc, nueva);

        try{
            gestionPreguntas.agregarPregunta(nueva);
            System.out.println("Pregunta múltiple choice agregada correctamente.");
        }catch(ElementoDuplicado e){
            System.out.println(e.getMessage());
        }

        System.out.println(nueva.toString());

    }

    //metodo para agregar opciones mc
    private void agregarOpcionesMC(Scanner sc, PreguntaMultipleChoice nueva) {
        boolean seguirAgregando = true;

        // Bucle para agregar opciones
        while (seguirAgregando) {
            System.out.println("Ingrese una opción: ");
            String opcion = sc.nextLine();
            nueva.agregarOpcion(opcion); // Añadir la opción a la lista

            System.out.println("¿Desea agregar otra opción? (si/no)");
            String respuesta = sc.nextLine();

            if (respuesta.equalsIgnoreCase("no")) {
                seguirAgregando = false;  // Salir del bucle si la respuesta es 'no'
            }
        }
        Collections.shuffle(nueva.getOpciones().getElementos());
    }


    //metodo para imprimir categoria
    private void imprimirCategoria(){
        for(Categoria categoria : Categoria.values()){
            System.out.println(categoria.getId() + ". " + categoria);
        }
    }

    //metodo imprimir menu agregar pregunta
    private void imprimirPregunta(){
        System.out.println("\n1\uFE0F⃣ Pregunta múltiple choice.");
        System.out.println("2\uFE0F⃣ Pregunta verdadero o falso.");
        System.out.println("3\uFE0F⃣ Volver al menú anterior.");
    }


    //metodo para registrar un nuevo usuario
    private void registrarUsuario(Scanner sc) {
        System.out.println("\uD83C\uDFAF Ingrese el nombre de usuario: ");
         String usuario = sc.nextLine();

        System.out.println("\uD83C\uDFAF Ingrese el correo electronico: ");
        String correo = sc.nextLine();

        try{
            gestionUsuario.existeUsuario(usuario);
            gestionUsuario.existeMail(correo);

        }catch (CorreoExistente | UsuarioExistente e){
            System.out.println(e.getMessage());
            System.out.println("\u001B[31mPor favor, inicie sesión o intente nuevamente.\u001B[0m\n");
            return;
        }

        System.out.println("\uD83C\uDFAF Ingrese la contraseña: ");
        String contrasena = sc.nextLine();

        Jugador nuevo = new Jugador(usuario, correo, contrasena);

        try{
            gestionUsuario.agregarUsuario(nuevo);
            System.out.println("\n\u001B[32mUsuario creado con éxito\u001B[0m");
        }catch(ElementoDuplicado e){
            System.out.println(e.getMessage());
        }

        System.out.println("\u001B[32mPor favor, inicie sesión para continuar.\u001B[0m\n");

    }

}
