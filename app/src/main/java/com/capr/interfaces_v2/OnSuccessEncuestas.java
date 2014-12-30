package com.capr.interfaces_v2;

import com.capr.beans_v2.Encuesta_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public interface OnSuccessEncuestas {
    void onSuccessEncuestas(boolean success,ArrayList<Encuesta_DTO> encuesta_dtos,String message);
}
