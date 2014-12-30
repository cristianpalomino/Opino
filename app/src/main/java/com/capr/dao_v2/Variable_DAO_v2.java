package com.capr.dao_v2;

import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 28/12/14.
 */
public interface Variable_DAO_v2 {
    void addVariable(Variable_DTO variable_dto);
    void updateVariable(Variable_DTO variable_dto);
    Variable_DTO getVariable(Variable_DTO variable_dto);
    ArrayList<Variable_DTO> getVariables(Local_DTO local_dto);
    ArrayList<Variable_DTO> getVariables();
}
