package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Comentario_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean comentario_estado;

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean iscomentario_estado() {
        return comentario_estado;
    }

    public void setcomentario_estado(boolean comentario_estado) {
        this.comentario_estado = comentario_estado;
    }
}
