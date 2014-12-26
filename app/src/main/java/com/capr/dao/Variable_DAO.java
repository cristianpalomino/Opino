package com.capr.dao;

import com.capr.beans.Variable_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public interface Variable_DAO {

    public int addVariable(Variable_DTO variable_dto);
    public int addVariables(ArrayList<Variable_DTO> variable_dtos);
    public ArrayList<Variable_DTO> getVariables(String codigo_local);
    public void cleanTable(String local_id);
    public void updateVariable(String codigo_local,String nombre_variable,String estado);
}
