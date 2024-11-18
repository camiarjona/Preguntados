package Modelo.Usuario;

import Gestion.GestionDeElementos;
import Modelo.Juego.Puntaje;
import org.json.JSONObject;

public class Jugador extends Usuario {

   private GestionDeElementos<Puntaje> puntajesHistorial;

   public Jugador(String nombreUsuario, String email, String contrasenia) {
      super(nombreUsuario, email, contrasenia);
      this.puntajesHistorial = new GestionDeElementos<Puntaje>();
   }

   public GestionDeElementos<Puntaje> getPuntajesHistorial() {
      return puntajesHistorial;
   }

   public void setPuntajesHistorial(GestionDeElementos<Puntaje> puntajesHistorial) {
      this.puntajesHistorial = puntajesHistorial;
   }

   @Override
   public JSONObject toJson() {
      return super.toJson();
   }

   @Override
   public Usuario toObj() {
      return super.toObj();
   }
}
