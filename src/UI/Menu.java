package UI;

import Excepciones.ElementoDuplicado;
import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import Gestion.GestionPreguntas;
import Gestion.GestionUsuario;
import Modelo.Enum.Categoria;
import Modelo.Juego.Pregunta;
import Modelo.Juego.PreguntaMultipleChoice;
import Modelo.Juego.PreguntaVerdaderoOFalso;
import Modelo.Usuario.Jugador;

import java.util.Scanner;

public class Menu {

    private static GestionUsuario gestionUsuario;
    private static GestionPreguntas gestionPreguntas;
    private Jugador jugadorAutenticado;

    public Menu(){
        gestionUsuario = new GestionUsuario();
        gestionPreguntas = new GestionPreguntas();
        jugadorAutenticado = null;
    }

    //metodo para mostrar el menu
    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        int opcion;

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
                    //inicar juego
                    break;
                case 2:
                    jugadorAutenticado.mostrarHistorial();
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
