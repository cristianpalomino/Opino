package com.capr.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.capr.utils.Util_Database;

/**
 * Created by Gantz on 7/12/14.
 */
public class Db_Opino_Helper extends SQLiteOpenHelper {

    private Context context;

    public Db_Opino_Helper(Context context) {
        super(context, Util_Database.DB_NAME,null,Util_Database.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Util_Database.createTableUsuario());
        db.execSQL(Util_Database.createTableLocal());
        db.execSQL(Util_Database.createTableSKU());
        db.execSQL(Util_Database.createTableAfiche());
        db.execSQL(Util_Database.createTablePromocion());
        db.execSQL(Util_Database.createTableCalidad());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Util_Database.dropTableUsuario());
        db.execSQL(Util_Database.dropTableLocal());
        db.execSQL(Util_Database.dropTableSKU());
        db.execSQL(Util_Database.dropTableAfiche());
        db.execSQL(Util_Database.dropTableCalidad());
        onCreate(db);
    }
}
