package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Variable_DTO {

    private String id_local;
    private String variable_id;
    private String variable_nombre;
    private boolean completado;


    public Variable_DTO() {
    }

    public Variable_DTO(String id_local,String variable_id, boolean completado, String variable_nombre) {
        this.id_local = id_local;
        this.variable_id = variable_id;
        this.completado = completado;
        this.variable_nombre = variable_nombre;
    }

    public String getId_local() {
        return id_local;
    }

    public void setId_local(String id_local) {
        this.id_local = id_local;
    }

    public String getVariable_id() {
        return variable_id;
    }

    public void setVariable_id(String variable_id) {
        this.variable_id = variable_id;
    }

    public String getVariable_nombre() {
        return variable_nombre;
    }

    public void setVariable_nombre(String variable_nombre) {
        this.variable_nombre = variable_nombre;
    }

    public boolean isCompletado() {
        return completado;
    }

    public void setCompletado(boolean completado) {
        this.completado = completado;
    }
}
