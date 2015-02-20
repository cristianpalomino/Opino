package com.capr.beans_v2;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Child_DTO extends Opino_DTO {

    private String tipo = "tipo";
    private String variable_id = "variable_id";
    private String variable_nombre = "variable_nombre";
    private String respuesta = "respuesta";
    private String dependencia = "dependencia";

    public String getTipo() {
        return parseString(tipo, getDataSource());
    }

    public String getVariable_id() {
        return parseString(variable_id, getDataSource());
    }

    public String getVariable_nombre() {
        return parseString(variable_nombre, getDataSource());
    }

    public String getRespuesta() {
        return parseString(respuesta, getDataSource());
    }

    public String getDependencia() {
        return parseString(dependencia,getDataSource());
    }

    public void setRespuesta(String mrespuesta) {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, mrespuesta);
            json_respuesta.put(dependencia, getDependencia());
            setDataSource(json_respuesta);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getDataSourceRespuesta() {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, getRespuesta());
            json_respuesta.put(dependencia, getDependencia());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json_respuesta;
    }
}
