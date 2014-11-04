package com.capr.beans;

/**
 * Created by Gantz on 21/10/14.
 */
public class Variable_DTO {

    private String variable_id;
    private String variable_nombre;

    public Variable_DTO() {
    }

    public Variable_DTO(String variable_id, String variable_nombre) {
        this.variable_id = variable_id;
        this.variable_nombre = variable_nombre;
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
}
