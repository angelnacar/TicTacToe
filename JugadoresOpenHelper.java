package com.example.angel.eltresenraya;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Angel on 01/03/2018.
 */

public class JugadoresOpenHelper extends SQLiteOpenHelper {
    public static final String LOG_TAG = "logtag";
    public static final String DATABASE_NAME = "jugadores.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_PLAYERS = "tablaDePuntos";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMBRE = "nombre";
    public static final String COLUMN_FOTO = "foto";
    public static final String COLUMN_VICTORIAS = "pGanadas";
    public static final String COLUMN_DERROTAS = "pPerdidas";
    public static final String COLUMN_EMPATES = "empates";
    public static final String COLUMN_TIEMPO = "tiempo";
    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_PLAYERS+" ("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NOMBRE+" TEXT, "+COLUMN_FOTO+" TEXT, "+COLUMN_VICTORIAS+" INTEGER,"+COLUMN_DERROTAS+" INTEGER,"+COLUMN_EMPATES+" INTEGER,"+COLUMN_TIEMPO+" INTEGER )";

    public JugadoresOpenHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PLAYERS);
        onCreate(db);
    }
}
