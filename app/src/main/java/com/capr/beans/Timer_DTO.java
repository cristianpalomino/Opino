package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Timer_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean tim_estado;

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean istim_estado() {
        return tim_estado;
    }

    public void settim_estado(boolean tim_estado) {
        this.tim_estado = tim_estado;
    }
}
