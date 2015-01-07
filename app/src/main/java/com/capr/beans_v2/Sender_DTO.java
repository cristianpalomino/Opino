package com.capr.beans_v2;

import org.json.JSONArray;

/**
 * Created by Gantz on 6/01/15.
 */
public class Sender_DTO {

    private JSONArray respuestas;
    private Local_DTO local_dto;
    private String idVariable;

    public Sender_DTO() {
    }

    public JSONArray getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(JSONArray respuestas) {
        this.respuestas = respuestas;
    }

    public Local_DTO getLocal_dto() {
        return local_dto;
    }

    public void setLocal_dto(Local_DTO local_dto) {
        this.local_dto = local_dto;
    }

    public String getIdVariable() {
        return idVariable;
    }

    public void setIdVariable(String idVariable) {
        this.idVariable = idVariable;
    }
}
