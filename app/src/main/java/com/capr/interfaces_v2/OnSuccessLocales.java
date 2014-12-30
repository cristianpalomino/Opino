package com.capr.interfaces_v2;

import com.capr.beans_v2.Local_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public interface OnSuccessLocales {
    void onSuccessLocales(boolean success,ArrayList<Local_DTO> local_dtos,String message);
}
