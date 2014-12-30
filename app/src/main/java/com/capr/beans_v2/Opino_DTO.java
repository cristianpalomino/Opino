package com.capr.beans_v2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.capr.beans_v2.Local_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public class Opino_DTO {

    private JSONObject datasource;

    public final String KEY_JSON_ARRAY_RESPUESTA = "respuestas";
    public final String KEY_JSON_ARRAY_LOCAL = "result";


    public ArrayList<Local_DTO> getLocal_dtos() {
        ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
        JSONArray jsonArray = parseJSONArray(KEY_JSON_ARRAY_LOCAL, getDataSource());
        for (int i = 0; i < jsonArray.length(); i++) {
            Local_DTO local_dto = parseLocalDTO(i, jsonArray);
            local_dtos.add(local_dto);
        }
        return local_dtos;
    }

    /**
     * Parse Data(JSON) to Local_DTO
     *
     * @param index
     * @param dataSource
     * @return
     */
    public Local_DTO parseLocalDTO(int index, JSONArray dataSource) {
        try {
            Local_DTO local_dto = new Local_DTO();
            local_dto.setDataSource(dataSource.getJSONObject(index));
            return local_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse Data(JSON) to Local_DTO
     *
     * @param key
     * @param dataSource
     * @return
     */
    public Local_DTO parseLocalDTO(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                Local_DTO local_dto = new Local_DTO();
                local_dto.setDataSource(dataSource.getJSONObject(key));
                return local_dto;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse Data(JSON) to Variable_DTO
     *
     * @param key
     * @param dataSource
     * @return
     */
    public Variable_DTO parseVariableDTO(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                Variable_DTO variable_dto = new Variable_DTO();
                variable_dto.setDataSource(dataSource.getJSONObject(key));
                return variable_dto;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse Data(JSON) to Encuesta_DTO
     *
     * @param key
     * @param dataSource
     * @return
     */
    public Encuesta_DTO parseEncuestaDTO(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                Encuesta_DTO encuesta_dto = new Encuesta_DTO();
                encuesta_dto.setDataSource(dataSource.getJSONObject(key));
                return encuesta_dto;
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse Data(JSON) to Encuesta_DTO
     *
     * @param index
     * @param dataSource
     * @return
     */
    public Encuesta_DTO parseEncuestaDTO(int index, JSONArray dataSource) {
        try {
            Encuesta_DTO encuesta_dto = new Encuesta_DTO();
            encuesta_dto.setDataSource(dataSource.getJSONObject(index));
            return encuesta_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    public ArrayList<Encuesta_DTO> getEncuesta_dtos(JSONArray jsonArray) {
        ArrayList<Encuesta_DTO> encuesta_dtos = new ArrayList<Encuesta_DTO>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Encuesta_DTO encuesta_dto = parseEncuestaDTO(i, jsonArray);
            encuesta_dtos.add(encuesta_dto);
        }
        return encuesta_dtos;
    }

    /**
     * Parse Data(JSON) to Offer_DTO
     *
     * @param index
     * @param dataSource
     * @return
     */
    public Respuesta_DTO parseRespuestaDTO(int index, JSONArray dataSource) {
        try {
            Respuesta_DTO respuesta_dto = new Respuesta_DTO();
            respuesta_dto.setDataSource(dataSource.getJSONObject(index));
            return respuesta_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Parse String
     *
     * @param key
     * @param dataSource
     * @return
     */
    public String parseString(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                return dataSource.getString(key);
            } else {
                return "NULL";
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return "NULL";
        }
    }

    /**
     * Parse Int
     *
     * @param key
     * @param dataSource
     * @return
     */
    public int parseInt(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                return dataSource.getInt(key);
            } else {
                return -1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Parse Boolean
     *
     * @param key
     * @param dataSource
     * @return
     */
    public boolean parseBooelan(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                return dataSource.getBoolean(key);
            } else {
                return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Parse JSON
     *
     * @param key
     * @param dataSource
     * @return
     */
    public JSONObject parseJSON(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                return dataSource.getJSONObject(key);
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Parse JSONArray
     *
     * @param key
     * @param dataSource
     * @return
     */
    public JSONArray parseJSONArray(String key, JSONObject dataSource) {
        try {
            if (!dataSource.isNull(key)) {
                return dataSource.getJSONArray(key);
            } else {
                return null;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Set Datasource API
     *
     * @param datasource
     */
    public void setDataSource(JSONObject datasource) {
        this.datasource = datasource;
    }

    /**
     * Get Datasource API
     *
     * @return
     */
    public JSONObject getDataSource() {
        return datasource;
    }
}
