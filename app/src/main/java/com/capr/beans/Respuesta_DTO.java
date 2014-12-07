package com.capr.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 22/10/14.
 */
public class Respuesta_DTO {

    private String respuesta_recurso_id;
    private String respuesta_tipo;
    private String respuesta_variable_id;
    private String variable_nombre;
    private String respuesta_string;
    private int respuesta_int = 0;

    private JSONObject respuesta_json;

    public Respuesta_DTO() {
    }

    public Respuesta_DTO(JSONObject respuesta_json) {
        this.respuesta_json = respuesta_json;
    }

    public Respuesta_DTO(String respuesta_recurso_id, JSONObject respuesta_json, String respuesta_string, String respuesta_variable_id, String respuesta_tipo) {
        this.respuesta_recurso_id = respuesta_recurso_id;
        this.respuesta_json = respuesta_json;
        this.respuesta_string = respuesta_string;
        this.respuesta_variable_id = respuesta_variable_id;
        this.respuesta_tipo = respuesta_tipo;
    }

    public Respuesta_DTO(String respuesta_recurso_id, int respuesta_int, String respuesta_variable_id, String respuesta_tipo, JSONObject respuesta_json) {
        this.respuesta_recurso_id = respuesta_recurso_id;
        this.respuesta_int = respuesta_int;
        this.respuesta_variable_id = respuesta_variable_id;
        this.respuesta_tipo = respuesta_tipo;
        this.respuesta_json = respuesta_json;
    }

    public String getRespuesta_recurso_id() {
        try {
            return respuesta_json.getString("recurso_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Recurso_Id";
    }

    public JSONObject getRespuesta_json() {
        JSONObject json_respuesta = new JSONObject();
        try {
            if (getRespuesta_recurso_id().endsWith("null")) {
                json_respuesta.put("recurso_id", JSONObject.NULL);
            } else {
                json_respuesta.put("recurso_id", getRespuesta_recurso_id());
            }
            if (getRespuesta_tipo().endsWith("null")) {
                json_respuesta.put("tipo", JSONObject.NULL);
            } else {
                json_respuesta.put("tipo", getRespuesta_tipo());
            }
            if (getRespuesta_variable_id().endsWith("null")) {
                json_respuesta.put("variable_id", JSONObject.NULL);
            } else {
                json_respuesta.put("variable_id", getRespuesta_variable_id());
            }

            if(getRespuesta_string() != null){
                if (getRespuesta_string().equals("null")) {
                    json_respuesta.put("respuesta", JSONObject.NULL);
                } else {
                    json_respuesta.put("respuesta", getRespuesta_string());
                }
            }else{
                if (getRespuesta_int() == 0) {
                    json_respuesta.put("respuesta", JSONObject.NULL);
                } else {
                    json_respuesta.put("respuesta", getRespuesta_int());
                }
            }

            return json_respuesta;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getRespuesta_int() {
        return respuesta_int;
    }

    public String getRespuesta_string() {
        return respuesta_string;
    }

    public String getRespuesta_variable_id() {
        try {
            return respuesta_json.getString("variable_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Variable_Id";
    }

    public String getRespuesta_tipo() {
        try {
            return respuesta_json.getString("tipo");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Tipo";
    }

    public String getVariable_nombre() {
        try {
            return respuesta_json.getString("variable_nombre");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_variable_nombre";
    }

    public void setRespuesta_json(JSONObject respuesta_json) {
        this.respuesta_json = respuesta_json;
    }

    public void setRespuesta_int(int respuesta_int) {
        this.respuesta_int = respuesta_int;
    }

    public void setRespuesta_string(String respuesta_string) {
        this.respuesta_string = respuesta_string;
    }

    public void setRespuesta_variable_id(String respuesta_variable_id) {
        this.respuesta_variable_id = respuesta_variable_id;
    }

    public void setRespuesta_recurso_id(String respuesta_recurso_id) {
        this.respuesta_recurso_id = respuesta_recurso_id;
    }

    public void setRespuesta_tipo(String respuesta_tipo) {
        this.respuesta_tipo = respuesta_tipo;
    }
}
