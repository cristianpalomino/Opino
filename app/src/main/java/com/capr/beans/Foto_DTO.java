package com.capr.beans;

import java.util.ArrayList;

/**
 * Created by Gantz on 21/10/14.
 */
public class Foto_DTO {

    private Imagen_DTO foto_imagen_dto;
    private boolean foto_estado;
    private Respuesta_DTO respuesta_dto;

    public Foto_DTO() {
    }

    public Foto_DTO(Imagen_DTO foto_imagen_dto, boolean foto_estado) {
        this.foto_imagen_dto = foto_imagen_dto;
        this.foto_estado = foto_estado;
    }

    public Imagen_DTO getFoto_imagen_dto() {
        return foto_imagen_dto;
    }

    public void setFoto_imagen_dto(Imagen_DTO foto_imagen_dto) {
        this.foto_imagen_dto = foto_imagen_dto;
    }

    public boolean isFoto_estado() {
        return foto_estado;
    }

    public void setFoto_estado(boolean foto_estado) {
        this.foto_estado = foto_estado;
    }

    public Respuesta_DTO getRespuesta_dto() {
        return respuesta_dto;
    }

    public void setRespuesta_dto(Respuesta_DTO respuesta_dto) {
        this.respuesta_dto = respuesta_dto;
    }
}
