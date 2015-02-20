package com.capr.beans_v2;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    private ArrayList<Child_DTO> hijos_reales;

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

    public String getRespuestaDaniado() {
        return parseString(respuesta, getHijos_reales().get(0).getDataSource());
    }

    public String getRespuestaInvadido() {
        return parseString(respuesta, getHijos_reales().get(1).getDataSource());
    }

    public String getCliente_id() {
        return parseString(cliente_id,getDataSource());
    }

    public ArrayList<Child_DTO> getHijos_reales() {
        return hijos_reales;
    }

    public void setHijos_reales(ArrayList<Child_DTO> hijos_reales) {
        this.hijos_reales = hijos_reales;
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

    public void setRespuestaPop(String mrespuesta) {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(recurso_id, getRecurso_id());
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, mrespuesta);

            JSONArray childs = new JSONArray();
            for (int i = 0; i < getHijos_reales().size() ; i++) {
                childs.put(getHijos_reales().get(i).getDataSourceRespuesta());
            }
            json_respuesta.put("hijos",childs);

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
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, getRespuesta());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json_respuesta;
    }

    public JSONObject getDataSourceRespuestaPop() {
        JSONObject json_respuesta = new JSONObject();
        try {
            json_respuesta.put(tipo, getTipo());
            json_respuesta.put(variable_id, getVariable_id());
            json_respuesta.put(variable_nombre, getVariable_nombre());
            json_respuesta.put(respuesta, getRespuesta());

            JSONArray childs = new JSONArray();
            for (int i = 0; i < getHijos_reales().size() ; i++) {
                childs.put(getHijos_reales().get(i).getDataSourceRespuesta());
            }
            json_respuesta.put("hijos",childs);

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

    public ArrayList<Child_DTO> getChild_dtos() {
        ArrayList<Child_DTO> child_dtos = new ArrayList<Child_DTO>();
        try {
            JSONArray array = getDataSource().getJSONArray("hijos");
            for (int i = 0; i < array.length(); i++) {
                Child_DTO child_dto = new Child_DTO();
                child_dto.setDataSource(array.getJSONObject(i));
                child_dtos.add(child_dto);
            }
            return child_dtos;
        } catch (Exception e) {
            return child_dtos;
        }
    }

    public void updateChild(){
        hijos_reales = getChild_dtos();
    }
}
