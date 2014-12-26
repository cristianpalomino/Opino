package com.capr.beans;

/**
 * Created by Gantz on 21/12/14.
 */
public class Opcion_DTO {

    private boolean presente;
    private boolean daniado;
    private boolean invadido;

    private String nombre;

    public Opcion_DTO(boolean presente, String nombre, boolean daniado, boolean invadido) {
        this.presente = presente;
        this.nombre = nombre;
        this.daniado = daniado;
        this.invadido = invadido;
    }

    public boolean isPresente() {
        return presente;
    }

    public void setPresente(boolean presente) {
        this.presente = presente;
    }

    public boolean isDaniado() {
        return daniado;
    }

    public void setDaniado(boolean daniado) {
        this.daniado = daniado;
    }

    public boolean isInvadido() {
        return invadido;
    }

    public void setInvadido(boolean invadido) {
        this.invadido = invadido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
