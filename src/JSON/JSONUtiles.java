package JSON;
import org.json.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JSONUtiles {

    ///metodo para leer el archivo
    public static String leerArchivo(String nombre){
        StringBuilder contenido = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(nombre))){
            String linea;
            while((linea = br.readLine()) != null){
                contenido.append(linea);
            }
            br.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return contenido.toString();
    }

    ///metodo para guardar el archivo un json array
    public static void guardarJSONArray(JSONArray array, String nombre) {
        try(FileWriter file = new FileWriter(nombre)){
            file.write(array.toString(4));
        }catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }

    ///metodo para guardar en el archivo un json object
    public static void guardarJSONObject(JSONObject obj, String nombre) {
        try(FileWriter file = new FileWriter(nombre)){
            file.write(obj.toString(4));
        }catch(IOException | JSONException e){
            e.printStackTrace();
        }
    }
}
