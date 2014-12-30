package com.capr.crud_v2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.capr.database.Opino_DB;
import com.capr.utils.Util_Database;

/**
 * Created by Gantz on 17/12/14.
 */
public class Main_CRUD {

    private Context context;
    private Opino_DB opino_db;

    public Main_CRUD(Context context) {
        this.context=context;
        opino_db = new Opino_DB(context);
    }

    public SQLiteDatabase getDatabase() {
        return opino_db.getWritableDatabase();
    }

    public void cleanAllTables(){
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_USUARIO + ";");
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_LOCAL + ";");
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_VARIABLES + ";");
        getDatabase().execSQL("DELETE FROM " + Util_Database.TABLE_CORE + ";");
    }

    protected void cleanTable(){}
}
