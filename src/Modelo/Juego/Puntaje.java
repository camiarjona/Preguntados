package Modelo.Juego;

import Interfaces.IJson;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Puntaje implements IJson {

    /// atirbutos
    private int puntaje;
    private LocalDateTime fecha;

    /// constructores
    public Puntaje(int puntaje) {
        this.puntaje = puntaje;
        fecha = LocalDateTime.now();
    }

    public Puntaje() {
        fecha = LocalDateTime.now();
    }

    /// get y set
    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    /// to string
    @Override
    public String toString() {
        DateTimeFormatter formatear = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return "\nPuntaje: " + puntaje +
                "\nFecha: " + fecha.format(formatear);
    }

    /// metodo para convertir un puntje a json object
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

    /// metodo para traer un puntaje desde el archivo json
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
