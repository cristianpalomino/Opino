package com.capr.beans;

import android.content.Context;

import com.capr.service.Local_Service;
import com.capr.service.Variable_Service;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 19/10/14.
 */
public class Local_DTO {

    private String local_id;
    private String local_nombre;
    private String local_direccion;
    private String local_distrito;
    private String local_canal;
    private String local_latitud;
    private String local_longitud;
    private boolean completado;

    private ArrayList<Variable_DTO> variable_dtos;

    private JSONObject local_json;

    public Local_DTO() {
    }

    public Local_DTO(String local_id, JSONObject local_json, String local_longitud, String local_latitud, String local_canal, String local_distrito, String local_direccion, String local_nombre) {
        this.local_id = local_id;
        this.local_json = local_json;
        this.local_longitud = local_longitud;
        this.local_latitud = local_latitud;
        this.local_canal = local_canal;
        this.local_distrito = local_distrito;
        this.local_direccion = local_direccion;
        this.local_nombre = local_nombre;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }

    public String getLocal_id() {
        try {
            return local_json.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Id";
    }

    public void setLocal_id(String local_id) {
        this.local_id = local_id;
    }

    public String getLocal_nombre() {
        try {
            return local_json.getString("nombre");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Local_Nombre";
    }

    public void setLocal_nombre(String local_nombre) {
        this.local_nombre = local_nombre;
    }

    public String getLocal_distrito() {
        try {
            return local_json.getString("distrito");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Local_Distrito";
    }

    public void setLocal_distrito(String local_distrito) {
        this.local_distrito = local_distrito;
    }

    public String getLocal_direccion() {
        try {
            return local_json.getString("direccion");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Local_Direccion";
    }

    public void setLocal_direccion(String local_direccion) {
        this.local_direccion = local_direccion;
    }

    public String getLocal_canal() {
        try {
            return local_json.getString("canal");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Local_Canal";
    }

    public void setLocal_canal(String local_canal) {
        this.local_canal = local_canal;
    }

    public String getLocal_latitud() {
        try {
            return local_json.getString("latitud");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Latitud";
    }

    public void setLocal_latitud(String local_latitud) {
        this.local_latitud = local_latitud;
    }

    public String getLocal_longitud() {
        try {
            return local_json.getString("longitud");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No_Longitud";
    }

    public void setLocal_longitud(String local_longitud) {
        this.local_longitud = local_longitud;
    }

    public JSONObject getLocal_json() {
        return local_json;
    }

    public void setLocal_json(JSONObject local_json) {
        this.local_json = local_json;
    }

    public ArrayList<Variable_DTO> getVariable_dtos() {
        return variable_dtos;
    }

    public void setVariable_dtos(ArrayList<Variable_DTO> variable_dtos,Context context,boolean state) {
        this.variable_dtos = variable_dtos;
        /**
         * Save to Db
         */
        Variable_Service variable_service = new Variable_Service(context,state);
        variable_service.addVariables(variable_dtos);
    }
}
