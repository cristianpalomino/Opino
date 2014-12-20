package com.capr.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.capr.utils.Util_Database;

/**
 * Created by Gantz on 7/12/14.
 */
public class Opino_DB extends SQLiteOpenHelper {

    private Context context;

    public Opino_DB(Context context) {
        super(context, Util_Database.DB_NAME,null,Util_Database.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Util_Database.createTableVariables());
        db.execSQL(Util_Database.createTableUsuario());
        db.execSQL(Util_Database.createTableLocal());
        db.execSQL(Util_Database.createTableCore());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Util_Database.dropTableVariables());
        db.execSQL(Util_Database.dropTableUsuario());
        db.execSQL(Util_Database.dropTableLocal());
        db.execSQL(Util_Database.dropTableCore());
        onCreate(db);
    }
}
