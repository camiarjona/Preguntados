package UI;

import Excepciones.ElementoDuplicado;
import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import Gestion.GestionPreguntas;
import Gestion.GestionUsuario;
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
                    //mostrar historial
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
                jugadorAutenticado = gestionUsuario.obtenerJugadorPorNombre(usuario);
                System.out.println("\n\u001B[32mInicio de sesion exitoso✔\uFE0F\u001B[0m\n");
                mostrarMenuJugador(sc);
            }

        }catch (UsuarioIncorrecto | ContraseniaInconrrecta e){
            System.out.println(e.getMessage());
            System.out.println("Intente nuevamente.");
        }

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
