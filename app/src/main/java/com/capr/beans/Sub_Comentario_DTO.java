package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Sub_Comentario_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean subcomentario_estado;



    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean issubcomentario_estado() {
        return subcomentario_estado;
    }

    public void setsubcomentario_estado(boolean subcomentario_estado) {
        this.subcomentario_estado = subcomentario_estado;
    }
}
