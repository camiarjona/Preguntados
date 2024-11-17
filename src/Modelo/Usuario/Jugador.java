package Modelo.Usuario;

import Gestion.GestionDeElementos;
import Modelo.Juego.Puntaje;
import org.json.JSONObject;

public class Jugador extends Usuario {

   private GestionDeElementos<Puntaje> puntajesHistorial;

   ///Constructor
   public Jugador(String nombreUsuario, String email, String contrasenia) {
      super(nombreUsuario, email, contrasenia);
      this.puntajesHistorial = new GestionDeElementos<Puntaje>();
   }

   ///Getter and setter
   public GestionDeElementos<Puntaje> getPuntajesHistorial() {
      return puntajesHistorial;
   }
   public void setPuntajesHistorial(GestionDeElementos<Puntaje> puntajesHistorial) {
      this.puntajesHistorial = puntajesHistorial;
   }

   //Sobreescritura
   @Override
   public JSONObject toJson() {
      return super.toJson();
   }

   @Override
   public Usuario toObj() {
      return super.toObj();
   }
}
