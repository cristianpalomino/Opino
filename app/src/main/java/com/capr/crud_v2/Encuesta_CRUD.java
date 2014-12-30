package com.capr.crud_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.capr.beans_v2.Encuesta_DTO;
import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.dao_v2.Encuesta_DAO_v2;
import com.capr.utils.Util_Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Encuesta_CRUD extends Main_CRUD implements Encuesta_DAO_v2 {

    public Encuesta_CRUD(Context context) {
        super(context);
    }

    @Override
    public void addEncuesta(Encuesta_DTO encuesta_dto) {
        ContentValues values = new ContentValues();
        values.put("id_local", encuesta_dto.get_idlocal());
        values.put("id_variable", encuesta_dto.get_idvariable());
        values.put("foto", encuesta_dto.get_uri());
        values.put("json_variable", encuesta_dto.get_data().toString());
        getDatabase().insert(Util_Database.TABLE_CORE, null, values);
    }

    @Override
    public void updateEncuesta(Encuesta_DTO encuesta_dto) {
        ContentValues values = new ContentValues();
        values.put("json_variable", encuesta_dto.getDataSource().toString());
        getDatabase().update(Util_Database.TABLE_CORE,
                values,
                "id_local = ? AND id_variable = ? AND _id = ?",
                new String[]{encuesta_dto.get_idlocal(),encuesta_dto.get_idvariable(),encuesta_dto.get_id()}
        );
    }

    @Override
    public void updatePhotoEncuesta(Encuesta_DTO encuesta_dto) {
        ContentValues values = new ContentValues();
        values.put("foto", encuesta_dto.get_uri());
        getDatabase().update(Util_Database.TABLE_CORE,
                values,
                "id_local = ? AND id_variable = ? AND _id = ?",
                new String[]{encuesta_dto.get_idlocal(),encuesta_dto.get_idvariable(),encuesta_dto.get_id()}
        );
    }

    @Override
    public Encuesta_DTO getEncuesta(Encuesta_DTO encuesta_dto) {
        Cursor cursor = getDatabase().query(
                Util_Database.TABLE_CORE,
                new String[]{"_id","id_local", "id_variable","foto", "json_variable"},
                "id_local = ? AND id_variable = ? AND _id = ?",
                new String[]{encuesta_dto.get_idlocal(),encuesta_dto.get_idvariable(),encuesta_dto.get_id()},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
            String _id = String.valueOf(cursor.getInt(0));
            String id_local = cursor.getString(1);
            String id_variable = cursor.getString(2);
            String uri = cursor.getString(3);
            JSONObject data = new JSONObject(cursor.getString(4));

            Encuesta_DTO mencuesta_dto = new Encuesta_DTO();
            mencuesta_dto.set_id(_id);
            mencuesta_dto.set_idlocal(id_local);
            mencuesta_dto.set_idvariable(id_variable);
            mencuesta_dto.set_uri(uri);
            mencuesta_dto.setDataSource(data);

            return mencuesta_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Encuesta_DTO> getEncuestas(String mid_local,String mid_variable) {
        try {
            ArrayList<Encuesta_DTO> encuesta_dtos = new ArrayList<Encuesta_DTO>();
            String selectQuery = "SELECT * FROM " + Util_Database.TABLE_CORE +
                                 " WHERE id_local = " + mid_local +
                                 " AND id_variable = '" + mid_variable + "'";

            Cursor cursor = getDatabase().rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String _id = String.valueOf(cursor.getInt(0));
                    String id_local = cursor.getString(1);
                    String id_variable = cursor.getString(2);
                    String uri = cursor.getString(3);
                    JSONObject data = new JSONObject(cursor.getString(4));

                    Encuesta_DTO mencuesta_dto = new Encuesta_DTO();
                    mencuesta_dto.setDataSource(data);
                    mencuesta_dto.set_id(_id);
                    mencuesta_dto.set_idlocal(id_local);
                    mencuesta_dto.set_idvariable(id_variable);
                    mencuesta_dto.set_uri(uri);
                    encuesta_dtos.add(mencuesta_dto);

                } while (cursor.moveToNext());
            }
            return encuesta_dtos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Encuesta_DTO> getEncuestas() {
        return null;
    }

    @Override
    public void cleanTable() {
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_CORE + ";");
    }
}
