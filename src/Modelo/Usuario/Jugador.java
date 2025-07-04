package Modelo.Usuario;

import Interfaces.IJson;
import Modelo.Juego.Puntaje;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Jugador extends Usuario implements IJson {

   ///atributos
   private ArrayList<Puntaje> puntajesHistorial;

   ///Constructores
   public Jugador(String nombreUsuario, String email, String contrasenia) {
      super(nombreUsuario, email, contrasenia);
      this.puntajesHistorial = new ArrayList<>();
   }

   public Jugador() {
      this.puntajesHistorial = new ArrayList<>();
   }

   ///metodo para agregar puntaje al historial
   public void agregarPuntaje(Puntaje puntaje) {
      if(puntaje != null) {
         puntajesHistorial.add(puntaje);
      }
   }

   ///metodo mostrar historial de puntajes
   public String mostrarHistorial() {
      StringBuilder sb = new StringBuilder();
      int i = 1;
      for (Puntaje puntaje : puntajesHistorial) {
         sb.append("\nPartida número ").append(i).append(". ").append(puntaje.toString()).append("\n");
         i++;
      }
      return sb.toString();
   }

   ///to string jugador
   @Override
   public String toString() {
      return super.toString() + "\nPuntajes: " + mostrarHistorial();
   }

   ///Metodo para convertir el historial de puntajes a un JSONArray
   public JSONArray toJsonArray() {
      JSONArray jsonArray = new JSONArray();
      for (Puntaje puntaje : puntajesHistorial) {
         jsonArray.put(puntaje.toJson());
      }
      return jsonArray;
   }

   ///metodo para convertir un jugador a un json object
   @Override
   public JSONObject toJson() {
      JSONObject json = new JSONObject();
      try{
         json.put("NombreUsuario", nombreUsuario);
         json.put("Email", email);
         json.put("Contrasenia", contrasenia);
         json.put("HistorialPuntajes", toJsonArray());
      }catch (JSONException e){
         e.printStackTrace();
      }
      return json;
   }

   ///metodo para traer un jugador desde el json
   public static Jugador toObj(JSONObject json) {
      Jugador nuevo = new Jugador();
      try{
         nuevo.setNombreUsuario(json.getString("NombreUsuario"));
         nuevo.setEmail(json.getString("Email"));
         nuevo.setContrasenia(json.getString("Contrasenia"));

         JSONArray puntajes = json.getJSONArray("HistorialPuntajes");

         for (int i = 0; i < puntajes.length(); i++) {
            JSONObject puntaje = puntajes.getJSONObject(i);
            nuevo.agregarPuntaje(Puntaje.fromJson(puntaje));
         }

      }catch (JSONException e){
         e.printStackTrace();
      }catch(RuntimeException e){
         throw new RuntimeException("Error al procesar los puntajes", e);
      }
      return nuevo;
   }
}
