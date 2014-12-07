package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Atencion_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean rango_estado;

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean isrango_estado() {
        return rango_estado;
    }

    public void setrango_estado(boolean rango_estado) {
        this.rango_estado = rango_estado;
    }
}
