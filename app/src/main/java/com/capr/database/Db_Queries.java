package com.capr.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Gantz on 7/12/14.
 */
public class Db_Queries {

    private SQLiteDatabase db;

    public Db_Queries(Context context) {
        db = new Db_Opino_Helper(context).getWritableDatabase();
    }
}
