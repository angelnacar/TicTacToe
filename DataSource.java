package com.example.angel.eltresenraya;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import static com.example.angel.eltresenraya.JugadoresOpenHelper.COLUMN_VICTORIAS;

/**
 * Created by Angel on 01/03/2018.
 */

public class DataSource {
    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    public static final String[] todas = {JugadoresOpenHelper.COLUMN_ID, JugadoresOpenHelper.COLUMN_NOMBRE, JugadoresOpenHelper.COLUMN_FOTO, COLUMN_VICTORIAS, JugadoresOpenHelper.COLUMN_DERROTAS, JugadoresOpenHelper.COLUMN_EMPATES, JugadoresOpenHelper.COLUMN_TIEMPO};

    public DataSource(Context context) {
        dbhelper = new JugadoresOpenHelper(context);
    }

    public void Open() {
        database = dbhelper.getWritableDatabase();  //abre la bbdd en modo escritura
    }

    public void Close() {
        dbhelper.close();

    }

    public void Create(Jugadores jugador) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(JugadoresOpenHelper.COLUMN_NOMBRE, jugador.getNombre());
        contentValues.put(JugadoresOpenHelper.COLUMN_FOTO, jugador.getRuta());
        contentValues.put(JugadoresOpenHelper.COLUMN_VICTORIAS, jugador.getPartidasGanadas());
        contentValues.put(JugadoresOpenHelper.COLUMN_DERROTAS, jugador.getPartidasPerdidas());
        contentValues.put(JugadoresOpenHelper.COLUMN_EMPATES, jugador.getEmpates());
        contentValues.put(JugadoresOpenHelper.COLUMN_TIEMPO, jugador.getTiempo());
        database.insert(JugadoresOpenHelper.TABLE_PLAYERS, null, contentValues);


    }
//actualiza datos de la bbdd
    public void Actualizar(String nombre, String modo, int tiempo) {
        database.execSQL("UPDATE tablaDePuntos set "+modo+" = "+modo+" + 1, tiempo = tiempo + "+tiempo+" WHERE nombre = '"+nombre+"'");
    }

    public List<Jugadores> muestraTodo() {
        Cursor cursor = database.query(JugadoresOpenHelper.TABLE_PLAYERS, todas, null, null, null, null, COLUMN_VICTORIAS+" DESC" );
        List<Jugadores> jugadores = enviarLista(cursor);
        return jugadores;
    }

    public List<Jugadores> muestraCondicion(String query) {
        List<Jugadores> jugadores;
        try (Cursor cursor = database.rawQuery("SELECT*FROM tablaDePuntos WHERE nombre = '" + query + "'", null)) {
            jugadores = enviarLista(cursor);
        }
        return jugadores;
    }

    public List<Jugadores> enviarLista(Cursor cursor) {
        List<Jugadores> lista = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Jugadores jugadores = new Jugadores();
                //jugadores.setId(cursor.getInt(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_ID)));
                jugadores.setRuta(cursor.getString(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_FOTO)));
                jugadores.setNombre(cursor.getString(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_NOMBRE)));
                jugadores.setPartidasGanadas(cursor.getInt(cursor.getColumnIndex(COLUMN_VICTORIAS)));
                jugadores.setPartidasPerdidas(cursor.getInt(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_DERROTAS)));
                jugadores.setEmpates(cursor.getInt(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_EMPATES)));
                jugadores.setEmpates(cursor.getInt(cursor.getColumnIndex(JugadoresOpenHelper.COLUMN_TIEMPO)));

                lista.add(jugadores);
            }

        }
        return lista;
    }

}
