package com.capr.dao_v2;

import com.capr.beans_v2.Local_DTO;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 28/12/14.
 */
public interface Local_DAO_v2 {
    void addLocal(Local_DTO local_dto);
    void updateLocal(Local_DTO local_dto);
    Local_DTO getLocal(Local_DTO local_dto);
    ArrayList<Local_DTO> getLocales();
}
