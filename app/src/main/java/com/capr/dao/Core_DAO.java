package com.capr.dao;

import com.capr.beans.Core_DTO;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public interface Core_DAO {

    public void addCore(Core_DTO core_dto);
    public void updateCore(Core_DTO core_dto);
    public Core_DTO getCore(String codigo_local,String codigo_variable);

    public void cleanTable();
}
