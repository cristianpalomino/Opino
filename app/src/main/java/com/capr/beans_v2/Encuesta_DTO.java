package com.capr.beans_v2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Encuesta_DTO extends Opino_DTO {

    public String campana_id = "campana_id";
    public String recurso_id = "recurso_id";
    public String descripcion = "descripcion";
    public String variable_id = "variable_id";

    public String getCampana_id() {
        return parseString(campana_id, getDataSource());
    }

    public String getRecurso_id() {
        return parseString(recurso_id, getDataSource());
    }

    public String getDescripcion() {
        return parseString(descripcion, getDataSource());
    }

    public String getVariable_id() {
        return parseString(variable_id,getDataSource());
    }

    public ArrayList<Respuesta_DTO> getRespuesta_dtos() {
        ArrayList<Respuesta_DTO> respuesta_dtos = new ArrayList<Respuesta_DTO>();
        JSONArray jsonArray = parseJSONArray(KEY_JSON_ARRAY_RESPUESTA, getDataSource());
        for (int i = 0; i < jsonArray.length(); i++) {
            Respuesta_DTO respuesta_dto = parseRespuestaDTO(i,jsonArray);
            respuesta_dtos.add(respuesta_dto);
        }
        return respuesta_dtos;
    }

    /***
     *
     */
    private String _id;
    private String _idlocal;
    private String _idvariable;
    private String _uri = "NONE";
    private JSONObject _data;

    public String get_uri() {
        return _uri;
    }

    public void set_uri(String _uri) {
        this._uri = _uri;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_idlocal() {
        return _idlocal;
    }

    public void set_idlocal(String _idlocal) {
        this._idlocal = _idlocal;
    }

    public String get_idvariable() {
        return _idvariable;
    }

    public void set_idvariable(String _idvariable) {
        this._idvariable = _idvariable;
    }

    public JSONObject get_data() {
        return _data;
    }

    public void set_data(JSONObject _data) {
        this._data = _data;
    }
}
