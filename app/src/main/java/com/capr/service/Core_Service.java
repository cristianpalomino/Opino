package com.capr.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.capr.beans.Core_DTO;
import com.capr.beans.Local_DTO;
import com.capr.dao.Core_DAO;
import com.capr.utils.Util_Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Core_Service extends Main_Service implements Core_DAO {

    private boolean refresh = false;

    public Core_Service(Context context) {
        super(context);
    }

    public Core_Service(Context context, boolean refresh) {
        super(context);
        this.refresh = refresh;
    }

    @Override
    public void addCore(Core_DTO core_dto) {
        if (refresh) {
            ContentValues values = new ContentValues();
            values.put("id_local", core_dto.getId_local());
            values.put("id_variable", core_dto.getId_variable());
            values.put("json_variable", core_dto.getJson_core().toString());
            getDatabase().insert(Util_Database.TABLE_CORE, null, values);
        } else {
            ContentValues values = new ContentValues();
            values.put("id_local", core_dto.getId_local());
            values.put("id_variable", core_dto.getId_variable());
            values.put("json_variable", core_dto.getJson_core().toString());
            getDatabase().insert(Util_Database.TABLE_CORE, null, values);
        }
    }

    @Override
    public void updateCore(Core_DTO core_dto) {
        ContentValues values = new ContentValues();
        values.put("id_local", core_dto.getId_local());
        values.put("id_variable", core_dto.getId_variable());
        values.put("json_variable", core_dto.getJson_core().toString());

        getDatabase().update(Util_Database.TABLE_CORE,
                values,
                "id_local = ? AND id_variable = ?",
                new String[]{core_dto.getId_local(), core_dto.getId_variable()}
        );
    }

    @Override
    public Core_DTO getCore(String codigo_local, String codigo_variable) {

        Cursor cursor = getDatabase().query(
                Util_Database.TABLE_CORE,
                new String[]{"id_local", "id_variable", "json_variable"},
                "id_local = ? AND id_variable = ?",
                new String[]{codigo_local, codigo_variable},
                null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        try {
            Core_DTO core_dto = new Core_DTO();
            core_dto.setId_local(cursor.getString(0));
            core_dto.setId_variable(cursor.getString(1));
            core_dto.setJson_core(new JSONArray(cursor.getString(2)));

            return core_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void cleanTable() {
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_CORE + ";");
    }
}
