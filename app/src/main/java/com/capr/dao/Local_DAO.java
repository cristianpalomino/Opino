package com.capr.dao;

import com.capr.beans.Local_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public interface Local_DAO {

    public int addLocal(Local_DTO local_dto);
    public int addLocales(ArrayList<Local_DTO> local_dtos);
    public ArrayList<Local_DTO> getLocales();
    public void cleanTable();

}
