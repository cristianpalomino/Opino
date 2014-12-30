package com.capr.dao_v2;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 28/12/14.
 */
public interface Encuesta_DAO_v2 {
    void addEncuesta(Encuesta_DTO encuesta_dto);
    void updateEncuesta(Encuesta_DTO encuesta_dto);
    void updatePhotoEncuesta(Encuesta_DTO encuesta_dto);
    Encuesta_DTO getEncuesta(Encuesta_DTO encuesta_dto);
    ArrayList<Encuesta_DTO> getEncuestas();
    ArrayList<Encuesta_DTO> getEncuestas(String mid_local,String mid_variable);
}
