package com.capr.application;

import android.app.Application;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 30/12/14.
 */
public class Opino_Application extends Application {

    private Local_DTO local_dto;
    private Variable_DTO variable_dto;
    private Encuesta_DTO encuesta_dto;
    private ArrayList<Encuesta_DTO> encuesta_dtos;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Local_DTO getLocal_dto() {
        return local_dto;
    }

    public void setLocal_dto(Local_DTO local_dto) {
        this.local_dto = local_dto;
    }

    public Variable_DTO getVariable_dto() {
        return variable_dto;
    }

    public void setVariable_dto(Variable_DTO variable_dto) {
        this.variable_dto = variable_dto;
    }

    public Encuesta_DTO getEncuesta_dto() {
        return encuesta_dto;
    }

    public void setEncuesta_dto(Encuesta_DTO encuesta_dto) {
        this.encuesta_dto = encuesta_dto;
    }

    public void setEncuesta_dtos(ArrayList<Encuesta_DTO> encuesta_dtos) {
        this.encuesta_dtos = encuesta_dtos;
    }

    public ArrayList<Encuesta_DTO> getEncuesta_dtos() {
        return encuesta_dtos;
    }
}
