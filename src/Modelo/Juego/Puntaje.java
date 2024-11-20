package Modelo.Juego;

import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Puntaje implements IJson {

    private int puntaje;
    private LocalDateTime fecha;

    public Puntaje(int puntaje) {
        this.puntaje = puntaje;
        fecha = LocalDateTime.now();
    }

    public Puntaje() {
        fecha = LocalDateTime.now();
    }


    public LocalDateTime getFecha() {
        return fecha;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "\nPuntaje: " + puntaje +
                "\nFecha: " + fecha.format(formatear);
    }

    @Override
    public JSONObject toJson() {
        JSONObject objeto = new JSONObject();
        try{
            objeto.put("Puntaje", puntaje);
            objeto.put("Fecha", fecha);
        }catch(JSONException e){
            e.printStackTrace();
        }
        return objeto;
    }

   public static Puntaje fromJson(JSONObject j) {
        Puntaje puntaje = new Puntaje();
        try {
            puntaje.setPuntaje(j.getInt("Puntaje"));
            String fechaStr = j.getString("Fecha"); // Leer la fecha como cadena
            LocalDateTime fecha = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.n")); // Parsear la fecha
            puntaje.setFecha(fecha); // Asignar la fecha al objeto Puntaje

        }catch (JSONException e) {
           e.printStackTrace();
        }
       return puntaje;
   }
}
