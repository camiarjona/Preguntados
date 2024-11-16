package Gestion;

import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;

public class GestionUsuario {

    private GestionDeElementos<Usuario> usuarios;

    public GestionUsuario() {
        usuarios = new GestionDeElementos<>();
    }

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


}
