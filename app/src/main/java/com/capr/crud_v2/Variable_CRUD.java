package com.capr.crud_v2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.capr.beans_v2.Local_DTO;
import com.capr.beans_v2.Variable_DTO;
import com.capr.dao_v2.Variable_DAO_v2;
import com.capr.utils.Util_Database;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Gantz on 17/12/14.
 */
public class Variable_CRUD extends Main_CRUD implements Variable_DAO_v2 {

    public Variable_CRUD(Context context) {
        super(context);
    }

    @Override
    public void addVariable(Variable_DTO variable_dto) {
        ContentValues values = new ContentValues();
        values.put("codigo_local", variable_dto.get_idLocal());
        values.put("codigo_variable", variable_dto.get_idVariable());
        values.put("estado", variable_dto.get_estado());
        values.put("json_variable", variable_dto.get_data().toString());
        getDatabase().insert(Util_Database.TABLE_VARIABLES, null, values);
    }

    @Override
    public void updateVariable(Variable_DTO variable_dto) {
        ContentValues values = new ContentValues();
        values.put("estado", variable_dto.get_estado());
        getDatabase().update(Util_Database.TABLE_VARIABLES,
                values,
                "codigo_local = ? and codigo_variable = ?",
                new String[]{variable_dto.get_idLocal(),variable_dto.get_idVariable()}
        );
    }

    @Override
    public Variable_DTO getVariable(Variable_DTO variable_dto) {
        Cursor cursor = getDatabase().query(
                Util_Database.TABLE_VARIABLES,
                new String[]{"codigo_local", "codigo_variable", "estado", "json_variable"},
                "codigo_local = ? and codigo_variable = ?",
                new String[]{variable_dto.get_idLocal(),variable_dto.get_idVariable()},
                null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        try {
            String idlocal = cursor.getString(1);
            String idVariable = cursor.getString(2);
            String estado = cursor.getString(3);
            JSONObject json_variable = new JSONObject(cursor.getString(4));

            Variable_DTO mvariable_dto = new Variable_DTO();
            mvariable_dto.setDataSource(json_variable);
            mvariable_dto.set_idLocal(idlocal);
            mvariable_dto.set_idVariable(idVariable);
            mvariable_dto.set_estado(estado);

            return mvariable_dto;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Variable_DTO> getVariables() {
        try {
            ArrayList<Variable_DTO> local_dtos = new ArrayList<Variable_DTO>();
            String selectQuery = "SELECT * FROM " + Util_Database.TABLE_VARIABLES;
            Cursor cursor = getDatabase().rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String idlocal = cursor.getString(1);
                    String idVariable = cursor.getString(2);
                    String estado = cursor.getString(3);
                    JSONObject json_variable = new JSONObject(cursor.getString(4));

                    Variable_DTO mvariable_dto = new Variable_DTO();
                    mvariable_dto.setDataSource(json_variable);
                    mvariable_dto.set_idLocal(idlocal);
                    mvariable_dto.set_idVariable(idVariable);
                    mvariable_dto.set_estado(estado);

                    local_dtos.add(mvariable_dto);
                } while (cursor.moveToNext());
            }
            return local_dtos;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ArrayList<Variable_DTO> getVariables(Local_DTO local_dto) {
        try {
            ArrayList<Variable_DTO> local_dtos = new ArrayList<Variable_DTO>();
            String selectQuery = "SELECT * FROM " + Util_Database.TABLE_VARIABLES +
                                 " WHERE codigo_local = " + local_dto.getId();

            Log.e("SQL",selectQuery);

            Cursor cursor = getDatabase().rawQuery(selectQuery, null);
            if (cursor.moveToFirst()) {
                do {
                    String idlocal = cursor.getString(1);
                    String idVariable = cursor.getString(2);
                    String estado = cursor.getString(3);
                    JSONObject json_variable = new JSONObject(cursor.getString(4));

                    Variable_DTO mvariable_dto = new Variable_DTO();
                    mvariable_dto.setDataSource(json_variable);
                    mvariable_dto.set_idLocal(idlocal);
                    mvariable_dto.set_idVariable(idVariable);
                    mvariable_dto.set_estado(estado);

                    local_dtos.add(mvariable_dto);
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
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_VARIABLES + ";");
    }
}
