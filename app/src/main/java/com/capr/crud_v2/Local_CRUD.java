package com.capr.crud_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.capr.beans.Core_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.dao_v2.Local_DAO_v2;
import com.capr.utils.Util_Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Local_CRUD extends Main_CRUD implements Local_DAO_v2 {

    public Local_CRUD(Context context) {
        super(context);
    }

    @Override
    public void addLocal(Local_DTO local_dto) {
        ContentValues values = new ContentValues();
        values.put("id_local", local_dto.get_idLocal());
        values.put("estado", local_dto.get_estado());
        values.put("json_local", local_dto.get_data().toString());
        getDatabase().insert(Util_Database.TABLE_LOCAL, null, values);
    }

    @Override
    public void updateLocal(Local_DTO local_dto) {
        ContentValues values = new ContentValues();
        values.put("estado", local_dto.get_estado());
        getDatabase().update(Util_Database.TABLE_LOCAL,
                values,
                "id_local = ?",
                new String[]{local_dto.get_idLocal()}
        );
    }

    @Override
    public Local_DTO getLocal(Local_DTO local_dto) {
        Cursor cursor = getDatabase().query(
                Util_Database.TABLE_LOCAL,
                new String[]{"id_local", "estado", "json_local"},
                "id_local = ?",
                new String[]{local_dto.get_idLocal()},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
            String idlocal = cursor.getString(1);
            String estado = cursor.getString(2);
            JSONObject json_local = new JSONObject(cursor.getString(3));

            Local_DTO mlocal_dto = new Local_DTO();
            mlocal_dto.setDataSource(json_local);
            mlocal_dto.set_idLocal(idlocal);
            mlocal_dto.set_estado(estado);

            return mlocal_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Local_DTO> getLocales() {
        try {
            ArrayList<Local_DTO> local_dtos = new ArrayList<Local_DTO>();
            String selectQuery = "SELECT * FROM " + Util_Database.TABLE_LOCAL;
            Cursor cursor = getDatabase().rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String idlocal = cursor.getString(1);
                    String estado = cursor.getString(2);
                    JSONObject json_local = new JSONObject(cursor.getString(3));

                    Local_DTO local_dto = new Local_DTO();
                    local_dto.setDataSource(json_local);
                    local_dto.set_idLocal(idlocal);
                    local_dto.set_estado(estado);

                    local_dtos.add(local_dto);
                } while (cursor.moveToNext());
            }
            return local_dtos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanTable() {
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_LOCAL + ";");
    }
}
