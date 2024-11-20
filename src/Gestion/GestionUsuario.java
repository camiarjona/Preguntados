package Gestion;

import Excepciones.Usuario.ContraseniaInconrrecta;
import Excepciones.Usuario.CorreoExistente;
import Excepciones.Usuario.UsuarioExistente;
import Excepciones.Usuario.UsuarioIncorrecto;
import JSON.JSONUtiles;
import Modelo.Usuario.Jugador;
import Modelo.Usuario.Usuario;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class GestionUsuario {

    private GestionDeElementos<Jugador> usuarios;

    public GestionUsuario() {
        usuarios = new GestionDeElementos<>();
    }

    public void agregarUsuario(Jugador usuario) {
        usuarios.agregarElemento(usuario);
    }

    public void eliminarUsuario(Jugador usuario) {
        usuarios.eliminarElemento(usuario);
    }

    public boolean buscarUsuario(Jugador usuario) {
        return usuarios.buscarElemento(usuario);
    }

    // Metodo login
    public boolean verificarLogin(String nombreUsuario, String contrasenia) throws UsuarioIncorrecto, ContraseniaInconrrecta {
        for (Jugador usuario : usuarios.getElementos()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                // Si el nombre de usuario es correcto, verifico la contraseña
                if (usuario.getContrasenia().equals(contrasenia)) {
                    return true; //Si ambos coinciden, retorno verdadero
                } else {
                    throw new ContraseniaInconrrecta("Contraseña incorrecta.");
                }
            }
        }
        // Si sale del bucle, significa que el usuario es incorrecto
        throw new UsuarioIncorrecto("Nombre de usuario incorrecto.");
    }

    public void existeUsuario(String nombreUsuario) throws UsuarioExistente {
        for (Jugador usuario : usuarios.getElementos()) {
            if (usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                throw new UsuarioExistente("\n\u001B[31mNombre de usuario existente.\u001B[0m");
            }
        }
    }

    public void existeMail(String mail) throws CorreoExistente {
        for (Jugador usuario : usuarios.getElementos()) {
            if (usuario.getEmail().equalsIgnoreCase(mail)) {
                throw new CorreoExistente("\n\u001B[31mEl correo ya se encuentra registrado.\u001B[0m");
            }
        }
    }

    //metodo para obtener el jugador una vez inciada la sesion
    public Jugador obtenerJugadorPorNombre(String nombreUsuario) throws UsuarioIncorrecto {
        for (Jugador usuario : usuarios.getElementos()) {
            if (usuario instanceof Jugador && usuario.getNombreUsuario().equals(nombreUsuario)) {
                return (Jugador) usuario;
            }
        }
        throw new UsuarioIncorrecto("El usuario no existe.");
    }

    //metodo para convertir mi lista de usuarios a formato json
    public JSONArray usuariosAJson(){
        JSONArray jsonArray = new JSONArray();
        for (Jugador usuario : usuarios.getElementos()) {
            jsonArray.put(usuario.toJson());
        }
        return jsonArray;
    }

    //metodo para convertir mi jsonArray de usuarios a formato Gestion de usuarios
    public static GestionUsuario toObjUsuarios(JSONArray jsonArray) {
        GestionUsuario gestionUsuario = new GestionUsuario();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Jugador jugador = Jugador.toObj(jsonObject);

                gestionUsuario.agregarUsuario(jugador);
            }
        }catch (JSONException e){
            e.printStackTrace();
        }
        return gestionUsuario;
    }

    public void cargarUsuariosDesdeJson(String rutaArchivo) {
        try {
            // Leer el contenido del archivo JSON
            String contenido = JSONUtiles.leerArchivo(rutaArchivo);

            // Convertir el contenido en un JSONArray
            JSONArray jsonArray = new JSONArray(contenido);

            // Convertir el JSONArray en un objeto GestionUsuario
            GestionUsuario gestionUsuario = toObjUsuarios(jsonArray);

            // Copiar los usuarios cargados a la lista de usuarios actual
            this.usuarios = gestionUsuario.usuarios;

            System.out.println("Usuarios cargados con éxito.");

        }catch (JSONException e) {
            System.out.println("Error al procesar el archivo JSON: " + e.getMessage());
        }
    }

    public void guardarUsuarios(String rutaArchivo) {
        JSONArray jsonArray = usuariosAJson();
        JSONUtiles.guardarJSONArray(jsonArray, rutaArchivo);
    }

}
