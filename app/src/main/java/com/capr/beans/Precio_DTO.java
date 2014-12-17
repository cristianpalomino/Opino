package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Precio_DTO {

    private Respuesta_DTO respuesta_dto;
    private boolean precio_estado;

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }

    public boolean isprecio_estado() {
        return precio_estado;
    }

    public void setprecio_estado(boolean precio_estado) {
        this.precio_estado = precio_estado;
    }
}
