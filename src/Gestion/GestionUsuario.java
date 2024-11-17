package Gestion;

import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;

public class GestionUsuario {

    private GestionDeElementos<Usuario> usuarios;

    ///Constructor
    public GestionUsuario() {
        usuarios = new GestionDeElementos<>();
    }

    ///Metodos
    public void agregarUsuario(Usuario usuario) {
        usuarios.agregarElemento(usuario);
    }

    public void eliminarUsuario(Usuario usuario) {
        usuarios.eliminarElemento(usuario);
    }

    public Usuario buscarUsuario(Usuario usuario) {
        return usuarios.buscarElemento(usuario);
    }


    //crear usuario admin
    //metodos para vericiar existencia
    //si no hay coincidencias, crear usuario

    //Se va a crear una excepcion que sea error al crear jugador
    public String crearJugador(String nombreUsuario, String email, String contrasenia)
    {
        String mensaje = " ";
        Jugador nuevoJugador = new Jugador(nombreUsuario, email, contrasenia);

        Boolean control = verificarExistenciaJugador(nuevoJugador.getNombreUsuario());

        if(control != true)
        {
            agregarUsuario(nuevoJugador);
            return mensaje = "El jugador fue creado exitosamente! ";
        }

        return mensaje = "El jugador no pudo ser creado! ";
    }

    ///Este metodo es dudoso, esta sujeto a posibles cambios debido al uso de return. Atte, pepo
    public boolean verificarExistenciaJugador(String nombreUsuario)
    {
        String mensaje = " ";
        for (Usuario usuario : usuarios.obtenerTodosLosElementos()) {
            if (usuario.getNombreUsuario().equals(nombreUsuario) ) {
                return true;
            }
        }
        return false;
    }



}