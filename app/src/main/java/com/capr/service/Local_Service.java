package com.capr.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.capr.beans.Local_DTO;
import com.capr.dao.Local_DAO;
import com.capr.utils.Util_Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Local_Service extends Main_Service implements Local_DAO {

    private boolean refresh = false;
    private Interface_Service_Locales interface_service_locales;

    public Local_Service(Context context) {
        super(context);
    }

    public Local_Service(Context context, boolean refresh) {
        super(context);
        this.refresh = refresh;
    }

    @Override
    public int addLocales(ArrayList<Local_DTO> local_dtos) {
        if (refresh) {
            /**
             * Limpia la Tabla
             */
            cleanTable();

            for (int i = 0; i < local_dtos.size(); i++) {
                Local_DTO local_dto = local_dtos.get(i);
                ContentValues values = new ContentValues();
                values.put("json_local", local_dto.getLocal_json().toString());
                getDatabase().insert(Util_Database.TABLE_LOCAL, null, values);
            }
        } else {
            for (int i = 0; i < local_dtos.size(); i++) {
                Local_DTO local_dto = local_dtos.get(i);
                ContentValues values = new ContentValues();
                values.put("json_local", local_dto.getLocal_json().toString());
            }
        }

        getLocales();

        return 1;
    }

    @Override
    public int addLocal(Local_DTO local_dto) {
        ContentValues values = new ContentValues();
        values.put("json_local", local_dto.getLocal_json().toString());
        return (int) getDatabase().insert(Util_Database.TABLE_LOCAL, null, values);
    }

    @Override
    public ArrayList<Local_DTO> getLocales() {
        try {
            ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
            String selectQuery = "SELECT  * FROM " + Util_Database.TABLE_LOCAL;
            Cursor cursor = getDatabase().rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    Local_DTO local_dto = new Local_DTO();
                    local_dto.setLocal_json(new JSONObject(cursor.getString(1)));
                    local_dtos.add(local_dto);
                } while (cursor.moveToNext());
            }

            if (interface_service_locales != null) {
                interface_service_locales.onFinish(true, local_dtos);
            }
            return local_dtos;
        } catch (JSONException e) {
            e.printStackTrace();
            if (interface_service_locales != null) {
                interface_service_locales.onFinish(false, null);
            }
            return null;
        }
    }

    @Override
    public void cleanTable() {
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_LOCAL + ";");
    }


    public void setInterface_service_locales(Interface_Service_Locales interface_service_locales) {
        this.interface_service_locales = interface_service_locales;
    }

    /**
     *
     */

    public interface Interface_Service_Locales {
        public void onFinish(boolean state, ArrayList<Local_DTO> local_dtos);
    }
}
