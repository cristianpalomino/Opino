package com.capr.beans_v2;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Respuesta_DTO extends Opino_DTO {

    private String recurso_id = "recurso_id";
    private String tipo = "tipo";
    private String variable_id = "variable_id";
    private String variable_nombre = "variable_nombre";
    private String respuesta = "respuesta";
    private String cliente_id = "cliente_id";

    public String getRecurso_id() {
        return parseString(recurso_id, getDataSource());
    }

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

    public String getCliente_id() {
        return parseString(cliente_id,getDataSource());
    }

    public void setRespuesta(String mrespuesta) {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(recurso_id, getRecurso_id());
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, mrespuesta);
            setDataSource(json_respuesta);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setRespuestaCalidad(String mrespuesta) {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(respuesta, mrespuesta);
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(cliente_id, getCliente_id());
            setDataSource(json_respuesta);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public JSONObject getDataSourceRespuesta() {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(recurso_id, getRecurso_id());
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, getRespuesta());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json_respuesta;
    }

    public JSONObject getDataSourceRespuestaCalidad() {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(respuesta, getRespuesta());
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(cliente_id,getCliente_id());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json_respuesta;
    }
}
