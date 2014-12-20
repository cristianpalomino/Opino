package com.capr.beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/10/14.
 */
public class Core_DTO {

    private String id_local;
    private String id_variable;
    private JSONArray json_core;

    public String getId_local() {
        return id_local;
    }

    public void setId_local(String id_local) {
        this.id_local = id_local;
    }

    public String getId_variable() {
        return id_variable;
    }

    public void setId_variable(String id_variable) {
        this.id_variable = id_variable;
    }

    public JSONArray getJson_core() {
        return json_core;
    }

    public void setJson_core(JSONArray json_core) {
        this.json_core = json_core;
    }
}

