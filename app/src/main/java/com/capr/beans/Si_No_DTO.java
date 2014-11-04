package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Si_No_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean si_no_estado;

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean isSi_no_estado() {
        return si_no_estado;
    }

    public void setSi_no_estado(boolean si_no_estado) {
        this.si_no_estado = si_no_estado;
    }
}
