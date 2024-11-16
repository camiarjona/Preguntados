package Interfaces;

import org.json.JSONObject;

public interface IJson<T> {

    JSONObject toJson();
    T toObj();
}
