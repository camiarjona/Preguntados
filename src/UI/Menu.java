package UI;

import Excepciones.ElementoDuplicado;
import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import Gestion.GestionPreguntas;
import Gestion.GestionUsuario;
import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;

import java.util.Scanner;

public class Menu {

    private static GestionUsuario gestionUsuario;
    private static GestionPreguntas gestionPreguntas;

    public Menu(){
        gestionUsuario = new GestionUsuario();
        gestionPreguntas = new GestionPreguntas();
    }

    public void mostrarMenu(){
        Scanner sc = new Scanner(System.in);
        int opcion;

        do{
            System.out.println("1. Iniciar sesion.\n 2. Registrarse.\n 3. Salir.\n");
            System.out.println("Selecciona una opcion: ");
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
                    System.out.println("Saliendo...");
                    sc.close();
                    break;
                default:
                    System.out.println("Opcion no valida.");
                    break;
            }
        }while(opcion != 3);

    }

    private void iniciarSesion(Scanner sc) {
        System.out.printf("Ingrese el nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.printf("Ingrese la contraseña: ");
        String contrasena = sc.nextLine();

        try{
            gestionUsuario.verificarLogin(usuario, contrasena);
            System.out.println("Inicio de sesion exitoso.");
        }catch (UsuarioIncorrecto | ContraseniaInconrrecta e){
            System.out.println(e.getMessage());
            System.out.println("Intente nuevamente.");
        }


    }

    private void registrarUsuario(Scanner sc) {
        System.out.println("Ingrese el nombre de usuario: ");
         String usuario = sc.nextLine();

        System.out.println("Ingrese el correo electronico: ");
        String correo = sc.nextLine();

        try{
            gestionUsuario.existeUsuario(usuario);
            gestionUsuario.existeMail(correo);

        }catch (CorreoExistente | UsuarioExistente e){
            System.out.println(e.getMessage());
            System.out.println("Por favor, inicie sesion o intente nuevamente.");
            return;
        }

        System.out.println("Ingrese la contraseña: ");
        String contrasena = sc.nextLine();

        Jugador nuevo = new Jugador(usuario, correo, contrasena);

        try{
            gestionUsuario.agregarUsuario(nuevo);
            System.out.println("Usuario creado con exito");
        }catch(ElementoDuplicado e){
            System.out.println(e.getMessage());
        }
    }
}
