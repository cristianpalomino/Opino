package com.capr.beans;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Gantz on 19/08/14.
 */
public class Usuario_DTO {

    private String usuario_id;
    private String usuario_token;
    private String usuario_fecha_expiracion;
    private JSONObject usuario_json;

    public Usuario_DTO() {
    }

    public Usuario_DTO(JSONObject usuario_json) {
        this.usuario_json = usuario_json;
    }

    public Usuario_DTO(String usuario_id, JSONObject usuario_json, String usuario_fecha_expiracion, String usuario_token) {
        this.usuario_id = usuario_id;
        this.usuario_json = usuario_json;
        this.usuario_fecha_expiracion = usuario_fecha_expiracion;
        this.usuario_token = usuario_token;
    }

    public String getUsuario_id() {
        try {
            return usuario_json.getString("usuario_id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Id";
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getUsuario_fecha_expiracion() {
        try {
            return usuario_json.getString("fexpiracion");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Fexpiracion";
    }

    public void setUsuario_fecha_expiracion(String usuario_fecha_expiracion) {
        this.usuario_fecha_expiracion = usuario_fecha_expiracion;
    }

    public String getUsuario_token() {
        try {
            return usuario_json.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Token";
    }

    public void setUsuario_token(String usuario_token) {
        this.usuario_token = usuario_token;
    }

    public JSONObject getUsuario_json() {
        return usuario_json;
    }

    public void setUsuario_json(JSONObject usuario_json) {
        this.usuario_json = usuario_json;
    }
}
