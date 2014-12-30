package com.capr.interfaces_v2;

import com.capr.beans_v2.Variable_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 26/12/14.
 */
public interface OnSuccessVariables {
    void onSuccessVariables(boolean success,ArrayList<Variable_DTO> variable_dtos,String message);
}
