package com.capr.beans_v2;

/**
 * Created by Gantz on 26/12/14.
 */
public class User_DTO extends Opino_DTO {

    private String usuario_id = "usuario_id";
    private String token = "token";
    private String fexpiracion = "fexpiracion";

    public String getUsuario_id() {
        return parseString(usuario_id,getDataSource());
    }

    public String getFexpiracion() {
        return parseString(fexpiracion,getDataSource());
    }

    public String getToken() {
        return parseString(token,getDataSource());
    }


}
