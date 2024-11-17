package Gestion;

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

    // Método login
    public boolean verificarLogin(String nombreUsuario, String contrasenia) {
        boolean existe = false;
        for (Usuario usuario : usuarios.getElementos()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario) &&
                    usuario.getContrasenia().equals(contrasenia)) {
                return existe ;

            }
        }
        return existe;
    }

    // Método para crear un usuario si no existe
    public Usuario registrarUsuario(String nombreUsuario, String email, String contrasenia)  {

        Usuario aux = new Usuario();
        if (!existeUsuario(nombreUsuario)) {
            Usuario nuevoUsuario = new Usuario(nombreUsuario, email, contrasenia);
            agregarUsuario(nuevoUsuario);
            aux = nuevoUsuario;
        }
        return aux; /// Aca quiza se puede hacer una exepcion
    }

    // Método para verificar si el nombre de usuario ya existe
    public boolean existeUsuario(String nombreUsuario) {

        boolean existe = false;

            for (Usuario usuario : usuarios.getElementos()) {
                if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                   existe = true;
                  break; // esto rompe el bucle para que no siga buscando
                }
            }
        return existe;
    }

    // Método para crear un usuario administrador
    public void crearAdmin() {
        if (existeUsuario("admin")) {
            Usuario admin = new Usuario("admin", "admin@example.com", "admin123");
            agregarUsuario(admin);
        }
    }


}
