package com.capr.beans_v2;

import org.json.JSONObject;

/**
 * Created by Gantz on 26/12/14.
 */
public class Local_DTO extends Opino_DTO {

    private String meta = "meta";

    private String id = "id";
    private String nombre = "nombre";
    private String direccion = "direccion";
    private String distrito = "distrito";
    private String canal = "canal";
    private String latitud = "latitud";
    private String longitud = "longitud";

    public Local_DTO() {
    }

    public String getId() {
        return parseString(id,getDataSource());
    }

    public String getNombre() {
        return parseString(nombre,getDataSource());
    }

    public String getDireccion() {
        return parseString(direccion,getDataSource());
    }

    public String getDistrito() {
        return parseString(distrito,getDataSource());
    }

    public String getCanal() {
        return parseString(canal,getDataSource());
    }

    public String getLatitud() {
        return parseString(latitud,getDataSource());
    }

    public String getLongitud() {
        return parseString(longitud,getDataSource());
    }

    /***
     * Database Atributte's
     */
    private int _id;
    private String _idLocal;
    private String _estado;
    private JSONObject _data;

    public Local_DTO(String _idLocal, String _estado, JSONObject _data) {
        this._idLocal = _idLocal;
        this._estado = _estado;
        this._data = _data;
    }

    public String get_idLocal() {
        return _idLocal;
    }

    public void set_idLocal(String _idLocal) {
        this._idLocal = _idLocal;
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
