package com.capr.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.capr.beans.Variable_DTO;
import com.capr.dao.Variable_DAO;
import com.capr.database.Opino_DB;
import com.capr.utils.Util_Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gantz on 17/12/14.
 */
public class Variable_Service extends  Main_Service implements Variable_DAO {

    private boolean refresh = false;

    public Variable_Service(Context context,boolean refresh) {
        super(context);
        this.refresh = refresh;
    }

    public Variable_Service(Context context) {
        super(context);
    }

    @Override
    public int addVariables(ArrayList<Variable_DTO> variable_dtos) {
        if(refresh){
            for (int i = 0; i < variable_dtos.size(); i++) {
                Variable_DTO variable_dto = variable_dtos.get(i);
                ContentValues values = new ContentValues();
                values.put("codigo_local",variable_dto.getId_local());
                values.put("nombre_variable",variable_dto.getVariable_nombre());
                values.put("codigo_variable",variable_dto.getVariable_id());
                values.put("estado_variable", variable_dto.isCompletado());
                getDatabase().insert(Util_Database.TABLE_VARIABLES,null,values);
            }
        }else{
            cleanTable(variable_dtos.get(0).getId_local());
            for (int i = 0; i < variable_dtos.size(); i++) {
                Variable_DTO variable_dto = variable_dtos.get(i);
                ContentValues values = new ContentValues();
                values.put("codigo_local",variable_dto.getId_local());
                values.put("nombre_variable",variable_dto.getVariable_nombre());
                values.put("codigo_variable",variable_dto.getVariable_id());
                values.put("estado_variable", variable_dto.isCompletado());
                getDatabase().insert(Util_Database.TABLE_VARIABLES,null,values);
            }
        }
        return 1;
    }

    @Override
    public int addVariable(Variable_DTO variable_dto) {
        ContentValues values = new ContentValues();
        values.put("codigo_local",variable_dto.getId_local());
        values.put("nombre_variable",variable_dto.getVariable_nombre());
        values.put("codigo_variable",variable_dto.getVariable_id());
        values.put("estado_variable", variable_dto.isCompletado());
        return (int)getDatabase().insert(Util_Database.TABLE_VARIABLES,null,values);
    }

    @Override
    public ArrayList<Variable_DTO> getVariables(String codigo_local) {
        ArrayList<Variable_DTO> variable_dtos = new ArrayList<Variable_DTO>();
        String selectQuery = "SELECT  * FROM " + Util_Database.TABLE_VARIABLES +" WHERE codigo_local = " + codigo_local;
        Cursor cursor = getDatabase().rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Variable_DTO variable_dto = new Variable_DTO();
                variable_dto.setId_local(cursor.getString(1));
                variable_dto.setVariable_nombre(cursor.getString(2));
                variable_dto.setVariable_id(cursor.getString(3));

                if(cursor.getInt(4) == 0){
                    variable_dto.setCompletado(false);
                }else{
                    variable_dto.setCompletado(true);
                }
                variable_dtos.add(variable_dto);
            } while (cursor.moveToNext());
        }
        return variable_dtos;
    }

    @Override
    public void cleanTable(String local_id) {
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_VARIABLES + " WHERE codigo_local = " + local_id +";");
    }
}
