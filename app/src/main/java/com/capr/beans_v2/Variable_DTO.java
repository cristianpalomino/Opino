package com.capr.beans_v2;

import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Variable_DTO extends Opino_DTO {

    private String id = "id";
    private String idlocal = "idlocal";
    private String nombre = "nombre";
    private String idvariable = "idvariable";
    private String estado = "estado";

    public String getId() {
        return parseString(id,getDataSource());
    }

    public String getIdlocal() {
        return parseString(idlocal,getDataSource());
    }

    public String getNombre() {
        return parseString(nombre,getDataSource());
    }

    public String getIdvariable() {
        return parseString(idvariable,getDataSource());
    }

    public boolean getEstado() {
        return parseBooelan(estado,getDataSource());
    }

    /***
     *
     */

    private String _idLocal;
    private String _idVariable;
    private String _estado;
    private JSONObject _data;

    public String get_idLocal() {
        return _idLocal;
    }

    public void set_idLocal(String _idLocal) {
        this._idLocal = _idLocal;
    }

    public String get_idVariable() {
        return _idVariable;
    }

    public void set_idVariable(String _idVariable) {
        this._idVariable = _idVariable;
    }

    public String get_estado() {
        return _estado;
    }

    public void set_estado(String _estado) {
        this._estado = _estado;
    }

    public JSONObject get_data() {
        return _data;
    }

    public void set_data(JSONObject _data) {
        this._data = _data;
    }
}
